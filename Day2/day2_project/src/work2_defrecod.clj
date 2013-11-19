(ns work2_defrecod)
 
(defprotocol Dragon
  (breath [this])
  (fly [this where]))
 
(defrecord RedDragon [name]
  Dragon
  (breath [this] (println "Red dragon breathes fire!"))
  (fly [this where] (println "Fly to " where)))
 
(def Verminthrax (RedDragon. "Verminthrax"))
 
(breath Verminthrax)
(fly Verminthrax :mystara)