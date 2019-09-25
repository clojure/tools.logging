(ns external.ns
  "Used by clojure.tools.logging.test-logging."
  (:require [clojure.tools.logging.impl :as impl]))

(defn factory []
  (reify impl/LoggerFactory
    (name [_] "external.ns/good-fn")
    (get-logger [_ _] (impl/disabled-logger))))
