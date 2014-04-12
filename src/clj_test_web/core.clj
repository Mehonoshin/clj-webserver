(ns clj-test-web.core
  (use server.socket)
  (use clj-test-web.logger)
  (use clj-test-web.file_reader)
  (use clj-test-web.parser))
(import '[java.io BufferedReader InputStreamReader OutputStreamWriter])
(require '[clj-test-web.config :as config])

(defn respond [request]
  (println request))

(defn log [input]
  (log2anywhere input))

(defn response [request]
  (file_contents_for request))

(defn event-loop []
  (loop [input(read-line)]
    (if config/verbose (log input))
    (respond (response (parse input)))))

(defn http-server [in out]
  (binding
    [*in* (BufferedReader. (InputStreamReader. in))
     *out* (OutputStreamWriter. out)]
    (event-loop)))

(defn main []
  (println "starting server on port" config/port)
  (create-server config/port http-server))


