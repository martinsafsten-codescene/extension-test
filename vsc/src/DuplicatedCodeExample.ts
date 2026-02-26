/**
 * Duplicated Code Example - Obvious code duplication
 * Violates the DRY (Don't Repeat Yourself) principle
 */

export class ReportGeneratorExample {
  // Duplicated code for generating different types of reports

  generateSalesReport(startDate: Date, endDate: Date, userId: string) {
    // Authentication check - duplicated
    if (!userId) {
      throw new Error("User ID is required");
    }
    const user = this.getUserFromDatabase(userId);
    if (!user) {
      throw new Error("User not found");
    }
    if (!user.permissions.includes("view_reports")) {
      throw new Error("Insufficient permissions");
    }

    // Date validation - duplicated
    if (!startDate || !endDate) {
      throw new Error("Start and end dates are required");
    }
    if (startDate > endDate) {
      throw new Error("Start date must be before end date");
    }
    const daysDiff = (endDate.getTime() - startDate.getTime()) / (1000 * 60 * 60 * 24);
    if (daysDiff > 365) {
      throw new Error("Date range cannot exceed 365 days");
    }

    // Fetch sales data
    const data = this.fetchSalesData(startDate, endDate);

    // Format report - duplicated pattern
    const report = {
      title: "Sales Report",
      generatedAt: new Date(),
      generatedBy: user.name,
      period: { start: startDate, end: endDate },
      data: data,
    };

    // Log the action - duplicated
    this.logToDatabase("report_generated", {
      type: "sales",
      userId: userId,
      timestamp: new Date(),
    });

    // Send notification - duplicated
    this.sendEmailNotification(user.email, "Report Ready", "Your sales report is ready");

    return report;
  }

  generateInventoryReport(startDate: Date, endDate: Date, userId: string) {
    // Authentication check - duplicated (exact same as above)
    if (!userId) {
      throw new Error("User ID is required");
    }
    const user = this.getUserFromDatabase(userId);
    if (!user) {
      throw new Error("User not found");
    }
    if (!user.permissions.includes("view_reports")) {
      throw new Error("Insufficient permissions");
    }

    // Date validation - duplicated (exact same as above)
    if (!startDate || !endDate) {
      throw new Error("Start and end dates are required");
    }
    if (startDate > endDate) {
      throw new Error("Start date must be before end date");
    }
    const daysDiff = (endDate.getTime() - startDate.getTime()) / (1000 * 60 * 60 * 24);
    if (daysDiff > 365) {
      throw new Error("Date range cannot exceed 365 days");
    }

    // Fetch inventory data
    const data = this.fetchInventoryData(startDate, endDate);

    // Format report - duplicated pattern
    const report = {
      title: "Inventory Report",
      generatedAt: new Date(),
      generatedBy: user.name,
      period: { start: startDate, end: endDate },
      data: data,
    };

    // Log the action - duplicated
    this.logToDatabase("report_generated", {
      type: "inventory",
      userId: userId,
      timestamp: new Date(),
    });

    // Send notification - duplicated
    this.sendEmailNotification(
      user.email,
      "Report Ready",
      "Your inventory report is ready"
    );

    return report;
  }

  generateCustomerReport(startDate: Date, endDate: Date, userId: string) {
    // Authentication check - duplicated (exact same as above)
    if (!userId) {
      throw new Error("User ID is required");
    }
    const user = this.getUserFromDatabase(userId);
    if (!user) {
      throw new Error("User not found");
    }
    if (!user.permissions.includes("view_reports")) {
      throw new Error("Insufficient permissions");
    }

    // Date validation - duplicated (exact same as above)
    if (!startDate || !endDate) {
      throw new Error("Start and end dates are required");
    }
    if (startDate > endDate) {
      throw new Error("Start date must be before end date");
    }
    const daysDiff = (endDate.getTime() - startDate.getTime()) / (1000 * 60 * 60 * 24);
    if (daysDiff > 365) {
      throw new Error("Date range cannot exceed 365 days");
    }

    // Fetch customer data
    const data = this.fetchCustomerData(startDate, endDate);

    // Format report - duplicated pattern
    const report = {
      title: "Customer Report",
      generatedAt: new Date(),
      generatedBy: user.name,
      period: { start: startDate, end: endDate },
      data: data,
    };

    // Log the action - duplicated
    this.logToDatabase("report_generated", {
      type: "customer",
      userId: userId,
      timestamp: new Date(),
    });

    // Send notification - duplicated
    this.sendEmailNotification(
      user.email,
      "Report Ready",
      "Your customer report is ready"
    );

    return report;
  }

  generateFinancialReport(startDate: Date, endDate: Date, userId: string) {
    // Authentication check - duplicated (exact same as above)
    if (!userId) {
      throw new Error("User ID is required");
    }
    const user = this.getUserFromDatabase(userId);
    if (!user) {
      throw new Error("User not found");
    }
    if (!user.permissions.includes("view_reports")) {
      throw new Error("Insufficient permissions");
    }

    // Date validation - duplicated (exact same as above)
    if (!startDate || !endDate) {
      throw new Error("Start and end dates are required");
    }
    if (startDate > endDate) {
      throw new Error("Start date must be before end date");
    }
    const daysDiff = (endDate.getTime() - startDate.getTime()) / (1000 * 60 * 60 * 24);
    if (daysDiff > 365) {
      throw new Error("Date range cannot exceed 365 days");
    }

    // Fetch financial data
    const data = this.fetchFinancialData(startDate, endDate);

    // Format report - duplicated pattern
    const report = {
      title: "Financial Report",
      generatedAt: new Date(),
      generatedBy: user.name,
      period: { start: startDate, end: endDate },
      data: data,
    };

    // Log the action - duplicated
    this.logToDatabase("report_generated", {
      type: "financial",
      userId: userId,
      timestamp: new Date(),
    });

    // Send notification - duplicated
    this.sendEmailNotification(
      user.email,
      "Report Ready",
      "Your financial report is ready"
    );

    return report;
  }

  // More duplication in validation methods
  validateEmail(email: string): boolean {
    if (!email) return false;
    if (email.length < 5) return false;
    if (!email.includes("@")) return false;
    if (!email.includes(".")) return false;
    return true;
  }

  validateUserEmail(email: string): boolean {
    // Almost identical to validateEmail
    if (!email) return false;
    if (email.length < 5) return false;
    if (!email.includes("@")) return false;
    if (!email.includes(".")) return false;
    return true;
  }

  validateAdminEmail(email: string): boolean {
    // Almost identical to validateEmail
    if (!email) return false;
    if (email.length < 5) return false;
    if (!email.includes("@")) return false;
    if (!email.includes(".")) return false;
    return true;
  }

  // Helper methods (not duplicated)
  private getUserFromDatabase(userId: string): any {
    return { id: userId, name: "User", email: "user@example.com", permissions: ["view_reports"] };
  }

  private fetchSalesData(startDate: Date, endDate: Date): any {
    return [];
  }

  private fetchInventoryData(startDate: Date, endDate: Date): any {
    return [];
  }

  private fetchCustomerData(startDate: Date, endDate: Date): any {
    return [];
  }

  private fetchFinancialData(startDate: Date, endDate: Date): any {
    return [];
  }

  private logToDatabase(action: string, data: any): void {}

  private sendEmailNotification(email: string, subject: string, body: string): void {}
}
