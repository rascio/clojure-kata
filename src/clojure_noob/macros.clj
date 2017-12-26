(ns clojure-noob.macros (:gen-class)
    (:require [clj-http.client :as client]
        [clojure.data.json :as json]
        [clojure.spec.alpha :as s]))

(defmacro test-suite
    [conf]
    `(let ~(:preload-variables conf) ~@(:test-cases conf)))

(defmacro test-case
    "describe a Heat test case"
    [test-id {:keys [url query-parameters assertions] :as conf}]
    `(let [~test-id (execute ~url ~query-parameters)]
        ~assertions))

(defn execute
    [path params]
    (let [
        querystring (map #(str (name (first %)) "=" (second %)) (seq params))
        url (str path "?" (clojure.string/join "&" querystring))
        response (client/get url)
        ns (str (ns-name *ns*))
        body (json/read-str (:body response) :key-fn #(keyword ns %))
    ]
    {:headers (:headers response) :body body}))

(s/def ::check (s/keys description empty?))
(s/def ::fieldCheck (s/coll-of ::check))
(s/def ::expects (s/keys :req [::fieldCheck]))
(s/def ::testCase (s/keys :req [::expects]))
(s/def ::testCases (s/coll-of ::testCase) )
(s/def ::testSuite (s/keys :req [::testCases]))

(defn -main [& args]
    (test-suite {
        :preload-variables [token "r4nd0m"]
        :test-cases [
            (test-case mytest {
                :url "https://raw.githubusercontent.com/HotelsDotCom/heat/master/heat-test-module/src/test/resources/testcases/ExampleSingleModeTestCases.json"
                :query-parameters {
                    :pippo token
                    :pluto 2
                }
                :assertions (do 
;                    (println (get-in mytest [:body ::testSuite ::testCases 0 ::expects ::fieldCheck]) "\n")
                    (s/explain ::testSuite (get-in mytest [:body ::testSuite])))
            })
        ]
    }))