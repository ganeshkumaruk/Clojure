(ns work2)
 
(defmacro unless [test body]
  (list 'cond (list '= true test) (println "True cond") :else body))
 
;(unless true (println "Danger, danger Will Robinson"))
(unless false (println "Do not underestimate the power of the dark side!"))

;-- Implement an unless with else condition using macros.
 
 
;-- Define the unless macro with an Else condition.
(defmacro
unless
[test if-body else-body]
`(if
(not ~test)
~if-body
~else-body
)
)
 
 
;-- --------------------------------------------------------- --
;-- --------------------------------------------------------- --
 
 
;-- Test the unless macro with a TRUE value. Because the unless
;-- condition tests for "not" test, calling it with true should
;-- result in the "else-body" executing.
(unless
true
(println "1: Hey, this is the IF condition!")
(println "1: Hey, this is the ELSE condition!")
)
 
 
;-- Test the unless macro with a FALSE value. Since we are calling
;-- it with a false, the "if-body" should execute.
(unless
false
(println "2: Hey, this is the IF condition!")
(println "2: Hey, this is the ELSE condition!")
)