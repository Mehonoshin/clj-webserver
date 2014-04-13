(ns clj-test-web.response.http-response
  (:require [clj-test-web.response.file_reader :as reader])
  (:require [clj-test-web.logger :as logger])
  (:require [clj-test-web.response.http_status :as http_status]))

(defn not_found
  []
  (str "HTTP/1.1 " http_status/not_found " \n\n" (reader/not_found)))

(defn build-http-response
  [request-hash]
  (try
    (reader/file_contents_for request-hash)
    (catch Exception e
      (logger/log2anywhere (str "404 not found: " (.getMessage e)))
      (not_found))))
