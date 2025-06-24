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

public class BillPaymentExample {
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

            BillPaymentRequest request = getBillPaymentRequest();

            BillPaymentResponse response = client.billPayment().payment(request);

            if (response.isSuccess()) {
                System.out.println("responseCode: " + response.getResponseCode());
                System.out.println("responseMessage: " + response.getResponseMessage());
            }
        } catch (FaspaySnapApiException e) {
            e.printStackTrace();
        }
    }

    @NotNull
    private static BillPaymentRequest getBillPaymentRequest() {
        BillPaymentRequest request = new BillPaymentRequest();
        BillPaymentRequest.PaidAmount paidAmount = new BillPaymentRequest.PaidAmount();
        BillPaymentRequest.AdditionalInfo additionalInfo = new BillPaymentRequest.AdditionalInfo();

        request.setPartnerReferenceNo("20250624151326396");
        request.setPartnerServiceId("    7008");
        request.setCustomerNo("08000047816");
        request.setVirtualAccountNo("700808000047816");
        request.setVirtualAccountName("DUMMY VA");
        request.setSourceAccount("9920017573");

        paidAmount.setValue("118680.00");
        paidAmount.setCurrency("IDR");
        request.setPaidAmount(paidAmount);
        request.setTrxDateTime("2025-06-24T15:13:26");

        additionalInfo.setBillerCode("013");
        additionalInfo.setInstructDate("2025-06-24T15:13:26+07:00");
        additionalInfo.setCallbackUrl("https://bdc7abc9-6b66-4d69-8a6c-1445f36ccce9-00-2ey1487bu215u.pike.replit.dev/v1/snap/callback");
        request.setAdditionalInfo(additionalInfo);
        return request;
    }
}
