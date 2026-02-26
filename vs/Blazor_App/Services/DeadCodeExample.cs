namespace Blazor_App.Services;

// CODE SMELL: Dead Code - Unused methods, classes, and commented code
public class DeadCodeExample
{
    // CODE SMELL: This method is never called anywhere
    private void OldProcessingLogic()
    {
        // Old implementation that's no longer used
        Console.WriteLine("Old logic");
    }

    // CODE SMELL: Commented out code left in the file
    public void ProcessOrder(string orderId)
    {
        // Old way - doesn't work anymore
        // var order = GetOrderFromDatabase(orderId);
        // if (order != null)
        // {
        //     ValidateOrder(order);
        //     ProcessPayment(order);
        //     SendConfirmation(order);
        // }

        // New way
        Console.WriteLine($"Processing order {orderId}");

        // TODO: Remove this old code after testing
        // LegacyOrderProcessor processor = new LegacyOrderProcessor();
        // processor.Process(orderId);
    }

    // CODE SMELL: Unused private method
    private bool ValidateOldFormat(string data)
    {
        // This format is no longer supported
        return data.StartsWith("OLD:");
    }

    // CODE SMELL: Method that's never called
    private void CalculateOldDiscountModel(decimal price)
    {
        // Previous discount calculation
        var discount = price * 0.05m;
        // Not used since 2023
    }

    // CODE SMELL: Large block of commented code
    /*
    public void OldImplementation()
    {
        // This was the original implementation from 2020
        // Kept here for reference
        var data = FetchData();
        var processed = TransformData(data);
        var validated = ValidateData(processed);
        if (validated)
        {
            SaveData(processed);
        }
        else
        {
            LogError("Validation failed");
        }
    }

    private string FetchData()
    {
        // Old data fetching logic
        return "";
    }

    private string TransformData(string data)
    {
        // Old transformation logic
        return data;
    }

    private bool ValidateData(string data)
    {
        // Old validation logic
        return true;
    }

    private void SaveData(string data)
    {
        // Old save logic
    }

    private void LogError(string message)
    {
        // Old logging
    }
    */

    // CODE SMELL: Unreachable code
    public string GetStatus()
    {
        return "Active";

        // This code is never reached
        Console.WriteLine("Status checked");
        var timestamp = DateTime.Now;
        return $"Active at {timestamp}";
    }

    // CODE SMELL: Unused parameter
    public void ProcessData(string data, bool legacy)
    {
        // 'legacy' parameter is never used
        Console.WriteLine($"Processing: {data}");
    }

    // CODE SMELL: Debug code left in
    public void PerformCalculation(int value)
    {
        // Debug statements that should be removed
        // Console.WriteLine($"DEBUG: Input value = {value}");
        // Console.WriteLine($"DEBUG: Starting calculation");

        var result = value * 2;

        // Console.WriteLine($"DEBUG: Result = {result}");
        // Console.WriteLine($"DEBUG: Calculation complete");
    }

    // CODE SMELL: Unused fields
    private string oldApiUrl = "https://old-api.example.com";
    private int legacyTimeout = 5000;
    private bool useOldFormat = false;

    // CODE SMELL: Unused constants
    private const string OLD_VERSION = "1.0";
    private const int MAX_RETRIES_OLD = 3;
    private const string LEGACY_FORMAT = "LEGACY";

    // CODE SMELL: Conditional compilation that's always false
    public void DoSomething()
    {
        #if NEVER_DEFINED
        // This code is never compiled
        Console.WriteLine("Never executed");
        #endif

        Console.WriteLine("Doing something");
    }

    // CODE SMELL: Experimental code left in
    public void ExperimentalFeature()
    {
        // TODO: This was an experiment, decide whether to keep or remove
        // Experiment started: 2023-01-15
        // Last tested: 2023-02-01
        // Results: Inconclusive

        /*
        var experimental = true;
        if (experimental)
        {
            TryNewApproach();
        }
        */
    }

    // CODE SMELL: Versioned methods that are no longer needed
    public void ProcessV1(string data)
    {
        // Version 1 - deprecated
    }

    public void ProcessV2(string data)
    {
        // Version 2 - deprecated
    }

    public void ProcessV3(string data)
    {
        // Version 3 - current version, but V1 and V2 are still in the code
    }
}

// CODE SMELL: Entire class that's no longer used
internal class LegacyProcessor
{
    public void Process(string data)
    {
        // Old processing logic
    }

    public bool Validate(string data)
    {
        // Old validation logic
        return true;
    }
}

// CODE SMELL: Interface with no implementations
internal interface IOldService
{
    void DoSomething();
    void DoSomethingElse();
}
