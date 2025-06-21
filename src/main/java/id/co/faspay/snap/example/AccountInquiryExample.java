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

/**
 * Example demonstrating how to use the Faspay SendMe Snap SDK for account inquiry.
 */
public class AccountInquiryExample {

    public static void main(String[] args) throws IOException {
        URL resourceSsl = AccountInquiryExample.class.getResource("/faspay.crt");
        URL privateKeyResource = AccountInquiryExample.class.getResource("/enc_stg.key");

        assert privateKeyResource != null;
        String privateKeyStr = Files.readString(new File(privateKeyResource.getFile()).toPath());

        assert resourceSsl != null;
        String sslString = Files.readString(new File(resourceSsl.getFile()).toPath());

        // Replace these values with your actual credentials
        String partnerId = "99999";

        try {
            // Create a configuration with your credentials
            FaspaySnapConfig config = new FaspaySnapConfig(partnerId, privateKeyStr, sslString);

            // By default, the environment is set to "sandbox"
            // You can explicitly set the environment if needed:
            config.setEnv("sandbox");     // For testing/development
            // config.setEnv("production");  // For production use

            // Create a Faspay SendMe Snap client
            FaspaySnapClient client = new FaspaySnapClient(config);

            // Create a request with the required format
            AccountInquiryRequest request = new AccountInquiryRequest(
                    "013",                  // beneficiaryBankCode
                    "1197363",      // beneficiaryAccountNo
                    "20250619161815596"  // partnerReferenceNo
            );

            // Add additional info
            request.setSourceAccount("9920017573");

            // Perform an account inquiry
            AccountInquiryResponse response = client.accountInquiry().inquire(request);

            // Process the response
            if (response.isSuccess()) {
                System.out.println("Account inquiry successful!");
                System.out.println("Response code: " + response.getResponseCode());
                System.out.println("Response message: " + response.getResponseMessage());
                System.out.println("Reference number: " + response.getReferenceNo());
                System.out.println("Beneficiary account name: " + response.getAccountHolderName());
                System.out.println("Beneficiary account number: " + response.getAccountNumber());
                System.out.println("Beneficiary bank code: " + response.getBankCode());
                System.out.println("Beneficiary bank name: " + response.getBankName());
                System.out.println("Currency: " + response.getCurrency());
                System.out.println("Additional info: " + response.getAdditionalInfo());
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
