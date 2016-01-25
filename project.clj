(defproject edgewise "0.3.0-SNAPSHOT"
  :description "Edgewise is a simple utility to cleanse, fold, and manipulate networks in Clojure."
  :url "edgewise.testedminds.com"
  :license {:name "Apache License, Version 2.0"
            :url "http://www.apache.org/licenses/LICENSE-2.0.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]]
  :pedantic? :warn
  :profiles {:dev {:plugins [[cider/cider-nrepl "0.10.1"]
                             [lein-gorilla "0.3.5"]]}
             :test {:dependencies [[gorilla-repl "0.3.5"]]}}
  :test-paths ["test" "doc"]
  :test-selectors {:default (fn [m] (not (or (:perf m) (:doc m))))
                   :perf :perf
                   :doc :doc})
