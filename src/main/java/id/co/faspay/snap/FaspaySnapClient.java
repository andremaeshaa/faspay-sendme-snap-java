package id.co.faspay.snap;

import id.co.faspay.snap.config.FaspaySnapConfig;
import id.co.faspay.snap.service.*;

/**
 * Main entry point for the Faspay SendMe Snap API SDK.
 * This client provides access to all Faspay SendMe Snap API endpoints.
 */
public class FaspaySnapClient {
    private final FaspaySnapConfig config;
    private final AccountInquiryService accountInquiryService;
    private final TransferInterbankService transferInterbankService;
    private final TransferStatusService transferStatusService;
    private final InquiryBalanceService inquiryBalanceService;
    private final HistoryListService historyListService;
    private final CustomerTopupService customerTopupService;
    private final CustomerTopupStatusService customerTopupStatusService;
    private final BillPaymentService billPaymentService;

    /**
     * Creates a new Faspay SendMe Snap API client with the provided configuration.
     *
     * @param config The configuration for the Faspay SendMe Snap API
     */
    public FaspaySnapClient(FaspaySnapConfig config) {
        this.config = config;
        this.accountInquiryService = new AccountInquiryService(config);
        this.transferInterbankService = new TransferInterbankService(config);
        this.transferStatusService = new TransferStatusService(config);
        this.inquiryBalanceService = new InquiryBalanceService(config);
        this.historyListService = new HistoryListService(config);
        this.customerTopupService = new CustomerTopupService(config);
        this.customerTopupStatusService = new CustomerTopupStatusService(config);
        this.billPaymentService = new BillPaymentService(config);
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
     * Gets the service for Transfer Interbank operations.
     *
     * @return The Transfer Interbank service
     */
    public TransferInterbankService transferInterbank() {
        return transferInterbankService;
    }

    public TransferStatusService transferStatus() {
        return transferStatusService;
    }

    public InquiryBalanceService inquiryBalance() {
        return inquiryBalanceService;
    }

    public HistoryListService historyList() {
        return historyListService;
    }

    public CustomerTopupService customerTopup() {
        return customerTopupService;
    }

    public CustomerTopupStatusService customerTopupStatus() {
        return customerTopupStatusService;
    }

    public BillPaymentService billPayment() {
        return billPaymentService;
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
