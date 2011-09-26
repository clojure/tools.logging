{:namespaces
 ({:source-url
   "https://github.com/clojure/tools.logging/blob/ec33ac5f794e996add825a8b60eb49720ad384bb/src/main/clojure/clojure/tools/logging.clj",
   :wiki-url
   "http://clojure.github.com/tools.logging/logging-api.html",
   :name "clojure.tools.logging",
   :author "Alex Taggart",
   :doc
   "Logging macros which delegate to a specific logging implementation. At\nruntime a specific implementation is selected from, in order, slf4j,\nApache commons-logging, slf4j, log4j, and finally java.util.logging."}
  {:source-url
   "https://github.com/clojure/tools.logging/blob/42dfba84a373e52a4e80af6467e47761e6f20678/src/main/clojure/clojure/tools/logging/commons_logging.clj",
   :wiki-url
   "http://clojure.github.com/tools.logging/logging.commons-logging-api.html",
   :name "clojure.tools.logging.commons-logging",
   :doc nil}
  {:source-url
   "https://github.com/clojure/tools.logging/blob/42dfba84a373e52a4e80af6467e47761e6f20678/src/main/clojure/clojure/tools/logging/impl.clj",
   :wiki-url
   "http://clojure.github.com/tools.logging/logging.impl-api.html",
   :name "clojure.tools.logging.impl",
   :author "Alex Taggart",
   :doc
   "Protocols used to allow access to logging implementations.\nEnd-users should not need to use this namespace."}
  {:source-url
   "https://github.com/clojure/tools.logging/blob/42dfba84a373e52a4e80af6467e47761e6f20678/src/main/clojure/clojure/tools/logging/java_util_logging.clj",
   :wiki-url
   "http://clojure.github.com/tools.logging/logging.java-util-logging-api.html",
   :name "clojure.tools.logging.java-util-logging",
   :doc nil}
  {:source-url
   "https://github.com/clojure/tools.logging/blob/42dfba84a373e52a4e80af6467e47761e6f20678/src/main/clojure/clojure/tools/logging/log4j.clj",
   :wiki-url
   "http://clojure.github.com/tools.logging/logging.log4j-api.html",
   :name "clojure.tools.logging.log4j",
   :doc nil}
  {:source-url
   "https://github.com/clojure/tools.logging/blob/42dfba84a373e52a4e80af6467e47761e6f20678/src/main/clojure/clojure/tools/logging/slf4j.clj",
   :wiki-url
   "http://clojure.github.com/tools.logging/logging.slf4j-api.html",
   :name "clojure.tools.logging.slf4j",
   :doc nil}),
 :vars
 ({:name "*force*",
   :namespace "clojure.tools.logging",
   :source-url
   "https://github.com/clojure/tools.logging/blob/ec33ac5f794e996add825a8b60eb49720ad384bb/src/main/clojure/clojure/tools/logging.clj#L38",
   :dynamic true,
   :raw-source-url
   "https://github.com/clojure/tools.logging/raw/ec33ac5f794e996add825a8b60eb49720ad384bb/src/main/clojure/clojure/tools/logging.clj",
   :wiki-url
   "http://clojure.github.com/tools.logging//logging-api.html#clojure.tools.logging/*force*",
   :doc
   "Overrides the default rules for choosing between logging directly or via an\nagent. Defaults to nil. See log* for details.",
   :var-type "var",
   :line 38,
   :file
   "/home/tom/src/clj/autodoc/../autodoc-work-area/tools.logging/src/src/main/clojure/clojure/tools/logging.clj"}
  {:name "*logger-factory*",
   :namespace "clojure.tools.logging",
   :source-url
   "https://github.com/clojure/tools.logging/blob/ec33ac5f794e996add825a8b60eb49720ad384bb/src/main/clojure/clojure/tools/logging.clj#L284",
   :dynamic true,
   :raw-source-url
   "https://github.com/clojure/tools.logging/raw/ec33ac5f794e996add825a8b60eb49720ad384bb/src/main/clojure/clojure/tools/logging.clj",
   :wiki-url
   "http://clojure.github.com/tools.logging//logging-api.html#clojure.tools.logging/*logger-factory*",
   :doc
   "An instance satisfying the LoggerFactory protocol. Used internally when\nneeding to obtain an instance satisfying the Logger protocol. Defaults to the\nfirst LoggerFactory found that is available from slf4j-logging,\ncommons-logging, log4j-logging, or java-util-logging. Can be rebound to provide\nalternate logging implementations",
   :var-type "var",
   :line 284,
   :file
   "/home/tom/src/clj/autodoc/../autodoc-work-area/tools.logging/src/src/main/clojure/clojure/tools/logging.clj"}
  {:name "*logging-agent*",
   :namespace "clojure.tools.logging",
   :source-url
   "https://github.com/clojure/tools.logging/blob/ec33ac5f794e996add825a8b60eb49720ad384bb/src/main/clojure/clojure/tools/logging.clj#L28",
   :dynamic true,
   :raw-source-url
   "https://github.com/clojure/tools.logging/raw/ec33ac5f794e996add825a8b60eb49720ad384bb/src/main/clojure/clojure/tools/logging.clj",
   :wiki-url
   "http://clojure.github.com/tools.logging//logging-api.html#clojure.tools.logging/*logging-agent*",
   :doc
   "The default agent used for performing logging when direct logging is\ndisabled. See log* for details.",
   :var-type "var",
   :line 28,
   :file
   "/home/tom/src/clj/autodoc/../autodoc-work-area/tools.logging/src/src/main/clojure/clojure/tools/logging.clj"}
  {:name "*tx-agent-levels*",
   :namespace "clojure.tools.logging",
   :source-url
   "https://github.com/clojure/tools.logging/blob/ec33ac5f794e996add825a8b60eb49720ad384bb/src/main/clojure/clojure/tools/logging.clj#L33",
   :dynamic true,
   :raw-source-url
   "https://github.com/clojure/tools.logging/raw/ec33ac5f794e996add825a8b60eb49720ad384bb/src/main/clojure/clojure/tools/logging.clj",
   :wiki-url
   "http://clojure.github.com/tools.logging//logging-api.html#clojure.tools.logging/*tx-agent-levels*",
   :doc
   "The set of levels that will require using an agent when logging from within a\nrunning transaction. Defaults to #{:info :warn}. See log* for details.",
   :var-type "var",
   :line 33,
   :file
   "/home/tom/src/clj/autodoc/../autodoc-work-area/tools.logging/src/src/main/clojure/clojure/tools/logging.clj"}
  {:arglists ([message & more] [throwable message & more]),
   :name "debug",
   :namespace "clojure.tools.logging",
   :source-url
   "https://github.com/clojure/tools.logging/blob/ec33ac5f794e996add825a8b60eb49720ad384bb/src/main/clojure/clojure/tools/logging.clj#L209",
   :raw-source-url
   "https://github.com/clojure/tools.logging/raw/ec33ac5f794e996add825a8b60eb49720ad384bb/src/main/clojure/clojure/tools/logging.clj",
   :wiki-url
   "http://clojure.github.com/tools.logging//logging-api.html#clojure.tools.logging/debug",
   :doc "Debug level logging using print-style args.",
   :var-type "macro",
   :line 209,
   :file
   "/home/tom/src/clj/autodoc/../autodoc-work-area/tools.logging/src/src/main/clojure/clojure/tools/logging.clj"}
  {:arglists ([fmt & fmt-args] [throwable fmt & fmt-args]),
   :name "debugf",
   :namespace "clojure.tools.logging",
   :source-url
   "https://github.com/clojure/tools.logging/blob/ec33ac5f794e996add825a8b60eb49720ad384bb/src/main/clojure/clojure/tools/logging.clj#L245",
   :raw-source-url
   "https://github.com/clojure/tools.logging/raw/ec33ac5f794e996add825a8b60eb49720ad384bb/src/main/clojure/clojure/tools/logging.clj",
   :wiki-url
   "http://clojure.github.com/tools.logging//logging-api.html#clojure.tools.logging/debugf",
   :doc "Debug level logging using format.",
   :var-type "macro",
   :line 245,
   :file
   "/home/tom/src/clj/autodoc/../autodoc-work-area/tools.logging/src/src/main/clojure/clojure/tools/logging.clj"}
  {:arglists ([level] [level logger-ns]),
   :name "enabled?",
   :namespace "clojure.tools.logging",
   :source-url
   "https://github.com/clojure/tools.logging/blob/ec33ac5f794e996add825a8b60eb49720ad384bb/src/main/clojure/clojure/tools/logging.clj#L108",
   :raw-source-url
   "https://github.com/clojure/tools.logging/raw/ec33ac5f794e996add825a8b60eb49720ad384bb/src/main/clojure/clojure/tools/logging.clj",
   :wiki-url
   "http://clojure.github.com/tools.logging//logging-api.html#clojure.tools.logging/enabled?",
   :doc
   "Returns true if the specific logging level is enabled.  Use of this macro\nshould only be necessary if one needs to execute alternate code paths beyond\nwhether the log should be written to.",
   :var-type "macro",
   :line 108,
   :file
   "/home/tom/src/clj/autodoc/../autodoc-work-area/tools.logging/src/src/main/clojure/clojure/tools/logging.clj"}
  {:arglists ([message & more] [throwable message & more]),
   :name "error",
   :namespace "clojure.tools.logging",
   :source-url
   "https://github.com/clojure/tools.logging/blob/ec33ac5f794e996add825a8b60eb49720ad384bb/src/main/clojure/clojure/tools/logging.clj#L227",
   :raw-source-url
   "https://github.com/clojure/tools.logging/raw/ec33ac5f794e996add825a8b60eb49720ad384bb/src/main/clojure/clojure/tools/logging.clj",
   :wiki-url
   "http://clojure.github.com/tools.logging//logging-api.html#clojure.tools.logging/error",
   :doc "Error level logging using print-style args.",
   :var-type "macro",
   :line 227,
   :file
   "/home/tom/src/clj/autodoc/../autodoc-work-area/tools.logging/src/src/main/clojure/clojure/tools/logging.clj"}
  {:arglists ([fmt & fmt-args] [throwable fmt & fmt-args]),
   :name "errorf",
   :namespace "clojure.tools.logging",
   :source-url
   "https://github.com/clojure/tools.logging/blob/ec33ac5f794e996add825a8b60eb49720ad384bb/src/main/clojure/clojure/tools/logging.clj#L263",
   :raw-source-url
   "https://github.com/clojure/tools.logging/raw/ec33ac5f794e996add825a8b60eb49720ad384bb/src/main/clojure/clojure/tools/logging.clj",
   :wiki-url
   "http://clojure.github.com/tools.logging//logging-api.html#clojure.tools.logging/errorf",
   :doc "Error level logging using format.",
   :var-type "macro",
   :line 263,
   :file
   "/home/tom/src/clj/autodoc/../autodoc-work-area/tools.logging/src/src/main/clojure/clojure/tools/logging.clj"}
  {:arglists ([message & more] [throwable message & more]),
   :name "fatal",
   :namespace "clojure.tools.logging",
   :source-url
   "https://github.com/clojure/tools.logging/blob/ec33ac5f794e996add825a8b60eb49720ad384bb/src/main/clojure/clojure/tools/logging.clj#L233",
   :raw-source-url
   "https://github.com/clojure/tools.logging/raw/ec33ac5f794e996add825a8b60eb49720ad384bb/src/main/clojure/clojure/tools/logging.clj",
   :wiki-url
   "http://clojure.github.com/tools.logging//logging-api.html#clojure.tools.logging/fatal",
   :doc "Fatal level logging using print-style args.",
   :var-type "macro",
   :line 233,
   :file
   "/home/tom/src/clj/autodoc/../autodoc-work-area/tools.logging/src/src/main/clojure/clojure/tools/logging.clj"}
  {:arglists ([fmt & fmt-args] [throwable fmt & fmt-args]),
   :name "fatalf",
   :namespace "clojure.tools.logging",
   :source-url
   "https://github.com/clojure/tools.logging/blob/ec33ac5f794e996add825a8b60eb49720ad384bb/src/main/clojure/clojure/tools/logging.clj#L269",
   :raw-source-url
   "https://github.com/clojure/tools.logging/raw/ec33ac5f794e996add825a8b60eb49720ad384bb/src/main/clojure/clojure/tools/logging.clj",
   :wiki-url
   "http://clojure.github.com/tools.logging//logging-api.html#clojure.tools.logging/fatalf",
   :doc "Fatal level logging using format.",
   :var-type "macro",
   :line 269,
   :file
   "/home/tom/src/clj/autodoc/../autodoc-work-area/tools.logging/src/src/main/clojure/clojure/tools/logging.clj"}
  {:arglists ([message & more] [throwable message & more]),
   :name "info",
   :namespace "clojure.tools.logging",
   :source-url
   "https://github.com/clojure/tools.logging/blob/ec33ac5f794e996add825a8b60eb49720ad384bb/src/main/clojure/clojure/tools/logging.clj#L215",
   :raw-source-url
   "https://github.com/clojure/tools.logging/raw/ec33ac5f794e996add825a8b60eb49720ad384bb/src/main/clojure/clojure/tools/logging.clj",
   :wiki-url
   "http://clojure.github.com/tools.logging//logging-api.html#clojure.tools.logging/info",
   :doc "Info level logging using print-style args.",
   :var-type "macro",
   :line 215,
   :file
   "/home/tom/src/clj/autodoc/../autodoc-work-area/tools.logging/src/src/main/clojure/clojure/tools/logging.clj"}
  {:arglists ([fmt & fmt-args] [throwable fmt & fmt-args]),
   :name "infof",
   :namespace "clojure.tools.logging",
   :source-url
   "https://github.com/clojure/tools.logging/blob/ec33ac5f794e996add825a8b60eb49720ad384bb/src/main/clojure/clojure/tools/logging.clj#L251",
   :raw-source-url
   "https://github.com/clojure/tools.logging/raw/ec33ac5f794e996add825a8b60eb49720ad384bb/src/main/clojure/clojure/tools/logging.clj",
   :wiki-url
   "http://clojure.github.com/tools.logging//logging-api.html#clojure.tools.logging/infof",
   :doc "Info level logging using format.",
   :var-type "macro",
   :line 251,
   :file
   "/home/tom/src/clj/autodoc/../autodoc-work-area/tools.logging/src/src/main/clojure/clojure/tools/logging.clj"}
  {:arglists
   ([level message]
    [level throwable message]
    [logger-ns level throwable message]
    [logger-factory logger-ns level throwable message]),
   :name "log",
   :namespace "clojure.tools.logging",
   :source-url
   "https://github.com/clojure/tools.logging/blob/ec33ac5f794e996add825a8b60eb49720ad384bb/src/main/clojure/clojure/tools/logging.clj#L68",
   :raw-source-url
   "https://github.com/clojure/tools.logging/raw/ec33ac5f794e996add825a8b60eb49720ad384bb/src/main/clojure/clojure/tools/logging.clj",
   :wiki-url
   "http://clojure.github.com/tools.logging//logging-api.html#clojure.tools.logging/log",
   :doc
   "Evaluates and logs a message only if the specified level is enabled. See log*\nfor more details.",
   :var-type "macro",
   :line 68,
   :file
   "/home/tom/src/clj/autodoc/../autodoc-work-area/tools.logging/src/src/main/clojure/clojure/tools/logging.clj"}
  {:arglists ([logger level throwable message]),
   :name "log*",
   :namespace "clojure.tools.logging",
   :source-url
   "https://github.com/clojure/tools.logging/blob/ec33ac5f794e996add825a8b60eb49720ad384bb/src/main/clojure/clojure/tools/logging.clj#L43",
   :raw-source-url
   "https://github.com/clojure/tools.logging/raw/ec33ac5f794e996add825a8b60eb49720ad384bb/src/main/clojure/clojure/tools/logging.clj",
   :wiki-url
   "http://clojure.github.com/tools.logging//logging-api.html#clojure.tools.logging/log*",
   :doc
   "Attempts to log a message, either directly or via an agent; does not check if\nthe level is enabled.\n\nFor performance reasons, an agent will only be used when invoked within a\nrunning transaction, and only for logging levels specified by\n*tx-agent-levels*. This allows those entries to only be written once the\ntransaction commits, and are discarded if it is retried or aborted.  As\ncorollary, other levels (e.g., :debug, :error) will be written even from\nfailed transactions though at the cost of repeat messages during retries.\n\nOne can override the above by setting *force* to :direct or :agent; all\nsubsequent writes will be direct or via an agent, respectively.",
   :var-type "function",
   :line 43,
   :file
   "/home/tom/src/clj/autodoc/../autodoc-work-area/tools.logging/src/src/main/clojure/clojure/tools/logging.clj"}
  {:arglists ([logger-ns] [logger-ns out-level err-level]),
   :name "log-capture!",
   :namespace "clojure.tools.logging",
   :source-url
   "https://github.com/clojure/tools.logging/blob/ec33ac5f794e996add825a8b60eb49720ad384bb/src/main/clojure/clojure/tools/logging.clj#L151",
   :raw-source-url
   "https://github.com/clojure/tools.logging/raw/ec33ac5f794e996add825a8b60eb49720ad384bb/src/main/clojure/clojure/tools/logging.clj",
   :wiki-url
   "http://clojure.github.com/tools.logging//logging-api.html#clojure.tools.logging/log-capture!",
   :doc
   "Captures System.out and System.err, piping all writes of those streams to\nthe log. If unspecified, levels default to :info and :error, respectively.\nThe specified logger-ns value will be used to namespace all log entries.\n\nNote: use with-logs to redirect output of *out* or *err*.\n\nWarning: if the logging implementation is configured to output to System.out\n(as is the default with java.util.logging) then using this function will\nresult in StackOverflowException when writing to the log.",
   :var-type "function",
   :line 151,
   :file
   "/home/tom/src/clj/autodoc/../autodoc-work-area/tools.logging/src/src/main/clojure/clojure/tools/logging.clj"}
  {:arglists ([level logger-ns]),
   :name "log-stream",
   :namespace "clojure.tools.logging",
   :source-url
   "https://github.com/clojure/tools.logging/blob/ec33ac5f794e996add825a8b60eb49720ad384bb/src/main/clojure/clojure/tools/logging.clj#L133",
   :raw-source-url
   "https://github.com/clojure/tools.logging/raw/ec33ac5f794e996add825a8b60eb49720ad384bb/src/main/clojure/clojure/tools/logging.clj",
   :wiki-url
   "http://clojure.github.com/tools.logging//logging-api.html#clojure.tools.logging/log-stream",
   :doc
   "Creates a PrintStream that will output to the log at the specified level.",
   :var-type "function",
   :line 133,
   :file
   "/home/tom/src/clj/autodoc/../autodoc-work-area/tools.logging/src/src/main/clojure/clojure/tools/logging.clj"}
  {:arglists ([]),
   :name "log-uncapture!",
   :namespace "clojure.tools.logging",
   :source-url
   "https://github.com/clojure/tools.logging/blob/ec33ac5f794e996add825a8b60eb49720ad384bb/src/main/clojure/clojure/tools/logging.clj#L171",
   :raw-source-url
   "https://github.com/clojure/tools.logging/raw/ec33ac5f794e996add825a8b60eb49720ad384bb/src/main/clojure/clojure/tools/logging.clj",
   :wiki-url
   "http://clojure.github.com/tools.logging//logging-api.html#clojure.tools.logging/log-uncapture!",
   :doc "Restores System.out and System.err to their original values.",
   :var-type "function",
   :line 171,
   :file
   "/home/tom/src/clj/autodoc/../autodoc-work-area/tools.logging/src/src/main/clojure/clojure/tools/logging.clj"}
  {:arglists ([level fmt & fmt-args] [level throwable fmt & fmt-args]),
   :name "logf",
   :namespace "clojure.tools.logging",
   :source-url
   "https://github.com/clojure/tools.logging/blob/ec33ac5f794e996add825a8b60eb49720ad384bb/src/main/clojure/clojure/tools/logging.clj#L95",
   :raw-source-url
   "https://github.com/clojure/tools.logging/raw/ec33ac5f794e996add825a8b60eb49720ad384bb/src/main/clojure/clojure/tools/logging.clj",
   :wiki-url
   "http://clojure.github.com/tools.logging//logging-api.html#clojure.tools.logging/logf",
   :doc
   "Logs a message using a format string and args. Can optionally take a\nthrowable as its second arg. See level-specific macros, e.g., debugf.",
   :var-type "macro",
   :line 95,
   :file
   "/home/tom/src/clj/autodoc/../autodoc-work-area/tools.logging/src/src/main/clojure/clojure/tools/logging.clj"}
  {:arglists ([level message & more] [level throwable message & more]),
   :name "logp",
   :namespace "clojure.tools.logging",
   :source-url
   "https://github.com/clojure/tools.logging/blob/ec33ac5f794e996add825a8b60eb49720ad384bb/src/main/clojure/clojure/tools/logging.clj#L82",
   :raw-source-url
   "https://github.com/clojure/tools.logging/raw/ec33ac5f794e996add825a8b60eb49720ad384bb/src/main/clojure/clojure/tools/logging.clj",
   :wiki-url
   "http://clojure.github.com/tools.logging//logging-api.html#clojure.tools.logging/logp",
   :doc
   "Logs a message using print style args. Can optionally take a throwable as its\nsecond arg. See level-specific macros, e.g., debug.",
   :var-type "macro",
   :line 82,
   :file
   "/home/tom/src/clj/autodoc/../autodoc-work-area/tools.logging/src/src/main/clojure/clojure/tools/logging.clj"}
  {:arglists ([expr] [level expr]),
   :name "spy",
   :namespace "clojure.tools.logging",
   :source-url
   "https://github.com/clojure/tools.logging/blob/ec33ac5f794e996add825a8b60eb49720ad384bb/src/main/clojure/clojure/tools/logging.clj#L117",
   :raw-source-url
   "https://github.com/clojure/tools.logging/raw/ec33ac5f794e996add825a8b60eb49720ad384bb/src/main/clojure/clojure/tools/logging.clj",
   :wiki-url
   "http://clojure.github.com/tools.logging//logging-api.html#clojure.tools.logging/spy",
   :doc
   "Evaluates expr and may write the form and its result to the log. Returns the\nresult of expr. Defaults to :debug log level.",
   :var-type "macro",
   :line 117,
   :file
   "/home/tom/src/clj/autodoc/../autodoc-work-area/tools.logging/src/src/main/clojure/clojure/tools/logging.clj"}
  {:arglists ([message & more] [throwable message & more]),
   :name "trace",
   :namespace "clojure.tools.logging",
   :source-url
   "https://github.com/clojure/tools.logging/blob/ec33ac5f794e996add825a8b60eb49720ad384bb/src/main/clojure/clojure/tools/logging.clj#L203",
   :raw-source-url
   "https://github.com/clojure/tools.logging/raw/ec33ac5f794e996add825a8b60eb49720ad384bb/src/main/clojure/clojure/tools/logging.clj",
   :wiki-url
   "http://clojure.github.com/tools.logging//logging-api.html#clojure.tools.logging/trace",
   :doc "Trace level logging using print-style args.",
   :var-type "macro",
   :line 203,
   :file
   "/home/tom/src/clj/autodoc/../autodoc-work-area/tools.logging/src/src/main/clojure/clojure/tools/logging.clj"}
  {:arglists ([fmt & fmt-args] [throwable fmt & fmt-args]),
   :name "tracef",
   :namespace "clojure.tools.logging",
   :source-url
   "https://github.com/clojure/tools.logging/blob/ec33ac5f794e996add825a8b60eb49720ad384bb/src/main/clojure/clojure/tools/logging.clj#L239",
   :raw-source-url
   "https://github.com/clojure/tools.logging/raw/ec33ac5f794e996add825a8b60eb49720ad384bb/src/main/clojure/clojure/tools/logging.clj",
   :wiki-url
   "http://clojure.github.com/tools.logging//logging-api.html#clojure.tools.logging/tracef",
   :doc "Trace level logging using format.",
   :var-type "macro",
   :line 239,
   :file
   "/home/tom/src/clj/autodoc/../autodoc-work-area/tools.logging/src/src/main/clojure/clojure/tools/logging.clj"}
  {:arglists ([message & more] [throwable message & more]),
   :name "warn",
   :namespace "clojure.tools.logging",
   :source-url
   "https://github.com/clojure/tools.logging/blob/ec33ac5f794e996add825a8b60eb49720ad384bb/src/main/clojure/clojure/tools/logging.clj#L221",
   :raw-source-url
   "https://github.com/clojure/tools.logging/raw/ec33ac5f794e996add825a8b60eb49720ad384bb/src/main/clojure/clojure/tools/logging.clj",
   :wiki-url
   "http://clojure.github.com/tools.logging//logging-api.html#clojure.tools.logging/warn",
   :doc "Warn level logging using print-style args.",
   :var-type "macro",
   :line 221,
   :file
   "/home/tom/src/clj/autodoc/../autodoc-work-area/tools.logging/src/src/main/clojure/clojure/tools/logging.clj"}
  {:arglists ([fmt & fmt-args] [throwable fmt & fmt-args]),
   :name "warnf",
   :namespace "clojure.tools.logging",
   :source-url
   "https://github.com/clojure/tools.logging/blob/ec33ac5f794e996add825a8b60eb49720ad384bb/src/main/clojure/clojure/tools/logging.clj#L257",
   :raw-source-url
   "https://github.com/clojure/tools.logging/raw/ec33ac5f794e996add825a8b60eb49720ad384bb/src/main/clojure/clojure/tools/logging.clj",
   :wiki-url
   "http://clojure.github.com/tools.logging//logging-api.html#clojure.tools.logging/warnf",
   :doc "Warn level logging using format.",
   :var-type "macro",
   :line 257,
   :file
   "/home/tom/src/clj/autodoc/../autodoc-work-area/tools.logging/src/src/main/clojure/clojure/tools/logging.clj"}
  {:arglists
   ([logger-ns & body] [[logger-ns out-level err-level] & body]),
   :name "with-logs",
   :namespace "clojure.tools.logging",
   :source-url
   "https://github.com/clojure/tools.logging/blob/ec33ac5f794e996add825a8b60eb49720ad384bb/src/main/clojure/clojure/tools/logging.clj#L180",
   :raw-source-url
   "https://github.com/clojure/tools.logging/raw/ec33ac5f794e996add825a8b60eb49720ad384bb/src/main/clojure/clojure/tools/logging.clj",
   :wiki-url
   "http://clojure.github.com/tools.logging//logging-api.html#clojure.tools.logging/with-logs",
   :doc
   "Evaluates exprs in a context in which *out* and *err* write to the log. The\nspecified logger-ns value will be used to namespace all log entries.\n\nBy default *out* and *err* write to :info and :error, respectively.",
   :var-type "macro",
   :line 180,
   :file
   "/home/tom/src/clj/autodoc/../autodoc-work-area/tools.logging/src/src/main/clojure/clojure/tools/logging.clj"}
  {:arglists ([]),
   :name "load-factory",
   :namespace "clojure.tools.logging.commons-logging",
   :source-url
   "https://github.com/clojure/tools.logging/blob/42dfba84a373e52a4e80af6467e47761e6f20678/src/main/clojure/clojure/tools/logging/commons_logging.clj#L12",
   :raw-source-url
   "https://github.com/clojure/tools.logging/raw/42dfba84a373e52a4e80af6467e47761e6f20678/src/main/clojure/clojure/tools/logging/commons_logging.clj",
   :wiki-url
   "http://clojure.github.com/tools.logging//logging-api.html#clojure.tools.logging.commons-logging/load-factory",
   :doc
   "Returns a commons-logging-based implementation of the LoggerFactory protocol, or\nnil if not available. End-users should not need to call this.",
   :var-type "function",
   :line 12,
   :file
   "/home/tom/src/clj/autodoc/../autodoc-work-area/tools.logging/src/src/main/clojure/clojure/tools/logging/commons_logging.clj"}
  {:raw-source-url
   "https://github.com/clojure/tools.logging/raw/42dfba84a373e52a4e80af6467e47761e6f20678/src/main/clojure/clojure/tools/logging/impl.clj",
   :source-url
   "https://github.com/clojure/tools.logging/blob/42dfba84a373e52a4e80af6467e47761e6f20678/src/main/clojure/clojure/tools/logging/impl.clj#L15",
   :wiki-url
   "http://clojure.github.com/tools.logging//logging-api.html#clojure.tools.logging.impl/Logger",
   :namespace "clojure.tools.logging.impl",
   :line 15,
   :file
   "/home/tom/src/clj/autodoc/../autodoc-work-area/tools.logging/src/src/main/clojure/clojure/tools/logging/impl.clj",
   :var-type "var",
   :doc
   "The protocol through which macros will interact with an underlying logging\nimplementation.  Implementations should at least support the six standard\nlogging levels if they wish to work from the level-specific macros.",
   :name "Logger"}
  {:raw-source-url
   "https://github.com/clojure/tools.logging/raw/42dfba84a373e52a4e80af6467e47761e6f20678/src/main/clojure/clojure/tools/logging/impl.clj",
   :source-url
   "https://github.com/clojure/tools.logging/blob/42dfba84a373e52a4e80af6467e47761e6f20678/src/main/clojure/clojure/tools/logging/impl.clj#L24",
   :wiki-url
   "http://clojure.github.com/tools.logging//logging-api.html#clojure.tools.logging.impl/LoggerFactory",
   :namespace "clojure.tools.logging.impl",
   :line 24,
   :file
   "/home/tom/src/clj/autodoc/../autodoc-work-area/tools.logging/src/src/main/clojure/clojure/tools/logging/impl.clj",
   :var-type "var",
   :doc
   "The protocol through which macros will obtain an instance satisfying Logger as\nwell as providing information about the particular implementation being used.\nImplementations should be bound to *logger-factory* in order to be picked up by\nthis library.",
   :name "LoggerFactory"}
  {:raw-source-url nil,
   :source-url nil,
   :wiki-url
   "http://clojure.github.com/tools.logging//logging-api.html#clojure.tools.logging.impl/enabled?",
   :namespace "clojure.tools.logging.impl",
   :var-type "function",
   :arglists ([logger level]),
   :doc "Check if a particular level is enabled for the given Logger.",
   :name "enabled?"}
  {:raw-source-url nil,
   :source-url nil,
   :wiki-url
   "http://clojure.github.com/tools.logging//logging-api.html#clojure.tools.logging.impl/get-logger",
   :namespace "clojure.tools.logging.impl",
   :var-type "function",
   :arglists ([factory logger-ns]),
   :doc "Returns an implementation-specific Logger by namespace.",
   :name "get-logger"}
  {:raw-source-url nil,
   :source-url nil,
   :wiki-url
   "http://clojure.github.com/tools.logging//logging-api.html#clojure.tools.logging.impl/name",
   :namespace "clojure.tools.logging.impl",
   :var-type "function",
   :arglists ([factory]),
   :doc "Returns some text identifying the underlying implementation.",
   :name "name"}
  {:raw-source-url nil,
   :source-url nil,
   :wiki-url
   "http://clojure.github.com/tools.logging//logging-api.html#clojure.tools.logging.impl/write!",
   :namespace "clojure.tools.logging.impl",
   :var-type "function",
   :arglists ([logger level throwable message]),
   :doc "Writes a log message to the given Logger.",
   :name "write!"}
  {:arglists ([]),
   :name "load-factory",
   :namespace "clojure.tools.logging.java-util-logging",
   :source-url
   "https://github.com/clojure/tools.logging/blob/42dfba84a373e52a4e80af6467e47761e6f20678/src/main/clojure/clojure/tools/logging/java_util_logging.clj#L12",
   :raw-source-url
   "https://github.com/clojure/tools.logging/raw/42dfba84a373e52a4e80af6467e47761e6f20678/src/main/clojure/clojure/tools/logging/java_util_logging.clj",
   :wiki-url
   "http://clojure.github.com/tools.logging//logging-api.html#clojure.tools.logging.java-util-logging/load-factory",
   :doc
   "Returns a java.util.logging-based implementation of the LoggerFactory protocol,\nor nil if not available. End-users should not need to call this.",
   :var-type "function",
   :line 12,
   :file
   "/home/tom/src/clj/autodoc/../autodoc-work-area/tools.logging/src/src/main/clojure/clojure/tools/logging/java_util_logging.clj"}
  {:arglists ([]),
   :name "load-factory",
   :namespace "clojure.tools.logging.log4j",
   :source-url
   "https://github.com/clojure/tools.logging/blob/42dfba84a373e52a4e80af6467e47761e6f20678/src/main/clojure/clojure/tools/logging/log4j.clj#L12",
   :raw-source-url
   "https://github.com/clojure/tools.logging/raw/42dfba84a373e52a4e80af6467e47761e6f20678/src/main/clojure/clojure/tools/logging/log4j.clj",
   :wiki-url
   "http://clojure.github.com/tools.logging//logging-api.html#clojure.tools.logging.log4j/load-factory",
   :doc
   "Returns a log4j-based implementation of the LoggerFactory protocol, or nil if\nnot available. End-users should not need to call this.",
   :var-type "function",
   :line 12,
   :file
   "/home/tom/src/clj/autodoc/../autodoc-work-area/tools.logging/src/src/main/clojure/clojure/tools/logging/log4j.clj"}
  {:arglists ([]),
   :name "load-factory",
   :namespace "clojure.tools.logging.slf4j",
   :source-url
   "https://github.com/clojure/tools.logging/blob/42dfba84a373e52a4e80af6467e47761e6f20678/src/main/clojure/clojure/tools/logging/slf4j.clj#L12",
   :raw-source-url
   "https://github.com/clojure/tools.logging/raw/42dfba84a373e52a4e80af6467e47761e6f20678/src/main/clojure/clojure/tools/logging/slf4j.clj",
   :wiki-url
   "http://clojure.github.com/tools.logging//logging-api.html#clojure.tools.logging.slf4j/load-factory",
   :doc
   "Returns a SLF4J-based implementation of the LoggerFactory protocol, or nil if\nnot available. End-users should not need to call this.",
   :var-type "function",
   :line 12,
   :file
   "/home/tom/src/clj/autodoc/../autodoc-work-area/tools.logging/src/src/main/clojure/clojure/tools/logging/slf4j.clj"})}
