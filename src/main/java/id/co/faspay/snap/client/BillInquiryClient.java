package id.co.faspay.snap.client;

import id.co.faspay.snap.config.FaspaySnapConfig;
import id.co.faspay.snap.exception.FaspaySnapApiException;
import id.co.faspay.snap.model.BillInquiryRequest;
import id.co.faspay.snap.model.BillInquiryResponse;
import id.co.faspay.snap.model.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class BillInquiryClient {
    private static final Logger logger = LoggerFactory.getLogger(BillInquiryClient.class);
    private final Constants constants;
    private final FaspaySnapHttpClient httpClient;

    public BillInquiryClient(FaspaySnapConfig config) {
        Objects.requireNonNull(config, "config must not be null");
        this.httpClient = new FaspaySnapHttpClient(config);
        this.constants = new Constants();
    }

    public BillInquiryResponse BillInquiry(BillInquiryRequest request) throws FaspaySnapApiException {
        Objects.requireNonNull(request, "request must not be null");

        logger.info("bill inquiry about virtual account {}", request.getVirtualAccountNo());

        try {
            BillInquiryResponse response = httpClient.post(constants.getEndpointBillInquiry(), constants.getUserAgent(), request, BillInquiryResponse.class);

            logger.info("bill inquiry completed with response code: {}", response.getResponseCode());

            return response;
        } catch (RuntimeException e) {
            logger.error("Error transfer status about account: {}", e.getMessage());
            throw e;
        }
    }
}
