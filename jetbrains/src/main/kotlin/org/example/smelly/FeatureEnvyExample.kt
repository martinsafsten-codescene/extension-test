package org.example.smelly

import java.util.Date

/**
 * Feature Envy - A method that seems more interested in other classes.
 * CodeScene flags methods that access other objects' data more than their own.
 */
class FeatureEnvyExample(
    private val customerId: String,
    private val name: String
) {

    fun calculateCustomerDiscount(order: Order): Double {
        val total = order.subtotal
        val itemCount = order.items.size
        val orderType = order.type
        val isExpress = order.express
        val shippingAddress = order.shippingAddress

        var discount = 0.0
        discount = when (orderType) {
            "PREMIUM" -> total * 0.20
            "REGULAR" -> total * 0.10
            else -> 0.0
        }
        if (itemCount > 10) discount += total * 0.05
        if (isExpress && "Local" in shippingAddress) discount += 5.0
        return discount
    }

    fun printInvoiceDetails(invoice: Invoice) {
        println("Invoice Number: ${invoice.invoiceNumber}")
        println("Date: ${invoice.invoiceDate}")
        println("Customer: ${invoice.customerName}")
        println("Amount: $${invoice.totalAmount}")
        println("Tax: $${invoice.taxAmount}")
        println("Grand Total: $${invoice.totalAmount + invoice.taxAmount}")
        println("Payment Method: ${invoice.paymentMethod}")
        println("Status: ${invoice.paymentStatus}")
    }

    fun updateAccountBalance(account: Account, amount: Double, type: String) {
        if (type == "DEBIT") {
            account.balance -= amount
            account.lastDebitDate = Date()
            account.debitCount++
        } else {
            account.balance += amount
            account.lastCreditDate = Date()
            account.creditCount++
        }
        account.lastTransactionDate = Date()
    }
}

data class Order(
    val subtotal: Double,
    val items: List<String>,
    val type: String,
    val express: Boolean,
    val shippingAddress: String
)

data class Invoice(
    val invoiceNumber: String,
    val invoiceDate: Date,
    val customerName: String,
    val totalAmount: Double,
    val taxAmount: Double,
    val paymentMethod: String,
    val paymentStatus: String
)

class Account(
    var balance: Double,
    var lastDebitDate: Date?,
    var lastCreditDate: Date?,
    var lastTransactionDate: Date?,
    var debitCount: Int,
    var creditCount: Int
)
