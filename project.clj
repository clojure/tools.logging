(defproject org.clojure/tools.logging "0.3.2-SNAPSHOT"
  :description "Clojure logging API."
  :url "https://github.com/clojure/tools.logging"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :source-paths ["src/main/clojure"]
  :test-paths ["src/test/clojure"]
  :dependencies [[org.clojure/clojure "1.3.0"]]
  :profiles {:test {:dependencies [[org.slf4j/slf4j-api "1.6.2"]
                                   [org.slf4j/slf4j-log4j12 "1.6.2"]
                                   [log4j "1.2.16"]
                                   [commons-logging "1.1.1"]]}})
