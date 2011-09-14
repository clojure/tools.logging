;; Copyright (c) Alex Taggart. All rights reserved.  The use
;; and distribution terms for this software are covered by the Eclipse
;; Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;; which can be found in the file epl-v10.html at the root of this
;; distribution.  By using this software in any fashion, you are
;; agreeing to be bound by the terms of this license.  You must not
;; remove this notice, or any other, from this software.

(ns clojure.tools.logging.slf4j
  (:require [clojure.tools.logging.impl :as impl]))

(defn load-factory
  "Returns a SLF4J-based implementation of the LoggerFactory protocol, or nil if
  not available. End-users should not need to call this."
  []
  (try
    (Class/forName "org.slf4j.Logger")
    (eval
      '(do
        (extend-type org.slf4j.Logger
          impl/Logger
          (enabled? [logger# level#]
            (condp = level#
              :trace (.isTraceEnabled logger#)
              :debug (.isDebugEnabled logger#)
              :info  (.isInfoEnabled  logger#)
              :warn  (.isWarnEnabled  logger#)
              :error (.isErrorEnabled logger#)
              :fatal (.isErrorEnabled logger#)
              (throw (IllegalArgumentException. (str level#)))))
          (write! [^org.slf4j.Logger logger# level# ^Throwable e# marker# msg#]
            (let [^String msg# (str msg#)]
              (condp = level#
                :trace (.trace logger# msg# marker# e#)
                :debug (.debug logger# msg# marker# e#)
                :info  (.info  logger# msg# marker# e#)
                :warn  (.warn  logger# msg# marker# e#)
                :error (.error logger# msg# marker# e#)
                :fatal (.error logger# msg# marker# e#)
                (throw (IllegalArgumentException. (str level#)))))))
        (reify impl/LoggerFactory
          (name [_#]
            "org.slf4j")
          (get-logger [_# logger-ns#]
            (org.slf4j.LoggerFactory/getLogger ^String (str logger-ns#))))))
    (catch Exception e nil)))

