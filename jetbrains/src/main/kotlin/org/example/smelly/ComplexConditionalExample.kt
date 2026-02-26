package org.example.smelly

/**
 * Complex Conditional - Overly complex boolean expressions.
 * CodeScene flags complex conditionals that are hard to understand.
 */
class ComplexConditionalExample {

    fun canProcessOrder(
        orderType: String,
        amount: Double,
        customerAge: Int,
        hasValidCard: Boolean,
        hasShippingAddress: Boolean,
        country: String,
        isPremium: Boolean,
        orderCount: Int,
        hasActiveSubscription: Boolean,
        accountBalance: Double
    ): Boolean {
        return (orderType == "STANDARD" && amount < 1000 && customerAge >= 18 && hasValidCard) ||
                (orderType == "EXPRESS" && amount < 5000 && customerAge >= 21 && hasValidCard && hasShippingAddress) ||
                (orderType == "PREMIUM" && isPremium && hasValidCard && hasShippingAddress &&
                        country in listOf("US", "CA", "UK")) ||
                (orderCount > 50 && hasActiveSubscription && accountBalance > amount * 1.5 && customerAge >= 18)
    }

    fun determineShippingMethod(
        weight: Double,
        destination: String,
        isFragile: Boolean,
        isPerishable: Boolean,
        deliveryDays: Int,
        value: Double,
        isInsured: Boolean,
        customerType: String
    ): String {
        return when {
            ((weight > 50 && destination == "INTERNATIONAL") || (weight > 100 && destination != "LOCAL")) &&
                    (isFragile || isPerishable) &&
                    (deliveryDays <= 3 || (customerType == "PREMIUM" && deliveryDays <= 5)) &&
                    ((isInsured && value > 1000) || (!isInsured && value < 100)) -> "SPECIALIZED_CARRIER"
            (weight <= 50 || destination == "LOCAL") && !isFragile && !isPerishable &&
                    deliveryDays > 5 && value < 1000 -> "STANDARD_CARRIER"
            else -> "EXPRESS_CARRIER"
        }
    }

    fun calculateDiscount(
        customerType: String,
        orderAmount: Double,
        itemCount: Int,
        hasPromoCode: Boolean,
        promoCode: String,
        isHoliday: Boolean,
        isFirstOrder: Boolean,
        previousOrderCount: Int
    ): Double {
        var discount = 0.0
        if (customerType == "PREMIUM" &&
            ((orderAmount > 100 && itemCount > 5) || orderAmount > 500) &&
            (hasPromoCode && promoCode in listOf("SAVE20", "VIP") || !hasPromoCode) &&
            (isHoliday || isFirstOrder || previousOrderCount > 10)
        ) {
            discount = when {
                orderAmount > 1000 && itemCount > 20 && previousOrderCount > 50 -> 0.30
                orderAmount > 500 && (itemCount > 10 || previousOrderCount > 25) -> 0.20
                else -> 0.15
            }
        } else if (customerType == "REGULAR" && orderAmount > 50 &&
            (hasPromoCode || isFirstOrder || (isHoliday && previousOrderCount > 5))
        ) {
            discount = when {
                hasPromoCode && promoCode == "SAVE20" -> 0.20
                isFirstOrder && orderAmount > 100 -> 0.10
                else -> 0.05
            }
        }
        return discount * orderAmount
    }
}
