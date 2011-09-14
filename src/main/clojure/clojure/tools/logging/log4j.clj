;; Copyright (c) Alex Taggart. All rights reserved.  The use
;; and distribution terms for this software are covered by the Eclipse
;; Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;; which can be found in the file epl-v10.html at the root of this
;; distribution.  By using this software in any fashion, you are
;; agreeing to be bound by the terms of this license.  You must not
;; remove this notice, or any other, from this software.

(ns clojure.tools.logging.log4j
  (:require [clojure.tools.logging.impl :as impl]))

(defn load-factory
  "Returns a log4j-based implementation of the LoggerFactory protocol, or nil if
  not available. End-users should not need to call this."
  []
  (try
    (Class/forName "org.apache.log4j.Logger")
    (eval
      '(let [levels# {:trace org.apache.log4j.Level/TRACE
                      :debug org.apache.log4j.Level/DEBUG
                      :info  org.apache.log4j.Level/INFO
                      :warn  org.apache.log4j.Level/WARN
                      :error org.apache.log4j.Level/ERROR
                      :fatal org.apache.log4j.Level/FATAL}]
         (extend-type org.apache.log4j.Logger
           impl/Logger
           (enabled? [logger# level#]
             (.isEnabledFor logger#
               (or
                 (levels# level#)
                 (throw (IllegalArgumentException. (str level#))))))
           (write! [logger# level# e# marker# msg#]
             (let [level# (or
                            (levels# level#)
                            (throw (IllegalArgumentException. (str level#))))]
               (if-not e#
                 (.log logger# level# msg#)
                 (.log logger# level# msg# e#)))))
         (reify impl/LoggerFactory
           (name [_#]
             "org.apache.log4j")
           (get-logger [_# logger-ns#]
             (org.apache.log4j.Logger/getLogger ^String (str logger-ns#))))))
    (catch Exception e nil)))

