(ns clj-test-web.parser)
(require '[clojure.string :as str])

(defn params_string [query]
  (if
    (> (count (str/split query #"\?")) 1)
    (nth (str/split query #"\?") 1)
    ""))

(defn url_string [query]
  (nth (str/split query #"\?") 0))

(defn url_with_params [request]
  (nth (str/split request #" ") 1))

(defn params [request]
  (apply hash-map (str/split (params_string (url_with_params request)) #"(&|=)")))

(defn path [request]
  (str/replace-first (url_string (url_with_params request)) #"^.*?/" ""))

(defn parse [request]
  { :path (path request) :params (params request) })
