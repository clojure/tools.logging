(defproject org.clojure/tools.logging "0.4.666"
  :description "Clojure logging API."
  :url "https://github.com/clojure/tools.logging"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :source-paths ["src/main/clojure"]
  :test-paths ["src/test/clojure"]
  :dependencies [[org.clojure/clojure "1.8.0"]]
  :profiles {:test {:dependencies [[org.slf4j/slf4j-api "1.7.25"]
                                   [org.slf4j/slf4j-log4j12 "1.7.25"]
                                   [log4j "1.2.17"]
                                   [org.apache.logging.log4j/log4j-api "2.11.0"]
                                   [org.apache.logging.log4j/log4j-core "2.11.0"]
                                   [commons-logging "1.2"]]}})