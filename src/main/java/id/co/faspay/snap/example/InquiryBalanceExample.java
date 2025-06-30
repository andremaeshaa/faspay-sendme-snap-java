package id.co.faspay.snap.example;

import id.co.faspay.snap.FaspaySnapClient;
import id.co.faspay.snap.config.FaspaySnapConfig;
import id.co.faspay.snap.exception.FaspaySnapApiException;
import id.co.faspay.snap.model.InquiryBalanceRequest;
import id.co.faspay.snap.model.InquiryBalanceResponse;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;

public class InquiryBalanceExample {
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

            InquiryBalanceRequest request = new InquiryBalanceRequest("9920017573");
            InquiryBalanceResponse response = client.inquiryBalance().balance(request);

            System.out.println("response code: " + response.getResponseCode());
        } catch (FaspaySnapApiException e) {
            e.printStackTrace();
        }
    }
}
