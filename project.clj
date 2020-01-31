(defproject nomnom/utility-belt "1.2.2"
  :description "Some of the tools you'll ever need to fight crime and write Clojure stuffs"
  :url "https://github.com/nomnom-insights/nomnom.utility-belt"
  :deploy-repositories {"clojars" {:sign-releases false
                                   :username :env/clojars_username
                                   :password :env/clojars_password}}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [nrepl "0.6.0"]]

  :plugins [[lein-cloverage "1.0.13" :exclusions [org.clojure/clojure]]]
  :profiles {:dev
             {:dependencies [[cheshire "5.9.0"]
                             [com.stuartsierra/component "0.4.0"]
                             [org.clojure/tools.logging "0.5.0"]
                             [org.clojure/java.jdbc "0.7.11"] ;; TODO - remove this once, nomnom. utility-belt.sql is stable
                             [clj-time "0.15.2"]]}})
