/**
 * Long Method Example - A method that is way too long
 * Should be broken down into smaller, focused methods
 */

export class DataProcessorExample {
  processCustomerData(rawData: any[]): any {
    // This method is way too long and does too many things
    const results: any[] = [];
    const errors: any[] = [];
    const warnings: any[] = [];
    const statistics = {
      total: 0,
      processed: 0,
      failed: 0,
      skipped: 0,
      duplicates: 0,
      invalid: 0,
    };

    console.log("Starting data processing...");
    console.log(`Total records to process: ${rawData.length}`);

    // Phase 1: Data validation
    for (let i = 0; i < rawData.length; i++) {
      const record = rawData[i];
      statistics.total++;

      // Validate required fields
      if (!record.id) {
        errors.push({ record: i, error: "Missing ID" });
        statistics.invalid++;
        continue;
      }
      if (!record.name) {
        errors.push({ record: i, error: "Missing name" });
        statistics.invalid++;
        continue;
      }
      if (!record.email) {
        errors.push({ record: i, error: "Missing email" });
        statistics.invalid++;
        continue;
      }

      // Email validation
      const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
      if (!emailRegex.test(record.email)) {
        errors.push({ record: i, error: "Invalid email format" });
        statistics.invalid++;
        continue;
      }

      // Check for duplicates
      const isDuplicate = results.some((r) => r.id === record.id);
      if (isDuplicate) {
        warnings.push({ record: i, warning: "Duplicate ID detected" });
        statistics.duplicates++;
        continue;
      }

      // Phase 2: Data transformation
      const transformedRecord: any = {
        id: record.id,
        name: record.name.trim().toUpperCase(),
        email: record.email.toLowerCase().trim(),
        phone: record.phone || "N/A",
        address: record.address || "N/A",
        city: record.city || "N/A",
        state: record.state || "N/A",
        zipCode: record.zipCode || "N/A",
        country: record.country || "USA",
      };

      // Phase 3: Data enrichment
      if (record.dateOfBirth) {
        const dob = new Date(record.dateOfBirth);
        const today = new Date();
        let age = today.getFullYear() - dob.getFullYear();
        const monthDiff = today.getMonth() - dob.getMonth();
        if (monthDiff < 0 || (monthDiff === 0 && today.getDate() < dob.getDate())) {
          age--;
        }
        transformedRecord.age = age;
        transformedRecord.dateOfBirth = dob.toISOString();
      }

      // Calculate customer score
      let score = 0;
      if (record.purchases) {
        score += record.purchases * 10;
      }
      if (record.reviews) {
        score += record.reviews * 5;
      }
      if (record.referrals) {
        score += record.referrals * 20;
      }
      if (record.accountAge) {
        score += record.accountAge * 2;
      }
      transformedRecord.customerScore = score;

      // Determine customer tier
      if (score >= 1000) {
        transformedRecord.tier = "PLATINUM";
      } else if (score >= 500) {
        transformedRecord.tier = "GOLD";
      } else if (score >= 200) {
        transformedRecord.tier = "SILVER";
      } else {
        transformedRecord.tier = "BRONZE";
      }

      // Phase 4: Add metadata
      transformedRecord.processedAt = new Date().toISOString();
      transformedRecord.processedBy = "DataProcessorExample v1.0";
      transformedRecord.recordVersion = 1;

      // Phase 5: Calculate additional fields
      if (record.orders && record.orders.length > 0) {
        let totalSpent = 0;
        let totalOrders = record.orders.length;
        let cancelledOrders = 0;
        let returnedOrders = 0;

        for (const order of record.orders) {
          totalSpent += order.amount || 0;
          if (order.status === "cancelled") cancelledOrders++;
          if (order.status === "returned") returnedOrders++;
        }

        transformedRecord.totalSpent = totalSpent;
        transformedRecord.totalOrders = totalOrders;
        transformedRecord.cancelledOrders = cancelledOrders;
        transformedRecord.returnedOrders = returnedOrders;
        transformedRecord.averageOrderValue = totalOrders > 0 ? totalSpent / totalOrders : 0;
        transformedRecord.cancellationRate =
          totalOrders > 0 ? (cancelledOrders / totalOrders) * 100 : 0;
        transformedRecord.returnRate = totalOrders > 0 ? (returnedOrders / totalOrders) * 100 : 0;

        // Risk assessment
        if (transformedRecord.cancellationRate > 30) {
          transformedRecord.riskLevel = "HIGH";
          warnings.push({ record: i, warning: "High cancellation rate" });
        } else if (transformedRecord.cancellationRate > 15) {
          transformedRecord.riskLevel = "MEDIUM";
        } else {
          transformedRecord.riskLevel = "LOW";
        }
      }

      // Phase 6: Generate recommendations
      transformedRecord.recommendations = [];
      if (transformedRecord.tier === "PLATINUM" || transformedRecord.tier === "GOLD") {
        transformedRecord.recommendations.push("Offer VIP program");
        transformedRecord.recommendations.push("Priority customer service");
      }
      if (transformedRecord.totalOrders > 50) {
        transformedRecord.recommendations.push("Loyalty rewards program");
      }
      if (transformedRecord.averageOrderValue > 500) {
        transformedRecord.recommendations.push("Premium product recommendations");
      }
      if (transformedRecord.returnRate > 20) {
        transformedRecord.recommendations.push("Product quality review");
      }

      // Phase 7: Format for output
      transformedRecord.formatted = {
        displayName: `${transformedRecord.name} (${transformedRecord.tier})`,
        contactInfo: `${transformedRecord.email} | ${transformedRecord.phone}`,
        fullAddress: `${transformedRecord.address}, ${transformedRecord.city}, ${transformedRecord.state} ${transformedRecord.zipCode}, ${transformedRecord.country}`,
        summary: `Customer #${transformedRecord.id} - ${transformedRecord.tier} tier - Score: ${transformedRecord.customerScore}`,
      };

      // Phase 8: Final validation
      if (transformedRecord.customerScore < 0) {
        warnings.push({ record: i, warning: "Negative customer score" });
      }
      if (!transformedRecord.tier) {
        errors.push({ record: i, error: "Failed to assign tier" });
        statistics.failed++;
        continue;
      }

      // Add to results
      results.push(transformedRecord);
      statistics.processed++;

      // Progress logging
      if ((i + 1) % 100 === 0) {
        console.log(`Processed ${i + 1} / ${rawData.length} records`);
      }
    }

    // Phase 9: Generate summary report
    const summary = {
      processingComplete: true,
      timestamp: new Date().toISOString(),
      statistics: statistics,
      successRate: statistics.total > 0 ? (statistics.processed / statistics.total) * 100 : 0,
      errorRate: statistics.total > 0 ? (statistics.failed / statistics.total) * 100 : 0,
      duplicateRate: statistics.total > 0 ? (statistics.duplicates / statistics.total) * 100 : 0,
      tierDistribution: {
        PLATINUM: results.filter((r) => r.tier === "PLATINUM").length,
        GOLD: results.filter((r) => r.tier === "GOLD").length,
        SILVER: results.filter((r) => r.tier === "SILVER").length,
        BRONZE: results.filter((r) => r.tier === "BRONZE").length,
      },
      riskDistribution: {
        HIGH: results.filter((r) => r.riskLevel === "HIGH").length,
        MEDIUM: results.filter((r) => r.riskLevel === "MEDIUM").length,
        LOW: results.filter((r) => r.riskLevel === "LOW").length,
      },
    };

    console.log("Processing complete!");
    console.log(`Successfully processed: ${statistics.processed}`);
    console.log(`Failed: ${statistics.failed}`);
    console.log(`Duplicates: ${statistics.duplicates}`);
    console.log(`Invalid: ${statistics.invalid}`);

    // Phase 10: Return comprehensive result
    return {
      success: statistics.failed === 0,
      data: results,
      errors: errors,
      warnings: warnings,
      summary: summary,
      metadata: {
        processingTime: Date.now(),
        version: "1.0.0",
        processor: "DataProcessorExample",
      },
    };
  }
}
