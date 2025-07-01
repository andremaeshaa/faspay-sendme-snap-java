package id.co.faspay.snap.example;

import id.co.faspay.snap.FaspaySnapClient;
import id.co.faspay.snap.config.FaspaySnapConfig;
import id.co.faspay.snap.exception.FaspaySnapApiException;
import id.co.faspay.snap.model.BillPaymentRequest;
import id.co.faspay.snap.model.BillPaymentResponse;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Example demonstrating how to use the Bill Payment API.
 * This example shows how to pay a virtual account bill after performing a bill inquiry.
 */
public class BillPaymentExample {
    public static void main(String[] args) throws IOException {
        // Load SSL certificate and private key from resources
        URL resourceSsl = BillPaymentExample.class.getResource("/faspay.crt");
        URL privateKeyResource = BillPaymentExample.class.getResource("/enc_stg_ori.key");

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

            // Create payment request
            BillPaymentRequest request = getBillPaymentRequest();

            System.out.println("Request data: " + request.toString());

            // Perform bill payment
            System.out.println("Performing bill payment for virtual account: " + request.getVirtualAccountNo());
            BillPaymentResponse response = client.billPayment().payment(request);

            // Process the response
            if (response.isSuccess()) {
                System.out.println("response: " + response.toString());
                System.out.println("Bill payment successful!");
                System.out.println("Response code: " + response.getResponseCode());
                System.out.println("Response message: " + response.getResponseMessage());

                // Virtual account data
                if (response.getVirtualAccountData() != null) {
                    System.out.println("Reference Number: " + response.getVirtualAccountData().getReferenceNo());
                    System.out.println("Partner Reference Number: " + response.getVirtualAccountData().getPartnerReferenceNo());
                    System.out.println("Virtual Account Number: " + response.getVirtualAccountData().getVirtualAccountNo());
                    System.out.println("Virtual Account Name: " + response.getVirtualAccountData().getVirtualAccountName());

                    // Paid amount
                    if (response.getVirtualAccountData().getPaidAmount() != null) {
                        System.out.println("Paid Amount: " + 
                            response.getVirtualAccountData().getPaidAmount().getValue() + " " + 
                            response.getVirtualAccountData().getPaidAmount().getCurrency());
                    }

                    System.out.println("Transaction Date Time: " + response.getVirtualAccountData().getTrxDateTime());
                }

                // Additional information
                if (response.getAdditionalInfo() != null) {
                    System.out.println("Additional Information:");
                    BillPaymentResponse.AdditionalInfo info = response.getAdditionalInfo();

                    if (info.getBillerCode() != null) {
                        System.out.println("  Biller Code: " + info.getBillerCode());
                    }
                    if (info.getInstructDate() != null) {
                        System.out.println("  Instruct Date: " + info.getInstructDate());
                    }
                    if (info.getCallbackUrl() != null) {
                        System.out.println("  Callback URL: " + info.getCallbackUrl());
                    }
                    if (info.getStatus() != null) {
                        System.out.println("  Status: " + info.getStatus());
                    }
                    if (info.getMessage() != null) {
                        System.out.println("  Message: " + info.getMessage());
                    }
                }
            } else {
                System.out.println("Bill payment failed!");
                System.out.println("Response code: " + response.getResponseCode());
                System.out.println("Response message: " + response.getResponseMessage());
            }
        } catch (FaspaySnapApiException e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Creates a bill payment request with sample data.
     * In a real application, this data would come from user input or a database.
     *
     * @return A populated bill payment request
     */
    @NotNull
    private static BillPaymentRequest getBillPaymentRequest() {
        // Generate current timestamp and reference number
        ZonedDateTime now = ZonedDateTime.now();
        String referenceNo = now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        String transactionDateTime = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
        String instructDate = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX"));

        // Create request objects
        BillPaymentRequest request = new BillPaymentRequest();
        BillPaymentRequest.PaidAmount paidAmount = new BillPaymentRequest.PaidAmount();
        BillPaymentRequest.AdditionalInfo additionalInfo = new BillPaymentRequest.AdditionalInfo();

        // Set request properties
        request.setPartnerReferenceNo(referenceNo);
        request.setPartnerServiceId("    7008");
        request.setCustomerNo("08000047816");
        request.setVirtualAccountNo("700808000047816");
        request.setVirtualAccountName("John Doe");
        request.setSourceAccount("9920017573");

        // Set payment amount
        paidAmount.setValue("118680.00");
        paidAmount.setCurrency("IDR");
        request.setPaidAmount(paidAmount);
        request.setTrxDateTime(transactionDateTime);

        // Set additional information
        additionalInfo.setBillerCode("013");
        additionalInfo.setInstructDate(instructDate);
        additionalInfo.setCallbackUrl("https://example.com/callback");
        request.setAdditionalInfo(additionalInfo);

        return request;
    }
}
