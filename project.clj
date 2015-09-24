(defproject looney "0.1.0-SNAPSHOT"
  :description "An application to rename the Looney Tunes Golden Collection into a tvdb compatible format"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [enlive "1.1.6"]
                 [clj-fuzzy "0.3.1"]
                 [me.raynes/fs "1.4.6"]]
  :main ^:skip-aot looney.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
