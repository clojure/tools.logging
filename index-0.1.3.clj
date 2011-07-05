{:namespaces
 ({:source-url
   "https://github.com/clojure/tools.logging/blob/687c1833c59ad1204c2a41cf0ed1f53be4a0fa8d/src/main/clojure/clojure/tools/logging.clj",
   :wiki-url
   "http://clojure.github.com/tools.logging/logging-api.html",
   :name "clojure.tools.logging",
   :author "Alex Taggart",
   :doc
   "Logging macros which delegate to a specific logging implementation. At\nruntime a specific implementation is selected from, in order, Apache\ncommons-logging, slf4j, log4j, and finally java.util.logging."}),
 :vars
 ({:name "*force*",
   :namespace "clojure.tools.logging",
   :source-url
   "https://github.com/clojure/tools.logging/blob/687c1833c59ad1204c2a41cf0ed1f53be4a0fa8d/src/main/clojure/clojure/tools/logging.clj#L54",
   :dynamic true,
   :raw-source-url
   "https://github.com/clojure/tools.logging/raw/687c1833c59ad1204c2a41cf0ed1f53be4a0fa8d/src/main/clojure/clojure/tools/logging.clj",
   :wiki-url
   "http://clojure.github.com/tools.logging//logging-api.html#clojure.tools.logging/*force*",
   :doc
   "Overrides the default rules for choosing between logging directly or via an\nagent. Defaults to nil. See log* for details.",
   :var-type "var",
   :line 54,
   :file
   "/home/tom/src/clj/autodoc/../autodoc-work-area/tools.logging/src/src/main/clojure/clojure/tools/logging.clj"}
  {:name "*log-factory*",
   :namespace "clojure.tools.logging",
   :source-url
   "https://github.com/clojure/tools.logging/blob/687c1833c59ad1204c2a41cf0ed1f53be4a0fa8d/src/main/clojure/clojure/tools/logging.clj#L448",
   :dynamic true,
   :raw-source-url
   "https://github.com/clojure/tools.logging/raw/687c1833c59ad1204c2a41cf0ed1f53be4a0fa8d/src/main/clojure/clojure/tools/logging.clj",
   :wiki-url
   "http://clojure.github.com/tools.logging//logging-api.html#clojure.tools.logging/*log-factory*",
   :doc
   "An instance satisfying the LogFactory protocol. Used internally when needing\nto obtain an instance satisfying the Log protocol. Defaults to the value\nreturned from find-factory. Can be rebound to provide alternate logging\nimplementations",
   :var-type "var",
   :line 448,
   :file
   "/home/tom/src/clj/autodoc/../autodoc-work-area/tools.logging/src/src/main/clojure/clojure/tools/logging.clj"}
  {:name "*logging-agent*",
   :namespace "clojure.tools.logging",
   :source-url
   "https://github.com/clojure/tools.logging/blob/687c1833c59ad1204c2a41cf0ed1f53be4a0fa8d/src/main/clojure/clojure/tools/logging.clj#L44",
   :dynamic true,
   :raw-source-url
   "https://github.com/clojure/tools.logging/raw/687c1833c59ad1204c2a41cf0ed1f53be4a0fa8d/src/main/clojure/clojure/tools/logging.clj",
   :wiki-url
   "http://clojure.github.com/tools.logging//logging-api.html#clojure.tools.logging/*logging-agent*",
   :doc
   "The default agent used for performing logging when direct logging is\ndisabled. See log* for details.",
   :var-type "var",
   :line 44,
   :file
   "/home/tom/src/clj/autodoc/../autodoc-work-area/tools.logging/src/src/main/clojure/clojure/tools/logging.clj"}
  {:name "*tx-agent-levels*",
   :namespace "clojure.tools.logging",
   :source-url
   "https://github.com/clojure/tools.logging/blob/687c1833c59ad1204c2a41cf0ed1f53be4a0fa8d/src/main/clojure/clojure/tools/logging.clj#L49",
   :dynamic true,
   :raw-source-url
   "https://github.com/clojure/tools.logging/raw/687c1833c59ad1204c2a41cf0ed1f53be4a0fa8d/src/main/clojure/clojure/tools/logging.clj",
   :wiki-url
   "http://clojure.github.com/tools.logging//logging-api.html#clojure.tools.logging/*tx-agent-levels*",
   :doc
   "The set of levels that will require using an agent when logging from within a\nrunning transaction. Defaults to #{:info :warn}. See log* for details.",
   :var-type "var",
   :line 49,
   :file
   "/home/tom/src/clj/autodoc/../autodoc-work-area/tools.logging/src/src/main/clojure/clojure/tools/logging.clj"}
  {:raw-source-url
   "https://github.com/clojure/tools.logging/raw/687c1833c59ad1204c2a41cf0ed1f53be4a0fa8d/src/main/clojure/clojure/tools/logging.clj",
   :source-url
   "https://github.com/clojure/tools.logging/blob/687c1833c59ad1204c2a41cf0ed1f53be4a0fa8d/src/main/clojure/clojure/tools/logging.clj#L22",
   :wiki-url
   "http://clojure.github.com/tools.logging//logging-api.html#clojure.tools.logging/Log",
   :namespace "clojure.tools.logging",
   :line 22,
   :file
   "/home/tom/src/clj/autodoc/../autodoc-work-area/tools.logging/src/src/main/clojure/clojure/tools/logging.clj",
   :var-type "var",
   :doc
   "The protocol through which macros will interact with an underlying logging\nimplementation.  Implementations should at least support the six specified\nlogging levels if they wish to benefit from the level-specific macros.",
   :name "Log"}
  {:raw-source-url
   "https://github.com/clojure/tools.logging/raw/687c1833c59ad1204c2a41cf0ed1f53be4a0fa8d/src/main/clojure/clojure/tools/logging.clj",
   :source-url
   "https://github.com/clojure/tools.logging/blob/687c1833c59ad1204c2a41cf0ed1f53be4a0fa8d/src/main/clojure/clojure/tools/logging.clj#L33",
   :wiki-url
   "http://clojure.github.com/tools.logging//logging-api.html#clojure.tools.logging/LogFactory",
   :namespace "clojure.tools.logging",
   :line 33,
   :file
   "/home/tom/src/clj/autodoc/../autodoc-work-area/tools.logging/src/src/main/clojure/clojure/tools/logging.clj",
   :var-type "var",
   :doc
   "The protocol through which macros will obtain an instance satisfying Log as\nwell as providing information about the particular implementation being used.\nImplementations should be bound to *log-factory* in order to be picked up by\nthis library.",
   :name "LogFactory"}
  {:arglists ([]),
   :name "commons-logging",
   :namespace "clojure.tools.logging",
   :source-url
   "https://github.com/clojure/tools.logging/blob/687c1833c59ad1204c2a41cf0ed1f53be4a0fa8d/src/main/clojure/clojure/tools/logging.clj#L294",
   :raw-source-url
   "https://github.com/clojure/tools.logging/raw/687c1833c59ad1204c2a41cf0ed1f53be4a0fa8d/src/main/clojure/clojure/tools/logging.clj",
   :wiki-url
   "http://clojure.github.com/tools.logging//logging-api.html#clojure.tools.logging/commons-logging",
   :doc
   "Returns a commons-logging-based implementation of the LogFactory protocol, or\nnil if not available. End-users should not need to call this.",
   :var-type "function",
   :line 294,
   :file
   "/home/tom/src/clj/autodoc/../autodoc-work-area/tools.logging/src/src/main/clojure/clojure/tools/logging.clj"}
  {:arglists ([message & more] [throwable message & more]),
   :name "debug",
   :namespace "clojure.tools.logging",
   :source-url
   "https://github.com/clojure/tools.logging/blob/687c1833c59ad1204c2a41cf0ed1f53be4a0fa8d/src/main/clojure/clojure/tools/logging.clj#L225",
   :raw-source-url
   "https://github.com/clojure/tools.logging/raw/687c1833c59ad1204c2a41cf0ed1f53be4a0fa8d/src/main/clojure/clojure/tools/logging.clj",
   :wiki-url
   "http://clojure.github.com/tools.logging//logging-api.html#clojure.tools.logging/debug",
   :doc "Debug level logging using print-style args.",
   :var-type "macro",
   :line 225,
   :file
   "/home/tom/src/clj/autodoc/../autodoc-work-area/tools.logging/src/src/main/clojure/clojure/tools/logging.clj"}
  {:arglists ([fmt & fmt-args] [throwable fmt & fmt-args]),
   :name "debugf",
   :namespace "clojure.tools.logging",
   :source-url
   "https://github.com/clojure/tools.logging/blob/687c1833c59ad1204c2a41cf0ed1f53be4a0fa8d/src/main/clojure/clojure/tools/logging.clj#L261",
   :raw-source-url
   "https://github.com/clojure/tools.logging/raw/687c1833c59ad1204c2a41cf0ed1f53be4a0fa8d/src/main/clojure/clojure/tools/logging.clj",
   :wiki-url
   "http://clojure.github.com/tools.logging//logging-api.html#clojure.tools.logging/debugf",
   :doc "Debug level logging using format.",
   :var-type "macro",
   :line 261,
   :file
   "/home/tom/src/clj/autodoc/../autodoc-work-area/tools.logging/src/src/main/clojure/clojure/tools/logging.clj"}
  {:arglists ([level] [level log-ns]),
   :name "enabled?",
   :namespace "clojure.tools.logging",
   :source-url
   "https://github.com/clojure/tools.logging/blob/687c1833c59ad1204c2a41cf0ed1f53be4a0fa8d/src/main/clojure/clojure/tools/logging.clj#L124",
   :raw-source-url
   "https://github.com/clojure/tools.logging/raw/687c1833c59ad1204c2a41cf0ed1f53be4a0fa8d/src/main/clojure/clojure/tools/logging.clj",
   :wiki-url
   "http://clojure.github.com/tools.logging//logging-api.html#clojure.tools.logging/enabled?",
   :doc
   "Returns true if the specific logging level is enabled.  Use of this function\nshould only be necessary if one needs to execute alternate code paths beyond\nwhether the log should be written to.",
   :var-type "macro",
   :line 124,
   :file
   "/home/tom/src/clj/autodoc/../autodoc-work-area/tools.logging/src/src/main/clojure/clojure/tools/logging.clj"}
  {:arglists ([message & more] [throwable message & more]),
   :name "error",
   :namespace "clojure.tools.logging",
   :source-url
   "https://github.com/clojure/tools.logging/blob/687c1833c59ad1204c2a41cf0ed1f53be4a0fa8d/src/main/clojure/clojure/tools/logging.clj#L243",
   :raw-source-url
   "https://github.com/clojure/tools.logging/raw/687c1833c59ad1204c2a41cf0ed1f53be4a0fa8d/src/main/clojure/clojure/tools/logging.clj",
   :wiki-url
   "http://clojure.github.com/tools.logging//logging-api.html#clojure.tools.logging/error",
   :doc "Error level logging using print-style args.",
   :var-type "macro",
   :line 243,
   :file
   "/home/tom/src/clj/autodoc/../autodoc-work-area/tools.logging/src/src/main/clojure/clojure/tools/logging.clj"}
  {:arglists ([fmt & fmt-args] [throwable fmt & fmt-args]),
   :name "errorf",
   :namespace "clojure.tools.logging",
   :source-url
   "https://github.com/clojure/tools.logging/blob/687c1833c59ad1204c2a41cf0ed1f53be4a0fa8d/src/main/clojure/clojure/tools/logging.clj#L279",
   :raw-source-url
   "https://github.com/clojure/tools.logging/raw/687c1833c59ad1204c2a41cf0ed1f53be4a0fa8d/src/main/clojure/clojure/tools/logging.clj",
   :wiki-url
   "http://clojure.github.com/tools.logging//logging-api.html#clojure.tools.logging/errorf",
   :doc "Error level logging using format.",
   :var-type "macro",
   :line 279,
   :file
   "/home/tom/src/clj/autodoc/../autodoc-work-area/tools.logging/src/src/main/clojure/clojure/tools/logging.clj"}
  {:arglists ([message & more] [throwable message & more]),
   :name "fatal",
   :namespace "clojure.tools.logging",
   :source-url
   "https://github.com/clojure/tools.logging/blob/687c1833c59ad1204c2a41cf0ed1f53be4a0fa8d/src/main/clojure/clojure/tools/logging.clj#L249",
   :raw-source-url
   "https://github.com/clojure/tools.logging/raw/687c1833c59ad1204c2a41cf0ed1f53be4a0fa8d/src/main/clojure/clojure/tools/logging.clj",
   :wiki-url
   "http://clojure.github.com/tools.logging//logging-api.html#clojure.tools.logging/fatal",
   :doc "Fatal level logging using print-style args.",
   :var-type "macro",
   :line 249,
   :file
   "/home/tom/src/clj/autodoc/../autodoc-work-area/tools.logging/src/src/main/clojure/clojure/tools/logging.clj"}
  {:arglists ([fmt & fmt-args] [throwable fmt & fmt-args]),
   :name "fatalf",
   :namespace "clojure.tools.logging",
   :source-url
   "https://github.com/clojure/tools.logging/blob/687c1833c59ad1204c2a41cf0ed1f53be4a0fa8d/src/main/clojure/clojure/tools/logging.clj#L285",
   :raw-source-url
   "https://github.com/clojure/tools.logging/raw/687c1833c59ad1204c2a41cf0ed1f53be4a0fa8d/src/main/clojure/clojure/tools/logging.clj",
   :wiki-url
   "http://clojure.github.com/tools.logging//logging-api.html#clojure.tools.logging/fatalf",
   :doc "Fatal level logging using format.",
   :var-type "macro",
   :line 285,
   :file
   "/home/tom/src/clj/autodoc/../autodoc-work-area/tools.logging/src/src/main/clojure/clojure/tools/logging.clj"}
  {:arglists ([]),
   :name "find-factory",
   :namespace "clojure.tools.logging",
   :source-url
   "https://github.com/clojure/tools.logging/blob/687c1833c59ad1204c2a41cf0ed1f53be4a0fa8d/src/main/clojure/clojure/tools/logging.clj#L435",
   :raw-source-url
   "https://github.com/clojure/tools.logging/raw/687c1833c59ad1204c2a41cf0ed1f53be4a0fa8d/src/main/clojure/clojure/tools/logging.clj",
   :wiki-url
   "http://clojure.github.com/tools.logging//logging-api.html#clojure.tools.logging/find-factory",
   :doc
   "Returns the first LogFactory found that is available from commons-logging,\nslf4j-logging, log4j-logging, or java-util-logging. End-users should not need\nto call this.",
   :var-type "function",
   :line 435,
   :file
   "/home/tom/src/clj/autodoc/../autodoc-work-area/tools.logging/src/src/main/clojure/clojure/tools/logging.clj"}
  {:raw-source-url nil,
   :source-url nil,
   :wiki-url
   "http://clojure.github.com/tools.logging//logging-api.html#clojure.tools.logging/impl-enabled?",
   :namespace "clojure.tools.logging",
   :var-type "function",
   :arglists ([log level]),
   :doc
   "Implementation-specific check if a particular level is enabled. End-users\nshould not need to call this.",
   :name "impl-enabled?"}
  {:raw-source-url nil,
   :source-url nil,
   :wiki-url
   "http://clojure.github.com/tools.logging//logging-api.html#clojure.tools.logging/impl-get-log",
   :namespace "clojure.tools.logging",
   :var-type "function",
   :arglists ([factory log-ns]),
   :doc
   "Returns an implementation-specific Log by namespace. End-users should not\nneed to call this.",
   :name "impl-get-log"}
  {:raw-source-url nil,
   :source-url nil,
   :wiki-url
   "http://clojure.github.com/tools.logging//logging-api.html#clojure.tools.logging/impl-name",
   :namespace "clojure.tools.logging",
   :var-type "function",
   :arglists ([factory]),
   :doc "Returns some text identifying the underlying implementation.",
   :name "impl-name"}
  {:raw-source-url nil,
   :source-url nil,
   :wiki-url
   "http://clojure.github.com/tools.logging//logging-api.html#clojure.tools.logging/impl-write!",
   :namespace "clojure.tools.logging",
   :var-type "function",
   :arglists ([log level throwable message]),
   :doc
   "Implementation-specific write of a log message. End-users should not need\nto call this.",
   :name "impl-write!"}
  {:arglists ([message & more] [throwable message & more]),
   :name "info",
   :namespace "clojure.tools.logging",
   :source-url
   "https://github.com/clojure/tools.logging/blob/687c1833c59ad1204c2a41cf0ed1f53be4a0fa8d/src/main/clojure/clojure/tools/logging.clj#L231",
   :raw-source-url
   "https://github.com/clojure/tools.logging/raw/687c1833c59ad1204c2a41cf0ed1f53be4a0fa8d/src/main/clojure/clojure/tools/logging.clj",
   :wiki-url
   "http://clojure.github.com/tools.logging//logging-api.html#clojure.tools.logging/info",
   :doc "Info level logging using print-style args.",
   :var-type "macro",
   :line 231,
   :file
   "/home/tom/src/clj/autodoc/../autodoc-work-area/tools.logging/src/src/main/clojure/clojure/tools/logging.clj"}
  {:arglists ([fmt & fmt-args] [throwable fmt & fmt-args]),
   :name "infof",
   :namespace "clojure.tools.logging",
   :source-url
   "https://github.com/clojure/tools.logging/blob/687c1833c59ad1204c2a41cf0ed1f53be4a0fa8d/src/main/clojure/clojure/tools/logging.clj#L267",
   :raw-source-url
   "https://github.com/clojure/tools.logging/raw/687c1833c59ad1204c2a41cf0ed1f53be4a0fa8d/src/main/clojure/clojure/tools/logging.clj",
   :wiki-url
   "http://clojure.github.com/tools.logging//logging-api.html#clojure.tools.logging/infof",
   :doc "Info level logging using format.",
   :var-type "macro",
   :line 267,
   :file
   "/home/tom/src/clj/autodoc/../autodoc-work-area/tools.logging/src/src/main/clojure/clojure/tools/logging.clj"}
  {:arglists ([]),
   :name "java-util-logging",
   :namespace "clojure.tools.logging",
   :source-url
   "https://github.com/clojure/tools.logging/blob/687c1833c59ad1204c2a41cf0ed1f53be4a0fa8d/src/main/clojure/clojure/tools/logging.clj#L399",
   :raw-source-url
   "https://github.com/clojure/tools.logging/raw/687c1833c59ad1204c2a41cf0ed1f53be4a0fa8d/src/main/clojure/clojure/tools/logging.clj",
   :wiki-url
   "http://clojure.github.com/tools.logging//logging-api.html#clojure.tools.logging/java-util-logging",
   :doc
   "Returns a java.util.logging-based implementation of the LogFactory protocol,\nor nil if not available. End-users should not need to call this.",
   :var-type "function",
   :line 399,
   :file
   "/home/tom/src/clj/autodoc/../autodoc-work-area/tools.logging/src/src/main/clojure/clojure/tools/logging.clj"}
  {:arglists
   ([level message]
    [level throwable message]
    [log-ns level throwable message]
    [log-factory log-ns level throwable message]),
   :name "log",
   :namespace "clojure.tools.logging",
   :source-url
   "https://github.com/clojure/tools.logging/blob/687c1833c59ad1204c2a41cf0ed1f53be4a0fa8d/src/main/clojure/clojure/tools/logging.clj#L84",
   :raw-source-url
   "https://github.com/clojure/tools.logging/raw/687c1833c59ad1204c2a41cf0ed1f53be4a0fa8d/src/main/clojure/clojure/tools/logging.clj",
   :wiki-url
   "http://clojure.github.com/tools.logging//logging-api.html#clojure.tools.logging/log",
   :doc
   "Evaluates and logs a message only if the specified level is enabled. See log*\nfor more details.",
   :var-type "macro",
   :line 84,
   :file
   "/home/tom/src/clj/autodoc/../autodoc-work-area/tools.logging/src/src/main/clojure/clojure/tools/logging.clj"}
  {:arglists ([log level throwable message]),
   :name "log*",
   :namespace "clojure.tools.logging",
   :source-url
   "https://github.com/clojure/tools.logging/blob/687c1833c59ad1204c2a41cf0ed1f53be4a0fa8d/src/main/clojure/clojure/tools/logging.clj#L59",
   :raw-source-url
   "https://github.com/clojure/tools.logging/raw/687c1833c59ad1204c2a41cf0ed1f53be4a0fa8d/src/main/clojure/clojure/tools/logging.clj",
   :wiki-url
   "http://clojure.github.com/tools.logging//logging-api.html#clojure.tools.logging/log*",
   :doc
   "Attempts to log a message, either directly or via an agent; does not check if\nthe level is enabled.\n\nFor performance reasons, an agent will only be used when invoked within a\nrunning transaction, and only for logging levels specified by\n*tx-agent-levels*. This allows those entries to only be written once the\ntransaction commits, and are discarded if it is retried or aborted.  As\ncorollary, other levels (e.g., :debug, :error) will be written even from\nfailed transactions though at the cost of repeat messages during retries.\n\nOne can override the above by setting *force* to :direct or :agent; all\nsubsequent writes will be direct or via an agent, respectively.",
   :var-type "function",
   :line 59,
   :file
   "/home/tom/src/clj/autodoc/../autodoc-work-area/tools.logging/src/src/main/clojure/clojure/tools/logging.clj"}
  {:arglists ([log-ns] [log-ns out-level err-level]),
   :name "log-capture!",
   :namespace "clojure.tools.logging",
   :source-url
   "https://github.com/clojure/tools.logging/blob/687c1833c59ad1204c2a41cf0ed1f53be4a0fa8d/src/main/clojure/clojure/tools/logging.clj#L167",
   :raw-source-url
   "https://github.com/clojure/tools.logging/raw/687c1833c59ad1204c2a41cf0ed1f53be4a0fa8d/src/main/clojure/clojure/tools/logging.clj",
   :wiki-url
   "http://clojure.github.com/tools.logging//logging-api.html#clojure.tools.logging/log-capture!",
   :doc
   "Captures System.out and System.err, piping all writes of those streams to\nthe log. If unspecified, levels default to :info and :error, respectively.\nThe specified log-ns value will be used to namespace all log entries.\n\nNote: use with-logs to redirect output of *out* or *err*.\n\nWarning: if the logging implementation is configured to output to System.out\n(as is the default with java.util.logging) then using this function will\nresult in StackOverflowException when writing to the log.",
   :var-type "function",
   :line 167,
   :file
   "/home/tom/src/clj/autodoc/../autodoc-work-area/tools.logging/src/src/main/clojure/clojure/tools/logging.clj"}
  {:arglists ([level log-ns]),
   :name "log-stream",
   :namespace "clojure.tools.logging",
   :source-url
   "https://github.com/clojure/tools.logging/blob/687c1833c59ad1204c2a41cf0ed1f53be4a0fa8d/src/main/clojure/clojure/tools/logging.clj#L149",
   :raw-source-url
   "https://github.com/clojure/tools.logging/raw/687c1833c59ad1204c2a41cf0ed1f53be4a0fa8d/src/main/clojure/clojure/tools/logging.clj",
   :wiki-url
   "http://clojure.github.com/tools.logging//logging-api.html#clojure.tools.logging/log-stream",
   :doc
   "Creates a PrintStream that will output to the log at the specified level.",
   :var-type "function",
   :line 149,
   :file
   "/home/tom/src/clj/autodoc/../autodoc-work-area/tools.logging/src/src/main/clojure/clojure/tools/logging.clj"}
  {:arglists ([]),
   :name "log-uncapture!",
   :namespace "clojure.tools.logging",
   :source-url
   "https://github.com/clojure/tools.logging/blob/687c1833c59ad1204c2a41cf0ed1f53be4a0fa8d/src/main/clojure/clojure/tools/logging.clj#L187",
   :raw-source-url
   "https://github.com/clojure/tools.logging/raw/687c1833c59ad1204c2a41cf0ed1f53be4a0fa8d/src/main/clojure/clojure/tools/logging.clj",
   :wiki-url
   "http://clojure.github.com/tools.logging//logging-api.html#clojure.tools.logging/log-uncapture!",
   :doc "Restores System.out and System.err to their original values.",
   :var-type "function",
   :line 187,
   :file
   "/home/tom/src/clj/autodoc/../autodoc-work-area/tools.logging/src/src/main/clojure/clojure/tools/logging.clj"}
  {:arglists ([]),
   :name "log4j-logging",
   :namespace "clojure.tools.logging",
   :source-url
   "https://github.com/clojure/tools.logging/blob/687c1833c59ad1204c2a41cf0ed1f53be4a0fa8d/src/main/clojure/clojure/tools/logging.clj#L365",
   :raw-source-url
   "https://github.com/clojure/tools.logging/raw/687c1833c59ad1204c2a41cf0ed1f53be4a0fa8d/src/main/clojure/clojure/tools/logging.clj",
   :wiki-url
   "http://clojure.github.com/tools.logging//logging-api.html#clojure.tools.logging/log4j-logging",
   :doc
   "Returns a log4j-based implementation of the LogFactory protocol, or nil if\nnot available. End-users should not need to call this.",
   :var-type "function",
   :line 365,
   :file
   "/home/tom/src/clj/autodoc/../autodoc-work-area/tools.logging/src/src/main/clojure/clojure/tools/logging.clj"}
  {:arglists ([level fmt & fmt-args] [level throwable fmt & fmt-args]),
   :name "logf",
   :namespace "clojure.tools.logging",
   :source-url
   "https://github.com/clojure/tools.logging/blob/687c1833c59ad1204c2a41cf0ed1f53be4a0fa8d/src/main/clojure/clojure/tools/logging.clj#L111",
   :raw-source-url
   "https://github.com/clojure/tools.logging/raw/687c1833c59ad1204c2a41cf0ed1f53be4a0fa8d/src/main/clojure/clojure/tools/logging.clj",
   :wiki-url
   "http://clojure.github.com/tools.logging//logging-api.html#clojure.tools.logging/logf",
   :doc
   "Logs a message using a format string and args. Can optionally take a\nthrowable as its second arg. See level-specific macros, e.g., debugf.",
   :var-type "macro",
   :line 111,
   :file
   "/home/tom/src/clj/autodoc/../autodoc-work-area/tools.logging/src/src/main/clojure/clojure/tools/logging.clj"}
  {:arglists ([level message & more] [level throwable message & more]),
   :name "logp",
   :namespace "clojure.tools.logging",
   :source-url
   "https://github.com/clojure/tools.logging/blob/687c1833c59ad1204c2a41cf0ed1f53be4a0fa8d/src/main/clojure/clojure/tools/logging.clj#L98",
   :raw-source-url
   "https://github.com/clojure/tools.logging/raw/687c1833c59ad1204c2a41cf0ed1f53be4a0fa8d/src/main/clojure/clojure/tools/logging.clj",
   :wiki-url
   "http://clojure.github.com/tools.logging//logging-api.html#clojure.tools.logging/logp",
   :doc
   "Logs a message using print style args. Can optionally take a throwable as its\nsecond arg. See level-specific macros, e.g., debug.",
   :var-type "macro",
   :line 98,
   :file
   "/home/tom/src/clj/autodoc/../autodoc-work-area/tools.logging/src/src/main/clojure/clojure/tools/logging.clj"}
  {:arglists ([]),
   :name "slf4j-logging",
   :namespace "clojure.tools.logging",
   :source-url
   "https://github.com/clojure/tools.logging/blob/687c1833c59ad1204c2a41cf0ed1f53be4a0fa8d/src/main/clojure/clojure/tools/logging.clj#L329",
   :raw-source-url
   "https://github.com/clojure/tools.logging/raw/687c1833c59ad1204c2a41cf0ed1f53be4a0fa8d/src/main/clojure/clojure/tools/logging.clj",
   :wiki-url
   "http://clojure.github.com/tools.logging//logging-api.html#clojure.tools.logging/slf4j-logging",
   :doc
   "Returns a SLF4J-based implementation of the LogFactory protocol, or nil if\nnot available. End-users should not need to call this.",
   :var-type "function",
   :line 329,
   :file
   "/home/tom/src/clj/autodoc/../autodoc-work-area/tools.logging/src/src/main/clojure/clojure/tools/logging.clj"}
  {:arglists ([expr] [level expr]),
   :name "spy",
   :namespace "clojure.tools.logging",
   :source-url
   "https://github.com/clojure/tools.logging/blob/687c1833c59ad1204c2a41cf0ed1f53be4a0fa8d/src/main/clojure/clojure/tools/logging.clj#L133",
   :raw-source-url
   "https://github.com/clojure/tools.logging/raw/687c1833c59ad1204c2a41cf0ed1f53be4a0fa8d/src/main/clojure/clojure/tools/logging.clj",
   :wiki-url
   "http://clojure.github.com/tools.logging//logging-api.html#clojure.tools.logging/spy",
   :doc
   "Evaluates expr and writes the form and its result to the log. Returns the\nresult of expr. Defaults to debug log level.",
   :var-type "macro",
   :line 133,
   :file
   "/home/tom/src/clj/autodoc/../autodoc-work-area/tools.logging/src/src/main/clojure/clojure/tools/logging.clj"}
  {:arglists ([message & more] [throwable message & more]),
   :name "trace",
   :namespace "clojure.tools.logging",
   :source-url
   "https://github.com/clojure/tools.logging/blob/687c1833c59ad1204c2a41cf0ed1f53be4a0fa8d/src/main/clojure/clojure/tools/logging.clj#L219",
   :raw-source-url
   "https://github.com/clojure/tools.logging/raw/687c1833c59ad1204c2a41cf0ed1f53be4a0fa8d/src/main/clojure/clojure/tools/logging.clj",
   :wiki-url
   "http://clojure.github.com/tools.logging//logging-api.html#clojure.tools.logging/trace",
   :doc "Trace level logging using print-style args.",
   :var-type "macro",
   :line 219,
   :file
   "/home/tom/src/clj/autodoc/../autodoc-work-area/tools.logging/src/src/main/clojure/clojure/tools/logging.clj"}
  {:arglists ([fmt & fmt-args] [throwable fmt & fmt-args]),
   :name "tracef",
   :namespace "clojure.tools.logging",
   :source-url
   "https://github.com/clojure/tools.logging/blob/687c1833c59ad1204c2a41cf0ed1f53be4a0fa8d/src/main/clojure/clojure/tools/logging.clj#L255",
   :raw-source-url
   "https://github.com/clojure/tools.logging/raw/687c1833c59ad1204c2a41cf0ed1f53be4a0fa8d/src/main/clojure/clojure/tools/logging.clj",
   :wiki-url
   "http://clojure.github.com/tools.logging//logging-api.html#clojure.tools.logging/tracef",
   :doc "Trace level logging using format.",
   :var-type "macro",
   :line 255,
   :file
   "/home/tom/src/clj/autodoc/../autodoc-work-area/tools.logging/src/src/main/clojure/clojure/tools/logging.clj"}
  {:arglists ([message & more] [throwable message & more]),
   :name "warn",
   :namespace "clojure.tools.logging",
   :source-url
   "https://github.com/clojure/tools.logging/blob/687c1833c59ad1204c2a41cf0ed1f53be4a0fa8d/src/main/clojure/clojure/tools/logging.clj#L237",
   :raw-source-url
   "https://github.com/clojure/tools.logging/raw/687c1833c59ad1204c2a41cf0ed1f53be4a0fa8d/src/main/clojure/clojure/tools/logging.clj",
   :wiki-url
   "http://clojure.github.com/tools.logging//logging-api.html#clojure.tools.logging/warn",
   :doc "Warn level logging using print-style args.",
   :var-type "macro",
   :line 237,
   :file
   "/home/tom/src/clj/autodoc/../autodoc-work-area/tools.logging/src/src/main/clojure/clojure/tools/logging.clj"}
  {:arglists ([fmt & fmt-args] [throwable fmt & fmt-args]),
   :name "warnf",
   :namespace "clojure.tools.logging",
   :source-url
   "https://github.com/clojure/tools.logging/blob/687c1833c59ad1204c2a41cf0ed1f53be4a0fa8d/src/main/clojure/clojure/tools/logging.clj#L273",
   :raw-source-url
   "https://github.com/clojure/tools.logging/raw/687c1833c59ad1204c2a41cf0ed1f53be4a0fa8d/src/main/clojure/clojure/tools/logging.clj",
   :wiki-url
   "http://clojure.github.com/tools.logging//logging-api.html#clojure.tools.logging/warnf",
   :doc "Warn level logging using format.",
   :var-type "macro",
   :line 273,
   :file
   "/home/tom/src/clj/autodoc/../autodoc-work-area/tools.logging/src/src/main/clojure/clojure/tools/logging.clj"}
  {:arglists ([log-ns & body] [[log-ns out-level err-level] & body]),
   :name "with-logs",
   :namespace "clojure.tools.logging",
   :source-url
   "https://github.com/clojure/tools.logging/blob/687c1833c59ad1204c2a41cf0ed1f53be4a0fa8d/src/main/clojure/clojure/tools/logging.clj#L196",
   :raw-source-url
   "https://github.com/clojure/tools.logging/raw/687c1833c59ad1204c2a41cf0ed1f53be4a0fa8d/src/main/clojure/clojure/tools/logging.clj",
   :wiki-url
   "http://clojure.github.com/tools.logging//logging-api.html#clojure.tools.logging/with-logs",
   :doc
   "Evaluates exprs in a context in which *out* and *err* write to the log. The\nspecified log-ns value will be used to namespace all log entries.\n\nBy default *out* and *err* write to :info and :error, respectively.",
   :var-type "macro",
   :line 196,
   :file
   "/home/tom/src/clj/autodoc/../autodoc-work-area/tools.logging/src/src/main/clojure/clojure/tools/logging.clj"})}
