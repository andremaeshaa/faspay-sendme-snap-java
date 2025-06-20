# Faspay SendMe Snap Java SDK

A Java SDK for integrating with the Faspay SendMe Snap API. This SDK provides a simple and convenient way to interact with the Faspay SendMe Snap API for disbursement operations.

## Features

- Account Inquiry API support
- Secure communication with SSL certificate
- Request signing with HMAC-SHA256
- Comprehensive error handling
- Easy-to-use fluent API

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

## Usage

### Configuration

To use the SDK, you need to create a configuration with your Faspay SendMe Snap API credentials:

```java
import id.co.faspay.snap.config.FaspaySnapConfig;

// Create a configuration with your credentials
FaspaySnapConfig config = new FaspaySnapConfig(
    "your-partner-id",
    "your-private-key",
    "path/to/your/ssl/certificate.pem"
);
```

### Creating a Client

Once you have a configuration, you can create a client to interact with the API:

```java
import id.co.faspay.snap.FaspaySnapClient;

// Create a client with your configuration
FaspaySnapClient client = new FaspaySnapClient(config);
```

### Account Inquiry

To inquire about a bank account:

```java
import id.co.faspay.snap.model.AccountInquiryResponse;
import id.co.faspay.snap.exception.FaspaySnapApiException;

try {
    // Perform an account inquiry
    AccountInquiryResponse response = client.accountInquiry().inquire(
        "BCA",                  // Bank code
        "1234567890",           // Account number
        "REF-" + System.currentTimeMillis()  // Partner reference number
    );
    
    // Process the response
    if (response.isSuccess()) {
        System.out.println("Account inquiry successful!");
        System.out.println("Account holder name: " + response.getAccountHolderName());
        System.out.println("Faspay reference number: " + response.getFaspayReferenceNumber());
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

## Error Handling

The SDK throws `FaspaySnapApiException` when an error occurs while interacting with the API. This exception contains information about the error, including the error message and the cause of the error.

```java
try {
    // API call
} catch (FaspaySnapApiException e) {
    // Handle the error
    System.err.println("Error: " + e.getMessage());
    e.printStackTrace();
}
```

## SSL Certificate

The SDK requires an SSL certificate to establish a secure connection with the Faspay SendMe Snap API. The certificate should be provided in PEM format.

## License

This SDK is licensed under the Apache License 2.0. See the [LICENSE](LICENSE) file for details.

## Support

For support or questions, please contact [support@faspay.co.id](mailto:support@faspay.co.id).