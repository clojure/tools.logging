{:namespaces
 ({:doc
   "Logging macros which delegate to a specific logging implementation. At\nruntime a specific implementation is selected from, in order, slf4j,\nApache commons-logging, log4j, and finally java.util.logging.\n\nThe logging implementation can be expliticly determined by using\nbinding or alter-var-root to change the value of *logger-factory* to\nanother implementation of clojure.tools.logging.impl/LoggerFactory\n(see also the *-factory functions in the impl namespace).",
   :author "Alex Taggart",
   :name "clojure.tools.logging",
   :wiki-url "http://clojure.github.io/tools.logging/index.html",
   :source-url
   "https://github.com/clojure/tools.logging/blob/67d2fa8411e1f226bf1e77f3e5f6326a00e5b954/src/main/clojure/clojure/tools/logging.clj"}
  {:doc
   "Protocols used to allow access to logging implementations.\nThis namespace only need be used by those providing logging\nimplementations to be consumed by the core api.",
   :author "Alex Taggart",
   :name "clojure.tools.logging.impl",
   :wiki-url
   "http://clojure.github.io/tools.logging/index.html#clojure.tools.logging.impl",
   :source-url
   "https://github.com/clojure/tools.logging/blob/26d5ebb12cf8b029644161faad9af2e5412efe94/src/main/clojure/clojure/tools/logging/impl.clj"}),
 :vars
 ({:raw-source-url
   "https://github.com/clojure/tools.logging/raw/67d2fa8411e1f226bf1e77f3e5f6326a00e5b954/src/main/clojure/clojure/tools/logging.clj",
   :name "*force*",
   :file "src/main/clojure/clojure/tools/logging.clj",
   :source-url
   "https://github.com/clojure/tools.logging/blob/67d2fa8411e1f226bf1e77f3e5f6326a00e5b954/src/main/clojure/clojure/tools/logging.clj#L38",
   :dynamic true,
   :line 38,
   :var-type "var",
   :arglists nil,
   :doc
   "Overrides the default rules for choosing between logging directly or via an\nagent. Defaults to nil. See log* for details.",
   :namespace "clojure.tools.logging",
   :wiki-url
   "http://clojure.github.io/tools.logging//index.html#clojure.tools.logging/*force*"}
  {:raw-source-url
   "https://github.com/clojure/tools.logging/raw/67d2fa8411e1f226bf1e77f3e5f6326a00e5b954/src/main/clojure/clojure/tools/logging.clj",
   :name "*logger-factory*",
   :file "src/main/clojure/clojure/tools/logging.clj",
   :source-url
   "https://github.com/clojure/tools.logging/blob/67d2fa8411e1f226bf1e77f3e5f6326a00e5b954/src/main/clojure/clojure/tools/logging.clj#L288",
   :dynamic true,
   :line 288,
   :var-type "var",
   :arglists nil,
   :doc
   "An instance satisfying the impl/LoggerFactory protocol. Used internally to\nobtain an impl/Logger. Defaults to the value returned from impl/find-factory.",
   :namespace "clojure.tools.logging",
   :wiki-url
   "http://clojure.github.io/tools.logging//index.html#clojure.tools.logging/*logger-factory*"}
  {:raw-source-url
   "https://github.com/clojure/tools.logging/raw/67d2fa8411e1f226bf1e77f3e5f6326a00e5b954/src/main/clojure/clojure/tools/logging.clj",
   :name "*logging-agent*",
   :file "src/main/clojure/clojure/tools/logging.clj",
   :source-url
   "https://github.com/clojure/tools.logging/blob/67d2fa8411e1f226bf1e77f3e5f6326a00e5b954/src/main/clojure/clojure/tools/logging.clj#L28",
   :dynamic true,
   :line 28,
   :var-type "var",
   :arglists nil,
   :doc
   "The default agent used for performing logging when direct logging is\ndisabled. See log* for details.",
   :namespace "clojure.tools.logging",
   :wiki-url
   "http://clojure.github.io/tools.logging//index.html#clojure.tools.logging/*logging-agent*"}
  {:raw-source-url
   "https://github.com/clojure/tools.logging/raw/67d2fa8411e1f226bf1e77f3e5f6326a00e5b954/src/main/clojure/clojure/tools/logging.clj",
   :name "*tx-agent-levels*",
   :file "src/main/clojure/clojure/tools/logging.clj",
   :source-url
   "https://github.com/clojure/tools.logging/blob/67d2fa8411e1f226bf1e77f3e5f6326a00e5b954/src/main/clojure/clojure/tools/logging.clj#L33",
   :dynamic true,
   :line 33,
   :var-type "var",
   :arglists nil,
   :doc
   "The set of levels that will require using an agent when logging from within a\nrunning transaction. Defaults to #{:info :warn}. See log* for details.",
   :namespace "clojure.tools.logging",
   :wiki-url
   "http://clojure.github.io/tools.logging//index.html#clojure.tools.logging/*tx-agent-levels*"}
  {:raw-source-url
   "https://github.com/clojure/tools.logging/raw/67d2fa8411e1f226bf1e77f3e5f6326a00e5b954/src/main/clojure/clojure/tools/logging.clj",
   :name "debug",
   :file "src/main/clojure/clojure/tools/logging.clj",
   :source-url
   "https://github.com/clojure/tools.logging/blob/67d2fa8411e1f226bf1e77f3e5f6326a00e5b954/src/main/clojure/clojure/tools/logging.clj#L222",
   :line 222,
   :var-type "macro",
   :arglists ([message & more] [throwable message & more]),
   :doc "Debug level logging using print-style args.",
   :namespace "clojure.tools.logging",
   :wiki-url
   "http://clojure.github.io/tools.logging//index.html#clojure.tools.logging/debug"}
  {:raw-source-url
   "https://github.com/clojure/tools.logging/raw/67d2fa8411e1f226bf1e77f3e5f6326a00e5b954/src/main/clojure/clojure/tools/logging.clj",
   :name "debugf",
   :file "src/main/clojure/clojure/tools/logging.clj",
   :source-url
   "https://github.com/clojure/tools.logging/blob/67d2fa8411e1f226bf1e77f3e5f6326a00e5b954/src/main/clojure/clojure/tools/logging.clj#L258",
   :line 258,
   :var-type "macro",
   :arglists ([fmt & fmt-args] [throwable fmt & fmt-args]),
   :doc "Debug level logging using format.",
   :namespace "clojure.tools.logging",
   :wiki-url
   "http://clojure.github.io/tools.logging//index.html#clojure.tools.logging/debugf"}
  {:raw-source-url
   "https://github.com/clojure/tools.logging/raw/67d2fa8411e1f226bf1e77f3e5f6326a00e5b954/src/main/clojure/clojure/tools/logging.clj",
   :name "enabled?",
   :file "src/main/clojure/clojure/tools/logging.clj",
   :source-url
   "https://github.com/clojure/tools.logging/blob/67d2fa8411e1f226bf1e77f3e5f6326a00e5b954/src/main/clojure/clojure/tools/logging.clj#L111",
   :line 111,
   :var-type "macro",
   :arglists ([level] [level logger-ns]),
   :doc
   "Returns true if the specific logging level is enabled.  Use of this macro\nshould only be necessary if one needs to execute alternate code paths beyond\nwhether the log should be written to.",
   :namespace "clojure.tools.logging",
   :wiki-url
   "http://clojure.github.io/tools.logging//index.html#clojure.tools.logging/enabled?"}
  {:raw-source-url
   "https://github.com/clojure/tools.logging/raw/67d2fa8411e1f226bf1e77f3e5f6326a00e5b954/src/main/clojure/clojure/tools/logging.clj",
   :name "error",
   :file "src/main/clojure/clojure/tools/logging.clj",
   :source-url
   "https://github.com/clojure/tools.logging/blob/67d2fa8411e1f226bf1e77f3e5f6326a00e5b954/src/main/clojure/clojure/tools/logging.clj#L240",
   :line 240,
   :var-type "macro",
   :arglists ([message & more] [throwable message & more]),
   :doc "Error level logging using print-style args.",
   :namespace "clojure.tools.logging",
   :wiki-url
   "http://clojure.github.io/tools.logging//index.html#clojure.tools.logging/error"}
  {:raw-source-url
   "https://github.com/clojure/tools.logging/raw/67d2fa8411e1f226bf1e77f3e5f6326a00e5b954/src/main/clojure/clojure/tools/logging.clj",
   :name "errorf",
   :file "src/main/clojure/clojure/tools/logging.clj",
   :source-url
   "https://github.com/clojure/tools.logging/blob/67d2fa8411e1f226bf1e77f3e5f6326a00e5b954/src/main/clojure/clojure/tools/logging.clj#L276",
   :line 276,
   :var-type "macro",
   :arglists ([fmt & fmt-args] [throwable fmt & fmt-args]),
   :doc "Error level logging using format.",
   :namespace "clojure.tools.logging",
   :wiki-url
   "http://clojure.github.io/tools.logging//index.html#clojure.tools.logging/errorf"}
  {:raw-source-url
   "https://github.com/clojure/tools.logging/raw/67d2fa8411e1f226bf1e77f3e5f6326a00e5b954/src/main/clojure/clojure/tools/logging.clj",
   :name "fatal",
   :file "src/main/clojure/clojure/tools/logging.clj",
   :source-url
   "https://github.com/clojure/tools.logging/blob/67d2fa8411e1f226bf1e77f3e5f6326a00e5b954/src/main/clojure/clojure/tools/logging.clj#L246",
   :line 246,
   :var-type "macro",
   :arglists ([message & more] [throwable message & more]),
   :doc "Fatal level logging using print-style args.",
   :namespace "clojure.tools.logging",
   :wiki-url
   "http://clojure.github.io/tools.logging//index.html#clojure.tools.logging/fatal"}
  {:raw-source-url
   "https://github.com/clojure/tools.logging/raw/67d2fa8411e1f226bf1e77f3e5f6326a00e5b954/src/main/clojure/clojure/tools/logging.clj",
   :name "fatalf",
   :file "src/main/clojure/clojure/tools/logging.clj",
   :source-url
   "https://github.com/clojure/tools.logging/blob/67d2fa8411e1f226bf1e77f3e5f6326a00e5b954/src/main/clojure/clojure/tools/logging.clj#L282",
   :line 282,
   :var-type "macro",
   :arglists ([fmt & fmt-args] [throwable fmt & fmt-args]),
   :doc "Fatal level logging using format.",
   :namespace "clojure.tools.logging",
   :wiki-url
   "http://clojure.github.io/tools.logging//index.html#clojure.tools.logging/fatalf"}
  {:raw-source-url
   "https://github.com/clojure/tools.logging/raw/67d2fa8411e1f226bf1e77f3e5f6326a00e5b954/src/main/clojure/clojure/tools/logging.clj",
   :name "info",
   :file "src/main/clojure/clojure/tools/logging.clj",
   :source-url
   "https://github.com/clojure/tools.logging/blob/67d2fa8411e1f226bf1e77f3e5f6326a00e5b954/src/main/clojure/clojure/tools/logging.clj#L228",
   :line 228,
   :var-type "macro",
   :arglists ([message & more] [throwable message & more]),
   :doc "Info level logging using print-style args.",
   :namespace "clojure.tools.logging",
   :wiki-url
   "http://clojure.github.io/tools.logging//index.html#clojure.tools.logging/info"}
  {:raw-source-url
   "https://github.com/clojure/tools.logging/raw/67d2fa8411e1f226bf1e77f3e5f6326a00e5b954/src/main/clojure/clojure/tools/logging.clj",
   :name "infof",
   :file "src/main/clojure/clojure/tools/logging.clj",
   :source-url
   "https://github.com/clojure/tools.logging/blob/67d2fa8411e1f226bf1e77f3e5f6326a00e5b954/src/main/clojure/clojure/tools/logging.clj#L264",
   :line 264,
   :var-type "macro",
   :arglists ([fmt & fmt-args] [throwable fmt & fmt-args]),
   :doc "Info level logging using format.",
   :namespace "clojure.tools.logging",
   :wiki-url
   "http://clojure.github.io/tools.logging//index.html#clojure.tools.logging/infof"}
  {:raw-source-url
   "https://github.com/clojure/tools.logging/raw/67d2fa8411e1f226bf1e77f3e5f6326a00e5b954/src/main/clojure/clojure/tools/logging.clj",
   :name "log",
   :file "src/main/clojure/clojure/tools/logging.clj",
   :source-url
   "https://github.com/clojure/tools.logging/blob/67d2fa8411e1f226bf1e77f3e5f6326a00e5b954/src/main/clojure/clojure/tools/logging.clj#L69",
   :line 69,
   :var-type "macro",
   :arglists
   ([level message]
    [level throwable message]
    [logger-ns level throwable message]
    [logger-factory logger-ns level throwable message]),
   :doc
   "Evaluates and logs a message only if the specified level is enabled. See log*\nfor more details.",
   :namespace "clojure.tools.logging",
   :wiki-url
   "http://clojure.github.io/tools.logging//index.html#clojure.tools.logging/log"}
  {:raw-source-url
   "https://github.com/clojure/tools.logging/raw/67d2fa8411e1f226bf1e77f3e5f6326a00e5b954/src/main/clojure/clojure/tools/logging.clj",
   :name "log*",
   :file "src/main/clojure/clojure/tools/logging.clj",
   :source-url
   "https://github.com/clojure/tools.logging/blob/67d2fa8411e1f226bf1e77f3e5f6326a00e5b954/src/main/clojure/clojure/tools/logging.clj#L43",
   :line 43,
   :var-type "function",
   :arglists ([logger level throwable message]),
   :doc
   "Attempts to log a message, either directly or via an agent; does not check if\nthe level is enabled.\n\nFor performance reasons, an agent will only be used when invoked within a\nrunning transaction, and only for logging levels specified by\n*tx-agent-levels*. This allows those entries to only be written once the\ntransaction commits, and are discarded if it is retried or aborted.  As\ncorollary, other levels (e.g., :debug, :error) will be written even from\nfailed transactions though at the cost of repeat messages during retries.\n\nOne can override the above by setting *force* to :direct or :agent; all\nsubsequent writes will be direct or via an agent, respectively.",
   :namespace "clojure.tools.logging",
   :wiki-url
   "http://clojure.github.io/tools.logging//index.html#clojure.tools.logging/log*"}
  {:raw-source-url
   "https://github.com/clojure/tools.logging/raw/67d2fa8411e1f226bf1e77f3e5f6326a00e5b954/src/main/clojure/clojure/tools/logging.clj",
   :name "log-capture!",
   :file "src/main/clojure/clojure/tools/logging.clj",
   :source-url
   "https://github.com/clojure/tools.logging/blob/67d2fa8411e1f226bf1e77f3e5f6326a00e5b954/src/main/clojure/clojure/tools/logging.clj#L164",
   :line 164,
   :var-type "function",
   :arglists ([logger-ns] [logger-ns out-level err-level]),
   :doc
   "Captures System.out and System.err, piping all writes of those streams to\nthe log. If unspecified, levels default to :info and :error, respectively.\nThe specified logger-ns value will be used to namespace all log entries.\n\nNote: use with-logs to redirect output of *out* or *err*.\n\nWarning: if the logging implementation is configured to output to System.out\n(as is the default with java.util.logging) then using this function will\nresult in StackOverflowException when writing to the log.",
   :namespace "clojure.tools.logging",
   :wiki-url
   "http://clojure.github.io/tools.logging//index.html#clojure.tools.logging/log-capture!"}
  {:raw-source-url
   "https://github.com/clojure/tools.logging/raw/67d2fa8411e1f226bf1e77f3e5f6326a00e5b954/src/main/clojure/clojure/tools/logging.clj",
   :name "log-stream",
   :file "src/main/clojure/clojure/tools/logging.clj",
   :source-url
   "https://github.com/clojure/tools.logging/blob/67d2fa8411e1f226bf1e77f3e5f6326a00e5b954/src/main/clojure/clojure/tools/logging.clj#L146",
   :line 146,
   :var-type "function",
   :arglists ([level logger-ns]),
   :doc
   "Creates a PrintStream that will output to the log at the specified level.",
   :namespace "clojure.tools.logging",
   :wiki-url
   "http://clojure.github.io/tools.logging//index.html#clojure.tools.logging/log-stream"}
  {:raw-source-url
   "https://github.com/clojure/tools.logging/raw/67d2fa8411e1f226bf1e77f3e5f6326a00e5b954/src/main/clojure/clojure/tools/logging.clj",
   :name "log-uncapture!",
   :file "src/main/clojure/clojure/tools/logging.clj",
   :source-url
   "https://github.com/clojure/tools.logging/blob/67d2fa8411e1f226bf1e77f3e5f6326a00e5b954/src/main/clojure/clojure/tools/logging.clj#L184",
   :line 184,
   :var-type "function",
   :arglists ([]),
   :doc "Restores System.out and System.err to their original values.",
   :namespace "clojure.tools.logging",
   :wiki-url
   "http://clojure.github.io/tools.logging//index.html#clojure.tools.logging/log-uncapture!"}
  {:raw-source-url
   "https://github.com/clojure/tools.logging/raw/67d2fa8411e1f226bf1e77f3e5f6326a00e5b954/src/main/clojure/clojure/tools/logging.clj",
   :name "logf",
   :file "src/main/clojure/clojure/tools/logging.clj",
   :source-url
   "https://github.com/clojure/tools.logging/blob/67d2fa8411e1f226bf1e77f3e5f6326a00e5b954/src/main/clojure/clojure/tools/logging.clj#L97",
   :line 97,
   :var-type "macro",
   :arglists ([level fmt & fmt-args] [level throwable fmt & fmt-args]),
   :doc
   "Logs a message using a format string and args. Can optionally take a\nthrowable as its second arg. See level-specific macros, e.g., debugf.",
   :namespace "clojure.tools.logging",
   :wiki-url
   "http://clojure.github.io/tools.logging//index.html#clojure.tools.logging/logf"}
  {:raw-source-url
   "https://github.com/clojure/tools.logging/raw/67d2fa8411e1f226bf1e77f3e5f6326a00e5b954/src/main/clojure/clojure/tools/logging.clj",
   :name "logp",
   :file "src/main/clojure/clojure/tools/logging.clj",
   :source-url
   "https://github.com/clojure/tools.logging/blob/67d2fa8411e1f226bf1e77f3e5f6326a00e5b954/src/main/clojure/clojure/tools/logging.clj#L83",
   :line 83,
   :var-type "macro",
   :arglists ([level message & more] [level throwable message & more]),
   :doc
   "Logs a message using print style args. Can optionally take a throwable as its\nsecond arg. See level-specific macros, e.g., debug.",
   :namespace "clojure.tools.logging",
   :wiki-url
   "http://clojure.github.io/tools.logging//index.html#clojure.tools.logging/logp"}
  {:raw-source-url
   "https://github.com/clojure/tools.logging/raw/67d2fa8411e1f226bf1e77f3e5f6326a00e5b954/src/main/clojure/clojure/tools/logging.clj",
   :name "spy",
   :file "src/main/clojure/clojure/tools/logging.clj",
   :source-url
   "https://github.com/clojure/tools.logging/blob/67d2fa8411e1f226bf1e77f3e5f6326a00e5b954/src/main/clojure/clojure/tools/logging.clj#L120",
   :line 120,
   :var-type "macro",
   :arglists ([expr] [level expr]),
   :doc
   "Evaluates expr and may write the form and its result to the log. Returns the\nresult of expr. Defaults to :debug log level.",
   :namespace "clojure.tools.logging",
   :wiki-url
   "http://clojure.github.io/tools.logging//index.html#clojure.tools.logging/spy"}
  {:raw-source-url
   "https://github.com/clojure/tools.logging/raw/67d2fa8411e1f226bf1e77f3e5f6326a00e5b954/src/main/clojure/clojure/tools/logging.clj",
   :name "spyf",
   :file "src/main/clojure/clojure/tools/logging.clj",
   :source-url
   "https://github.com/clojure/tools.logging/blob/67d2fa8411e1f226bf1e77f3e5f6326a00e5b954/src/main/clojure/clojure/tools/logging.clj#L136",
   :line 136,
   :var-type "macro",
   :arglists ([fmt expr] [level fmt expr]),
   :doc
   "Evaluates expr and may write (format fmt result) to the log. Returns the\nresult of expr. Defaults to :debug log level.",
   :namespace "clojure.tools.logging",
   :wiki-url
   "http://clojure.github.io/tools.logging//index.html#clojure.tools.logging/spyf"}
  {:raw-source-url
   "https://github.com/clojure/tools.logging/raw/67d2fa8411e1f226bf1e77f3e5f6326a00e5b954/src/main/clojure/clojure/tools/logging.clj",
   :name "trace",
   :file "src/main/clojure/clojure/tools/logging.clj",
   :source-url
   "https://github.com/clojure/tools.logging/blob/67d2fa8411e1f226bf1e77f3e5f6326a00e5b954/src/main/clojure/clojure/tools/logging.clj#L216",
   :line 216,
   :var-type "macro",
   :arglists ([message & more] [throwable message & more]),
   :doc "Trace level logging using print-style args.",
   :namespace "clojure.tools.logging",
   :wiki-url
   "http://clojure.github.io/tools.logging//index.html#clojure.tools.logging/trace"}
  {:raw-source-url
   "https://github.com/clojure/tools.logging/raw/67d2fa8411e1f226bf1e77f3e5f6326a00e5b954/src/main/clojure/clojure/tools/logging.clj",
   :name "tracef",
   :file "src/main/clojure/clojure/tools/logging.clj",
   :source-url
   "https://github.com/clojure/tools.logging/blob/67d2fa8411e1f226bf1e77f3e5f6326a00e5b954/src/main/clojure/clojure/tools/logging.clj#L252",
   :line 252,
   :var-type "macro",
   :arglists ([fmt & fmt-args] [throwable fmt & fmt-args]),
   :doc "Trace level logging using format.",
   :namespace "clojure.tools.logging",
   :wiki-url
   "http://clojure.github.io/tools.logging//index.html#clojure.tools.logging/tracef"}
  {:raw-source-url
   "https://github.com/clojure/tools.logging/raw/67d2fa8411e1f226bf1e77f3e5f6326a00e5b954/src/main/clojure/clojure/tools/logging.clj",
   :name "warn",
   :file "src/main/clojure/clojure/tools/logging.clj",
   :source-url
   "https://github.com/clojure/tools.logging/blob/67d2fa8411e1f226bf1e77f3e5f6326a00e5b954/src/main/clojure/clojure/tools/logging.clj#L234",
   :line 234,
   :var-type "macro",
   :arglists ([message & more] [throwable message & more]),
   :doc "Warn level logging using print-style args.",
   :namespace "clojure.tools.logging",
   :wiki-url
   "http://clojure.github.io/tools.logging//index.html#clojure.tools.logging/warn"}
  {:raw-source-url
   "https://github.com/clojure/tools.logging/raw/67d2fa8411e1f226bf1e77f3e5f6326a00e5b954/src/main/clojure/clojure/tools/logging.clj",
   :name "warnf",
   :file "src/main/clojure/clojure/tools/logging.clj",
   :source-url
   "https://github.com/clojure/tools.logging/blob/67d2fa8411e1f226bf1e77f3e5f6326a00e5b954/src/main/clojure/clojure/tools/logging.clj#L270",
   :line 270,
   :var-type "macro",
   :arglists ([fmt & fmt-args] [throwable fmt & fmt-args]),
   :doc "Warn level logging using format.",
   :namespace "clojure.tools.logging",
   :wiki-url
   "http://clojure.github.io/tools.logging//index.html#clojure.tools.logging/warnf"}
  {:raw-source-url
   "https://github.com/clojure/tools.logging/raw/67d2fa8411e1f226bf1e77f3e5f6326a00e5b954/src/main/clojure/clojure/tools/logging.clj",
   :name "with-logs",
   :file "src/main/clojure/clojure/tools/logging.clj",
   :source-url
   "https://github.com/clojure/tools.logging/blob/67d2fa8411e1f226bf1e77f3e5f6326a00e5b954/src/main/clojure/clojure/tools/logging.clj#L193",
   :line 193,
   :var-type "macro",
   :arglists
   ([logger-ns & body] [[logger-ns out-level err-level] & body]),
   :doc
   "Evaluates exprs in a context in which *out* and *err* write to the log. The\nspecified logger-ns value will be used to namespace all log entries.\n\nBy default *out* and *err* write to :info and :error, respectively.",
   :namespace "clojure.tools.logging",
   :wiki-url
   "http://clojure.github.io/tools.logging//index.html#clojure.tools.logging/with-logs"}
  {:raw-source-url
   "https://github.com/clojure/tools.logging/raw/26d5ebb12cf8b029644161faad9af2e5412efe94/src/main/clojure/clojure/tools/logging/impl.clj",
   :name "cl-factory",
   :file "src/main/clojure/clojure/tools/logging/impl.clj",
   :source-url
   "https://github.com/clojure/tools.logging/blob/26d5ebb12cf8b029644161faad9af2e5412efe94/src/main/clojure/clojure/tools/logging/impl.clj#L94",
   :line 94,
   :var-type "function",
   :arglists ([]),
   :doc
   "Returns a Commons Logging-based implementation of the LoggerFactory protocol, or\nnil if not available.",
   :namespace "clojure.tools.logging.impl",
   :wiki-url
   "http://clojure.github.io/tools.logging//index.html#clojure.tools.logging.impl/cl-factory"}
  {:raw-source-url
   "https://github.com/clojure/tools.logging/raw/26d5ebb12cf8b029644161faad9af2e5412efe94/src/main/clojure/clojure/tools/logging/impl.clj",
   :name "disabled-logger",
   :file "src/main/clojure/clojure/tools/logging/impl.clj",
   :source-url
   "https://github.com/clojure/tools.logging/blob/26d5ebb12cf8b029644161faad9af2e5412efe94/src/main/clojure/clojure/tools/logging/impl.clj#L35",
   :line 35,
   :var-type "var",
   :arglists nil,
   :doc "A Logger that is not enabled and does nothing on write.",
   :namespace "clojure.tools.logging.impl",
   :wiki-url
   "http://clojure.github.io/tools.logging//index.html#clojure.tools.logging.impl/disabled-logger"}
  {:raw-source-url
   "https://github.com/clojure/tools.logging/raw/26d5ebb12cf8b029644161faad9af2e5412efe94/src/main/clojure/clojure/tools/logging/impl.clj",
   :name "disabled-logger-factory",
   :file "src/main/clojure/clojure/tools/logging/impl.clj",
   :source-url
   "https://github.com/clojure/tools.logging/blob/26d5ebb12cf8b029644161faad9af2e5412efe94/src/main/clojure/clojure/tools/logging/impl.clj#L41",
   :line 41,
   :var-type "var",
   :arglists nil,
   :doc "A LoggerFactory that always provides the disabled-logger.",
   :namespace "clojure.tools.logging.impl",
   :wiki-url
   "http://clojure.github.io/tools.logging//index.html#clojure.tools.logging.impl/disabled-logger-factory"}
  {:raw-source-url
   "https://github.com/clojure/tools.logging/raw/26d5ebb12cf8b029644161faad9af2e5412efe94/src/main/clojure/clojure/tools/logging/impl.clj",
   :name "find-factory",
   :file "src/main/clojure/clojure/tools/logging/impl.clj",
   :source-url
   "https://github.com/clojure/tools.logging/blob/26d5ebb12cf8b029644161faad9af2e5412efe94/src/main/clojure/clojure/tools/logging/impl.clj#L214",
   :line 214,
   :var-type "function",
   :arglists ([]),
   :doc
   "Returns the first non-nil value from slf4j-factory, cl-factory,\nlog4j-factory, and jul-factory.",
   :namespace "clojure.tools.logging.impl",
   :wiki-url
   "http://clojure.github.io/tools.logging//index.html#clojure.tools.logging.impl/find-factory"}
  {:raw-source-url
   "https://github.com/clojure/tools.logging/raw/26d5ebb12cf8b029644161faad9af2e5412efe94/src/main/clojure/clojure/tools/logging/impl.clj",
   :name "jul-factory",
   :file "src/main/clojure/clojure/tools/logging/impl.clj",
   :source-url
   "https://github.com/clojure/tools.logging/blob/26d5ebb12cf8b029644161faad9af2e5412efe94/src/main/clojure/clojure/tools/logging/impl.clj#L176",
   :line 176,
   :var-type "function",
   :arglists ([]),
   :doc
   "Returns a java.util.logging-based implementation of the LoggerFactory protocol,\nor nil if not available.",
   :namespace "clojure.tools.logging.impl",
   :wiki-url
   "http://clojure.github.io/tools.logging//index.html#clojure.tools.logging.impl/jul-factory"}
  {:raw-source-url
   "https://github.com/clojure/tools.logging/raw/26d5ebb12cf8b029644161faad9af2e5412efe94/src/main/clojure/clojure/tools/logging/impl.clj",
   :name "log4j-factory",
   :file "src/main/clojure/clojure/tools/logging/impl.clj",
   :source-url
   "https://github.com/clojure/tools.logging/blob/26d5ebb12cf8b029644161faad9af2e5412efe94/src/main/clojure/clojure/tools/logging/impl.clj#L140",
   :line 140,
   :var-type "function",
   :arglists ([]),
   :doc
   "Returns a Log4j-based implementation of the LoggerFactory protocol, or nil if\nnot available.",
   :namespace "clojure.tools.logging.impl",
   :wiki-url
   "http://clojure.github.io/tools.logging//index.html#clojure.tools.logging.impl/log4j-factory"}
  {:raw-source-url
   "https://github.com/clojure/tools.logging/raw/26d5ebb12cf8b029644161faad9af2e5412efe94/src/main/clojure/clojure/tools/logging/impl.clj",
   :name "slf4j-factory",
   :file "src/main/clojure/clojure/tools/logging/impl.clj",
   :source-url
   "https://github.com/clojure/tools.logging/blob/26d5ebb12cf8b029644161faad9af2e5412efe94/src/main/clojure/clojure/tools/logging/impl.clj#L47",
   :line 47,
   :var-type "function",
   :arglists ([]),
   :doc
   "Returns a SLF4J-based implementation of the LoggerFactory protocol, or nil if\nnot available.",
   :namespace "clojure.tools.logging.impl",
   :wiki-url
   "http://clojure.github.io/tools.logging//index.html#clojure.tools.logging.impl/slf4j-factory"}
  {:raw-source-url
   "https://github.com/clojure/tools.logging/raw/26d5ebb12cf8b029644161faad9af2e5412efe94/src/main/clojure/clojure/tools/logging/impl.clj",
   :name "Logger",
   :file "src/main/clojure/clojure/tools/logging/impl.clj",
   :source-url
   "https://github.com/clojure/tools.logging/blob/26d5ebb12cf8b029644161faad9af2e5412efe94/src/main/clojure/clojure/tools/logging/impl.clj#L16",
   :line 16,
   :var-type "protocol",
   :arglists nil,
   :doc
   "The protocol through which the core api will interact with an underlying logging\nimplementation.  Implementations should at least support the six standard\nlogging levels if they wish to work from the level-specific macros.",
   :namespace "clojure.tools.logging.impl",
   :wiki-url
   "http://clojure.github.io/tools.logging//index.html#clojure.tools.logging.impl/Logger"}
  {:raw-source-url
   "https://github.com/clojure/tools.logging/raw/26d5ebb12cf8b029644161faad9af2e5412efe94/src/main/clojure/clojure/tools/logging/impl.clj",
   :name "LoggerFactory",
   :file "src/main/clojure/clojure/tools/logging/impl.clj",
   :source-url
   "https://github.com/clojure/tools.logging/blob/26d5ebb12cf8b029644161faad9af2e5412efe94/src/main/clojure/clojure/tools/logging/impl.clj#L25",
   :line 25,
   :var-type "protocol",
   :arglists nil,
   :doc
   "The protocol through which the core api will obtain an instance satisfying Logger\nas well as providing information about the particular implementation being used.\nImplementations should be bound to *logger-factory* in order to be picked up by\nthis library.",
   :namespace "clojure.tools.logging.impl",
   :wiki-url
   "http://clojure.github.io/tools.logging//index.html#clojure.tools.logging.impl/LoggerFactory"}
  {:name "enabled?",
   :doc "Check if a particular level is enabled for the given Logger.",
   :var-type "function",
   :namespace "clojure.tools.logging.impl",
   :arglists ([logger level]),
   :wiki-url
   "http://clojure.github.io/tools.logging//index.html#clojure.tools.logging.impl/enabled?",
   :source-url nil,
   :raw-source-url nil,
   :file nil}
  {:name "write!",
   :doc "Writes a log message to the given Logger.",
   :var-type "function",
   :namespace "clojure.tools.logging.impl",
   :arglists ([logger level throwable message]),
   :wiki-url
   "http://clojure.github.io/tools.logging//index.html#clojure.tools.logging.impl/write!",
   :source-url nil,
   :raw-source-url nil,
   :file nil}
  {:name "get-logger",
   :doc "Returns an implementation-specific Logger by namespace.",
   :var-type "function",
   :namespace "clojure.tools.logging.impl",
   :arglists ([factory logger-ns]),
   :wiki-url
   "http://clojure.github.io/tools.logging//index.html#clojure.tools.logging.impl/get-logger",
   :source-url nil,
   :raw-source-url nil,
   :file nil}
  {:name "name",
   :doc "Returns some text identifying the underlying implementation.",
   :var-type "function",
   :namespace "clojure.tools.logging.impl",
   :arglists ([factory]),
   :wiki-url
   "http://clojure.github.io/tools.logging//index.html#clojure.tools.logging.impl/name",
   :source-url nil,
   :raw-source-url nil,
   :file nil})}
