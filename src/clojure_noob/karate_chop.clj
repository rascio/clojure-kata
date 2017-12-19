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
        [start end]
        (let [size (half end)
            idx (+ start size) 
            current (get elements idx)]
;            (println "start" start "size" size "idx" idx "current" current)
            (cond
                (and (= end 0) (not= current search)) nil
                (= current search) idx
                (< current search) (binary-search (+ start size) size)
                (> current search) (binary-search start size))))]
        (binary-search 0 (count elements))))
    