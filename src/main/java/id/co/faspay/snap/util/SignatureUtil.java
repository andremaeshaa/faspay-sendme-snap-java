package id.co.faspay.snap.util;

import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.security.MessageDigest;
import java.util.regex.Pattern;

/**
 * Utility class for generating signatures for Faspay SendMe Snap API requests.
 * Supports both HMAC-SHA256 and RSA-SHA256 signatures.
 */
public class SignatureUtil {
    private static final Logger logger = LoggerFactory.getLogger(SignatureUtil.class);
    private static final String HMAC_SHA256 = "HmacSHA256";
    private static final String RSA_SHA256 = "SHA256withRSA";
    private static final String SHA256 = "SHA-256";
    private static final String RSA = "RSA";
    
    // Pattern to match whitespace for JSON minification
    private static final Pattern WHITESPACE_PATTERN = Pattern.compile("\\s+");

    public static String cleanPrivateKey(String privateKeyStr) {
        return privateKeyStr
                .replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "")
                .replace("-----BEGIN RSA PRIVATE KEY-----", "")
                .replace("-----END RSA PRIVATE KEY-----", "")
                .replaceAll("\\s+", ""); // Remove all whitespace including newlines
    }
    
    /**
     * Generates a signature for the given payload using the provided private key.
     * The signature is generated using HMAC-SHA256.
     *
     * @param payload The payload to sign
     * @param privateKey The private key to use for signing
     * @return The generated signature
     * @throws IllegalStateException If an error occurs while generating the signature
     */
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
    
    /**
     * Generates an RSA signature using SHA256withRSA algorithm.
     *
     * @param stringToSign The string to be signed
     * @param privateKeyBase64 The RSA private key in Base64 format (PKCS#8)
     * @return The generated signature in Base64 format
     * @throws IllegalStateException If an error occurs while generating the signature
     */
    public static String generateRSASignature(String stringToSign, String privateKeyBase64) {
        try {
            // Decode the private key
            byte[] privateKeyBytes = Base64.getDecoder().decode(privateKeyBase64);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(RSA);
            PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
            
            // Create signature
            Signature signature = Signature.getInstance(RSA_SHA256);
            signature.initSign(privateKey);
            signature.update(stringToSign.getBytes(StandardCharsets.UTF_8));
            
            byte[] signatureBytes = signature.sign();
            return Base64.getEncoder().encodeToString(signatureBytes);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | InvalidKeyException | SignatureException e) {
            logger.error("Error generating RSA signature: {}", e.getMessage());
            throw new IllegalStateException("Error generating RSA signature: " + e.getMessage(), e);
        }
    }
    
    /**
     * Creates a string to sign according to the formula:
     * HTTPMethod + ":" + EndpointUrl + ":" + Lowercase(HexEncode(SHA256(minify(RequestBody)))) + ":" + TimeStamp
     *
     * @param httpMethod The HTTP method (GET, POST, etc.)
     * @param endpointUrl The endpoint URL
     * @param requestBody The request body (JSON string)
     * @param timestamp The timestamp
     * @return The string to sign
     */
    public static String createStringToSign(String httpMethod, String endpointUrl, String requestBody, String timestamp) {
        try {
            // Minify the request body (remove unnecessary whitespace)
            String minifiedBody = minifyJson(requestBody);
            
            // Calculate SHA256 hash of minified body
            MessageDigest digest = MessageDigest.getInstance(SHA256);
            byte[] hashBytes = digest.digest(minifiedBody.getBytes(StandardCharsets.UTF_8));
            
            // Convert to hex and lowercase
            String hexHash = Hex.encodeHexString(hashBytes).toLowerCase();
            
            // Build the string to sign
            return httpMethod + ":" + endpointUrl + ":" + hexHash + ":" + timestamp;
        } catch (NoSuchAlgorithmException e) {
            logger.error("Error creating string to sign: {}", e.getMessage());
            throw new IllegalStateException("Error creating string to sign: " + e.getMessage(), e);
        }
    }
    
    /**
     * Minifies JSON by removing unnecessary whitespace.
     * This is a simple implementation that removes spaces, tabs, and newlines between JSON elements.
     *
     * @param json The JSON string to minify
     * @return The minified JSON string
     */
    private static String minifyJson(String json) {
        if (json == null || json.trim().isEmpty()) {
            return json;
        }
        
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
            } else if (inString) {
                result.append(c);
            } else if (!Character.isWhitespace(c)) {
                result.append(c);
            }
        }
        
        return result.toString();
    }
    
    /**
     * Complete method that creates string to sign and generates RSA signature.
     *
     * @param httpMethod The HTTP method
     * @param endpointUrl The endpoint URL
     * @param requestBody The request body
     * @param timestamp The timestamp
     * @param privateKeyBase64 The RSA private key in Base64 format
     * @return The RSA signature in Base64 format
     */
    public static String generateCompleteRSASignature(String httpMethod, String endpointUrl, 
                                                     String requestBody, String timestamp, 
                                                     String privateKeyBase64) {
        String stringToSign = createStringToSign(httpMethod, endpointUrl, requestBody, timestamp);
        logger.debug("String to sign: {}", stringToSign);
        return generateRSASignature(stringToSign, privateKeyBase64);
    }
    
    /**
     * Verifies that the provided signature matches the expected signature for the given payload and private key.
     *
     * @param payload The payload that was signed
     * @param signature The signature to verify
     * @param privateKey The private key used for signing
     * @return True if the signature is valid, false otherwise
     */
    public static boolean verifySignature(String payload, String signature, String privateKey) {
        String expectedSignature = generateSignature(payload, privateKey);
        return expectedSignature.equals(signature);
    }
}