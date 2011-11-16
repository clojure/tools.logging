(ns clojure.tools.test-logging
  [:use clojure.test
        clojure.tools.logging]
  [:require [clojure.tools.logging.impl :as impl]])

(deftest test-impls
  (are [lf] (binding [*logger-factory* lf]
              (enabled? :fatal))
       (impl/slf4j-factory)
       (impl/cl-factory)
       (impl/log4j-factory)
       (impl/jul-factory)))

(deftest test-get-factory
  (are [lf] (binding [*logger-factory* lf]
              (enabled? :fatal))
       (impl/get-factory :slf4j)
       (impl/get-factory :cl)
       (impl/get-factory :log4j)
       (impl/get-factory :jul)))

(def ^{:dynamic true} *entries* (atom []))

(defn test-factory [enabled-set]
  (reify impl/LoggerFactory
    (name [_] "test factory")
    (get-logger [_ log-ns]
      (reify impl/Logger
        (enabled? [_ level] (contains? enabled-set level))
        (write! [_ lvl ex msg]
          (swap! *entries* conj [(str log-ns) lvl ex msg]))))))

(use-fixtures :once
  (fn [f]
    (binding [*logger-factory*
              (test-factory #{:trace :debug :info :warn :error :fatal})]
      (f))))

(use-fixtures :each
  (fn [f]
    (f)
    (swap! *entries* (constantly []))))


(deftest log-msg
  (log :debug "foo")
  (is (= ["clojure.tools.test-logging"
          :debug
          nil
          "foo"]
        (peek @*entries*))))

(deftest log-ex
  (let [e (Exception.)]
    (log :debug e "foo")
    (is (= ["clojure.tools.test-logging"
            :debug
            e
            "foo"]
          (peek @*entries*)))))

(deftest log-custom-ns
  (let [e (Exception.)]
    (log "other.ns" :debug e "foo")
    (is (= ["other.ns"
            :debug
            e
            "foo"]
          (peek @*entries*)))))

(comment ; Tests failing on build machine for some unknown reason.
         ; See http://groups.google.com/group/clojure-dev/browse_thread/thread/fde32342f3c006bd
(deftest log-tx-agent
  (let [flag (atom false)
        so send-off]
    (binding [send-off (fn [a f & args]
                         (compare-and-set! flag false true)
                         (apply so a f args))]
      (dosync
        (log :info "foo")))
    (await *logging-agent*)
    (is (= ["clojure.tools.test-logging"
            :info
            nil
            "foo"]
          (peek @*entries*)))
    (is (true? @flag))))

(deftest log-tx-noagent
  (let [flag (atom false)
        so send-off]
    (binding [send-off (fn [a f & args]
                         (compare-and-set! flag false true)
                         (apply so a f args))]
      (dosync
        (log :error "foo")))
    (await *logging-agent*)
    (is (= ["clojure.tools.test-logging"
            :error
            nil
            "foo"]
          (peek @*entries*)))
    (is (false? @flag))))

(deftest log-alter-tx-agent-levels
  (let [flag (atom false)
        so send-off]
    (binding [send-off (fn [a f & args]
                         (compare-and-set! flag false true)
                         (apply so a f args))
              *tx-agent-levels* #{:error}]
      (dosync
        (log :error "foo")))
    (await *logging-agent*)
    (is (= ["clojure.tools.test-logging"
            :error
            nil
            "foo"]
          (peek @*entries*)))
    (is (true? @flag))))

(deftest log-force-agent
  (let [flag (atom false)
        so send-off]
    (binding [send-off (fn [a f & args]
                         (compare-and-set! flag false true)
                         (apply so a f args))
              *force* :agent]
      (log :debug "foo"))
    (await *logging-agent*)
    (is (= ["clojure.tools.test-logging"
            :debug
            nil
            "foo"]
          (peek @*entries*)))
    (is (true? @flag))))

(deftest log-force-direct
  (let [flag (atom false)
        so send-off]
    (binding [send-off (fn [a f & args]
                         (compare-and-set! flag false true)
                         (apply so a f args))
              *force* :direct]
      (dosync
        (log :info "foo")))
    (await *logging-agent*)
    (is (= ["clojure.tools.test-logging"
            :info
            nil
            "foo"]
          (peek @*entries*)))
    (is (false? @flag))))
)

(deftest logp-msg-no-optimize
  (let [a "foo"
        b "bar"]
    (logp :debug a b))
  (is (= ["clojure.tools.test-logging"
          :debug
          nil
          "foo bar"]
        (peek @*entries*))))

(deftest logp-msg1
  (logp :debug "hello")
  (is (= ["clojure.tools.test-logging"
          :debug
          nil
          "hello"]
        (peek @*entries*))))

(deftest logp-msg2
  (logp :debug "hello" "world")
  (is (= ["clojure.tools.test-logging"
          :debug
          nil
          "hello world"]
        (peek @*entries*))))

(deftest logp-ex0
  (let [e (Exception.)]
    (logp :debug e)
    (is (= ["clojure.tools.test-logging"
            :debug
            nil
            (print-str e)]
          (peek @*entries*)))))

(deftest logp-ex1
  (let [e (Exception.)]
    (logp :debug e "hello")
    (is (= ["clojure.tools.test-logging"
            :debug
            e
            "hello"]
          (peek @*entries*)))))

(deftest logp-ex2
  (let [e (Exception.)]
    (logp :debug e "hello" "world")
    (is (= ["clojure.tools.test-logging"
            :debug
            e
            "hello world"]
          (peek @*entries*)))))

(deftest logf-msg-no-optimize
  (let [a "foo %s"
        b "bar"]
    (logf :debug a b))
  (is (= ["clojure.tools.test-logging"
          :debug
          nil
          "foo bar"]
        (peek @*entries*))))

(deftest logf-msg1
  (logf :debug "hello")
  (is (= ["clojure.tools.test-logging"
          :debug
          nil
          "hello"]
        (peek @*entries*))))

(deftest logf-msg3
  (logf :debug "%s %s" "hello" "world")
  (is (= ["clojure.tools.test-logging"
          :debug
          nil
          "hello world"]
        (peek @*entries*))))

(deftest logf-ex0
  (is (thrown? Exception (logf :debug (Exception.)))))

(deftest logf-ex3
  (let [e (Exception.)]
    (logf :debug e "%s %s" "hello" "world")
    (is (= ["clojure.tools.test-logging"
            :debug
            e
            "hello world"]
          (peek @*entries*)))))

(deftest enabled-true
  (is (= true (enabled? :fatal))))

(deftest enabled-false
  (binding [*logger-factory* (test-factory #{})]
    (is (= false (enabled? :fatal)))))

(deftest spy-default
  (spy (+ 4 5))
  (is (= ["clojure.tools.test-logging"
          :debug
          nil
          (format "(+ 4 5)%n=> 9")]
        (peek @*entries*))))

(deftest spy-level
  (spy :fatal (+ 4 5))
  (is (= ["clojure.tools.test-logging"
          :fatal
          nil
          (format "(+ 4 5)%n=> 9")]
        (peek @*entries*))))

(comment
(deftest capturing
  (log-capture! "foobar")
  (.println System/out "hello world")
  (is (= ["foobar"
          :info
          nil
          "hello world"]
        (peek @*entries*)))
  (.println System/err "oh noes")
  (is (= ["foobar"
          :error
          nil
          "oh noes"]
        (peek @*entries*)))
  (log-uncapture!))

(deftest capturing-level
  (log-capture! "foobar" :error :fatal)
  (.println System/out "hello world")
  (is (= ["foobar"
          :error
          nil
          "hello world"]
        (peek @*entries*)))
  (.println System/err "oh noes")
  (is (= ["foobar"
          :fatal
          nil
          "oh noes"]
        (peek @*entries*)))
  (log-uncapture!))

(deftest with-logs-default
  (with-logs "foobar" (println "hello world"))
  (is (= ["foobar"
          :info
          nil
          "hello world"]
        (peek @*entries*))))

(deftest with-logs-level
  (with-logs ["foobar" :fatal :fatal] (println "hello world"))
  (is (= ["foobar"
          :fatal
          nil
          "hello world"]
        (peek @*entries*))))
)

(deftest println-style
  (are [f kw]
    (= ["clojure.tools.test-logging"
        kw
        nil
        "hello world"]
      (do
        (f "hello" "world")
        (peek @*entries*)))
    trace :trace
    debug :debug
    info :info
    warn :warn
    error :error
    fatal :fatal))

(deftest println-style-ex
  (let [e (Exception.)]
    (are [f kw]
      (= ["clojure.tools.test-logging"
          kw
          e
          "hello world"]
        (do
          (f e "hello" "world")
          (peek @*entries*)))
      trace :trace
      debug :debug
      info :info
      warn :warn
      error :error
      fatal :fatal)))

(deftest format-style
  (are [f kw]
    (= ["clojure.tools.test-logging"
        kw
        nil
        "hello world"]
      (do
        (f "%s %s" "hello" "world")
        (peek @*entries*)))
    tracef :trace
    debugf :debug
    infof :info
    warnf :warn
    errorf :error
    fatalf :fatal))

(deftest format-style-ex
  (let [e (Exception.)]
    (are [f kw]
      (= ["clojure.tools.test-logging"
          kw
          e
          "hello world"]
        (do
          (f e "%s %s" "hello" "world")
          (peek @*entries*)))
      tracef :trace
      debugf :debug
      infof :info
      warnf :warn
      errorf :error
      fatalf :fatal)))
