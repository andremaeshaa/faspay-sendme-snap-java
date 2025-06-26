package id.co.faspay.snap.client;

import id.co.faspay.snap.config.FaspaySnapConfig;
import id.co.faspay.snap.exception.FaspaySnapApiException;
import id.co.faspay.snap.model.Amount;
import id.co.faspay.snap.model.Constants;
import id.co.faspay.snap.model.TransferInterbankRequest;
import id.co.faspay.snap.model.TransferInterbankResponse;
import id.co.faspay.snap.logging.Logger;
import id.co.faspay.snap.logging.LoggerFactory;

import java.time.OffsetDateTime;
import java.util.Objects;

/**
 * Client for the Transfer Interbank API.
 * This class provides methods for transferring money between banks.
 */
public class TransferInterbankClient {
    private static final Logger logger = LoggerFactory.getLogger(TransferInterbankClient.class);

    private final Constants constants;

    private final FaspaySnapHttpClient httpClient;

    /**
     * Creates a new Transfer Interbank client with the provided configuration.
     *
     * @param config The configuration for the Faspay SendMe Snap API
     */
    public TransferInterbankClient(FaspaySnapConfig config) {
        Objects.requireNonNull(config, "config must not be null");
        this.httpClient = new FaspaySnapHttpClient(config);
        this.constants = new Constants();
    }

    /**
     * Transfers money between banks.
     *
     * @param partnerReferenceNumber The partner reference number for tracking
     * @param amount The amount to transfer
     * @param beneficiaryAccountName The name of the beneficiary account
     * @param beneficiaryAccountNumber The account number of the beneficiary
     * @param beneficiaryBankCode The bank code of the beneficiary
     * @param sourceAccountNumber The account number of the source
     * @param transactionDate The date and time of the transaction
     * @return The response from the API
     * @throws FaspaySnapApiException If an error occurs while making the request
     */
    public TransferInterbankResponse transfer(String partnerReferenceNumber, Amount amount, 
                                             String beneficiaryAccountName, String beneficiaryAccountNumber, 
                                             String beneficiaryBankCode, String sourceAccountNumber,
                                             String transactionDate) throws FaspaySnapApiException {
        Objects.requireNonNull(partnerReferenceNumber, "partnerReferenceNumber must not be null");
        Objects.requireNonNull(amount, "amount must not be null");
        Objects.requireNonNull(beneficiaryAccountName, "beneficiaryAccountName must not be null");
        Objects.requireNonNull(beneficiaryAccountNumber, "beneficiaryAccountNumber must not be null");
        Objects.requireNonNull(beneficiaryBankCode, "beneficiaryBankCode must not be null");
        Objects.requireNonNull(sourceAccountNumber, "sourceAccountNumber must not be null");
        Objects.requireNonNull(transactionDate, "transactionDate must not be null");

        TransferInterbankRequest request = new TransferInterbankRequest(partnerReferenceNumber, amount, 
                beneficiaryAccountName, beneficiaryAccountNumber, beneficiaryBankCode, 
                sourceAccountNumber);
        return transfer(request);
    }

    /**
     * Transfers money between banks.
     *
     * @param request The request object containing the transfer details
     * @return The response from the API
     * @throws FaspaySnapApiException If an error occurs while making the request
     */
    public TransferInterbankResponse transfer(TransferInterbankRequest request) throws FaspaySnapApiException {
        Objects.requireNonNull(request, "request must not be null");

        logger.info("Transferring {} {} from account {} to account {} at bank {}", 
                request.getAmount().getValue(), request.getAmount().getCurrency(),
                request.getSourceAccountNumber(), request.getBeneficiaryAccountNumber(), 
                request.getBeneficiaryBankCode());

        try {
            TransferInterbankResponse response = httpClient.post(constants.getEndpointTransferInterbank(), 
                    constants.getUserAgent(), request, TransferInterbankResponse.class);

            logger.info("Transfer completed with response code: {}", response.getResponseCode());

            return response;
        } catch (FaspaySnapApiException e) {
            logger.error("Error transferring money: {}", e.getMessage());
            throw e;
        }
    }
}
