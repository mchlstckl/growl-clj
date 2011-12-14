(ns growl-clj.core
  (:import (javax.script ScriptEngine ScriptEngineManager ScriptException))
  (:require (growl-clj [scripts :as script])))

(def engine (.getEngineByName (ScriptEngineManager.) "AppleScript"))

(defn- run-script
  [script]
  (.eval engine script (.getContext engine)))

(defn growl-enabled?
  "Check if Growl is enabled"
  []
  (let [count (run-script (script/growl-enabled))]
    (> count 0)))

(defn register-app
  "Register application with Growl notification system"
  [available-notifications
   enabled-notifications
   app-name]
  (run-script (script/register-app
                available-notifications
                enabled-notifications
                app-name)))

(defn notify
  "Create a Growl notification"
  [notification-name title message app-name]
  (run-script (script/notify
                notification-name
                title
                message
                app-name)))
