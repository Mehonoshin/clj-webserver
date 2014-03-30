(ns clj-test-web.logger)
(require 'clojure.java.io)

(defn print-logger
  [writer]
  #(binding [*out* writer]
     (println %)))


(def memory-stream (java.io.StringWriter.))

(def retained-logger (print-logger memory-stream))
(def out-logger (print-logger *out*))

(defn file-logger
      [file]
      #(with-open [f (clojure.java.io/writer file :append true)]
         ((print-logger f) %)))

(defn multi-logger
      [& logger-fns]
      #(doseq
        [f logger-fns]
        (f %)))

(defn timestamped-logger
      [logger]
      #(logger (format "[%1$tY-%1$tm-%1$te %1$tH:%1$tM:%1$tS] %2$s"
                       (java.util.Date.) %)))

(def log2anywhere (timestamped-logger
                    (multi-logger
                      (print-logger *out*)
                      (file-logger "messages.log"))))
