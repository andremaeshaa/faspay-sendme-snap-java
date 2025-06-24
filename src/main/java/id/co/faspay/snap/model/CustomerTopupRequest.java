package id.co.faspay.snap.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.OffsetDateTime;
import java.util.Objects;

/**
 * Request model for the Customer Topup API.
 * This class represents the request payload for customer topup transactions.
 */
public class CustomerTopupRequest {

    @JsonProperty("partnerReferenceNo")
    private String partnerReferenceNo;

    @JsonProperty("customerNumber")
    private String customerNumber;

    @JsonProperty("amount")
    private Amount amount;

    @JsonProperty("transactionDate")
    private String transactionDate;

    @JsonProperty("additionalInfo")
    private AdditionalInfo additionalInfo;

    /**
     * Default constructor for Jackson deserialization.
     */
    public CustomerTopupRequest() {
    }

    /**
     * Creates a new customer topup request with the specified parameters.
     *
     * @param partnerReferenceNo The partner reference number
     * @param customerNumber The customer number
     * @param amount The transaction amount
     * @param transactionDate The transaction date
     * @param additionalInfo Additional information
     */
    public CustomerTopupRequest(String partnerReferenceNo, String customerNumber, Amount amount, 
                               String transactionDate, AdditionalInfo additionalInfo) {
        this.partnerReferenceNo = Objects.requireNonNull(partnerReferenceNo, "partnerReferenceNo must not be null");
        this.customerNumber = Objects.requireNonNull(customerNumber, "customerNumber must not be null");
        this.amount = Objects.requireNonNull(amount, "amount must not be null");
        this.transactionDate = Objects.requireNonNull(transactionDate, "transactionDate must not be null");
        this.additionalInfo = Objects.requireNonNull(additionalInfo, "additionalInfo must not be null");
    }

    /**
     * Gets the partner reference number.
     *
     * @return The partner reference number
     */
    public String getPartnerReferenceNo() {
        return partnerReferenceNo;
    }

    /**
     * Sets the partner reference number.
     *
     * @param partnerReferenceNo The partner reference number
     * @return This request object
     */
    public CustomerTopupRequest setPartnerReferenceNo(String partnerReferenceNo) {
        this.partnerReferenceNo = partnerReferenceNo;
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
     * @return This request object
     */
    public CustomerTopupRequest setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
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
     * @return This request object
     */
    public CustomerTopupRequest setAmount(Amount amount) {
        this.amount = amount;
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
     * @return This request object
     */
    public CustomerTopupRequest setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
        return this;
    }

    /**
     * Sets the transaction date using OffsetDateTime.
     *
     * @param transactionDate The transaction date as OffsetDateTime
     * @return This request object
     */
    public CustomerTopupRequest setTransactionDate(OffsetDateTime transactionDate) {
        this.transactionDate = transactionDate != null ? transactionDate.toString() : null;
        return this;
    }

    /**
     * Gets the additional info.
     *
     * @return The additional info
     */
    public AdditionalInfo getAdditionalInfo() {
        return additionalInfo;
    }

    /**
     * Sets the additional info.
     *
     * @param additionalInfo The additional info
     * @return This request object
     */
    public CustomerTopupRequest setAdditionalInfo(AdditionalInfo additionalInfo) {
        this.additionalInfo = additionalInfo;
        return this;
    }

    /**
     * Sets the source account in additional info.
     *
     * @param sourceAccount The source account
     * @return This request object
     */
    public CustomerTopupRequest setSourceAccount(String sourceAccount) {
        if (this.additionalInfo == null) {
            this.additionalInfo = new AdditionalInfo();
        }
        this.additionalInfo.setSourceAccount(sourceAccount);
        return this;
    }

    /**
     * Sets the platform code in additional info.
     *
     * @param platformCode The platform code
     * @return This request object
     */
    public CustomerTopupRequest setPlatformCode(String platformCode) {
        if (this.additionalInfo == null) {
            this.additionalInfo = new AdditionalInfo();
        }
        this.additionalInfo.setPlatformCode(platformCode);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerTopupRequest that = (CustomerTopupRequest) o;
        return Objects.equals(partnerReferenceNo, that.partnerReferenceNo) &&
                Objects.equals(customerNumber, that.customerNumber) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(transactionDate, that.transactionDate) &&
                Objects.equals(additionalInfo, that.additionalInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(partnerReferenceNo, customerNumber, amount, transactionDate, additionalInfo);
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
            return "CustomerTopupRequest{error: \"Failed to convert to JSON: " + e.getMessage() + "\"}";
        }
    }

    /**
     * Inner class representing additional information for customer topup.
     */
    public static class AdditionalInfo {
        @JsonProperty("sourceAccount")
        private String sourceAccount;

        @JsonProperty("platformCode")
        private String platformCode;

        @JsonProperty("instructDate")
        private String instructDate;

        @JsonProperty("beneficiaryEmail")
        private String beneficiaryEmail;

        @JsonProperty("transactionDescription")
        private String transactionDescription;

        @JsonProperty("callbackUrl")
        private String callbackUrl;

        /**
         * Default constructor for Jackson deserialization.
         */
        public AdditionalInfo() {
        }

        /**
         * Creates a new additional info with the specified parameters.
         *
         * @param sourceAccount The source account
         * @param platformCode The platform code
         */
        public AdditionalInfo(String sourceAccount, String platformCode) {
            this.sourceAccount = sourceAccount;
            this.platformCode = platformCode;
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
        public AdditionalInfo setSourceAccount(String sourceAccount) {
            this.sourceAccount = sourceAccount;
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
        public AdditionalInfo setPlatformCode(String platformCode) {
            this.platformCode = platformCode;
            return this;
        }

        /**
         * Gets the instruct date.
         *
         * @return The instruct date
         */
        public String getInstructDate() {
            return instructDate;
        }

        /**
         * Sets the instruct date.
         *
         * @param instructDate The instruct date
         * @return This additional info object
         */
        public AdditionalInfo setInstructDate(String instructDate) {
            this.instructDate = instructDate;
            return this;
        }

        /**
         * Sets the instruct date using OffsetDateTime.
         *
         * @param instructDate The instruct date as OffsetDateTime
         * @return This additional info object
         */
        public AdditionalInfo setInstructDate(OffsetDateTime instructDate) {
            this.instructDate = instructDate != null ? instructDate.toString() : null;
            return this;
        }

        /**
         * Gets the beneficiary email.
         *
         * @return The beneficiary email
         */
        public String getBeneficiaryEmail() {
            return beneficiaryEmail;
        }

        /**
         * Sets the beneficiary email.
         *
         * @param beneficiaryEmail The beneficiary email
         * @return This additional info object
         */
        public AdditionalInfo setBeneficiaryEmail(String beneficiaryEmail) {
            this.beneficiaryEmail = beneficiaryEmail;
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
        public AdditionalInfo setTransactionDescription(String transactionDescription) {
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
        public AdditionalInfo setCallbackUrl(String callbackUrl) {
            this.callbackUrl = callbackUrl;
            return this;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            AdditionalInfo that = (AdditionalInfo) o;
            return Objects.equals(sourceAccount, that.sourceAccount) &&
                    Objects.equals(platformCode, that.platformCode) &&
                    Objects.equals(instructDate, that.instructDate) &&
                    Objects.equals(beneficiaryEmail, that.beneficiaryEmail) &&
                    Objects.equals(transactionDescription, that.transactionDescription) &&
                    Objects.equals(callbackUrl, that.callbackUrl);
        }

        @Override
        public int hashCode() {
            return Objects.hash(sourceAccount, platformCode, instructDate, beneficiaryEmail, transactionDescription, callbackUrl);
        }

        @Override
        public String toString() {
            return "AdditionalInfo{" +
                    "sourceAccount='" + sourceAccount + '\'' +
                    ", platformCode='" + platformCode + '\'' +
                    ", instructDate='" + instructDate + '\'' +
                    ", beneficiaryEmail='" + beneficiaryEmail + '\'' +
                    ", transactionDescription='" + transactionDescription + '\'' +
                    ", callbackUrl='" + callbackUrl + '\'' +
                    '}';
        }
    }
}