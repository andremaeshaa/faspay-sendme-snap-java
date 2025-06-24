package id.co.faspay.snap.service;

import id.co.faspay.snap.client.CustomerTopupStatusClient;
import id.co.faspay.snap.config.FaspaySnapConfig;
import id.co.faspay.snap.exception.FaspaySnapApiException;
import id.co.faspay.snap.model.CustomerTopupStatusRequest;
import id.co.faspay.snap.model.CustomerTopupStatusResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class CustomerTopupStatusService {
    private static final Logger logger = LoggerFactory.getLogger(CustomerTopupStatusService.class);
    private final CustomerTopupStatusClient customerTopupStatusClient;

    public CustomerTopupStatusService(FaspaySnapConfig config) {
        Objects.requireNonNull(config, "config must not be null");
        this.customerTopupStatusClient = new CustomerTopupStatusClient(config);
    }

    public CustomerTopupStatusResponse status(CustomerTopupStatusRequest request) throws FaspaySnapApiException {
        Objects.requireNonNull(request, "request must not be null");

        logger.debug("Processing customer topup status request: {}", request);

        try {
            CustomerTopupStatusResponse response = customerTopupStatusClient.status(request);

            logger.info("customer topup status completed with response code: {}", response.getResponseCode());

            return response;
        } catch (FaspaySnapApiException e) {
            logger.error("Error processing transfer status inquiry: {}", e.getMessage());
            throw e;
        }
    }
}
