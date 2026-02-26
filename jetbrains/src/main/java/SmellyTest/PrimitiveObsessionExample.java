package SmellyTest;

/**
 * Primitive Obsession Example - Overuse of primitives instead of small objects
 * CodeScene flags code that uses primitives when domain objects would be better
 */
public class PrimitiveObsessionExample {

    // Using primitives to represent complex concepts like money, addresses, emails
    public void processOrder(String userId, double priceAmount, String priceCurrency,
                            String emailAddress, String phoneCountryCode,
                            String phoneAreaCode, String phoneNumber,
                            String addressLine1, String addressLine2,
                            String city, String state, String zipCode, String country) {

        // Email validation using primitives - should be an Email class
        if (!emailAddress.contains("@") || !emailAddress.contains(".")) {
            System.err.println("Invalid email");
            return;
        }

        // Phone validation using primitives - should be a PhoneNumber class
        if (phoneCountryCode.length() < 1 || phoneAreaCode.length() != 3 || phoneNumber.length() != 7) {
            System.err.println("Invalid phone");
            return;
        }

        // Money calculations using primitives - should be a Money class
        double taxRate = 0.08;
        double tax = priceAmount * taxRate;
        double total = priceAmount + tax;

        // Currency conversion using primitives
        double conversionRate = getConversionRate(priceCurrency, "USD");
        double totalInUSD = total * conversionRate;

        System.out.println("Order total: " + totalInUSD + " USD");
    }

    // Date/time represented as primitives instead of proper classes
    public boolean isAvailable(int year, int month, int day, int hour, int minute) {
        // Date validation using primitives
        if (month < 1 || month > 12) return false;
        if (day < 1 || day > 31) return false;
        if (hour < 0 || hour > 23) return false;
        if (minute < 0 || minute > 59) return false;

        // Should use proper Date/DateTime classes
        return true;
    }

    // Coordinates represented as primitives
    public double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        // Lat/lon validation using primitives - should be a Coordinate/GeoPoint class
        if (lat1 < -90 || lat1 > 90) throw new IllegalArgumentException("Invalid latitude");
        if (lon1 < -180 || lon1 > 180) throw new IllegalArgumentException("Invalid longitude");

        // Distance calculation
        double R = 6371; // Earth radius in km
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                   Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                   Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }

    // Range represented as two primitives instead of a Range class
    public boolean isPriceInRange(double price, double minPrice, double maxPrice) {
        return price >= minPrice && price <= maxPrice;
    }

    // Color represented as separate RGB values instead of a Color class
    public String mixColors(int r1, int g1, int b1, int r2, int g2, int b2) {
        int r = (r1 + r2) / 2;
        int g = (g1 + g2) / 2;
        int b = (b1 + b2) / 2;
        return "rgb(" + r + "," + g + "," + b + ")";
    }

    private double getConversionRate(String from, String to) {
        return 1.0; // Simplified
    }
}
