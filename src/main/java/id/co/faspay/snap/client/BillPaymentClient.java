package id.co.faspay.snap.client;

import id.co.faspay.snap.config.FaspaySnapConfig;
import id.co.faspay.snap.exception.FaspaySnapApiException;
import id.co.faspay.snap.model.BillPaymentRequest;
import id.co.faspay.snap.model.BillPaymentResponse;
import id.co.faspay.snap.model.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class BillPaymentClient {
    private static final Logger logger = LoggerFactory.getLogger(BillPaymentClient.class);
    private final Constants constants;
    private final FaspaySnapHttpClient httpClient;

    public BillPaymentClient(FaspaySnapConfig config) {
        Objects.requireNonNull(config, "config must not be null");
        this.httpClient = new FaspaySnapHttpClient(config);
        this.constants = new Constants();
    }

    public BillPaymentResponse billPayment(BillPaymentRequest request) throws FaspaySnapApiException {
        Objects.requireNonNull(request, "request must not be null");

        logger.info("bill payment about virtual account {}", request.getVirtualAccountNo());

        try {
            BillPaymentResponse response = httpClient.post(constants.getEndpointBillPayment(), constants.getUserAgent(), request, BillPaymentResponse.class);

            logger.info("bill payment completed with response code: {}", response.getResponseCode());

            return response;
        } catch (RuntimeException e) {
            logger.error("Error transfer status about account: {}", e.getMessage());
            throw e;
        }
    }
}
