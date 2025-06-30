package id.co.faspay.snap.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Response model for the Customer Topup Status API.
 * This class represents the response payload from checking customer topup status.
 */
public class CustomerTopupStatusResponse {

    @JsonProperty("responseCode")
    private String responseCode;

    @JsonProperty("responseMessage")
    private String responseMessage;

    @JsonProperty("originalReferenceNo")
    private String originalReferenceNo;

    @JsonProperty("originalPartnerReferenceNo")
    private String originalPartnerReferenceNo;

    @JsonProperty("serviceCode")
    private String serviceCode;

    @JsonProperty("amount")
    private Amount amount;

    @JsonProperty("latestTransactionStatus")
    private String latestTransactionStatus;

    @JsonProperty("transactionStatusDesc")
    private String transactionStatusDesc;

    @JsonProperty("additionalInfo")
    private CustomerTopupAdditionalInfo additionalInfo;

    /**
     * Default constructor for Jackson deserialization.
     */
    public CustomerTopupStatusResponse() {
    }

    /**
     * Gets the response code.
     *
     * @return The response code
     */
    public String getResponseCode() {
        return responseCode;
    }

    /**
     * Sets the response code.
     *
     * @param responseCode The response code
     * @return This response object
     */
    public CustomerTopupStatusResponse setResponseCode(String responseCode) {
        this.responseCode = responseCode;
        return this;
    }

    /**
     * Gets the response message.
     *
     * @return The response message
     */
    public String getResponseMessage() {
        return responseMessage;
    }

    /**
     * Sets the response message.
     *
     * @param responseMessage The response message
     * @return This response object
     */
    public CustomerTopupStatusResponse setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
        return this;
    }

    /**
     * Gets the original reference number.
     *
     * @return The original reference number
     */
    public String getOriginalReferenceNo() {
        return originalReferenceNo;
    }

    /**
     * Sets the original reference number.
     *
     * @param originalReferenceNo The original reference number
     * @return This response object
     */
    public CustomerTopupStatusResponse setOriginalReferenceNo(String originalReferenceNo) {
        this.originalReferenceNo = originalReferenceNo;
        return this;
    }

    /**
     * Gets the original partner reference number.
     *
     * @return The original partner reference number
     */
    public String getOriginalPartnerReferenceNo() {
        return originalPartnerReferenceNo;
    }

    /**
     * Sets the original partner reference number.
     *
     * @param originalPartnerReferenceNo The original partner reference number
     * @return This response object
     */
    public CustomerTopupStatusResponse setOriginalPartnerReferenceNo(String originalPartnerReferenceNo) {
        this.originalPartnerReferenceNo = originalPartnerReferenceNo;
        return this;
    }

    /**
     * Gets the service code.
     *
     * @return The service code
     */
    public String getServiceCode() {
        return serviceCode;
    }

    /**
     * Sets the service code.
     *
     * @param serviceCode The service code
     * @return This response object
     */
    public CustomerTopupStatusResponse setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
        return this;
    }

    /**
     * Gets the amount.
     *
     * @return The amount
     */
    public Amount getAmount() {
        return amount;
    }

    /**
     * Sets the amount.
     *
     * @param amount The amount
     * @return This response object
     */
    public CustomerTopupStatusResponse setAmount(Amount amount) {
        this.amount = amount;
        return this;
    }

    /**
     * Gets the latest transaction status.
     *
     * @return The latest transaction status
     */
    public String getLatestTransactionStatus() {
        return latestTransactionStatus;
    }

    /**
     * Sets the latest transaction status.
     *
     * @param latestTransactionStatus The latest transaction status
     * @return This response object
     */
    public CustomerTopupStatusResponse setLatestTransactionStatus(String latestTransactionStatus) {
        this.latestTransactionStatus = latestTransactionStatus;
        return this;
    }

    /**
     * Gets the transaction status description.
     *
     * @return The transaction status description
     */
    public String getTransactionStatusDesc() {
        return transactionStatusDesc;
    }

    /**
     * Sets the transaction status description.
     *
     * @param transactionStatusDesc The transaction status description
     * @return This response object
     */
    public CustomerTopupStatusResponse setTransactionStatusDesc(String transactionStatusDesc) {
        this.transactionStatusDesc = transactionStatusDesc;
        return this;
    }

    /**
     * Gets the additional info.
     *
     * @return The additional info
     */
    public CustomerTopupAdditionalInfo getAdditionalInfo() {
        return additionalInfo;
    }

    /**
     * Sets the additional info.
     *
     * @param additionalInfo The additional info
     * @return This response object
     */
    public CustomerTopupStatusResponse setAdditionalInfo(CustomerTopupAdditionalInfo additionalInfo) {
        this.additionalInfo = additionalInfo;
        return this;
    }

    /**
     * Checks if the customer topup status inquiry was successful.
     *
     * @return True if the inquiry was successful, false otherwise
     */
    public boolean isSuccess() {
        return Objects.equals(responseCode, "2003900");
    }

    /**
     * Checks if the transaction is completed successfully.
     *
     * @return True if the transaction is completed, false otherwise
     */
    public boolean isTransactionCompleted() {
        return Objects.equals(latestTransactionStatus, "00");
    }

    /**
     * Checks if the transaction is pending.
     *
     * @return True if the transaction is pending, false otherwise
     */
    public boolean isTransactionPending() {
        return Objects.equals(latestTransactionStatus, "03");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerTopupStatusResponse that = (CustomerTopupStatusResponse) o;
        return Objects.equals(responseCode, that.responseCode) &&
                Objects.equals(responseMessage, that.responseMessage) &&
                Objects.equals(originalReferenceNo, that.originalReferenceNo) &&
                Objects.equals(originalPartnerReferenceNo, that.originalPartnerReferenceNo) &&
                Objects.equals(serviceCode, that.serviceCode) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(latestTransactionStatus, that.latestTransactionStatus) &&
                Objects.equals(transactionStatusDesc, that.transactionStatusDesc) &&
                Objects.equals(additionalInfo, that.additionalInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(responseCode, responseMessage, originalReferenceNo, originalPartnerReferenceNo,
                serviceCode, amount, latestTransactionStatus, transactionStatusDesc, additionalInfo);
    }

    @Override
    public String toString() {
        try {
            ObjectMapper mapper = new ObjectMapper()
                    .registerModule(new com.fasterxml.jackson.datatype.jsr310.JavaTimeModule())
                    .disable(com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
//            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this);
            return mapper.writeValueAsString(this);
        } catch (com.fasterxml.jackson.core.JsonProcessingException e) {
            return "CustomerTopupStatus{error: \"Failed to convert to JSON: " + e.getMessage() + "\"}";
        }
    }

    /**
     * Additional info class for Customer Topup Status Response.
     */
    public static class CustomerTopupAdditionalInfo {

        @JsonProperty("sourceAccount")
        private String sourceAccount;

        @JsonProperty("transactionDate")
        private String transactionDate;

        @JsonProperty("platformCode")
        private String platformCode;

        @JsonProperty("platformName")
        private String platformName;

        @JsonProperty("customerNumber")
        private String customerNumber;

        @JsonProperty("customerName")
        private String customerName;

        @JsonProperty("transactionDescription")
        private String transactionDescription;

        @JsonProperty("callbackUrl")
        private String callbackUrl;

        @JsonProperty("transactionStatusDate")
        private String transactionStatusDate;

        /**
         * Default constructor for Jackson deserialization.
         */
        public CustomerTopupAdditionalInfo() {
        }

        /**
         * Gets the source account.
         *
         * @return The source account
         */
        public String getSourceAccount() {
            return sourceAccount;
        }

        /**
         * Sets the source account.
         *
         * @param sourceAccount The source account
         * @return This additional info object
         */
        public CustomerTopupAdditionalInfo setSourceAccount(String sourceAccount) {
            this.sourceAccount = sourceAccount;
            return this;
        }

        /**
         * Gets the transaction date.
         *
         * @return The transaction date
         */
        public String getTransactionDate() {
            return transactionDate;
        }

        /**
         * Sets the transaction date.
         *
         * @param transactionDate The transaction date
         * @return This additional info object
         */
        public CustomerTopupAdditionalInfo setTransactionDate(String transactionDate) {
            this.transactionDate = transactionDate;
            return this;
        }

        /**
         * Gets the platform code.
         *
         * @return The platform code
         */
        public String getPlatformCode() {
            return platformCode;
        }

        /**
         * Sets the platform code.
         *
         * @param platformCode The platform code
         * @return This additional info object
         */
        public CustomerTopupAdditionalInfo setPlatformCode(String platformCode) {
            this.platformCode = platformCode;
            return this;
        }

        /**
         * Gets the platform name.
         *
         * @return The platform name
         */
        public String getPlatformName() {
            return platformName;
        }

        /**
         * Sets the platform name.
         *
         * @param platformName The platform name
         * @return This additional info object
         */
        public CustomerTopupAdditionalInfo setPlatformName(String platformName) {
            this.platformName = platformName;
            return this;
        }

        /**
         * Gets the customer number.
         *
         * @return The customer number
         */
        public String getCustomerNumber() {
            return customerNumber;
        }

        /**
         * Sets the customer number.
         *
         * @param customerNumber The customer number
         * @return This additional info object
         */
        public CustomerTopupAdditionalInfo setCustomerNumber(String customerNumber) {
            this.customerNumber = customerNumber;
            return this;
        }

        /**
         * Gets the customer name.
         *
         * @return The customer name
         */
        public String getCustomerName() {
            return customerName;
        }

        /**
         * Sets the customer name.
         *
         * @param customerName The customer name
         * @return This additional info object
         */
        public CustomerTopupAdditionalInfo setCustomerName(String customerName) {
            this.customerName = customerName;
            return this;
        }

        /**
         * Gets the transaction description.
         *
         * @return The transaction description
         */
        public String getTransactionDescription() {
            return transactionDescription;
        }

        /**
         * Sets the transaction description.
         *
         * @param transactionDescription The transaction description
         * @return This additional info object
         */
        public CustomerTopupAdditionalInfo setTransactionDescription(String transactionDescription) {
            this.transactionDescription = transactionDescription;
            return this;
        }

        /**
         * Gets the callback URL.
         *
         * @return The callback URL
         */
        public String getCallbackUrl() {
            return callbackUrl;
        }

        /**
         * Sets the callback URL.
         *
         * @param callbackUrl The callback URL
         * @return This additional info object
         */
        public CustomerTopupAdditionalInfo setCallbackUrl(String callbackUrl) {
            this.callbackUrl = callbackUrl;
            return this;
        }

        /**
         * Gets the transaction status date.
         *
         * @return The transaction status date
         */
        public String getTransactionStatusDate() {
            return transactionStatusDate;
        }

        /**
         * Sets the transaction status date.
         *
         * @param transactionStatusDate The transaction status date
         * @return This additional info object
         */
        public CustomerTopupAdditionalInfo setTransactionStatusDate(String transactionStatusDate) {
            this.transactionStatusDate = transactionStatusDate;
            return this;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CustomerTopupAdditionalInfo that = (CustomerTopupAdditionalInfo) o;
            return Objects.equals(sourceAccount, that.sourceAccount) &&
                    Objects.equals(transactionDate, that.transactionDate) &&
                    Objects.equals(platformCode, that.platformCode) &&
                    Objects.equals(platformName, that.platformName) &&
                    Objects.equals(customerNumber, that.customerNumber) &&
                    Objects.equals(customerName, that.customerName) &&
                    Objects.equals(transactionDescription, that.transactionDescription) &&
                    Objects.equals(callbackUrl, that.callbackUrl) &&
                    Objects.equals(transactionStatusDate, that.transactionStatusDate);
        }

        @Override
        public int hashCode() {
            return Objects.hash(sourceAccount, transactionDate, platformCode, platformName, 
                    customerNumber, customerName, transactionDescription, callbackUrl, transactionStatusDate);
        }

        @Override
        public String toString() {
            return "CustomerTopupAdditionalInfo{" +
                    "sourceAccount='" + sourceAccount + '\'' +
                    ", transactionDate='" + transactionDate + '\'' +
                    ", platformCode='" + platformCode + '\'' +
                    ", platformName='" + platformName + '\'' +
                    ", customerNumber='" + customerNumber + '\'' +
                    ", customerName='" + customerName + '\'' +
                    ", transactionDescription='" + transactionDescription + '\'' +
                    ", callbackUrl='" + callbackUrl + '\'' +
                    ", transactionStatusDate='" + transactionStatusDate + '\'' +
                    '}';
        }
    }
}