(ns clojure.tools.logging.test-test
  (:require [clojure.test :refer [are deftest is testing]]
            [clojure.tools.logging :as log]
            [clojure.tools.logging.impl :as impl]
            [clojure.tools.logging.test :refer :all]))

(def this-ns (the-ns 'clojure.tools.logging.test-test))


; Note using (= true/false ...) in order to prove the result is a boolean.

(deftest test-match-logger-ns?
  (are [expected actual] (= true (match-logger-ns? expected actual))
       1 1
       keyword? :kw
       "clojure.tools.logging.test-test" this-ns
       'clojure.tools.logging.test-test this-ns)

  (are [expected actual] (= false (match-logger-ns? expected actual))
       2 1
       symbol? :kw
       "xxx" this-ns
       'xxx this-ns))


(deftest test-match-level?
  (are [expected actual] (= true (match-level? expected actual))
       1 1
       keyword? :kw
       #{:a} :a)

  (are [expected actual] (= false (match-level? expected actual))
       2 1
       symbol? :kw
       #{:b} :a))


(deftest test-match-throwable?
  (are [expected actual] (= true (match-throwable? expected actual))
       1 1
       keyword? :kw
       Throwable (Exception.)
       [Throwable "oops"] (Exception. "oops")
       [Throwable #"o+"] (Exception. "oops"))

  (are [expected actual] (= false (match-throwable? expected actual))
       2 1
       symbol? :kw
       RuntimeException (Exception.)
       [RuntimeException "oops"] (Exception. "oops")
       [Exception "ok"] (Exception. "oops")
       [RuntimeException #"o+"] (Exception. "oops")
       [Exception #"a+"] (Exception. "oops")))


(deftest test-match-message?
  (are [expected actual] (= true (match-message? expected actual))
       1 1
       keyword? :kw
       #"o+" "oops")

  (are [expected actual] (= false (match-message? expected actual))
       2 1
       symbol? :kw
       #"a+" "oops"))

(deftest test-log-entry
  (let [log-entry (->LogEntry :logger-ns-1 :level-1 :throwable-1 :message-1)]
    (is (matches? log-entry :logger-ns-1 :level-1 :throwable-1 :message-1))
    (are [exp-logger-ns exp-level exp-throwable exp-message]
         (not (matches? log-entry exp-logger-ns exp-level exp-throwable exp-message))
      :logger-ns-2 :level-1 :throwable-1 :message-1
      :logger-ns-1 :level-2 :throwable-1 :message-1
      :logger-ns-1 :level-1 :throwable-2 :message-1
      :logger-ns-1 :level-1 :throwable-1 :message-2)))


(deftest test-atomic-log
  (is (= [] (entries (atomic-log ->LogEntry))))
  (let [log (-> (atomic-log ->LogEntry)
              (append! :logger-ns-1 :level-1 :throwable-1 :message-1)
              (append! :logger-ns-2 :level-2 :throwable-2 :message-2))]
    (is (vector? (entries log)))
    (is (= [(->LogEntry :logger-ns-1 :level-1 :throwable-1 :message-1)
            (->LogEntry :logger-ns-2 :level-2 :throwable-2 :message-2)]
           (entries log)))))


(deftest test-logger-factory
  (let [stateful-log   (atomic-log ->LogEntry)
        logger-factory (logger-factory stateful-log (constantly true))
        logger-1 (impl/get-logger logger-factory :logger-ns-1)
        logger-2 (impl/get-logger logger-factory :logger-ns-2)]
    (is (= "clojure.tools.logging.test/logger-factory"
           (impl/name logger-factory)))
    (are [level] (impl/enabled? logger-1 level)
         :trace :debug :info :warn :error :fatal :foobar)
    (impl/write! logger-1 :level-1 :throwable-1 :message-1)
    (impl/write! logger-2 :level-2 :throwable-2 :message-2)
    (is (= [(->LogEntry :logger-ns-1 :level-1 :throwable-1 :message-1)
            (->LogEntry :logger-ns-2 :level-2 :throwable-2 :message-2)]
           (entries stateful-log)))))


(deftest test-with-log
  (let [ex (ex-info "oops" {:a 1})]
    (with-log
      (log/debug "debugging")
      (log/error ex "erroring")
      (is (= true (logged? this-ns :debug "debugging")))
      (is (= true (logged? this-ns :error ex "erroring")))
      (is (= false (logged? this-ns :fatal "fataling"))))))


; Test extending example

(defrecord ContextualMessage [text context])

(defmethod match-message? [clojure.lang.IPersistentVector ContextualMessage]
  [[expected-text expected-context] actual]
  (and (= expected-text (:text actual))
       (= expected-context (-> actual :context (select-keys (keys expected-context))))))

(deftest test-with-log-custom-message
  (let [msg     (->ContextualMessage "stuff" {:a 1 :b 2})]
    (with-log
      (log/log :debug msg)
      (is (= [(->LogEntry this-ns :debug nil msg)] (the-log)))
      (is      (logged? this-ns :debug ["stuff" {:a 1}]))
      (is      (logged? this-ns :debug ["stuff" {:a 1 :b 2}]))
      (is (not (logged? this-ns :debug ["stuff" {:a 1 :c 2}]))))))

