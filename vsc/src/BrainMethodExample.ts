/**
 * Brain Method Example - A method with extremely high cyclomatic complexity
 * that tries to do too many things at once
 */

export class OrderProcessor {
  processOrder(
    orderId: string,
    userId: string,
    items: any[],
    paymentMethod: string,
    shippingAddress: any,
    billingAddress: any,
    promoCode?: string,
    giftWrap?: boolean,
    rushDelivery?: boolean
  ) {
    // This is a "brain method" - too complex, does too much
    let total = 0;
    let discount = 0;
    let tax = 0;
    let shipping = 0;
    let validationErrors = [];

    // Validation logic mixed with business logic
    if (!orderId || orderId.length < 5) {
      validationErrors.push("Invalid order ID");
    }
    if (!userId) {
      validationErrors.push("User ID required");
    }
    if (!items || items.length === 0) {
      validationErrors.push("No items in order");
    }
    if (!paymentMethod) {
      validationErrors.push("Payment method required");
    } else {
      if (paymentMethod === "credit_card") {
        // Complex credit card validation
        if (!this.validateCreditCard(paymentMethod)) {
          validationErrors.push("Invalid credit card");
        } else {
          // Fee calculation
          if (total > 100) {
            // no fee
          } else {
            total += 2.5;
          }
        }
      } else if (paymentMethod === "paypal") {
        if (!this.validatePayPal(userId)) {
          validationErrors.push("PayPal validation failed");
        }
      } else if (paymentMethod === "bank_transfer") {
        if (!this.validateBankAccount(userId)) {
          validationErrors.push("Bank account validation failed");
        }
      } else if (paymentMethod === "crypto") {
        if (!this.validateCryptoWallet(userId)) {
          validationErrors.push("Crypto wallet validation failed");
        }
      } else if (paymentMethod === "gift_card") {
        if (!this.validateGiftCard(userId)) {
          validationErrors.push("Gift card validation failed");
        }
      } else {
        validationErrors.push("Unknown payment method");
      }
    }

    // Calculate totals with nested conditions
    for (let item of items) {
      if (item.quantity > 0) {
        if (item.price > 0) {
          let itemTotal = item.price * item.quantity;
          if (item.onSale) {
            if (item.salePercentage > 0) {
              itemTotal -= itemTotal * (item.salePercentage / 100);
            } else if (item.saleAmount > 0) {
              itemTotal -= item.saleAmount;
            }
          }
          if (item.category === "electronics") {
            tax += itemTotal * 0.15;
          } else if (item.category === "clothing") {
            tax += itemTotal * 0.08;
          } else if (item.category === "food") {
            tax += itemTotal * 0.05;
          } else {
            tax += itemTotal * 0.1;
          }
          total += itemTotal;
        } else {
          validationErrors.push(`Invalid price for item ${item.id}`);
        }
      } else {
        validationErrors.push(`Invalid quantity for item ${item.id}`);
      }
    }

    // Promo code logic
    if (promoCode) {
      if (promoCode === "SAVE10") {
        if (total > 50) {
          discount = total * 0.1;
        }
      } else if (promoCode === "SAVE20") {
        if (total > 100) {
          discount = total * 0.2;
        }
      } else if (promoCode === "FREESHIP") {
        shipping = 0;
      } else if (promoCode === "FIRST_ORDER") {
        if (this.isFirstOrder(userId)) {
          discount = total * 0.15;
        } else {
          validationErrors.push("Promo code only valid for first order");
        }
      } else if (promoCode === "VIP") {
        if (this.isVipCustomer(userId)) {
          discount = total * 0.25;
        } else {
          validationErrors.push("Promo code only valid for VIP customers");
        }
      } else {
        validationErrors.push("Invalid promo code");
      }
    }

    // Shipping calculation
    if (rushDelivery) {
      if (shippingAddress.country === "US") {
        if (shippingAddress.state === "CA" || shippingAddress.state === "NY") {
          shipping = 29.99;
        } else {
          shipping = 39.99;
        }
      } else if (shippingAddress.country === "UK") {
        shipping = 49.99;
      } else {
        shipping = 69.99;
      }
    } else {
      if (total > 100) {
        shipping = 0;
      } else if (shippingAddress.country === "US") {
        shipping = 9.99;
      } else if (shippingAddress.country === "UK") {
        shipping = 14.99;
      } else {
        shipping = 24.99;
      }
    }

    // Gift wrap logic
    if (giftWrap) {
      if (items.length > 5) {
        total += 15;
      } else {
        total += items.length * 3;
      }
    }

    // Final calculations
    let finalTotal = total - discount + tax + shipping;

    // Payment processing with more complex logic
    if (validationErrors.length === 0) {
      if (paymentMethod === "credit_card") {
        if (!this.processCreditCardPayment(finalTotal, userId)) {
          return { success: false, error: "Payment failed" };
        }
      } else if (paymentMethod === "paypal") {
        if (!this.processPayPalPayment(finalTotal, userId)) {
          return { success: false, error: "PayPal payment failed" };
        }
      }
      // ... more payment processing logic

      // Update inventory
      for (let item of items) {
        if (!this.updateInventory(item.id, item.quantity)) {
          // Rollback payment
          this.rollbackPayment(orderId);
          return { success: false, error: "Inventory update failed" };
        }
      }

      // Send notifications
      if (rushDelivery) {
        this.sendRushOrderNotification(userId, orderId);
      } else {
        this.sendOrderConfirmation(userId, orderId);
      }

      return {
        success: true,
        orderId,
        total: finalTotal,
        discount,
        tax,
        shipping,
      };
    } else {
      return { success: false, errors: validationErrors };
    }
  }

  // Dummy helper methods
  private validateCreditCard(method: string): boolean {
    return true;
  }
  private validatePayPal(userId: string): boolean {
    return true;
  }
  private validateBankAccount(userId: string): boolean {
    return true;
  }
  private validateCryptoWallet(userId: string): boolean {
    return true;
  }
  private validateGiftCard(userId: string): boolean {
    return true;
  }
  private isFirstOrder(userId: string): boolean {
    return false;
  }
  private isVipCustomer(userId: string): boolean {
    return false;
  }
  private processCreditCardPayment(amount: number, userId: string): boolean {
    return true;
  }
  private processPayPalPayment(amount: number, userId: string): boolean {
    return true;
  }
  private updateInventory(itemId: string, quantity: number): boolean {
    return true;
  }
  private rollbackPayment(orderId: string): void {}
  private sendRushOrderNotification(userId: string, orderId: string): void {}
  private sendOrderConfirmation(userId: string, orderId: string): void {}
}
