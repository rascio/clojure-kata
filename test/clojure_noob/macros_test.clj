(ns clojure-noob.core-test
    (:require [clojure.test :refer :all]
              [clojure-noob.core :refer :all]))
              
(test-suite {
    :preload-variables [token "r4nd0m"]
    :test-cases [
        (test-case mytest {
            :url "https://raw.githubusercontent.com/HotelsDotCom/heat/master/heat-test-module/src/test/resources/testcases/ExampleSingleModeTestCases.json"
            :query-parameters {
                :pippo token
                :pluto 2
            }
            :assertions (println mytest)
        })
    ]
})