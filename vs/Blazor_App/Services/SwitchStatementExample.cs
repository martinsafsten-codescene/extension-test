namespace Blazor_App.Services;

// CODE SMELL: Long Switch Statements - Should use polymorphism or strategy pattern
public class SwitchStatementExample
{
    // CODE SMELL: Long switch statement that gets repeated across multiple methods
    public decimal CalculateShipping(string customerType, decimal orderTotal)
    {
        switch (customerType)
        {
            case "PREMIUM":
                if (orderTotal > 100)
                    return 0;
                else
                    return 5;

            case "GOLD":
                if (orderTotal > 75)
                    return 0;
                else
                    return 7.50m;

            case "SILVER":
                if (orderTotal > 50)
                    return 0;
                else
                    return 10;

            case "BRONZE":
                if (orderTotal > 25)
                    return 5;
                else
                    return 12.50m;

            case "REGULAR":
                if (orderTotal > 50)
                    return 5;
                else
                    return 15;

            default:
                return 20;
        }
    }

    // CODE SMELL: Same switch statement repeated (code duplication)
    public decimal CalculateDiscount(string customerType, decimal orderTotal)
    {
        switch (customerType)
        {
            case "PREMIUM":
                return orderTotal * 0.20m;

            case "GOLD":
                return orderTotal * 0.15m;

            case "SILVER":
                return orderTotal * 0.10m;

            case "BRONZE":
                return orderTotal * 0.05m;

            case "REGULAR":
                return 0;

            default:
                return 0;
        }
    }

    // CODE SMELL: Same switch statement again!
    public int CalculateLoyaltyPoints(string customerType, decimal orderTotal)
    {
        switch (customerType)
        {
            case "PREMIUM":
                return (int)(orderTotal * 2);

            case "GOLD":
                return (int)(orderTotal * 1.5);

            case "SILVER":
                return (int)(orderTotal);

            case "BRONZE":
                return (int)(orderTotal * 0.5);

            case "REGULAR":
                return (int)(orderTotal * 0.25);

            default:
                return 0;
        }
    }

    // CODE SMELL: Nested switch statements
    public string ProcessOrder(string orderType, string paymentMethod, string shippingMethod)
    {
        switch (orderType)
        {
            case "RETAIL":
                switch (paymentMethod)
                {
                    case "CREDIT_CARD":
                        switch (shippingMethod)
                        {
                            case "EXPRESS":
                                return "Retail - Credit Card - Express";
                            case "STANDARD":
                                return "Retail - Credit Card - Standard";
                            case "ECONOMY":
                                return "Retail - Credit Card - Economy";
                            default:
                                return "Unknown shipping";
                        }
                    case "PAYPAL":
                        switch (shippingMethod)
                        {
                            case "EXPRESS":
                                return "Retail - PayPal - Express";
                            case "STANDARD":
                                return "Retail - PayPal - Standard";
                            default:
                                return "Unknown shipping";
                        }
                    default:
                        return "Unknown payment";
                }

            case "WHOLESALE":
                switch (paymentMethod)
                {
                    case "INVOICE":
                        return "Wholesale - Invoice";
                    case "BANK_TRANSFER":
                        return "Wholesale - Bank Transfer";
                    default:
                        return "Unknown payment";
                }

            case "DROPSHIP":
                return "Dropship order";

            default:
                return "Unknown order type";
        }
    }

    // CODE SMELL: Switch on type code (should use polymorphism)
    public void ProcessPayment(string paymentType, decimal amount)
    {
        switch (paymentType)
        {
            case "CREDIT_CARD":
                // Process credit card
                Console.WriteLine("Processing credit card payment");
                Console.WriteLine($"Amount: ${amount}");
                Console.WriteLine("Validating card number");
                Console.WriteLine("Charging card");
                Console.WriteLine("Sending receipt");
                break;

            case "DEBIT_CARD":
                // Process debit card
                Console.WriteLine("Processing debit card payment");
                Console.WriteLine($"Amount: ${amount}");
                Console.WriteLine("Validating card number");
                Console.WriteLine("Checking balance");
                Console.WriteLine("Charging card");
                Console.WriteLine("Sending receipt");
                break;

            case "PAYPAL":
                // Process PayPal
                Console.WriteLine("Processing PayPal payment");
                Console.WriteLine($"Amount: ${amount}");
                Console.WriteLine("Redirecting to PayPal");
                Console.WriteLine("Waiting for confirmation");
                Console.WriteLine("Sending receipt");
                break;

            case "BANK_TRANSFER":
                // Process bank transfer
                Console.WriteLine("Processing bank transfer");
                Console.WriteLine($"Amount: ${amount}");
                Console.WriteLine("Generating payment instructions");
                Console.WriteLine("Waiting for transfer");
                Console.WriteLine("Confirming receipt");
                break;

            case "CRYPTOCURRENCY":
                // Process crypto
                Console.WriteLine("Processing cryptocurrency payment");
                Console.WriteLine($"Amount: ${amount}");
                Console.WriteLine("Generating wallet address");
                Console.WriteLine("Waiting for blockchain confirmation");
                Console.WriteLine("Sending receipt");
                break;

            case "CASH":
                // Process cash
                Console.WriteLine("Processing cash payment");
                Console.WriteLine($"Amount: ${amount}");
                Console.WriteLine("Recording cash receipt");
                break;

            default:
                throw new Exception("Unknown payment type");
        }
    }

    // CODE SMELL: Switch statement that will grow whenever new types are added
    public string GetPaymentIcon(string paymentType)
    {
        switch (paymentType)
        {
            case "CREDIT_CARD":
                return "💳";
            case "DEBIT_CARD":
                return "💳";
            case "PAYPAL":
                return "🅿️";
            case "BANK_TRANSFER":
                return "🏦";
            case "CRYPTOCURRENCY":
                return "₿";
            case "CASH":
                return "💵";
            case "CHECK":
                return "✔️";
            case "GIFT_CARD":
                return "🎁";
            case "STORE_CREDIT":
                return "🏪";
            default:
                return "❓";
        }
    }
}
