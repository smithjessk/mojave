(defproject mojave "0.1.0-SNAPSHOT"
  :description "Be informed about how you spend your day"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [
    [org.clojure/clojure "1.8.0"]
    [com.draines/postal "2.0.2"]
    [org.clojure/data.json "0.2.6"]
  ]
  :main ^:skip-aot mojave.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
