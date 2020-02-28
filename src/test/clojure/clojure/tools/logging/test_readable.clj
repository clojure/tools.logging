(ns clojure.tools.logging.test-readable
  (:use clojure.test)
  (:require
    [clojure.set :as set]
    [clojure.tools.logging.readable :refer :all]
    [clojure.tools.logging.test :as log-test :refer [logged? with-log]]))

(deftest logp-single-eval
  (with-log
    (let [cnt (atom 0)]
      (logp :debug (str (swap! cnt inc)))
      (logp :debug (str (swap! cnt inc)) :foo)
      (logp :debug (Exception. (str (swap! cnt inc))) (str (swap! cnt inc)))
      (logp :debug (Exception. (str (swap! cnt inc))) (str (swap! cnt inc)) :foo)
      (is (== 6 @cnt)))))

(deftest logf-single-eval
  (with-log
    (let [cnt (atom 0)]
      (logf :debug (str (swap! cnt inc)))
      (logf :debug (str (swap! cnt inc)) :foo)
      (logf :debug (Exception. (str (swap! cnt inc))) (str (swap! cnt inc)))
      (logf :debug (Exception. (str (swap! cnt inc))) (str (swap! cnt inc)) :foo)
      (is (== 6 @cnt)))))

(deftest logp-msg1
  (with-log
    (logp :debug "hello")
    (is (logged? "clojure.tools.logging.test-readable" :debug nil "hello")))
  (with-log
    (let [hello "hello"]
      (logp :debug hello))
    (is (logged? "clojure.tools.logging.test-readable" :debug nil "\"hello\"")))
  (with-log
    (let [hello "hello"]
      (binding [*print-readably* nil]
        (logp :debug hello)))
    (is (logged? "clojure.tools.logging.test-readable" :debug nil "\"hello\"")))
  (with-log
    (logp :debug (pr-str "hello"))
    (is (logged? "clojure.tools.logging.test-readable" :debug nil "\"\\\"hello\\\"\"")))
  (with-log
    (logp :debug (print-str "hello"))
    (is (logged? "clojure.tools.logging.test-readable" :debug nil "\"hello\""))))

(deftest logp-msg2
  (let [hello "hello"
        world "world"]
    (with-log
      (logp :debug "hello" "world")
      (is (logged? "clojure.tools.logging.test-readable" :debug nil "hello world")))
    (with-log
      (logp :debug "hello" world)
      (is (logged? "clojure.tools.logging.test-readable" :debug nil "hello \"world\"")))
    (with-log
      (logp :debug hello "world")
      (is (logged? "clojure.tools.logging.test-readable" :debug nil "\"hello\" world")))
    (with-log
      (logp :debug hello world)
      (is (logged? "clojure.tools.logging.test-readable" :debug nil "\"hello\" \"world\"")))
    (with-log
      (binding [*print-readably* nil]
        (logp :debug hello world))
      (is (logged? "clojure.tools.logging.test-readable" :debug nil "\"hello\" \"world\"")))))

(deftest logp-ex0
  (let [e (Exception.)]
    (with-log
      (logp :debug e)
      (is (logged? "clojure.tools.logging.test-readable" :debug nil (print-str (pr-str e)))))
    (with-log
      (binding [*print-readably* nil]
        (logp :debug e))
      (is (logged? "clojure.tools.logging.test-readable" :debug nil (print-str (pr-str e)))))))

(deftest logp-ex1
  (let [e     (Exception.)
        hello "hello"]
    (with-log
      (logp :debug e "hello")
      (is (logged? "clojure.tools.logging.test-readable" :debug e "hello")))
    (with-log
      (logp :debug e hello)
      (is (logged? "clojure.tools.logging.test-readable" :debug e "\"hello\"")))
    (with-log
      (binding [*print-readably* nil]
        (logp :debug e hello))
      (is (logged? "clojure.tools.logging.test-readable" :debug e "\"hello\"")))))

(deftest logp-ex2
  (let [e     (Exception.)
        hello "hello"
        world "world"]
    (with-log
      (logp :debug e "hello" "world")
      (is (logged? "clojure.tools.logging.test-readable" :debug e "hello world")))
    (with-log
      (logp :debug e "hello" world)
      (is (logged? "clojure.tools.logging.test-readable" :debug e "hello \"world\"")))
    (with-log
      (logp :debug e hello "world")
      (is (logged? "clojure.tools.logging.test-readable" :debug e "\"hello\" world")))
    (with-log
      (logp :debug e hello world)
      (is (logged? "clojure.tools.logging.test-readable" :debug e "\"hello\" \"world\"")))
    (with-log
      (binding [*print-readably* nil]
        (logp :debug e hello world))
      (is (logged? "clojure.tools.logging.test-readable" :debug e "\"hello\" \"world\"")))))


(deftest logf-msg1
  (with-log
    (logf :debug "hello")
    (is (logged? "clojure.tools.logging.test-readable" :debug nil "hello")))
  (with-log
    (let [hello "hello"]
      (logf :debug hello))
    (is (logged? "clojure.tools.logging.test-readable" :debug nil "hello")))
  (with-log
    (let [hello "hello"]
      (binding [*print-readably* nil]
        (logf :debug hello)))
    (is (logged? "clojure.tools.logging.test-readable" :debug nil "hello")))
  (with-log
    (logf :debug (pr-str "hello"))
    (is (logged? "clojure.tools.logging.test-readable" :debug nil "\"hello\"")))
  (with-log
    (logf :debug (print-str "hello"))
    (is (logged? "clojure.tools.logging.test-readable" :debug nil "hello"))))

(deftest logf-msg2
  (let [hello "hello %s"
        world "world"]
    (with-log
      (logf :debug "hello %s" "world")
      (is (logged? "clojure.tools.logging.test-readable" :debug nil "hello \"world\"")))
    (with-log
      (logf :debug "hello %s" world)
      (is (logged? "clojure.tools.logging.test-readable" :debug nil "hello \"world\"")))
    (with-log
      (logf :debug hello "world")
      (is (logged? "clojure.tools.logging.test-readable" :debug nil "hello \"world\"")))
    (with-log
      (logf :debug hello world)
      (is (logged? "clojure.tools.logging.test-readable" :debug nil "hello \"world\"")))
    (with-log
      (binding [*print-readably* nil]
        (logf :debug hello world))
      (is (logged? "clojure.tools.logging.test-readable" :debug nil "hello \"world\"")))))

(deftest logf-ex0
  (is (thrown? ClassCastException (logf :debug (Exception.)))))

(deftest logf-ex1
  (let [e     (Exception.)
        hello "hello"]
    (with-log
      (logf :debug e "hello")
      (is (logged? "clojure.tools.logging.test-readable" :debug e "hello")))
    (with-log
      (logf :debug e hello)
      (is (logged? "clojure.tools.logging.test-readable" :debug e "hello")))
    (with-log
      (binding [*print-readably* nil]
        (logf :debug e hello))
      (is (logged? "clojure.tools.logging.test-readable" :debug e "hello")))))

(deftest logf-ex2
  (let [e     (Exception.)
        hello "hello %s"
        world "world"]
    (with-log
      (logf :debug e "hello %s" "world")
      (is (logged? "clojure.tools.logging.test-readable" :debug e "hello \"world\"")))
    (with-log
      (logf :debug e "hello %s" world)
      (is (logged? "clojure.tools.logging.test-readable" :debug e "hello \"world\"")))
    (with-log
      (logf :debug e hello "world")
      (is (logged? "clojure.tools.logging.test-readable" :debug e "hello \"world\"")))
    (with-log
      (logf :debug e hello world)
      (is (logged? "clojure.tools.logging.test-readable" :debug e "hello \"world\"")))
    (with-log
      (binding [*print-readably* nil]
        (logf :debug e hello world))
      (is (logged? "clojure.tools.logging.test-readable" :debug e "hello \"world\"")))))


(deftest println-style
  (are [f kw] (with-log
                (f "hello" "world")
                (logged? "clojure.tools.logging.test-readable" kw nil "hello world"))
    trace :trace
    debug :debug
    info :info
    warn :warn
    error :error
    fatal :fatal))

(deftest println-style-ex
  (let [e (Exception.)]
    (are [f kw] (with-log
                  (f e "hello" "world")
                  (logged? "clojure.tools.logging.test-readable" kw e "hello world"))
      trace :trace
      debug :debug
      info :info
      warn :warn
      error :error
      fatal :fatal)))

(deftest format-style
  (are [f kw] (with-log
                (f "%s %s" "hello" "world")
                (logged? "clojure.tools.logging.test-readable" kw nil "\"hello\" \"world\""))
    tracef :trace
    debugf :debug
    infof :info
    warnf :warn
    errorf :error
    fatalf :fatal))

(deftest format-style-ex
  (let [e (Exception.)]
    (are [f kw] (with-log
                  (f e "%s %s" "hello" "world")
                  (logged? "clojure.tools.logging.test-readable" kw e "\"hello\" \"world\""))
      tracef :trace
      debugf :debug
      infof :info
      warnf :warn
      errorf :error
      fatalf :fatal)))
