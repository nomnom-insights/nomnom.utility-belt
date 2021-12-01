(ns utility-belt.lifecycle
  (:require
    [clojure.tools.logging :as log]))


(def hooks (atom {:shutdown-agents shutdown-agents}))


(defn register-shutdown-hook
  "Add a function to run when the application *gracefully* shuts down.
  Useful for stopping the Component system or long running threads"
  [name hook-fn]
  (log/infof "registered hook %s" name)
  (swap! hooks assoc name hook-fn))


(defn run-registered-hooks
  []
  (mapv (fn [[name hook-fn]]
          (try
            (log/infof "shutdown-hook=%s" name)
            (hook-fn)
            (catch Exception err
              (log/errorf err "shutdown hook=%s failed" name))))
        @hooks))


(defn install-shutdown-hooks!
  "Install the shutdown handler, which will run any registered shutdown hooks."
  []
  (.addShutdownHook (Runtime/getRuntime)
                    (Thread. ^Runnable run-registered-hooks)))
