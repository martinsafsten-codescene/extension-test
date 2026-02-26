package org.example.smelly

import java.io.FileWriter

/**
 * God Class - A class that does everything and knows too much.
 * CodeScene flags classes with too many responsibilities and dependencies.
 */
class GodClassExample {

    private val users = mutableMapOf<String, User>()
    private val activeUsers = mutableListOf<String>()
    private val products = mutableMapOf<String, Product>()
    private val inventory = mutableMapOf<String, Int>()
    private val orders = mutableMapOf<String, Order>()
    private val pendingOrders = mutableListOf<Order>()
    private var paymentGatewayUrl: String = ""
    private var merchantId: String = ""
    private var smtpServer: String = ""
    private var emailFrom: String = ""
    private var dbConnection: String = ""
    private var dbUsername: String = ""
    private var dbPassword: String = ""
    private var logFile: FileWriter? = null
    private var debugMode: Boolean = false

    fun createUser(id: String, name: String, email: String) {
        users[id] = User(id, name, email)
        activeUsers.add(id)
        logActivity("User created: $id")
    }

    fun getUser(id: String) = users[id]

    fun deleteUser(id: String) {
        users.remove(id)
        activeUsers.remove(id)
        logActivity("User deleted: $id")
    }

    fun updateUserEmail(id: String, newEmail: String) {
        val user = users[id]!!
        user.email = newEmail
        sendEmailConfirmation(user)
    }

    fun addProduct(id: String, name: String, price: Double) {
        products[id] = Product(id, name, price)
        inventory[id] = 0
        logActivity("Product added: $id")
    }

    fun updateInventory(productId: String, quantity: Int) {
        inventory[productId] = (inventory[productId] ?: 0) + quantity
    }

    fun checkInventory(productId: String, needed: Int) =
        (inventory[productId] ?: 0) >= needed

    fun createOrder(orderId: String, userId: String, productIds: List<String>) {
        val order = Order(orderId, userId, productIds)
        orders[orderId] = order
        pendingOrders.add(order)
        productIds.forEach { updateInventory(it, -1) }
        logActivity("Order created: $orderId")
    }

    fun processOrder(orderId: String) {
        val order = orders[orderId]!!
        if (processPayment(order)) {
            order.status = "PAID"
            sendOrderConfirmation(order)
            saveOrderToDatabase(order)
        }
    }

    fun shipOrder(orderId: String) {
        val order = orders[orderId]!!
        order.status = "SHIPPED"
        sendShippingNotification(order)
        pendingOrders.remove(order)
    }

    fun processPayment(order: Order): Boolean {
        logActivity("Processing payment for order: ${order.id}")
        return true
    }

    fun refundPayment(orderId: String, amount: Double) {
        logActivity("Refunding $amount for order $orderId")
    }

    fun sendEmailConfirmation(user: User) {
        logActivity("Sending email to: ${user.email}")
    }

    fun sendOrderConfirmation(order: Order) {
        val user = users[order.userId]!!
        logActivity("Sending order confirmation to: ${user.email}")
    }

    fun sendShippingNotification(order: Order) {
        val user = users[order.userId]!!
        logActivity("Sending shipping notification to: ${user.email}")
    }

    fun connectToDatabase() {
        logActivity("Connecting to database: $dbConnection")
    }

    fun saveUserToDatabase(user: User) {
        logActivity("Saving user to database: ${user.id}")
    }

    fun saveOrderToDatabase(order: Order) {
        logActivity("Saving order to database: ${order.id}")
    }

    fun saveProductToDatabase(product: Product) {
        logActivity("Saving product to database: ${product.id}")
    }

    fun logActivity(message: String) = println("[INFO] $message")
    fun logError(message: String) = System.err.println("[ERROR] $message")

    fun generateSalesReport() = "Sales Report: ${orders.size} orders"
    fun generateInventoryReport() = "Inventory Report: ${products.size} products"
    fun generateUserReport() = "User Report: ${users.size} users"

    data class User(var id: String, var name: String, var email: String)
    data class Product(val id: String, val name: String, val price: Double)
    data class Order(val id: String, val userId: String, val productIds: List<String>, var status: String = "")
}
