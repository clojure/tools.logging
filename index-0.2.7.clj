{:namespaces
 ({:source-url
   "https://github.com/clojure/tools.logging/blob/0d701fda6eb4945389783a0c24d5884d41e5d8b5/src/main/clojure/clojure/tools/logging.clj",
   :wiki-url
   "http://clojure.github.com/tools.logging/clojure.tools.logging-api.html",
   :name "clojure.tools.logging",
   :author "Alex Taggart",
   :doc
   "Logging macros which delegate to a specific logging implementation. At\n runtime a specific implementation is selected from, in order, slf4j,\n Apache commons-logging, log4j, and finally java.util.logging.\n\nThe logging implementation can be expliticly determined by using \nbinding or alter-var-root to change the value of *logger-factory* to\nanother implementation of clojure.tools.logging.impl/LoggerFactory."}
  {:source-url
   "https://github.com/clojure/tools.logging/blob/26d5ebb12cf8b029644161faad9af2e5412efe94/src/main/clojure/clojure/tools/logging/impl.clj",
   :wiki-url
   "http://clojure.github.com/tools.logging/clojure.tools.logging.impl-api.html",
   :name "clojure.tools.logging.impl",
   :author "Alex Taggart",
   :doc
   "Protocols used to allow access to logging implementations.\nThis namespace only need be used by those providing logging\nimplementations to be consumed by the core api."}),
 :vars
 ({:name "*force*",
   :namespace "clojure.tools.logging",
   :source-url
   "https://github.com/clojure/tools.logging/blob/0d701fda6eb4945389783a0c24d5884d41e5d8b5/src/main/clojure/clojure/tools/logging.clj#L37",
   :dynamic true,
   :raw-source-url
   "https://github.com/clojure/tools.logging/raw/0d701fda6eb4945389783a0c24d5884d41e5d8b5/src/main/clojure/clojure/tools/logging.clj",
   :wiki-url
   "http://clojure.github.com/tools.logging//clojure.tools.logging-api.html#clojure.tools.logging/*force*",
   :doc
   "Overrides the default rules for choosing between logging directly or via an\nagent. Defaults to nil. See log* for details.",
   :var-type "var",
   :line 37,
   :file "src/main/clojure/clojure/tools/logging.clj"}
  {:name "*logger-factory*",
   :namespace "clojure.tools.logging",
   :source-url
   "https://github.com/clojure/tools.logging/blob/0d701fda6eb4945389783a0c24d5884d41e5d8b5/src/main/clojure/clojure/tools/logging.clj#L287",
   :dynamic true,
   :raw-source-url
   "https://github.com/clojure/tools.logging/raw/0d701fda6eb4945389783a0c24d5884d41e5d8b5/src/main/clojure/clojure/tools/logging.clj",
   :wiki-url
   "http://clojure.github.com/tools.logging//clojure.tools.logging-api.html#clojure.tools.logging/*logger-factory*",
   :doc
   "An instance satisfying the impl/LoggerFactory protocol. Used internally to\nobtain an impl/Logger. Defaults to the value returned from impl/find-factory.",
   :var-type "var",
   :line 287,
   :file "src/main/clojure/clojure/tools/logging.clj"}
  {:name "*logging-agent*",
   :namespace "clojure.tools.logging",
   :source-url
   "https://github.com/clojure/tools.logging/blob/0d701fda6eb4945389783a0c24d5884d41e5d8b5/src/main/clojure/clojure/tools/logging.clj#L27",
   :dynamic true,
   :raw-source-url
   "https://github.com/clojure/tools.logging/raw/0d701fda6eb4945389783a0c24d5884d41e5d8b5/src/main/clojure/clojure/tools/logging.clj",
   :wiki-url
   "http://clojure.github.com/tools.logging//clojure.tools.logging-api.html#clojure.tools.logging/*logging-agent*",
   :doc
   "The default agent used for performing logging when direct logging is\ndisabled. See log* for details.",
   :var-type "var",
   :line 27,
   :file "src/main/clojure/clojure/tools/logging.clj"}
  {:name "*tx-agent-levels*",
   :namespace "clojure.tools.logging",
   :source-url
   "https://github.com/clojure/tools.logging/blob/0d701fda6eb4945389783a0c24d5884d41e5d8b5/src/main/clojure/clojure/tools/logging.clj#L32",
   :dynamic true,
   :raw-source-url
   "https://github.com/clojure/tools.logging/raw/0d701fda6eb4945389783a0c24d5884d41e5d8b5/src/main/clojure/clojure/tools/logging.clj",
   :wiki-url
   "http://clojure.github.com/tools.logging//clojure.tools.logging-api.html#clojure.tools.logging/*tx-agent-levels*",
   :doc
   "The set of levels that will require using an agent when logging from within a\nrunning transaction. Defaults to #{:info :warn}. See log* for details.",
   :var-type "var",
   :line 32,
   :file "src/main/clojure/clojure/tools/logging.clj"}
  {:arglists ([message & more] [throwable message & more]),
   :name "debug",
   :namespace "clojure.tools.logging",
   :source-url
   "https://github.com/clojure/tools.logging/blob/0d701fda6eb4945389783a0c24d5884d41e5d8b5/src/main/clojure/clojure/tools/logging.clj#L221",
   :raw-source-url
   "https://github.com/clojure/tools.logging/raw/0d701fda6eb4945389783a0c24d5884d41e5d8b5/src/main/clojure/clojure/tools/logging.clj",
   :wiki-url
   "http://clojure.github.com/tools.logging//clojure.tools.logging-api.html#clojure.tools.logging/debug",
   :doc "Debug level logging using print-style args.",
   :var-type "macro",
   :line 221,
   :file "src/main/clojure/clojure/tools/logging.clj"}
  {:arglists ([fmt & fmt-args] [throwable fmt & fmt-args]),
   :name "debugf",
   :namespace "clojure.tools.logging",
   :source-url
   "https://github.com/clojure/tools.logging/blob/0d701fda6eb4945389783a0c24d5884d41e5d8b5/src/main/clojure/clojure/tools/logging.clj#L257",
   :raw-source-url
   "https://github.com/clojure/tools.logging/raw/0d701fda6eb4945389783a0c24d5884d41e5d8b5/src/main/clojure/clojure/tools/logging.clj",
   :wiki-url
   "http://clojure.github.com/tools.logging//clojure.tools.logging-api.html#clojure.tools.logging/debugf",
   :doc "Debug level logging using format.",
   :var-type "macro",
   :line 257,
   :file "src/main/clojure/clojure/tools/logging.clj"}
  {:arglists ([level] [level logger-ns]),
   :name "enabled?",
   :namespace "clojure.tools.logging",
   :source-url
   "https://github.com/clojure/tools.logging/blob/0d701fda6eb4945389783a0c24d5884d41e5d8b5/src/main/clojure/clojure/tools/logging.clj#L110",
   :raw-source-url
   "https://github.com/clojure/tools.logging/raw/0d701fda6eb4945389783a0c24d5884d41e5d8b5/src/main/clojure/clojure/tools/logging.clj",
   :wiki-url
   "http://clojure.github.com/tools.logging//clojure.tools.logging-api.html#clojure.tools.logging/enabled?",
   :doc
   "Returns true if the specific logging level is enabled.  Use of this macro\nshould only be necessary if one needs to execute alternate code paths beyond\nwhether the log should be written to.",
   :var-type "macro",
   :line 110,
   :file "src/main/clojure/clojure/tools/logging.clj"}
  {:arglists ([message & more] [throwable message & more]),
   :name "error",
   :namespace "clojure.tools.logging",
   :source-url
   "https://github.com/clojure/tools.logging/blob/0d701fda6eb4945389783a0c24d5884d41e5d8b5/src/main/clojure/clojure/tools/logging.clj#L239",
   :raw-source-url
   "https://github.com/clojure/tools.logging/raw/0d701fda6eb4945389783a0c24d5884d41e5d8b5/src/main/clojure/clojure/tools/logging.clj",
   :wiki-url
   "http://clojure.github.com/tools.logging//clojure.tools.logging-api.html#clojure.tools.logging/error",
   :doc "Error level logging using print-style args.",
   :var-type "macro",
   :line 239,
   :file "src/main/clojure/clojure/tools/logging.clj"}
  {:arglists ([fmt & fmt-args] [throwable fmt & fmt-args]),
   :name "errorf",
   :namespace "clojure.tools.logging",
   :source-url
   "https://github.com/clojure/tools.logging/blob/0d701fda6eb4945389783a0c24d5884d41e5d8b5/src/main/clojure/clojure/tools/logging.clj#L275",
   :raw-source-url
   "https://github.com/clojure/tools.logging/raw/0d701fda6eb4945389783a0c24d5884d41e5d8b5/src/main/clojure/clojure/tools/logging.clj",
   :wiki-url
   "http://clojure.github.com/tools.logging//clojure.tools.logging-api.html#clojure.tools.logging/errorf",
   :doc "Error level logging using format.",
   :var-type "macro",
   :line 275,
   :file "src/main/clojure/clojure/tools/logging.clj"}
  {:arglists ([message & more] [throwable message & more]),
   :name "fatal",
   :namespace "clojure.tools.logging",
   :source-url
   "https://github.com/clojure/tools.logging/blob/0d701fda6eb4945389783a0c24d5884d41e5d8b5/src/main/clojure/clojure/tools/logging.clj#L245",
   :raw-source-url
   "https://github.com/clojure/tools.logging/raw/0d701fda6eb4945389783a0c24d5884d41e5d8b5/src/main/clojure/clojure/tools/logging.clj",
   :wiki-url
   "http://clojure.github.com/tools.logging//clojure.tools.logging-api.html#clojure.tools.logging/fatal",
   :doc "Fatal level logging using print-style args.",
   :var-type "macro",
   :line 245,
   :file "src/main/clojure/clojure/tools/logging.clj"}
  {:arglists ([fmt & fmt-args] [throwable fmt & fmt-args]),
   :name "fatalf",
   :namespace "clojure.tools.logging",
   :source-url
   "https://github.com/clojure/tools.logging/blob/0d701fda6eb4945389783a0c24d5884d41e5d8b5/src/main/clojure/clojure/tools/logging.clj#L281",
   :raw-source-url
   "https://github.com/clojure/tools.logging/raw/0d701fda6eb4945389783a0c24d5884d41e5d8b5/src/main/clojure/clojure/tools/logging.clj",
   :wiki-url
   "http://clojure.github.com/tools.logging//clojure.tools.logging-api.html#clojure.tools.logging/fatalf",
   :doc "Fatal level logging using format.",
   :var-type "macro",
   :line 281,
   :file "src/main/clojure/clojure/tools/logging.clj"}
  {:arglists ([message & more] [throwable message & more]),
   :name "info",
   :namespace "clojure.tools.logging",
   :source-url
   "https://github.com/clojure/tools.logging/blob/0d701fda6eb4945389783a0c24d5884d41e5d8b5/src/main/clojure/clojure/tools/logging.clj#L227",
   :raw-source-url
   "https://github.com/clojure/tools.logging/raw/0d701fda6eb4945389783a0c24d5884d41e5d8b5/src/main/clojure/clojure/tools/logging.clj",
   :wiki-url
   "http://clojure.github.com/tools.logging//clojure.tools.logging-api.html#clojure.tools.logging/info",
   :doc "Info level logging using print-style args.",
   :var-type "macro",
   :line 227,
   :file "src/main/clojure/clojure/tools/logging.clj"}
  {:arglists ([fmt & fmt-args] [throwable fmt & fmt-args]),
   :name "infof",
   :namespace "clojure.tools.logging",
   :source-url
   "https://github.com/clojure/tools.logging/blob/0d701fda6eb4945389783a0c24d5884d41e5d8b5/src/main/clojure/clojure/tools/logging.clj#L263",
   :raw-source-url
   "https://github.com/clojure/tools.logging/raw/0d701fda6eb4945389783a0c24d5884d41e5d8b5/src/main/clojure/clojure/tools/logging.clj",
   :wiki-url
   "http://clojure.github.com/tools.logging//clojure.tools.logging-api.html#clojure.tools.logging/infof",
   :doc "Info level logging using format.",
   :var-type "macro",
   :line 263,
   :file "src/main/clojure/clojure/tools/logging.clj"}
  {:arglists
   ([level message]
    [level throwable message]
    [logger-ns level throwable message]
    [logger-factory logger-ns level throwable message]),
   :name "log",
   :namespace "clojure.tools.logging",
   :source-url
   "https://github.com/clojure/tools.logging/blob/0d701fda6eb4945389783a0c24d5884d41e5d8b5/src/main/clojure/clojure/tools/logging.clj#L68",
   :raw-source-url
   "https://github.com/clojure/tools.logging/raw/0d701fda6eb4945389783a0c24d5884d41e5d8b5/src/main/clojure/clojure/tools/logging.clj",
   :wiki-url
   "http://clojure.github.com/tools.logging//clojure.tools.logging-api.html#clojure.tools.logging/log",
   :doc
   "Evaluates and logs a message only if the specified level is enabled. See log*\nfor more details.",
   :var-type "macro",
   :line 68,
   :file "src/main/clojure/clojure/tools/logging.clj"}
  {:arglists ([logger level throwable message]),
   :name "log*",
   :namespace "clojure.tools.logging",
   :source-url
   "https://github.com/clojure/tools.logging/blob/0d701fda6eb4945389783a0c24d5884d41e5d8b5/src/main/clojure/clojure/tools/logging.clj#L42",
   :raw-source-url
   "https://github.com/clojure/tools.logging/raw/0d701fda6eb4945389783a0c24d5884d41e5d8b5/src/main/clojure/clojure/tools/logging.clj",
   :wiki-url
   "http://clojure.github.com/tools.logging//clojure.tools.logging-api.html#clojure.tools.logging/log*",
   :doc
   "Attempts to log a message, either directly or via an agent; does not check if\nthe level is enabled.\n\nFor performance reasons, an agent will only be used when invoked within a\nrunning transaction, and only for logging levels specified by\n*tx-agent-levels*. This allows those entries to only be written once the\ntransaction commits, and are discarded if it is retried or aborted.  As\ncorollary, other levels (e.g., :debug, :error) will be written even from\nfailed transactions though at the cost of repeat messages during retries.\n\nOne can override the above by setting *force* to :direct or :agent; all\nsubsequent writes will be direct or via an agent, respectively.",
   :var-type "function",
   :line 42,
   :file "src/main/clojure/clojure/tools/logging.clj"}
  {:arglists ([logger-ns] [logger-ns out-level err-level]),
   :name "log-capture!",
   :namespace "clojure.tools.logging",
   :source-url
   "https://github.com/clojure/tools.logging/blob/0d701fda6eb4945389783a0c24d5884d41e5d8b5/src/main/clojure/clojure/tools/logging.clj#L163",
   :raw-source-url
   "https://github.com/clojure/tools.logging/raw/0d701fda6eb4945389783a0c24d5884d41e5d8b5/src/main/clojure/clojure/tools/logging.clj",
   :wiki-url
   "http://clojure.github.com/tools.logging//clojure.tools.logging-api.html#clojure.tools.logging/log-capture!",
   :doc
   "Captures System.out and System.err, piping all writes of those streams to\nthe log. If unspecified, levels default to :info and :error, respectively.\nThe specified logger-ns value will be used to namespace all log entries.\n\nNote: use with-logs to redirect output of *out* or *err*.\n\nWarning: if the logging implementation is configured to output to System.out\n(as is the default with java.util.logging) then using this function will\nresult in StackOverflowException when writing to the log.",
   :var-type "function",
   :line 163,
   :file "src/main/clojure/clojure/tools/logging.clj"}
  {:arglists ([level logger-ns]),
   :name "log-stream",
   :namespace "clojure.tools.logging",
   :source-url
   "https://github.com/clojure/tools.logging/blob/0d701fda6eb4945389783a0c24d5884d41e5d8b5/src/main/clojure/clojure/tools/logging.clj#L145",
   :raw-source-url
   "https://github.com/clojure/tools.logging/raw/0d701fda6eb4945389783a0c24d5884d41e5d8b5/src/main/clojure/clojure/tools/logging.clj",
   :wiki-url
   "http://clojure.github.com/tools.logging//clojure.tools.logging-api.html#clojure.tools.logging/log-stream",
   :doc
   "Creates a PrintStream that will output to the log at the specified level.",
   :var-type "function",
   :line 145,
   :file "src/main/clojure/clojure/tools/logging.clj"}
  {:arglists ([]),
   :name "log-uncapture!",
   :namespace "clojure.tools.logging",
   :source-url
   "https://github.com/clojure/tools.logging/blob/0d701fda6eb4945389783a0c24d5884d41e5d8b5/src/main/clojure/clojure/tools/logging.clj#L183",
   :raw-source-url
   "https://github.com/clojure/tools.logging/raw/0d701fda6eb4945389783a0c24d5884d41e5d8b5/src/main/clojure/clojure/tools/logging.clj",
   :wiki-url
   "http://clojure.github.com/tools.logging//clojure.tools.logging-api.html#clojure.tools.logging/log-uncapture!",
   :doc "Restores System.out and System.err to their original values.",
   :var-type "function",
   :line 183,
   :file "src/main/clojure/clojure/tools/logging.clj"}
  {:arglists ([level fmt & fmt-args] [level throwable fmt & fmt-args]),
   :name "logf",
   :namespace "clojure.tools.logging",
   :source-url
   "https://github.com/clojure/tools.logging/blob/0d701fda6eb4945389783a0c24d5884d41e5d8b5/src/main/clojure/clojure/tools/logging.clj#L96",
   :raw-source-url
   "https://github.com/clojure/tools.logging/raw/0d701fda6eb4945389783a0c24d5884d41e5d8b5/src/main/clojure/clojure/tools/logging.clj",
   :wiki-url
   "http://clojure.github.com/tools.logging//clojure.tools.logging-api.html#clojure.tools.logging/logf",
   :doc
   "Logs a message using a format string and args. Can optionally take a\nthrowable as its second arg. See level-specific macros, e.g., debugf.",
   :var-type "macro",
   :line 96,
   :file "src/main/clojure/clojure/tools/logging.clj"}
  {:arglists ([level message & more] [level throwable message & more]),
   :name "logp",
   :namespace "clojure.tools.logging",
   :source-url
   "https://github.com/clojure/tools.logging/blob/0d701fda6eb4945389783a0c24d5884d41e5d8b5/src/main/clojure/clojure/tools/logging.clj#L82",
   :raw-source-url
   "https://github.com/clojure/tools.logging/raw/0d701fda6eb4945389783a0c24d5884d41e5d8b5/src/main/clojure/clojure/tools/logging.clj",
   :wiki-url
   "http://clojure.github.com/tools.logging//clojure.tools.logging-api.html#clojure.tools.logging/logp",
   :doc
   "Logs a message using print style args. Can optionally take a throwable as its\nsecond arg. See level-specific macros, e.g., debug.",
   :var-type "macro",
   :line 82,
   :file "src/main/clojure/clojure/tools/logging.clj"}
  {:arglists ([expr] [level expr]),
   :name "spy",
   :namespace "clojure.tools.logging",
   :source-url
   "https://github.com/clojure/tools.logging/blob/0d701fda6eb4945389783a0c24d5884d41e5d8b5/src/main/clojure/clojure/tools/logging.clj#L119",
   :raw-source-url
   "https://github.com/clojure/tools.logging/raw/0d701fda6eb4945389783a0c24d5884d41e5d8b5/src/main/clojure/clojure/tools/logging.clj",
   :wiki-url
   "http://clojure.github.com/tools.logging//clojure.tools.logging-api.html#clojure.tools.logging/spy",
   :doc
   "Evaluates expr and may write the form and its result to the log. Returns the\nresult of expr. Defaults to :debug log level.",
   :var-type "macro",
   :line 119,
   :file "src/main/clojure/clojure/tools/logging.clj"}
  {:arglists ([fmt expr] [level fmt expr]),
   :name "spyf",
   :namespace "clojure.tools.logging",
   :source-url
   "https://github.com/clojure/tools.logging/blob/0d701fda6eb4945389783a0c24d5884d41e5d8b5/src/main/clojure/clojure/tools/logging.clj#L135",
   :raw-source-url
   "https://github.com/clojure/tools.logging/raw/0d701fda6eb4945389783a0c24d5884d41e5d8b5/src/main/clojure/clojure/tools/logging.clj",
   :wiki-url
   "http://clojure.github.com/tools.logging//clojure.tools.logging-api.html#clojure.tools.logging/spyf",
   :doc
   "Evaluates expr and may write (format fmt result) to the log. Returns the\nresult of expr. Defaults to :debug log level.",
   :var-type "macro",
   :line 135,
   :file "src/main/clojure/clojure/tools/logging.clj"}
  {:arglists ([message & more] [throwable message & more]),
   :name "trace",
   :namespace "clojure.tools.logging",
   :source-url
   "https://github.com/clojure/tools.logging/blob/0d701fda6eb4945389783a0c24d5884d41e5d8b5/src/main/clojure/clojure/tools/logging.clj#L215",
   :raw-source-url
   "https://github.com/clojure/tools.logging/raw/0d701fda6eb4945389783a0c24d5884d41e5d8b5/src/main/clojure/clojure/tools/logging.clj",
   :wiki-url
   "http://clojure.github.com/tools.logging//clojure.tools.logging-api.html#clojure.tools.logging/trace",
   :doc "Trace level logging using print-style args.",
   :var-type "macro",
   :line 215,
   :file "src/main/clojure/clojure/tools/logging.clj"}
  {:arglists ([fmt & fmt-args] [throwable fmt & fmt-args]),
   :name "tracef",
   :namespace "clojure.tools.logging",
   :source-url
   "https://github.com/clojure/tools.logging/blob/0d701fda6eb4945389783a0c24d5884d41e5d8b5/src/main/clojure/clojure/tools/logging.clj#L251",
   :raw-source-url
   "https://github.com/clojure/tools.logging/raw/0d701fda6eb4945389783a0c24d5884d41e5d8b5/src/main/clojure/clojure/tools/logging.clj",
   :wiki-url
   "http://clojure.github.com/tools.logging//clojure.tools.logging-api.html#clojure.tools.logging/tracef",
   :doc "Trace level logging using format.",
   :var-type "macro",
   :line 251,
   :file "src/main/clojure/clojure/tools/logging.clj"}
  {:arglists ([message & more] [throwable message & more]),
   :name "warn",
   :namespace "clojure.tools.logging",
   :source-url
   "https://github.com/clojure/tools.logging/blob/0d701fda6eb4945389783a0c24d5884d41e5d8b5/src/main/clojure/clojure/tools/logging.clj#L233",
   :raw-source-url
   "https://github.com/clojure/tools.logging/raw/0d701fda6eb4945389783a0c24d5884d41e5d8b5/src/main/clojure/clojure/tools/logging.clj",
   :wiki-url
   "http://clojure.github.com/tools.logging//clojure.tools.logging-api.html#clojure.tools.logging/warn",
   :doc "Warn level logging using print-style args.",
   :var-type "macro",
   :line 233,
   :file "src/main/clojure/clojure/tools/logging.clj"}
  {:arglists ([fmt & fmt-args] [throwable fmt & fmt-args]),
   :name "warnf",
   :namespace "clojure.tools.logging",
   :source-url
   "https://github.com/clojure/tools.logging/blob/0d701fda6eb4945389783a0c24d5884d41e5d8b5/src/main/clojure/clojure/tools/logging.clj#L269",
   :raw-source-url
   "https://github.com/clojure/tools.logging/raw/0d701fda6eb4945389783a0c24d5884d41e5d8b5/src/main/clojure/clojure/tools/logging.clj",
   :wiki-url
   "http://clojure.github.com/tools.logging//clojure.tools.logging-api.html#clojure.tools.logging/warnf",
   :doc "Warn level logging using format.",
   :var-type "macro",
   :line 269,
   :file "src/main/clojure/clojure/tools/logging.clj"}
  {:arglists
   ([logger-ns & body] [[logger-ns out-level err-level] & body]),
   :name "with-logs",
   :namespace "clojure.tools.logging",
   :source-url
   "https://github.com/clojure/tools.logging/blob/0d701fda6eb4945389783a0c24d5884d41e5d8b5/src/main/clojure/clojure/tools/logging.clj#L192",
   :raw-source-url
   "https://github.com/clojure/tools.logging/raw/0d701fda6eb4945389783a0c24d5884d41e5d8b5/src/main/clojure/clojure/tools/logging.clj",
   :wiki-url
   "http://clojure.github.com/tools.logging//clojure.tools.logging-api.html#clojure.tools.logging/with-logs",
   :doc
   "Evaluates exprs in a context in which *out* and *err* write to the log. The\nspecified logger-ns value will be used to namespace all log entries.\n\nBy default *out* and *err* write to :info and :error, respectively.",
   :var-type "macro",
   :line 192,
   :file "src/main/clojure/clojure/tools/logging.clj"}
  {:arglists ([]),
   :name "cl-factory",
   :namespace "clojure.tools.logging.impl",
   :source-url
   "https://github.com/clojure/tools.logging/blob/26d5ebb12cf8b029644161faad9af2e5412efe94/src/main/clojure/clojure/tools/logging/impl.clj#L94",
   :raw-source-url
   "https://github.com/clojure/tools.logging/raw/26d5ebb12cf8b029644161faad9af2e5412efe94/src/main/clojure/clojure/tools/logging/impl.clj",
   :wiki-url
   "http://clojure.github.com/tools.logging//clojure.tools.logging-api.html#clojure.tools.logging.impl/cl-factory",
   :doc
   "Returns a Commons Logging-based implementation of the LoggerFactory protocol, or\nnil if not available.",
   :var-type "function",
   :line 94,
   :file "src/main/clojure/clojure/tools/logging/impl.clj"}
  {:file "src/main/clojure/clojure/tools/logging/impl.clj",
   :raw-source-url
   "https://github.com/clojure/tools.logging/raw/26d5ebb12cf8b029644161faad9af2e5412efe94/src/main/clojure/clojure/tools/logging/impl.clj",
   :source-url
   "https://github.com/clojure/tools.logging/blob/26d5ebb12cf8b029644161faad9af2e5412efe94/src/main/clojure/clojure/tools/logging/impl.clj#L35",
   :wiki-url
   "http://clojure.github.com/tools.logging//clojure.tools.logging-api.html#clojure.tools.logging.impl/disabled-logger",
   :namespace "clojure.tools.logging.impl",
   :line 35,
   :var-type "var",
   :doc "A Logger that is not enabled and does nothing on write.",
   :name "disabled-logger"}
  {:file "src/main/clojure/clojure/tools/logging/impl.clj",
   :raw-source-url
   "https://github.com/clojure/tools.logging/raw/26d5ebb12cf8b029644161faad9af2e5412efe94/src/main/clojure/clojure/tools/logging/impl.clj",
   :source-url
   "https://github.com/clojure/tools.logging/blob/26d5ebb12cf8b029644161faad9af2e5412efe94/src/main/clojure/clojure/tools/logging/impl.clj#L41",
   :wiki-url
   "http://clojure.github.com/tools.logging//clojure.tools.logging-api.html#clojure.tools.logging.impl/disabled-logger-factory",
   :namespace "clojure.tools.logging.impl",
   :line 41,
   :var-type "var",
   :doc "A LoggerFactory that always provides the disabled-logger.",
   :name "disabled-logger-factory"}
  {:arglists ([]),
   :name "find-factory",
   :namespace "clojure.tools.logging.impl",
   :source-url
   "https://github.com/clojure/tools.logging/blob/26d5ebb12cf8b029644161faad9af2e5412efe94/src/main/clojure/clojure/tools/logging/impl.clj#L214",
   :raw-source-url
   "https://github.com/clojure/tools.logging/raw/26d5ebb12cf8b029644161faad9af2e5412efe94/src/main/clojure/clojure/tools/logging/impl.clj",
   :wiki-url
   "http://clojure.github.com/tools.logging//clojure.tools.logging-api.html#clojure.tools.logging.impl/find-factory",
   :doc
   "Returns the first non-nil value from slf4j-factory, cl-factory,\nlog4j-factory, and jul-factory.",
   :var-type "function",
   :line 214,
   :file "src/main/clojure/clojure/tools/logging/impl.clj"}
  {:arglists ([]),
   :name "jul-factory",
   :namespace "clojure.tools.logging.impl",
   :source-url
   "https://github.com/clojure/tools.logging/blob/26d5ebb12cf8b029644161faad9af2e5412efe94/src/main/clojure/clojure/tools/logging/impl.clj#L176",
   :raw-source-url
   "https://github.com/clojure/tools.logging/raw/26d5ebb12cf8b029644161faad9af2e5412efe94/src/main/clojure/clojure/tools/logging/impl.clj",
   :wiki-url
   "http://clojure.github.com/tools.logging//clojure.tools.logging-api.html#clojure.tools.logging.impl/jul-factory",
   :doc
   "Returns a java.util.logging-based implementation of the LoggerFactory protocol,\nor nil if not available.",
   :var-type "function",
   :line 176,
   :file "src/main/clojure/clojure/tools/logging/impl.clj"}
  {:arglists ([]),
   :name "log4j-factory",
   :namespace "clojure.tools.logging.impl",
   :source-url
   "https://github.com/clojure/tools.logging/blob/26d5ebb12cf8b029644161faad9af2e5412efe94/src/main/clojure/clojure/tools/logging/impl.clj#L140",
   :raw-source-url
   "https://github.com/clojure/tools.logging/raw/26d5ebb12cf8b029644161faad9af2e5412efe94/src/main/clojure/clojure/tools/logging/impl.clj",
   :wiki-url
   "http://clojure.github.com/tools.logging//clojure.tools.logging-api.html#clojure.tools.logging.impl/log4j-factory",
   :doc
   "Returns a Log4j-based implementation of the LoggerFactory protocol, or nil if\nnot available.",
   :var-type "function",
   :line 140,
   :file "src/main/clojure/clojure/tools/logging/impl.clj"}
  {:arglists ([]),
   :name "slf4j-factory",
   :namespace "clojure.tools.logging.impl",
   :source-url
   "https://github.com/clojure/tools.logging/blob/26d5ebb12cf8b029644161faad9af2e5412efe94/src/main/clojure/clojure/tools/logging/impl.clj#L47",
   :raw-source-url
   "https://github.com/clojure/tools.logging/raw/26d5ebb12cf8b029644161faad9af2e5412efe94/src/main/clojure/clojure/tools/logging/impl.clj",
   :wiki-url
   "http://clojure.github.com/tools.logging//clojure.tools.logging-api.html#clojure.tools.logging.impl/slf4j-factory",
   :doc
   "Returns a SLF4J-based implementation of the LoggerFactory protocol, or nil if\nnot available.",
   :var-type "function",
   :line 47,
   :file "src/main/clojure/clojure/tools/logging/impl.clj"}
  {:file "src/main/clojure/clojure/tools/logging/impl.clj",
   :raw-source-url
   "https://github.com/clojure/tools.logging/raw/26d5ebb12cf8b029644161faad9af2e5412efe94/src/main/clojure/clojure/tools/logging/impl.clj",
   :source-url
   "https://github.com/clojure/tools.logging/blob/26d5ebb12cf8b029644161faad9af2e5412efe94/src/main/clojure/clojure/tools/logging/impl.clj#L16",
   :wiki-url
   "http://clojure.github.com/tools.logging//clojure.tools.logging-api.html#clojure.tools.logging.impl/Logger",
   :namespace "clojure.tools.logging.impl",
   :line 16,
   :var-type "protocol",
   :doc
   "The protocol through which the core api will interact with an underlying logging\nimplementation.  Implementations should at least support the six standard\nlogging levels if they wish to work from the level-specific macros.",
   :name "Logger"}
  {:file "src/main/clojure/clojure/tools/logging/impl.clj",
   :raw-source-url
   "https://github.com/clojure/tools.logging/raw/26d5ebb12cf8b029644161faad9af2e5412efe94/src/main/clojure/clojure/tools/logging/impl.clj",
   :source-url
   "https://github.com/clojure/tools.logging/blob/26d5ebb12cf8b029644161faad9af2e5412efe94/src/main/clojure/clojure/tools/logging/impl.clj#L25",
   :wiki-url
   "http://clojure.github.com/tools.logging//clojure.tools.logging-api.html#clojure.tools.logging.impl/LoggerFactory",
   :namespace "clojure.tools.logging.impl",
   :line 25,
   :var-type "protocol",
   :doc
   "The protocol through which the core api will obtain an instance satisfying Logger\nas well as providing information about the particular implementation being used.\nImplementations should be bound to *logger-factory* in order to be picked up by\nthis library.",
   :name "LoggerFactory"}
  {:file nil,
   :raw-source-url nil,
   :source-url nil,
   :wiki-url
   "http://clojure.github.com/tools.logging//clojure.tools.logging-api.html#clojure.tools.logging.impl/enabled?",
   :namespace "clojure.tools.logging.impl",
   :var-type "function",
   :arglists ([logger level]),
   :doc "Check if a particular level is enabled for the given Logger.",
   :name "enabled?"}
  {:file nil,
   :raw-source-url nil,
   :source-url nil,
   :wiki-url
   "http://clojure.github.com/tools.logging//clojure.tools.logging-api.html#clojure.tools.logging.impl/write!",
   :namespace "clojure.tools.logging.impl",
   :var-type "function",
   :arglists ([logger level throwable message]),
   :doc "Writes a log message to the given Logger.",
   :name "write!"}
  {:file nil,
   :raw-source-url nil,
   :source-url nil,
   :wiki-url
   "http://clojure.github.com/tools.logging//clojure.tools.logging-api.html#clojure.tools.logging.impl/get-logger",
   :namespace "clojure.tools.logging.impl",
   :var-type "function",
   :arglists ([factory logger-ns]),
   :doc "Returns an implementation-specific Logger by namespace.",
   :name "get-logger"}
  {:file nil,
   :raw-source-url nil,
   :source-url nil,
   :wiki-url
   "http://clojure.github.com/tools.logging//clojure.tools.logging-api.html#clojure.tools.logging.impl/name",
   :namespace "clojure.tools.logging.impl",
   :var-type "function",
   :arglists ([factory]),
   :doc "Returns some text identifying the underlying implementation.",
   :name "name"})}
