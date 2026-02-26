package org.example.smelly

/**
 * Long Parameter List - Methods with too many parameters.
 * CodeScene flags methods with excessive parameters (typically > 3-4).
 */
class LongParameterListExample {

    fun createUser(
        userId: String,
        firstName: String,
        lastName: String,
        email: String,
        phone: String,
        addressLine1: String,
        addressLine2: String,
        city: String,
        state: String,
        zipCode: String,
        country: String,
        age: Int,
        gender: String,
        occupation: String,
        isActive: Boolean,
        hasSubscription: Boolean,
        referralCode: String,
        preferredLanguage: String,
        timezone: String
    ) {
        println("Creating user: $userId")
        println("Name: $firstName $lastName")
        println("Email: $email")
        println("Address: $addressLine1, $city, $state")
    }

    fun calculateShipping(
        origin: String,
        destination: String,
        weight: Double,
        length: Double,
        width: Double,
        height: Double,
        serviceLevel: String,
        isInsured: Boolean,
        insuranceValue: Double,
        requireSignature: Boolean,
        isFragile: Boolean,
        deliveryDate: String,
        pickupDate: String
    ): Double {
        val baseRate = 10.0
        val weightCharge = weight * 0.5
        val dimensionCharge = (length * width * height) / 166
        val serviceCharge = if (serviceLevel == "EXPRESS") 25.0 else 0.0
        val insuranceCharge = if (isInsured) insuranceValue * 0.01 else 0.0
        return baseRate + weightCharge + dimensionCharge + serviceCharge + insuranceCharge
    }

    fun processPayment(
        customerId: String,
        orderId: String,
        amount: Double,
        currency: String,
        paymentMethod: String,
        cardNumber: String,
        cardExpiry: String,
        cvv: String,
        cardholderName: String,
        billingAddress: String,
        billingCity: String,
        billingState: String,
        billingZip: String,
        billingCountry: String,
        saveCard: Boolean,
        ipAddress: String
    ): Boolean {
        println("Processing payment of $amount $currency")
        println("Customer: $customerId")
        return true
    }
}
