/**
 * Duplicated Code - Copy-pasted or highly similar code.
 * CodeScene detects code duplication across methods and classes.
 */

export class DuplicatedCodeExample {
  validateAdminUser(username, password, email) {
    if (!username?.trim()) {
      console.error("Username is required");
      return false;
    }
    if (username.length < 3) {
      console.error("Username too short");
      return false;
    }
    if (!password?.trim()) {
      console.error("Password is required");
      return false;
    }
    if (password.length < 8) {
      console.error("Password too short");
      return false;
    }
    if (!email?.trim()) {
      console.error("Email is required");
      return false;
    }
    if (!email.includes("@")) {
      console.error("Invalid email");
      return false;
    }
    return true;
  }

  validateRegularUser(username, password, email) {
    if (!username?.trim()) {
      console.error("Username is required");
      return false;
    }
    if (username.length < 3) {
      console.error("Username too short");
      return false;
    }
    if (!password?.trim()) {
      console.error("Password is required");
      return false;
    }
    if (password.length < 8) {
      console.error("Password too short");
      return false;
    }
    if (!email?.trim()) {
      console.error("Email is required");
      return false;
    }
    if (!email.includes("@")) {
      console.error("Invalid email");
      return false;
    }
    return true;
  }

  saveUserToDatabase(userId, name, email) {
    try {
      console.log("Opening database connection...");
      console.log("Starting transaction...");
      console.log(`Inserting user: ${userId}`);
      console.log("Committing transaction...");
      console.log("Closing database connection...");
    } catch (e) {
      console.error(`Error saving user: ${e.message}`);
      console.log("Rolling back transaction...");
      console.log("Closing database connection...");
    }
  }

  saveProductToDatabase(productId, name, price) {
    try {
      console.log("Opening database connection...");
      console.log("Starting transaction...");
      console.log(`Inserting product: ${productId}`);
      console.log("Committing transaction...");
      console.log("Closing database connection...");
    } catch (e) {
      console.error(`Error saving product: ${e.message}`);
      console.log("Rolling back transaction...");
      console.log("Closing database connection...");
    }
  }

  saveOrderToDatabase(orderId, userId, total) {
    try {
      console.log("Opening database connection...");
      console.log("Starting transaction...");
      console.log(`Inserting order: ${orderId}`);
      console.log("Committing transaction...");
      console.log("Closing database connection...");
    } catch (e) {
      console.error(`Error saving order: ${e.message}`);
      console.log("Rolling back transaction...");
      console.log("Closing database connection...");
    }
  }

  calculateTotalForPremiumCustomer(prices) {
    const subtotal = prices.reduce((a, b) => a + b, 0);
    const discount = subtotal * 0.15;
    const tax = subtotal * 0.08;
    return subtotal - discount + tax;
  }

  calculateTotalForRegularCustomer(prices) {
    const subtotal = prices.reduce((a, b) => a + b, 0);
    const discount = subtotal * 0.05;
    const tax = subtotal * 0.08;
    return subtotal - discount + tax;
  }

  calculateTotalForGuestCustomer(prices) {
    const subtotal = prices.reduce((a, b) => a + b, 0);
    const tax = subtotal * 0.08;
    return subtotal + tax;
  }
}
