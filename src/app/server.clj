(ns app.server
  (:require [mount.core :as mount]
            [app.webserver]))

(defn -main [& args]
  (.addShutdownHook (Runtime/getRuntime)
                    (Thread. #(do (println "Stopping app!") (mount/stop))))
  (mount/start))