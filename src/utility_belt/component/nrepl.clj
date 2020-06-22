(ns utility-belt.component.nrepl
  (:require
    [clojure.tools.logging :as log]
    [com.stuartsierra.component :as component]
    [nrepl.server :refer [start-server stop-server]]))


(defrecord ReplServer [port bind-to server]
  component/Lifecycle
  (start [this]
    (log/infof "nrepl-server=start port=%s" port)
    (assoc this :server (start-server :port port :bind bind-to)))
  (stop [this]
    (if (:server this)
      (do
        (log/warn "nrepl-server=stop")
        (stop-server server)
        (assoc this :server nil))
      this)))


(defn create
  ([port]
   {:pre [(number? port)]}
   (->ReplServer port "0.0.0.0" nil))
  ([bind-to port]
   (->ReplServer port bind-to nil)))
