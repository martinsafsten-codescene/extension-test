package org.example.smelly

/**
 * Duplicated Code - Copy-pasted or highly similar code.
 * CodeScene detects code duplication across methods and classes.
 */
class DuplicatedCodeExample {

    fun validateAdminUser(username: String?, password: String?, email: String?): Boolean {
        if (username.isNullOrBlank()) {
            System.err.println("Username is required")
            return false
        }
        if (username.length < 3) {
            System.err.println("Username too short")
            return false
        }
        if (password.isNullOrBlank()) {
            System.err.println("Password is required")
            return false
        }
        if (password.length < 8) {
            System.err.println("Password too short")
            return false
        }
        if (email.isNullOrBlank()) {
            System.err.println("Email is required")
            return false
        }
        if (!email.contains("@")) {
            System.err.println("Invalid email")
            return false
        }
        return true
    }

    fun validateRegularUser(username: String?, password: String?, email: String?): Boolean {
        if (username.isNullOrBlank()) {
            System.err.println("Username is required")
            return false
        }
        if (username.length < 3) {
            System.err.println("Username too short")
            return false
        }
        if (password.isNullOrBlank()) {
            System.err.println("Password is required")
            return false
        }
        if (password.length < 8) {
            System.err.println("Password too short")
            return false
        }
        if (email.isNullOrBlank()) {
            System.err.println("Email is required")
            return false
        }
        if (!email.contains("@")) {
            System.err.println("Invalid email")
            return false
        }
        return true
    }

    fun saveUserToDatabase(userId: String, name: String, email: String) {
        try {
            println("Opening database connection...")
            println("Starting transaction...")
            println("Inserting user: $userId")
            println("Committing transaction...")
            println("Closing database connection...")
        } catch (e: Exception) {
            System.err.println("Error saving user: ${e.message}")
            println("Rolling back transaction...")
            println("Closing database connection...")
        }
    }

    fun saveProductToDatabase(productId: String, name: String, price: Double) {
        try {
            println("Opening database connection...")
            println("Starting transaction...")
            println("Inserting product: $productId")
            println("Committing transaction...")
            println("Closing database connection...")
        } catch (e: Exception) {
            System.err.println("Error saving product: ${e.message}")
            println("Rolling back transaction...")
            println("Closing database connection...")
        }
    }

    fun saveOrderToDatabase(orderId: String, userId: String, total: Double) {
        try {
            println("Opening database connection...")
            println("Starting transaction...")
            println("Inserting order: $orderId")
            println("Committing transaction...")
            println("Closing database connection...")
        } catch (e: Exception) {
            System.err.println("Error saving order: ${e.message}")
            println("Rolling back transaction...")
            println("Closing database connection...")
        }
    }

    fun calculateTotalForPremiumCustomer(prices: List<Double>): Double {
        val subtotal = prices.sum()
        val discount = subtotal * 0.15
        val tax = subtotal * 0.08
        return subtotal - discount + tax
    }

    fun calculateTotalForRegularCustomer(prices: List<Double>): Double {
        val subtotal = prices.sum()
        val discount = subtotal * 0.05
        val tax = subtotal * 0.08
        return subtotal - discount + tax
    }

    fun calculateTotalForGuestCustomer(prices: List<Double>): Double {
        val subtotal = prices.sum()
        val discount = 0.0
        val tax = subtotal * 0.08
        return subtotal - discount + tax
    }
}
