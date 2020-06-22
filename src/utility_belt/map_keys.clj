(ns utility-belt.map-keys
  (:require
    [clojure.string :as str]
    [clojure.walk]))


(defn- change-char [k from to]
  (-> k name (str/replace from to)))


(defn to-kebab-case [k]
  (change-char k \_ \-))


(defn to-snake-case [k]
  (change-char k \- \_))

;; Adapted from https://github.com/clojure/clojure/blob/b8c78ebf79b6a996f349dd112aaed658c132735d/src/clj/clojure/walk.clj#L102
(defn convert-keys
  "Recursively transforms all map keys from keywords to *transformation-fn* type strings."
  [transformation-fn m]
  (let [f (fn [[k v]] [(transformation-fn k) v])]
    ;; only apply to maps
    (clojure.walk/postwalk (fn [x] (if (map? x) (into {} (map f x)) x)) m)))


(defn kebabify-keys
  "Converts keys from snake case to kebab case"
  ([m]
   (kebabify-keys m identity))
  ([m post-fn]
   (convert-keys (comp post-fn to-kebab-case) m)))


(defn snakeify-keys
  "Converts keys from kebab case to snake case"
  ([m]
   (snakeify-keys m identity))
  ([m post-fn]
   (convert-keys (comp post-fn to-snake-case) m)))


(defn
  kebabify-keys-kw
  "Like kebabify-keys but keys end up being keywords"
  [m]
  (kebabify-keys m keyword))


(defn
  snakeify-keys-kw
  "Like snakeify-keys but keys end up being keywords"
  [m]
  (snakeify-keys m keyword))
