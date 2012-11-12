(ns overtone-explorations.instrumets.clj
 (:use [overtone.live]))

;; start with simple instrumet
(definst saw-wave [freq 440 width 0.1 attack 0.01 sustain 1 release 0.1 vol 0.4]
  (* (env-gen (lin-env attack sustain release) 1 1 0 1 FREE)
     (saw freq)
     vol))

;; Examples from wiki
;; low-pass: move mouse left and right to change threshold frequency
(demo 10 (lpf (saw 100) (mouse-x 40 5000 EXP)))

;; high-pass
(demo 30 (bpf (saw 100) (mouse-x 40 5000 EXP)))

;; band-pass; move mouse left/right to change threshold frequency; up/down to change bandwidth (top is narrowest)
(demo 30 (bpf (saw 100) (mouse-x 40 5000 EXP) (mouse-y 0.01 1 LIN)))

;; here we generate a pulse of white noise, and pass it through a pluck filter
;; with a delay based on the given frequency
(let [freq 220]
  (demo (pluck (* (white-noise) (env-gen (perc 0.001 2) :action FREE)) 1 3 (/ 1 freq))))

()
