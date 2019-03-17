(ns clojure.tools.test-logging
  (:use clojure.test
        clojure.tools.logging)
  (:require
    [clojure.set :as set]
    [clojure.tools.logging.impl :as impl]
    [clojure.tools.logging.test :as log-test :refer [logged?
                                                     matches]]))

; TODO: Improve tools.logging.test so that the ability to check the thread is
;       not so hacky/out-of-band from the normal flow.


(defn log-entry-with-thread [logger-ns level throwable message]
  (assoc (log-test/->LogEntry logger-ns level throwable message)
         ::thread (Thread/currentThread)))

(defn same-thread? [log-entry]
  (identical? (::thread log-entry)
              (Thread/currentThread)))

(defn direct-logged? [logger-ns level throwable message]
  (->> (log-test/matches logger-ns level throwable message)
    (filter same-thread?)
    seq
    boolean))

(defn agent-logged? [logger-ns level throwable message]
  (->> (log-test/matches logger-ns level throwable message)
    (remove same-thread?)
    seq
    boolean))

(defmacro with-log [& body]
  `(let [stateful-log# (log-test/atomic-log log-entry-with-thread)
         logger-factory# (log-test/logger-factory stateful-log# (constantly true))]
     (binding [log-test/*stateful-log*   stateful-log#
               *logger-factory* logger-factory#]
       ~@body)))

(defmacro with-log-level [level & body]
  `(let [stateful-log# (log-test/atomic-log log-entry-with-thread)
         logger-factory# (log-test/logger-factory stateful-log# #(= ~level %2))]
     (binding [log-test/*stateful-log*   stateful-log#
               *logger-factory* logger-factory#]
       ~@body)))

(deftest log-yields-nil
  (let [enabled-logger  (reify impl/Logger
                          (enabled? [_ _] true)
                          (write! [_ _ _ _] :non-nil))
        disabled-logger (reify impl/Logger
                          (enabled? [_ _] false)
                          (write! [_ _ _ _] :non-nil))]
    (binding [*force* :agent]
      (is (nil? (log* enabled-logger :debug nil :msg)))
      (is (nil? (log* disabled-logger :debug nil :msg))))
    (binding [*force* :direct]
      (is (nil? (log* enabled-logger :debug nil :msg)))
      (is (nil? (log* disabled-logger :debug nil :msg))))))

(deftest log-single-eval
  (with-log
    (let [cnt (atom 0)]
      (log :debug (swap! cnt inc))
      (log :debug (Exception. (str (swap! cnt inc))) (swap! cnt inc))
      (is (== 3 @cnt)))))

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

(deftest log-msg
  (with-log
    (log :debug "foo")
    (is (logged? "clojure.tools.test-logging"
                 :debug
                 nil
                 "foo"))))

(deftest log-ex
  (with-log
    (let [e (Exception.)]
      (log :debug e "foo")
      (is (logged? "clojure.tools.test-logging"
                   :debug
                   e
                   "foo")))))

(deftest log-custom-ns
  (with-log
    (let [e (Exception.)]
      (log "other.ns" :debug e "foo")
      (is (logged? "other.ns"
                   :debug
                   e
                   "foo")))))

(def all-levels #{:trace :debug :info :warn :error :fatal})
(def default-tx-agent-levels #{:info :warn})
(def default-no-tx-agent-levels (set/difference all-levels default-tx-agent-levels))

(deftest log-tx-agent-default
  (doseq [level default-tx-agent-levels]
    (with-log-level level
      (dosync
        (log level "foo"))
      (await *logging-agent*)
      (is (agent-logged? "clojure.tools.test-logging" level nil "foo")))))

(deftest log-no-tx-agent-default
  (doseq [level default-no-tx-agent-levels]
    (with-log-level level
      (dosync
        (log level "foo"))
      (await *logging-agent*)
      (is (direct-logged? "clojure.tools.test-logging" level nil "foo")))))

(deftest log-tx-agent-custom
  (doseq [level default-no-tx-agent-levels]
    (binding [*tx-agent-levels* default-no-tx-agent-levels]
      (with-log-level level
        (dosync
          (log level "foo"))
        (await *logging-agent*)
        (is (agent-logged? "clojure.tools.test-logging" level nil "foo"))))))

(deftest log-no-tx-agent-custom
  (doseq [level default-tx-agent-levels]
    (binding [*tx-agent-levels* default-no-tx-agent-levels]
      (with-log-level level
        (dosync
          (log level "foo"))
        (await *logging-agent*)
        (is (direct-logged? "clojure.tools.test-logging" level nil "foo"))))))

(deftest log-force-agent
  (doseq [level all-levels]
    (binding [*force* :agent]
      (with-log-level level
        (dosync
          (log level "foo"))
        (await *logging-agent*)
        (is (agent-logged? "clojure.tools.test-logging" level nil "foo"))))))

(deftest log-force-direct
  (doseq [level all-levels]
    (binding [*force* :direct]
      (with-log-level level
        (dosync
          (log level "foo"))
        (await *logging-agent*)
        (is (direct-logged? "clojure.tools.test-logging" level nil "foo"))))))

(deftest logp-msg-no-optimize
  (with-log
    (let [a "foo"
          b "bar"]
      (logp :debug a b))
    (is (logged? "clojure.tools.test-logging" :debug nil "foo bar"))))

(deftest logp-msg1
  (with-log
    (logp :debug "hello")
    (is (logged? "clojure.tools.test-logging" :debug nil "hello"))))

(deftest logp-msg2
  (with-log
    (logp :debug "hello" "world")
    (is (logged? "clojure.tools.test-logging" :debug nil "hello world"))))

(deftest logp-ex0
  (with-log
    (let [e (Exception.)]
      (logp :debug e)
      (is (logged? "clojure.tools.test-logging" :debug nil (print-str e))))))

(deftest logp-ex1
  (with-log
    (let [e (Exception.)]
      (logp :debug e "hello")
      (is (logged? "clojure.tools.test-logging" :debug e "hello")))))

(deftest logp-ex2
  (with-log
    (let [e (Exception.)]
      (logp :debug e "hello" "world")
      (is (logged? "clojure.tools.test-logging" :debug e "hello world")))))

(deftest logf-msg-no-optimize
  (with-log
    (let [a "foo %s"
          b "bar"]
      (logf :debug a b))
    (is (logged? "clojure.tools.test-logging" :debug nil "foo bar"))))

(deftest logf-msg1
  (with-log
    (logf :debug "hello")
    (is (logged? "clojure.tools.test-logging" :debug nil "hello"))))

(deftest logf-msg3
  (with-log
    (logf :debug "%s %s" "hello" "world")
    (is (logged? "clojure.tools.test-logging" :debug nil "hello world"))))

(deftest logf-ex0
  (is (thrown? ClassCastException (logf :debug (Exception.)))))

(deftest logf-ex3
  (with-log
    (let [e (Exception.)]
      (logf :debug e "%s %s" "hello" "world")
      (is (logged? "clojure.tools.test-logging" :debug e "hello world")))))

(deftest enabled-true
  (with-log-level :fatal
    (is (enabled? :fatal))))

(deftest enabled-false
  (with-log-level :debug
    (is (not (enabled? :fatal)))))

(deftest spy-default
  (with-log-level :debug
    (spy (+ 4 5))
    (is (logged? "clojure.tools.test-logging" :debug nil (format "(+ 4 5)%n=> 9")))))

(deftest spy-level
  (doseq [level all-levels]
    (with-log-level level
      (spy level (+ 4 5))
      (is (logged? "clojure.tools.test-logging" level nil (format "(+ 4 5)%n=> 9"))))))

(deftest spyf-default
  (with-log-level :debug
    (spyf "result: %s" (+ 4 5))
    (is (logged? "clojure.tools.test-logging" :debug nil (format "result: 9")))))

(deftest spyf-level
  (doseq [level all-levels]
    (with-log-level level
      (spyf level "result: %s" (+ 4 5))
      (is (logged? "clojure.tools.test-logging" level nil (format "result: 9"))))))

(deftest capturing
  (with-log
    (log-capture! "foobar")
    (.println System/out "hello world")
    (is (logged? "foobar" :info nil "hello world"))
    (log-uncapture!))
  
  (with-log
    (log-capture! "foobar")
    (.println System/err "oh noes")
    (is (logged? "foobar" :error nil "oh noes"))
    (log-uncapture!)))

(deftest capturing-level
  (with-log
    (log-capture! "foobar" :error :fatal)
    (.println System/out "hello world")
    (is (logged? "foobar" :error nil "hello world"))
    (log-uncapture!))
  
  (with-log
    (log-capture! "foobar" :error :fatal)
    (.println System/err "oh noes")
    (is (logged? "foobar" :fatal nil "oh noes"))
    (log-uncapture!)))


(deftest with-logs-default
  (with-log
    (with-logs "foobar"
      (println "hello world"))
    (is (logged? "foobar" :info nil "hello world"))))

(deftest with-logs-level
  (with-log
    (with-logs ["foobar" :error :fatal]
      (println "hello world"))
    (is (logged? "foobar" :error nil "hello world")))

  (with-log
    (with-logs ["foobar" :error :fatal]
      (binding [*out* *err*]
        (println "hello world")))
    (is (logged? "foobar" :fatal nil "hello world"))))

(deftest println-style
  (are [f kw] (with-log
                (f "hello" "world")
                (logged? "clojure.tools.test-logging" kw nil "hello world"))
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
                  (logged? "clojure.tools.test-logging" kw e "hello world"))
      trace :trace
      debug :debug
      info :info
      warn :warn
      error :error
      fatal :fatal)))

(deftest format-style
  (are [f kw] (with-log
                (f "%s %s" "hello" "world")
                (logged? "clojure.tools.test-logging" kw nil "hello world"))
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
                  (logged? "clojure.tools.test-logging" kw e "hello world"))
      tracef :trace
      debugf :debug
      infof :info
      warnf :warn
      errorf :error
      fatalf :fatal)))
