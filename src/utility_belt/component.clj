(ns utility-belt.component
  (:require [com.stuartsierra.component]))

(defn deps
  "Converts a mixed list of dependencies into a
  dependency map.
  [:a :b {:c :f} :d] -> { :a :a :b :b :c :f :d :d }
  Useful when a component's dependency list is mostly
  the same but has one or two exceptions which require aliasing"
  [dependencies]
  (into {} (map (fn [x]
                  (if (map? x)
                    (vec (flatten (seq x)))
                    [x x]))
                dependencies)))

(defn using+
  "Like component/using but accepts a mixed list of component dependencies. See +utility-belt.component/deps+.
  (using+ (some-component/create) [ :a :b {:c :d}])"
  [component dependencies-list]
  (com.stuartsierra.component/using
   component
   (deps dependencies-list)))
