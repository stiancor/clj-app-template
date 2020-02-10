(ns app.service
  (:require [clojure.tools.logging :as log]
            [ring.util.response :as resp]))

(defn evaluate [body]
  (log/info "Body: " body)
  (resp/response {}))

(defn handle-req [req]
  (log/info "Request received from: " (:remote-addr req))
  (try (evaluate (:body req))
       (catch Exception e
         (log/error "Error: " e)
         {:status 501
          :body {:error "Not implemented yet!"}})))