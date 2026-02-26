package SmellyTest;

import java.util.*;

/**
 * Duplicated Code Example - Copy-pasted or highly similar code
 * CodeScene detects code duplication across methods and classes
 */
public class DuplicatedCodeExample {

    // Duplicated validation logic
    public boolean validateAdminUser(String username, String password, String email) {
        if (username == null || username.isEmpty()) {
            System.err.println("Username is required");
            return false;
        }
        if (username.length() < 3) {
            System.err.println("Username too short");
            return false;
        }
        if (password == null || password.isEmpty()) {
            System.err.println("Password is required");
            return false;
        }
        if (password.length() < 8) {
            System.err.println("Password too short");
            return false;
        }
        if (email == null || email.isEmpty()) {
            System.err.println("Email is required");
            return false;
        }
        if (!email.contains("@")) {
            System.err.println("Invalid email");
            return false;
        }
        return true;
    }

    // Almost identical to validateAdminUser - duplicated code!
    public boolean validateRegularUser(String username, String password, String email) {
        if (username == null || username.isEmpty()) {
            System.err.println("Username is required");
            return false;
        }
        if (username.length() < 3) {
            System.err.println("Username too short");
            return false;
        }
        if (password == null || password.isEmpty()) {
            System.err.println("Password is required");
            return false;
        }
        if (password.length() < 8) {
            System.err.println("Password too short");
            return false;
        }
        if (email == null || email.isEmpty()) {
            System.err.println("Email is required");
            return false;
        }
        if (!email.contains("@")) {
            System.err.println("Invalid email");
            return false;
        }
        return true;
    }

    // More duplication - nearly identical database operations
    public void saveUserToDatabase(String userId, String name, String email) {
        try {
            System.out.println("Opening database connection...");
            System.out.println("Starting transaction...");
            System.out.println("Inserting user: " + userId);
            System.out.println("Committing transaction...");
            System.out.println("Closing database connection...");
        } catch (Exception e) {
            System.err.println("Error saving user: " + e.getMessage());
            System.out.println("Rolling back transaction...");
            System.out.println("Closing database connection...");
        }
    }

    public void saveProductToDatabase(String productId, String name, double price) {
        try {
            System.out.println("Opening database connection...");
            System.out.println("Starting transaction...");
            System.out.println("Inserting product: " + productId);
            System.out.println("Committing transaction...");
            System.out.println("Closing database connection...");
        } catch (Exception e) {
            System.err.println("Error saving product: " + e.getMessage());
            System.out.println("Rolling back transaction...");
            System.out.println("Closing database connection...");
        }
    }

    public void saveOrderToDatabase(String orderId, String userId, double total) {
        try {
            System.out.println("Opening database connection...");
            System.out.println("Starting transaction...");
            System.out.println("Inserting order: " + orderId);
            System.out.println("Committing transaction...");
            System.out.println("Closing database connection...");
        } catch (Exception e) {
            System.err.println("Error saving order: " + e.getMessage());
            System.out.println("Rolling back transaction...");
            System.out.println("Closing database connection...");
        }
    }

    // Duplicated calculation logic
    public double calculateTotalForPremiumCustomer(List<Double> prices) {
        double subtotal = 0.0;
        for (double price : prices) {
            subtotal += price;
        }
        double discount = subtotal * 0.15;
        double tax = subtotal * 0.08;
        return subtotal - discount + tax;
    }

    public double calculateTotalForRegularCustomer(List<Double> prices) {
        double subtotal = 0.0;
        for (double price : prices) {
            subtotal += price;
        }
        double discount = subtotal * 0.05;
        double tax = subtotal * 0.08;
        return subtotal - discount + tax;
    }

    public double calculateTotalForGuestCustomer(List<Double> prices) {
        double subtotal = 0.0;
        for (double price : prices) {
            subtotal += price;
        }
        double discount = 0.0;
        double tax = subtotal * 0.08;
        return subtotal - discount + tax;
    }
}
