(ns app.webserver
  (:require [prone.middleware :refer [wrap-exceptions]]
            [immutant.web :as immutant]
            [clojure.tools.logging :as log]
            [app.routes :as routes]
            [mount.core :refer [defstate]]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults]]
            [ring.middleware.json :refer [wrap-json-body wrap-json-response]]))

(def is-dev? true)

(def web-conf {:port 7331
               :host "0.0.0.0"})

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
                               (immutant/run web-conf))]
                   (immutant/server web)
                   (log/info (str "Started Immutant webserver on port " (:port web-conf))))
          :stop (do (when (.isRunning webserver) (.stop webserver))
                    (log/info "Stopped Immutant webserver")))
