(ns clojure.tools.logging.test-impl
  (:use clojure.test)
  (:require [clojure.tools.logging.impl :as impl]))

(def logger-factories [(impl/slf4j-factory)
                       (impl/cl-factory)
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
                    (impl/enabled? logger level))
       (impl/log4j-factory) org.apache.log4j.Level/FATAL
       (impl/jul-factory)   java.util.logging.Level/SEVERE))

(deftest test-write-non-kw-levels
  ; Just checking that the call doesn't blow up.
  (are [lf level] (let [logger (impl/get-logger lf "logger-ns")]
                    (nil? (impl/write! logger level nil "message")))
       (impl/log4j-factory) org.apache.log4j.Level/FATAL
       (impl/jul-factory)   java.util.logging.Level/SEVERE))
