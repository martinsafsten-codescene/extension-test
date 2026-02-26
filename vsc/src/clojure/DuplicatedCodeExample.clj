(ns smelly.duplicated-code-example
  "Example of Duplicated Code smell in Clojure

  This file demonstrates the Duplicated Code smell where similar
  logic is repeated across multiple functions instead of being
  extracted into reusable components.

  Problems with this approach:
  - Changes must be made in multiple places
  - Inconsistencies can creep in
  - Harder to maintain and test
  - Violates the DRY (Don't Repeat Yourself) principle")


;; Example 1: Duplicated validation logic
(defn create-user
  "Creates a new user - CODE SMELL: Duplicated validation"
  [user-data]
  ;; Email validation (duplicated)
  (when-not (:email user-data)
    (throw (ex-info "Email is required" {:field :email})))
  (when-not (re-matches #".+@.+\..+" (:email user-data))
    (throw (ex-info "Invalid email format" {:field :email})))

  ;; Password validation (duplicated)
  (when-not (:password user-data)
    (throw (ex-info "Password is required" {:field :password})))
  (when (< (count (:password user-data)) 8)
    (throw (ex-info "Password must be at least 8 characters" {:field :password})))
  (when-not (re-find #"[A-Z]" (:password user-data))
    (throw (ex-info "Password must contain uppercase letter" {:field :password})))
  (when-not (re-find #"[0-9]" (:password user-data))
    (throw (ex-info "Password must contain a number" {:field :password})))

  ;; Create user
  {:id (java.util.UUID/randomUUID)
   :email (:email user-data)
   :password (hash-password (:password user-data))
   :created-at (java.time.LocalDateTime/now)})


(defn update-user-email
  "Updates user email - CODE SMELL: Same validation duplicated here"
  [user-id new-email]
  ;; Email validation (duplicated from create-user)
  (when-not new-email
    (throw (ex-info "Email is required" {:field :email})))
  (when-not (re-matches #".+@.+\..+" new-email)
    (throw (ex-info "Invalid email format" {:field :email})))

  ;; Update email
  {:user-id user-id
   :email new-email
   :updated-at (java.time.LocalDateTime/now)})


(defn change-password
  "Changes user password - CODE SMELL: Same validation duplicated here"
  [user-id new-password]
  ;; Password validation (duplicated from create-user)
  (when-not new-password
    (throw (ex-info "Password is required" {:field :password})))
  (when (< (count new-password) 8)
    (throw (ex-info "Password must be at least 8 characters" {:field :password})))
  (when-not (re-find #"[A-Z]" new-password)
    (throw (ex-info "Password must contain uppercase letter" {:field :password})))
  (when-not (re-find #"[0-9]" new-password)
    (throw (ex-info "Password must contain a number" {:field :password})))

  ;; Change password
  {:user-id user-id
   :password (hash-password new-password)
   :updated-at (java.time.LocalDateTime/now)})


;; Example 2: Duplicated formatting logic
(defn format-user-profile
  "Formats user profile - CODE SMELL: Duplicated formatting"
  [user]
  (str "Name: " (:first-name user) " " (:last-name user) "\n"
       "Email: " (:email user) "\n"
       "Member since: " (.format (:created-at user)
                                 (java.time.format.DateTimeFormatter/ofPattern "MMM dd, yyyy")) "\n"
       "Status: " (if (:active user) "Active" "Inactive")))


(defn format-user-card
  "Formats user card - CODE SMELL: Same formatting logic duplicated"
  [user]
  (str (:first-name user) " " (:last-name user) " <" (:email user) ">\n"
       "Joined: " (.format (:created-at user)
                          (java.time.format.DateTimeFormatter/ofPattern "MMM dd, yyyy")) "\n"
       (if (:active user) "[Active]" "[Inactive]")))


(defn format-user-email-signature
  "Formats email signature - CODE SMELL: Similar formatting duplicated again"
  [user]
  (str (:first-name user) " " (:last-name user) "\n"
       (:email user) "\n"
       "Customer since " (.format (:created-at user)
                                 (java.time.format.DateTimeFormatter/ofPattern "MMM dd, yyyy"))))


;; Example 3: Duplicated calculation logic
(defn calculate-order-total
  "Calculates order total - CODE SMELL: Duplicated calculation"
  [items]
  (let [subtotal (reduce + (map #(* (:price %) (:quantity %)) items))
        tax (* subtotal 0.08)
        shipping (if (>= subtotal 50) 0 9.99)]
    (+ subtotal tax shipping)))


(defn estimate-cart-price
  "Estimates cart price - CODE SMELL: Same calculation duplicated"
  [cart-items]
  (let [subtotal (reduce + (map #(* (:price %) (:quantity %)) cart-items))
        tax (* subtotal 0.08)
        shipping (if (>= subtotal 50) 0 9.99)]
    {:subtotal subtotal
     :tax tax
     :shipping shipping
     :total (+ subtotal tax shipping)}))


(defn calculate-invoice-amount
  "Calculates invoice - CODE SMELL: Same calculation duplicated yet again"
  [line-items]
  (let [subtotal (reduce + (map #(* (:price %) (:quantity %)) line-items))
        tax (* subtotal 0.08)
        shipping (if (>= subtotal 50) 0 9.99)
        total (+ subtotal tax shipping)]
    {:subtotal subtotal
     :tax tax
     :shipping shipping
     :total total}))


;; Mock helper function
(defn hash-password [password]
  (str "hashed:" password))


;; REFACTORED VERSION - Better approach with extracted common logic
(comment
  "The duplicated code above should be refactored by extracting common logic:

  ;; Extracted validation functions
  (defn validate-email [email]
    (when-not email
      (throw (ex-info \"Email is required\" {:field :email})))
    (when-not (re-matches #\".+@.+\\..+\" email)
      (throw (ex-info \"Invalid email format\" {:field :email}))))

  (defn validate-password [password]
    (when-not password
      (throw (ex-info \"Password is required\" {:field :password})))
    (when (< (count password) 8)
      (throw (ex-info \"Password must be at least 8 characters\" {:field :password})))
    (when-not (re-find #\"[A-Z]\" password)
      (throw (ex-info \"Password must contain uppercase letter\" {:field :password})))
    (when-not (re-find #\"[0-9]\" password)
      (throw (ex-info \"Password must contain a number\" {:field :password}))))

  ;; Extracted formatting functions
  (defn format-full-name [user]
    (str (:first-name user) \" \" (:last-name user)))

  (defn format-date [date]
    (.format date (java.time.format.DateTimeFormatter/ofPattern \"MMM dd, yyyy\")))

  (defn format-status [active?]
    (if active? \"Active\" \"Inactive\"))

  ;; Extracted calculation functions
  (defn calculate-subtotal [items]
    (reduce + (map #(* (:price %) (:quantity %)) items)))

  (defn calculate-tax [subtotal]
    (* subtotal 0.08))

  (defn calculate-shipping [subtotal]
    (if (>= subtotal 50) 0 9.99))

  (defn calculate-pricing [items]
    (let [subtotal (calculate-subtotal items)
          tax (calculate-tax subtotal)
          shipping (calculate-shipping subtotal)]
      {:subtotal subtotal
       :tax tax
       :shipping shipping
       :total (+ subtotal tax shipping)}))

  Benefits of the refactored approach:
  - Single source of truth for each piece of logic
  - Changes only need to be made in one place
  - Easier to test individual functions
  - More consistent behavior across the codebase
  - Better code reusability")
