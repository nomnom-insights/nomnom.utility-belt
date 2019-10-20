(defproject nomnom/utility-belt "1.2.0"
  :description "Some of the tools you'll ever need to fight crime and build Clojure services"
  :url "https://github.com/nomnom-insights/nomnom.utility-belt"
  :profiles {:dev
             {:dependencies [[cheshire "5.8.1"]
                             [com.stuartsierra/component "0.4.0"]
                             [org.clojure/tools.logging "0.5.0"]
                             [org.clojure/java.jdbc "0.7.10"]
                             [clj-time "0.15.1"]]}}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [nrepl "0.6.0"]])
