package id.co.faspay.snap.client;

import id.co.faspay.snap.config.FaspaySnapConfig;
import id.co.faspay.snap.exception.FaspaySnapApiException;
import id.co.faspay.snap.model.Constants;
import id.co.faspay.snap.model.CustomerTopupRequest;
import id.co.faspay.snap.model.CustomerTopupResponse;
import id.co.faspay.snap.logging.Logger;
import id.co.faspay.snap.logging.LoggerFactory;

import java.util.Objects;

public class CustomerTopupClient {
    private static final Logger logger = LoggerFactory.getLogger(CustomerTopupClient.class);
    private final Constants constants;
    private final FaspaySnapHttpClient httpClient;

    public CustomerTopupClient(FaspaySnapConfig config) {
        Objects.requireNonNull(config, "config must not be null");
        this.httpClient = new FaspaySnapHttpClient(config);
        this.constants = new Constants();
    }

    public CustomerTopupResponse topup (CustomerTopupRequest request) throws FaspaySnapApiException {
        logger.debug("Processing customer topup request: {}", request);

        logger.info("customer topup about account {} at platform {}", request.getAdditionalInfo().getSourceAccount(), request.getAdditionalInfo().getPlatformCode());

        try {
            CustomerTopupResponse response = httpClient.post(constants.getEndpointCustomerTopup(), constants.getUserAgent(), request, CustomerTopupResponse.class);

            logger.info("transfer status completed with response code: {}", response.getResponseCode());

            return response;
        } catch (FaspaySnapApiException e) {
            logger.error("Error transfer status about account: {}", e.getMessage());
            throw e;
        }
    }
}
