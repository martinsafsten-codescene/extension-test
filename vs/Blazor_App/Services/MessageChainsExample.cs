namespace Blazor_App.Services;

// CODE SMELL: Message Chains (Law of Demeter violations)
// "Don't talk to strangers" - only talk to immediate friends
public class MessageChainsExample
{
    // CODE SMELL: Long chain of method calls (A.B.C.D.E...)
    public string GetCustomerCity(Order order)
    {
        // Violates Law of Demeter - reaching through multiple objects
        return order.GetCustomer().GetAddress().GetCity().ToUpper();
    }

    // CODE SMELL: Another message chain
    public decimal GetOrderTotal(Order order)
    {
        // Digging deep into object structure
        return order.GetLineItems().First().GetProduct().GetPrice().GetAmount() *
               order.GetLineItems().First().GetQuantity();
    }

    // CODE SMELL: Complex navigation through object graph
    public void UpdateInventory(Order order)
    {
        // Too many dots!
        var warehouseId = order.GetShipment().GetWarehouse().GetLocation().GetRegion().GetWarehouseId();
        var productId = order.GetLineItems().First().GetProduct().GetId();
        var quantity = order.GetLineItems().First().GetQuantity();

        // Update inventory
    }

    // CODE SMELL: Exposing internal structure
    public string GetProductCategory(Order order)
    {
        // Reaching too far into the object graph
        return order.GetLineItems()
                   .First()
                   .GetProduct()
                   .GetCategory()
                   .GetParentCategory()
                   .GetName()
                   .ToLower();
    }

    // CODE SMELL: Multiple chains in one method
    public void ProcessShipment(Order order)
    {
        var customerName = order.GetCustomer().GetProfile().GetFullName();
        var customerEmail = order.GetCustomer().GetProfile().GetContactInfo().GetEmail();
        var customerPhone = order.GetCustomer().GetProfile().GetContactInfo().GetPhone();

        var shippingStreet = order.GetCustomer().GetAddress().GetStreet();
        var shippingCity = order.GetCustomer().GetAddress().GetCity();
        var shippingState = order.GetCustomer().GetAddress().GetState();
        var shippingZip = order.GetCustomer().GetAddress().GetZipCode();

        var warehouseName = order.GetShipment().GetWarehouse().GetName();
        var warehouseAddress = order.GetShipment().GetWarehouse().GetLocation().GetFullAddress();

        // Process shipment
    }

    // CODE SMELL: Conditional logic based on deep chains
    public bool IsExpressEligible(Order order)
    {
        // Multiple deep chains in conditionals
        if (order.GetCustomer().GetSubscription().GetTier().GetLevel() == "PREMIUM" &&
            order.GetShipment().GetWarehouse().GetLocation().GetRegion().GetName() == "US-WEST" &&
            order.GetLineItems().All(item => item.GetProduct().GetInventory().GetQuantity() > 0))
        {
            return true;
        }

        return false;
    }

    // CODE SMELL: Chaining with null checks (even worse!)
    public string GetCustomerPreferredLanguage(Order order)
    {
        // Null checking at each level makes it even more verbose
        if (order != null)
        {
            var customer = order.GetCustomer();
            if (customer != null)
            {
                var profile = customer.GetProfile();
                if (profile != null)
                {
                    var preferences = profile.GetPreferences();
                    if (preferences != null)
                    {
                        var language = preferences.GetLanguage();
                        if (language != null)
                        {
                            return language.GetCode();
                        }
                    }
                }
            }
        }
        return "en";
    }

    // CODE SMELL: Train wreck - everything chained in one line
    public void SendNotification(Order order)
    {
        // The "train wreck" - all chained in one expression
        order.GetCustomer().GetProfile().GetContactInfo().GetNotificationPreferences().GetChannels().First().Send(order.GetId().ToString());
    }
}

// Supporting classes to make the example compile
public class Order
{
    public Customer GetCustomer() => new Customer();
    public List<LineItem> GetLineItems() => new List<LineItem>();
    public Shipment GetShipment() => new Shipment();
    public OrderId GetId() => new OrderId();
}

public class Customer
{
    public Address GetAddress() => new Address();
    public Profile GetProfile() => new Profile();
    public Subscription GetSubscription() => new Subscription();
}

public class Address
{
    public City GetCity() => new City();
    public string GetStreet() => "";
    public string GetState() => "";
    public string GetZipCode() => "";
}

public class City
{
    public string ToUpper() => "";
}

public class LineItem
{
    public Product GetProduct() => new Product();
    public int GetQuantity() => 0;
}

public class Product
{
    public Price GetPrice() => new Price();
    public string GetId() => "";
    public Category GetCategory() => new Category();
    public Inventory GetInventory() => new Inventory();
}

public class Price
{
    public decimal GetAmount() => 0;
}

public class Shipment
{
    public Warehouse GetWarehouse() => new Warehouse();
}

public class Warehouse
{
    public Location GetLocation() => new Location();
    public string GetName() => "";
}

public class Location
{
    public string GetWarehouseId() => "";
    public Region GetRegion() => new Region();
    public string GetFullAddress() => "";
}

public class Region
{
    public string GetWarehouseId() => "";
    public string GetName() => "";
}

public class Category
{
    public Category GetParentCategory() => new Category();
    public string GetName() => "";
}

public class Profile
{
    public string GetFullName() => "";
    public ContactInfo GetContactInfo() => new ContactInfo();
    public Preferences GetPreferences() => new Preferences();
}

public class ContactInfo
{
    public string GetEmail() => "";
    public string GetPhone() => "";
    public NotificationPreferences GetNotificationPreferences() => new NotificationPreferences();
}

public class Subscription
{
    public Tier GetTier() => new Tier();
}

public class Tier
{
    public string GetLevel() => "";
}

public class Inventory
{
    public int GetQuantity() => 0;
}

public class Preferences
{
    public Language GetLanguage() => new Language();
}

public class Language
{
    public string GetCode() => "";
}

public class NotificationPreferences
{
    public List<Channel> GetChannels() => new List<Channel>();
}

public class Channel
{
    public void Send(string message) { }
}

public class OrderId
{
    public override string ToString() => "";
}
