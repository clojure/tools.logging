(defproject org.clojure/tools.logging "1.1.1-SNAPSHOT"
  :description "Clojure logging API."
  :url "https://github.com/clojure/tools.logging"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :source-paths ["src/main/clojure"]
  :test-paths ["src/test/clojure"]
  :dependencies [[org.clojure/clojure "1.11.4"]]
  :aliases {"cloverage" ["with-profile" "dev,cloverage" "cloverage" "--fail-threshold" "100"]}
  :profiles {:cloverage {:plugins [[lein-cloverage "1.2.4"]]}
             :dev       {:dependencies [[org.clojure/clojure "1.11.4"]
                                        [org.clojure/test.check "1.1.2"]
                                        [org.slf4j/slf4j-api "2.0.17"]
                                        [org.slf4j/slf4j-log4j12 "2.0.17"]
                                        [org.apache.logging.log4j/log4j-api "2.25.3"]
                                        [org.apache.logging.log4j/log4j-core "2.25.3"]
                                        [commons-logging "1.3.5"]
                                        [criterium "0.4.6"]]}})
