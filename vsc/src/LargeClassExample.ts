/**
 * Large Class / God Class Example - A class that does too many things
 * This violates Single Responsibility Principle
 */

export class ApplicationManagerExample {
  private users: any[] = [];
  private products: any[] = [];
  private orders: any[] = [];
  private inventory: any[] = [];
  private payments: any[] = [];
  private notifications: any[] = [];
  private logs: any[] = [];
  private cache: Map<string, any> = new Map();
  private config: any = {};
  private database: any;
  private emailService: any;
  private smsService: any;
  private analyticsService: any;

  // User Management
  createUser(userData: any) {
    this.users.push(userData);
    this.logAction("User created", userData.id);
    this.sendWelcomeEmail(userData.email);
    this.trackAnalytics("user_created", userData.id);
  }

  updateUser(userId: string, updates: any) {
    const user = this.users.find((u) => u.id === userId);
    Object.assign(user, updates);
    this.invalidateCache(`user:${userId}`);
    this.logAction("User updated", userId);
  }

  deleteUser(userId: string) {
    this.users = this.users.filter((u) => u.id !== userId);
    this.logAction("User deleted", userId);
    this.sendGoodbyeEmail(userId);
  }

  getUserById(userId: string) {
    const cached = this.cache.get(`user:${userId}`);
    if (cached) return cached;
    const user = this.users.find((u) => u.id === userId);
    this.cache.set(`user:${userId}`, user);
    return user;
  }

  authenticateUser(email: string, password: string) {
    const user = this.users.find((u) => u.email === email);
    if (user && this.hashPassword(password) === user.password) {
      this.logAction("User authenticated", user.id);
      return this.generateToken(user);
    }
    return null;
  }

  // Product Management
  createProduct(productData: any) {
    this.products.push(productData);
    this.updateInventory(productData.id, productData.stock);
    this.logAction("Product created", productData.id);
    this.notifyAdmins("New product added", productData.name);
  }

  updateProduct(productId: string, updates: any) {
    const product = this.products.find((p) => p.id === productId);
    Object.assign(product, updates);
    this.invalidateCache(`product:${productId}`);
    this.logAction("Product updated", productId);
  }

  deleteProduct(productId: string) {
    this.products = this.products.filter((p) => p.id !== productId);
    this.removeFromInventory(productId);
    this.logAction("Product deleted", productId);
  }

  searchProducts(query: string, filters: any) {
    let results = this.products.filter((p) =>
      p.name.toLowerCase().includes(query.toLowerCase())
    );
    if (filters.category) {
      results = results.filter((p) => p.category === filters.category);
    }
    if (filters.minPrice) {
      results = results.filter((p) => p.price >= filters.minPrice);
    }
    if (filters.maxPrice) {
      results = results.filter((p) => p.price <= filters.maxPrice);
    }
    this.trackAnalytics("product_search", query);
    return results;
  }

  // Order Management
  createOrder(orderData: any) {
    const order = { ...orderData, id: this.generateOrderId() };
    this.orders.push(order);
    this.processPayment(order.paymentInfo);
    this.updateInventoryForOrder(order.items);
    this.sendOrderConfirmation(order.userId, order.id);
    this.logAction("Order created", order.id);
    this.trackAnalytics("order_placed", order.id);
    return order;
  }

  updateOrderStatus(orderId: string, status: string) {
    const order = this.orders.find((o) => o.id === orderId);
    order.status = status;
    this.sendStatusUpdateNotification(order.userId, orderId, status);
    this.logAction("Order status updated", orderId);
  }

  cancelOrder(orderId: string) {
    const order = this.orders.find((o) => o.id === orderId);
    order.status = "cancelled";
    this.refundPayment(order.paymentId);
    this.restoreInventoryForOrder(order.items);
    this.sendCancellationEmail(order.userId, orderId);
    this.logAction("Order cancelled", orderId);
  }

  // Inventory Management
  updateInventory(productId: string, quantity: number) {
    const item = this.inventory.find((i) => i.productId === productId);
    if (item) {
      item.quantity = quantity;
    } else {
      this.inventory.push({ productId, quantity });
    }
    if (quantity < 10) {
      this.notifyAdmins("Low inventory", productId);
    }
    this.logAction("Inventory updated", productId);
  }

  checkInventory(productId: string) {
    const item = this.inventory.find((i) => i.productId === productId);
    return item ? item.quantity : 0;
  }

  removeFromInventory(productId: string) {
    this.inventory = this.inventory.filter((i) => i.productId !== productId);
  }

  updateInventoryForOrder(items: any[]) {
    items.forEach((item) => {
      const current = this.checkInventory(item.productId);
      this.updateInventory(item.productId, current - item.quantity);
    });
  }

  restoreInventoryForOrder(items: any[]) {
    items.forEach((item) => {
      const current = this.checkInventory(item.productId);
      this.updateInventory(item.productId, current + item.quantity);
    });
  }

  // Payment Processing
  processPayment(paymentInfo: any) {
    const payment = {
      id: this.generatePaymentId(),
      ...paymentInfo,
      status: "processing",
    };
    this.payments.push(payment);
    // Simulate payment processing
    setTimeout(() => {
      payment.status = "completed";
      this.logAction("Payment processed", payment.id);
    }, 1000);
    return payment.id;
  }

  refundPayment(paymentId: string) {
    const payment = this.payments.find((p) => p.id === paymentId);
    payment.status = "refunded";
    this.logAction("Payment refunded", paymentId);
  }

  // Notification System
  sendWelcomeEmail(email: string) {
    this.notifications.push({ type: "email", to: email, template: "welcome" });
    this.emailService.send(email, "Welcome!", "Welcome to our platform");
  }

  sendOrderConfirmation(userId: string, orderId: string) {
    const user = this.getUserById(userId);
    this.emailService.send(user.email, "Order Confirmation", `Order ${orderId}`);
    this.smsService.send(user.phone, `Order ${orderId} confirmed`);
  }

  sendStatusUpdateNotification(userId: string, orderId: string, status: string) {
    const user = this.getUserById(userId);
    this.emailService.send(user.email, "Order Update", `Status: ${status}`);
  }

  sendCancellationEmail(userId: string, orderId: string) {
    const user = this.getUserById(userId);
    this.emailService.send(user.email, "Order Cancelled", `Order ${orderId}`);
  }

  sendGoodbyeEmail(userId: string) {
    const user = this.getUserById(userId);
    this.emailService.send(user.email, "Goodbye", "We'll miss you");
  }

  notifyAdmins(subject: string, message: string) {
    const admins = this.users.filter((u) => u.role === "admin");
    admins.forEach((admin) => {
      this.emailService.send(admin.email, subject, message);
    });
  }

  // Analytics
  trackAnalytics(event: string, data: any) {
    this.analyticsService.track(event, data);
    this.logAction("Analytics tracked", event);
  }

  // Logging
  logAction(action: string, details: any) {
    this.logs.push({ timestamp: new Date(), action, details });
  }

  getLogs(filter?: any) {
    return filter ? this.logs.filter((log) => log.action === filter.action) : this.logs;
  }

  // Cache Management
  invalidateCache(key: string) {
    this.cache.delete(key);
  }

  clearCache() {
    this.cache.clear();
  }

  // Configuration
  getConfig(key: string) {
    return this.config[key];
  }

  setConfig(key: string, value: any) {
    this.config[key] = value;
    this.logAction("Config updated", key);
  }

  // Utility Methods
  private generateOrderId(): string {
    return `ORD-${Date.now()}`;
  }

  private generatePaymentId(): string {
    return `PAY-${Date.now()}`;
  }

  private generateToken(user: any): string {
    return `TOKEN-${user.id}-${Date.now()}`;
  }

  private hashPassword(password: string): string {
    return `hashed-${password}`;
  }
}
