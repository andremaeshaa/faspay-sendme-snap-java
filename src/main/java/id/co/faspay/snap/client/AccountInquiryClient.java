package id.co.faspay.snap.client;

import id.co.faspay.snap.config.FaspaySnapConfig;
import id.co.faspay.snap.exception.FaspaySnapApiException;
import id.co.faspay.snap.model.AccountInquiryRequest;
import id.co.faspay.snap.model.AccountInquiryResponse;
import id.co.faspay.snap.model.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

/**
 * Client for the Account Inquiry API.
 * This class provides methods for inquiring about bank accounts.
 */
public class AccountInquiryClient {
    private static final Logger logger = LoggerFactory.getLogger(AccountInquiryClient.class);
    private static final String ACCOUNT_INQUIRY_ENDPOINT = "/disbursement/account-inquiry";

    private final Constants constants;

    private final FaspaySnapHttpClient httpClient;
    
    /**
     * Creates a new Account Inquiry client with the provided configuration.
     *
     * @param config The configuration for the Faspay SendMe Snap API
     */
    public AccountInquiryClient(FaspaySnapConfig config) {
        Objects.requireNonNull(config, "config must not be null");
        this.httpClient = new FaspaySnapHttpClient(config);
        this.constants = new Constants();
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
        
        logger.info("Inquiring about account {} at bank {}", request.getAccountNumber(), request.getBankCode());
        
        try {
            AccountInquiryResponse response = httpClient.post(constants.getEndpointAccountInquiry(), request, AccountInquiryResponse.class);
            
            logger.info("Account inquiry completed with status: {}", response.getStatus());
            
            return response;
        } catch (FaspaySnapApiException e) {
            logger.error("Error inquiring about account: {}", e.getMessage());
            throw e;
        }
    }
}