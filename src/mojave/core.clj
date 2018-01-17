(ns mojave.core
  (:require postal.core)
  (:require clojure.java.io)
  (:require clojure.string)
  (:require [clojure.data.json :as json])
  (:gen-class))

(defn load-props
  [path]
  (with-open [reader (clojure.java.io/reader path)]
    (let [m (json/read reader)]
      (into {} (for [[k, v] m] [(keyword k) v])))))

(defn send-email
  [{:keys [number email password message]}]
  (printf "Texting phone number %s from email %s%n" number email)
  (let [target (clojure.string/join [number "@vtext.com"])]
    (postal.core/send-message {:host "smtp.gmail.com"
      :user email
      :pass password
      :ssl true}
      {:from email
        :to [target]
        :subject "Hi!"
        :body "Test..."})))

(defn -main
  [& args]
  (printf "Using path %s for system properties%n" (first args))
  (let [p (load-props (first args))]
    (send-email p)))