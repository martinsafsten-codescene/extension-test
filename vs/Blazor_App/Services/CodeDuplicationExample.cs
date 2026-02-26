namespace Blazor_App.Services;

// CODE SMELL: Massive Code Duplication - Copy-paste programming
// Nearly identical methods with only minor variations
public class CodeDuplicationExample
{
    // CODE SMELL: Duplicated report generation logic
    public string GenerateSalesReport(DateTime startDate, DateTime endDate)
    {
        var report = "";
        report += "Sales Report\n";
        report += "=============\n";
        report += $"Start Date: {startDate:yyyy-MM-dd}\n";
        report += $"End Date: {endDate:yyyy-MM-dd}\n";
        report += "\n";
        report += "Details:\n";
        report += "--------\n";

        // Fetch and format sales data
        for (int i = 0; i < 100; i++)
        {
            report += $"Sale {i}: $100\n";
        }

        report += "\n";
        report += "Summary\n";
        report += "--------\n";
        report += "Total Sales: $10,000\n";

        return report;
    }

    // CODE SMELL: Almost identical to GenerateSalesReport
    public string GenerateInventoryReport(DateTime startDate, DateTime endDate)
    {
        var report = "";
        report += "Inventory Report\n";
        report += "================\n";
        report += $"Start Date: {startDate:yyyy-MM-dd}\n";
        report += $"End Date: {endDate:yyyy-MM-dd}\n";
        report += "\n";
        report += "Details:\n";
        report += "--------\n";

        // Fetch and format inventory data
        for (int i = 0; i < 100; i++)
        {
            report += $"Item {i}: 50 units\n";
        }

        report += "\n";
        report += "Summary\n";
        report += "--------\n";
        report += "Total Items: 5,000\n";

        return report;
    }

    // CODE SMELL: More duplication
    public string GenerateCustomerReport(DateTime startDate, DateTime endDate)
    {
        var report = "";
        report += "Customer Report\n";
        report += "===============\n";
        report += $"Start Date: {startDate:yyyy-MM-dd}\n";
        report += $"End Date: {endDate:yyyy-MM-dd}\n";
        report += "\n";
        report += "Details:\n";
        report += "--------\n";

        // Fetch and format customer data
        for (int i = 0; i < 100; i++)
        {
            report += $"Customer {i}: Active\n";
        }

        report += "\n";
        report += "Summary\n";
        report += "--------\n";
        report += "Total Customers: 100\n";

        return report;
    }

    // CODE SMELL: Copy-pasted validation logic
    public bool ValidateReportParameters(DateTime startDate, DateTime endDate, string format)
    {
        if (startDate > endDate)
        {
            return false;
        }

        if (endDate > DateTime.Now)
        {
            return false;
        }

        if (string.IsNullOrEmpty(format))
        {
            return false;
        }

        if (format != "PDF" && format != "Excel" && format != "CSV")
        {
            return false;
        }

        return true;
    }

    // CODE SMELL: Identical validation with minor differences
    public bool ValidateSalesReportParameters(DateTime startDate, DateTime endDate, string format)
    {
        if (startDate > endDate)
        {
            return false;
        }

        if (endDate > DateTime.Now)
        {
            return false;
        }

        if (string.IsNullOrEmpty(format))
        {
            return false;
        }

        if (format != "PDF" && format != "Excel")
        {
            return false;
        }

        return true;
    }

    // CODE SMELL: String concatenation in loop (performance issue)
    public string GenerateLargeReport()
    {
        string report = "";
        for (int i = 0; i < 10000; i++)
        {
            report += $"Line {i}: Some data here\n";
        }
        return report;
    }

    // CODE SMELL: Magic numbers and hard-coded values scattered throughout
    public decimal CalculateReportMetrics(int value1, int value2, int value3)
    {
        if (value1 > 100)
        {
            return value1 * 1.5m;
        }
        else if (value1 > 50)
        {
            return value1 * 1.3m;
        }
        else if (value1 > 25)
        {
            return value1 * 1.1m;
        }

        if (value2 > 200)
        {
            return value2 * 2.0m;
        }
        else if (value2 > 100)
        {
            return value2 * 1.8m;
        }

        if (value3 > 500)
        {
            return value3 * 3.0m;
        }

        return 0;
    }
}
