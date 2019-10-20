(ns utility-belt.id
  "ID generation of any sort"
  (:import (java.util UUID)))

(defn uuid
  []
  (UUID/randomUUID))

(defn uuid-str []
  (str (UUID/randomUUID)))

(defn str->uuid
  "Convert string uuid to uuid."
  [uuid-str]
  (if (string? uuid-str)
    (UUID/fromString uuid-str)
    uuid-str))

(defn uuid->str
  [uuid]
  (str uuid))
