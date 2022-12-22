(ns app.webserver
  (:require [prone.middleware :refer [wrap-exceptions]]
            [ring.adapter.undertow :refer [run-undertow]]
            [clojure.tools.logging :as log]
            [app.routes :as routes]
            [mount.core :refer [defstate]]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults]]
            [ring.middleware.json :refer [wrap-json-body wrap-json-response]])
  (:import (io.undertow Undertow)))

(def is-dev? true)

(def web-conf {:port 7331
               :host "0.0.0.0"})

(defn- ring-handler [handler]
  (fn [request] (handler request)))

(def default-app
  (-> #'routes/routes
      (wrap-json-body {:keywords? true :bigdecimals? true})
      wrap-json-response
      (wrap-defaults (assoc api-defaults
                       :session true
                       :cookies true
                       :security {:anti-forgery false}
                       :not-modified-responses false))))


(def development-app (wrap-exceptions
                       default-app))

(defstate ^{:on-reload :noop} webserver
          :start (let [web (-> (if is-dev? #'development-app
                                           #'default-app)
                               ring-handler
                               (run-undertow web-conf))]
                   (log/info (str "Started Immutant webserver on port " (:port web-conf)))
                   web)
          :stop (do (.stop ^Undertow webserver)
                    (log/info "Stopped Immutant webserver")))

(comment
  "Run this fn to start it all up"
  (mount.core/start))
