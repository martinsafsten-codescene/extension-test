/**
 * Brain Method - A method that does too much and has high complexity.
 * CodeScene flags methods with high cyclomatic complexity and excessive lines.
 */

export class BrainMethodExample {
  processOrder(
    orderId,
    customerId,
    items,
    paymentMethod,
    shippingAddress,
    isExpress
  ) {
    if (!orderId?.trim()) {
      console.error("Invalid order ID");
      return;
    }
    if (!customerId?.trim()) {
      console.error("Invalid customer ID");
      return;
    }

    let customer;
    if (customerId.startsWith("PREM")) {
      customer = this.lookupPremiumCustomer(customerId);
      if (!customer) {
        console.error("Premium customer not found");
        return;
      }
    } else if (customerId.startsWith("REG")) {
      customer = this.lookupRegularCustomer(customerId);
      if (!customer) {
        console.error("Regular customer not found");
        return;
      }
    } else {
      customer = this.lookupGuestCustomer(customerId);
    }

    let subtotal = 0;
    for (const item of items) {
      if (item.startsWith("BOOK")) subtotal += 15.99;
      else if (item.startsWith("DVD")) subtotal += 19.99;
      else if (item.startsWith("GAME")) subtotal += 59.99;
      else if (item.startsWith("TOY")) subtotal += 29.99;
      else subtotal += 9.99;
    }

    let discount = 0;
    if (customer.type === "PREMIUM") {
      if (subtotal > 100) discount = subtotal * 0.2;
      else if (subtotal > 50) discount = subtotal * 0.15;
      else discount = subtotal * 0.1;
    } else if (customer.type === "REGULAR") {
      if (subtotal > 100) discount = subtotal * 0.1;
      else if (subtotal > 50) discount = subtotal * 0.05;
    }

    let shipping = 0;
    if (isExpress) {
      if (shippingAddress.includes("NY")) shipping = 15.99;
      else if (shippingAddress.includes("CA")) shipping = 19.99;
      else shipping = 24.99;
    } else {
      if (shippingAddress.includes("NY")) shipping = 5.99;
      else if (shippingAddress.includes("CA")) shipping = 7.99;
      else shipping = 9.99;
    }

    let tax = 0;
    if (shippingAddress.includes("NY")) tax = subtotal * 0.08875;
    else if (shippingAddress.includes("CA")) tax = subtotal * 0.0725;
    else if (shippingAddress.includes("TX")) tax = subtotal * 0.0625;
    else tax = subtotal * 0.06;

    const total = subtotal - discount + tax + shipping;

    let paymentSuccess = false;
    if (paymentMethod === "CREDIT_CARD") {
      if (total < 10000)
        paymentSuccess = this.processCreditCard(customerId, total);
      else {
        console.error("Amount too high for credit card");
        return;
      }
    } else if (paymentMethod === "PAYPAL") {
      paymentSuccess = this.processPayPal(customerId, total);
    } else if (paymentMethod === "BITCOIN") {
      if (total > 100) paymentSuccess = this.processBitcoin(customerId, total);
      else {
        console.error("Bitcoin payment too small");
        return;
      }
    } else {
      console.error("Unknown payment method");
      return;
    }

    if (!paymentSuccess) {
      console.error("Payment failed");
      this.sendPaymentFailureEmail(customerId);
      this.logPaymentFailure(orderId, customerId, total);
      return;
    }

    items.forEach((item) => this.updateInventory(item, -1));
    this.sendOrderConfirmationEmail(customerId, orderId, total);
    if (isExpress) this.sendExpressShippingEmail(customerId, orderId);
    this.logOrderCreated(orderId, customerId, total);
    this.logPaymentProcessed(orderId, paymentMethod, total);
    this.logShippingScheduled(orderId, shippingAddress, isExpress);
    this.updateCustomerOrderCount(customerId);
    this.updateCustomerLifetimeValue(customerId, total);
    console.log(`Order processed successfully: ${orderId}`);
  }

  lookupPremiumCustomer(id) {
    return { type: "PREMIUM" };
  }
  lookupRegularCustomer(id) {
    return { type: "REGULAR" };
  }
  lookupGuestCustomer(id) {
    return { type: "GUEST" };
  }
  processCreditCard() {
    return true;
  }
  processPayPal() {
    return true;
  }
  processBitcoin() {
    return true;
  }
  sendPaymentFailureEmail() {}
  sendOrderConfirmationEmail() {}
  sendExpressShippingEmail() {}
  logPaymentFailure() {}
  logOrderCreated() {}
  logPaymentProcessed() {}
  logShippingScheduled() {}
  updateInventory() {}
  updateCustomerOrderCount() {}
  updateCustomerLifetimeValue() {}
}
