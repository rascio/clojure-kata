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

(defmacro code-critic
  "Phrases are courtesy Hermes Conrad from Futurama"
  [bad good]
  `(do 
      (println "Great squid of Madrid, this is bad code:" (quote ~bad))
      (println "Sweet gorilla of Manila, this is good code:" (quote ~good))))
      
;(code-critic (1 + 1) (+ 1 1))
;Great squid of Madrid, this is bad code: (1 + 1)
;Sweet gorilla of Manila, this is good code: (+ 1 1)

(defn sum3Or5
  [n]
  (let [numbers (range n)
        valid? #(or (mod % 3) (mod % 5))]
    (reduce + (filter valid? numbers))))

(defn -main
  "I don't do a whole lot."
  [& x]
  (println x "Hello, World!"))
