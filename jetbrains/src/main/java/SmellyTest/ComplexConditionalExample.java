package SmellyTest;

/**
 * Complex Conditional Example - Overly complex boolean expressions
 * CodeScene flags complex conditionals that are hard to understand
 */
public class ComplexConditionalExample {

    // Complex boolean expression that's hard to understand
    public boolean canProcessOrder(String orderType, double amount, int customerAge,
                                  boolean hasValidCard, boolean hasShippingAddress,
                                  String country, boolean isPremium, int orderCount,
                                  boolean hasActiveSubscription, double accountBalance) {

        // This is a nightmare to read and understand
        if ((orderType.equals("STANDARD") && amount < 1000 && customerAge >= 18 && hasValidCard) ||
            (orderType.equals("EXPRESS") && amount < 5000 && customerAge >= 21 && hasValidCard && hasShippingAddress) ||
            (orderType.equals("PREMIUM") && isPremium && hasValidCard && hasShippingAddress &&
             (country.equals("US") || country.equals("CA") || country.equals("UK"))) ||
            (orderCount > 50 && hasActiveSubscription && accountBalance > amount * 1.5 && customerAge >= 18)) {
            return true;
        }

        return false;
    }

    // Another complex conditional with nested logic
    public String determineShippingMethod(double weight, String destination, boolean isFragile,
                                         boolean isPerishable, int deliveryDays, double value,
                                         boolean isInsured, String customerType) {

        if (((weight > 50 && destination.equals("INTERNATIONAL")) ||
             (weight > 100 && !destination.equals("LOCAL"))) &&
            (isFragile || isPerishable) &&
            (deliveryDays <= 3 || (customerType.equals("PREMIUM") && deliveryDays <= 5)) &&
            ((isInsured && value > 1000) || (!isInsured && value < 100))) {
            return "SPECIALIZED_CARRIER";
        } else if ((weight <= 50 || destination.equals("LOCAL")) &&
                   !isFragile && !isPerishable &&
                   deliveryDays > 5 &&
                   value < 1000) {
            return "STANDARD_CARRIER";
        } else {
            return "EXPRESS_CARRIER";
        }
    }

    // Complex nested conditionals
    public double calculateDiscount(String customerType, double orderAmount, int itemCount,
                                   boolean hasPromoCode, String promoCode, boolean isHoliday,
                                   boolean isFirstOrder, int previousOrderCount) {

        double discount = 0.0;

        if (customerType.equals("PREMIUM") &&
            ((orderAmount > 100 && itemCount > 5) || (orderAmount > 500)) &&
            (hasPromoCode && (promoCode.equals("SAVE20") || promoCode.equals("VIP")) || !hasPromoCode) &&
            (isHoliday || isFirstOrder || previousOrderCount > 10)) {

            if (orderAmount > 1000 && itemCount > 20 && previousOrderCount > 50) {
                discount = 0.30;
            } else if (orderAmount > 500 && (itemCount > 10 || previousOrderCount > 25)) {
                discount = 0.20;
            } else {
                discount = 0.15;
            }
        } else if (customerType.equals("REGULAR") &&
                   orderAmount > 50 &&
                   (hasPromoCode || isFirstOrder || (isHoliday && previousOrderCount > 5))) {

            if (hasPromoCode && promoCode.equals("SAVE20")) {
                discount = 0.20;
            } else if (isFirstOrder && orderAmount > 100) {
                discount = 0.10;
            } else {
                discount = 0.05;
            }
        }

        return discount * orderAmount;
    }
}
