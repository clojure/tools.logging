# Logging

Logging macros which delegate to a specific logging implementation. At runtime a specific implementation is selected from, in order, Apache commons-logging, slf4j, log4j, and finally java.util.logging.  Logging levels are specified by clojure keywords corresponding to the values used in log4j and commons-logging:

    :trace, :debug, :info, :warn, :error, :fatal

Logging occurs with the log macro, or the level-specific convenience macros, which write either directly or via an agent.  The log macros will not evaluate their 'message' unless the specific logging level is in effect. Alternately, you can use the spy macro when you have code that needs to be evaluated, and also want to output the code and its result to the log.  Unless otherwise specified, the current namespace (as identified by *ns*) will be used as the log-ns (similar to how the java class name is usually used).  Note: your log configuration should display the name that was passed to the logging implementation, and not perform stack-inspection, otherwise you'll see some ugly and unhelpful text in your logs.  Use the enabled? macro to write conditional code against the logging level (beyond simply whether or not to call log, which is handled automatically).  You can redirect all java writes of System.out and System.err to the log system by calling log-capture!.  To bind *out* and *err* to the log system invoke with-logs.  In both cases a log-ns (e.g., \"com.example.captured\") must be specified in order to namespace the output.

## Usage

The following short example should give you what you need to get started:

    (ns example.core
      (:use [clojure.tools.logging :only (info error)]))

    (defn divide [one two]
      (try
        (info "Calculated" (/ one two))
        (catch Exception ex
          (error "There was an error in caculation" ex))))

For those new to using a java logging library, the following is a very basic configuration for log4j. Place it in a file called \"log4j.properties\" and place that file (and the log4j JAR) on the classpath.

    log4j.rootLogger=WARN, A1
    log4j.logger.user=DEBUG
    log4j.appender.A1=org.apache.log4j.ConsoleAppender
    log4j.appender.A1.layout=org.apache.log4j.PatternLayout
    log4j.appender.A1.layout.ConversionPattern=%d %-5p %c: %m%n

The above will print messages to the console for :debug or higher if one is in the user namespace, and :warn or higher in all other namespaces.

### "Installation"

Logging is available in Maven central.  Add it to your Maven project's `pom.xml`:

    <dependency>
      <groupId>org.clojure</groupId>
      <artifactId>tools.logging</artifactId>
      <version>0.1.0</version>
    </dependency>

or your leiningen project.clj:

    [org.clojure/tools.logging "0.1.0"]

Please note the changelog below.

### Building Logging

0. Clone the repo
1. Make sure you have maven installed
2. Run the maven build; run either:
    1. `mvn package`: This will produce a logging jar file in the `target`
directory, and run all tests with the most recently-released build
of Clojure (currently 1.2.0). Or,
    2. `mvn verify`: This does the same, but also runs the tests with
other Clojure "profiles" (currently v1.1.0 and v1.1.0 + clojure-contrib). 

## Thanks

* Chris Dean
* Phil Hagelberg
* Richard Newman
* Timothy Pratley

## License

Copyright © 2009 Alex Taggart

Licensed under the EPL. (See the file epl.html.)
