(ns utility-belt.conv)


;; Deprecated: clojure.core/parse-long
(defn ^{:Deprecated "1.3.3"} str->int
  [s]
  (if (string? s)
    (Integer/parseInt s)
    s))


(defn ensure-vector
  [data]
  (-> data vector flatten vec))
