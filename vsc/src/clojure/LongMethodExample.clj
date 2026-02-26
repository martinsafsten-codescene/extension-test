(ns smelly.long-method-example
  "Example of Long Method code smell in Clojure

  The process-order function below does too many things:
  - Validates the order
  - Calculates totals and discounts
  - Updates inventory
  - Sends notifications
  - Logs the transaction

  This violates the Single Responsibility Principle and makes
  the code hard to test, understand, and maintain.")

(defn process-order
  "A long method that does too many things - CODE SMELL!"
  [order user-id]
  ;; Validate order
  (when-not (:items order)
    (throw (ex-info "Order must have items" {:order order})))
  (when-not (pos? (count (:items order)))
    (throw (ex-info "Order must have at least one item" {:order order})))
  (when-not user-id
    (throw (ex-info "User ID is required" {:user-id user-id})))

  ;; Calculate subtotal
  (let [subtotal (reduce (fn [acc item]
                          (+ acc (* (:price item) (:quantity item))))
                        0
                        (:items order))

        ;; Calculate discount
        discount-percent (cond
                          (>= subtotal 1000) 0.15
                          (>= subtotal 500) 0.10
                          (>= subtotal 100) 0.05
                          :else 0.0)
        discount-amount (* subtotal discount-percent)

        ;; Calculate tax
        tax-rate 0.08
        taxable-amount (- subtotal discount-amount)
        tax-amount (* taxable-amount tax-rate)

        ;; Calculate total
        total (+ taxable-amount tax-amount)

        ;; Calculate shipping
        shipping (cond
                  (>= total 100) 0.0
                  (>= total 50) 5.99
                  :else 9.99)
        final-total (+ total shipping)]

    ;; Update inventory for each item
    (doseq [item (:items order)]
      (let [current-stock (get-in @inventory-db [(:product-id item) :stock])
            new-stock (- current-stock (:quantity item))]
        (when (neg? new-stock)
          (throw (ex-info "Insufficient stock" {:item item})))
        (swap! inventory-db assoc-in [(:product-id item) :stock] new-stock)))

    ;; Update user's purchase history
    (swap! users-db update-in [user-id :total-spent] (fnil + 0) final-total)
    (swap! users-db update-in [user-id :order-count] (fnil inc 0))

    ;; Determine loyalty tier
    (let [total-spent (get-in @users-db [user-id :total-spent])
          loyalty-tier (cond
                        (>= total-spent 10000) :platinum
                        (>= total-spent 5000) :gold
                        (>= total-spent 1000) :silver
                        :else :bronze)]
      (swap! users-db assoc-in [user-id :loyalty-tier] loyalty-tier))

    ;; Send confirmation email
    (println (str "Sending email to user " user-id))
    (println (str "Subject: Order Confirmation #" (java.util.UUID/randomUUID)))
    (println (str "Your order total: $" (format "%.2f" final-total)))

    ;; Send SMS notification if enabled
    (when (get-in @users-db [user-id :sms-enabled])
      (println (str "Sending SMS to user " user-id))
      (println "Your order has been confirmed!"))

    ;; Log transaction
    (println (str "[LOG] " (java.time.LocalDateTime/now)))
    (println (str "[LOG] Order processed for user: " user-id))
    (println (str "[LOG] Subtotal: $" (format "%.2f" subtotal)))
    (println (str "[LOG] Discount: $" (format "%.2f" discount-amount)))
    (println (str "[LOG] Tax: $" (format "%.2f" tax-amount)))
    (println (str "[LOG] Shipping: $" (format "%.2f" shipping)))
    (println (str "[LOG] Total: $" (format "%.2f" final-total)))

    ;; Update analytics
    (swap! analytics-db update :total-orders inc)
    (swap! analytics-db update :total-revenue + final-total)
    (swap! analytics-db update :items-sold + (reduce + (map :quantity (:items order))))

    ;; Return result
    {:order-id (java.util.UUID/randomUUID)
     :user-id user-id
     :subtotal subtotal
     :discount discount-amount
     :tax tax-amount
     :shipping shipping
     :total final-total
     :status :confirmed
     :timestamp (java.time.LocalDateTime/now)}))


;; Mock databases
(def inventory-db (atom {}))
(def users-db (atom {}))
(def analytics-db (atom {:total-orders 0 :total-revenue 0 :items-sold 0}))


;; REFACTORED VERSION - Better approach with separate concerns
(comment
  "The function above should be refactored into multiple smaller functions:

  (defn validate-order [order user-id] ...)
  (defn calculate-pricing [order] ...)
  (defn update-inventory! [items] ...)
  (defn update-user-stats! [user-id amount] ...)
  (defn send-notifications! [user-id order-details] ...)
  (defn log-transaction! [order-details] ...)
  (defn update-analytics! [order] ...)

  (defn process-order [order user-id]
    (validate-order order user-id)
    (let [pricing (calculate-pricing order)]
      (update-inventory! (:items order))
      (update-user-stats! user-id (:total pricing))
      (send-notifications! user-id pricing)
      (log-transaction! pricing)
      (update-analytics! order)
      (create-order-result user-id pricing)))

  Each function would have a single responsibility and be easier to:
  - Test in isolation
  - Understand and maintain
  - Reuse in other contexts
  - Mock for testing")
