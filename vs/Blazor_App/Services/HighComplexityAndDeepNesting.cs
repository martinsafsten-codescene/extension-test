namespace Blazor_App.Services;

// CODE SMELL: High Cyclomatic Complexity - Too many decision points
// CODE SMELL: Long methods with high cyclomatic complexity
// CODE SMELL: Deep nesting (5-6 levels)
public class HighComplexityAndDeepNesting
{
    // CODE SMELL: Long method (over 100 lines) with high cyclomatic complexity
    public bool ProcessOrder(string orderId, string customerId, string productId,
        int quantity, decimal price, string paymentMethod, string shippingAddress,
        string billingAddress, bool isExpress, bool giftWrap, string couponCode,
        bool insuranceRequested, string customerNotes)
    {
        if (string.IsNullOrEmpty(orderId))
        {
            if (string.IsNullOrEmpty(customerId))
            {
                if (string.IsNullOrEmpty(productId))
                {
                    // CODE SMELL: Deep nesting (4+ levels)
                    if (quantity <= 0)
                    {
                        if (price < 0)
                        {
                            return false;
                        }
                    }
                }
            }
        }

        // CODE SMELL: High cyclomatic complexity - many branches
        if (paymentMethod == "CreditCard")
        {
            if (price > 1000)
            {
                if (customerId.StartsWith("PREMIUM"))
                {
                    if (isExpress)
                    {
                        // Process premium express
                        return true;
                    }
                    else
                    {
                        // Process premium regular
                        return true;
                    }
                }
                else if (customerId.StartsWith("GOLD"))
                {
                    if (giftWrap)
                    {
                        // Add gift wrap
                        price += 5;
                    }
                    return true;
                }
                else
                {
                    // Standard customer
                    if (couponCode != null)
                    {
                        if (couponCode == "SAVE10")
                        {
                            price *= 0.9m;
                        }
                        else if (couponCode == "SAVE20")
                        {
                            price *= 0.8m;
                        }
                        else if (couponCode == "SAVE30")
                        {
                            price *= 0.7m;
                        }
                    }
                }
            }
        }
        else if (paymentMethod == "PayPal")
        {
            if (price > 500)
            {
                if (isExpress)
                {
                    return true;
                }
            }
        }
        else if (paymentMethod == "BankTransfer")
        {
            if (price > 2000)
            {
                return false;
            }
        }
        else if (paymentMethod == "Crypto")
        {
            if (customerId.Contains("VERIFIED"))
            {
                return true;
            }
        }

        // CODE SMELL: Code duplication - similar logic repeated
        if (shippingAddress.Contains("USA"))
        {
            if (isExpress)
            {
                price += 20;
            }
            else
            {
                price += 10;
            }
        }
        else if (shippingAddress.Contains("Canada"))
        {
            if (isExpress)
            {
                price += 25;
            }
            else
            {
                price += 15;
            }
        }
        else if (shippingAddress.Contains("UK"))
        {
            if (isExpress)
            {
                price += 30;
            }
            else
            {
                price += 20;
            }
        }
        else if (shippingAddress.Contains("Australia"))
        {
            if (isExpress)
            {
                price += 40;
            }
            else
            {
                price += 25;
            }
        }

        return true;
    }

    // CODE SMELL: Another long method with duplication
    public string ValidateOrder(string orderId, string customerId, string productId,
        int quantity, decimal price)
    {
        if (string.IsNullOrEmpty(orderId))
        {
            return "Order ID is required";
        }

        if (string.IsNullOrEmpty(customerId))
        {
            return "Customer ID is required";
        }

        if (string.IsNullOrEmpty(productId))
        {
            return "Product ID is required";
        }

        if (quantity <= 0)
        {
            return "Quantity must be positive";
        }

        if (price < 0)
        {
            return "Price cannot be negative";
        }

        return "Valid";
    }

    // CODE SMELL: Duplicated validation logic (Feature Envy)
    public string ValidateCustomer(string customerId, string email, string phone)
    {
        if (string.IsNullOrEmpty(customerId))
        {
            return "Customer ID is required";
        }

        if (string.IsNullOrEmpty(email))
        {
            return "Email is required";
        }

        if (string.IsNullOrEmpty(phone))
        {
            return "Phone is required";
        }

        return "Valid";
    }

    // CODE SMELL: More duplication
    public string ValidateProduct(string productId, string name, decimal price)
    {
        if (string.IsNullOrEmpty(productId))
        {
            return "Product ID is required";
        }

        if (string.IsNullOrEmpty(name))
        {
            return "Name is required";
        }

        if (price < 0)
        {
            return "Price cannot be negative";
        }

        return "Valid";
    }
}
