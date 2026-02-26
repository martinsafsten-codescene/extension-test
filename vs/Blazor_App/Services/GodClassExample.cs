namespace Blazor_App.Services;

// CODE SMELL: God Class - Too many responsibilities in one class
// Handles user CRUD, email sending, logging, preferences, settings, access control
public class GodClassExample
{
    // CODE SMELL: Primitive Obsession - using strings/ints instead of value objects
    public void CreateUser(string firstName, string lastName, string email, string phone,
        string address1, string address2, string city, string state, string zip,
        string country, int age, string gender, string ssn)
    {
        // Create user logic
    }

    public void UpdateUser(string userId, string firstName, string lastName, string email,
        string phone, string address1, string address2, string city, string state,
        string zip, string country, int age, string gender)
    {
        // Update user logic
    }

    public void DeleteUser(string userId)
    {
        // Delete user logic
    }

    public void ActivateUser(string userId)
    {
        // Activate logic
    }

    public void DeactivateUser(string userId)
    {
        // Deactivate logic
    }

    public void SendWelcomeEmail(string userId)
    {
        // Email logic
    }

    public void SendPasswordResetEmail(string userId)
    {
        // Email logic
    }

    public void SendNotificationEmail(string userId, string message)
    {
        // Email logic
    }

    public void LogUserActivity(string userId, string activity)
    {
        // Logging logic
    }

    public void UpdateUserPreferences(string userId, string theme, string language, bool notifications)
    {
        // Preferences logic
    }

    public void UpdateUserSettings(string userId, bool twoFactor, bool emailNotifications,
        bool smsNotifications, bool pushNotifications)
    {
        // Settings logic
    }

    // CODE SMELL: Bumpy road - uneven complexity distribution
    public string GetUserStatus(string userId, bool checkSubscription, bool checkActivity,
        bool checkPayment)
    {
        string status = "Active";

        if (checkSubscription)
        {
            if (userId.StartsWith("SUB"))
            {
                if (userId.Contains("PREMIUM"))
                {
                    if (userId.EndsWith("ANNUAL"))
                    {
                        status = "Premium Annual";
                    }
                    else if (userId.EndsWith("MONTHLY"))
                    {
                        status = "Premium Monthly";
                    }
                    else
                    {
                        status = "Premium";
                    }
                }
                else if (userId.Contains("BASIC"))
                {
                    status = "Basic";
                }
            }
        }

        // Simple line
        return status;
    }

    public void ProcessUserData(string data)
    {
        // Simple processing
    }

    // CODE SMELL: Another complex method after simple one (bumpy road)
    public bool ValidateUserAccess(string userId, string resource, string action,
        string ip, string userAgent)
    {
        if (string.IsNullOrEmpty(userId))
        {
            if (string.IsNullOrEmpty(resource))
            {
                if (string.IsNullOrEmpty(action))
                {
                    return false;
                }
            }
        }

        if (resource == "Admin")
        {
            if (userId.StartsWith("ADMIN"))
            {
                if (action == "Delete")
                {
                    if (ip.StartsWith("192.168"))
                    {
                        return true;
                    }
                    else if (ip.StartsWith("10.0"))
                    {
                        return true;
                    }
                    else
                    {
                        return false;
                    }
                }
            }
        }
        else if (resource == "User")
        {
            if (action == "Read")
            {
                return true;
            }
            else if (action == "Write")
            {
                if (userId.Contains("EDITOR"))
                {
                    return true;
                }
            }
        }

        return false;
    }

    // CODE SMELL: God method - does everything
    public void ManageUserLifecycle(string userId, string action, string firstName,
        string lastName, string email, string phone, string address, bool sendEmail,
        bool logActivity, bool updateStats, string notes)
    {
        if (action == "create")
        {
            CreateUser(firstName, lastName, email, phone, address, "", "", "", "",
                "", 0, "", "");
            if (sendEmail)
            {
                SendWelcomeEmail(userId);
            }
            if (logActivity)
            {
                LogUserActivity(userId, "User created");
            }
        }
        else if (action == "update")
        {
            UpdateUser(userId, firstName, lastName, email, phone, address, "", "",
                "", "", "", 0, "");
            if (logActivity)
            {
                LogUserActivity(userId, "User updated");
            }
        }
        else if (action == "delete")
        {
            DeleteUser(userId);
            if (sendEmail)
            {
                SendNotificationEmail(userId, "Account deleted");
            }
            if (logActivity)
            {
                LogUserActivity(userId, "User deleted");
            }
        }
        else if (action == "activate")
        {
            ActivateUser(userId);
        }
        else if (action == "deactivate")
        {
            DeactivateUser(userId);
        }
    }
}
