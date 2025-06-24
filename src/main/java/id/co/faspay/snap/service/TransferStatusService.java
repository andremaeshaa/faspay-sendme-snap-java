package id.co.faspay.snap.service;

import id.co.faspay.snap.client.TransferStatusClient;
import id.co.faspay.snap.config.FaspaySnapConfig;
import id.co.faspay.snap.exception.FaspaySnapApiException;
import id.co.faspay.snap.model.StatusTransferRequest;
import id.co.faspay.snap.model.StatusTransferResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class TransferStatusService {
    private static final Logger logger = LoggerFactory.getLogger(TransferStatusService.class);
    private final TransferStatusClient transferStatusClient;

    public TransferStatusService(FaspaySnapConfig config) {
        Objects.requireNonNull(config, "config must not be null");
        this.transferStatusClient = new TransferStatusClient(config);
    }

    public StatusTransferResponse status(StatusTransferRequest request) throws FaspaySnapApiException {
        Objects.requireNonNull(request, "request must not be null");

        logger.debug("Processing transfer status request: {}", request);

        try {

            // Here we could add additional business logic after receiving the API response
            // For example, enrichment, transformation, caching, etc.

            return transferStatusClient.status(request);
        } catch (FaspaySnapApiException e) {
            logger.error("Error processing transfer status inquiry: {}", e.getMessage());
            throw e;
        }
    }
}
