(ns clj-test-web.core
  (:use server.socket)
  (:use clj-test-web.response.http-response
        clj-test-web.request.parser)
  (:import (java.io BufferedReader InputStreamReader OutputStreamWriter))
  (:require [clj-test-web.config :as config])
  (:require [clj-test-web.logger :as logger]))

(defn respond [response-data]
  (println response-data))

(defn log [http-request]
  (logger/log2anywhere http-request))

(defn event-loop []
  (loop [http-request(read-line)]
    (if config/verbose (log http-request))
    (respond (build-http-response (parse http-request)))))

(defn http-server [in out]
  (binding
    [*in* (BufferedReader. (InputStreamReader. in))
     *out* (OutputStreamWriter. out)]
    (event-loop)))

(defn main []
  (println "starting server on port" config/port)
  (create-server config/port http-server))


