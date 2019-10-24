(defproject nomnom/utility-belt "1.2.0"
  :description "Some of the tools you'll ever need to fight crime and write Clojure stuffs"
  :url "https://github.com/nomnom-insights/nomnom.utility-belt"
  :deploy-repositories {"clojars" {:sign-releases false
                                   :username [:gpg :env/clojars_username]
                                   :password [:gpg :env/clojars_password]}}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [nrepl "0.6.0"]]

  :plugins [[lein-cloverage "1.0.13" :exclusions [org.clojure/clojure]]]
  :profiles {:dev
             {:dependencies [[cheshire "5.8.1"]
                             [com.stuartsierra/component "0.4.0"]
                             [org.clojure/tools.logging "0.5.0"]
                             [org.clojure/java.jdbc "0.7.10"]
                             [clj-time "0.15.1"]]}})
