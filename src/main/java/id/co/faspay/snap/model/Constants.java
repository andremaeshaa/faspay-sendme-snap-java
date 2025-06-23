package id.co.faspay.snap.model;

public class Constants {
    public static final String baseUrlSandbox = "https://account-staging.faspay.co.id";
    public static final String baseUrlProd = "https://sendme.faspay.co.id";

    public String getEndpointAccountInquiry() {
        return "/account/v1.0/account-inquiry-external";
    }

    public String getEndpointTransferInterbank() {
        return "/account/v1.0/transfer-interbank";
    }

    public String getEndpointTransferInquiryStatus() {
        return "/account/v1.0/transfer/status";
    }

    public String getEndpointInquiryBalance() {
        return "/account/v1.0/balance-inquiry";
    }

    public String getUserAgent() {
        String versionSDK = "1.0.0";
        return "FaspaySendMeSnapJava/" + versionSDK;
    }
}
