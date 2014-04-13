(ns clj-test-web.response.file_reader
  (:require [clj-test-web.config :as config]))

(defn filename
  [request]
  (if
    (re-matches #".*.html" (get request :path))
    (get request :path)
    (str (get request :path) "/" config/default_file)))

(defn path_to_file
  [filename]
  (str config/root_dir "/" filename))

(defn file_contents_for
  [request]
  (slurp
    (path_to_file
      (filename request))))

(defn not_found
  []
  (slurp (str config/root_dir "/" config/not_found_file)))
