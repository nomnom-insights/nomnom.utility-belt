(ns utility-belt.map-keys-test
  (:require [clojure.test :refer [deftest is testing]]
            [utility-belt.map-keys :as map-keys]))

(def snake-case-tests
  [["FOO-BAR" "FOO_BAR"]
   ["foo-bar" "foo_bar"]
   ["foo-and-le-bar" "foo_and_le_bar"]
   [:foo_and-le-bar "foo_and_le_bar"]
   [:baz-BAR "baz_BAR"]
   [:nodash "nodash"]])

(def kebab-case-tests
  [["FOO_BAR" "FOO-BAR"]
   ["foo_bar" "foo-bar"]
   ["foo_and_le_bar" "foo-and-le-bar"]
   [:foo_and-le-bar "foo-and-le-bar"]
   [:baz_BAR "baz-BAR"]
   [:nodash "nodash"]])

(def snake-case-keys-tests
  {:FOO-BAR {:nested {:nested-2 "foo"}}
   :not-so-nested "wow"
   "strings-work" "working"
   "arrays-too"
   [{:working-key 20}
    {:another-working-key 10}]
   :values-are-safe "je-suis-safe"})

(def kebab-case-keys-tests
  {:FOO_BAR {:nested {:nested_2 "foo"}}
   :not_so_nested "wow"
   "strings_work" "working"
   "arrays_too"
   [{:working_key 20}
    {:another_working_key 10}]
   :values_are_safe "je_suis_safe"})

(deftest snake-case-test
  (testing "snake cases strings and keywords"
    (mapv
     (fn [[test-case expect]]
       (is (= expect (map-keys/to-snake-case test-case))))
     snake-case-tests)))

(deftest kebab-case-test
  (testing "kebab cases strings and keywords"
    (mapv
     (fn [[test-case expect]]
       (is (= expect (map-keys/to-kebab-case test-case))))
     kebab-case-tests)))

(deftest snakeify-keys-test
  (testing "snake cases all keys"
    (is (= {"FOO_BAR" {"nested" {"nested_2" "foo"}}
            "not_so_nested" "wow"
            "strings_work" "working"
            "arrays_too"
            [{"working_key" 20}
             {"another_working_key" 10}]
            "values_are_safe" "je-suis-safe"}
           (map-keys/snakeify-keys snake-case-keys-tests))))
  (testing "snake cases and applies passed fn"
    (is (= {:FOO_BAR {:nested {:nested_2 "foo"}}
            :not_so_nested "wow"
            :strings_work "working"
            :arrays_too
            [{:working_key 20}
             {:another_working_key 10}]
            :values_are_safe "je-suis-safe"}
           (map-keys/snakeify-keys snake-case-keys-tests keyword)))))

(deftest kebabify-keys-test
  (testing "kebabifies cases all keys"
    (is (= {"FOO-BAR" {"nested" {"nested-2" "foo"}}
            "not-so-nested" "wow"
            "strings-work" "working"
            "arrays-too"
            [{"working-key" 20}
             {"another-working-key" 10}]
            "values-are-safe" "je_suis_safe"}
           (map-keys/kebabify-keys kebab-case-keys-tests))))
  (testing "kebabifies and applies passed fn"
    (is (= {:FOO-BAR {:nested {:nested-2 "foo"}}
            :not-so-nested "wow"
            :strings-work "working"
            :arrays-too
            [{:working-key 20}
             {:another-working-key 10}]
            :values-are-safe "je_suis_safe"}
           (map-keys/kebabify-keys kebab-case-keys-tests keyword)))))

(deftest keywordized-versions
  (testing "mmm kebab"
    (let [kebab-keywords (map-keys/kebabify-keys-kw kebab-case-keys-tests)]
      (is (= {:FOO-BAR {:nested {:nested-2 "foo"}}
              :not-so-nested "wow"
              :strings-work "working"
              :arrays-too
              [{:working-key 20}
               {:another-working-key 10}]
              :values-are-safe "je_suis_safe"}
             kebab-keywords))))
  (testing "ahhh, snakes"
    (is (= {:FOO_BAR {:nested {:nested_2 "foo"}}
            :not_so_nested "wow"
            :strings_work "working"
            :arrays_too
            [{:working_key 20}
             {:another_working_key 10}]
            :values_are_safe "je-suis-safe"}
           (map-keys/snakeify-keys-kw snake-case-keys-tests)))))
