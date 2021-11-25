(ns utility-belt.debug-test
  (:require
    [clojure.string :as str]
    [clojure.test :refer [deftest is testing]]
    [clojure.tools.logging.test :as log.test]
    [utility-belt.debug :as debug]
    [utility-belt.time]))


(defn slow-fun
  []
  (Thread/sleep 140) :fun-fun-fun)


(deftest time-debugging
  (with-redefs [utility-belt.time/now (fn [] "IT IS NOW")]
    (testing "time+ macro"
      (is (= :fun-fun-fun
             (debug/time+ :test (slow-fun))))

      (let [out (str/split-lines
                  (with-out-str
                    (debug/time+ :test (slow-fun))))]
        (is (= "[:test]:start IT IS NOW" (first out)))
        (is (= "[:test]:end IT IS NOW" (last out)))
        (is (true?
              (not
                (nil?
                  (re-find #".+:test.+Elapsed time.+" (second out))))))))
    (testing "log-time"
      (is (= :fun-fun-fun
             (debug/log-time :info :test (slow-fun))))
      (log.test/with-log
        (debug/log-time :debug :test (slow-fun))
        (log.test/logged? *ns* :debug "tag=:test start")
        (log.test/logged? *ns* :debug "tag=:test end")))))
