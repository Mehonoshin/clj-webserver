(ns clj-test-web.file_reader
  (:require [clj-test-web.config :as config]))

(defn filename
  [request]
  (if
    (= (get request :path) "")
    "index.html"
    (get request :path)))

(defn path_to_file
  [path]
  (str config/root_dir "/" path))

(defn file_contents_for
  [request]
  (slurp
    (path_to_file
      (filename request))))
