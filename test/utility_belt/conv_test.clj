(ns utility-belt.conv-test
  (:require
    [clojure.test :refer [deftest is testing]]
    [utility-belt.conv :as conv]))


(deftest simple-conversions
  (testing "simple conversions"
    (is (= 1
           (conv/str->int "1")))

    (is (= [1 2 3]
           (conv/ensure-vector '(1 2 3))))))
