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
        :body message})))

(defn build-options
  [props]
    (let [current_seconds (.toSecondOfDay (java.time.LocalTime/now))]
      (let [{:keys [day_start_time]} props]
        (let [elapsed_seconds (- current_seconds day_start_time)]
          (let [percent (* 100 (double (/ elapsed_seconds (* 60 16 60))))]
            (let [msg (clojure.string/join [percent "% of your day has passed"])]
              (into props {:message msg})))))))

(defn -main
  [& args]
  (printf "Using path %s for system properties%n" (first args))
  (let [p (load-props (first args))]
    (send-email (build-options p))))