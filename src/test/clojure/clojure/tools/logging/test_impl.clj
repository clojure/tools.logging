(ns clojure.tools.logging.test-impl
  (:use clojure.test)
  (:require [clojure.tools.logging.impl :as impl]))

(let [[factory level] (try
                        [(impl/log4j2-factory) (eval 'org.apache.logging.log4j.Level/FATAL)]
                        (catch UnsupportedClassVersionError _
                          ; This will happen when not using a 1.7+ jvm, e.g.,
                          ; during matrix testing. Use the no-op factory
                          ; to get past testing log4j2.
                          [impl/disabled-logger-factory nil]))]
  (def maybe-log4j2-factory factory)
  (def maybe-log4j2-level level))

(def logger-factories [impl/disabled-logger-factory
                       (impl/slf4j-factory)
                       (impl/cl-factory)
                       maybe-log4j2-factory
                       (impl/log4j-factory)
                       (impl/jul-factory)])

(deftest test-logger-factory-impls-satisfy
  (doseq [lf logger-factories]
    (is (satisfies? impl/LoggerFactory lf))))

(deftest test-logger-impls-satisfy
  (doseq [lf logger-factories]
    (is (satisfies? impl/Logger (impl/get-logger lf "logger-ns")))))

(deftest test-enabled-non-kw-levels
  ; Just checking that the call doesn't blow up.
  (are [lf level] (let [logger (impl/get-logger lf "logger-ns")]
                    (impl/enabled? logger level)
                    true)
       maybe-log4j2-factory maybe-log4j2-level
       (impl/log4j-factory) org.apache.log4j.Level/FATAL
       (impl/jul-factory)   java.util.logging.Level/SEVERE))

(deftest test-write-non-kw-levels
  ; Just checking that the call doesn't blow up.
  (are [lf level] (let [logger (impl/get-logger lf "logger-ns")]
                    (impl/write! logger level nil "message")
                    true)
       maybe-log4j2-factory maybe-log4j2-level
       (impl/log4j-factory) org.apache.log4j.Level/FATAL
       (impl/jul-factory)   java.util.logging.Level/SEVERE))
