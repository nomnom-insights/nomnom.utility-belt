(ns utility-belt.validation-test
  (:require
    [clojure.test :refer [is deftest testing]]
    [utility-belt.validation :as validation]))


(deftest email-validation-and-extraction
  (testing "it finds valid/invalid adddresses"
    (is (= ["test@example.com"
            nil
            nil
            nil
            "ok.lolling@then.example.com"
            "ok_lolling-1@then.example"
            nil
            "ok_lolling-1+cool-label_ok@then.example.com"]
           (mapv #(re-matches validation/email-pattern %)
                 ["test@example.com"
                  "@example.com"
                  "ok@example."
                  "foo"
                  "ok.lolling@then.example.com"
                  "ok_lolling-1@then.example"
                  "ok_lolling-1+cool-label_ok@then"
                  "ok_lolling-1+cool-label_ok@then.example.com"])))))
