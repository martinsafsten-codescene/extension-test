package SmellyTest;

/**
 * Long Parameter List Example - Methods with too many parameters
 * CodeScene flags methods with excessive parameters (typically > 3-4)
 */
public class LongParameterListExample {

    // This method has way too many parameters - should use a parameter object
    public void createUser(String userId, String firstName, String lastName,
                          String email, String phone, String addressLine1,
                          String addressLine2, String city, String state,
                          String zipCode, String country, int age,
                          String gender, String occupation, boolean isActive,
                          boolean hasSubscription, String referralCode,
                          String preferredLanguage, String timezone) {

        System.out.println("Creating user: " + userId);
        System.out.println("Name: " + firstName + " " + lastName);
        System.out.println("Email: " + email);
        System.out.println("Phone: " + phone);
        System.out.println("Address: " + addressLine1 + ", " + city + ", " + state);
        // ... and so on
    }

    // Another example with too many parameters
    public double calculateShipping(String origin, String destination,
                                   double weight, double length, double width,
                                   double height, String serviceLevel,
                                   boolean isInsured, double insuranceValue,
                                   boolean requireSignature, boolean isFragile,
                                   String deliveryDate, String pickupDate) {

        double baseRate = 10.0;
        double weightCharge = weight * 0.5;
        double dimensionCharge = (length * width * height) / 166;
        double serviceCharge = serviceLevel.equals("EXPRESS") ? 25.0 : 0.0;
        double insuranceCharge = isInsured ? insuranceValue * 0.01 : 0.0;

        return baseRate + weightCharge + dimensionCharge + serviceCharge + insuranceCharge;
    }

    // Yet another example - payment processing with too many parameters
    public boolean processPayment(String customerId, String orderId,
                                 double amount, String currency,
                                 String paymentMethod, String cardNumber,
                                 String cardExpiry, String cvv,
                                 String cardholderName, String billingAddress,
                                 String billingCity, String billingState,
                                 String billingZip, String billingCountry,
                                 boolean saveCard, String ipAddress) {

        System.out.println("Processing payment of " + amount + " " + currency);
        System.out.println("Customer: " + customerId);
        System.out.println("Order: " + orderId);
        // Payment processing logic...
        return true;
    }
}
