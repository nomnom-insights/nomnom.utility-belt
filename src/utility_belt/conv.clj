(ns utility-belt.conv)


(defn str->int
  [s]
  (if (string? s)
    (Integer/parseInt s)
    s))


(defn ensure-vector
  [data]
  (-> data vector flatten vec))
