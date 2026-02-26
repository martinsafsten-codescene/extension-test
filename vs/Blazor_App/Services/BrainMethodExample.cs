namespace Blazor_App.Services;

// CODE SMELL: Brain Method - overly complex method that tries to do everything
// Single method handles filtering, sorting, transforming, and validating with deep nesting
public class BrainMethodExample
{
    // CODE SMELL: High cyclomatic complexity + long method + deep nesting
    public List<Dictionary<string, object>> ProcessComplexData(
        List<string> rawData,
        bool filterEnabled,
        string filterType,
        bool sortEnabled,
        string sortColumn,
        bool groupEnabled,
        string groupBy,
        bool transformEnabled,
        string transformType,
        bool validateEnabled,
        int maxRecords,
        bool includeMetadata)
    {
        var results = new List<Dictionary<string, object>>();

        if (rawData == null || rawData.Count == 0)
        {
            return results;
        }

        // CODE SMELL: Nested loops with complex conditions
        for (int i = 0; i < rawData.Count; i++)
        {
            if (i >= maxRecords)
            {
                break;
            }

            var item = rawData[i];

            if (filterEnabled)
            {
                if (filterType == "Type1")
                {
                    if (item.Contains("ACTIVE"))
                    {
                        if (item.Length > 10)
                        {
                            if (item.StartsWith("A"))
                            {
                                // Process Type1
                                var dict = new Dictionary<string, object>();
                                dict["data"] = item;
                                dict["type"] = "Type1";
                                results.Add(dict);
                            }
                        }
                    }
                }
                else if (filterType == "Type2")
                {
                    if (item.Contains("PENDING"))
                    {
                        if (item.Length > 5)
                        {
                            if (!item.StartsWith("Z"))
                            {
                                var dict = new Dictionary<string, object>();
                                dict["data"] = item;
                                dict["type"] = "Type2";
                                results.Add(dict);
                            }
                        }
                    }
                }
                else if (filterType == "Type3")
                {
                    if (item.Contains("COMPLETED"))
                    {
                        var dict = new Dictionary<string, object>();
                        dict["data"] = item;
                        dict["type"] = "Type3";
                        results.Add(dict);
                    }
                }
            }
            else
            {
                var dict = new Dictionary<string, object>();
                dict["data"] = item;
                results.Add(dict);
            }
        }

        // CODE SMELL: More nested conditions
        if (sortEnabled)
        {
            if (sortColumn == "Name")
            {
                if (results.Count > 1)
                {
                    // Sort by name
                    for (int i = 0; i < results.Count - 1; i++)
                    {
                        for (int j = 0; j < results.Count - i - 1; j++)
                        {
                            if (string.Compare(results[j]["data"].ToString(),
                                results[j + 1]["data"].ToString()) > 0)
                            {
                                var temp = results[j];
                                results[j] = results[j + 1];
                                results[j + 1] = temp;
                            }
                        }
                    }
                }
            }
            else if (sortColumn == "Date")
            {
                // Similar sorting logic
            }
        }

        if (transformEnabled)
        {
            if (transformType == "Uppercase")
            {
                for (int i = 0; i < results.Count; i++)
                {
                    if (results[i].ContainsKey("data"))
                    {
                        results[i]["data"] = results[i]["data"].ToString().ToUpper();
                    }
                }
            }
            else if (transformType == "Lowercase")
            {
                for (int i = 0; i < results.Count; i++)
                {
                    if (results[i].ContainsKey("data"))
                    {
                        results[i]["data"] = results[i]["data"].ToString().ToLower();
                    }
                }
            }
        }

        if (validateEnabled)
        {
            var validResults = new List<Dictionary<string, object>>();
            foreach (var result in results)
            {
                if (result.ContainsKey("data"))
                {
                    if (result["data"] != null)
                    {
                        if (!string.IsNullOrEmpty(result["data"].ToString()))
                        {
                            if (result["data"].ToString().Length > 0)
                            {
                                validResults.Add(result);
                            }
                        }
                    }
                }
            }
            results = validResults;
        }

        return results;
    }

    // CODE SMELL: Copy-pasted logic with minor variations
    public List<string> ProcessDataType1(List<string> data)
    {
        var results = new List<string>();
        foreach (var item in data)
        {
            if (!string.IsNullOrEmpty(item))
            {
                if (item.Length > 5)
                {
                    if (item.Contains("@"))
                    {
                        results.Add(item.ToUpper());
                    }
                }
            }
        }
        return results;
    }

    // CODE SMELL: Almost identical to ProcessDataType1
    public List<string> ProcessDataType2(List<string> data)
    {
        var results = new List<string>();
        foreach (var item in data)
        {
            if (!string.IsNullOrEmpty(item))
            {
                if (item.Length > 3)
                {
                    if (item.Contains("#"))
                    {
                        results.Add(item.ToLower());
                    }
                }
            }
        }
        return results;
    }

    // CODE SMELL: And another duplicate
    public List<string> ProcessDataType3(List<string> data)
    {
        var results = new List<string>();
        foreach (var item in data)
        {
            if (!string.IsNullOrEmpty(item))
            {
                if (item.Length > 10)
                {
                    if (item.Contains("$"))
                    {
                        results.Add(item.Trim());
                    }
                }
            }
        }
        return results;
    }
}
