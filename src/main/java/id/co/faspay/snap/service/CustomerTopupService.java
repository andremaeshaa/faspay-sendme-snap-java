package id.co.faspay.snap.service;

import id.co.faspay.snap.client.CustomerTopupClient;
import id.co.faspay.snap.config.FaspaySnapConfig;
import id.co.faspay.snap.exception.FaspaySnapApiException;
import id.co.faspay.snap.model.CustomerTopupRequest;
import id.co.faspay.snap.model.CustomerTopupResponse;
import id.co.faspay.snap.logging.Logger;
import id.co.faspay.snap.logging.LoggerFactory;

import java.util.Objects;

public class CustomerTopupService {
    private static final Logger logger = LoggerFactory.getLogger(CustomerTopupService.class);
    private final CustomerTopupClient customerTopupClient;

    public CustomerTopupService(FaspaySnapConfig config) {
        Objects.requireNonNull(config, "config must not be null");
        this.customerTopupClient = new CustomerTopupClient(config);
    }

    public CustomerTopupResponse topup (CustomerTopupRequest request) throws FaspaySnapApiException {
        Objects.requireNonNull(request, "request must not be null");

        logger.debug("Processing customer topup request: {}", request);

        try {
            return customerTopupClient.topup(request);
        } catch (FaspaySnapApiException e) {
            logger.error("Error processing customer topup: {}", e.getMessage());
            throw e;
        }
    }
}
