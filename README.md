# Logging

Logging macros which delegate to a specific logging implementation, selected
at runtime when the `clojure.tools.logging` namespace is first loaded.

## Installation

Lastest stable release is [1.1.0]

Leiningen:

```clojure
[org.clojure/tools.logging "1.1.0"]
```

Maven:

```xml
<dependency>
  <groupId>org.clojure</groupId>
  <artifactId>tools.logging</artifactId>
  <version>1.1.0</version>
</dependency>
```

Gradle:

```clojure
compile "org.clojure:tools.logging:1.1.0"
```


## Usage

[Latest API Documentation](http://clojure.github.com/tools.logging)

Logging occurs with the `log` macro, or the level-specific convenience macros
(e.g., `debug`, `debugf`). Only when the specified logging level is enabled will
the message arguments be evaluated and the underlying logging implementation be
invoked. By default that invocation will occur via an agent when inside a running
STM transaction.

### Namespacing of log entries

Unless otherwise specified, the current namespace (as identified by `*ns*`) will
be used as the log-ns. This value can be emitted in the log entry, and used by most
logging implementations when using namespace-specific logging levels.

Note: You should configure your logging implementation to display the name that
was passed to it. If it instead performs stack-inspection you'll see some ugly
and unhelpful text in your logs.

### Redirecting output to logs

You can redirect all java writes of `System.out` and `System.err` to the log
system by calling `log-capture!`.  To bind `*out*` and `*err*` to the log system
invoke `with-logs`.  In both cases a log-ns value must be specified in order to
namespace the output.

## Configuration

_NOTE: Logging configuration (e.g., setting of logging levels, formatting) is
specific to the underlying logging implementation, and is out of scope for this
library._

### Selecting a logging implementation

To control which logging implementation is used, set the `clojure.tools.logging.factory`
system property to the fully-qualified name of a no-arg function that returns an
instance of `clojure.tools.logging.impl/LoggerFactory`. There are a number of
factory functions provided in the [`clojure.tools.logging.impl`](https://clojure.github.io/tools.logging/#clojure.tools.logging.impl/find-factory)
namespace.

[Leiningen example]:

```clojure
:jvm-opts ["-Dclojure.tools.logging.factory=clojure.tools.logging.impl/slf4j-factory"]
```

If the system property is unset, an implementation will be automatically chosen
based on whichever of the following implementations is successfully loaded first:

1. [SLF4J]
2. [Apache Commons Logging]
3. [Log4J 2]
4. [Log4J]
5. [java.util.logging]

The above approach is problematic given that applications often inadvertently pull
in multiple logging implementations as transitive dependencies. As such, it is
_strongly_ advised that you set the system property.


## Thanks

* Chris Dean
* Phil Hagelberg
* Richard Newman
* Sean Corfield
* Timothy Pratley

## License

Copyright © 2009 Alex Taggart

Licensed under the EPL. (See the file epl.html.)


[1.1.0]: https://github.com/clojure/tools.logging/tree/tools.logging-1.1.0
[Leiningen example]: https://github.com/technomancy/leiningen/blob/master/doc/TUTORIAL.md#setting-jvm-options
[SLF4J]: http://www.slf4j.org/
[Apache Commons Logging]: https://commons.apache.org/logging
[Log4J 2]: https://logging.apache.org/log4j/2.x/
[Log4J]: http://logging.apache.org/log4j/1.2/
[java.util.logging]: https://docs.oracle.com/en/java/javase/13/docs/api/java.logging/java/util/logging/package-summary.html 
