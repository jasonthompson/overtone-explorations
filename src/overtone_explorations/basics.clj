(ns overtone-explorations.basics)

(use 'overtone.live)

(use 'overtone.inst.synth)

(use 'overtone.osc)

;; start osc server
(def server (osc-server 44100 "osc-clj"))

;; Turn on zero-conf to set up server with TouchOSC. For some reason this doesn't work
;; on my iMac or linux laptop. Using ip address instead.
(zero-conf-on)
(zero-conf-off)

;; Turn this on to see what messages various TouchOSC
;; controllers are sending.
(osc-listen server (fn [msg] (println msg)) :debug)

;; Turns off listener server
(osc-rm-listener server :debug)

;; Set up an instrumet to play with
(defn foo-controller
  [val]
  ;; scale-range converts values between 0 and 1 send by controller to
  ;; a range of 50 to 1000, suitable for freq
  (let [val (scale-range val 0 1 50 1000)]
    (ctl foo :freq val)))

;; Use fader five to run fn overpad-control
(osc-handle server "/1/fader5" (fn [msg] (foo-controller (first (:args )))))



(foo)
(stop)

;; Let's try a more complex intrument with multiple controlls
(mooger)

(ctl mooger :note 70)



(defn mooger-osc1-controller [osc1-level]
  (ctl mooger :osc1-level osc1-level))
(defn mooger-osc2-controller [osc2-level]
  (ctl mooger :osc2-level osc2-level))

;; Chooser doesn't seem to make any difference
(defn mooger-osc1-chooser [osc1]
  (let [osc1 (scale-range osc1 0 1 0 2)]
    (ctl mooger :osc1 osc1)))

(defn mooger-cutoff-controller [cutoff]
  (let [cutoff (scale-range cutoff 0 1 0 20000)]
    (ctl mooger :cutoff cutoff)))


(stop)



(mooger-controller 0.8)
(osc-handler server "/1/fader5" (fn [msg] (mooger-amp-controller (first (:args msg)))))
(osc-handle server "/1/fader1" (fn [msg] (mooger-osc1-controller (first (:args msg)))))
(osc-handle server "/1/fader2" (fn [msg] (mooger-osc2-controller (first (:args msg)))))
(osc-handle server "/1/fader3" (fn [msg] (mooger-cutoff-controller (first (:args msg)))))

(defn foo-controller
  [val]
  ;; scale-range converts values between 0 and 1 send by controller to
  ;; a range of 50 to 1000, suitable for freq
  (let [val (scale-range val 0 1 50 1000)]
    (ctl overpad :freq val)))

()
