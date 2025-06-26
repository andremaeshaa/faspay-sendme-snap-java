package id.co.faspay.snap.client;

import id.co.faspay.snap.config.FaspaySnapConfig;
import id.co.faspay.snap.exception.FaspaySnapApiException;
import id.co.faspay.snap.model.Constants;
import id.co.faspay.snap.model.HistoryListRequest;
import id.co.faspay.snap.model.HistoryListResponse;
import id.co.faspay.snap.model.InquiryBalanceResponse;
import id.co.faspay.snap.logging.Logger;
import id.co.faspay.snap.logging.LoggerFactory;

import java.util.Objects;

public class HistoryListClient {
    private static final Logger logger = LoggerFactory.getLogger(HistoryListClient.class);
    private final Constants constants;
    private final FaspaySnapHttpClient httpClient;

    public HistoryListClient(FaspaySnapConfig config) {
        Objects.requireNonNull(config, "config must not be null");

        this.httpClient = new FaspaySnapHttpClient(config);
        this.constants = new Constants();
    }

    public HistoryListResponse historyList(HistoryListRequest request) throws FaspaySnapApiException {
        Objects.requireNonNull(request, "request must not be null");

        logger.info("Starting history list for account: {}", request.getAccountNo());

        try {
            HistoryListResponse response = httpClient.post(constants.getEndpointHistoryList(), constants.getUserAgent(), request, HistoryListResponse.class);

            logger.info("history list completed with message: {}", response.getResponseMessage());

            return response;
        } catch (FaspaySnapApiException e) {
            logger.error("Error inquiring about account: {}", e.getMessage());
            throw e;
        }
    }
}
