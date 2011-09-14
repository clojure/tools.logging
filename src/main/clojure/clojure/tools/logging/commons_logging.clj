;; Copyright (c) Alex Taggart. All rights reserved.  The use
;; and distribution terms for this software are covered by the Eclipse
;; Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;; which can be found in the file epl-v10.html at the root of this
;; distribution.  By using this software in any fashion, you are
;; agreeing to be bound by the terms of this license.  You must not
;; remove this notice, or any other, from this software.

(ns clojure.tools.logging.commons-logging
  (:require [clojure.tools.logging.impl :as impl]))

(defn load-factory
  "Returns a commons-logging-based implementation of the LoggerFactory protocol, or
  nil if not available. End-users should not need to call this."
  []
  (try
    (Class/forName "org.apache.commons.logging.Log")
    (eval
      '(do
         (extend-type org.apache.commons.logging.Log
           impl/Logger
           (enabled? [logger# level#]
             (condp = level#
               :trace (.isTraceEnabled logger#)
               :debug (.isDebugEnabled logger#)
               :info  (.isInfoEnabled  logger#)
               :warn  (.isWarnEnabled  logger#)
               :error (.isErrorEnabled logger#)
               :fatal (.isFatalEnabled logger#)
               (throw (IllegalArgumentException. (str level#)))))
           (write! [logger# level# e# marker# msg#]
             (condp = level#
               :trace (.trace logger# msg# e#)
               :debug (.debug logger# msg# e#)
               :info  (.info  logger# msg# e#)
               :warn  (.warn  logger# msg# e#)
               :error (.error logger# msg# e#)
               :fatal (.fatal logger# msg# e#)
               (throw (IllegalArgumentException. (str level#))))))
         (reify impl/LoggerFactory
           (name [_#]
             "org.apache.commons.logging")
           (get-logger [_# logger-ns#]
             (org.apache.commons.logging.LogFactory/getLog (str logger-ns#))))))
    (catch Exception e nil)))
