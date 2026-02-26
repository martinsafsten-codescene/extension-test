/**
 * Primitive Obsession Example - Overuse of primitive types
 * instead of creating small, meaningful objects
 */

export class OrderServiceExample {
  // Everything is represented as primitives - code smell!

  createOrder(
    // Using primitives for complex concepts
    customerEmail: string, // Should be an Email object
    customerPhone: string, // Should be a PhoneNumber object
    streetAddress: string, // Should be an Address object
    city: string,
    state: string,
    zipCode: string,
    country: string,
    cardNumber: string, // Should be a CreditCard object
    cardholderName: string,
    expiryMonth: number,
    expiryYear: number,
    cvv: string,
    amountInCents: number, // Should be a Money object
    currency: string,
    latitude: number, // Should be a Location object
    longitude: number,
    shippingOption: string // Should be an enum or ShippingOption object
  ) {
    // Validation scattered throughout using primitives
    if (!customerEmail.includes("@")) {
      throw new Error("Invalid email");
    }

    if (customerPhone.length < 10) {
      throw new Error("Invalid phone");
    }

    if (cardNumber.length !== 16) {
      throw new Error("Invalid card number");
    }

    if (expiryMonth < 1 || expiryMonth > 12) {
      throw new Error("Invalid expiry month");
    }

    if (amountInCents < 0) {
      throw new Error("Invalid amount");
    }

    // Business logic dealing with primitives
    console.log(`Processing order for ${customerEmail}`);
    console.log(`Amount: ${amountInCents / 100} ${currency}`);
    console.log(`Card: ${cardNumber.substring(0, 4)}****`);
  }

  calculateShipping(
    originZipCode: string,
    destinationZipCode: string,
    weightInGrams: number,
    lengthInCm: number,
    widthInCm: number,
    heightInCm: number,
    isFragile: boolean,
    isHazmat: boolean,
    shippingSpeed: string // "standard", "express", "overnight"
  ): number {
    // Complex calculations with primitives
    let baseCost = 5.0;

    if (weightInGrams > 1000) {
      baseCost += (weightInGrams - 1000) * 0.001;
    }

    const volume = lengthInCm * widthInCm * heightInCm;
    if (volume > 1000) {
      baseCost += volume * 0.0001;
    }

    if (isFragile) {
      baseCost *= 1.5;
    }

    if (isHazmat) {
      baseCost *= 2.0;
    }

    if (shippingSpeed === "express") {
      baseCost *= 2.0;
    } else if (shippingSpeed === "overnight") {
      baseCost *= 3.0;
    }

    return baseCost;
  }

  validateAddress(
    street: string,
    city: string,
    state: string,
    zipCode: string,
    country: string,
    apartmentNumber: string,
    building: string,
    floor: string
  ): boolean {
    // Validation logic scattered across many primitive parameters
    if (!street || street.length < 3) return false;
    if (!city || city.length < 2) return false;
    if (!state || state.length !== 2) return false;
    if (!zipCode || zipCode.length !== 5) return false;
    if (!country || country.length !== 2) return false;
    return true;
  }

  processPayment(
    cardNumber: string,
    cardholderName: string,
    expiryMonth: number,
    expiryYear: number,
    cvv: string,
    billingStreet: string,
    billingCity: string,
    billingState: string,
    billingZipCode: string,
    billingCountry: string,
    amountInCents: number,
    currency: string,
    merchantId: string,
    transactionId: string,
    timestamp: number,
    ipAddress: string,
    userAgent: string
  ): boolean {
    // Too many primitives representing complex concepts
    console.log(`Processing ${amountInCents / 100} ${currency}`);
    console.log(`Card: ${cardNumber.substring(0, 4)}****${cardNumber.substring(12)}`);
    console.log(`Billing: ${billingStreet}, ${billingCity}, ${billingState}`);
    return true;
  }

  calculateTax(
    subtotalInCents: number,
    currency: string,
    state: string,
    city: string,
    zipCode: string,
    productCategory: string,
    isExempt: boolean,
    exemptionReason: string
  ): number {
    // Tax calculation with primitives
    if (isExempt) return 0;

    let taxRate = 0.0;

    // State tax rates as magic numbers
    if (state === "CA") {
      taxRate = 0.0725;
    } else if (state === "NY") {
      taxRate = 0.0825;
    } else if (state === "TX") {
      taxRate = 0.0625;
    }

    // Category-specific rates
    if (productCategory === "food") {
      taxRate *= 0.5;
    } else if (productCategory === "alcohol") {
      taxRate *= 1.5;
    }

    return Math.round(subtotalInCents * taxRate);
  }

  calculateDiscount(
    originalPriceInCents: number,
    currency: string,
    discountType: string, // "percentage", "fixed", "bogo"
    discountValue: number,
    minimumPurchaseInCents: number,
    maximumDiscountInCents: number,
    customerTier: string, // "bronze", "silver", "gold", "platinum"
    isFirstPurchase: boolean,
    promoCode: string
  ): number {
    // Discount logic with many primitive checks
    if (originalPriceInCents < minimumPurchaseInCents) {
      return 0;
    }

    let discount = 0;

    if (discountType === "percentage") {
      discount = Math.round(originalPriceInCents * (discountValue / 100));
    } else if (discountType === "fixed") {
      discount = discountValue;
    }

    // Customer tier multipliers as magic numbers
    if (customerTier === "gold") {
      discount = Math.round(discount * 1.1);
    } else if (customerTier === "platinum") {
      discount = Math.round(discount * 1.2);
    }

    if (isFirstPurchase) {
      discount = Math.round(discount * 1.15);
    }

    if (discount > maximumDiscountInCents) {
      discount = maximumDiscountInCents;
    }

    return discount;
  }

  scheduleDelivery(
    orderId: string,
    deliveryDate: string, // Should be a Date object
    timeWindowStart: string, // "09:00"
    timeWindowEnd: string, // "17:00"
    preferredTimeSlot: string, // "morning", "afternoon", "evening"
    deliveryInstructions: string,
    requiresSignature: boolean,
    allowWeekendDelivery: boolean,
    allowHolidayDelivery: boolean
  ): boolean {
    // Date/time handling with string primitives - error-prone
    console.log(`Scheduling delivery for ${deliveryDate} between ${timeWindowStart} and ${timeWindowEnd}`);
    return true;
  }
}
