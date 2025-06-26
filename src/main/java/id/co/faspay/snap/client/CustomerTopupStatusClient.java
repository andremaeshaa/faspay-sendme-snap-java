package id.co.faspay.snap.client;

import id.co.faspay.snap.config.FaspaySnapConfig;
import id.co.faspay.snap.exception.FaspaySnapApiException;
import id.co.faspay.snap.model.Constants;
import id.co.faspay.snap.model.CustomerTopupStatusRequest;
import id.co.faspay.snap.model.CustomerTopupStatusResponse;
import id.co.faspay.snap.model.StatusTransferResponse;
import id.co.faspay.snap.logging.Logger;
import id.co.faspay.snap.logging.LoggerFactory;

import java.util.Objects;

public class CustomerTopupStatusClient {
    private static final Logger logger = LoggerFactory.getLogger(CustomerTopupStatusClient.class);
    private final Constants constants;
    private final FaspaySnapHttpClient httpClient;

    public CustomerTopupStatusClient(FaspaySnapConfig config) {
        Objects.requireNonNull(config, "config must not be null");

        this.httpClient = new FaspaySnapHttpClient(config);
        this.constants = new Constants();
    }

    public CustomerTopupStatusResponse status(CustomerTopupStatusRequest request) throws FaspaySnapApiException {
        Objects.requireNonNull(request, "request must not be null");

        logger.info("topup status about account {} at service code {}", request.getOriginalReferenceNo(), request.getServiceCode());

        try {
            CustomerTopupStatusResponse response = httpClient.post(constants.getEndpointCustomerTopupStatus(), constants.getUserAgent(), request, CustomerTopupStatusResponse.class);

            logger.info("transfer status completed with response code: {}", response.getResponseCode());

            return response;
        } catch (RuntimeException e) {
            logger.error("Error transfer status about account: {}", e.getMessage());
            throw e;
        }
    }
}
