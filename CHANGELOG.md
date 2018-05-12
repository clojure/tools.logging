# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](http://keepachangelog.com/en/1.0.0/)
and this project adheres to [Semantic Versioning](http://semver.org/spec/v2.0.0.html)
([despite its flaws](https://www.youtube.com/watch?v=oyLBGkS5ICk)).

## [Unreleased]
Fix the problem of missing quotes caused by print-str
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

[Unreleased]: https://github.com/clojure/tools.logging/compare/tools.logging-0.4.1...HEAD
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
