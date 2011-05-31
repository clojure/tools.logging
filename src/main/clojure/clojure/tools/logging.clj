;;; logging.clj -- delegated logging for Clojure

;; by Alex Taggart
;; July 27, 2009

;; Copyright (c) Alex Taggart, July 2009. All rights reserved.  The use
;; and distribution terms for this software are covered by the Eclipse
;; Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;; which can be found in the file epl-v10.html at the root of this
;; distribution.  By using this software in any fashion, you are
;; agreeing to be bound by the terms of this license.  You must not
;; remove this notice, or any other, from this software.
(ns ^{:author "Alex Taggart"
      :doc "Logging macros which delegate to a specific logging implementation. At
            runtime a specific implementation is selected from, in order, Apache
            commons-logging, slf4j, log4j, and finally java.util.logging."}
  clojure.tools.logging
  [:use
   [clojure.string :only [trim-newline]]
   [clojure.pprint :only [code-dispatch pprint with-pprint-dispatch]]])

(defprotocol Logger
  "The protocol through which macros will interact with an underlying logging
  implementation.  Implementations should at least support the six specified
  logging levels if they wish to benefit from the level-specific macros."
  (impl-enabled? [logger level]
    "Implementation-specific check if a particular level is enabled. End-users
    should not need to call this.")
  (impl-write! [logger level throwable message]
    "Implementation-specific write of a log message. End-users should not need
    to call this."))

(defprotocol LoggerFactory
  "The protocol through which macros will obtain an instance satisfying Logger as
  well as providing information about the particular implementation being used.
  Implementations should be bound to *logger-factory* in order to be picked up by
  this library."
  (impl-name [factory]
    "Returns some text identifying the underlying implementation.")
  (impl-get-logger [factory log-ns]
    "Returns an implementation-specific Logger by namespace. End-users should not
    need to call this."))

(def ^{:doc
  "The default agent used for performing logging when direct logging is
  disabled. See log* for details." :dynamic true}
  *logging-agent* (agent nil :error-mode :continue))

(def ^{:doc
  "The set of levels that will require using an agent when logging from within a
  running transaction. Defaults to #{:info :warn}. See log* for details." :dynamic true}
  *tx-agent-levels* #{:info :warn})

(def ^{:doc
  "Overrides the default rules for choosing between logging directly or via an
  agent. Defaults to nil. See log* for details." :dynamic true}
  *force* nil)

(defn log*
  "Attempts to log a message, either directly or via an agent; does not check if
  the level is enabled.

  For performance reasons, an agent will only be used when invoked within a
  running transaction, and only for logging levels specified by
  *tx-agent-levels*. This allows those entries to only be written once the
  transaction commits, and are discarded if it is retried or aborted.  As
  corollary, other levels (e.g., :debug, :error) will be written even from
  failed transactions though at the cost of repeat messages during retries.

  One can override the above by setting *force* to :direct or :agent; all
  subsequent writes will be direct or via an agent, respectively."
  [logger level throwable message]
  (if (cond
        (nil? *force*) (and (clojure.lang.LockingTransaction/isRunning)
                         (*tx-agent-levels* level))
        (= *force* :agent) true
        (= *force* :direct) false)
    (send-off *logging-agent*
      (fn [_#] (impl-write! logger level throwable message)))
    (impl-write! logger level throwable message)))

(declare ^{:dynamic true} *logger-factory*) ; default LoggerFactory instance for calling impl-get-logger

(defmacro log
  "Evaluates and logs a message only if the specified level is enabled. See log*
  for more details."
  ([level message]
    `(log ~level nil ~message))
  ([level throwable message]
    `(log ~*ns* ~level ~throwable ~message))
  ([log-ns level throwable message]
    `(log *logger-factory* ~log-ns ~level ~throwable ~message))
  ([logger-factory log-ns level throwable message]
    `(let [logger# (impl-get-logger ~logger-factory ~log-ns)]
       (if (impl-enabled? logger# ~level)
         (log* logger# ~level ~throwable ~message)))))

(defmacro logp
  "Logs a message using print style args. Can optionally take a throwable as its
  second arg. See level-specific macros, e.g., debug."
  {:arglists '([level message & more] [level throwable message & more])}
  [level x & more]
  (if (or (instance? String x) (nil? more)) ; optimize for common case
    `(log ~level (print-str ~x ~@more))
    `(let [logger# (impl-get-logger *logger-factory* ~*ns*)]
       (if (impl-enabled? logger# ~level)
         (if (instance? Throwable ~x) ; type check only when enabled
           (log* logger# ~level ~x (print-str ~@more))
           (log* logger# ~level nil (print-str ~x ~@more)))))))

(defmacro logf
  "Logs a message using a format string and args. Can optionally take a
  throwable as its second arg. See level-specific macros, e.g., debugf."
  {:arglists '([level fmt & fmt-args] [level throwable fmt & fmt-args])}
  [level x & more]
  (if (or (instance? String x) (nil? more)) ; optimize for common case
    `(log ~level (format ~x ~@more))
    `(let [logger# (impl-get-logger *logger-factory* ~*ns*)]
       (if (impl-enabled? logger# ~level)
         (if (instance? Throwable ~x) ; type check only when enabled
           (log* logger# ~level ~x (format ~@more))
           (log* logger# ~level (format ~x ~@more)))))))

(defmacro enabled?
  "Returns true if the specific logging level is enabled.  Use of this function
  should only be necessary if one needs to execute alternate code paths beyond
  whether the logger should be written to."
  ([level]
    `(enabled? ~level ~*ns*))
  ([level log-ns]
    `(impl-enabled? (impl-get-logger *logger-factory* ~log-ns) ~level)))

(defmacro spy
  "Evaluates expr and writes the form and its result to the logger. Returns the
  result of expr. Defaults to debug log level."
  ([expr]
    `(spy :debug ~expr))
  ([level expr]
    `(let [a# ~expr]
       (log ~level
         (let [s# (with-out-str
                    (with-pprint-dispatch code-dispatch ; need a better way
                      (pprint '~expr)
                      (print "=> ")
                      (pprint a#)))]
           (trim-newline s#)))
       a#)))

(defn log-stream
  "Creates a PrintStream that will output to the logger at the specified level."
  [level log-ns]
  (let [logger (impl-get-logger *logger-factory* log-ns)]
    (java.io.PrintStream.
      (proxy [java.io.ByteArrayOutputStream] []
        (flush []
          ; deal with reflection in proxy-super
          (let [^java.io.ByteArrayOutputStream this this]
            (proxy-super flush)
            (let [message (.trim (.toString this))]
              (proxy-super reset)
              (if (> (.length message) 0)
                (log* logger level nil message))))))
      true)))

(let [orig (atom nil)    ; holds original System.out and System.err
      monitor (Object.)] ; sync monitor for calling setOut/setErr
  (defn log-capture!
    "Captures System.out and System.err, piping all writes of those streams to
    the logger. If unspecified, levels default to :info and :error, respectively.
    The specified log-ns value will be used to namespace all log entries.

    Note: use with-logs to redirect output of *out* or *err*.

    Warning: if the logging implementation is configured to output to System.out
    (as is the default with java.util.logging) then using this function will
    result in StackOverflowException when writing to the logger."
    ; Implementation Notes:
    ; - only set orig when nil to preserve original out/err
    ; - no enabled? check before making streams since that may change later
    ([log-ns]
      (log-capture! log-ns :info :error))
    ([log-ns out-level err-level]
      (locking monitor
        (compare-and-set! orig nil [System/out System/err])
        (System/setOut  (log-stream out-level log-ns))
        (System/setErr (log-stream err-level log-ns)))))
  (defn log-uncapture!
    "Restores System.out and System.err to their original values."
    []
    (locking monitor
      (when-let [[out err :as v] @orig]
        (swap! orig (constantly nil))
        (System/setOut out)
        (System/setErr err)))))

(defmacro with-logs
  "Evaluates exprs in a context in which *out* and *err* write to the logger. The
  specified log-ns value will be used to namespace all log entries.

  By default *out* and *err* write to :info and :error, respectively."
  {:arglists '([log-ns & body]
               [[log-ns out-level err-level] & body])}
  [arg & body]
  ; Implementation Notes:
  ; - no enabled? check before making writers since that may change later
  (let [[log-ns out-level err-level] (if (vector? arg)
                                       arg
                                       [arg :info :error])]
    (if (and log-ns (seq body))
      `(binding [*out* (java.io.OutputStreamWriter.
                         (log-stream ~out-level ~log-ns))
                 *err* (java.io.OutputStreamWriter.
                         (log-stream ~err-level ~log-ns))]
         ~@body))))


;; level-specific macros

(defmacro trace
  "Trace level logging using print-style args."
  {:arglists '([message & more] [throwable message & more])}
  [& args]
  `(logp :trace ~@args))

(defmacro debug
  "Debug level logging using print-style args."
  {:arglists '([message & more] [throwable message & more])}
  [& args]
  `(logp :debug ~@args))

(defmacro info
  "Info level logging using print-style args."
  {:arglists '([message & more] [throwable message & more])}
  [& args]
  `(logp :info ~@args))

(defmacro warn
  "Warn level logging using print-style args."
  {:arglists '([message & more] [throwable message & more])}
  [& args]
  `(logp :warn ~@args))

(defmacro error
  "Error level logging using print-style args."
  {:arglists '([message & more] [throwable message & more])}
  [& args]
  `(logp :error ~@args))

(defmacro fatal
  "Fatal level logging using print-style args."
  {:arglists '([message & more] [throwable message & more])}
  [& args]
  `(logp :fatal ~@args))

(defmacro tracef
  "Trace level logging using format."
  {:arglists '([fmt & fmt-args] [throwable fmt & fmt-args])}
  [& args]
  `(logf :trace ~@args))

(defmacro debugf
  "Debug level logging using format."
  {:arglists '([fmt & fmt-args] [throwable fmt & fmt-args])}
  [& args]
  `(logf :debug ~@args))

(defmacro infof
  "Info level logging using format."
  {:arglists '([fmt & fmt-args] [throwable fmt & fmt-args])}
  [& args]
  `(logf :info ~@args))

(defmacro warnf
  "Warn level logging using format."
  {:arglists '([fmt & fmt-args] [throwable fmt & fmt-args])}
  [& args]
  `(logf :warn ~@args))

(defmacro errorf
  "Error level logging using format."
  {:arglists '([fmt & fmt-args] [throwable fmt & fmt-args])}
  [& args]
  `(logf :error ~@args))

(defmacro fatalf
  "Fatal level logging using format."
  {:arglists '([fmt & fmt-args] [throwable fmt & fmt-args])}
  [& args]
  `(logf :fatal ~@args))


;; Implementations of Logger and LoggerFactory protocols:

(defn commons-logging
  "Returns a commons-logging-based implementation of the LoggerFactory protocol, or
  nil if not available. End-users should not need to call this."
  []
  (try
    (Class/forName "org.apache.commons.logging.Log")
    (eval
      '(do
         (extend-type org.apache.commons.logging.Log
           Logger
           (impl-enabled? [logger# level#]
             (condp = level#
               :trace (.isTraceEnabled logger#)
               :debug (.isDebugEnabled logger#)
               :info  (.isInfoEnabled  logger#)
               :warn  (.isWarnEnabled  logger#)
               :error (.isErrorEnabled logger#)
               :fatal (.isFatalEnabled logger#)
               (throw (IllegalArgumentException. (str level#)))))
           (impl-write! [logger# level# e# msg#]
             (condp = level#
               :trace (.trace logger# msg# e#)
               :debug (.debug logger# msg# e#)
               :info  (.info  logger# msg# e#)
               :warn  (.warn  logger# msg# e#)
               :error (.error logger# msg# e#)
               :fatal (.fatal logger# msg# e#)
               (throw (IllegalArgumentException. (str level#))))))
         (reify LoggerFactory
           (impl-name [_#]
             "org.apache.commons.logging")
           (impl-get-logger [_# log-ns#]
             (org.apache.commons.logging.LogFactory/getLog (str log-ns#))))))
    (catch Exception e nil)))

(defn slf4j-logging
  "Returns a SLF4J-based implementation of the LoggerFactory protocol, or nil if
  not available. End-users should not need to call this."
  []
  (try
    (Class/forName "org.slf4j.Logger")
    (eval
      '(do
        (extend-type org.slf4j.Logger
          Logger
          (impl-enabled? [logger# level#]
            (condp = level#
              :trace (.isTraceEnabled logger#)
              :debug (.isDebugEnabled logger#)
              :info  (.isInfoEnabled  logger#)
              :warn  (.isWarnEnabled  logger#)
              :error (.isErrorEnabled logger#)
              :fatal (.isErrorEnabled logger#)
              (throw (IllegalArgumentException. (str level#)))))
          (impl-write! [^org.slf4j.Logger logger# level# ^Throwable e# msg#]
            (let [^String msg# (str msg#)]
              (condp = level#
                :trace (.trace logger# msg# e#)
                :debug (.debug logger# msg# e#)
                :info  (.info  logger# msg# e#)
                :warn  (.warn  logger# msg# e#)
                :error (.error logger# msg# e#)
                :fatal (.error logger# msg# e#)
                (throw (IllegalArgumentException. (str level#)))))))
        (reify LoggerFactory
          (impl-name [_#]
            "org.slf4j")
          (impl-get-logger [_# log-ns#]
            (org.slf4j.LoggerFactory/getLogger ^String (str log-ns#))))))
    (catch Exception e nil)))

(defn log4j-logging
  "Returns a log4j-based implementation of the LoggerFactory protocol, or nil if
  not available. End-users should not need to call this."
  []
  (try
    (Class/forName "org.apache.log4j.Logger")
    (eval
      '(let [levels# {:trace org.apache.log4j.Level/TRACE
                      :debug org.apache.log4j.Level/DEBUG
                      :info  org.apache.log4j.Level/INFO
                      :warn  org.apache.log4j.Level/WARN
                      :error org.apache.log4j.Level/ERROR
                      :fatal org.apache.log4j.Level/FATAL}]
         (extend-type org.apache.log4j.Logger
           Logger
           (impl-enabled? [logger# level#]
             (.isEnabledFor logger#
               (or
                 (levels# level#)
                 (throw (IllegalArgumentException. (str level#))))))
           (impl-write! [logger# level# e# msg#]
             (let [level# (or
                            (levels# level#)
                            (throw (IllegalArgumentException. (str level#))))]
               (if-not e#
                 (.log logger# level# msg#)
                 (.log logger# level# msg# e#)))))
         (reify LoggerFactory
           (impl-name [_#]
             "org.apache.log4j")
           (impl-get-logger [_# log-ns#]
             (org.apache.log4j.Logger/getLogger ^String (str log-ns#))))))
    (catch Exception e nil)))

(defn java-util-logging
  "Returns a java.util.logging-based implementation of the LoggerFactory protocol,
  or nil if not available. End-users should not need to call this."
  []
  (try
    (Class/forName "java.util.logging.Logger")
    (eval
      '(let [levels# {:trace java.util.logging.Level/FINEST
                      :debug java.util.logging.Level/FINE
                      :info  java.util.logging.Level/INFO
                      :warn  java.util.logging.Level/WARNING
                      :error java.util.logging.Level/SEVERE
                      :fatal java.util.logging.Level/SEVERE}]
         (extend-type java.util.logging.Logger
           Logger
           (impl-enabled? [logger# level#]
             (.isLoggable logger#
               (or
                 (levels# level#)
                 (throw (IllegalArgumentException. (str level#))))))
           (impl-write! [logger# level# ^Throwable e# msg#]
             (let [^java.util.logging.Level level#
                   (or
                     (levels# level#)
                     (throw (IllegalArgumentException. (str level#))))
                   ^String msg# (str msg#)]
               (if e#
                 (.log logger# level# msg# e#)
                 (.log logger# level# msg#)))))
         (reify LoggerFactory
           (impl-name [_#]
             "java.util.logging")
           (impl-get-logger [_# log-ns#]
             (java.util.logging.Logger/getLogger (str log-ns#))))))
    (catch Exception e nil)))

(defn find-factory
  "Returns the first LoggerFactory found that is available from commons-logging,
  slf4j-logging, log4j-logging, or java-util-logging. End-users should not need
  to call this."
  []
  (or (commons-logging)
    (slf4j-logging)
    (log4j-logging)
    (java-util-logging)
    (throw ; this should never happen in 1.5+
      (RuntimeException.
        "Valid logging implementation could not be found."))))

(def ^{:doc
  "An instance satisfying the LoggerFactory protocol. Used internally when needing
  to obtain an instance satisfying the Logger protocol. Defaults to the value
  returned from find-factory. Can be rebound to provide alternate logging
  implementations" :dynamic true}
  *logger-factory*
  (find-factory))
