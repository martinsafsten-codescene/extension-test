package org.example.smelly

/**
 * Primitive Obsession - Overuse of primitives instead of small objects.
 * CodeScene flags code that uses primitives when domain objects would be better.
 */
class PrimitiveObsessionExample {

    fun processOrder(
        userId: String,
        priceAmount: Double,
        priceCurrency: String,
        emailAddress: String,
        phoneCountryCode: String,
        phoneAreaCode: String,
        phoneNumber: String,
        addressLine1: String,
        addressLine2: String,
        city: String,
        state: String,
        zipCode: String,
        country: String
    ) {
        if (!emailAddress.contains("@") || !emailAddress.contains(".")) {
            System.err.println("Invalid email")
            return
        }
        if (phoneCountryCode.length < 1 || phoneAreaCode.length != 3 || phoneNumber.length != 7) {
            System.err.println("Invalid phone")
            return
        }

        val taxRate = 0.08
        val tax = priceAmount * taxRate
        val total = priceAmount + tax
        val conversionRate = getConversionRate(priceCurrency, "USD")
        val totalInUSD = total * conversionRate
        println("Order total: $totalInUSD USD")
    }

    fun isAvailable(year: Int, month: Int, day: Int, hour: Int, minute: Int): Boolean {
        if (month !in 1..12) return false
        if (day !in 1..31) return false
        if (hour !in 0..23) return false
        if (minute !in 0..59) return false
        return true
    }

    fun calculateDistance(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
        if (lat1 < -90 || lat1 > 90) throw IllegalArgumentException("Invalid latitude")
        if (lon1 < -180 || lon1 > 180) throw IllegalArgumentException("Invalid longitude")
        val R = 6371
        val dLat = Math.toRadians(lat2 - lat1)
        val dLon = Math.toRadians(lon2 - lon1)
        val a = kotlin.math.sin(dLat / 2) * kotlin.math.sin(dLat / 2) +
                kotlin.math.cos(Math.toRadians(lat1)) * kotlin.math.cos(Math.toRadians(lat2)) *
                kotlin.math.sin(dLon / 2) * kotlin.math.sin(dLon / 2)
        val c = 2 * kotlin.math.atan2(kotlin.math.sqrt(a), kotlin.math.sqrt(1 - a))
        return R * c
    }

    fun isPriceInRange(price: Double, minPrice: Double, maxPrice: Double) =
        price in minPrice..maxPrice

    fun mixColors(r1: Int, g1: Int, b1: Int, r2: Int, g2: Int, b2: Int): String {
        val r = (r1 + r2) / 2
        val g = (g1 + g2) / 2
        val b = (b1 + b2) / 2
        return "rgb($r,$g,$b)"
    }

    private fun getConversionRate(from: String, to: String) = 1.0
}
