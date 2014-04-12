(ns clj-test-web.file_reader)
(require '[clj-test-web.config :as config])

(defn filename_for_path
  [path]
  (str config/root_dir "/" path))

(defn file_contents_for
  [request]
  (slurp (filename_for_path (get request :path))))
