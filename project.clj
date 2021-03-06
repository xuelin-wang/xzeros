(defproject xzeros "0.0.1-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :repositories [["bintray" "https://dl.bintray.com/jaycroaker/maven"]]

  :dependencies [[org.clojure/clojure "1.9.0"]
                 [io.pedestal/pedestal.service "0.5.4"]

                 ;; Remove this line and uncomment one of the next lines to
                 ;; use Immutant or Tomcat instead of Jetty:
                 [io.pedestal/pedestal.jetty "0.5.4"]
                 ;; [io.pedestal/pedestal.immutant "0.5.4"]
                 ;; [io.pedestal/pedestal.tomcat "0.5.4"]
                 [org.clojure/data.json        "0.2.6"]
                 [com.datomic/client-pro "0.8.28"]

                 [redis.clients/jedis "3.0.1"]

                 [com.fasterxml.jackson.core/jackson-databind "2.9.8"]
                 [com.google.auth/google-auth-library-oauth2-http "0.12.0"]
                 [com.squareup.okhttp3/okhttp "3.12.1"]
                 [uncomplicate/neanderthal "0.22.1"]
                 [ch.qos.logback/logback-classic "1.2.3" :exclusions [org.slf4j/slf4j-api]]
                 [org.slf4j/jul-to-slf4j "1.7.25"]
                 [com.auth0/java-jwt "3.8.0"]
                 [org.slf4j/jcl-over-slf4j "1.7.25"]
                 [org.slf4j/log4j-over-slf4j "1.7.25"]

                 [it.ozimov/embedded-redis "0.7.2"]

                 ]

  :exclusions [[org.jcuda/jcuda-natives :classifier "apple-x86_64"]
               [org.jcuda/jcublas-natives :classifier "apple-x86_64"]]

  :min-lein-version "2.0.0"
  :resource-paths ["config", "resources"]
  :source-paths ["src/clj"]
  :test-paths ["test"]
  :java-source-paths ["src/java"]
  ;; If you use HTTP/2 or ALPN, use the java-agent to pull in the correct alpn-boot dependency
  ;:java-agents [[org.mortbay.jetty.alpn/jetty-alpn-agent "2.0.5"]]
  :profiles {:dev {:aliases {"run-dev" ["trampoline" "run" "-m" "xzeros.server/run-dev"]}
                   :dependencies [[io.pedestal/pedestal.service-tools "0.5.4"]]}
             :uberjar {:aot [xzeros.server]}}
  :main ^{:skip-aot true} xzeros.server)

