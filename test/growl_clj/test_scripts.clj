(ns growl-clj.test_scripts
  (:use [growl-clj.scripts])
  (:use [clojure.test]))

(defn print-test [expected actual]
  (print "\n--------------------------------\n")
  (print "Actual:\n" actual)
  (print "\n-----------------\n")
  (print "Expected:\n" expected)
  (print "\n--------------------------------\n"))

(deftest test-growl-enabled
  (let [expected "tell application \"System Events\"\nreturn count of (every process whose name is \"GrowlHelperApp\") > 0\nend tell"
        actual (growl-enabled)]
    (print-test expected actual)
    (is (= expected actual))))

(deftest test-notify
  (let [expected (str "tell application \"GrowlHelperApp\"\n"
    "notify with name \"name\" title \"title\" description \"message\" application name \"application\"\n"
    "end tell")
        actual (notify "name" "title" "message" "application")]
    (print-test expected actual)
    (is (= expected actual))))

(deftest test-register-app
  (let [expected (str "tell application \"GrowlHelperApp\"\n"
    "set the availableList to {\"system\",\"user\"}\n"
    "set the enabledList to {\"system\",\"user\"}\n"
    "register as application \"application\" all notifications availableList default notifications enabledList\n"
    "end tell")
        actual (register-app ["system" "user"] ["system" "user"] "application")]
    (print-test expected actual)
    (is (= expected actual))))
