package id.co.faspay.snap.client;

import id.co.faspay.snap.config.FaspaySnapConfig;
import id.co.faspay.snap.example.AccountInquiryExample;
import id.co.faspay.snap.exception.FaspaySnapApiException;
import id.co.faspay.snap.model.Amount;
import id.co.faspay.snap.model.TransferInterbankRequest;
import id.co.faspay.snap.model.TransferInterbankResponse;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the TransferInterbankClient class.
 */
public class TransferInterbankClientTest {

    private MockWebServer mockWebServer;
    private TransferInterbankClient client;
    private FaspaySnapConfig config;

    @BeforeEach
    public void setup() throws IOException {
        // Start the mock web server
        mockWebServer = new MockWebServer();
        mockWebServer.start();

        URL resourceSsl = AccountInquiryExample.class.getResource("/faspay.crt");
        URL privateKeyResource = AccountInquiryExample.class.getResource("/enc_stg.key");

        assert privateKeyResource != null;
        String privateKeyStr = Files.readString(new File(privateKeyResource.getFile()).toPath());

        assert resourceSsl != null;
        String sslString = Files.readString(new File(resourceSsl.getFile()).toPath());

        // Create a configuration that points to the mock server
        String baseUrl = mockWebServer.url("").toString();
        config = new FaspaySnapConfig("test-partner-id", privateKeyStr, sslString);

        // Create the client
        client = new TransferInterbankClient(config);
    }

    @AfterEach
    public void tearDown() throws IOException {
        // Shut down the mock web server
        mockWebServer.shutdown();
    }

    @Test
    public void testTransferSuccess() throws Exception {
        // Prepare the mock response
        String responseJson = "{"
                + "\"responseCode\": \"2001800\","
                + "\"responseMessage\": \"Success\","
                + "\"referenceNo\": \"FP-123456\","
                + "\"partnerReferenceNo\": \"REF-123456\","
                + "\"amount\": {"
                + "  \"value\": \"100000.00\","
                + "  \"currency\": \"IDR\""
                + "},"
                + "\"beneficiaryAccountNo\": \"1234567890\","
                + "\"beneficiaryBankCode\": \"008\","
                + "\"sourceAccountNo\": \"9876543210\","
                + "\"additionalInfo\": {"
                + "  \"beneficiaryAccountName\": \"John Doe\","
                + "  \"beneficiaryBankName\": \"BANK MANDIRI\","
                + "  \"instructDate\": \"2023-01-01T12:00:00+07:00\","
                + "  \"transactionDescription\": \"Payment for services\","
                + "  \"callbackUrl\": \"https://example.com/callback\","
                + "  \"latestTransactionStatus\": \"COMPLETED\","
                + "  \"transactionStatusDesc\": \"Transaction completed successfully\""
                + "}"
                + "}";

        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(responseJson));

        // Create the request
        Amount amount = new Amount("100000.00", "IDR");
        TransferInterbankRequest request = new TransferInterbankRequest(
                "REF-123456",
                amount,
                "John Doe",
                "1234567890",
                "008",
                "9876543210"
        );
        request.setTransactionDescription("Payment for services");
        request.setCallbackUrl("https://example.com/callback");

        // Make the request
        TransferInterbankResponse response = client.transfer(request);

        // Verify the request
        RecordedRequest recordedRequest = mockWebServer.takeRequest();
        assertEquals("POST", recordedRequest.getMethod());
        assertEquals("/disbursement/transfer-interbank", recordedRequest.getPath());
        assertTrue(recordedRequest.getBody().readUtf8().contains("\"partnerReferenceNo\":\"REF-123456\""));
        assertTrue(recordedRequest.getBody().readUtf8().contains("\"beneficiaryAccountNo\":\"1234567890\""));
        assertTrue(recordedRequest.getBody().readUtf8().contains("\"beneficiaryBankCode\":\"008\""));

        // Verify the response
        assertTrue(response.isSuccess());
        assertEquals("2001800", response.getResponseCode());
        assertEquals("Success", response.getResponseMessage());
        assertEquals("FP-123456", response.getReferenceNumber());
        assertEquals("REF-123456", response.getPartnerReferenceNumber());
        assertEquals("100000.00", response.getAmount().getValue());
        assertEquals("IDR", response.getAmount().getCurrency());
        assertEquals("1234567890", response.getBeneficiaryAccountNumber());
        assertEquals("008", response.getBeneficiaryBankCode());
        assertEquals("9876543210", response.getSourceAccountNumber());
        assertEquals("John Doe", response.getBeneficiaryAccountName());
        assertEquals("BANK MANDIRI", response.getBeneficiaryBankName());
        assertEquals("2023-01-01T12:00:00+07:00", response.getInstructDate());
        assertEquals("Payment for services", response.getTransactionDescription());
        assertEquals("https://example.com/callback", response.getCallbackUrl());
        assertEquals("COMPLETED", response.getLatestTransactionStatus());
        assertEquals("Transaction completed successfully", response.getTransactionStatusDesc());
    }

    @Test
    public void testTransferFailure() throws Exception {
        // Prepare the mock response
        String responseJson = "{"
                + "\"responseCode\": \"2001801\","
                + "\"responseMessage\": \"Insufficient funds\","
                + "\"referenceNo\": \"FP-123456\","
                + "\"partnerReferenceNo\": \"REF-123456\","
                + "\"amount\": {"
                + "  \"value\": \"100000.00\","
                + "  \"currency\": \"IDR\""
                + "},"
                + "\"beneficiaryAccountNo\": \"1234567890\","
                + "\"beneficiaryBankCode\": \"008\","
                + "\"sourceAccountNo\": \"9876543210\","
                + "\"additionalInfo\": {"
                + "  \"beneficiaryAccountName\": \"John Doe\","
                + "  \"beneficiaryBankName\": \"BANK MANDIRI\","
                + "  \"latestTransactionStatus\": \"FAILED\","
                + "  \"transactionStatusDesc\": \"Insufficient funds in source account\""
                + "}"
                + "}";

        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(responseJson));

        // Create the request
        Amount amount = new Amount("100000.00", "IDR");
        TransferInterbankRequest request = new TransferInterbankRequest(
                "REF-123456",
                amount,
                "John Doe",
                "1234567890",
                "008",
                "9876543210"
        );

        // Make the request
        TransferInterbankResponse response = client.transfer(request);

        // Verify the response
        assertFalse(response.isSuccess());
        assertEquals("2001801", response.getResponseCode());
        assertEquals("Insufficient funds", response.getResponseMessage());
        assertEquals("FAILED", response.getLatestTransactionStatus());
        assertEquals("Insufficient funds in source account", response.getTransactionStatusDesc());
    }

    @Test
    public void testTransferApiError() {
        // Prepare the mock response
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(500)
                .setHeader("Content-Type", "application/json")
                .setBody("{\"error\": \"Internal server error\"}"));

        // Create the request
        Amount amount = new Amount("100000.00", "IDR");
        TransferInterbankRequest request = new TransferInterbankRequest(
                "REF-123456",
                amount,
                "John Doe",
                "1234567890",
                "008",
                "9876543210"
        );

        // Make the request and verify it throws an exception
        assertThrows(FaspaySnapApiException.class, () -> {
            client.transfer(request);
        });
    }

    @Test
    public void testTransferWithIndividualParameters() throws Exception {
        // Prepare the mock response
        String responseJson = "{"
                + "\"responseCode\": \"2001800\","
                + "\"responseMessage\": \"Success\","
                + "\"referenceNo\": \"FP-123456\","
                + "\"partnerReferenceNo\": \"REF-123456\","
                + "\"amount\": {"
                + "  \"value\": \"100000.00\","
                + "  \"currency\": \"IDR\""
                + "},"
                + "\"beneficiaryAccountNo\": \"1234567890\","
                + "\"beneficiaryBankCode\": \"008\","
                + "\"sourceAccountNo\": \"9876543210\","
                + "\"additionalInfo\": {"
                + "  \"beneficiaryAccountName\": \"John Doe\","
                + "  \"beneficiaryBankName\": \"BANK MANDIRI\","
                + "  \"latestTransactionStatus\": \"COMPLETED\""
                + "}"
                + "}";

        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(responseJson));

        // Make the request with individual parameters
        Amount amount = new Amount("100000.00", "IDR");
        TransferInterbankResponse response = client.transfer(
                "REF-123456",
                amount,
                "John Doe",
                "1234567890",
                "008",
                "9876543210",
                "2023-01-01T12:00:00+07:00"
        );

        // Verify the response
        assertTrue(response.isSuccess());
        assertEquals("2001800", response.getResponseCode());
        assertEquals("Success", response.getResponseMessage());
    }
}