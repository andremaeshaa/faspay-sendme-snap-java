package id.co.faspay.snap.example;

import id.co.faspay.snap.FaspaySnapClient;
import id.co.faspay.snap.config.FaspaySnapConfig;
import id.co.faspay.snap.exception.FaspaySnapApiException;
import id.co.faspay.snap.model.AccountInquiryRequest;
import id.co.faspay.snap.model.AccountInquiryResponse;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.Objects;

/**
 * Example demonstrating how to use the Faspay SendMe Snap SDK for account inquiry.
 * 
 * This example shows how to:
 * 1. Load SSL certificate and private key
 * 2. Create a configuration with your credentials
 * 3. Create a client
 * 4. Create and configure an account inquiry request
 * 5. Send the request and process the response
 * 6. Handle errors
 */
public class AccountInquiryExample {

    public static void main(String[] args) throws IOException {
        // ======== STEP 1: Load SSL certificate and private key ========
        // These files should be in your resources directory
        URL resourceSsl = AccountInquiryExample.class.getResource("/faspay.crt");
        URL privateKeyResource = AccountInquiryExample.class.getResource("/enc_stg.key");

        assert privateKeyResource != null;
        String privateKeyStr = Files.readString(new File(privateKeyResource.getFile()).toPath());

        assert resourceSsl != null;
        String sslString = Files.readString(new File(resourceSsl.getFile()).toPath());

        // ======== STEP 2: Create a configuration with your credentials ========
        // Replace these values with your actual credentials provided by Faspay
        String partnerId = "99999"; // Your partner ID from Faspay

        try {
            // Create a configuration with your credentials
            FaspaySnapConfig config = new FaspaySnapConfig(partnerId, privateKeyStr, sslString);

            // By default, the environment is set to "sandbox"
            // You can explicitly set the environment if needed:
            config.setEnv("sandbox");     // For testing/development
            // config.setEnv("production");  // For production use

            // ======== STEP 3: Create a Faspay SendMe Snap client ========
            FaspaySnapClient client = new FaspaySnapClient(config);

            // ======== STEP 4: Create and configure an account inquiry request ========
            // Method 1: Using the constructor with required parameters
            AccountInquiryRequest request = new AccountInquiryRequest(
                    "013",                  // beneficiaryBankCode - Bank code (e.g., 013 for Bank Permata)
                    "1197363",              // beneficiaryAccountNo - Account number to inquire
                    "20250619161815596"     // partnerReferenceNo - Unique reference number for tracking
            );

            // Add optional parameters
            request.setSourceAccount("9920017573"); // Source account number (if required)

            // ======== STEP 5: Send the request and process the response ========
            System.out.println("Sending account inquiry request...");
            AccountInquiryResponse response = client.accountInquiry().inquire(request);

            // Process the response
            if (response.isSuccess()) {
                System.out.println("\n===== ACCOUNT INQUIRY SUCCESSFUL =====");
                System.out.println("Response code: " + response.getResponseCode());
                System.out.println("Response message: " + response.getResponseMessage());
                System.out.println("\n----- Account Details -----");
                System.out.println("Account holder name: " + response.getAccountHolderName());
                System.out.println("Account number: " + response.getAccountNumber());
                System.out.println("Bank code: " + response.getBankCode());
                System.out.println("Bank name: " + response.getBankName());
                System.out.println("Currency: " + response.getCurrency());
                System.out.println("\n----- Reference Information -----");
                System.out.println("Faspay reference number: " + response.getReferenceNo());
                System.out.println("Partner reference number: " + response.getPartnerReferenceNumber());

                System.out.println("status: " + response.getStatus());
                System.out.println("message: " + response.getMessage());

                // Print additional info if available
                if (response.getAdditionalInfo() != null && !response.getAdditionalInfo().isEmpty()) {
                    System.out.println("\n----- Additional Information -----");
                    response.getAdditionalInfo().forEach((key, value) -> 
                        System.out.println(key + ": " + value));
                }
            } else {
                System.out.println("\n===== ACCOUNT INQUIRY FAILED =====");
                System.out.println("Response code: " + response.getResponseCode());
                System.out.println("Response message: " + response.getResponseMessage());

                // Print additional error details if available
                if (response.getAdditionalInfo() != null && !response.getAdditionalInfo().isEmpty()) {
                    System.out.println("\n----- Error Details -----");
                    response.getAdditionalInfo().forEach((key, value) -> 
                        System.out.println(key + ": " + value));
                }
            }

        } catch (FaspaySnapApiException e) {
            // ======== STEP 6: Handle errors ========
            System.err.println("\n===== ERROR OCCURRED =====");
            System.err.println("Error message: " + e.getMessage());
            System.err.println("Error cause: " + (e.getCause() != null ? e.getCause().getMessage() : "Unknown"));
            e.printStackTrace();
        }
    }
}
