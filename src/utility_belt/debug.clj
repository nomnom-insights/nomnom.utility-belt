(ns utility-belt.debug
  (:require
    [clojure.tools.logging :as log]
    [utility-belt.time :as time]))


(defmacro time+
  "Similar to `time` macro, but prints before/after markers and associated tag"
  [tag body]
  `(let [state# (atom nil)]
     (printf "[%s]:start %s\n"  ~tag (time/now))
     (printf "[%s] %s" ~tag (with-out-str
                              (time (reset! state# (do ~body)))))
     (printf "[%s]:end %s\n"  ~tag (time/now))
     (deref state#)))


(defmacro log-time
  "Like time+ but uses underlying logger facility."
  [tag body]
  `(let [state# (atom nil)]
     (log/debugf "tag=%s start"  ~tag)
     (let [elapsed#  (with-out-str
                       (time (reset! state# (do ~body))))]
       (log/debugf "tag=%s %s" ~tag elapsed#))
     (log/debugf "tag=%s end"  ~tag)
     (deref state#)))
