;; Copyright (c) Alex Taggart. All rights reserved.  The use
;; and distribution terms for this software are covered by the Eclipse
;; Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;; which can be found in the file epl-v10.html at the root of this
;; distribution.  By using this software in any fashion, you are
;; agreeing to be bound by the terms of this license.  You must not
;; remove this notice, or any other, from this software.

(ns clojure.tools.logging.java-util-logging
  (:require [clojure.tools.logging.impl :as impl]))

(defn load-factory
  "Returns a java.util.logging-based implementation of the LoggerFactory protocol,
  or nil if not available. End-users should not need to call this."
  []
  (try
    (Class/forName "java.util.logging.Logger")
    (eval
      '(let [levels# {:trace java.util.logging.Level/FINEST
                      :debug java.util.logging.Level/FINE
                      :info  java.util.logging.Level/INFO
                      :warn  java.util.logging.Level/WARNING
                      :error java.util.logging.Level/SEVERE
                      :fatal java.util.logging.Level/SEVERE}]
         (extend-type java.util.logging.Logger
           impl/Logger
           (enabled? [logger# level#]
             (.isLoggable logger#
               (or
                 (levels# level#)
                 (throw (IllegalArgumentException. (str level#))))))
           (write! [logger# level# ^Throwable e# marker# msg#]
             (let [^java.util.logging.Level level#
                   (or
                     (levels# level#)
                     (throw (IllegalArgumentException. (str level#))))
                   ^String msg# (str msg#)]
               (if e#
                 (.log logger# level# msg# e#)
                 (.log logger# level# msg#)))))
         (reify impl/LoggerFactory
           (name [_#]
             "java.util.logging")
           (get-logger [_# logger-ns#]
             (java.util.logging.Logger/getLogger (str logger-ns#))))))
    (catch Exception e nil)))

