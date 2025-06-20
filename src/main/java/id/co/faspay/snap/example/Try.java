package id.co.faspay.snap.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import id.co.faspay.snap.model.AccountInquiryRequest;
import id.co.faspay.snap.util.SignatureUtil;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.Base64;

public class Try {
    public static void main(String[] args) {
        AccountInquiryRequest request = new AccountInquiryRequest(
                "002",                  // beneficiaryBankCode
                "888801000157508",      // beneficiaryAccountNo
                "2020102900000000000001"  // partnerReferenceNo
        );

        // Add additional info
        request.setSourceAccount("9920000082");

        String string = request.toString();

        System.out.println(string);
    }

//    public static void main(String[] args) throws IOException {
//        URL privateKeyResource = Try.class.getResource("/enc_stg.key");
//        assert privateKeyResource != null;
//        String privateKeyStr = Files.readString(new File(privateKeyResource.getFile()).toPath());
//
//        // Clean up the private key string (remove headers, footers, and newlines)
//        String cleanPrivateKey = cleanPrivateKey(privateKeyStr);
//
//        String httpMethod = "POST";
//        String endpointUrl = "/account/v1.0/account-inquiry-external";
//        String requestBody = "{\n  \"amount\": 10000,\n  \"currency\": \"IDR\",\n  \"description\": \"Payment\"\n}";
//        String timestamp = "1640995200"; // Unix timestamp
//
//        try {
//            // Debug: Print the cleaned private key (first 50 chars only for security)
//            System.out.println("Private key (first 50 chars): " + cleanPrivateKey.substring(0, Math.min(50, cleanPrivateKey.length())));
//
//            String stringToSign = SignatureUtil.createStringToSign(httpMethod, endpointUrl, requestBody, timestamp);
//            System.out.println("String to sign: " + stringToSign);
//
//            String signature = SignatureUtil.generateRSASignature(stringToSign, cleanPrivateKey);
//            System.out.println("Signature: " + signature);
//
//        } catch (Exception e) {
//            System.err.println("Error occurred: " + e.getMessage());
//            e.printStackTrace();
//
//            // Additional debugging
//            System.out.println("Private key length: " + cleanPrivateKey.length());
//            System.out.println("Is valid Base64: " + isValidBase64(cleanPrivateKey));
//        }
//    }
//
//
//    private static String cleanPrivateKey(String privateKeyStr) {
//        return privateKeyStr
//                .replace("-----BEGIN PRIVATE KEY-----", "")
//                .replace("-----END PRIVATE KEY-----", "")
//                .replace("-----BEGIN RSA PRIVATE KEY-----", "")
//                .replace("-----END RSA PRIVATE KEY-----", "")
//                .replaceAll("\\s+", ""); // Remove all whitespace including newlines
//    }
//
//    private static boolean isValidBase64(String str) {
//        try {
//            Base64.getDecoder().decode(str);
//            return true;
//        } catch (IllegalArgumentException e) {
//            return false;
//        }
//    }


}
