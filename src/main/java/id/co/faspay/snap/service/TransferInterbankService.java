package id.co.faspay.snap.service;

import id.co.faspay.snap.client.TransferInterbankClient;
import id.co.faspay.snap.config.FaspaySnapConfig;
import id.co.faspay.snap.exception.FaspaySnapApiException;
import id.co.faspay.snap.model.Amount;
import id.co.faspay.snap.model.TransferInterbankRequest;
import id.co.faspay.snap.model.TransferInterbankResponse;
import id.co.faspay.snap.logging.Logger;
import id.co.faspay.snap.logging.LoggerFactory;

import java.time.OffsetDateTime;
import java.util.Objects;

/**
 * Service for interbank transfer operations.
 * This service provides methods for transferring money between banks and handles business logic.
 */
public class TransferInterbankService {
    private static final Logger logger = LoggerFactory.getLogger(TransferInterbankService.class);

    private final TransferInterbankClient transferInterbankClient;

    /**
     * Creates a new Transfer Interbank service with the provided configuration.
     *
     * @param config The configuration for the Faspay SendMe Snap API
     */
    public TransferInterbankService(FaspaySnapConfig config) {
        Objects.requireNonNull(config, "config must not be null");
        this.transferInterbankClient = new TransferInterbankClient(config);
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

        logger.debug("Processing interbank transfer request for {} {} from account {} to account {} at bank {}", 
                amount.getValue(), amount.getCurrency(), sourceAccountNumber, beneficiaryAccountNumber, beneficiaryBankCode);

        // Here we could add additional business logic before making the API call
        // For example, validation, transformation, caching, etc.

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

        logger.debug("Processing interbank transfer request: {}", request);

        // Here we could add additional business logic before making the API call

        try {

            // Here we could add additional business logic after receiving the API response
            // For example, enrichment, transformation, caching, etc.

            return transferInterbankClient.transfer(request);
        } catch (FaspaySnapApiException e) {
            logger.error("Error processing interbank transfer: {}", e.getMessage());
            throw e;
        }
    }

    /**
     * Transfers money between banks with additional information.
     *
     * @param partnerReferenceNumber The partner reference number for tracking
     * @param amount The amount to transfer
     * @param beneficiaryAccountName The name of the beneficiary account
     * @param beneficiaryAccountNumber The account number of the beneficiary
     * @param beneficiaryBankCode The bank code of the beneficiary
     * @param beneficiaryEmail The email of the beneficiary
     * @param sourceAccountNumber The account number of the source
     * @param transactionDate The date and time of the transaction
     * @param instructDate The instruct date
     * @param transactionDescription The transaction description
     * @param callbackUrl The callback URL
     * @return The response from the API
     * @throws FaspaySnapApiException If an error occurs while making the request
     */
    public TransferInterbankResponse transfer(String partnerReferenceNumber, Amount amount, 
                                             String beneficiaryAccountName, String beneficiaryAccountNumber, 
                                             String beneficiaryBankCode, String beneficiaryEmail, 
                                             String sourceAccountNumber, String transactionDate,
                                             String instructDate, String transactionDescription, 
                                             String callbackUrl) throws FaspaySnapApiException {
        Objects.requireNonNull(partnerReferenceNumber, "partnerReferenceNumber must not be null");
        Objects.requireNonNull(amount, "amount must not be null");
        Objects.requireNonNull(beneficiaryAccountName, "beneficiaryAccountName must not be null");
        Objects.requireNonNull(beneficiaryAccountNumber, "beneficiaryAccountNumber must not be null");
        Objects.requireNonNull(beneficiaryBankCode, "beneficiaryBankCode must not be null");
        Objects.requireNonNull(sourceAccountNumber, "sourceAccountNumber must not be null");
        Objects.requireNonNull(transactionDate, "transactionDate must not be null");

        logger.debug("Processing interbank transfer request with additional info for {} {} from account {} to account {} at bank {}", 
                amount.getValue(), amount.getCurrency(), sourceAccountNumber, beneficiaryAccountNumber, beneficiaryBankCode);

        TransferInterbankRequest request = new TransferInterbankRequest(partnerReferenceNumber, amount, 
                beneficiaryAccountName, beneficiaryAccountNumber, beneficiaryBankCode, 
                sourceAccountNumber);

        if (beneficiaryEmail != null) {
            request.setBeneficiaryEmail(beneficiaryEmail);
        }

        if (instructDate != null) {
            request.setInstructDate(instructDate);
        }

        if (transactionDescription != null) {
            request.setTransactionDescription(transactionDescription);
        }

        if (callbackUrl != null) {
            request.setCallbackUrl(callbackUrl);
        }

        return transfer(request);
    }
}
