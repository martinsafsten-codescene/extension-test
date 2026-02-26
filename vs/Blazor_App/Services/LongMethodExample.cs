namespace Blazor_App.Services;

// CODE SMELL: Long Method - Single method that does too much (200+ lines)
public class LongMethodExample
{
    // CODE SMELL: This method is way too long and should be broken into smaller methods
    public string ProcessCustomerOrder(string customerId, string orderId)
    {
        var result = "";

        // Step 1: Validate customer (should be extracted)
        if (string.IsNullOrEmpty(customerId))
        {
            return "Invalid customer ID";
        }

        var customerExists = false;
        // Simulate database lookup
        if (customerId.StartsWith("CUST"))
        {
            customerExists = true;
        }

        if (!customerExists)
        {
            return "Customer not found";
        }

        // Step 2: Validate order (should be extracted)
        if (string.IsNullOrEmpty(orderId))
        {
            return "Invalid order ID";
        }

        var orderExists = false;
        // Simulate database lookup
        if (orderId.StartsWith("ORD"))
        {
            orderExists = true;
        }

        if (!orderExists)
        {
            return "Order not found";
        }

        // Step 3: Check inventory (should be extracted)
        var inventory = 100;
        var orderQuantity = 10;

        if (inventory < orderQuantity)
        {
            return "Insufficient inventory";
        }

        inventory -= orderQuantity;

        // Step 4: Calculate pricing (should be extracted)
        var basePrice = 100.00m;
        var quantity = 10;
        var subtotal = basePrice * quantity;

        // Apply discounts
        var discount = 0m;
        if (customerId.Contains("PREMIUM"))
        {
            discount = subtotal * 0.15m;
        }
        else if (customerId.Contains("GOLD"))
        {
            discount = subtotal * 0.10m;
        }
        else if (customerId.Contains("SILVER"))
        {
            discount = subtotal * 0.05m;
        }

        var discountedTotal = subtotal - discount;

        // Calculate tax
        var taxRate = 0.08m;
        var tax = discountedTotal * taxRate;

        // Calculate shipping
        var shipping = 0m;
        if (discountedTotal < 50)
        {
            shipping = 10m;
        }
        else if (discountedTotal < 100)
        {
            shipping = 5m;
        }

        var total = discountedTotal + tax + shipping;

        // Step 5: Process payment (should be extracted)
        var paymentSuccessful = false;

        if (total > 0)
        {
            // Simulate payment processing
            if (customerId.Contains("VERIFIED"))
            {
                paymentSuccessful = true;
            }
        }

        if (!paymentSuccessful)
        {
            return "Payment failed";
        }

        // Step 6: Update order status (should be extracted)
        var orderStatus = "PENDING";
        orderStatus = "PROCESSING";
        orderStatus = "CONFIRMED";

        // Step 7: Send notifications (should be extracted)
        // Send email notification
        var emailSubject = "Order Confirmation";
        var emailBody = $"Your order {orderId} has been confirmed.";
        // Simulate email sending

        // Send SMS notification
        var smsMessage = $"Order {orderId} confirmed. Total: ${total}";
        // Simulate SMS sending

        // Send push notification
        var pushTitle = "Order Confirmed";
        var pushMessage = $"Order {orderId} - ${total}";
        // Simulate push notification

        // Step 8: Update analytics (should be extracted)
        var totalOrders = 1000;
        totalOrders++;

        var totalRevenue = 50000m;
        totalRevenue += total;

        var averageOrderValue = totalRevenue / totalOrders;

        // Step 9: Generate invoice (should be extracted)
        var invoice = "";
        invoice += "INVOICE\n";
        invoice += "=======\n";
        invoice += $"Order ID: {orderId}\n";
        invoice += $"Customer ID: {customerId}\n";
        invoice += $"Subtotal: ${subtotal}\n";
        invoice += $"Discount: -${discount}\n";
        invoice += $"Tax: ${tax}\n";
        invoice += $"Shipping: ${shipping}\n";
        invoice += $"Total: ${total}\n";

        // Step 10: Update inventory records (should be extracted)
        var inventoryLog = $"Order {orderId}: Reduced inventory by {orderQuantity} units";

        // Step 11: Create shipment (should be extracted)
        var shipmentId = "SHIP" + orderId;
        var shipmentStatus = "CREATED";
        var estimatedDelivery = DateTime.Now.AddDays(5);

        // Step 12: Update customer loyalty points (should be extracted)
        var pointsEarned = (int)(total / 10);
        var customerPoints = 500;
        customerPoints += pointsEarned;

        // Step 13: Generate tracking number (should be extracted)
        var trackingNumber = "TRK" + orderId + DateTime.Now.Ticks;

        // Step 14: Send tracking email (should be extracted)
        var trackingEmailSubject = "Your Order Has Shipped";
        var trackingEmailBody = $"Tracking number: {trackingNumber}";

        // Step 15: Log transaction (should be extracted)
        var logEntry = $"{DateTime.Now}: Order {orderId} processed successfully";

        // Step 16: Update metrics (should be extracted)
        var processingTime = 150; // milliseconds
        var averageProcessingTime = 120;
        averageProcessingTime = (averageProcessingTime + processingTime) / 2;

        // Step 17: Cleanup (should be extracted)
        result = "Order processed successfully";
        result += $"\nOrder ID: {orderId}";
        result += $"\nTotal: ${total}";
        result += $"\nTracking: {trackingNumber}";
        result += $"\nPoints earned: {pointsEarned}";

        return result;
    }

    // CODE SMELL: Another long method
    public void GenerateMonthlyReport(int year, int month)
    {
        // 150+ lines of report generation logic that should be broken up
        var reportData = "";

        // Sales summary
        reportData += "MONTHLY SALES REPORT\n";
        reportData += "====================\n";
        reportData += $"Period: {year}-{month:D2}\n\n";

        // Daily breakdown
        for (int day = 1; day <= 30; day++)
        {
            reportData += $"Day {day}:\n";
            reportData += $"  Orders: {day * 10}\n";
            reportData += $"  Revenue: ${day * 1000}\n";
            reportData += $"  Customers: {day * 5}\n";
        }

        // Weekly summary
        for (int week = 1; week <= 4; week++)
        {
            reportData += $"\nWeek {week} Summary:\n";
            reportData += $"  Total Orders: {week * 70}\n";
            reportData += $"  Total Revenue: ${week * 7000}\n";
        }

        // Product breakdown
        var products = new[] { "Product A", "Product B", "Product C", "Product D", "Product E" };
        foreach (var product in products)
        {
            reportData += $"\n{product}:\n";
            reportData += $"  Units Sold: {100}\n";
            reportData += $"  Revenue: ${5000}\n";
        }

        // Customer segments
        reportData += "\n\nCustomer Segments:\n";
        reportData += "Premium: 100 customers, $20,000\n";
        reportData += "Gold: 200 customers, $30,000\n";
        reportData += "Silver: 300 customers, $25,000\n";
        reportData += "Regular: 500 customers, $15,000\n";

        // ... more report sections (this would continue for many more lines)
    }
}
