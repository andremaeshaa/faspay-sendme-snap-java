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

public class BillInquiryExample {
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

            BillInquiryRequest.AdditionalInfo additionalInfo = new BillInquiryRequest.AdditionalInfo();
            additionalInfo.setBillerCode("013");
            additionalInfo.setSourceAccount("9920017573");

            BillInquiryRequest request = new BillInquiryRequest("20250624151221271", "    7008", "08000047816", "700808000047816", additionalInfo);

            BillInquiryResponse response = client.billInquiry().inquiry(request);

            if (response.isSuccess()) {
                System.out.println("responseCode: " + response.getResponseCode());
                System.out.println("responseMessage: " + response.getResponseMessage());
            }
        } catch (FaspaySnapApiException e) {
            e.printStackTrace();
        }
    }
}
