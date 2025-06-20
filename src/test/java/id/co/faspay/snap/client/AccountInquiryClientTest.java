package id.co.faspay.snap.client;

import id.co.faspay.snap.config.FaspaySnapConfig;
import id.co.faspay.snap.exception.FaspaySnapApiException;
import id.co.faspay.snap.model.AccountInquiryRequest;
import id.co.faspay.snap.model.AccountInquiryResponse;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the AccountInquiryClient class.
 */
public class AccountInquiryClientTest {

    private MockWebServer mockWebServer;
    private AccountInquiryClient client;
    private FaspaySnapConfig config;

    @BeforeEach
    public void setup() throws IOException {
        // Start the mock web server
        mockWebServer = new MockWebServer();
        mockWebServer.start();

        // Create a configuration that points to the mock server
        String baseUrl = mockWebServer.url("").toString();
        config = new FaspaySnapConfig("test-partner-id", "test-private-key", "src/test/resources/test-cert.pem");

        // Create the client
        client = new AccountInquiryClient(config);
    }

    @AfterEach
    public void tearDown() throws IOException {
        // Shut down the mock web server
        mockWebServer.shutdown();
    }

    @Test
    public void testInquireSuccess() throws Exception {
        // Prepare the mock response
        String responseJson = "{"
                + "\"status\": \"success\","
                + "\"responseCode\": \"00\","
                + "\"responseMessage\": \"Success\","
                + "\"beneficiaryBankCode\": \"BCA\","
                + "\"beneficiaryAccountNo\": \"1234567890\","
                + "\"beneficiaryAccountName\": \"John Doe\","
                + "\"partnerReferenceNo\": \"REF-123456\","
                + "\"referenceNo\": \"FP-123456\","
                + "\"beneficiaryBankName\": \"BANK BCA\","
                + "\"currency\": \"IDR\","
                + "\"additionalInfo\": {\"status\": \"1\", \"message\": \"Success\"}"
                + "}";

        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(responseJson));

        // Make the request
        AccountInquiryResponse response = client.inquire("BCA", "1234567890", "REF-123456");

        // Verify the request
        RecordedRequest recordedRequest = mockWebServer.takeRequest();
        assertEquals("POST", recordedRequest.getMethod());
        assertEquals("/disbursement/account-inquiry", recordedRequest.getPath());
        assertTrue(recordedRequest.getBody().readUtf8().contains("\"beneficiaryBankCode\":\"BCA\""));
        assertTrue(recordedRequest.getBody().readUtf8().contains("\"beneficiaryAccountNo\":\"1234567890\""));
        assertTrue(recordedRequest.getBody().readUtf8().contains("\"partnerReferenceNo\":\"REF-123456\""));

        // Verify the response
        assertTrue(response.isSuccess());
        assertEquals("00", response.getResponseCode());
        assertEquals("Success", response.getResponseMessage());
        assertEquals("BCA", response.getBankCode());
        assertEquals("1234567890", response.getAccountNumber());
        assertEquals("John Doe", response.getAccountHolderName());
        assertEquals("REF-123456", response.getPartnerReferenceNumber());
        assertEquals("FP-123456", response.getReferenceNo());
        assertEquals("BANK BCA", response.getBankName());
        assertEquals("IDR", response.getCurrency());
        assertNotNull(response.getAdditionalInfo());
        assertEquals("1", response.getAdditionalInfo().get("status"));
        assertEquals("Success", response.getAdditionalInfo().get("message"));
    }

    @Test
    public void testInquireFailure() throws Exception {
        // Prepare the mock response
        String responseJson = "{"
                + "\"status\": \"failed\","
                + "\"responseCode\": \"01\","
                + "\"responseMessage\": \"Account not found\","
                + "\"beneficiaryBankCode\": \"BCA\","
                + "\"beneficiaryAccountNo\": \"1234567890\","
                + "\"beneficiaryAccountName\": \"\","
                + "\"partnerReferenceNo\": \"REF-123456\","
                + "\"referenceNo\": \"FP-123456\","
                + "\"beneficiaryBankName\": \"\","
                + "\"currency\": \"\","
                + "\"additionalInfo\": {\"status\": \"0\", \"message\": \"Failed\"}"
                + "}";

        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(responseJson));

        // Make the request
        AccountInquiryResponse response = client.inquire("BCA", "1234567890", "REF-123456");

        // Verify the response
        assertFalse(response.isSuccess());
        assertEquals("01", response.getResponseCode());
        assertEquals("Account not found", response.getResponseMessage());
    }

    @Test
    public void testInquireApiError() {
        // Prepare the mock response
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(500)
                .setHeader("Content-Type", "application/json")
                .setBody("{\"error\": \"Internal server error\"}"));

        // Make the request and verify it throws an exception
        assertThrows(FaspaySnapApiException.class, () -> {
            client.inquire("BCA", "1234567890", "REF-123456");
        });
    }

    @Test
    public void testInquireWithRequest() throws Exception {
        // Prepare the mock response
        String responseJson = "{"
                + "\"status\": \"success\","
                + "\"responseCode\": \"00\","
                + "\"responseMessage\": \"Success\","
                + "\"beneficiaryBankCode\": \"BCA\","
                + "\"beneficiaryAccountNo\": \"1234567890\","
                + "\"beneficiaryAccountName\": \"John Doe\","
                + "\"partnerReferenceNo\": \"REF-123456\","
                + "\"referenceNo\": \"FP-123456\","
                + "\"beneficiaryBankName\": \"BANK BCA\","
                + "\"currency\": \"IDR\","
                + "\"additionalInfo\": {\"status\": \"1\", \"message\": \"Success\"}"
                + "}";

        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(responseJson));

        // Create the request object
        AccountInquiryRequest request = new AccountInquiryRequest("BCA", "1234567890", "REF-123456");
        request.setSourceAccount("9920000082");

        // Make the request
        AccountInquiryResponse response = client.inquire(request);

        // Verify the response
        assertTrue(response.isSuccess());
        assertEquals("00", response.getResponseCode());
        assertEquals("Success", response.getResponseMessage());
        assertEquals("BCA", response.getBankCode());
        assertEquals("1234567890", response.getAccountNumber());
        assertEquals("John Doe", response.getAccountHolderName());
        assertEquals("REF-123456", response.getPartnerReferenceNumber());
        assertEquals("FP-123456", response.getReferenceNo());
        assertEquals("BANK BCA", response.getBankName());
        assertEquals("IDR", response.getCurrency());
        assertNotNull(response.getAdditionalInfo());
        assertEquals("1", response.getAdditionalInfo().get("status"));
        assertEquals("Success", response.getAdditionalInfo().get("message"));
    }
}
