namespace Blazor_App.Services;

// CODE SMELL: Magic Numbers - Unexplained numeric literals scattered throughout code
// Should use named constants or enums
public class MagicNumbersExample
{
    // CODE SMELL: What do these numbers mean?
    public decimal CalculatePrice(decimal basePrice, string customerType)
    {
        if (customerType == "PREMIUM")
        {
            return basePrice * 0.85m; // What is 0.85?
        }
        else if (customerType == "GOLD")
        {
            return basePrice * 0.90m; // What is 0.90?
        }
        else if (customerType == "SILVER")
        {
            return basePrice * 0.95m; // What is 0.95?
        }

        return basePrice;
    }

    // CODE SMELL: Magic numbers everywhere
    public bool ValidateOrder(int quantity, decimal amount)
    {
        // What do these numbers represent?
        if (quantity < 1 || quantity > 100)
        {
            return false;
        }

        if (amount < 0.01m || amount > 10000)
        {
            return false;
        }

        return true;
    }

    // CODE SMELL: Array indices as magic numbers
    public void ProcessTransactionData(string[] data)
    {
        // What are indices 0, 1, 2, 3?
        var transactionId = data[0];
        var amount = data[1];
        var currency = data[2];
        var status = data[3];
        var customerId = data[4];
        var timestamp = data[5];
    }

    // CODE SMELL: Status codes as magic numbers
    public string GetOrderStatus(int statusCode)
    {
        if (statusCode == 0) return "Pending";
        if (statusCode == 1) return "Processing";
        if (statusCode == 2) return "Shipped";
        if (statusCode == 3) return "Delivered";
        if (statusCode == 4) return "Cancelled";
        if (statusCode == 5) return "Refunded";
        return "Unknown";
    }

    // CODE SMELL: Timeouts and delays as magic numbers
    public async Task ProcessWithRetry()
    {
        for (int i = 0; i < 3; i++) // Why 3?
        {
            try
            {
                await Task.Delay(1000); // Why 1000ms?
                // Process something
                break;
            }
            catch
            {
                if (i == 2) // Why 2?
                {
                    throw;
                }
            }
        }
    }

    // CODE SMELL: Calculation with magic numbers
    public decimal CalculateShipping(decimal weight, string destination)
    {
        decimal cost = 5.00m; // Base cost - what is 5.00?

        if (weight > 10) // Why 10?
        {
            cost += (weight - 10) * 0.50m; // Why 0.50?
        }

        if (destination == "International")
        {
            cost *= 2.5m; // Why 2.5?
        }

        if (cost > 50) // Why 50?
        {
            cost = 50; // Cap at 50
        }

        return cost;
    }

    // CODE SMELL: Business rules as magic numbers
    public bool IsEligibleForFreeShipping(decimal orderTotal, int itemCount)
    {
        // What do these thresholds mean?
        if (orderTotal >= 75 && itemCount <= 10)
        {
            return true;
        }

        if (orderTotal >= 100)
        {
            return true;
        }

        return false;
    }

    // CODE SMELL: Date calculations with magic numbers
    public DateTime CalculateDeliveryDate(DateTime orderDate, string shippingMethod)
    {
        if (shippingMethod == "EXPRESS")
        {
            return orderDate.AddDays(2); // Why 2 days?
        }
        else if (shippingMethod == "STANDARD")
        {
            return orderDate.AddDays(5); // Why 5 days?
        }
        else if (shippingMethod == "ECONOMY")
        {
            return orderDate.AddDays(10); // Why 10 days?
        }

        return orderDate.AddDays(7); // Why 7?
    }

    // CODE SMELL: Buffer sizes and limits as magic numbers
    public void ProcessBatch(List<string> items)
    {
        var batchSize = 50; // Why 50?
        var maxRetries = 5; // Why 5?
        var timeout = 30000; // Why 30000? (milliseconds?)

        for (int i = 0; i < items.Count; i += batchSize)
        {
            var batch = items.Skip(i).Take(batchSize);
            // Process batch
        }
    }

    // CODE SMELL: Percentage calculations with magic numbers
    public decimal CalculateTax(decimal amount, string state)
    {
        // Tax rates as magic numbers
        if (state == "CA") return amount * 0.0725m;
        if (state == "NY") return amount * 0.08875m;
        if (state == "TX") return amount * 0.0625m;
        if (state == "FL") return amount * 0.06m;
        return amount * 0.05m; // Default tax rate
    }

    // CODE SMELL: Priority levels as magic numbers
    public void ScheduleTask(string task, int priority)
    {
        if (priority == 1) // Urgent
        {
            // Process immediately
        }
        else if (priority == 2) // High
        {
            // Process within 1 hour
        }
        else if (priority == 3) // Medium
        {
            // Process within 4 hours
        }
        else if (priority == 4) // Low
        {
            // Process within 24 hours
        }
    }

    // CODE SMELL: Color codes as magic numbers
    public string GetStatusColor(int status)
    {
        if (status == 0) return "#FF0000"; // Red
        if (status == 1) return "#FFA500"; // Orange
        if (status == 2) return "#FFFF00"; // Yellow
        if (status == 3) return "#00FF00"; // Green
        return "#808080"; // Gray
    }

    // CODE SMELL: Complex formula with magic numbers
    public decimal CalculateScore(int views, int likes, int shares, int comments)
    {
        // What is this formula? What do these weights mean?
        return (views * 0.1m) + (likes * 2.5m) + (shares * 5.0m) + (comments * 3.0m);
    }
}
