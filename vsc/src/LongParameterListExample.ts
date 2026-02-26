/**
 * Long Parameter List Example - Functions with too many parameters
 * This makes the code hard to call, understand, and maintain
 */

export class UserServiceExample {
  // Too many parameters - code smell!
  createUser(
    firstName: string,
    lastName: string,
    email: string,
    phone: string,
    address: string,
    city: string,
    state: string,
    zipCode: string,
    country: string,
    dateOfBirth: Date,
    gender: string,
    occupation: string,
    company: string,
    salary: number,
    department: string,
    employeeId: string,
    hireDate: Date,
    manager: string,
    officeLocation: string,
    workPhone: string,
    emergencyContact: string,
    emergencyPhone: string,
    bloodType: string,
    allergies: string[],
    medications: string[],
    insuranceProvider: string,
    policyNumber: string,
    taxId: string,
    bankAccount: string,
    routingNumber: string
  ) {
    // Implementation with way too many parameters
    console.log("Creating user with", firstName, lastName);
    // ... rest of implementation
  }

  // Another example with many parameters
  sendNotification(
    userId: string,
    notificationType: string,
    title: string,
    message: string,
    priority: string,
    channel: string,
    scheduledTime: Date,
    expiryTime: Date,
    retryCount: number,
    retryDelay: number,
    recipientEmail: string,
    recipientPhone: string,
    recipientPush: boolean,
    templateId: string,
    variables: Record<string, any>,
    attachments: string[],
    callbackUrl: string,
    metadata: Record<string, any>
  ) {
    console.log("Sending notification to", userId);
    // ... implementation
  }

  // Yet another example
  processPayment(
    orderId: string,
    userId: string,
    amount: number,
    currency: string,
    paymentMethod: string,
    cardNumber: string,
    cardHolderName: string,
    expiryMonth: number,
    expiryYear: number,
    cvv: string,
    billingAddress: string,
    billingCity: string,
    billingState: string,
    billingZip: string,
    billingCountry: string,
    taxAmount: number,
    shippingAmount: number,
    discountCode: string,
    discountAmount: number,
    invoiceNumber: string,
    description: string,
    metadata: Record<string, any>,
    idempotencyKey: string
  ) {
    console.log("Processing payment for order", orderId);
    // ... implementation
  }
}

// More examples showing the anti-pattern
export function generateReportExample(
  reportType: string,
  startDate: Date,
  endDate: Date,
  userId: string,
  departmentId: string,
  includeGraphs: boolean,
  includeRawData: boolean,
  format: string,
  orientation: string,
  paperSize: string,
  fontSize: number,
  fontFamily: string,
  headerText: string,
  footerText: string,
  logo: string,
  watermark: string,
  pageNumbers: boolean,
  tableOfContents: boolean,
  executiveSummary: boolean,
  detailedBreakdown: boolean,
  comparisonData: boolean,
  previousPeriod: boolean,
  benchmarkData: boolean,
  annotations: string[],
  highlightThreshold: number,
  colorScheme: string,
  outputPath: string,
  emailRecipients: string[],
  scheduledDelivery: boolean,
  compressionLevel: number
) {
  console.log("Generating report:", reportType);
  // ... implementation
}

export function configureServerExample(
  host: string,
  port: number,
  protocol: string,
  maxConnections: number,
  timeout: number,
  keepAlive: boolean,
  keepAliveTimeout: number,
  headerTimeout: number,
  requestTimeout: number,
  enableCors: boolean,
  corsOrigins: string[],
  corsMethods: string[],
  corsHeaders: string[],
  enableCompression: boolean,
  compressionLevel: number,
  enableCache: boolean,
  cacheMaxAge: number,
  enableRateLimit: boolean,
  rateLimitMax: number,
  rateLimitWindow: number,
  enableLogging: boolean,
  logLevel: string,
  logFormat: string,
  logOutput: string,
  enableMetrics: boolean,
  metricsPort: number,
  enableHealthCheck: boolean,
  healthCheckPath: string,
  sslEnabled: boolean,
  sslCert: string,
  sslKey: string,
  sslCa: string
) {
  console.log(`Configuring server on ${host}:${port}`);
  // ... implementation
}
