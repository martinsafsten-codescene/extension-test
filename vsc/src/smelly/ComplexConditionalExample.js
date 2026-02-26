/**
 * Complex Conditional - Overly complex boolean expressions.
 * CodeScene flags complex conditionals that are hard to understand.
 */

export class ComplexConditionalExample {
  canProcessOrder(
    orderType,
    amount,
    customerAge,
    hasValidCard,
    hasShippingAddress,
    country,
    isPremium,
    orderCount,
    hasActiveSubscription,
    accountBalance
  ) {
    return (
      (orderType === "STANDARD" &&
        amount < 1000 &&
        customerAge >= 18 &&
        hasValidCard) ||
      (orderType === "EXPRESS" &&
        amount < 5000 &&
        customerAge >= 21 &&
        hasValidCard &&
        hasShippingAddress) ||
      (orderType === "PREMIUM" &&
        isPremium &&
        hasValidCard &&
        hasShippingAddress &&
        ["US", "CA", "UK"].includes(country)) ||
      (orderCount > 50 &&
        hasActiveSubscription &&
        accountBalance > amount * 1.5 &&
        customerAge >= 18)
    );
  }

  determineShippingMethod(
    weight,
    destination,
    isFragile,
    isPerishable,
    deliveryDays,
    value,
    isInsured,
    customerType
  ) {
    if (
      ((weight > 50 && destination === "INTERNATIONAL") ||
        (weight > 100 && destination !== "LOCAL")) &&
      (isFragile || isPerishable) &&
      (deliveryDays <= 3 ||
        (customerType === "PREMIUM" && deliveryDays <= 5)) &&
      ((isInsured && value > 1000) || (!isInsured && value < 100))
    ) {
      return "SPECIALIZED_CARRIER";
    }
    if (
      (weight <= 50 || destination === "LOCAL") &&
      !isFragile &&
      !isPerishable &&
      deliveryDays > 5 &&
      value < 1000
    ) {
      return "STANDARD_CARRIER";
    }
    return "EXPRESS_CARRIER";
  }

  calculateDiscount(
    customerType,
    orderAmount,
    itemCount,
    hasPromoCode,
    promoCode,
    isHoliday,
    isFirstOrder,
    previousOrderCount
  ) {
    let discount = 0;
    if (
      customerType === "PREMIUM" &&
      ((orderAmount > 100 && itemCount > 5) || orderAmount > 500) &&
      ((hasPromoCode && ["SAVE20", "VIP"].includes(promoCode)) ||
        !hasPromoCode) &&
      (isHoliday || isFirstOrder || previousOrderCount > 10)
    ) {
      if (orderAmount > 1000 && itemCount > 20 && previousOrderCount > 50)
        discount = 0.3;
      else if (orderAmount > 500 && (itemCount > 10 || previousOrderCount > 25))
        discount = 0.2;
      else discount = 0.15;
    } else if (
      customerType === "REGULAR" &&
      orderAmount > 50 &&
      (hasPromoCode || isFirstOrder || (isHoliday && previousOrderCount > 5))
    ) {
      if (hasPromoCode && promoCode === "SAVE20") discount = 0.2;
      else if (isFirstOrder && orderAmount > 100) discount = 0.1;
      else discount = 0.05;
    }
    return discount * orderAmount;
  }
}
