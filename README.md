# Faspay SendMe Snap Java SDK

A Java SDK for integrating with the Faspay SendMe Snap API. This SDK provides a simple and convenient way to interact with the Faspay SendMe Snap API for disbursement operations, bill payments, account inquiries, and more.

## Table of Contents

- [Features](#features)
- [Requirements](#requirements)
- [Installation](#installation)
  - [Gradle](#gradle)
  - [Maven](#maven)
  - [Direct JAR Usage](#direct-jar-usage)
- [Quick Start](#quick-start)
- [Configuration](#configuration)
- [Available Services](#available-services)
  - [Account Inquiry](#account-inquiry)
  - [Interbank Transfer](#interbank-transfer)
  - [Bill Inquiry](#bill-inquiry)
  - [Bill Payment](#bill-payment)
  - [Customer Topup](#customer-topup)
  - [Customer Topup Status](#customer-topup-status)
  - [History List](#history-list)
  - [Inquiry Balance](#inquiry-balance)
  - [Transfer Status](#transfer-status)
- [Error Handling](#error-handling)
- [SSL Certificate](#ssl-certificate)
- [Complete Examples](#complete-examples)
- [Project Structure](#project-structure)
- [Contributing](#contributing)
- [License](#license)
- [Support](#support)

## Features

- Complete API support for all Faspay SendMe Snap services:
  - Account Inquiry
  - Interbank Transfer
  - Bill Inquiry
  - Bill Payment
  - Customer Topup
  - Customer Topup Status
  - History List
  - Inquiry Balance
  - Transfer Status
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

### Direct JAR Usage

If you prefer to use the JAR file directly without a build system:

1. Download or build the JAR file:
   - Build it yourself using `.\gradlew shadowJar` command
   - The JAR file will be created at `build\libs\faspay-sendme-snap-java-1.0.0.jar`

2. Add the JAR to your project's classpath:
   - For a simple Java application, use the `-cp` option when running:
     ```
     java -cp path\to\faspay-sendme-snap-java-1.0.0.jar;. YourMainClass
     ```
   - For an IDE like Eclipse or IntelliJ IDEA, add the JAR file to your project's libraries

3. Import and use the SDK classes in your code as shown in the examples below

## Quick Start

Here's a complete example to get you started with the Faspay SendMe Snap Java SDK:

```java
import id.co.faspay.snap.FaspaySnapClient;
import id.co.faspay.snap.config.FaspaySnapConfig;
import id.co.faspay.snap.model.AccountInquiryResponse;
import id.co.faspay.snap.exception.FaspaySnapApiException;

import java.io.File;
import java.nio.file.Files;

public class QuickStartExample {
    public static void main(String[] args) throws Exception {
        // Load your SSL certificate and private key
        String privateKeyStr = Files.readString(new File("path/to/your/private.key").toPath());
        String sslString = Files.readString(new File("path/to/your/certificate.pem").toPath());

        // Create a configuration with your credentials
        FaspaySnapConfig config = new FaspaySnapConfig(
            "your-partner-id",     // Partner ID provided by Faspay
            privateKeyStr,         // Private key for signing requests
            sslString              // SSL certificate for secure communication
        );
        config.setEnv("sandbox");  // For testing/development

        // Create a client with your configuration
        FaspaySnapClient client = new FaspaySnapClient(config);

        // Perform an account inquiry
        try {
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
            } else {
                System.out.println("Account inquiry failed!");
                System.out.println("Response code: " + response.getResponseCode());
                System.out.println("Response message: " + response.getResponseMessage());
            }
        } catch (FaspaySnapApiException e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
```

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

## Bill Inquiry

The Bill Inquiry API allows you to check details of a virtual account bill before making a payment.

### Basic Usage

```java
import id.co.faspay.snap.model.BillInquiryRequest;
import id.co.faspay.snap.model.BillInquiryResponse;
import id.co.faspay.snap.exception.FaspaySnapApiException;

try {
    // Create additional info
    BillInquiryRequest.AdditionalInfo additionalInfo = new BillInquiryRequest.AdditionalInfo();
    additionalInfo.setBillerCode("013");
    additionalInfo.setSourceAccount("9920017573");

    // Perform a bill inquiry with direct parameters
    BillInquiryResponse response = client.billInquiry().inquiry(
        new BillInquiryRequest(
            "REF-" + System.currentTimeMillis(),  // Unique partner reference number
            "7008",                               // Partner service ID
            "08000047816",                        // Customer number
            "700808000047816",                    // Virtual account number
            additionalInfo                        // Additional info
        )
    );

    // Process the response
    if (response.isSuccess()) {
        System.out.println("Bill inquiry successful!");

        // Get virtual account data
        BillInquiryResponse.VirtualAccountData vaData = response.getVirtualAccountData();
        System.out.println("Virtual Account Name: " + vaData.getVirtualAccountName());
        System.out.println("Total Amount: " + vaData.getTotalAmount().getValue() + " " + 
                                             vaData.getTotalAmount().getCurrency());
        System.out.println("Partner Reference Number: " + vaData.getPartnerReferenceNo());
    } else {
        System.out.println("Bill inquiry failed!");
        System.out.println("Response code: " + response.getResponseCode());
        System.out.println("Response message: " + response.getResponseMessage());
    }
} catch (FaspaySnapApiException e) {
    System.err.println("Error: " + e.getMessage());
    e.printStackTrace();
}
```

### Response Handling

The `BillInquiryResponse` object contains detailed information about the virtual account:

```java
if (response.isSuccess()) {
    // Basic information
    String responseCode = response.getResponseCode();
    String responseMessage = response.getResponseMessage();

    // Virtual account data
    BillInquiryResponse.VirtualAccountData vaData = response.getVirtualAccountData();
    String partnerServiceId = vaData.getPartnerServiceId();
    String customerNo = vaData.getCustomerNo();
    String virtualAccountNo = vaData.getVirtualAccountNo();
    String virtualAccountName = vaData.getVirtualAccountName();
    String virtualAccountTrxType = vaData.getVirtualAccountTrxType();
    String partnerReferenceNo = vaData.getPartnerReferenceNo();

    // Amount information
    Amount totalAmount = vaData.getTotalAmount();
    String value = totalAmount.getValue();
    String currency = totalAmount.getCurrency();

    // Additional information
    Map<String, String> additionalInfo = response.getAdditionalInfo();
}
```

## Bill Payment

The Bill Payment API allows you to pay a virtual account bill after performing a bill inquiry.

### Basic Usage

```java
import id.co.faspay.snap.model.BillPaymentRequest;
import id.co.faspay.snap.model.BillPaymentResponse;
import id.co.faspay.snap.exception.FaspaySnapApiException;

try {
    // Create additional info
    BillPaymentRequest.AdditionalInfo additionalInfo = new BillPaymentRequest.AdditionalInfo();
    additionalInfo.setBillerCode("013");
    additionalInfo.setSourceAccount("9920017573");

    // Create amount
    Amount amount = new Amount("100000.00", "IDR");

    // Perform a bill payment
    BillPaymentResponse response = client.billPayment().pay(
        new BillPaymentRequest(
            "REF-" + System.currentTimeMillis(),  // Unique partner reference number
            "7008",                               // Partner service ID
            "08000047816",                        // Customer number
            "700808000047816",                    // Virtual account number
            amount,                               // Amount to pay
            additionalInfo                        // Additional info
        )
    );

    // Process the response
    if (response.isSuccess()) {
        System.out.println("Bill payment successful!");
        System.out.println("Transaction ID: " + response.getTransactionId());
        System.out.println("Reference Number: " + response.getReferenceNo());
    } else {
        System.out.println("Bill payment failed!");
        System.out.println("Response code: " + response.getResponseCode());
        System.out.println("Response message: " + response.getResponseMessage());
    }
} catch (FaspaySnapApiException e) {
    System.err.println("Error: " + e.getMessage());
    e.printStackTrace();
}
```

## Customer Topup

The Customer Topup API allows you to top up a customer's account (e.g., for mobile credit or similar services).

### Basic Usage

```java
import id.co.faspay.snap.model.Amount;
import id.co.faspay.snap.model.CustomerTopupRequest;
import id.co.faspay.snap.model.CustomerTopupResponse;
import id.co.faspay.snap.exception.FaspaySnapApiException;

try {
    // Create amount object
    Amount amount = new Amount();
    amount.setCurrency("IDR");
    amount.setValue("90107.00");

    // Create additional info
    CustomerTopupRequest.AdditionalInfo additionalInfo = new CustomerTopupRequest.AdditionalInfo();
    additionalInfo.setSourceAccount("9920017573");
    additionalInfo.setPlatformCode("gpy");
    additionalInfo.setBeneficiaryEmail("customer@example.com");
    additionalInfo.setTransactionDescription("Monthly Mobile Credit");
    additionalInfo.setCallbackUrl("https://your-domain.com/callback");

    // Create request object
    CustomerTopupRequest request = new CustomerTopupRequest();
    request.setPartnerReferenceNo("REF-" + System.currentTimeMillis());
    request.setCustomerNumber("0812254830");  // Customer's phone number
    request.setAmount(amount);
    request.setTransactionDate(ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX")));
    request.setAdditionalInfo(additionalInfo);

    // Perform the topup
    CustomerTopupResponse response = client.customerTopup().topup(request);

    // Process the response
    if (response.isSuccess()) {
        System.out.println("Customer topup successful!");
        System.out.println("Response code: " + response.getResponseCode());
        System.out.println("Response message: " + response.getResponseMessage());
        System.out.println("Transaction status: " + response.getAdditionalInfo().getLatestTransactionStatus());
    } else {
        System.out.println("Customer topup failed!");
        System.out.println("Response code: " + response.getResponseCode());
        System.out.println("Response message: " + response.getResponseMessage());
    }
} catch (FaspaySnapApiException e) {
    System.err.println("Error: " + e.getMessage());
    e.printStackTrace();
}
```

### Response Handling

The `CustomerTopupResponse` object contains information about the topup transaction:

```java
if (response.isSuccess()) {
    // Basic information
    String responseCode = response.getResponseCode();
    String responseMessage = response.getResponseMessage();

    // Transaction status
    String transactionStatus = response.getAdditionalInfo().getLatestTransactionStatus();

    // Additional information
    Map<String, String> additionalInfo = response.getAdditionalInfo().getAdditionalInfo();
}
```

## Customer Topup Status

The Customer Topup Status API allows you to check the status of a previous customer topup transaction.

### Basic Usage

```java
import id.co.faspay.snap.model.CustomerTopupStatusRequest;
import id.co.faspay.snap.model.CustomerTopupStatusResponse;
import id.co.faspay.snap.exception.FaspaySnapApiException;

try {
    // Create request object with partner reference number and transaction identifiers
    CustomerTopupStatusRequest request = new CustomerTopupStatusRequest(
        "REF-12345678",  // Partner reference number used in the original topup request
        "150207",        // Transaction ID
        "38"             // Additional identifier
    );

    // Check the status
    CustomerTopupStatusResponse response = client.customerTopupStatus().status(request);

    // Process the response
    if (response.isSuccess()) {
        System.out.println("Status check successful!");
        System.out.println("Response code: " + response.getResponseCode());
        System.out.println("Latest transaction status: " + response.getLatestTransactionStatus());
    } else {
        System.out.println("Status check failed!");
        System.out.println("Response code: " + response.getResponseCode());
        System.out.println("Response message: " + response.getResponseMessage());
    }
} catch (FaspaySnapApiException e) {
    System.err.println("Error: " + e.getMessage());
    e.printStackTrace();
}
```

### Response Handling

The `CustomerTopupStatusResponse` object contains information about the status of the topup transaction:

```java
if (response.isSuccess()) {
    // Status information
    String responseCode = response.getResponseCode();
    String responseMessage = response.getResponseMessage();
    String transactionStatus = response.getLatestTransactionStatus();

    // Additional information if available
    Map<String, String> additionalInfo = response.getAdditionalInfo();
}
```

## History List

The History List API allows you to retrieve a list of transaction history within a specified time range for a specific account.

### Basic Usage

```java
import id.co.faspay.snap.model.HistoryListRequest;
import id.co.faspay.snap.model.HistoryListResponse;
import id.co.faspay.snap.exception.FaspaySnapApiException;

try {
    // Create request object with time range and account number
    HistoryListRequest request = new HistoryListRequest(
        "2023-06-23T08:00:00+07:00",  // Start date/time in ISO 8601 format
        "2023-06-23T18:00:00+07:00",  // End date/time in ISO 8601 format
        "9920017573"                  // Account number
    );

    // Retrieve the history list
    HistoryListResponse response = client.historyList().list(request);

    // Process the response
    if (response.isSuccess()) {
        System.out.println("History list retrieved successfully!");
        System.out.println("Response message: " + response.getResponseMessage());
        System.out.println("Detail data: " + response.getDetailData());
        System.out.println("Account number: " + response.getAdditionalInfo().getAccountNo());

        // Process transaction history data if available
        // The structure depends on the API response format
    } else {
        System.out.println("History list retrieval failed!");
        System.out.println("Response code: " + response.getResponseCode());
        System.out.println("Response message: " + response.getResponseMessage());
    }
} catch (FaspaySnapApiException e) {
    System.err.println("Error: " + e.getMessage());
    e.printStackTrace();
}
```

### Response Handling

The `HistoryListResponse` object contains the transaction history data:

```java
if (response.isSuccess()) {
    // Basic information
    String responseMessage = response.getResponseMessage();
    Object detailData = response.getDetailData();  // This might be a list or map of transactions

    // Account information
    String accountNo = response.getAdditionalInfo().getAccountNo();

    // Additional information
    Map<String, String> additionalInfo = response.getAdditionalInfo().getAdditionalInfo();

    // Process the detail data according to its structure
    // This might involve iterating through a list of transactions
}
```

## Inquiry Balance

The Inquiry Balance API allows you to check the balance of a specific account.

### Basic Usage

```java
import id.co.faspay.snap.model.InquiryBalanceRequest;
import id.co.faspay.snap.model.InquiryBalanceResponse;
import id.co.faspay.snap.exception.FaspaySnapApiException;

try {
    // Create request object with account number
    InquiryBalanceRequest request = new InquiryBalanceRequest("9920017573");

    // Check the balance
    InquiryBalanceResponse response = client.inquiryBalance().balance(request);

    // Process the response
    if (response.isSuccess()) {
        System.out.println("Balance inquiry successful!");
        System.out.println("Response code: " + response.getResponseCode());
        System.out.println("Response message: " + response.getResponseMessage());
        System.out.println("Account balance: " + response.getBalance());
        System.out.println("Currency: " + response.getCurrency());
    } else {
        System.out.println("Balance inquiry failed!");
        System.out.println("Response code: " + response.getResponseCode());
        System.out.println("Response message: " + response.getResponseMessage());
    }
} catch (FaspaySnapApiException e) {
    System.err.println("Error: " + e.getMessage());
    e.printStackTrace();
}
```

### Response Handling

The `InquiryBalanceResponse` object contains the account balance information:

```java
if (response.isSuccess()) {
    // Basic information
    String responseCode = response.getResponseCode();
    String responseMessage = response.getResponseMessage();

    // Balance information
    String balance = response.getBalance();
    String currency = response.getCurrency();

    // Additional information if available
    Map<String, String> additionalInfo = response.getAdditionalInfo();
}
```

## Transfer Status

The Transfer Status API allows you to check the status of a previous transfer transaction.

### Basic Usage

```java
import id.co.faspay.snap.model.StatusTransferRequest;
import id.co.faspay.snap.model.StatusTransferResponse;
import id.co.faspay.snap.exception.FaspaySnapApiException;

try {
    // Create request object with partner reference number and transaction identifiers
    StatusTransferRequest request = new StatusTransferRequest(
        "REF-12345678",  // Partner reference number used in the original transfer request
        "150120",        // Transaction ID
        "18"             // Additional identifier
    );

    // Check the status
    StatusTransferResponse response = client.transferStatus().status(request);

    // Process the response
    if (response.isSuccess()) {
        System.out.println("Status check successful!");
        System.out.println("Response code: " + response.getResponseCode());
        System.out.println("Latest transaction status: " + response.getLatestTransactionStatus());
        System.out.println("Callback URL: " + response.getCallbackUrl());
    } else {
        System.out.println("Status check failed!");
        System.out.println("Response code: " + response.getResponseCode());
        System.out.println("Response message: " + response.getResponseMessage());
    }
} catch (FaspaySnapApiException e) {
    System.err.println("Error: " + e.getMessage());
    e.printStackTrace();
}
```

### Response Handling

The `StatusTransferResponse` object contains information about the status of the transfer transaction:

```java
if (response.isSuccess()) {
    // Status information
    String responseCode = response.getResponseCode();
    String responseMessage = response.getResponseMessage();
    String transactionStatus = response.getLatestTransactionStatus();
    String callbackUrl = response.getCallbackUrl();

    // Additional information if available
    Map<String, String> additionalInfo = response.getAdditionalInfo();
}
```

## Complete Examples

For complete examples, see the example classes in the SDK:

- `AccountInquiryExample.java` - Example for account inquiry
- `TransferInterbankExample.java` - Example for interbank transfer
- `BillInquiryExample.java` - Example for bill inquiry
- `BillPaymentExample.java` - Example for bill payment
- `CustomerTopupExample.java` - Example for customer topup
- `CustomerTopupStatusExample.java` - Example for checking customer topup status
- `HistoryListExample.java` - Example for retrieving transaction history
- `InquiryBalanceExample.java` - Example for checking account balance
- `TransferStatusExample.java` - Example for checking transfer status

## Project Structure

The SDK is organized into the following packages:

- `id.co.faspay.snap` - Main package containing the `FaspaySnapClient` class
- `id.co.faspay.snap.client` - HTTP clients for each service
- `id.co.faspay.snap.config` - Configuration classes
- `id.co.faspay.snap.exception` - Exception classes
- `id.co.faspay.snap.model` - Request and response models
- `id.co.faspay.snap.service` - Service interfaces and implementations
- `id.co.faspay.snap.util` - Utility classes
- `id.co.faspay.snap.example` - Example code for each service

## Publishing to Maven Central

This SDK is configured to be published to Maven Central. Follow these steps to publish a new version:

### Prerequisites

1. **Sonatype OSSRH Account**: You need an account on [Sonatype OSSRH](https://central.sonatype.org/publish/publish-guide/).
   - Register at [Sonatype JIRA](https://issues.sonatype.org/secure/Signup)
   - Create a new project ticket to request access to publish under your group ID

2. **GPG Key**: You need a GPG key pair to sign the artifacts.
   - Install GPG: [GnuPG](https://gnupg.org/download/)
   - Generate a key pair: `gpg --gen-key`
   - List your keys: `gpg --list-keys`
   - Publish your key: `gpg --keyserver keyserver.ubuntu.com --send-keys YOUR_KEY_ID`

### Configuration

1. **Update gradle.properties**: Create or update your `~/.gradle/gradle.properties` file with your credentials:

```properties
# Sonatype credentials
sonatype.username=your_sonatype_username
sonatype.password=your_sonatype_password

# Signing configuration
signing.keyId=YOUR_KEY_ID_LAST_8_CHARS
signing.password=your_gpg_key_password
signing.secretKeyRingFile=C:/Users/your_username/.gnupg/secring.gpg
```

2. **Export your secret key ring file** (if it doesn't exist):
   - For GPG 2.1 and newer: `gpg --export-secret-keys -o secring.gpg`
   - Move the file to the location specified in your gradle.properties

### Publishing Process

1. **Prepare the release**:
   - Update the version in `build.gradle.kts`
   - Update the README.md with the new version
   - Commit and push your changes

2. **Build and publish**:
   - Run the following command to publish to Maven Central:
     ```
     ./gradlew publishToSonatype closeAndReleaseSonatypeStagingRepository
     ```
   - This will:
     - Build the project
     - Sign the artifacts
     - Upload to Sonatype OSSRH
     - Close and release the staging repository

3. **Verify the publication**:
   - Check the status in the [Sonatype Repository Manager](https://s01.oss.sonatype.org/)
   - The artifacts will be available in Maven Central within a few hours

### Troubleshooting

- If you encounter signing issues, make sure your GPG key is correctly set up and the password is correct
- If you have issues with the Sonatype repository, check the [Sonatype OSSRH Guide](https://central.sonatype.org/publish/publish-guide/)

## Contributing

Contributions to the SDK are welcome! If you find a bug or have a feature request, please open an issue on the GitHub repository. If you want to contribute code, please fork the repository and submit a pull request.

### Development Setup

1. Clone the repository:
   ```
   git clone https://github.com/faspay/faspay-sendme-snap-java.git
   ```

2. Build the project:
   ```
   cd faspay-sendme-snap-java
   ./gradlew build
   ```

3. Run the tests:
   ```
   ./gradlew test
   ```

### Coding Standards

- Follow the Java coding conventions
- Write unit tests for new features
- Update documentation for changes

## License

This SDK is licensed under the Apache License 2.0. See the [LICENSE](LICENSE) file for details.

## Using the JAR File Directly

If you prefer to use the JAR file directly without a build system:

1. Download or build the JAR file:
   - Build it yourself using `.\gradlew shadowJar` command
   - The JAR file will be created at `build\libs\faspay-sendme-snap-java-1.0.0.jar`

2. Add the JAR to your project's classpath:
   - For a simple Java application, use the `-cp` option when running:
     ```
     java -cp path\to\faspay-sendme-snap-java-1.0.0.jar;. YourMainClass
     ```
   - For an IDE like Eclipse or IntelliJ IDEA, add the JAR file to your project's libraries

3. Import and use the SDK classes in your code as shown in the examples above

## Support

For support or questions, please contact [support@faspay.co.id](mailto:support@faspay.co.id).
