(ns looney.parse-test
  (:require [clojure.test :refer :all]
            [looney.parse :refer :all]))

(deftest test-get-raw-url
  (testing "Check file url contains episode-list.html"
    (is (.contains (.toString (get-raw-url)) "episode-list.html"))))

(deftest test-scrape
  (let [res (l-episode-no-and-name (get-file-contents (get-raw-url)))]
    (testing "Check returns a list" (is (seq? res)))
    (testing "Should be of even length" (is (even? (count res))))))

(deftest test-with-keys
  (let [res (with-keys (l-episode-no-and-name (get-file-contents (get-raw-url))))]
    (testing "Check returns a sequence" (is (seq? res)))
    (testing "Check first element is an object" (is (map? (first res))))
    (testing "Check first element has :episode and :title properties" (is (= (keys (first res)) [:season-episode :title])))))

(deftest test-season-split
  (testing "Returns correct for a sample input"
    (is (= (split-season-episode {:title "Bosko" :season-episode "1929 x 1"}) {:title "Bosko" :season "1929" :episode "1"}))))
