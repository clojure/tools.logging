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
