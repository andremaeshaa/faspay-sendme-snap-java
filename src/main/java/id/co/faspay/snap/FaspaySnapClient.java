package id.co.faspay.snap;

import id.co.faspay.snap.config.FaspaySnapConfig;
import id.co.faspay.snap.service.AccountInquiryService;

/**
 * Main entry point for the Faspay SendMe Snap API SDK.
 * This client provides access to all Faspay SendMe Snap API endpoints.
 */
public class FaspaySnapClient {
    private final FaspaySnapConfig config;
    private final AccountInquiryService accountInquiryService;

    /**
     * Creates a new Faspay SendMe Snap API client with the provided configuration.
     *
     * @param config The configuration for the Faspay SendMe Snap API
     */
    public FaspaySnapClient(FaspaySnapConfig config) {
        this.config = config;
        this.accountInquiryService = new AccountInquiryService(config);
    }

    /**
     * Gets the service for Account Inquiry operations.
     *
     * @return The Account Inquiry service
     */
    public AccountInquiryService accountInquiry() {
        return accountInquiryService;
    }

    /**
     * Gets the configuration used by this client.
     *
     * @return The Faspay SendMe Snap API configuration
     */
    public FaspaySnapConfig getConfig() {
        return config;
    }
}
