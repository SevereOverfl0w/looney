(ns looney.match
  (:require [clj-fuzzy.metrics :refer [levenshtein]]))

(defn best-match
  [ep-name key-fn episodes]
  (first (sort-by #(levenshtein ep-name (key-fn %)) episodes)))
