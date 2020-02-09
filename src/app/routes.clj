(ns app.routes
  (:require
    [compojure.core :refer [defroutes GET POST]]
    [compojure.route :refer [not-found resources]]
    [app.service :as s]))

(defroutes routes
           (resources "/")
           (GET "/*" [] "Hello world")
           (POST "/*" [] #(s/handle-req %)))
