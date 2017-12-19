(ns clojure-noob.core (:gen-class))

(defn any
  [p values]
  first (filter p values))

(defn greet
  "Ask for a name and greet"
  []
  (println "What's your name?")
  (let [name (read-line)]
    (str "Hello " name)))

(defn factorial
  [n]
  (if (= n 1)
    1
    (* n (factorial (dec n)))))

(defn sum3Or5
  [n]
  (let [numbers (range n)
        valid? #(or (mod % 3) (mod % 5))]
    (reduce + (filter valid? numbers))))

(defn -main
  "I don't do a whole lot."
  [& x]
  (println x "Hello, World!"))
