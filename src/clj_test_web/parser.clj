(ns clj-test-web.parser
  (:require [clojure.string :as str]))

(defn params_string
  [query]
  (if
    (> (count (str/split query #"\?")) 1)
    (nth (str/split query #"\?") 1)
    ""))

(defn url_string
  [query]
  (nth (str/split query #"\?") 0))

(defn url_with_params
  [request]
  (nth (str/split request #" ") 1))

(defn params_vector
  [request]
  (str/split
    (params_string
      (url_with_params request))
    #"(&|=)"))

(defn params_present?
  [request]
  (> (count (params_vector request)) 1))

(defn params
  [request]
  (if
    (params_present? request)
    (apply hash-map (params_vector request)))
    [])

(defn path
  [request]
  (str/replace-first
    (url_string
      (url_with_params request))
    #"^.*?/" ""))

(defn parse
  [request]
  { :path (path request) :params (params request) })
