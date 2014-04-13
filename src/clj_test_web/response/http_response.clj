(ns clj-test-web.response.http-response
  (:require [clj-test-web.response.file_reader :as reader]))

(defn build-http-response
  [request-hash]
  (reader/file_contents_for request-hash))
