(ns utility-belt.time-test
  (:require
   [utility-belt.time :refer :all]
   [clojure.test :refer :all]
   [clj-time.core :as time])
  (:import (java.sql Timestamp)))

(deftest sql-data-test
  (testing "convert to/form sql date")
  (let [date (now)
        sql-date (to-sql-time date)]
    (is (instance? Timestamp sql-date))
    (is (time/equal? date (from-sql-date sql-date)))))

(deftest to-seconds-test
  (testing "convert datetime string to UTF seconds"
    (is (= 3601 (to-seconds "1970-01-01 01:00:01")))))

(deftest end-of-day-time-test
  (with-redefs [now (fn [] (->date-time "2016-12-31T00:01"))]
    (testing "end of day in future"
      (is (= (->date-time "2016-12-31T00:01") (end-of-day-time "2016-12-31"))))
    (testing "end of day in past"
      (is (= (->date-time "2016-01-01T23:59:59.999") (end-of-day-time "2016-01-01")))
      (is (= (->date-time "2016-01-01T23:59:59.999") (end-of-day-time "2016-01-01T00:01:01"))))))
