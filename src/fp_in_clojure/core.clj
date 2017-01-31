(ns fp-in-clojure.core)

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))

(defn factorial [n]
  (defn go [n acc]
    (if (<= n 0)
      (do (println acc) acc)
      (do (println "dec:"(dec n) "acc*n" (* n acc))
          (go (dec n) (* n acc)))))
  (go n 1))


(factorial 5)

;; 2-1

;; fibo 0 1 1 2 3 5 8

;; fibo(1)= 0 (n = 1)
;; fibo(2)= 1 (n = 2)
;; fibo(n)= fibo (n-1) + fibo (n-2)

(defn fibo [n] ;;꼬리 재귀가 아님
  {:pre [(not (neg? n))]}
  (case n
    1 0
    2 1
    (+ (fibo (- n 1)) (fibo (- n 2)))))

(defn fibo-tail [n]
  (letfn [(fib
            [prev current n]
            (if (zero? (dec n))
              prev
              (fib current (+ current prev) (dec n))))]
    (fib 0 1 n)))

(defn fibo-tail2 [n]
  {:pre [(pos? n)]}
  (letfn [(fib
            [current next n]
            (if (= n 1)
              current
              (fib current (+ current next) (dec n))))]
    (fib 0 1 n)))

;; 2-2

(def asc-sample [1 2 3 4 5 6 10 11])

(def dsc-sample [10 9 8 7 6 5])

(def random-sam [1 29 10 39 19 50])

(defn asc [x y]
  (if (< x y)
    true
    false))

(defn dsc [x y]
  (if (> x y)
    true
    false))

(defn ssorted? [array f]
  (letfn [(temp [array]
            (if (seq array)
              true
              (if (f (first array) (second array))
                (recur (rest array))
                false)))]
    (temp array)))


;; 2-3
(defn curry [f]
  (fn [a]
    (fn [b]
      (f a b))))
