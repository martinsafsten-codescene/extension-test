namespace Blazor_App.Services;

// CODE SMELL: Long Parameter List - Methods with too many parameters (10+)
// Makes methods hard to understand, test, and maintain
public class LongParameterListExample
{
    // CODE SMELL: 15 parameters!
    public void CreateOrder(
        string orderId,
        string customerId,
        string customerName,
        string customerEmail,
        string customerPhone,
        string productId,
        string productName,
        int quantity,
        decimal unitPrice,
        decimal tax,
        decimal discount,
        string shippingAddress,
        string billingAddress,
        string paymentMethod,
        string notes)
    {
        // Order creation logic
    }

    // CODE SMELL: 17 parameters!!
    public void UpdateCustomer(
        string customerId,
        string firstName,
        string lastName,
        string email,
        string phone,
        string mobile,
        string address1,
        string address2,
        string city,
        string state,
        string zip,
        string country,
        DateTime dateOfBirth,
        string gender,
        string occupation,
        bool newsletterSubscribed,
        bool smsNotifications)
    {
        // Update logic
    }

    // CODE SMELL: 12 parameters
    public void ProcessPayment(
        string paymentId,
        string orderId,
        string customerId,
        decimal amount,
        string currency,
        string paymentMethod,
        string cardNumber,
        string cardHolderName,
        string expiryDate,
        string cvv,
        string billingZip,
        bool saveCard)
    {
        // Payment processing logic
    }

    // CODE SMELL: 14 parameters
    public void GenerateInvoice(
        string invoiceId,
        string orderId,
        string customerId,
        DateTime invoiceDate,
        DateTime dueDate,
        decimal subtotal,
        decimal tax,
        decimal shipping,
        decimal discount,
        decimal total,
        string paymentTerms,
        string notes,
        bool sendEmail,
        string templateType)
    {
        // Invoice generation logic
    }

    // CODE SMELL: Boolean flags as parameters (Flag Arguments)
    public void ProcessData(
        string data,
        bool validate,
        bool transform,
        bool encrypt,
        bool compress,
        bool log,
        bool cache,
        bool notify,
        bool backup)
    {
        if (validate)
        {
            // Validate
        }

        if (transform)
        {
            // Transform
        }

        if (encrypt)
        {
            // Encrypt
        }

        if (compress)
        {
            // Compress
        }

        if (log)
        {
            // Log
        }

        if (cache)
        {
            // Cache
        }

        if (notify)
        {
            // Notify
        }

        if (backup)
        {
            // Backup
        }
    }
}
