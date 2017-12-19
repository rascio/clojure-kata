(ns clojure-noob.karate-chop (:gen-class))

(defn- half
    [n]
    (int (/ n 2)))

(defn log
    [a b]
    (println a b)
    b)

(defmulti sum (fn [&args] (count (filter nil? &args))))
(defmethod sum true [a b] nil)
(defmethod sum false [a b] (+ a b))

(defn binary-search-recursive
    [elements search]
    (let [size (count elements)
        idx (half (count elements))
        current (get elements idx)]
;        (println "idx:" idx "current:" current "comparison:" (compare current search) "size:" size "elements:" elements)
        (cond
            (and (= size 1) (not= current search)) nil
            (= current search) idx
            (< current search) (sum idx (binary-search-recursive (subvec elements idx) search))
            (> current search) (binary-search-recursive (subvec elements 0 idx) search))))

(defn binary-search-offset
    [elements search]
    (letfn [(binary-search 
        [offset size]
        (let [half-size (half size)
            idx (+ offset half-size) 
            current (get elements idx)]
;            (println "offset" offset "half-size" half-size "idx" idx "current" current)
            (cond
                (and (= size 0) (not= current search)) nil
                (= current search) idx
                (< current search) (binary-search (+ offset half-size) half-size)
                (> current search) (binary-search offset half-size))))]
        (binary-search 0 (count elements))))
    