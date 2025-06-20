package id.co.faspay.snap.model;

public class Constants {
    public static final String baseUrlSandbox = "https://account-staging.faspay.co.id";
    public static final String baseUrlProd = "https://sendme.faspay.co.id";

    private final String endpointTransferInterbank = "/account/v1.0/transfer-interbank";
    private final String endpointInquiryStatus = "/account/v1.0/transfer/status";

    public String getEndpointAccountInquiry() {
        return "/account/v1.0/account-inquiry-external";
    }

    public String userAgent() {
        String versionSDK = "1.0.0";
        return "FaspaySendMeSnapGo/" + versionSDK;
    }
}
