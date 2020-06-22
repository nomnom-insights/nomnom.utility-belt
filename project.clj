(defproject nomnom/utility-belt "1.3.0-SNAPSHOT"
  :description "Some of the tools you'll ever need to fight crime and write Clojure stuffs"
  :url "https://github.com/nomnom-insights/nomnom.utility-belt"
  :deploy-repositories {"clojars" {:sign-releases false
                                   :username :env/clojars_username
                                   :password :env/clojars_password}}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [nrepl "0.7.0"]]

  :plugins [[lein-cloverage "1.0.13" :exclusions [org.clojure/clojure]]]
  :profiles {:dev
             {:dependencies [[cheshire "5.10.0"]
                             [com.stuartsierra/component "1.0.0"]
                             [org.clojure/tools.logging "1.1.0"]
                             [ch.qos.logback/logback-classic "1.2.3"]
                             [clj-time "0.15.2"]]}})
