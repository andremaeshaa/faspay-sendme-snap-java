# Faspay SendMe Snap Java SDK

A Java SDK for integrating with the Faspay SendMe Snap API. This SDK provides a simple and convenient way to interact with the Faspay SendMe Snap API for disbursement operations.

## Features

- Account Inquiry API support
- Interbank Transfer API support
- Secure communication with SSL certificate
- Request signing with HMAC-SHA256
- Comprehensive error handling
- Easy-to-use fluent API
- Detailed examples and documentation

## Requirements

- Java 11 or higher
- Maven or Gradle build system

## Installation

### Gradle

Add the following dependency to your `build.gradle` file:

```groovy
implementation 'id.co.faspay:faspay-sendme-snap-java:1.0.0'
```

### Maven

Add the following dependency to your `pom.xml` file:

```xml
<dependency>
    <groupId>id.co.faspay</groupId>
    <artifactId>faspay-sendme-snap-java</artifactId>
    <version>1.0.0</version>
</dependency>
```

## Getting Started

### Configuration

To use the SDK, you need to create a configuration with your Faspay SendMe Snap API credentials:

```java
import id.co.faspay.snap.config.FaspaySnapConfig;
import java.io.File;
import java.nio.file.Files;

// Load your SSL certificate and private key
String privateKeyStr = Files.readString(new File("path/to/your/private.key").toPath());
String sslString = Files.readString(new File("path/to/your/certificate.pem").toPath());

// Create a configuration with your credentials
FaspaySnapConfig config = new FaspaySnapConfig(
    "your-partner-id",     // Partner ID provided by Faspay
    privateKeyStr,         // Private key for signing requests
    sslString              // SSL certificate for secure communication
);

// Set the environment (default is "sandbox")
config.setEnv("sandbox");     // For testing/development
// config.setEnv("production");  // For production use
```

### Creating a Client

Once you have a configuration, you can create a client to interact with the API:

```java
import id.co.faspay.snap.FaspaySnapClient;

// Create a client with your configuration
FaspaySnapClient client = new FaspaySnapClient(config);
```

## Account Inquiry

The Account Inquiry API allows you to verify bank account details before making a transfer.

### Basic Usage

```java
import id.co.faspay.snap.model.AccountInquiryResponse;
import id.co.faspay.snap.exception.FaspaySnapApiException;

try {
    // Perform an account inquiry with direct parameters
    AccountInquiryResponse response = client.accountInquiry().inquire(
        "014",                  // Bank code (e.g., 014 for BCA)
        "1234567890",           // Account number to inquire
        "REF-" + System.currentTimeMillis()  // Unique partner reference number
    );

    // Process the response
    if (response.isSuccess()) {
        System.out.println("Account inquiry successful!");
        System.out.println("Account holder name: " + response.getAccountHolderName());
        System.out.println("Bank name: " + response.getBankName());
        System.out.println("Faspay reference number: " + response.getReferenceNo());
    } else {
        System.out.println("Account inquiry failed!");
        System.out.println("Response code: " + response.getResponseCode());
        System.out.println("Response message: " + response.getResponseMessage());
    }
} catch (FaspaySnapApiException e) {
    System.err.println("Error: " + e.getMessage());
    e.printStackTrace();
}
```

### Using Request Object

For more control, you can use the request object:

```java
import id.co.faspay.snap.model.AccountInquiryRequest;

// Create a request object
AccountInquiryRequest request = new AccountInquiryRequest(
    "014",                  // Bank code (e.g., 014 for BCA)
    "1234567890",           // Account number to inquire
    "REF-" + System.currentTimeMillis()  // Unique partner reference number
);

// Add optional parameters
request.setSourceAccount("9876543210");  // Your source account number

// Perform the inquiry
AccountInquiryResponse response = client.accountInquiry().inquire(request);
```

### Response Handling

The `AccountInquiryResponse` object contains detailed information about the account:

```java
if (response.isSuccess()) {
    // Basic information
    String accountName = response.getAccountHolderName();
    String accountNumber = response.getAccountNumber();
    String bankCode = response.getBankCode();
    String bankName = response.getBankName();

    // Reference information
    String referenceNo = response.getReferenceNo();
    String partnerReferenceNo = response.getPartnerReferenceNumber();

    // Additional information
    Map<String, String> additionalInfo = response.getAdditionalInfo();
}
```

## Interbank Transfer

The Interbank Transfer API allows you to transfer money from your account to an account at another bank.

### Basic Usage

```java
import id.co.faspay.snap.model.Amount;
import id.co.faspay.snap.model.TransferInterbankResponse;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

try {
    // Create an amount object
    Amount amount = new Amount("100000.00", "IDR");

    // Generate a unique reference number
    String uniqueRef = "REF" + ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));

    // Current timestamp for transaction date
    String transactionDate = ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX"));

    // Perform an interbank transfer with direct parameters
    TransferInterbankResponse response = client.transferInterbank().transfer(
        uniqueRef,              // Unique partner reference number
        amount,                 // Amount object with value and currency
        "John Doe",             // Beneficiary account name
        "1234567890",           // Beneficiary account number
        "008",                  // Beneficiary bank code (e.g., 008 for Mandiri)
        "9876543210",           // Source account number
        transactionDate         // Transaction date
    );

    // Process the response
    if (response.isSuccess()) {
        System.out.println("Interbank transfer successful!");
        System.out.println("Reference number: " + response.getReferenceNumber());
        System.out.println("Transaction status: " + response.getLatestTransactionStatus());
    } else {
        System.out.println("Interbank transfer failed!");
        System.out.println("Response code: " + response.getResponseCode());
        System.out.println("Response message: " + response.getResponseMessage());
    }
} catch (FaspaySnapApiException e) {
    System.err.println("Error: " + e.getMessage());
    e.printStackTrace();
}
```

### Using Request Object

For more control, you can use the request object:

```java
import id.co.faspay.snap.model.TransferInterbankRequest;

// Create an amount object
Amount amount = new Amount("100000.00", "IDR");

// Create a request object
TransferInterbankRequest request = new TransferInterbankRequest(
    "REF-" + System.currentTimeMillis(),  // Unique partner reference number
    amount,                               // Amount object with value and currency
    "John Doe",                           // Beneficiary account name
    "1234567890",                         // Beneficiary account number
    "008",                                // Beneficiary bank code (e.g., 008 for Mandiri)
    "9876543210"                          // Source account number
);

// Add optional parameters
request.setBeneficiaryEmail("recipient@example.com");  // Email of the recipient
request.setTransactionDescription("Payment for services");  // Description of the transaction
request.setCallbackUrl("https://your-domain.com/callback");  // URL for callback notifications

// Perform the transfer
TransferInterbankResponse response = client.transferInterbank().transfer(request);
```

### Response Handling

The `TransferInterbankResponse` object contains detailed information about the transfer:

```java
if (response.isSuccess()) {
    // Transaction details
    Amount transferAmount = response.getAmount();
    String transactionDescription = response.getTransactionDescription();
    String transactionStatus = response.getLatestTransactionStatus();
    String statusDescription = response.getTransactionStatusDesc();

    // Beneficiary details
    String beneficiaryName = response.getBeneficiaryAccountName();
    String beneficiaryAccount = response.getBeneficiaryAccountNumber();
    String beneficiaryBankCode = response.getBeneficiaryBankCode();
    String beneficiaryBankName = response.getBeneficiaryBankName();

    // Reference information
    String referenceNumber = response.getReferenceNumber();
    String partnerReferenceNumber = response.getPartnerReferenceNumber();
    String instructDate = response.getInstructDate();

    // Additional information
    Map<String, String> additionalInfo = response.getAdditionalInfo();
}
```

## Error Handling

The SDK throws `FaspaySnapApiException` when an error occurs while interacting with the API. This exception contains information about the error, including the error message and the cause of the error.

```java
try {
    // API call
} catch (FaspaySnapApiException e) {
    // Get error details
    String errorMessage = e.getMessage();
    Throwable cause = e.getCause();

    // Log the error
    System.err.println("Error: " + errorMessage);
    if (cause != null) {
        System.err.println("Cause: " + cause.getMessage());
    }

    // You might want to handle different types of errors differently
    if (errorMessage.contains("timeout")) {
        // Handle timeout errors
    } else if (errorMessage.contains("authentication")) {
        // Handle authentication errors
    } else {
        // Handle other errors
    }
}
```

## SSL Certificate

The SDK requires an SSL certificate to establish a secure connection with the Faspay SendMe Snap API. The certificate should be provided in PEM format.

You can load the certificate from a file:

```java
String sslCertificate = Files.readString(new File("path/to/your/certificate.pem").toPath());
```

Or from a resource in your classpath:

```java
URL resourceSsl = YourClass.class.getResource("/faspay.crt");
String sslCertificate = Files.readString(new File(resourceSsl.getFile()).toPath());
```

## Complete Examples

For complete examples, see the example classes in the SDK:

- `AccountInquiryExample.java` - Example for account inquiry
- `TransferInterbankExample.java` - Example for interbank transfer

## License

This SDK is licensed under the Apache License 2.0. See the [LICENSE](LICENSE) file for details.

## Support

For support or questions, please contact [support@faspay.co.id](mailto:support@faspay.co.id).
