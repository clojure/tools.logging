(ns clojure.tools.logging.test-impl
  (:use clojure.test)
  (:require [clojure.tools.logging.impl :as impl]))


(deftest test-disabled-logger-factory
  (is (= "disabled" (impl/name impl/disabled-logger-factory)))
  (is (identical? impl/disabled-logger (impl/get-logger impl/disabled-logger-factory nil))))

(deftest test-disabled-logger
  (is (= false (impl/enabled? impl/disabled-logger :fatal))))

(defn blacklist-loader [blacklist loader]
  (proxy [ClassLoader] []
    (loadClass [name]
      (if (contains? (set blacklist) name)
        (throw (ClassNotFoundException. name))
        (.loadClass loader name)))))

(deftest test-blacklist-loader
  (let [white            "org.slf4j.Logger"
        black-1          "java.util.logging.Logger"
        black-2          "org.apache.log4j.Logger"
        orig-loader      (.. Thread currentThread getContextClassLoader)
        blacklist-loader (blacklist-loader #{black-1 black-2} orig-loader)]
    (Class/forName white true orig-loader)
    (Class/forName black-1 true orig-loader)
    (Class/forName black-2 true orig-loader)
    (Class/forName white true blacklist-loader)
    (is (thrown? ClassNotFoundException
           (Class/forName black-1 true blacklist-loader)))
    (is (thrown? ClassNotFoundException
           (Class/forName black-2 true blacklist-loader)))))

(defmacro with-blacklist-loader [forbidden-set & body]
  `(let [forbidden-set# ~forbidden-set
         orig-loader# (.. Thread currentThread getContextClassLoader)
         this-loader# (blacklist-loader forbidden-set# orig-loader#)]
     (try
       (.. Thread currentThread (setContextClassLoader this-loader#))
       ~@body
       (finally
         (.. Thread currentThread (setContextClassLoader orig-loader#))))))

(defn thread-loader []
  (.. Thread currentThread getContextClassLoader))

(deftest test-with-blacklist-loader
  (with-blacklist-loader #{"org.apache.log4j.Logger"}
    (Class/forName "org.slf4j.Logger" true (thread-loader))
    (is (thrown? ClassNotFoundException
          (Class/forName "org.apache.log4j.Logger" true (thread-loader)))))
  (Class/forName "org.apache.log4j.Logger" true (thread-loader)))


(deftest test-class-found?
  (is (= true (impl/class-found? "org.apache.log4j.Logger")))
  (is (= true (impl/class-found? "org.slf4j.Logger")))
  (with-blacklist-loader #{"org.apache.log4j.Logger"}
    (is (= true (impl/class-found? "org.slf4j.Logger")))
    (is (= false (impl/class-found? "org.apache.log4j.Logger")))))


(deftest test-find-factory
  (testing "has all loggers"
    (are [classname] (impl/class-found? classname)
         "org.slf4j.Logger"
         "org.apache.commons.logging.Log"
         "org.apache.logging.log4j.Logger"
         "org.apache.log4j.Logger"
         "java.util.logging.Logger"))
  
  (testing "finds slf4j"
    (with-blacklist-loader []
      (is (= "org.slf4j" (impl/name (impl/find-factory))))))
  
  (testing "finds cl"
    (with-blacklist-loader ["org.slf4j.Logger"]
      (is (= "org.apache.commons.logging" (impl/name (impl/find-factory))))))
  
  (testing "finds log4j2"
    (with-blacklist-loader ["org.slf4j.Logger"
                            "org.apache.commons.logging.Log"]
      (is (= "org.apache.logging.log4j" (impl/name (impl/find-factory))))))
  
  (testing "finds log4j"
    (with-blacklist-loader ["org.slf4j.Logger"
                            "org.apache.commons.logging.Log"
                            "org.apache.logging.log4j.Logger"]
      (is (= "org.apache.log4j" (impl/name (impl/find-factory))))))
  
  (testing "finds jul"
    (with-blacklist-loader ["org.slf4j.Logger"
                            "org.apache.commons.logging.Log"
                            "org.apache.logging.log4j.Logger"
                            "org.apache.log4j.Logger"]
      (is (= "java.util.logging" (impl/name (impl/find-factory))))))
  
  (testing "finds none"
    (with-blacklist-loader ["org.slf4j.Logger"
                            "org.apache.commons.logging.Log"
                            "org.apache.logging.log4j.Logger"
                            "org.apache.log4j.Logger"
                            "java.util.logging.Logger"]
      (is (thrown? RuntimeException (impl/find-factory))))))


(let [[factory level] (try
                        [(impl/log4j2-factory) (eval 'org.apache.logging.log4j.Level/FATAL)]
                        (catch UnsupportedClassVersionError _
                          ; This will happen when not using a 1.7+ jvm, e.g.,
                          ; during matrix testing. Use the no-op factory
                          ; to get past testing log4j2.
                          [impl/disabled-logger-factory nil]))]
  (def maybe-log4j2-factory factory)
  (def maybe-log4j2-level level))

(def logger-factories [impl/disabled-logger-factory
                       (impl/slf4j-factory)
                       (impl/cl-factory)
                       maybe-log4j2-factory
                       (impl/log4j-factory)
                       (impl/jul-factory)])

(deftest test-logger-factory-impls-satisfy
  (doseq [lf logger-factories]
    (is (satisfies? impl/LoggerFactory lf))))

(deftest test-logger-impls-satisfy
  (doseq [lf logger-factories]
    (is (satisfies? impl/Logger (impl/get-logger lf "logger-ns")))))

(deftest test-enabled-non-kw-levels
  ; Just checking that the call doesn't blow up.
  (are [lf level] (let [logger (impl/get-logger lf "logger-ns")]
                    (impl/enabled? logger level)
                    true)
       maybe-log4j2-factory maybe-log4j2-level
       (impl/log4j-factory) org.apache.log4j.Level/FATAL
       (impl/jul-factory)   java.util.logging.Level/SEVERE))

(deftest test-write-non-kw-levels
  ; Just checking that the call doesn't blow up.
  (are [lf level] (let [logger (impl/get-logger lf "logger-ns")]
                    (impl/write! logger level nil "message")
                    true)
       maybe-log4j2-factory maybe-log4j2-level
       (impl/log4j-factory) org.apache.log4j.Level/FATAL
       (impl/jul-factory)   java.util.logging.Level/SEVERE))
