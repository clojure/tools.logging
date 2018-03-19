;; Copyright (c) Alex Taggart. All rights reserved.  The use
;; and distribution terms for this software are covered by the Eclipse
;; Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;; which can be found in the file epl-v10.html at the root of this
;; distribution.  By using this software in any fashion, you are
;; agreeing to be bound by the terms of this license.  You must not
;; remove this notice, or any other, from this software.

(ns ^{:author "Alex Taggart"
      :doc "Extensible support for testing whether logging calls are made.

            Usage example:
              (with-log
                (log/info \"Hello World!\")
                (log/error (Exception. \"Did a thing\") \"Error: oops\")
                (prn (logged? *ns* :info #\"Hello\"))                                ; true
                (prn (logged? *ns* :error [Throwable #\"thing\"] #\"Error:\"))       ; true
                (prn (logged? *ns* :debug \"Hi\")))                                  ; false


            Extension example, testing custom message types:
              (defrecord ContextualMessage [text context])

              (defmethod match-message? [[String Map] ContextualMessage]
                [[expected-text expected-context] actual]
                (and (= expected-text (:text actual))
                     (= expected-context (-> actual :context (select-keys (keys expected-context))))))

              (with-log
                (log/log :debug (->ContextualMessage \"stuff\" {:a 1 :b 2}))
                (prn (logged? *ns* :debug [\"stuff\" {:a 1}]))                   ; true
                (prn (logged? *ns* :debug [\"stuff\" {:a 1 :b 2}]))              ; true
                (prn (logged? *ns* :debug [\"stuff\" {:a 1 :c 3}])))             ; false"}
    clojure.tools.logging.test
  (:import [clojure.lang Keyword Namespace Symbol]
           java.util.Set
           java.util.regex.Pattern)
  (:require [clojure.tools.logging :as log]
            [clojure.tools.logging.impl :as impl]))

(defn- types [coll]
  (reduce #(conj % (if (vector? %2)
                     (types %2)
                     (type %2)))
          []
          coll))


(defmulti match-logger-ns?
  "Returns true if expected matches the actual namespace.
   Dispatches on the (possibly-nested) types of expected and actual."
  (fn [expected actual]
    (types [expected actual])))

(defmethod match-logger-ns? [Namespace Namespace]
  [expected actual]
  (= expected actual))

(defmethod match-logger-ns? [String Namespace]
  [expected actual]
  (= expected (str actual)))

(defmethod match-logger-ns? [Symbol Namespace]
  [expected actual]
  (= expected (.name actual)))


(defmulti match-level?
  "Returns true if expected matches the actual level.
   Dispatches on the (possibly-nested) types of expected and actual."
  (fn [expected actual]
    (types [expected actual])))

(defmethod match-level? [Keyword Keyword]
  [expected actual]
  (= expected actual))

(defmethod match-level? [Set Keyword]
  [expected actual]
  (contains? expected actual))


(defmulti match-throwable?
  "Returns true if expected matches the actual throwable.
   Dispatches on the (possibly-nested) types of expected and actual."
  (fn [expected actual]
    (types [expected actual])))

(defmethod match-throwable? [Throwable Throwable]
  [expected actual]
  (= expected actual))

(defmethod match-throwable? [Class Throwable]
  [expected actual]
  (instance? expected actual))

(defmethod match-throwable? [[Class String] Throwable]
  [[expected-ex expected-msg] actual]
  (and (match-throwable? expected-ex actual)
       (= expected-msg (.getMessage actual))))

(defmethod match-throwable? [[Class Pattern] Throwable]
  [[expected-ex expected-msg] actual]
  (and (match-throwable? expected-ex actual)
       (boolean (re-find expected-msg (.getMessage actual)))))


(defmulti match-message?
  "Returns true if expected matches the actual message.
   Dispatches on the (possibly-nested) types of expected and actual."
  (fn [expected actual]
    (types [expected actual])))

(defmethod match-message? [String String]
  [expected actual]
  (= expected actual))

(defmethod match-message? [Pattern String]
  [expected actual]
  (boolean (re-find expected actual)))



(defprotocol Matcher
  (match?
    [log-entry logger-ns level message]
    [log-entry logger-ns level throwable message]
    "Returns true if the log-entry fields match the respective param."))

(defrecord LogEntry [logger-ns level throwable message]
  Matcher
  (match? [_ exp-logger-ns exp-level exp-message]
    (boolean
      (and (match-logger-ns? exp-logger-ns logger-ns)
           (match-level? exp-level level)
           (nil? throwable)
           (match-message? exp-message message))))
  (match? [_ exp-logger-ns exp-level exp-throwable exp-message]
    (boolean
      (and (match-logger-ns? exp-logger-ns logger-ns)
           (match-level? exp-level level)
           (match-throwable? exp-throwable throwable)
           (match-message? exp-message message)))))



(defprotocol InspectableLog
  (state [this])
  (has-match? [this logger-ns level message]
              [this logger-ns level throwable message]
    "Returns true if this log contains a matching log entry.")
  (has-matches? [this match-params]
    "Returns true if this log contains a matching log entries, in order."))


(def ^:dynamic ^{:doc "An instance of InspectableLog. By default unbound.
                       See with-log, logged?, and the-log."}
  *inspectable-log*)


(defn the-log
  "Returns the state of the log. Must be invoked within a context in which
   *inspectable-log* is bound to an instance of InspectableLog (e.g., inside
   with-log).

   This may be useful when trying to determine why a call to logged? does not
   yield the expected result."
  []
  (state *inspectable-log*))


(defn logged?
  "Returns true if the log has a log entry that matches the specified params.
   Must be invoked within a context in which *inspectable-log* is bound to an
   instance of InspectableLog (e.g., inside with-log).

   Default matching support:
     logger-ns: namespace, symbol, string
     level:     keyword, set of keywords
     throwable: throwable, class, [class message-string], [class message-regex]
     message:   string, regex

   To support additional matching styles for each param, provide new methods to
   the respective match-*? multifn."
  ([logger-ns level message]
   (has-match? *inspectable-log* logger-ns level message))
  ([logger-ns level throwable message]
   (has-match? *inspectable-log* logger-ns level throwable message)))


(defn logged-all?
  "Returns true if the log has log entries that match the specified params and
   in the same order. Must be invoked within a context in which *inspectable-log*
   is bound to an instance of InspectableLog (e.g., inside with-log)"
  [logged-params]
  (has-matches? *inspectable-log* logged-params))



(defrecord ^:private LoggerFactory [log-atom]
  impl/LoggerFactory
  (name [_] "clojure.tools.logging.test/logger-factory")
  (get-logger [_ logger-ns]
    (reify impl/Logger
      (enabled? [_ _] true)
      (write! [_ level throwable message]
        (let [entry (->LogEntry logger-ns level throwable message)]
          (swap! log-atom (fnil conj []) entry)))))

  InspectableLog
  (state [_] @log-atom)
  (has-match? [_ logger-ns level message]
    (boolean (some #(match? % logger-ns level message) @log-atom)))
  (has-match? [_ logger-ns level throwable message]
    (boolean (some #(match? % logger-ns level throwable message) @log-atom)))
  (has-matches? [_ match-params]
    (loop [[mp :as match-params] match-params
           log                   @log-atom]
      (cond
        (empty? match-params) true
        (empty? log)          false
        :else (if-let [remaining (seq (drop-while #(not (apply match? % mp)) log))]
                (recur (next match-params) (next remaining))
                false)))))


(defn logger-factory
  "Returns a LoggerFactory that internally stores each logging call as a
   LogEntry, and satisfies the InspectableLog protocol.

   See with-log."
  []
  (->LoggerFactory (atom [])))


(defmacro with-log
  "Evaluates body within a context in which logging is collected and available
   via *inspectable-log*, allowing use of logged?, logged-all?, and the-log."
  [& body]
  `(let [lf# (logger-factory)]
     (binding [log/*logger-factory* lf#
               *inspectable-log*    lf#]
       ~@body)))

