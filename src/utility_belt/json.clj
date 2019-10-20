(ns utility-belt.json
  (:require [cheshire.generate :as json.generate]
            [clj-time.jdbc]
            [clj-time.coerce :as coerce])
  (:import
   (org.joda.time DateTime)
   (com.fasterxml.jackson.core.json WriterBasedJsonGenerator)))

(json.generate/add-encoder
 DateTime
 (fn [data jsonGenerator]
   (.writeString ^WriterBasedJsonGenerator jsonGenerator ^String (coerce/to-string ^DateTime data))))
