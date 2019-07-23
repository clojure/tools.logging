# Logging

Logging macros which delegate to a specific logging implementation. At runtime a specific implementation is selected from, in order, slf4j, Apache commons-logging, log4j2, log4j, and finally java.util.logging.

Logging levels are specified by clojure keywords, in increasingly severe order:

```clojure
:trace, :debug, :info, :warn, :error, :fatal
```

Logging occurs with the `log` macro, or the level-specific convenience macros (e.g., `debug`, `debugf`). Only when the specified logging level is enabled will the message arguments be evaluated and the underlying logging implementation be invoked. By default that invocation will occur via an agent when inside a running STM transaction.

Unless otherwise specified, the current namespace (as identified by `*ns*`) will be used as the log-ns (similar to how the java class name is usually used).  Note: you should configure your logging implementation to display the name that was passed to it; if it instead performs stack-inspection you'll see some ugly and unhelpful text in your logs.

You can redirect all java writes of `System.out` and `System.err` to the log system by calling `log-capture!`.  To bind `*out*` and `*err*` to the log system invoke `with-logs`.  In both cases a log-ns value must be specified in order to namespace the output.

## Usage

The latest API documentation can be found at http://clojure.github.com/tools.logging

The following short example should give you what you need to get started:

```clojure
(ns example.math
  (:require [clojure.tools.logging :as log]))

(defn divide [x y]
  (log/info "dividing" x "by" y)
  (try
    (log/spyf "result is %s" (/ x y)) ; yields the result
    (catch Exception ex
      (log/error ex "There was an error in calculation"))))
```

Example repl output using the configuration below:

```
user=> (use 'my.example)
nil
user=> (divide 1 2)
INFO  example.math: dividing 1 by 2
DEBUG example.math: result is 1/2
1/2
user=> (divide 2 0)
INFO  example.math: dividing 2 by 0
ERROR example.math: There was an error in calculation
java.lang.ArithmeticException: Divide by zero
	at clojure.lang.Numbers.divide(Numbers.java:156)
    ...
```

For those new to using a java logging library, the following is a very basic configuration for log4j. Place it in a file called `log4j.properties` and place that file (and the log4j JAR) on the classpath.

```
log4j.rootLogger=INFO, console
log4j.logger.example=DEBUG
log4j.appender.console=org.apache.log4j.ConsoleAppender
log4j.appender.console.layout=org.apache.log4j.PatternLayout
log4j.appender.console.layout.ConversionPattern=%-5p %c: %m%n
```

The above will print messages to the console for `:debug` or higher if one is in the `example` namespace, and `:info` or higher in all other namespaces.

### Installation

Logging is available in Maven central.  Add it to your Maven project's `pom.xml`:

```xml
<dependency>
  <groupId>org.clojure</groupId>
  <artifactId>tools.logging</artifactId>
  <version>0.5.0</version>
</dependency>
```

or your leiningen project.clj:

```clojure
[org.clojure/tools.logging "0.5.0"]
```

Please note the changelog below.

### Building Logging

0. Clone the repo
1. Make sure you have maven installed
2. Run the maven build; run either:
    1. `mvn install`: This will produce a logging jar file in the `target`
directory, and run all tests with the most recently-released build
of Clojure.

## Thanks

* Chris Dean
* Phil Hagelberg
* Richard Newman
* Timothy Pratley

## License

Copyright © 2009 Alex Taggart

Licensed under the EPL. (See the file epl.html.)
