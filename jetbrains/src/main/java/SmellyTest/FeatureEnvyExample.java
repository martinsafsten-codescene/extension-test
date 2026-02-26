package SmellyTest;

/**
 * Feature Envy Example - A method that seems more interested in other classes
 * CodeScene flags methods that access other objects' data more than their own
 */
public class FeatureEnvyExample {

    private String customerId;
    private String name;

    // This method is more interested in the Order class than its own data
    public double calculateCustomerDiscount(Order order) {
        // Accessing order's data repeatedly - feature envy!
        double total = order.getSubtotal();
        int itemCount = order.getItems().size();
        String orderType = order.getType();
        boolean isExpress = order.isExpress();
        String shippingAddress = order.getShippingAddress();

        double discount = 0.0;

        if (orderType.equals("PREMIUM")) {
            discount = total * 0.20;
        } else if (orderType.equals("REGULAR")) {
            discount = total * 0.10;
        }

        if (itemCount > 10) {
            discount += total * 0.05;
        }

        if (isExpress && shippingAddress.contains("Local")) {
            discount += 5.0;
        }

        return discount;
    }

    // This method should be in the Invoice class - it's all about invoice data
    public void printInvoiceDetails(Invoice invoice) {
        System.out.println("Invoice Number: " + invoice.getInvoiceNumber());
        System.out.println("Date: " + invoice.getInvoiceDate());
        System.out.println("Customer: " + invoice.getCustomerName());
        System.out.println("Amount: $" + invoice.getTotalAmount());
        System.out.println("Tax: $" + invoice.getTaxAmount());
        System.out.println("Grand Total: $" +
            (invoice.getTotalAmount() + invoice.getTaxAmount()));
        System.out.println("Payment Method: " + invoice.getPaymentMethod());
        System.out.println("Status: " + invoice.getPaymentStatus());
    }

    // This method manipulates Account data more than its own
    public void updateAccountBalance(Account account, double amount, String type) {
        if (type.equals("DEBIT")) {
            account.setBalance(account.getBalance() - amount);
            account.setLastDebitDate(new java.util.Date());
            account.setDebitCount(account.getDebitCount() + 1);
        } else {
            account.setBalance(account.getBalance() + amount);
            account.setLastCreditDate(new java.util.Date());
            account.setCreditCount(account.getCreditCount() + 1);
        }
        account.setLastTransactionDate(new java.util.Date());
    }
}

// Supporting classes
class Order {
    private double subtotal;
    private java.util.List<String> items;
    private String type;
    private boolean express;
    private String shippingAddress;

    public double getSubtotal() { return subtotal; }
    public java.util.List<String> getItems() { return items; }
    public String getType() { return type; }
    public boolean isExpress() { return express; }
    public String getShippingAddress() { return shippingAddress; }
}

class Invoice {
    private String invoiceNumber, customerName, paymentMethod, paymentStatus;
    private java.util.Date invoiceDate;
    private double totalAmount, taxAmount;

    public String getInvoiceNumber() { return invoiceNumber; }
    public java.util.Date getInvoiceDate() { return invoiceDate; }
    public String getCustomerName() { return customerName; }
    public double getTotalAmount() { return totalAmount; }
    public double getTaxAmount() { return taxAmount; }
    public String getPaymentMethod() { return paymentMethod; }
    public String getPaymentStatus() { return paymentStatus; }
}

class Account {
    private double balance;
    private java.util.Date lastDebitDate, lastCreditDate, lastTransactionDate;
    private int debitCount, creditCount;

    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }
    public java.util.Date getLastDebitDate() { return lastDebitDate; }
    public void setLastDebitDate(java.util.Date date) { this.lastDebitDate = date; }
    public java.util.Date getLastCreditDate() { return lastCreditDate; }
    public void setLastCreditDate(java.util.Date date) { this.lastCreditDate = date; }
    public void setLastTransactionDate(java.util.Date date) { this.lastTransactionDate = date; }
    public int getDebitCount() { return debitCount; }
    public void setDebitCount(int count) { this.debitCount = count; }
    public int getCreditCount() { return creditCount; }
    public void setCreditCount(int count) { this.creditCount = count; }
}
