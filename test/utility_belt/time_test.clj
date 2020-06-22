(ns utility-belt.time-test
  (:require
    [clj-time.core :as time]
    [clojure.test :refer [deftest is testing]]
    [utility-belt.time :as t])
  (:import
    (java.sql
      Timestamp)))


(deftest sql-data-test
  (testing "convert to/form sql date")
  (let [date (t/now)
        sql-date (t/to-sql-time date)]
    (is (instance? Timestamp sql-date))
    (is (time/equal? date (t/from-sql-date sql-date)))))


(deftest to-seconds-test
  (testing "convert datetime string to UTF seconds"
    (is (= 3601 (t/to-seconds "1970-01-01 01:00:01")))))


(deftest end-of-day-time-test
  (with-redefs [t/now (fn [] (t/->date-time "2016-12-31T00:01"))]
    (testing "end of day in future"
      (is (= (t/->date-time "2016-12-31T00:01") (t/end-of-day-time "2016-12-31"))))
    (testing "end of day in past"
      (is (= (t/->date-time "2016-01-01T23:59:59.999") (t/end-of-day-time "2016-01-01")))
      (is (= (t/->date-time "2016-01-01T23:59:59.999") (t/end-of-day-time "2016-01-01T00:01:01"))))))
