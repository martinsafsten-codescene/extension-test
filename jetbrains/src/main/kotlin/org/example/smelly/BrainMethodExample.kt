package org.example.smelly

/**
 * Brain Method - A method that does too much and has high complexity.
 * CodeScene flags methods with high cyclomatic complexity and excessive lines.
 */
class BrainMethodExample {

    fun processOrder(
        orderId: String,
        customerId: String,
        items: List<String>,
        paymentMethod: String,
        shippingAddress: String,
        isExpress: Boolean
    ) {
        if (orderId.isBlank()) {
            System.err.println("Invalid order ID")
            return
        }
        if (customerId.isBlank()) {
            System.err.println("Invalid customer ID")
            return
        }

        val customer = when {
            customerId.startsWith("PREM") -> lookupPremiumCustomer(customerId)
                ?: run { System.err.println("Premium customer not found"); return }
            customerId.startsWith("REG") -> lookupRegularCustomer(customerId)
                ?: run { System.err.println("Regular customer not found"); return }
            else -> lookupGuestCustomer(customerId)
        }

        var subtotal = 0.0
        for (item in items) {
            subtotal += when {
                item.startsWith("BOOK") -> 15.99
                item.startsWith("DVD") -> 19.99
                item.startsWith("GAME") -> 59.99
                item.startsWith("TOY") -> 29.99
                else -> 9.99
            }
        }

        val discount = when {
            customer["type"] == "PREMIUM" -> when {
                subtotal > 100 -> subtotal * 0.20
                subtotal > 50 -> subtotal * 0.15
                else -> subtotal * 0.10
            }
            customer["type"] == "REGULAR" -> when {
                subtotal > 100 -> subtotal * 0.10
                subtotal > 50 -> subtotal * 0.05
                else -> 0.0
            }
            else -> 0.0
        }

        val shipping = when {
            isExpress -> when {
                "NY" in shippingAddress -> 15.99
                "CA" in shippingAddress -> 19.99
                else -> 24.99
            }
            else -> when {
                "NY" in shippingAddress -> 5.99
                "CA" in shippingAddress -> 7.99
                else -> 9.99
            }
        }

        val tax = when {
            "NY" in shippingAddress -> subtotal * 0.08875
            "CA" in shippingAddress -> subtotal * 0.0725
            "TX" in shippingAddress -> subtotal * 0.0625
            else -> subtotal * 0.06
        }

        val total = subtotal - discount + tax + shipping

        val paymentSuccess = when (paymentMethod) {
            "CREDIT_CARD" -> if (total < 10000) processCreditCard(customerId, total) else {
                System.err.println("Amount too high for credit card"); return
            }
            "PAYPAL" -> processPayPal(customerId, total)
            "BITCOIN" -> if (total > 100) processBitcoin(customerId, total) else {
                System.err.println("Bitcoin payment too small"); return
            }
            else -> { System.err.println("Unknown payment method"); return }
        }

        if (!paymentSuccess) {
            System.err.println("Payment failed")
            sendPaymentFailureEmail(customerId)
            logPaymentFailure(orderId, customerId, total)
            return
        }

        items.forEach { updateInventory(it, -1) }
        sendOrderConfirmationEmail(customerId, orderId, total)
        if (isExpress) sendExpressShippingEmail(customerId, orderId)
        logOrderCreated(orderId, customerId, total)
        logPaymentProcessed(orderId, paymentMethod, total)
        logShippingScheduled(orderId, shippingAddress, isExpress)
        updateCustomerOrderCount(customerId)
        updateCustomerLifetimeValue(customerId, total)
        println("Order processed successfully: $orderId")
    }

    private fun lookupPremiumCustomer(id: String): Map<String, Any?>? = emptyMap()
    private fun lookupRegularCustomer(id: String): Map<String, Any?>? = emptyMap()
    private fun lookupGuestCustomer(id: String): Map<String, Any?> = emptyMap()
    private fun processCreditCard(customerId: String, amount: Double): Boolean = true
    private fun processPayPal(customerId: String, amount: Double): Boolean = true
    private fun processBitcoin(customerId: String, amount: Double): Boolean = true
    private fun sendPaymentFailureEmail(customerId: String) {}
    private fun sendOrderConfirmationEmail(customerId: String, orderId: String, total: Double) {}
    private fun sendExpressShippingEmail(customerId: String, orderId: String) {}
    private fun logPaymentFailure(orderId: String, customerId: String, total: Double) {}
    private fun logOrderCreated(orderId: String, customerId: String, total: Double) {}
    private fun logPaymentProcessed(orderId: String, method: String, total: Double) {}
    private fun logShippingScheduled(orderId: String, address: String, express: Boolean) {}
    private fun updateInventory(item: String, change: Int) {}
    private fun updateCustomerOrderCount(customerId: String) {}
    private fun updateCustomerLifetimeValue(customerId: String, amount: Double) {}
}
