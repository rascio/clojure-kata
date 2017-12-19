(ns clojure-noob.karate-chop (:gen-class))

(defn- half
    [n]
    (int (/ n 2)))

(defn log
    [a b]
    (println a b)
    b)

(defn binary-search-recursive
    [elements search]
    (let [size (count elements)
        idx (half (count elements))
        current (get elements idx)]
        (println "idx:" idx "current:" current "comparison:" (compare current search) "size:" size "elements:" elements)
        (cond
            (and (= size 1) (not= current search)) Integer/MIN_VALUE
            (= current search) idx
            (< current search) (+ idx (binary-search-recursive (subvec elements idx) search))
            (> current search) (binary-search-recursive (subvec elements 0 idx) search))))