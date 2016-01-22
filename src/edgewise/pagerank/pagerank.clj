;; gorilla-repl.fileformat = 1

;; @@
(in-ns 'edgewise.core)

(require '[edgewise.util :as util]
         '[edgewise.pagerank.diffusion :as pd])
;; @@

;; @@
(declare valid-ranks! id->label)
;; @@

;; **
;;; A PageRank of 0.5 means there is a 50% chance that a person clicking on a random link will be directed to the document with the 0.5 PageRank.
;;; 
;; **

;; @@
(defn pagerank
  ([g num-iterations] (pagerank g num-iterations 0.85))
  ([g num-iterations damping-factor]
   (->> (pd/diffusion g damping-factor num-iterations)
        valid-ranks!
        (sort-by val >)
        (id->label g))))
;; @@

;; @@
(defn valid-ranks!
  [ranks]
  (let [sum-of-rank (apply + (map second ranks))]
    (assert (util/nearly 1 sum-of-rank) (str "rank should sum to nearly 1.0, but was " sum-of-rank))
    ranks))
;; @@

;; @@
(defn- id->label
  "[id rank]->[label rank]"
  [g ranks]
  (map (fn [[id rank]] [(-> (v g id) (props :label) ffirst) rank]) ranks))
;; @@
