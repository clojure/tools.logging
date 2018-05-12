(ns clojure.tools.test-logging
  (:use clojure.test
        clojure.tools.logging)
  (:require
    [clojure.set :as set]
    [clojure.tools.logging.impl :as impl]))

(defn test-factory [enabled-set entries-atom agent-used-atom]
  (let [main-thread (Thread/currentThread)]
    (reify impl/LoggerFactory
      (name [_] "test factory")
      (get-logger [_ log-ns]
        (reify impl/Logger
          (enabled? [_ level] (contains? enabled-set level))
          (write! [_ lvl ex msg]
            (reset! entries-atom [(str log-ns) lvl ex msg])
            (reset! agent-used-atom (not (identical? main-thread (Thread/currentThread))))))))))

(defmacro with-test-logging
  [[enabled-level-set log-entry-sym agent-used?-sym] & body]
  (let [enabled-level-set (or enabled-level-set #{:trace :debug :info :warn :error :fatal})
        log-entry-sym (or log-entry-sym 'log-entry-sym)
        agent-used?-sym (or agent-used?-sym 'agent-used?-sym)]
    `(let [~log-entry-sym (atom nil)
           ~agent-used?-sym (atom nil)]
       (binding [*logger-factory* (test-factory
                                    ~enabled-level-set
                                    ~log-entry-sym
                                    ~agent-used?-sym)]
         ~@body))))

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
  (with-test-logging []
    (let [cnt (atom 0)]
      (log :debug (swap! cnt inc))
      (log :debug (Exception. (str (swap! cnt inc))) (swap! cnt inc))
      (is (== 3 @cnt)))))

(deftest logp-single-eval
  (with-test-logging []
    (let [cnt (atom 0)]
      (logp :debug (str (swap! cnt inc)))
      (logp :debug (str (swap! cnt inc)) :foo)
      (logp :debug (Exception. (str (swap! cnt inc))) (str (swap! cnt inc)))
      (logp :debug (Exception. (str (swap! cnt inc))) (str (swap! cnt inc)) :foo)
      (is (== 6 @cnt)))))

(deftest logf-single-eval
  (with-test-logging []
    (let [cnt (atom 0)]
      (logf :debug (str (swap! cnt inc)))
      (logf :debug (str (swap! cnt inc)) :foo)
      (logf :debug (Exception. (str (swap! cnt inc))) (str (swap! cnt inc)))
      (logf :debug (Exception. (str (swap! cnt inc))) (str (swap! cnt inc)) :foo)
      (is (== 6 @cnt)))))

(deftest log-msg
  (with-test-logging [#{:debug} log-entry]
    (log :debug "foo")
    (is (= ["clojure.tools.test-logging"
            :debug
            nil
            "foo"]
           @log-entry))))

(deftest log-ex
  (with-test-logging [#{:debug} log-entry]
    (let [e (Exception.)]
      (log :debug e "foo")
      (is (= ["clojure.tools.test-logging"
              :debug
              e
              "foo"]
             @log-entry)))))

(deftest log-custom-ns
  (with-test-logging [#{:debug} log-entry]
    (let [e (Exception.)]
      (log "other.ns" :debug e "foo")
      (is (= ["other.ns"
              :debug
              e
              "foo"]
             @log-entry)))))

(def all-levels #{:trace :debug :info :warn :error :fatal})
(def default-tx-agent-levels #{:info :warn})
(def default-no-tx-agent-levels (set/difference all-levels default-tx-agent-levels))

(deftest log-tx-agent-default
  (doseq [level default-tx-agent-levels]
    (with-test-logging [#{level} log-entry agent-used?]
      (dosync
        (log level "foo"))
      (await *logging-agent*)
      (is (= ["clojure.tools.test-logging" level nil "foo"] @log-entry))
      (is @agent-used?))))

(deftest log-no-tx-agent-default
  (doseq [level default-no-tx-agent-levels]
    (with-test-logging [#{level} log-entry agent-used?]
      (dosync
        (log level "foo"))
      (await *logging-agent*)
      (is (= ["clojure.tools.test-logging" level nil "foo"] @log-entry))
      (is (not @agent-used?)))))

(deftest log-tx-agent-custom
  (doseq [level default-no-tx-agent-levels]
    (binding [*tx-agent-levels* default-no-tx-agent-levels]
      (with-test-logging [#{level} log-entry agent-used?]
        (dosync
          (log level "foo"))
        (await *logging-agent*)
        (is (= ["clojure.tools.test-logging" level nil "foo"] @log-entry))
        (is @agent-used?)))))

(deftest log-no-tx-agent-custom
  (doseq [level default-tx-agent-levels]
    (binding [*tx-agent-levels* default-no-tx-agent-levels]
      (with-test-logging [#{level} log-entry agent-used?]
        (dosync
          (log level "foo"))
        (await *logging-agent*)
        (is (= ["clojure.tools.test-logging" level nil "foo"] @log-entry))
        (is (not @agent-used?))))))

(deftest log-force-agent
  (doseq [level all-levels]
    (binding [*force* :agent]
      (with-test-logging [#{level} log-entry agent-used?]
        (dosync
          (log level "foo"))
        (await *logging-agent*)
        (is (= ["clojure.tools.test-logging" level nil "foo"] @log-entry))
        (is @agent-used?)))))

(deftest log-force-direct
  (doseq [level all-levels]
    (binding [*force* :direct]
      (with-test-logging [#{level} log-entry agent-used?]
        (dosync
          (log level "foo"))
        (await *logging-agent*)
        (is (= ["clojure.tools.test-logging" level nil "foo"] @log-entry))
        (is (not @agent-used?))))))

;(comment ; Tests failing on build machine for some unknown reason.
;         ; See http://groups.google.com/group/clojure-dev/browse_thread/thread/fde32342f3c006bd
;)

(deftest logp-msg-no-optimize
  (with-test-logging [#{:debug} log-entry]
    (let [a "foo"
          b "bar"]
      (logp :debug a b))
    (is (= ["clojure.tools.test-logging" :debug nil "foo bar"] @log-entry))))

(deftest logp-msg1
  (with-test-logging [#{:debug} log-entry]
    (logp :debug "hello")
    (is (= ["clojure.tools.test-logging" :debug nil "hello"] @log-entry))))

(deftest logp-msg2
  (with-test-logging [#{:debug} log-entry]
    (logp :debug "hello" "world")
    (is (= ["clojure.tools.test-logging" :debug nil "hello world"]
           @log-entry))))

(deftest logp-ex0
  (with-test-logging [#{:debug} log-entry]
    (let [e (Exception.)]
      (logp :debug e)
      (is (= ["clojure.tools.test-logging" :debug nil (print-str e)]
             @log-entry)))))

(deftest logp-ex1
  (with-test-logging [#{:debug} log-entry]
    (let [e (Exception.)]
      (logp :debug e "hello")
      (is (= ["clojure.tools.test-logging" :debug e "hello"]
             @log-entry)))))

(deftest logp-ex2
  (with-test-logging [#{:debug} log-entry]
    (let [e (Exception.)]
      (logp :debug e "hello" "world")
      (is (= ["clojure.tools.test-logging" :debug e "hello world"]
             @log-entry)))))

(deftest logf-msg-no-optimize
  (with-test-logging [#{:debug} log-entry]
    (let [a "foo %s"
          b "bar"]
      (logf :debug a b))
    (is (= ["clojure.tools.test-logging" :debug nil "foo bar"]
           @log-entry))))

(deftest logf-msg1
  (with-test-logging [#{:debug} log-entry]
    (logf :debug "hello")
    (is (= ["clojure.tools.test-logging" :debug nil "hello"]
           @log-entry))))

(deftest logf-msg3
  (with-test-logging [#{:debug} log-entry]
    (logf :debug "%s %s" "hello" "world")
    (is (= ["clojure.tools.test-logging" :debug nil "hello world"]
           @log-entry))))

(deftest logf-ex0
  (is (nil? (logf :debug (Exception.))))
  )

(deftest logf-ex3
  (with-test-logging [#{:debug} log-entry]
    (let [e (Exception.)]
      (logf :debug e "%s %s" "hello" "world")
      (is (= ["clojure.tools.test-logging" :debug e "hello world"]
             @log-entry)))))

(deftest enabled-true
  (with-test-logging [#{:fatal}]
    (is (enabled? :fatal))))

(deftest enabled-false
  (with-test-logging [#{:debug}]
    (is (not (enabled? :fatal)))))

(deftest spy-default
  (with-test-logging [#{:debug} log-entry]
    (spy (+ 4 5))
    (is (= ["clojure.tools.test-logging" :debug nil (format "(+ 4 5)%n=> 9")]
           @log-entry))))

(deftest spy-level
  (doseq [level all-levels]
    (with-test-logging [#{level} log-entry]
      (spy level (+ 4 5))
      (is (= ["clojure.tools.test-logging" level nil (format "(+ 4 5)%n=> 9")]
             @log-entry)))))

(deftest spyf-default
  (with-test-logging [#{:debug} log-entry]
    (spyf "result: %s" (+ 4 5))
    (is (= ["clojure.tools.test-logging" :debug nil (format "result: 9")]
           @log-entry))))

(deftest spyf-level
  (doseq [level all-levels]
    (with-test-logging [#{level} log-entry]
      (spyf level "result: %s" (+ 4 5))
      (is (= ["clojure.tools.test-logging" level nil (format "result: 9")]
             @log-entry)))))

(deftest capturing
  (with-test-logging [all-levels log-entry]
    (log-capture! "foobar")
    (.println System/out "hello world")
    (is (= ["foobar" :info nil "hello world"]
           @log-entry))
    (log-uncapture!))
  
  (with-test-logging [all-levels log-entry]
    (log-capture! "foobar")
    (.println System/err "oh noes")
    (is (= ["foobar" :error nil "oh noes"]
           @log-entry))
    (log-uncapture!)))

(deftest capturing-level
  (with-test-logging [all-levels log-entry]
    (log-capture! "foobar" :error :fatal)
    (.println System/out "hello world")
    (is (= ["foobar" :error nil "hello world"]
           @log-entry))
    (log-uncapture!))
  
  (with-test-logging [all-levels log-entry]
    (log-capture! "foobar" :error :fatal)
    (.println System/err "oh noes")
    (is (= ["foobar" :fatal nil "oh noes"]
           @log-entry))
    (log-uncapture!)))


(deftest with-logs-default
  (with-test-logging [all-levels log-entry]
    (with-logs "foobar"
      (println "hello world"))
    (is (= ["foobar" :info nil "hello world"]
           @log-entry))))

(deftest with-logs-level
  (with-test-logging [all-levels log-entry]
    (with-logs ["foobar" :error :fatal]
      (println "hello world"))
    (is (= ["foobar" :error nil "hello world"]
           @log-entry)))

  (with-test-logging [all-levels log-entry]
    (with-logs ["foobar" :error :fatal]
      (binding [*out* *err*]
        (println "hello world")))
    (is (= ["foobar" :fatal nil "hello world"]
           @log-entry))))

(deftest println-style
  (are [f kw] (with-test-logging [all-levels log-entry]
                (f "hello" "world")
                (= ["clojure.tools.test-logging" kw nil "hello world"]
                   @log-entry))
    trace :trace
    debug :debug
    info :info
    warn :warn
    error :error
    fatal :fatal))

(deftest println-style-ex
  (let [e (Exception.)]
    (are [f kw] (with-test-logging [all-levels log-entry]
                  (f e "hello" "world")
                  (= ["clojure.tools.test-logging" kw e "hello world"]
                     @log-entry))
      trace :trace
      debug :debug
      info :info
      warn :warn
      error :error
      fatal :fatal)))

(deftest format-style
  (are [f kw] (with-test-logging [all-levels log-entry]
                  (f "%s %s" "hello" "world")
                  (= ["clojure.tools.test-logging" kw nil "hello world"]
                     @log-entry))
    tracef :trace
    debugf :debug
    infof :info
    warnf :warn
    errorf :error
    fatalf :fatal))

(deftest format-style-ex
  (let [e (Exception.)]
    (are [f kw] (with-test-logging [all-levels log-entry]
                  (f e "%s %s" "hello" "world")
                  (= ["clojure.tools.test-logging" kw e "hello world"]
                     @log-entry))
      tracef :trace
      debugf :debug
      infof :info
      warnf :warn
      errorf :error
      fatalf :fatal)))
