;; Copyright (c) Alex Taggart. All rights reserved.  The use
;; and distribution terms for this software are covered by the Eclipse
;; Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;; which can be found in the file epl-v10.html at the root of this
;; distribution.  By using this software in any fashion, you are
;; agreeing to be bound by the terms of this license.  You must not
;; remove this notice, or any other, from this software.

(ns ^{:author "Alex Taggart"
      :doc "Protocols used to allow access to logging implementations.
            This namespace only need be used by those providing logging
            implementations to be consumed by the core api."}
     clojure.tools.logging.impl
  (:refer-clojure :exclude [name]))

(defprotocol Logger
  "The protocol through which the core api will interact with an underlying logging
  implementation.  Implementations should at least support the six standard
  logging levels if they wish to work from the level-specific macros."
  (enabled? [logger level]
    "Check if a particular level is enabled for the given Logger.")
  (write! [logger level throwable message]
    "Writes a log message to the given Logger."))

(defprotocol LoggerFactory
  "The protocol through which the core api will obtain an instance satisfying Logger
  as well as providing information about the particular implementation being used.
  Implementations should be bound to *logger-factory* in order to be picked up by
  this library."
  (name [factory]
    "Returns some text identifying the underlying implementation.")
  (get-logger [factory logger-ns]
    "Returns an implementation-specific Logger by namespace."))

(defn slf4j-factory
  "Returns a SLF4J-based implementation of the LoggerFactory protocol, or nil if
  not available."
  []
  (try
    (Class/forName "org.slf4j.Logger")
    (eval
      `(do
        (extend org.slf4j.Logger
          Logger
          {:enabled?
           (fn [logger# level#]
             (condp = level#
               :trace (.isTraceEnabled logger#)
               :debug (.isDebugEnabled logger#)
               :info  (.isInfoEnabled  logger#)
               :warn  (.isWarnEnabled  logger#)
               :error (.isErrorEnabled logger#)
               :fatal (.isErrorEnabled logger#)
               (throw (IllegalArgumentException. (str level#)))))
           :write!
           (fn [^org.slf4j.Logger logger# level# ^Throwable e# msg#]
             (let [^String msg# (str msg#)]
               (condp = level#
                 :trace (.trace logger# msg# e#)
                 :debug (.debug logger# msg# e#)
                 :info  (.info  logger# msg# e#)
                 :warn  (.warn  logger# msg# e#)
                 :error (.error logger# msg# e#)
                 :fatal (.error logger# msg# e#)
                 (throw (IllegalArgumentException. (str level#))))))})
        (reify LoggerFactory
          (name [_#]
            "org.slf4j")
          (get-logger [_# logger-ns#]
            (org.slf4j.LoggerFactory/getLogger ^String (str logger-ns#))))))
    (catch Exception e nil)))

(defn cl-factory
  "Returns a Commons Logging-based implementation of the LoggerFactory protocol, or
  nil if not available."
  []
  (try
    (Class/forName "org.apache.commons.logging.Log")
    (eval
      `(do
         (extend org.apache.commons.logging.Log
           Logger
           {:enabled?
            (fn [logger# level#]
              (condp = level#
                :trace (.isTraceEnabled logger#)
                :debug (.isDebugEnabled logger#)
                :info  (.isInfoEnabled  logger#)
                :warn  (.isWarnEnabled  logger#)
                :error (.isErrorEnabled logger#)
                :fatal (.isFatalEnabled logger#)
                (throw (IllegalArgumentException. (str level#)))))
            :write!
            (fn [logger# level# e# msg#]
              (condp = level#
                :trace (.trace logger# msg# e#)
                :debug (.debug logger# msg# e#)
                :info  (.info  logger# msg# e#)
                :warn  (.warn  logger# msg# e#)
                :error (.error logger# msg# e#)
                :fatal (.fatal logger# msg# e#)
                (throw (IllegalArgumentException. (str level#)))))})
         (reify LoggerFactory
           (name [_#]
             "org.apache.commons.logging")
           (get-logger [_# logger-ns#]
             (org.apache.commons.logging.LogFactory/getLog (str logger-ns#))))))
    (catch Exception e nil)))

(defn log4j-factory
  "Returns a Log4j-based implementation of the LoggerFactory protocol, or nil if
  not available."
  []
  (try
    (Class/forName "org.apache.log4j.Logger")
    (eval
      `(let [levels# {:trace org.apache.log4j.Level/TRACE
                      :debug org.apache.log4j.Level/DEBUG
                      :info  org.apache.log4j.Level/INFO
                      :warn  org.apache.log4j.Level/WARN
                      :error org.apache.log4j.Level/ERROR
                      :fatal org.apache.log4j.Level/FATAL}]
         (extend org.apache.log4j.Logger
           Logger
           {:enabled?
            (fn [logger# level#]
              (.isEnabledFor logger#
                 (or
                   (levels# level#)
                   (throw (IllegalArgumentException. (str level#))))))
            :write!
            (fn [logger# level# e# msg#]
              (let [level# (or
                             (levels# level#)
                             (throw (IllegalArgumentException. (str level#))))]
                (if-not e#
                  (.log logger# level# msg#)
                  (.log logger# level# msg# e#))))})
         (reify LoggerFactory
           (name [_#]
             "org.apache.log4j")
           (get-logger [_# logger-ns#]
             (org.apache.log4j.Logger/getLogger ^String (str logger-ns#))))))
    (catch Exception e nil)))

(defn jul-factory
  "Returns a java.util.logging-based implementation of the LoggerFactory protocol,
  or nil if not available."
  []
  (try
    (Class/forName "java.util.logging.Logger")
    (eval
      `(let [levels# {:trace java.util.logging.Level/FINEST
                      :debug java.util.logging.Level/FINE
                      :info  java.util.logging.Level/INFO
                      :warn  java.util.logging.Level/WARNING
                      :error java.util.logging.Level/SEVERE
                      :fatal java.util.logging.Level/SEVERE}]
         (extend java.util.logging.Logger
           Logger
           {:enabled?
            (fn [logger# level#]
              (.isLoggable logger#
                (or
                  (levels# level#)
                  (throw (IllegalArgumentException. (str level#))))))
            :write!
            (fn [logger# level# ^Throwable e# msg#]
              (let [^java.util.logging.Level level#
                    (or
                      (levels# level#)
                      (throw (IllegalArgumentException. (str level#))))
                    ^String msg# (str msg#)]
                (if e#
                  (.log logger# level# msg# e#)
                  (.log logger# level# msg#))))})
         (reify LoggerFactory
           (name [_#]
             "java.util.logging")
           (get-logger [_# logger-ns#]
             (java.util.logging.Logger/getLogger (str logger-ns#))))))
    (catch Exception e nil)))

(defn find-factory
  "Returns the first non-nil value from slf4j-factory, cl-factory,
   log4j-factory, and jul-factory."
  []
  (or (slf4j-factory)
      (cl-factory)
      (log4j-factory)
      (jul-factory)
      (throw ; this should never happen in 1.5+
        (RuntimeException.
          "Valid logging implementation could not be found."))))