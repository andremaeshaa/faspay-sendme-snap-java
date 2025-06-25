package id.co.faspay.snap.service;

import id.co.faspay.snap.client.BillInquiryClient;
import id.co.faspay.snap.config.FaspaySnapConfig;
import id.co.faspay.snap.exception.FaspaySnapApiException;
import id.co.faspay.snap.model.BillInquiryRequest;
import id.co.faspay.snap.model.BillInquiryResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class BillInquiryService {
    private static final Logger logger = LoggerFactory.getLogger(BillInquiryService.class);
    private final BillInquiryClient billInquiryClient;

    public BillInquiryService(FaspaySnapConfig config) {
        Objects.requireNonNull(config, "config must not be null");
        this.billInquiryClient = new BillInquiryClient(config);
    }

    public BillInquiryResponse inquiry(BillInquiryRequest request) throws FaspaySnapApiException {
        Objects.requireNonNull(request, "request must not be null");

        logger.debug("Processing bill inquiry request: {}", request);

        try {

            // Here we could add additional business logic after receiving the API response
            // For example, enrichment, transformation, caching, etc.

            return billInquiryClient.billInquiry(request);
        } catch (FaspaySnapApiException e) {
            logger.error("Error processing transfer status inquiry: {}", e.getMessage());
            throw e;
        }
    }
}
