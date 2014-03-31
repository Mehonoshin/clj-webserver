(ns clj-test-web.core
  (use server.socket)
  (use clj-test-web.logger)
  (use clj-test-web.parser))
(import '[java.io BufferedReader InputStreamReader OutputStreamWriter])

(def port 1234)
(def verbose true)

(defn respond [request]
  (println request))

(defn log [input]
  (log2anywhere input))

(defn response [body]
  (str "HTTP/1.1 200 OK \n\r\n\r <html><body>You requested: "
    body
    "</body></html> \n\r"))

(defn event-loop []
  (loop [input(read-line)]
    (if verbose (log input))
    (respond (response (parse input)))))

(defn http-server [in out]
  (binding
    [*in* (BufferedReader. (InputStreamReader. in))
     *out* (OutputStreamWriter. out)]
    (event-loop)))

(defn main []
  (println "starting server on port" port))
  (create-server port http-server)


