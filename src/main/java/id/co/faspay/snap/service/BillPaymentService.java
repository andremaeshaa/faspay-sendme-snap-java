package id.co.faspay.snap.service;

import id.co.faspay.snap.client.BillPaymentClient;
import id.co.faspay.snap.client.TransferStatusClient;
import id.co.faspay.snap.config.FaspaySnapConfig;
import id.co.faspay.snap.exception.FaspaySnapApiException;
import id.co.faspay.snap.model.BillPaymentRequest;
import id.co.faspay.snap.model.BillPaymentResponse;
import id.co.faspay.snap.logging.Logger;
import id.co.faspay.snap.logging.LoggerFactory;

import java.util.Objects;

public class BillPaymentService {
    private static final Logger logger = LoggerFactory.getLogger(BillPaymentService.class);
    private final BillPaymentClient billPaymentClient;

    public BillPaymentService(FaspaySnapConfig config) {
        Objects.requireNonNull(config, "config must not be null");
        this.billPaymentClient = new BillPaymentClient(config);
    }

    public BillPaymentResponse payment(BillPaymentRequest request) throws FaspaySnapApiException {
        Objects.requireNonNull(request, "request must not be null");

        logger.debug("Processing bill payment request: {}", request);

        try {

            // Here we could add additional business logic after receiving the API response
            // For example, enrichment, transformation, caching, etc.

            return billPaymentClient.billPayment(request);
        } catch (FaspaySnapApiException e) {
            logger.error("Error processing transfer status inquiry: {}", e.getMessage());
            throw e;
        }
    }
}
