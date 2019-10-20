(ns utility-belt.conv-test
  (:require [clojure.test :refer :all]
            [utility-belt.conv :refer :all]))

(deftest simple-conversions
  (testing "simple conversions"
    (is (= 1
           (str->int "1")))

    (is (= [1 2 3]
           (ensure-vector '(1 2 3))))))
