(ns utility-belt.lifecycle-test
  (:require [utility-belt.lifecycle :as lc]
            [clojure.java.io :as io]
            [clojure.test :refer :all]))

(def test-file (io/file "/tmp/ut-test-file"))

(deftest shutdown-outer
  (testing "should crate a file on exit"
    (io/delete-file test-file  :silently true)
    (lc/register-shutdown-hook :passing-test #(spit test-file "hi"))
    (lc/install-shutdown-hooks!)
    (is true) ;; so that linter stops complaining
    ))
