(ns utility-belt.debug-test
  (:require
    [clojure.test :refer [deftest is testing]]
    [utility-belt.debug :as debug]
    [clojure.string :as str]
    [utility-belt.time]))


(defn slow-fun [] (Thread/sleep 140) :fun-fun-fun)


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
                (re-find #".+:test.+Elapsed time.+" (second out))))))))))
