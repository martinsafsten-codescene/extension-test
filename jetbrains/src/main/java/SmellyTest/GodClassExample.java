package SmellyTest;

import java.util.*;
import java.io.*;

/**
 * God Class Example - A class that does everything and knows too much
 * CodeScene flags classes with too many responsibilities and dependencies
 */
public class GodClassExample {

    // User management fields
    private Map<String, User> users = new HashMap<>();
    private List<String> activeUsers = new ArrayList<>();

    // Product management fields
    private Map<String, Product> products = new HashMap<>();
    private Map<String, Integer> inventory = new HashMap<>();

    // Order management fields
    private Map<String, Order> orders = new HashMap<>();
    private List<Order> pendingOrders = new ArrayList<>();

    // Payment fields
    private String paymentGatewayUrl;
    private String merchantId;

    // Email fields
    private String smtpServer;
    private String emailFrom;

    // Database fields
    private String dbConnection;
    private String dbUsername;
    private String dbPassword;

    // Logging fields
    private FileWriter logFile;
    private boolean debugMode;

    // User Management Methods
    public void createUser(String id, String name, String email) {
        users.put(id, new User(id, name, email));
        activeUsers.add(id);
        logActivity("User created: " + id);
    }

    public User getUser(String id) { return users.get(id); }

    public void deleteUser(String id) {
        users.remove(id);
        activeUsers.remove(id);
        logActivity("User deleted: " + id);
    }

    public void updateUserEmail(String id, String newEmail) {
        User user = users.get(id);
        user.setEmail(newEmail);
        sendEmailConfirmation(user);
    }

    // Product Management Methods
    public void addProduct(String id, String name, double price) {
        products.put(id, new Product(id, name, price));
        inventory.put(id, 0);
        logActivity("Product added: " + id);
    }

    public void updateInventory(String productId, int quantity) {
        inventory.put(productId, inventory.getOrDefault(productId, 0) + quantity);
    }

    public boolean checkInventory(String productId, int needed) {
        return inventory.getOrDefault(productId, 0) >= needed;
    }

    // Order Management Methods
    public void createOrder(String orderId, String userId, List<String> productIds) {
        Order order = new Order(orderId, userId, productIds);
        orders.put(orderId, order);
        pendingOrders.add(order);

        // Reduce inventory
        for (String productId : productIds) {
            updateInventory(productId, -1);
        }

        logActivity("Order created: " + orderId);
    }

    public void processOrder(String orderId) {
        Order order = orders.get(orderId);
        if (processPayment(order)) {
            order.setStatus("PAID");
            sendOrderConfirmation(order);
            saveOrderToDatabase(order);
        }
    }

    public void shipOrder(String orderId) {
        Order order = orders.get(orderId);
        order.setStatus("SHIPPED");
        sendShippingNotification(order);
        pendingOrders.remove(order);
    }

    // Payment Methods
    public boolean processPayment(Order order) {
        try {
            // Connect to payment gateway
            logActivity("Processing payment for order: " + order.getId());
            return true; // Simulated success
        } catch (Exception e) {
            logError("Payment failed: " + e.getMessage());
            return false;
        }
    }

    public void refundPayment(String orderId, double amount) {
        logActivity("Refunding " + amount + " for order " + orderId);
    }

    // Email Methods
    public void sendEmailConfirmation(User user) {
        logActivity("Sending email to: " + user.getEmail());
    }

    public void sendOrderConfirmation(Order order) {
        User user = users.get(order.getUserId());
        logActivity("Sending order confirmation to: " + user.getEmail());
    }

    public void sendShippingNotification(Order order) {
        User user = users.get(order.getUserId());
        logActivity("Sending shipping notification to: " + user.getEmail());
    }

    // Database Methods
    public void connectToDatabase() {
        logActivity("Connecting to database: " + dbConnection);
    }

    public void saveUserToDatabase(User user) {
        logActivity("Saving user to database: " + user.getId());
    }

    public void saveOrderToDatabase(Order order) {
        logActivity("Saving order to database: " + order.getId());
    }

    public void saveProductToDatabase(Product product) {
        logActivity("Saving product to database: " + product.getId());
    }

    // Logging Methods
    public void logActivity(String message) {
        System.out.println("[INFO] " + message);
    }

    public void logError(String message) {
        System.err.println("[ERROR] " + message);
    }

    // Report Generation Methods
    public String generateSalesReport() {
        return "Sales Report: " + orders.size() + " orders";
    }

    public String generateInventoryReport() {
        return "Inventory Report: " + products.size() + " products";
    }

    public String generateUserReport() {
        return "User Report: " + users.size() + " users";
    }

    // Inner classes
    class User {
        private String id, name, email;
        User(String id, String name, String email) { this.id = id; this.name = name; this.email = email; }
        String getId() { return id; }
        String getEmail() { return email; }
        void setEmail(String email) { this.email = email; }
    }

    class Product {
        private String id, name;
        private double price;
        Product(String id, String name, double price) { this.id = id; this.name = name; this.price = price; }
        String getId() { return id; }
    }

    class Order {
        private String id, userId, status;
        private List<String> productIds;
        Order(String id, String userId, List<String> productIds) {
            this.id = id; this.userId = userId; this.productIds = productIds;
        }
        String getId() { return id; }
        String getUserId() { return userId; }
        void setStatus(String status) { this.status = status; }
    }
}
