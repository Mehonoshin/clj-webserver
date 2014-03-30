(ns clj-test-web.core
  (use server.socket)
  (use clj-test-web.logger))
(import '[java.io BufferedReader InputStreamReader OutputStreamWriter])

(def port 1234)

(defn respond [request]
  (println
    (cond (= "quit" request) "good bye"
          (= "?" request) "42"
          :else request)))

(defn log [input]
  (log2anywhere input))

(defn response [input]
  "HTTP/1.1 200 OK \n\r\n\r <html><body>Test</body></html>")

(defn event-loop []
  (loop [input(read-line)]
    (log input)
    (respond (response input))
    (if (not= "quit" input) (recur (read-line)))))

(defn echo-server [in out]
  (binding
    [*in* (BufferedReader. (InputStreamReader. in))
     *out* (OutputStreamWriter. out)]
    (event-loop)))

(defn main
  []
  (println "starting server on port" port))
  (create-server port echo-server)


