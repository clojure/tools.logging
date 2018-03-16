(ns clojure.tools.logging.test-test
  (:import java.util.Map)
  (:require [clojure.tools.logging :as log])
  (:use clojure.test
        clojure.tools.logging.test))


(defn strip-stack-trace [^Throwable ex]
  (doto ex (.setStackTrace (into-array StackTraceElement []))))

(defmacro ex
  ([ex-class]
   `(strip-stack-trace (new ~ex-class)))
  ([ex-class ex-message]
   `(strip-stack-trace (new ~ex-class ~ex-message))))



(deftest test-match-logger-ns?
  (let [ns-a (the-ns 'clojure.test)
        ns-a-str (str ns-a)
        ns-a-sym (symbol ns-a-str)
        ns-b (the-ns 'clojure.tools.logging.test)]
    (testing "equality"
      (is      (match-logger-ns? ns-a ns-a))
      (is (not (match-logger-ns? ns-a ns-b))))

    (testing "by name"
      (is      (match-logger-ns? ns-a-str ns-a))
      (is (not (match-logger-ns? ns-a-str ns-b))))

    (testing "by symbol"
      (is      (match-logger-ns? ns-a-sym ns-a))
      (is (not (match-logger-ns? ns-a-sym ns-b))))))

(deftest test-match-level?
  (testing "equality"
    (is      (match-level? :error :error))
    (is (not (match-level? :debug :error ))))

  (testing "by set"
    (is      (match-level? #{:error}        :error))
    (is      (match-level? #{:error :debug} :error))
    (is (not (match-level? #{:error}        :debug)))))

(deftest test-match-throwable?
  (let [ex-a (ex RuntimeException "a")
        ex-b (ex Error)]
    (testing "equality"
      (is      (match-throwable? ex-a ex-a))
      (is (not (match-throwable? ex-a ex-b))))

    (testing "by class"
      (is      (match-throwable? Exception        ex-a))
      (is      (match-throwable? RuntimeException ex-a))
      (is (not (match-throwable? Error            ex-a))))

    (testing "by class and string"
      (is      (match-throwable? [Exception        "a"] ex-a))
      (is      (match-throwable? [RuntimeException "a"] ex-a))
      (is (not (match-throwable? [Error            "a"] ex-a)))
      (is (not (match-throwable? [RuntimeException "b"] ex-a))))

    (testing "by class and regex"
      (is      (match-throwable? [Exception        #"a"] ex-a))
      (is      (match-throwable? [RuntimeException #"a"] ex-a))
      (is (not (match-throwable? [Error            #"a"] ex-a)))
      (is (not (match-throwable? [RuntimeException #"b"] ex-a))))))

(deftest test-match-message?
  (testing "equality"
    (is      (match-message? "a" "a"))
    (is (not (match-message? "a" "b"))))

  (testing "by regex"
    (is      (match-message? #"a" "a"))
    (is (not (match-message? #"a" "b")))))

(deftest test-log-entry-matcher
  (let [ns-a      (the-ns 'clojure.test)
        ns-b      (the-ns 'clojure.tools.logging.test)
        log-entry (->LogEntry ns-a :debug nil "a")]
    (is      (match? log-entry ns-a :debug "a"))
    (is (not (match? log-entry ns-b :debug "a")))
    (is (not (match? log-entry ns-a :error "a")))
    (is (not (match? log-entry ns-a :debug "b"))))
  (let [ns-a      (the-ns 'clojure.test)
        ns-b      (the-ns 'clojure.tools.logging.test)
        ex-a      (ex Exception)
        ex-b      (ex Exception)
        log-entry (->LogEntry ns-a :debug ex-a "a")]
    (testing "4-arg match"
      (is      (match? log-entry ns-a :debug ex-a "a")))
    (testing "4-arg non-match"
      (is (not (match? log-entry ns-b :debug ex-a "a")))
      (is (not (match? log-entry ns-a :error ex-a "a")))
      (is (not (match? log-entry ns-a :debug ex-b "a")))
      (is (not (match? log-entry ns-a :debug ex-a "b"))))
    (testing "3-arg does not match 4-arg"
      (is (not (match? log-entry ns-a :debug "a"))))))

(deftest test-with-log
  (let [this-ns (the-ns 'clojure.tools.logging.test-test)
        that-ns (the-ns 'clojure.core)
        this-ex (ex Exception)
        that-ex (ex Exception)]
    (with-log
      (testing "3-arg logged?"
        (log/debug "a")
        (is (= [(->LogEntry this-ns :debug nil "a")] (the-log)))
        (is      (logged? this-ns :debug "a"))
        (is (not (logged? that-ns :debug "a")))
        (is (not (logged? this-ns :error "a")))
        (is (not (logged? this-ns :debug "b")))))
    (with-log
      (testing "4-arg logged?"
        (log/debug this-ex "a")
        (is (= [(->LogEntry this-ns :debug this-ex "a")] (the-log)))
        (is      (logged? this-ns :debug this-ex "a"))
        (is (not (logged? that-ns :debug this-ex "a")))
        (is (not (logged? this-ns :error this-ex "a")))
        (is (not (logged? this-ns :debug that-ex "a")))
        (is (not (logged? this-ns :debug this-ex "b")))))
    (with-log
      (testing "logged-all? with 3-arg params")
      (log/trace "a")
      (log/debug "b")
      (log/error "c")
      (testing "correct order"
        (is      (logged-all? [[this-ns :trace "a"]
                               [this-ns :debug "b"]
                               [this-ns :error "c"]])))
      (testing "incorrect order"
        (is (not (logged-all? [[this-ns :trace "a"]
                               [this-ns :error "c"]
                               [this-ns :debug "b"]]))))
      (testing "incorrect match"
        (is (not (logged-all? [[this-ns :trace "a"]
                               [this-ns :debug "b"]
                               [this-ns :error "x"]])))))
    (with-log
      (testing "logged-all? with 4-arg params")
      (log/trace this-ex "a")
      (log/debug this-ex "b")
      (log/error this-ex "c")
      (testing "correct order"
        (is      (logged-all? [[this-ns :trace this-ex "a"]
                               [this-ns :debug this-ex "b"]
                               [this-ns :error this-ex "c"]])))
      (testing "incorrect order"
        (is (not (logged-all? [[this-ns :trace this-ex "a"]
                               [this-ns :error this-ex "c"]
                               [this-ns :debug this-ex "b"]]))))
      (testing "incorrect match"
        (is (not (logged-all? [[this-ns :trace this-ex "a"]
                               [this-ns :debug this-ex "b"]
                               [this-ns :error this-ex "x"]])))))))


(defrecord ContextualMessage [text context])

(defmethod match-message? [[String Map] ContextualMessage]
  [[expected-text expected-context] actual]
  (and (= expected-text (:text actual))
       (= expected-context (-> actual :context (select-keys (keys expected-context))))))

(deftest test-with-log-custom-message
  (let [this-ns (the-ns 'clojure.tools.logging.test-test)
        msg     (->ContextualMessage "stuff" {:a 1 :b 2})]
    (with-log
      (log/log :debug msg)
      (is (= [(->LogEntry this-ns :debug nil msg)] (the-log)))
      (is      (logged? this-ns :debug ["stuff" {:a 1}]))
      (is      (logged? this-ns :debug ["stuff" {:a 1 :b 2}]))
      (is (not (logged? this-ns :debug ["stuff" {:a 1 :c 2}]))))))
