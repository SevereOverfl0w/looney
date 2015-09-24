(ns looney.core
  (:gen-class)
  (:require [looney.match :as m]
            [looney.parse :as p]
            [me.raynes.fs :as fs]))

(defn get-targets
  [input]
  (fs/find-files* input fs/file?))

(defn get-episode
  [target episodes]
  (m/best-match (.getName target) :title episodes))

(defn match-target
  [episodes target]
  {:file target
   :episode (get-episode target episodes)})

(defn get-new-path
  [output {:keys [file episode]}]
  (fs/file output (:season episode) (str (:episode episode) " - " (:title episode) (fs/extension file))))

(defn do-work [output episodes target]
  (let [matched (match-target episodes target)
        new-path (get-new-path output matched)]
    (println (str "Copying " (get-in matched [:episode :title]) " to " new-path))
    (fs/copy+ (:file matched) new-path)))

(defn -main
  [input output numb & args]
  (let [targets (get-targets input)
        episodes (p/episode-no-and-names)]
    (dorun ; Required becuase (p)map is lazily evaluated
      (pmap (partial do-work output episodes) (take (Integer. numb) targets)))
    (shutdown-agents)))
