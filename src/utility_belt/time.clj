(ns utility-belt.time
  (:require
    [clj-time.coerce :as coerce]
    [clj-time.core :as time])
  (:import
    (java.util
      TimeZone)
    (org.joda.time
      DateTime
      DateTimeZone)))


(defn force-utc! []
  (TimeZone/setDefault (TimeZone/getTimeZone "UTC")))


(defn now []
  (DateTime.))


(defn to-date [date]
  (DateTime. date))


(defmacro with-utc-timezone
  "Evaluate code using UTC timezone"
  [& body]
  `(let [tz# (DateTimeZone/getDefault)]
     (try
       (DateTimeZone/setDefault (DateTimeZone/UTC))
       ~@body
       (finally
         (DateTimeZone/setDefault tz#)))))


(defn to-sql-time
  "Return sql date for given datetime millis"
  [date]
  (coerce/to-sql-time date))


(defn from-sql-date
  "Return datetime object for given sql date"
  [sql-date]
  (coerce/from-sql-date sql-date))


(defn to-millis
  "Convert date string to milliseconds"
  [^String date]
  (coerce/to-long date))


(defn to-seconds
  "Convert date string to seconds"
  [^String date]
  (-> date to-millis (quot 1000)))


(def utf-zero
  (to-date 0))


(defn ->date-time [s]
  (cond
    (instance? DateTime s) s
    (string? s) (coerce/to-date-time s)))


(defn end-of-day-time
  "Return end of day for given date string/datetime.
   If in future return now!"
  [date]
  (let [datetime (->date-time date)
        start-date (time/with-time-at-start-of-day datetime)
        end-day (-> start-date
                    (time/plus (time/days 1))
                    (time/minus (time/millis 1)))
        now (now)]
    ;; ensure end-day is not in future
    (if (time/before? now end-day)
      now
      end-day)))
