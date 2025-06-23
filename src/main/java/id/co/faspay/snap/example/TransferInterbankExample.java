package id.co.faspay.snap.example;

import id.co.faspay.snap.FaspaySnapClient;
import id.co.faspay.snap.config.FaspaySnapConfig;
import id.co.faspay.snap.exception.FaspaySnapApiException;
import id.co.faspay.snap.model.Amount;
import id.co.faspay.snap.model.TransferInterbankRequest;
import id.co.faspay.snap.model.TransferInterbankResponse;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Example demonstrating how to use the Faspay SendMe Snap SDK for interbank transfers.
 * This example shows how to:
 * 1. Load SSL certificate and private key
 * 2. Create a configuration with your credentials
 * 3. Create a client
 * 4. Create and configure an interbank transfer request
 * 5. Send the request and process the response
 * 6. Handle errors
 * The interbank transfer API allows you to transfer money from your account to an account
 * at another bank. This is useful for disbursement operations.
 */
public class TransferInterbankExample {

    public static void main(String[] args) throws IOException {
        // ======== STEP 1: Load SSL certificate and private key ========
        // These files should be in your resources directory
        URL resourceSsl = TransferInterbankExample.class.getResource("/faspay.crt");
        URL privateKeyResource = TransferInterbankExample.class.getResource("/enc_stg.key");

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

            // ======== STEP 4: Create and configure an interbank transfer request ========
            // First, create an amount object with the transfer amount and currency
            Amount amount = new Amount("50001.00", "IDR");

            // Method 1: Using the constructor with required parameters
            TransferInterbankRequest request = new TransferInterbankRequest(
                    "20250623101414882",       // partnerReferenceNo - Unique reference number for tracking
                    amount,                    // amount - Amount object with value and currency
                    "SUSANTO WANGSADJAJA",   // beneficiaryAccountName - Name of the recipient
                    "1197363",             // beneficiaryAccountNo - Account number of the recipient
                    "013",                     // beneficiaryBankCode - Bank code (e.g., 008 for Mandiri)
                    "9920017573"               // sourceAccountNo - Your account number
            );

            // originatorInfos parameter
            request.setOriginatorCustomerName("PT kurang duit");
            request.setOriginatorCustomerNo("087742290748");
            request.setOriginatorBankCode("099");

            // Add optional parameters
            request.setBeneficiaryEmail("andremaesha@gmail.com"); // Email of the recipient (for notifications)
            request.setTransactionDescription("snapmandiri20250609103003"); // Description of the transaction
            request.setCallbackUrl("http://account-service/account/api/mail/sendtotele"); // URL for callback notifications

            // ======== STEP 5: Send the request and process the response ========
            System.out.println("Sending interbank transfer request...");
            TransferInterbankResponse response = client.transferInterbank().transfer(request);

            // Process the response
            if (response.isSuccess()) {
                System.out.println("\n===== INTERBANK TRANSFER SUCCESSFUL =====");
                System.out.println("Response code: " + response.getResponseCode());
                System.out.println("Response message: " + response.getResponseMessage());

                System.out.println("\n----- Transaction Details -----");
                System.out.println("Amount: " + response.getAmount().getValue() + " " + response.getAmount().getCurrency());
                System.out.println("Transaction description: " + response.getTransactionDescription());
                System.out.println("Latest transaction status: " + response.getLatestTransactionStatus());
                System.out.println("Transaction status description: " + response.getTransactionStatusDesc());

                System.out.println("\n----- Beneficiary Details -----");
                System.out.println("Beneficiary account name: " + response.getBeneficiaryAccountName());
                System.out.println("Beneficiary account number: " + response.getBeneficiaryAccountNumber());
                System.out.println("Beneficiary bank code: " + response.getBeneficiaryBankCode());
                System.out.println("Beneficiary bank name: " + response.getBeneficiaryBankName());

                System.out.println("\n----- Source Details -----");
                System.out.println("Source account number: " + response.getSourceAccountNumber());

                System.out.println("\n----- Reference Information -----");
                System.out.println("Faspay reference number: " + response.getReferenceNumber());
                System.out.println("Partner reference number: " + response.getPartnerReferenceNumber());
                System.out.println("Instruct date: " + response.getInstructDate());
                System.out.println("Callback URL: " + response.getCallbackUrl());

                // Print additional info if available
                if (response.getAdditionalInfo() != null && !response.getAdditionalInfo().isEmpty()) {
                    System.out.println("\n----- Additional Information -----");
                    response.getAdditionalInfo().forEach((key, value) -> {
                        // Skip fields we've already displayed
                        if (!key.equals("beneficiaryAccountName") && 
                            !key.equals("beneficiaryBankName") && 
                            !key.equals("instructDate") && 
                            !key.equals("transactionDescription") && 
                            !key.equals("callbackUrl") && 
                            !key.equals("latestTransactionStatus") && 
                            !key.equals("transactionStatusDesc")) {
                            System.out.println(key + ": " + value);
                        }
                    });
                }
            } else {
                System.out.println("\n===== INTERBANK TRANSFER FAILED =====");
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
