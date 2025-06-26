package id.co.faspay.snap.client;

import id.co.faspay.snap.config.FaspaySnapConfig;
import id.co.faspay.snap.exception.FaspaySnapApiException;
import id.co.faspay.snap.model.Constants;
import id.co.faspay.snap.model.InquiryBalanceRequest;
import id.co.faspay.snap.model.InquiryBalanceResponse;
import id.co.faspay.snap.logging.Logger;
import id.co.faspay.snap.logging.LoggerFactory;

import java.util.Objects;

public class InquiryBalanceClient {
    private static final Logger logger = LoggerFactory.getLogger(InquiryBalanceClient.class);

    private final Constants constants;

    private final FaspaySnapHttpClient httpClient;

    public InquiryBalanceClient(FaspaySnapConfig config) {
        Objects.requireNonNull(config, "config must not be null");
        this.httpClient = new FaspaySnapHttpClient(config);
        this.constants = new Constants();
    }

    public InquiryBalanceResponse inquiryBalance(InquiryBalanceRequest request) throws FaspaySnapApiException {
        Objects.requireNonNull(request, "request must not be null");

        logger.info("Starting balance inquiry for account: {}", request.getAccountNo());

        try {
            InquiryBalanceResponse response = httpClient.post(constants.getEndpointInquiryBalance(), constants.getUserAgent(), request, InquiryBalanceResponse.class);

            logger.info("Account inquiry balance completed with message: {}", response.getResponseMessage());

            return response;
        } catch (FaspaySnapApiException e) {
            logger.error("Error inquiring about account: {}", e.getMessage());
            throw e;
        }
    }
}
