package id.co.faspay.snap.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import id.co.faspay.snap.config.FaspaySnapConfig;
import id.co.faspay.snap.exception.FaspaySnapApiException;
import id.co.faspay.snap.model.Constants;
import id.co.faspay.snap.util.SignatureUtil;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * HTTP client for making requests to the Faspay SendMe Snap API.
 * This class handles the low-level HTTP communication with the API.
 */
public class FaspaySnapHttpClient {
    private static final Logger logger = LoggerFactory.getLogger(FaspaySnapHttpClient.class);
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    
    private final FaspaySnapConfig config;
    private final OkHttpClient httpClient;
    private final ObjectMapper objectMapper;
    
    /**
     * Creates a new HTTP client with the provided configuration.
     *
     * @param config The configuration for the Faspay SendMe Snap API
     */
    public FaspaySnapHttpClient(FaspaySnapConfig config) {
        this.config = Objects.requireNonNull(config, "config must not be null");
        
        // Configure OkHttpClient with SSL context from config
        this.httpClient = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .sslSocketFactory(config.getSslContext().getSocketFactory(), config.getTrustManager())
                .build();
        
        // Configure ObjectMapper for JSON serialization/deserialization
        this.objectMapper = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }
    
    /**
     * Makes a POST request to the specified endpoint with the given request body.
     *
     * @param endpoint The API endpoint to call
     * @param requestBody The request body to send
     * @param responseType The class of the expected response
     * @param <T> The type of the expected response
     * @return The response from the API
     * @throws FaspaySnapApiException If an error occurs while making the request
     */
    public <T> T post(String endpoint, String userAgent, Object requestBody, Class<T> responseType) throws FaspaySnapApiException {
        try {
            String url = config.getBaseUrl() + endpoint;
//            String requestJson = objectMapper.writeValueAsString(requestBody);
            String requestJson = requestBody.toString();

            String privateKey = SignatureUtil.cleanPrivateKey(config.getPrivateKey());
            String stringToSign = SignatureUtil.createStringToSign("POST", endpoint, requestJson, config.getTimestamp());
            String signature = SignatureUtil.generateRSASignature(stringToSign, privateKey);
            
            // Build the request
            Request request = new Request.Builder()
                    .url(url)
                    .post(RequestBody.create(requestJson, JSON))
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json")
                    .header("User-Agent", userAgent)
                    .header("X-TIMESTAMP", config.getTimestamp())
                    .header("X-Signature", signature)
                    .header("X-Partner-Id", config.getPartnerId())
                    .header("X-EXTERNAL-ID", config.getExternalId())
                    .header("CHANNEL-ID", "88001")
                    .build();
            
            logger.debug("Making POST request to {}: {}", url, requestJson);
            
            // Execute the request
            try (Response response = httpClient.newCall(request).execute()) {
                return handleResponse(response, responseType);
            }
        } catch (IOException e) {
            logger.error("Error making POST request to {}: {}", endpoint, e.getMessage());
            throw new FaspaySnapApiException("Error making POST request: " + e.getMessage(), e);
        }
    }
    
    /**
     * Handles the HTTP response and converts it to the expected response type.
     *
     * @param response The HTTP response
     * @param responseType The class of the expected response
     * @param <T> The type of the expected response
     * @return The response from the API
     * @throws IOException If an error occurs while reading the response
     * @throws FaspaySnapApiException If the API returns an error
     */
    private <T> T handleResponse(Response response, Class<T> responseType) throws IOException, FaspaySnapApiException {
        String responseBody = response.body() != null ? response.body().string() : "";
        
        logger.debug("Received response with status code {}: {}", response.code(), responseBody);
        
        if (!response.isSuccessful()) {
            throw new FaspaySnapApiException("API request failed with status code " + response.code() + ": " + responseBody);
        }
        
        try {
            return objectMapper.readValue(responseBody, responseType);
        } catch (IOException e) {
            logger.error("Error parsing response: {}", e.getMessage());
            throw new FaspaySnapApiException("Error parsing response: " + e.getMessage(), e);
        }
    }
    
    /**
     * Gets the ObjectMapper used by this client.
     *
     * @return The ObjectMapper
     */
    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }
}