(ns growl-clj.scripts)

(def growl-app "GrowlHelperApp")

(defn- quotes
  [msg]
  (str "\"" msg "\""))

(defn- join
  [items]
  (str "{" (apply str (interpose "," (map quotes items))) "}"))

(defn notify
  "Create applescript for creating a Growl notification"
  [notification-name title message app-name]
  (let [notification-name- (quotes notification-name)
        title- (quotes title)
        message- (quotes message)
        app-name- (quotes app-name)
        growl- (quotes growl-app)]

    (str "tell application " growl- "\n"
      "notify with name " notification-name- " title " title- " description " message- " application name " app-name- "\n"
      "end tell")))

(defn growl-enabled
  "Create applescript to test if Growl is enabled"
  []
  (let [sysevents- (quotes "System Events")
        growl- (quotes growl-app)]

    (str "tell application " sysevents- "\n"
      "return count of (every process whose name is " growl- ") > 0\n"
      "end tell")))

(defn register-app
  "Create applescript for registering a Growl enabled application"
  [available-notifications enabled-notifications app]
  (let [growl- (quotes growl-app)
        available-notifications- (join available-notifications)
        enabled-notifications- (join enabled-notifications)
        app- (quotes app)]

    (str "tell application " growl- "\n"
      "set the availableList to " available-notifications- "\n"
      "set the enabledList to " enabled-notifications- "\n"
      "register as application " app- " all notifications availableList default notifications enabledList\n"
      "end tell")))

