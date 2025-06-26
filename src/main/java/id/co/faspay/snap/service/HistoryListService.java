package id.co.faspay.snap.service;

import id.co.faspay.snap.client.HistoryListClient;
import id.co.faspay.snap.client.InquiryBalanceClient;
import id.co.faspay.snap.config.FaspaySnapConfig;
import id.co.faspay.snap.exception.FaspaySnapApiException;
import id.co.faspay.snap.model.HistoryListRequest;
import id.co.faspay.snap.model.HistoryListResponse;
import id.co.faspay.snap.logging.Logger;
import id.co.faspay.snap.logging.LoggerFactory;

import java.util.Objects;

public class HistoryListService {
    private static final Logger logger = LoggerFactory.getLogger(HistoryListService.class);
    private final HistoryListClient historyListService;

    public HistoryListService(FaspaySnapConfig config) {
        Objects.requireNonNull(config, "config must not be null");
        this.historyListService = new HistoryListClient(config);
    }

    public HistoryListResponse list(HistoryListRequest request) throws FaspaySnapApiException {
        Objects.requireNonNull(request, "request must not be null");

        logger.debug("Processing history list request: {}", request);

        try {

            // Here we could add additional business logic after receiving the API response
            // For example, enrichment, transformation, caching, etc.

            return historyListService.historyList(request);
        } catch (FaspaySnapApiException e) {
            logger.error("Error processing history list: {}", e.getMessage());
            throw e;
        }
    }
}
