;; Copyright (c) Alex Taggart. All rights reserved.  The use
;; and distribution terms for this software are covered by the Eclipse
;; Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;; which can be found in the file epl-v10.html at the root of this
;; distribution.  By using this software in any fashion, you are
;; agreeing to be bound by the terms of this license.  You must not
;; remove this notice, or any other, from this software.

(ns ^{:author "Alex Taggart"
      :doc "Protocols used to allow access to logging implementations.
            End-users should not need to use this namespace."}
     clojure.tools.logging.impl
  (:refer-clojure :exclude [name]))

(defprotocol Logger
  "The protocol through which macros will interact with an underlying logging
  implementation.  Implementations should at least support the six standard
  logging levels if they wish to work from the level-specific macros."
  (enabled? [logger level]
    "Check if a particular level is enabled for the given Logger.")
  (write! [logger level throwable message]
    "Writes a log message to the given Logger."))

(defprotocol LoggerFactory
  "The protocol through which macros will obtain an instance satisfying Logger as
  well as providing information about the particular implementation being used.
  Implementations should be bound to *logger-factory* in order to be picked up by
  this library."
  (name [factory]
    "Returns some text identifying the underlying implementation.")
  (get-logger [factory logger-ns]
    "Returns an implementation-specific Logger by namespace."))
