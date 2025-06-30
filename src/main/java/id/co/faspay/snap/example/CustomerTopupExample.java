package id.co.faspay.snap.example;

import id.co.faspay.snap.FaspaySnapClient;
import id.co.faspay.snap.config.FaspaySnapConfig;
import id.co.faspay.snap.exception.FaspaySnapApiException;
import id.co.faspay.snap.model.Amount;
import id.co.faspay.snap.model.CustomerTopupRequest;
import id.co.faspay.snap.model.CustomerTopupResponse;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;

public class CustomerTopupExample {
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

            CustomerTopupRequest request = getCustomerTopupRequest();

            CustomerTopupResponse response = client.customerTopup().topup(request);

            if (response.isSuccess()) {
                System.out.println(response.getResponseCode());
                System.out.println(response.getResponseMessage());
                System.out.println(response.getAdditionalInfo().getLatestTransactionStatus());
            }
        } catch (FaspaySnapApiException e) {
            e.printStackTrace();
        }
    }

    @NotNull
    private static CustomerTopupRequest getCustomerTopupRequest() {
        Amount amount = new Amount();
        amount.setCurrency("IDR");
        amount.setValue("90107.00");

        CustomerTopupRequest request = new CustomerTopupRequest();
        CustomerTopupRequest.AdditionalInfo additionalInfoRequest = new CustomerTopupRequest.AdditionalInfo();

        request.setPartnerReferenceNo("20250624103326251");
        request.setCustomerNumber("0812254830");
        request.setAmount(amount);
        request.setTransactionDate("2025-06-24T10:33:26+07:00");

        // Set values ONLY in additionalInfo, not in request level
        additionalInfoRequest.setSourceAccount("9920017573");
        additionalInfoRequest.setPlatformCode("gpy");
        additionalInfoRequest.setInstructDate("");
        additionalInfoRequest.setBeneficiaryEmail("aanfaspay2022@gmail.com,aan28setiawan@gmail.com");
        additionalInfoRequest.setTransactionDescription("Tunjangan Pulsa 20250624");
        additionalInfoRequest.setCallbackUrl("https://bdc7abc9-6b66-4d69-8a6c-1445f36ccce9-00-2ey1487bu215u.pike.replit.dev/v1/snap/callback");

        request.setAdditionalInfo(additionalInfoRequest);
        return request;
    }
}