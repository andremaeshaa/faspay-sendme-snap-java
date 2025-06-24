package id.co.faspay.snap.example;

import id.co.faspay.snap.FaspaySnapClient;
import id.co.faspay.snap.config.FaspaySnapConfig;
import id.co.faspay.snap.exception.FaspaySnapApiException;
import id.co.faspay.snap.model.CustomerTopupStatusRequest;
import id.co.faspay.snap.model.CustomerTopupStatusResponse;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;

public class CustomerTopupStatusExample {
    public static void main(String[] args) throws IOException {
        URL resourceSsl = AccountInquiryExample.class.getResource("/faspay.crt");
        URL privateKeyResource = AccountInquiryExample.class.getResource("/enc_stg.key");

        assert privateKeyResource != null;
        String privateKeyStr = Files.readString(new File(privateKeyResource.getFile()).toPath());

        assert resourceSsl != null;
        String sslString = Files.readString(new File(resourceSsl.getFile()).toPath());

        String partnerId = "99999";

        try {
            FaspaySnapConfig config = new FaspaySnapConfig(partnerId, privateKeyStr, sslString);
            config.setEnv("sandbox");
            FaspaySnapClient client = new FaspaySnapClient(config);

            CustomerTopupStatusRequest request = new CustomerTopupStatusRequest("20250624103326251", "150207", "38");

            CustomerTopupStatusResponse response = client.customerTopupStatus().status(request);

            if (response.isSuccess()) {
                System.out.println("responseCode: " + response.getResponseCode());
                System.out.println("Latest Transaction Status: " + response.getLatestTransactionStatus());
            }
        } catch (FaspaySnapApiException e) {
            e.printStackTrace();
        }
    }
}
