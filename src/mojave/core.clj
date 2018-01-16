(ns mojave.core
  (:require postal.core)
  (:require clojure.java.io)
  (:gen-class))

(defn -main
  [& args]
  (let [number (get-number args)
    user (get-email args)
    pass (get-password args)
    message (get-message args)]
    (printf "Texting phone number %s from email%n" number user)
    (postal.core/send-message {:host "smtp.gmail.com"
      :user user
      :pass pass
      :ssl true}
      {:from (join [user "@gmail.com"])
        :to (join [number "@vtext.com"])
        :subject "Hi!"
        :body "Test..."})))

(defn load-properties
  [file-name]
  (with-open [^java.io.Reader reader (clojure.java.io/reader file-name)]
  (let [p (java.util.Properties.)]
    (.load props reader)
    (into {} (for [[k v] props] [(keyword k) (read-string v)])))))