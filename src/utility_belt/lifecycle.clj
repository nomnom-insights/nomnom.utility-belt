(ns utility-belt.lifecycle
  (:require [clojure.tools.logging :as log]))

(def ^:private hooks (atom {}))

(defn register-shutdown-hook
  "Add a function to run when the application *gracefully* shuts down.
  Useful for stopping the Component system or long running threads"
  [name hook-fn]
  (swap! hooks assoc name hook-fn))

(defn- run-registerd-hooks []
  (for [[name hook-fn] @hooks]
    (log/infof "shutdown-hook=%s" name (hook-fn))))

(defn install-shutdown-hooks!
  "Install the shutdown handler, which will:
  - run any registerd shutdown hooks
  - call shutdown-agents"
  []
  (.addShutdownHook (Runtime/getRuntime)
                    (Thread. ^Runnable (fn shutdown-hook-runner []
                                         (run-registerd-hooks)
                                         (shutdown-agents)))))
