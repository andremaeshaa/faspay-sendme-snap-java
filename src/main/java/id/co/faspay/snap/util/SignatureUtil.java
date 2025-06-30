package id.co.faspay.snap.util;

import org.apache.commons.codec.binary.Hex;
import id.co.faspay.snap.logging.Logger;
import id.co.faspay.snap.logging.LoggerFactory;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.pkcs.RSAPrivateKey;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPrivateCrtKeySpec;
import java.util.Base64;
import java.util.regex.Pattern;

public class SignatureUtil {
    private static final Logger logger = LoggerFactory.getLogger(SignatureUtil.class);
    private static final String HMAC_SHA256 = "HmacSHA256";
    private static final String RSA_SHA256 = "SHA256withRSA";
    private static final String SHA256 = "SHA-256";
    private static final String RSA = "RSA";

    public static String generateSignature(String payload, String privateKey) {
        try {
            Mac hmacSha256 = Mac.getInstance(HMAC_SHA256);
            SecretKeySpec secretKey = new SecretKeySpec(privateKey.getBytes(StandardCharsets.UTF_8), HMAC_SHA256);
            hmacSha256.init(secretKey);

            byte[] hmacBytes = hmacSha256.doFinal(payload.getBytes(StandardCharsets.UTF_8));
            return Hex.encodeHexString(hmacBytes);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            logger.error("Error generating signature: {}", e.getMessage());
            throw new IllegalStateException("Error generating signature: " + e.getMessage(), e);
        }
    }

    public static String generateRSASignature(String stringToSign, String privateKeyPem) {
        try {
            PrivateKey privateKey = loadPrivateKey(privateKeyPem);
            Signature signature = Signature.getInstance(RSA_SHA256);
            signature.initSign(privateKey);
            signature.update(stringToSign.getBytes(StandardCharsets.UTF_8));

            byte[] signatureBytes = signature.sign();
            return Base64.getEncoder().encodeToString(signatureBytes);
        } catch (Exception e) {
            logger.error("Error generating RSA signature: {}", e.getMessage());
            throw new IllegalStateException("Error generating RSA signature: " + e.getMessage(), e);
        }
    }

    public static PrivateKey loadPrivateKey(String pem) throws Exception {
        pem = pem.replace("\r", "").trim();

        if (pem.contains("-----BEGIN RSA PRIVATE KEY-----")) {
            String base64 = pem.replace("-----BEGIN RSA PRIVATE KEY-----", "")
                    .replace("-----END RSA PRIVATE KEY-----", "")
                    .replaceAll("\\s+", "");
            byte[] keyBytes = Base64.getDecoder().decode(base64);
            RSAPrivateKey rsa = RSAPrivateKey.getInstance(ASN1Sequence.fromByteArray(keyBytes));
            RSAPrivateCrtKeySpec keySpec = new RSAPrivateCrtKeySpec(
                    rsa.getModulus(),
                    rsa.getPublicExponent(),
                    rsa.getPrivateExponent(),
                    rsa.getPrime1(),
                    rsa.getPrime2(),
                    rsa.getExponent1(),
                    rsa.getExponent2(),
                    rsa.getCoefficient());
            KeyFactory keyFactory = KeyFactory.getInstance(RSA);
            return keyFactory.generatePrivate(keySpec);
        } else if (pem.contains("-----BEGIN PRIVATE KEY-----")) {
            String base64 = pem.replace("-----BEGIN PRIVATE KEY-----", "")
                    .replace("-----END PRIVATE KEY-----", "")
                    .replaceAll("\\s+", "");
            byte[] keyBytes = Base64.getDecoder().decode(base64);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(RSA);
            return keyFactory.generatePrivate(keySpec);
        } else {
            throw new IllegalArgumentException("Unsupported key format or corrupted PEM");
        }
    }

    public static String createStringToSign(String httpMethod, String endpointUrl, String requestBody, String timestamp) {
        try {
            String minifiedBody = minifyJson(requestBody);
            MessageDigest digest = MessageDigest.getInstance(SHA256);
            byte[] hashBytes = digest.digest(minifiedBody.getBytes(StandardCharsets.UTF_8));
            String hexHash = Hex.encodeHexString(hashBytes).toLowerCase();
            return httpMethod + ":" + endpointUrl + ":" + hexHash + ":" + timestamp;
        } catch (NoSuchAlgorithmException e) {
            logger.error("Error creating string to sign: {}", e.getMessage());
            throw new IllegalStateException("Error creating string to sign: " + e.getMessage(), e);
        }
    }

    private static String minifyJson(String json) {
        if (json == null || json.trim().isEmpty()) return json;

        StringBuilder result = new StringBuilder();
        boolean inString = false;
        boolean escaped = false;

        for (char c : json.toCharArray()) {
            if (escaped) {
                result.append(c);
                escaped = false;
            } else if (c == '\\' && inString) {
                result.append(c);
                escaped = true;
            } else if (c == '"') {
                result.append(c);
                inString = !inString;
            } else if (inString || !Character.isWhitespace(c)) {
                result.append(c);
            }
        }
        return result.toString();
    }

    public static String generateCompleteRSASignature(String httpMethod, String endpointUrl, String requestBody, String timestamp, String privateKeyPem) {
        String stringToSign = createStringToSign(httpMethod, endpointUrl, requestBody, timestamp);
        logger.debug("String to sign: {}", stringToSign);
        return generateRSASignature(stringToSign, privateKeyPem);
    }

    public static boolean verifySignature(String payload, String signature, String privateKey) {
        String expectedSignature = generateSignature(payload, privateKey);
        return expectedSignature.equals(signature);
    }
}