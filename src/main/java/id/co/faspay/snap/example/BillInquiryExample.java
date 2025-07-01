package id.co.faspay.snap.example;

import id.co.faspay.snap.FaspaySnapClient;
import id.co.faspay.snap.config.FaspaySnapConfig;
import id.co.faspay.snap.exception.FaspaySnapApiException;
import id.co.faspay.snap.model.BillInquiryRequest;
import id.co.faspay.snap.model.BillInquiryResponse;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Example demonstrating how to use the Bill Inquiry API.
 * This example shows how to check details of a virtual account bill before making a payment.
 */
public class BillInquiryExample {
    public static void main(String[] args) throws IOException {
        // Load SSL certificate and private key from resources
        URL resourceSsl = BillInquiryExample.class.getResource("/faspay.crt");
        URL privateKeyResource = BillInquiryExample.class.getResource("/enc_stg_ori.key");

        assert privateKeyResource != null;
        String privateKeyStr = Files.readString(new File(privateKeyResource.getFile()).toPath());

        assert resourceSsl != null;
        String sslString = Files.readString(new File(resourceSsl.getFile()).toPath());

        // Your partner ID provided by Faspay
        String partnerId = "99999";

        try {
            // Create configuration
            FaspaySnapConfig config = new FaspaySnapConfig(partnerId, privateKeyStr, sslString);
            config.setEnv("sandbox"); // Use "production" for production environment

            // Create client
            FaspaySnapClient client = new FaspaySnapClient(config);

            // Generate a unique reference number
            String referenceNo = ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));

            // Create additional info
            BillInquiryRequest.AdditionalInfo additionalInfo = new BillInquiryRequest.AdditionalInfo();
            additionalInfo.setBillerCode("013");
            additionalInfo.setSourceAccount("9920017573");

            // Create request
            BillInquiryRequest request = new BillInquiryRequest(
                referenceNo,        // Unique partner reference number
                "    7008",             // Partner service ID
                "08000047816",      // Customer number
                "700808000047816",  // Virtual account number
                additionalInfo      // Additional info
            );

            System.out.println("Request data: " + request.toString());
            // Perform bill inquiry
            System.out.println("Performing bill inquiry for virtual account: " + request.getVirtualAccountNo());
            BillInquiryResponse response = client.billInquiry().inquiry(request);

            System.out.println("Response data: " + response.toString());

            // Process the response
            if (response.isSuccess()) {
                System.out.println("Bill inquiry successful!");
                System.out.println("Response code: " + response.getResponseCode());
                System.out.println("Response message: " + response.getResponseMessage());

                // Get virtual account data
                BillInquiryResponse.VirtualAccountData vaData = response.getVirtualAccountData();
                if (vaData != null) {
                    System.out.println("Virtual Account Name: " + vaData.getVirtualAccountName());
                    if (vaData.getTotalAmount() != null) {
                        System.out.println("Total Amount: " + vaData.getTotalAmount().getValue() + " " + 
                                                             vaData.getTotalAmount().getCurrency());
                    }
                    System.out.println("Partner Reference Number: " + vaData.getPartnerReferenceNo());
                }
            } else {
                System.out.println("Bill inquiry failed!");
                System.out.println("Response code: " + response.getResponseCode());
                System.out.println("Response message: " + response.getResponseMessage());
            }
        } catch (FaspaySnapApiException e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
