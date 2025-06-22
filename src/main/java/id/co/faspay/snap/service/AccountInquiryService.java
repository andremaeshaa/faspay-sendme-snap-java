package id.co.faspay.snap.service;

import id.co.faspay.snap.client.AccountInquiryClient;
import id.co.faspay.snap.config.FaspaySnapConfig;
import id.co.faspay.snap.exception.FaspaySnapApiException;
import id.co.faspay.snap.model.AccountInquiryRequest;
import id.co.faspay.snap.model.AccountInquiryResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

/**
 * Service for account inquiry operations.
 * This service provides methods for inquiring about bank accounts and handles business logic.
 */
public class AccountInquiryService {
    private static final Logger logger = LoggerFactory.getLogger(AccountInquiryService.class);
    
    private final AccountInquiryClient accountInquiryClient;
    
    /**
     * Creates a new Account Inquiry service with the provided configuration.
     *
     * @param config The configuration for the Faspay SendMe Snap API
     */
    public AccountInquiryService(FaspaySnapConfig config) {
        Objects.requireNonNull(config, "config must not be null");
        this.accountInquiryClient = new AccountInquiryClient(config);
    }
    
    /**
     * Inquires about a bank account.
     *
     * @param bankCode The bank code
     * @param accountNumber The account number to inquire about
     * @param partnerReferenceNumber The partner reference number for tracking
     * @return The response from the API
     * @throws FaspaySnapApiException If an error occurs while making the request
     */
    public AccountInquiryResponse inquire(String bankCode, String accountNumber, String partnerReferenceNumber) 
            throws FaspaySnapApiException {
        Objects.requireNonNull(bankCode, "bankCode must not be null");
        Objects.requireNonNull(accountNumber, "accountNumber must not be null");
        Objects.requireNonNull(partnerReferenceNumber, "partnerReferenceNumber must not be null");
        
        logger.debug("Processing account inquiry request for account {} at bank {}", accountNumber, bankCode);
        
        // Here we could add additional business logic before making the API call
        // For example, validation, transformation, caching, etc.
        
        AccountInquiryRequest request = new AccountInquiryRequest(bankCode, accountNumber, partnerReferenceNumber);
        return inquire(request);
    }
    
    /**
     * Inquires about a bank account.
     *
     * @param request The request object containing the account details
     * @return The response from the API
     * @throws FaspaySnapApiException If an error occurs while making the request
     */
    public AccountInquiryResponse inquire(AccountInquiryRequest request) throws FaspaySnapApiException {
        Objects.requireNonNull(request, "request must not be null");
        
        logger.debug("Processing account inquiry request: {}", request);
        
        // Here we could add additional business logic before making the API call
        
        try {

            // Here we could add additional business logic after receiving the API response
            // For example, enrichment, transformation, caching, etc.
            
            return accountInquiryClient.inquire(request);
        } catch (FaspaySnapApiException e) {
            logger.error("Error processing account inquiry: {}", e.getMessage());
            throw e;
        }
    }
}