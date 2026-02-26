namespace Blazor_App.Services;

// CODE SMELL: Primitive Obsession - Using primitives instead of domain objects
// Should use value objects like Address, Money, Email, PhoneNumber, etc.
public class PrimitiveObsessionExample
{
    // CODE SMELL: Using strings for everything instead of proper types
    public void CreateCustomer(
        string firstName,
        string lastName,
        string email,           // Should be Email value object
        string phone,           // Should be PhoneNumber value object
        string address1,        // Should be Address value object
        string address2,
        string city,
        string state,
        string zip,
        string country)
    {
        // Validation scattered everywhere
        if (!email.Contains("@"))
        {
            throw new Exception("Invalid email");
        }

        if (phone.Length != 10)
        {
            throw new Exception("Invalid phone");
        }

        // Business logic
    }

    // CODE SMELL: Using strings for currency amounts instead of Money type
    public string CalculateTotal(string price, string tax, string shipping, string discount)
    {
        // String manipulation for money calculations - dangerous!
        var priceDecimal = decimal.Parse(price);
        var taxDecimal = decimal.Parse(tax);
        var shippingDecimal = decimal.Parse(shipping);
        var discountDecimal = decimal.Parse(discount);

        var total = priceDecimal + taxDecimal + shippingDecimal - discountDecimal;

        return total.ToString();
    }

    // CODE SMELL: Using strings for dates
    public bool IsValidDate(string date)
    {
        // Manual date parsing instead of using DateTime
        var parts = date.Split('-');
        if (parts.Length != 3)
        {
            return false;
        }

        var year = int.Parse(parts[0]);
        var month = int.Parse(parts[1]);
        var day = int.Parse(parts[2]);

        if (year < 1900 || year > 2100) return false;
        if (month < 1 || month > 12) return false;
        if (day < 1 || day > 31) return false;

        return true;
    }

    // CODE SMELL: Using int for status instead of enum
    public string GetOrderStatus(int status)
    {
        // Magic numbers instead of enum
        if (status == 1) return "Pending";
        if (status == 2) return "Processing";
        if (status == 3) return "Shipped";
        if (status == 4) return "Delivered";
        if (status == 5) return "Cancelled";
        return "Unknown";
    }

    // CODE SMELL: Using string for status code instead of enum
    public void ProcessOrderStatus(string statusCode)
    {
        // String comparisons instead of type-safe enum
        if (statusCode == "PEND")
        {
            // Process pending
        }
        else if (statusCode == "PROC")
        {
            // Process processing
        }
        else if (statusCode == "SHIP")
        {
            // Process shipped
        }
        else if (statusCode == "DELV")
        {
            // Process delivered
        }
    }

    // CODE SMELL: Data Clumps - same group of primitives always appear together
    public void ShipOrder(
        string streetAddress,
        string city,
        string state,
        string zipCode,
        string country)
    {
        // These 5 parameters always appear together - should be Address object
    }

    // CODE SMELL: Same data clump in different method
    public void BillCustomer(
        string streetAddress,
        string city,
        string state,
        string zipCode,
        string country)
    {
        // Same 5 parameters again!
    }

    // CODE SMELL: And again!
    public void ValidateAddress(
        string streetAddress,
        string city,
        string state,
        string zipCode,
        string country)
    {
        // And again! These should be an Address value object
    }

    // CODE SMELL: Using arrays/lists of primitives for complex data
    public void ProcessTransaction(string[] transactionData)
    {
        // Magic indices instead of named properties
        var transactionId = transactionData[0];
        var amount = transactionData[1];
        var currency = transactionData[2];
        var timestamp = transactionData[3];
        var status = transactionData[4];
        var customerId = transactionData[5];
        // ... etc
    }
}
