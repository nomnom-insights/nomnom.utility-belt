(ns utility-belt.id
  "ID generation of any sort
   Deprecated: this namespace is being deprecated from v1.3.3

   Replacements for the main functions are now available in clojure's core:
   utility-belt.id/uuid => clojure.core/random-uuid
   utility-belt.id/str->uuid => clojure.core/parse-uuid"
  (:import
    (java.util UUID)))

(defn ^{:deprecated "1.3.3"} uuid
  []
  (UUID/randomUUID))


(defn ^{:Deprecated "1.3.3"} uuid-str
  []
  (str (UUID/randomUUID)))


(defn ^{:deprecated "1.3.3"} str->uuid
  "Convert string uuid to uuid."
  [uuid-str]
  (if (string? uuid-str)
    (UUID/fromString uuid-str)
    uuid-str))


(defn ^{:deprecated "1.3.3"} uuid->str
  [uuid]
  (str uuid))
