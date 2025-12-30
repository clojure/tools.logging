# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](http://keepachangelog.com/en/1.0.0/)
and this project adheres to [Semantic Versioning](http://semver.org/spec/v2.0.0.html)
([despite its flaws](https://www.youtube.com/watch?v=oyLBGkS5ICk)).

## [1.3.1] - 2025-12-30

### Changed

- Bumped parent pom to 1.4.0
- Bumped deps to Clojure 1.11.4
- Bumped test deps to latest

## [1.3.0] - 2024-02-06

### Changed
- TLOG-28 - Moved slf4j test deps to 2.0.6
- Bumped Clojure dep to 1.10.3

## [1.2.4] - 2022-01-04

### Changed
- Bumped log4j test deps to 2.17.1

## [1.2.3] - 2021-12-18

### Changed
- Bumped log4j test deps to 2.17.0

## [1.2.2] - 2021-12-14

### Changed
- Bumped log4j test deps to 2.16.0

## [1.2.1] - 2021-12-10

### Changed
- Bumped all testing deps to latest (particularly log4j)

## [1.2.0] - 2021-12-10

### Changed
- Bumped all testing deps to latest (particularly log4j)

## [1.1.0] - 2020-04-25
### Added 
- Add implementation of `spyf` to `clojure.tools.logging.readable`.

### Changed
- Decreased the per-call overhead when using SLF4J, Commons Logging, and Log4j2.
  Previously, their associated `logger-factory` implementations were calling
  library-specific static convenience methods for each call to `get-logger`.
  These methods have some baked-in setup overhead, which now only occurs during
  instantiation of the associated `logger-factory`.

## [1.0.0] - 2020-02-27
### Added
- Add support for printing message arguments readably in
  [`clojure.tools.logging.readable`](https://clojure.github.io/tools.logging/#clojure.tools.logging.readable).

## [0.6.0] - 2020-02-07
### Added
- Add support for explicitly selecting a logger factory by setting the
 `"clojure.tools.logging.factory"` system property. For details, see the
 [README](https://github.com/clojure/tools.logging#configuration),
 and the documentation for [\*logger-factory\*](https://clojure.github.io/tools.logging/#clojure.tools.logging/*logger-factory*).

## [0.5.0] - 2019-07-22
### Added
- Add support for testing logs in [`clojure.tools.logging.test`](https://clojure.github.io/tools.logging/#clojure.tools.logging.test).

### Changed
- Now passes the context classloader of current thread to `Class/forName` when
  determining whether logging implementation classes are available on the
  classpath. This was done to allow testing of the various `impl/*-factory`
  functions, and seems consistent with clojure internals.

## [0.4.1] - 2018-05-07
### Fixed
- Fix inadvertent reflection when using log4j2.

## [0.4.0] - 2017-06-04
### Added
- Allow arbitrary values for `level` in logging adapters.
- Add support for log4j2.

## [0.3.1] - 2014-09-20
### Fixed
- Change ns forms so their subforms are contained in parens not [].

## [0.3.0] - 2014-06-07
### Added
- Provide disabled instances of `Logger` and `LoggerFactory`.
- Add `spyf`, like `spy` but takes a format string for the result.

### Fixed
- Fix typos in recent README changes.
- Update outdated unreleased diff link.

## [0.2.6] - 2013-02-04
### Fixed
- Avoid double evaluation of `message` arg to `logf` and `logp`.

## [0.2.4] - 2012-07-09
### Fixed
- Avoid reflection in logging adapters.

## [0.2.3] - 2011-09-29
### Fixed
- Fix broken protocol extension due to incompatibility between syntax-quote and
  extend-type.

## [0.2.2] - 2011-09-28
### Changed
- Auto-detecting logging implementation now prefers SLF4J over Commons Logging.
- Move logging implementation code into `clojure.tools.logging.impl`.

## [0.2.0] - 2011-07-19
### Changed
- Changed naming from "log" to "logger".
- Move implementation support to `clojure.tools.logging.impl`.
### Fixed
- Fix newline trimming in `spy` macro.
- Fix improper arity call of `log*` from `logp` and `logf`.

## [0.1.2] - 2011-03-18
### Fixed
- Annotate dynamic vars.

## 0.1.0 - 2011-03-11
### Added
- Initial implementation of Clojure-aware logging abstraction. 

[Unreleased]: https://github.com/clojure/tools.logging/compare/tools.logging-1.1.0...HEAD
[1.3.1]: https://github.com/clojure/tools.logging/compare/v1.3.0...v1.3.1
[1.3.0]: https://github.com/clojure/tools.logging/compare/v1.2.4...v1.3.0
[1.2.4]: https://github.com/clojure/tools.logging/compare/v1.2.3...v1.2.4
[1.2.3]: https://github.com/clojure/tools.logging/compare/v1.2.2...v1.2.3
[1.2.2]: https://github.com/clojure/tools.logging/compare/v1.2.1...v1.2.2
[1.2.1]: https://github.com/clojure/tools.logging/compare/v1.2.0...v1.2.1
[1.2.0]: https://github.com/clojure/tools.logging/compare/tools.logging-1.1.0...v1.2.0
[1.1.0]: https://github.com/clojure/tools.logging/compare/tools.logging-1.0.0...tools.logging-1.1.0
[1.0.0]: https://github.com/clojure/tools.logging/compare/tools.logging-0.6.0...tools.logging-1.0.0
[0.6.0]: https://github.com/clojure/tools.logging/compare/tools.logging-0.5.0...tools.logging-0.6.0
[0.5.0]: https://github.com/clojure/tools.logging/compare/tools.logging-0.4.1...tools.logging-0.5.0
[0.4.1]: https://github.com/clojure/tools.logging/compare/tools.logging-0.4.0...tools.logging-0.4.1
[0.4.0]: https://github.com/clojure/tools.logging/compare/tools.logging-0.3.1...tools.logging-0.4.0
[0.3.1]: https://github.com/clojure/tools.logging/compare/tools.logging-0.3.0...tools.logging-0.3.1
[0.3.0]: https://github.com/clojure/tools.logging/compare/tools.logging-0.2.6...tools.logging-0.3.0
[0.2.6]: https://github.com/clojure/tools.logging/compare/tools.logging-0.2.4...tools.logging-0.2.6
[0.2.4]: https://github.com/clojure/tools.logging/compare/tools.logging-0.2.3...tools.logging-0.2.4
[0.2.3]: https://github.com/clojure/tools.logging/compare/tools.logging-0.2.2...tools.logging-0.2.3
[0.2.2]: https://github.com/clojure/tools.logging/compare/tools.logging-0.2.0...tools.logging-0.2.2
[0.2.0]: https://github.com/clojure/tools.logging/compare/tools.logging-0.1.2...tools.logging-0.2.0
[0.1.2]: https://github.com/clojure/tools.logging/compare/tools.logging-0.1.0...tools.logging-0.1.2
