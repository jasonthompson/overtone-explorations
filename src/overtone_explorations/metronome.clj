(ns overtone-explorations.metronome.clj
  (:use [overtone.live]))

(definst sin-wave [freq 440 attack 0.01 sustain 0.4 release 0.1 vol 0.4]
  (* (env-gen (lin-env attack sustain release) 1 1 0 1 FREE)
     (sin-osc freq)
     vol))

(sin-wave)

(def metro (metronome 120))
(metro)

(defn sin-player [beat]
  (at (metro (+ 0.3 beat)) (sin-wave (midi 60)))
  (at (metro (+ 0.6 beat)) (sin-wave (midi )))
  (at (metro (+ 0.9 beat)) (sin-wave 600))
  (apply-at (metro (* 4 beat))  #'sin-player (inc (+ 4 beat)) []))

(sin-player (metro))

(ctl sin-wave :attack 1)
(ctl sin-wave :release 0.4)

(metro)

(plucked-string)

(stop)
