(ns growl-clj.test-core
  (:use [growl-clj.core])
  (:use [clojure.test]))

(deftest test-growl-enabled?
  (if growl-enabled?
    (print ">>>>>>>>>>> Growl is enabled")
    (print ">>>>>>>>>>> Growl is NOT enabled")))

(deftest test-register-app
  "Registering application with Growl"
  (let [available-notifications ["system" "user"]
        enabled-notifications ["system" "user"]
        app-name "MyApp"]

    (register-app
      available-notifications
      enabled-notifications
      app-name)))

(deftest test-notify
  "You should see a Growl notification after running this test"
  (let [notification-name "user"
        title "hello from test"
        message "nice! it works"
        app-name "MyApp"]

    (notify
      notification-name
      title
      message
      app-name)))