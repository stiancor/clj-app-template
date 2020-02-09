(ns app.service
  (:require [clojure.tools.logging :as log]
            [ring.util.response :as resp]))

(defn evaluate [body]
  (log/info body)
  (resp/response {}))

(defn handle-input [body]
  (try (evaluate body)
       (catch Exception e
         (log/error "Error: " e)
         (resp/response {:status 501
                         :body "Not implemented yet!"}))))

(defn handle-req [req]
  (merge {:headers {:content-type :json}}
         (handle-input (:body req))))
