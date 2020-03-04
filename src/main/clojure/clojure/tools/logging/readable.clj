;; Copyright (c) Alex Taggart. All rights reserved.  The use
;; and distribution terms for this software are covered by the Eclipse
;; Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;; which can be found in the file epl-v10.html at the root of this
;; distribution.  By using this software in any fashion, you are
;; agreeing to be bound by the terms of this license.  You must not
;; remove this notice, or any other, from this software.

(ns ^{:author "Alex Taggart"
      :doc    "Logging macros that support printing message (some) arguments as
  if wrapped in pr-str, the goal being to preserve their data representation
  distinct from the explanatory text. See logp and logf for details regarding
  which args are treated in this manner.

  Examples:

  (require '[clojure.tools.logging :as log]
           '[clojure.tools.logging.readable :as logr])

  (def x \"bar\")
                                    ; Logged as...
  (log/debug \"foo\" x \"baz\")         ; foo bar baz
  (logr/debug \"foo\" x \"baz\")        ; foo \"bar\" baz
  (log/debugf \"foo %s %s\" x \"baz\")  ; foo bar baz
  (logr/debugf \"foo %s %s\" x \"baz\") ; foo \"bar\" \"baz\"
  "}
  clojure.tools.logging.readable
  (:require [clojure.tools.logging :as log]
            [clojure.tools.logging.impl :as impl]))

(defn- readable-print-args [args]
  (for [arg args]
    (if (string? arg)
      arg
      `(pr-str ~arg))))

(defmacro logp
  "Logs a message using print style args, where message args that are not
  literal strings will be printed readably, as if wrapped in pr-str. Can
  optionally take a throwable as its second arg. See level-specific macros,
  e.g., debug."
  {:arglists '([level message & more] [level throwable message & more])}
  [level x & more]
  ; Why bind `*print-readably*` to true?
  ; The `pr` functions do not bind `*print-readably*`, which defaults to true;
  ; instead the `print` functions bind `*print-readably*` to nil, then invoke
  ; `pr` functions.
  ; As such, absent explicit binding, if this code happened to be invoked in a
  ; context where `*print-readably*` was nil, then the non-string message args
  ; passed to pr-str would still not be printed readably.
  (if (or (string? x) (nil? more)) ; optimize for non-exception case
    `(log/log ~level (binding [*print-readably* true]
                       (print-str ~@(readable-print-args (cons x more)))))
    `(let [logger# (impl/get-logger log/*logger-factory* ~*ns*)]
       (if (impl/enabled? logger# ~level)
         (let [x# ~x]
           (if (instance? Throwable x#)
             (log/log* logger# ~level x#
                       (binding [*print-readably* true]
                         (print-str ~@(readable-print-args more))))
             (log/log* logger# ~level nil
                       (binding [*print-readably* true]
                         (print-str (pr-str x#) ; x is not a literal string, so pr-str x#
                                    ~@(readable-print-args more))))))))))

(defn- readable-format-args [args]
  (for [arg args]
    `(pr-str ~arg)))

(defmacro logf
  "Logs a message using a format string and args, where all format args will be
  printed readably, as if wrapped in pr-str. Can optionally take a throwable as
  its second arg. See level-specific macros, e.g., debugf."
  {:arglists '([level fmt & fmt-args] [level throwable fmt & fmt-args])}
  [level x & more]
  (if (or (instance? String x) (nil? more)) ; optimize for non-exception case
    `(log/log ~level (binding [*print-readably* true]
                       (format ~x ~@(readable-format-args more))))
    `(let [logger# (impl/get-logger log/*logger-factory* ~*ns*)]
       (if (impl/enabled? logger# ~level)
         (let [x# ~x]
           (if (instance? Throwable x#) ; type check only when enabled
             (log/log* logger# ~level x# (binding [*print-readably* true]
                                           (format ~(first more) ; not applied to the format string
                                                   ~@(readable-format-args (rest more)))))
             (log/log* logger# ~level nil (binding [*print-readably* true]
                                            (format x# ~@(readable-format-args more))))))))))

;; level-specific macros

(defmacro trace
  "Trace level logging using print-style args. See logp for details."
  {:arglists '([message & more] [throwable message & more])}
  [& args]
  `(logp :trace ~@args))

(defmacro debug
  "Debug level logging using print-style args. See logp for details."
  {:arglists '([message & more] [throwable message & more])}
  [& args]
  `(logp :debug ~@args))

(defmacro info
  "Info level logging using print-style args. See logp for details."
  {:arglists '([message & more] [throwable message & more])}
  [& args]
  `(logp :info ~@args))

(defmacro warn
  "Warn level logging using print-style args. See logp for details."
  {:arglists '([message & more] [throwable message & more])}
  [& args]
  `(logp :warn ~@args))

(defmacro error
  "Error level logging using print-style args. See logp for details."
  {:arglists '([message & more] [throwable message & more])}
  [& args]
  `(logp :error ~@args))

(defmacro fatal
  "Fatal level logging using print-style args. See logp for details."
  {:arglists '([message & more] [throwable message & more])}
  [& args]
  `(logp :fatal ~@args))

(defmacro tracef
  "Trace level logging using format. See logf for details."
  {:arglists '([fmt & fmt-args] [throwable fmt & fmt-args])}
  [& args]
  `(logf :trace ~@args))

(defmacro debugf
  "Debug level logging using format. See logf for details."
  {:arglists '([fmt & fmt-args] [throwable fmt & fmt-args])}
  [& args]
  `(logf :debug ~@args))

(defmacro infof
  "Info level logging using format. See logf for details."
  {:arglists '([fmt & fmt-args] [throwable fmt & fmt-args])}
  [& args]
  `(logf :info ~@args))

(defmacro warnf
  "Warn level logging using format. See logf for details."
  {:arglists '([fmt & fmt-args] [throwable fmt & fmt-args])}
  [& args]
  `(logf :warn ~@args))

(defmacro errorf
  "Error level logging using format. See logf for details."
  {:arglists '([fmt & fmt-args] [throwable fmt & fmt-args])}
  [& args]
  `(logf :error ~@args))

(defmacro fatalf
  "Fatal level logging using format. See logf for details."
  {:arglists '([fmt & fmt-args] [throwable fmt & fmt-args])}
  [& args]
  `(logf :fatal ~@args))
