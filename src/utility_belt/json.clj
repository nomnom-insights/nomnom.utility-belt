(ns utility-belt.json
  (:require
    [cheshire.generate :as json.generate]
    [clj-time.coerce :as coerce]
    [clj-time.jdbc])
  (:import
    (com.fasterxml.jackson.core.json
      WriterBasedJsonGenerator)
    (org.joda.time
      DateTime)))


(json.generate/add-encoder
  DateTime
  (fn [data jsonGenerator]
    (.writeString ^WriterBasedJsonGenerator jsonGenerator ^String (coerce/to-string ^DateTime data))))
