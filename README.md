# Logging

Logging macros which delegate to a specific logging implementation, selected
at runtime when the `clojure.tools.logging` namespace is first loaded.

## Installation

Lastest stable release is [1.2.4]

[CLI/`deps.edn`](https://clojure.org/reference/deps_and_cli) dependency information:
```clojure
org.clojure/tools.logging {:mvn/version "1.2.4"}
```

Leiningen:

```clojure
[org.clojure/tools.logging "1.2.4"]
```

Maven:

```xml
<dependency>
  <groupId>org.clojure</groupId>
  <artifactId>tools.logging</artifactId>
  <version>1.2.4</version>
</dependency>
```

## Usage

[Latest API Documentation](https://clojure.github.io/tools.logging)

Logging occurs with the `log` macro, or the level-specific convenience macros
(e.g., `debug`, `debugf`). Only when the specified logging level is enabled will
the message arguments be evaluated and the underlying logging implementation be
invoked. By default, that invocation will occur via an agent when inside a
running STM transaction.

### Namespacing of log entries

Unless otherwise specified, the current namespace (as identified by `*ns*`) will
be used as the "logger name" when interacting with logging implementations. Most
logging implementations allow for varying configuration by logger name.

Note: You should configure your logging implementation to display the logger
name that was passed to it. If, instead, the logging implementation performs
stack-inspection you'll see some ugly and unhelpful text in your logs.

### Redirecting output to logs

You can redirect all java writes of `System.out` and `System.err` to the log
system by calling `log-capture!`.  To bind `*out*` and `*err*` to the log system
invoke `with-logs`.  In both cases a logger name must be provided in lieu of
using `*ns*`.

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

### Log4J2

A simple Log4j2 [configuration](https://logging.apache.org/log4j/2.x/manual/configuration.html):

```properties
status = warn
monitorInterval = 5

appender.console.type = Console
appender.console.name = STDOUT
appender.console.layout.type = PatternLayout
appender.console.layout.pattern = %date %level %logger %message%n%throwable

rootLogger.level = info
rootLogger.appenderRef.stdout.ref = STDOUT
```

*Note:* The above [pattern](https://logging.apache.org/log4j/2.x/manual/layouts.html#Patterns)
explicitly uses `%throwable` so that `clojure.lang.ExceptionInfo` exceptions
will be printed with their data maps. If either `%xThrowable` (the default) or
`%rThrowable` is used, the data maps will not be printed.

## FAQ

#### When logging an `ex-info` exception, why isn't the data map printed?

This is likely because the logging implementation is printing the contents of
`Throwable.getMessage()`, which returns just the message arg to `ex-info`.

Logging implementations that print the contents of `toString()` or use `Throwable.printStackTrace(...)`
will end up printing the data map.

## Thanks

* Chris Dean
* Phil Hagelberg
* Richard Newman
* Sean Corfield
* Timothy Pratley

## License

Copyright © 2009-2022 Rich Hickey, Alex Taggart, and contributors

Licensed under the EPL. (See the file epl.html.)


[1.2.4]: https://github.com/clojure/tools.logging/tree/v1.2.4
[Leiningen example]: https://github.com/technomancy/leiningen/blob/master/doc/TUTORIAL.md#setting-jvm-options
[SLF4J]: http://www.slf4j.org/
[Apache Commons Logging]: https://commons.apache.org/logging
[Log4J 2]: https://logging.apache.org/log4j/2.x/
[Log4J]: http://logging.apache.org/log4j/1.2/
[java.util.logging]: https://docs.oracle.com/en/java/javase/13/docs/api/java.logging/java/util/logging/package-summary.html 
