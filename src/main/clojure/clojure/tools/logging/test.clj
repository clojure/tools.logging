;; Copyright (c) Alex Taggart. All rights reserved.  The use
;; and distribution terms for this software are covered by the Eclipse
;; Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;; which can be found in the file epl-v10.html at the root of this
;; distribution.  By using this software in any fashion, you are
;; agreeing to be bound by the terms of this license.  You must not
;; remove this notice, or any other, from this software.


(ns ^{:author "Alex Taggart"
      :doc
  "Support for testing whether logging calls are made.

  Usage example:
    (require '[clojure.tools.logging :as log]
             '[clojure.tools.logging.test :refer [logged? with-log])

    (with-log
      (log/info \"Hello World!\")
      (log/error (Exception. \"Did a thing\") \"Error: oops\")
      (logged? 'user :info #\"Hello\")                           ; true
      (logged? 'user :error [Throwable #\"thing\"] #\"Error:\")    ; true
      (logged? 'user :debug \"Hi\"))                             ; false"}
    clojure.tools.logging.test
  (:import [clojure.lang Fn Keyword Namespace Symbol]
           java.util.Set
           java.util.regex.Pattern)
  (:require [clojure.tools.logging :refer [*logger-factory*]]
            [clojure.tools.logging.impl :as impl]))


(defmulti match-logger-ns?
  "Returns true if expected matches the actual namespace. Used by
  LogEntry/matches? implementation.

  Dispatches on the types of expected and actual.

  Provided methods' dispatch values and matching:
    :default            if equal
    [Fn Object]         if (f actual) is logically true
    [String Namespace]  if string equals namespace's string
    [Symbol Namespace]  if symbol equals namespace's symbol"
  (fn [expected actual]
    [(type expected) (type actual)]))

(defmethod match-logger-ns? :default
  [expected actual]
  (= expected actual))

(defmethod match-logger-ns? [Fn Object]
  [expected actual]
  (expected actual))

(defmethod match-logger-ns? [String Namespace]
  [expected actual]
  (= expected (str actual)))

(defmethod match-logger-ns? [Symbol Namespace]
  [expected ^Namespace actual]
  (= expected (.name actual)))



(defmulti match-level?
  "Returns true if expected matches the actual level. Used by
  LogEntry/matches? implementation.

  Dispatches on the types of expected and actual.

  Provided methods' dispatch values and matching:
    :default      if equal
    [Fn Object]   if (f actual) is logically true
    [Set Object]  if set contains actual"
  (fn [expected actual]
    [(type expected) (type actual)]))

(defmethod match-level? :default
  [expected actual]
  (= expected actual))

(defmethod match-level? [Fn Object]
  [expected actual]
  (expected actual))

(defmethod match-level? [Set Object]
  [expected actual]
  (contains? expected actual))



(defmulti match-throwable?
  "Returns true if expected matches the actual throwable. Used by
  LogEntry/matches? implementation.

  Dispatches on the types of expected and actual. If expected is a vector, will
  instead use a vector of the contained types.

  Provided methods' dispatch values and matching:
    :default                     if equal
    [Fn Object]                  if (f actual) is logically true
    [Class Object]               if actual is an instance of Class
    [[Class String] Throwable]   ... and if string equals exception message
    [[Class Pattern] Throwable]  ... and if pattern matches exception message"
  (fn [expected actual]
    (if (vector? expected)
      [(mapv type expected) (type actual)]
      [(type expected) (type actual)])))

(defmethod match-throwable? :default
  [expected actual]
  (= expected actual))

(defmethod match-throwable? [Fn Object]
  [expected actual]
  (expected actual))

(defmethod match-throwable? [Class Object]
  [expected actual]
  (instance? expected actual))

(defmethod match-throwable? [[Class String] Throwable]
  [[expected-ex expected-msg] ^Throwable actual]
  (and (match-throwable? expected-ex actual)
       (= expected-msg (.getMessage actual))))

(defmethod match-throwable? [[Class Pattern] Throwable]
  [[expected-ex expected-msg] ^Throwable actual]
  (and (match-throwable? expected-ex actual)
       (boolean (re-find expected-msg (.getMessage actual)))))



(defmulti match-message?
  "Returns true if expected matches the actual message. Used by
  LogEntry/matches? implementation.

  Dispatches on the types of expected and actual.

  Provided methods' dispatch values and matching:
    :default          if equal
    [Fn Object]       if (f actual) is logically true
    [Pattern String]  if pattern matches actual"
  (fn [expected actual]
    [(type expected) (type actual)]))

(defmethod match-message? :default
  [expected actual]
  (= expected actual))

(defmethod match-message? [Fn Object]
  [expected actual]
  (expected actual))

(defmethod match-message? [Pattern String]
  [expected actual]
  (boolean (re-find expected actual)))



(defprotocol MatchableLogEntry
  (matches? [this logger-ns level throwable message]))



(defrecord LogEntry [logger-ns level throwable message]
  MatchableLogEntry
  (matches? [_ exp-logger-ns exp-level exp-throwable exp-message]
    (and (match-logger-ns? exp-logger-ns logger-ns)
         (match-level? exp-level level)
         (match-throwable? exp-throwable throwable)
         (match-message? exp-message message))))



(defprotocol StatefulLog
  (entries [this]
         "Returns a vector of the entries in this log, oldest first.")
  (append! [this logger-ns level throwable message]
           "Returns this log with a new log entry appended."))



(defn atomic-log
  "Returns a StatefulLog, appending to an atom the result of invoking
  log-entry-fn with the same args as append!"
  [log-entry-fn]
  (let [log (atom [])]
    (reify
      StatefulLog
      (entries [_]
        (deref log))
      (append! [this logger-ns level throwable message]
        (swap! log (fnil conj []) (log-entry-fn logger-ns level throwable message))
        this))))



(defn logger-factory
  "Returns a LoggerFactory that will append log entries to stateful-log. Levels
  are enabled when (enabled-pred logger-ns level) is true."
  [stateful-log enabled-pred]
  (reify impl/LoggerFactory
    (name [_] "clojure.tools.logging.test/logger-factory")
    (get-logger [_ logger-ns]
      (reify impl/Logger
        (enabled? [_ level] (enabled-pred logger-ns level))
        (write! [_ level throwable message]
          (append! stateful-log logger-ns level throwable message)
          nil)))))



(def ^:dynamic
     ^{:doc "The instance of StatefulLog used by with-log. By default unbound."}
  *stateful-log*)



(defn the-log
  "Returns the vector of current log entries.

  Must be invoked within a context where *stateful-log* is bound to an instance
  of StatefulLog containing MatchableLogEntry items (e.g., inside with-log)."
  []
  (entries *stateful-log*))



(defn matches
  "Returns matching log entries, otherwise nil. See match-logger-ns?,
  match-level?, match-throwable?, and match-message? for the default matching
  behavior applied to the given args.

  Must be invoked within a context where *stateful-log* is bound to an instance
  of StatefulLog containing MatchableLogEntry items (e.g., inside with-log)."
  ([logger-ns level message]
    (matches logger-ns level nil message))
  ([logger-ns level throwable message]
    (seq (filter #(matches? % logger-ns level throwable message) (the-log)))))



(defn logged?
  "Returns true if the log contains matching entries. See match-logger-ns?,
  match-level?, match-throwable?, and match-message? for the default matching
  behavior applied to the given args.

  Must be invoked within a context where *stateful-log* is bound to an instance
  of StatefulLog containing MatchableLogEntry items (e.g., inside with-log)."
  ([logger-ns level message]
    (boolean (matches logger-ns level message)))
  ([logger-ns level throwable message]
    (boolean (matches logger-ns level throwable message))))



(defmacro with-log [& body]
  "Evaluates body within a context where logging is collected.

  See logged?, matches, the-log."
  `(let [stateful-log# (atomic-log ->LogEntry)
         logger-factory# (logger-factory stateful-log# (constantly true))]
     (binding [*stateful-log*   stateful-log#
               *logger-factory* logger-factory#]
       ~@body)))



