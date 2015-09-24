(ns looney.parse
  (:require [clojure.java.io :as io]
            [net.cgrand.enlive-html :as html]))

(defn get-raw-url
  "Gets the url of the html file to parse from"
  ([] (get-raw-url "episode-list.html"))
  ([file] (io/resource file)))

(defn get-file-contents
  "Returns an enlive-friendly version of the content of a URL"
  [url]
  (html/html-resource url))

(defn l-episode-no-and-name
  "Taking enlive-friendly html, grabs the episode number and title out"
  [p-html]
  (map html/text
       (html/select p-html #{[:#listtable :tr (html/nth-child 1) :a]
                             [:#listtable :tr (html/nth-child 2) :a]})))

(def into-keys (fn [[episode title]] {:season-episode episode :title title}))

(defn with-keys
  "Attaches keys to the sequence of episodes and numbers passed"
  [episode-nos-names]
  (map into-keys (partition 2 episode-nos-names)))

(defn split-season-episode
  [{:keys [season-episode title]}]
  (let [s-e-split (.split season-episode " x ")]
    {:season (first s-e-split)
     :episode (last s-e-split)
     :title title}))

(defn episode-no-and-names
  [& alt-url]
  (->> (or alt-url (get-raw-url))
       get-file-contents
       l-episode-no-and-name
       with-keys
       (map split-season-episode)))
