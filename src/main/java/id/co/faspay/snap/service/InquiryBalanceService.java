package id.co.faspay.snap.service;

import id.co.faspay.snap.client.AccountInquiryClient;
import id.co.faspay.snap.client.InquiryBalanceClient;
import id.co.faspay.snap.config.FaspaySnapConfig;
import id.co.faspay.snap.exception.FaspaySnapApiException;
import id.co.faspay.snap.model.InquiryBalanceRequest;
import id.co.faspay.snap.model.InquiryBalanceResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class InquiryBalanceService {
    private static final Logger logger = LoggerFactory.getLogger(InquiryBalanceService.class);
    private final InquiryBalanceClient inquiryBalanceClient;

    public InquiryBalanceService(FaspaySnapConfig config) {
        Objects.requireNonNull(config, "config must not be null");
        this.inquiryBalanceClient = new InquiryBalanceClient(config);
    }

    public InquiryBalanceResponse balance(InquiryBalanceRequest request) throws FaspaySnapApiException {
        Objects.requireNonNull(request, "request must not be null");

        logger.debug("Processing inquiry balance request: {}", request);

        try {

            // Here we could add additional business logic after receiving the API response
            // For example, enrichment, transformation, caching, etc.

            return inquiryBalanceClient.inquiryBalance(request);
        } catch (FaspaySnapApiException e) {
            logger.error("Error processing account inquiry: {}", e.getMessage());
            throw e;
        }
    }
}
