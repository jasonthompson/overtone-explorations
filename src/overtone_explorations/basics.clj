(ns overtone-explorations.basics.clj)

(use 'overtone.live)

(use 'overtone.inst.synth)

(use 'overtone.osc)

(def server (osc-server 44100 "osc-clj"))

(zero-conf-off)

(osc-listen server (fn [msg] (println msg)) :debug)

(osc-rm-listener server :debug)

(osc-handle server "/1/fader5" (fn [msg] (overpad (first (:args msg)))))

(simple-flute)

(definst foo [freq 440] (sin-osc freq))

(defn overpad-note
  [val]
  (let [val (scale-range val 0 1 0 127)]
    (ctl overpad :note val)))

()
