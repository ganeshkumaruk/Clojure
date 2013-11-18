(ns work1_bignum)
(defn twice-count [w] (* 2(count w)))
(twice-count "asdfadfafds")


;;check if string is bigger than the number
(defn big [w n] (> (count w) n))
(big "asdfadfafds" 5)

;-- :list, :map, or :vector based on the type of collection.
 
 
;-- Define our function.
(defn
collection-type
[input]
(cond
(list? input) :list
(map? input) :map
(vector? input) :vector
)
)
 
 
;-- Define our various data types to test again.
(def myList '(1 2 3))
(def myMap {:a 1, :b 2, :c 3})
(def myVector [1 2 3])
 
 
;-- Output the class types for the three test values in order to
;-- make sure that we know we are dealing with the correct test
;-- data types.
(println "myList:" (class myList))
(println "myMap:" (class myMap))
(println "myVector:" (class myVector))
 
(println "")
 
(println "myList:" (collection-type myList))
(println "myMap:" (collection-type myMap))
(println "myVector:" (collection-type myVector))
 
;; And one test for nil on an unexpected collection type.
;;(println "Nil Test:" (collection-type #{}))