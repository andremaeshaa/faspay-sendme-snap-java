package id.co.faspay.snap.client;

import id.co.faspay.snap.config.FaspaySnapConfig;
import id.co.faspay.snap.exception.FaspaySnapApiException;
import id.co.faspay.snap.model.Constants;
import id.co.faspay.snap.model.StatusTransferRequest;
import id.co.faspay.snap.model.StatusTransferResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class TransferStatusClient {
    private static final Logger logger = LoggerFactory.getLogger(TransferStatusClient.class);
    private final Constants constants;
    private final FaspaySnapHttpClient httpClient;

    public TransferStatusClient(FaspaySnapConfig config) {
        Objects.requireNonNull(config, "config must not be null");

        this.httpClient = new FaspaySnapHttpClient(config);
        this.constants = new Constants();
    }

    public StatusTransferResponse status(StatusTransferRequest request) throws FaspaySnapApiException {
        Objects.requireNonNull(request, "request must not be null");

        logger.info("transfer status about account {} at bank {}", request.getOriginalReferenceNo(), request.getServiceCode());

        try {
            StatusTransferResponse response = httpClient.post(constants.getEndpointTransferInquiryStatus(), constants.getUserAgent(), request, StatusTransferResponse.class);

            logger.info("transfer status completed with response code: {}", response.getResponseCode());

            return response;
        } catch (RuntimeException e) {
            logger.error("Error transfer status about account: {}", e.getMessage());
            throw e;
        }
    }
}
