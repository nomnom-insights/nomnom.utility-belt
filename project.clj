(defproject nomnom/utility-belt "1.3.3"
  :description "Some of the tools you'll ever need to fight crime and write Clojure stuffs"
  :url "https://github.com/nomnom-insights/nomnom.utility-belt"
  :deploy-repositories {"clojars" {:sign-releases false
                                   :username :env/clojars_username
                                   :password :env/clojars_password}}

  :dependencies [[org.clojure/clojure "1.10.3"]
                 [nrepl "0.8.3"]]

  :global-vars {*warn-on-reflection* true}

  :profiles {:dev
             {:dependencies [[cheshire "5.10.1"]
                             [com.stuartsierra/component "1.0.0"]
                             [org.clojure/tools.logging "1.1.0"]
                             [ch.qos.logback/logback-classic "1.2.7"]
                             [org.clojure/java.jdbc "0.7.12"] ; used for time coercions
                             [clj-time "0.15.2"]]}})
