package id.co.faspay.snap.example;

import id.co.faspay.snap.FaspaySnapClient;
import id.co.faspay.snap.config.FaspaySnapConfig;
import id.co.faspay.snap.exception.FaspaySnapApiException;
import id.co.faspay.snap.model.StatusTransferRequest;
import id.co.faspay.snap.model.StatusTransferResponse;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;

public class TransferStatusExample {
    public static void main(String[] args) throws IOException {
        URL resourceSsl = AccountInquiryExample.class.getResource("/faspay.crt");
        URL privateKeyResource = AccountInquiryExample.class.getResource("/enc_stg_ori.key");

        assert privateKeyResource != null;
        String privateKeyStr = Files.readString(new File(privateKeyResource.getFile()).toPath());

        assert resourceSsl != null;
        String sslString = Files.readString(new File(resourceSsl.getFile()).toPath());

        String partnerId = "99999";

        try {
            FaspaySnapConfig config = new FaspaySnapConfig(partnerId, privateKeyStr, sslString);
            config.setEnv("sandbox");
            FaspaySnapClient client = new FaspaySnapClient(config);

            StatusTransferRequest request = new StatusTransferRequest("20250623101414812", "150120", "18");

            StatusTransferResponse response = client.transferStatus().status(request);

            if (response.isSuccess()) {
                System.out.println("response code: " + response.getResponseCode());
                System.out.println("Transfer status: " + response.getLatestTransactionStatus());
                System.out.println(response.getCallbackUrl());
            }
        } catch (FaspaySnapApiException e) {
            e.printStackTrace();
        }
    }
}
