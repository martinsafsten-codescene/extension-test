package SmellyTest;

import java.util.*;

/**
 * Brain Method Example - A method that does too much and has high complexity
 * CodeScene flags methods with high cyclomatic complexity and excessive lines
 */
public class BrainMethodExample {

    // This massive method does way too much - processes orders, validates, calculates, emails, logs, etc.
    public void processOrder(String orderId, String customerId, List<String> items,
                            String paymentMethod, String shippingAddress, boolean isExpress) {

        // Validation logic
        if (orderId == null || orderId.isEmpty()) {
            System.err.println("Invalid order ID");
            return;
        }

        if (customerId == null || customerId.isEmpty()) {
            System.err.println("Invalid customer ID");
            return;
        }

        // Customer lookup
        Map<String, Object> customer = null;
        if (customerId.startsWith("PREM")) {
            customer = lookupPremiumCustomer(customerId);
            if (customer == null) {
                System.err.println("Premium customer not found");
                return;
            }
        } else if (customerId.startsWith("REG")) {
            customer = lookupRegularCustomer(customerId);
            if (customer == null) {
                System.err.println("Regular customer not found");
                return;
            }
        } else {
            customer = lookupGuestCustomer(customerId);
        }

        // Calculate totals
        double subtotal = 0.0;
        double tax = 0.0;
        double shipping = 0.0;
        double discount = 0.0;

        for (String item : items) {
            if (item.startsWith("BOOK")) {
                subtotal += 15.99;
            } else if (item.startsWith("DVD")) {
                subtotal += 19.99;
            } else if (item.startsWith("GAME")) {
                subtotal += 59.99;
            } else if (item.startsWith("TOY")) {
                subtotal += 29.99;
            } else {
                subtotal += 9.99;
            }
        }

        // Apply discounts
        if (customer != null && customer.get("type").equals("PREMIUM")) {
            if (subtotal > 100) {
                discount = subtotal * 0.20;
            } else if (subtotal > 50) {
                discount = subtotal * 0.15;
            } else {
                discount = subtotal * 0.10;
            }
        } else if (customer != null && customer.get("type").equals("REGULAR")) {
            if (subtotal > 100) {
                discount = subtotal * 0.10;
            } else if (subtotal > 50) {
                discount = subtotal * 0.05;
            }
        }

        // Calculate shipping
        if (isExpress) {
            if (shippingAddress.contains("NY")) {
                shipping = 15.99;
            } else if (shippingAddress.contains("CA")) {
                shipping = 19.99;
            } else {
                shipping = 24.99;
            }
        } else {
            if (shippingAddress.contains("NY")) {
                shipping = 5.99;
            } else if (shippingAddress.contains("CA")) {
                shipping = 7.99;
            } else {
                shipping = 9.99;
            }
        }

        // Calculate tax based on state
        if (shippingAddress.contains("NY")) {
            tax = subtotal * 0.08875;
        } else if (shippingAddress.contains("CA")) {
            tax = subtotal * 0.0725;
        } else if (shippingAddress.contains("TX")) {
            tax = subtotal * 0.0625;
        } else {
            tax = subtotal * 0.06;
        }

        double total = subtotal - discount + tax + shipping;

        // Payment processing
        boolean paymentSuccess = false;
        if (paymentMethod.equals("CREDIT_CARD")) {
            if (total < 10000) {
                paymentSuccess = processCreditCard(customerId, total);
            } else {
                System.err.println("Amount too high for credit card");
                return;
            }
        } else if (paymentMethod.equals("PAYPAL")) {
            paymentSuccess = processPayPal(customerId, total);
        } else if (paymentMethod.equals("BITCOIN")) {
            if (total > 100) {
                paymentSuccess = processBitcoin(customerId, total);
            } else {
                System.err.println("Bitcoin payment too small");
                return;
            }
        } else {
            System.err.println("Unknown payment method");
            return;
        }

        if (!paymentSuccess) {
            System.err.println("Payment failed");
            sendPaymentFailureEmail(customerId);
            logPaymentFailure(orderId, customerId, total);
            return;
        }

        // Update inventory
        for (String item : items) {
            updateInventory(item, -1);
        }

        // Send confirmation emails
        sendOrderConfirmationEmail(customerId, orderId, total);
        if (isExpress) {
            sendExpressShippingEmail(customerId, orderId);
        }

        // Log everything
        logOrderCreated(orderId, customerId, total);
        logPaymentProcessed(orderId, paymentMethod, total);
        logShippingScheduled(orderId, shippingAddress, isExpress);

        // Update customer stats
        updateCustomerOrderCount(customerId);
        updateCustomerLifetimeValue(customerId, total);

        System.out.println("Order processed successfully: " + orderId);
    }

    private Map<String, Object> lookupPremiumCustomer(String id) { return new HashMap<>(); }
    private Map<String, Object> lookupRegularCustomer(String id) { return new HashMap<>(); }
    private Map<String, Object> lookupGuestCustomer(String id) { return new HashMap<>(); }
    private boolean processCreditCard(String customerId, double amount) { return true; }
    private boolean processPayPal(String customerId, double amount) { return true; }
    private boolean processBitcoin(String customerId, double amount) { return true; }
    private void sendPaymentFailureEmail(String customerId) {}
    private void sendOrderConfirmationEmail(String customerId, String orderId, double total) {}
    private void sendExpressShippingEmail(String customerId, String orderId) {}
    private void logPaymentFailure(String orderId, String customerId, double total) {}
    private void logOrderCreated(String orderId, String customerId, double total) {}
    private void logPaymentProcessed(String orderId, String method, double total) {}
    private void logShippingScheduled(String orderId, String address, boolean express) {}
    private void updateInventory(String item, int change) {}
    private void updateCustomerOrderCount(String customerId) {}
    private void updateCustomerLifetimeValue(String customerId, double amount) {}
}
