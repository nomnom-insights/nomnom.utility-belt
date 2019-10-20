(ns utility-belt.json-test
  (:require [clojure.test :refer :all]
            [utility-belt.time :as t]
            [cheshire.core :as json]
            [utility-belt.json]))

(deftest allows-json-serialization-of-joda-date-time
  (is (= "{\"time\":\"2019-04-10T12:23:43.000Z\"}"
         (json/generate-string {:time (t/->date-time "2019-04-10 12:23:43")}))))
