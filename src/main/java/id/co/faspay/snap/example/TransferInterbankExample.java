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

/**
 * Example demonstrating how to use the Faspay SendMe Snap SDK for interbank transfers.
 */
public class TransferInterbankExample {

    public static void main(String[] args) throws IOException {
        URL resourceSsl = TransferInterbankExample.class.getResource("/faspay.crt");
        URL privateKeyResource = TransferInterbankExample.class.getResource("/enc_stg.key");

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

            // Create an amount object
            Amount amount = new Amount("59614.00", "IDR");

            // Create a request with the required format
            TransferInterbankRequest request = new TransferInterbankRequest(
                    "20250609103003235",  // partnerReferenceNo
                    amount,                     // amount
                    "GolangTestAjoji Ajojo",           // beneficiaryAccountName
                    "60004400184",          // beneficiaryAccountNo
                    "008",                      // beneficiaryBankCode
                    "9920017573"          // sourceAccountNo
            );

            // Add additional info
            request.setBeneficiaryEmail("andremaesha@gmail.com");
            request.setTransactionDescription("snapmandiri20250609103003");
            request.setCallbackUrl("http://account-service/account/api/mail/sendtotele");

            // Perform an interbank transfer
            TransferInterbankResponse response = client.transferInterbank().transfer(request);

            // Process the response
            if (response.isSuccess()) {
                System.out.println("Interbank transfer successful!");
                System.out.println("Response code: " + response.getResponseCode());
                System.out.println("Response message: " + response.getResponseMessage());
                System.out.println("Reference number: " + response.getReferenceNumber());
                System.out.println("Partner reference number: " + response.getPartnerReferenceNumber());
                System.out.println("Amount: " + response.getAmount().getValue() + " " + response.getAmount().getCurrency());
                System.out.println("Beneficiary account number: " + response.getBeneficiaryAccountNumber());
                System.out.println("Beneficiary bank code: " + response.getBeneficiaryBankCode());
                System.out.println("Source account number: " + response.getSourceAccountNumber());
                System.out.println("Beneficiary account name: " + response.getBeneficiaryAccountName());
                System.out.println("Beneficiary bank name: " + response.getBeneficiaryBankName());
                System.out.println("Instruct date: " + response.getInstructDate());
                System.out.println("Transaction description: " + response.getTransactionDescription());
                System.out.println("Callback URL: " + response.getCallbackUrl());
                System.out.println("Latest transaction status: " + response.getLatestTransactionStatus());
                System.out.println("Transaction status description: " + response.getTransactionStatusDesc());
            } else {
                System.out.println("Interbank transfer failed!");
                System.out.println("Response code: " + response.getResponseCode());
                System.out.println("Response message: " + response.getResponseMessage());
            }

        } catch (FaspaySnapApiException e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}