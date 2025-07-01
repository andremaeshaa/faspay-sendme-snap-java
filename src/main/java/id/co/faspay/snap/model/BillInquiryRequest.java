package id.co.faspay.snap.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Represents a request for bill inquiry.
 * This class contains all the necessary information to perform a bill inquiry.
 */
public class BillInquiryRequest {

    @JsonProperty("partnerReferenceNo")
    private String partnerReferenceNo;

    @JsonProperty("partnerServiceId")
    private String partnerServiceId;

    @JsonProperty("customerNo")
    private String customerNo;

    @JsonProperty("virtualAccountNo")
    private String virtualAccountNo;

    @JsonProperty("additionalInfo")
    private AdditionalInfo additionalInfo;

    /**
     * Default constructor for Jackson deserialization.
     */
    public BillInquiryRequest() {
    }

    /**
     * Constructor with all parameters.
     *
     * @param partnerReferenceNo The partner reference number for tracking
     * @param partnerServiceId The partner service ID
     * @param customerNo The customer number
     * @param virtualAccountNo The virtual account number
     * @param additionalInfo Additional information for the request
     */
    public BillInquiryRequest(String partnerReferenceNo, String partnerServiceId, 
                             String customerNo, String virtualAccountNo, 
                             AdditionalInfo additionalInfo) {
        this.partnerReferenceNo = partnerReferenceNo;
        this.partnerServiceId = partnerServiceId;
        this.customerNo = customerNo;
        this.virtualAccountNo = virtualAccountNo;
        this.additionalInfo = additionalInfo;
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
    public BillInquiryRequest setPartnerReferenceNo(String partnerReferenceNo) {
        this.partnerReferenceNo = partnerReferenceNo;
        return this;
    }

    /**
     * Gets the partner service ID.
     *
     * @return The partner service ID
     */
    public String getPartnerServiceId() {
        return partnerServiceId;
    }

    /**
     * Sets the partner service ID.
     *
     * @param partnerServiceId The partner service ID
     * @return This request object
     */
    public BillInquiryRequest setPartnerServiceId(String partnerServiceId) {
        this.partnerServiceId = partnerServiceId;
        return this;
    }

    /**
     * Gets the customer number.
     *
     * @return The customer number
     */
    public String getCustomerNo() {
        return customerNo;
    }

    /**
     * Sets the customer number.
     *
     * @param customerNo The customer number
     * @return This request object
     */
    public BillInquiryRequest setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
        return this;
    }

    /**
     * Gets the virtual account number.
     *
     * @return The virtual account number
     */
    public String getVirtualAccountNo() {
        return virtualAccountNo;
    }

    /**
     * Sets the virtual account number.
     *
     * @param virtualAccountNo The virtual account number
     * @return This request object
     */
    public BillInquiryRequest setVirtualAccountNo(String virtualAccountNo) {
        this.virtualAccountNo = virtualAccountNo;
        return this;
    }

    /**
     * Gets the additional information.
     *
     * @return The additional information
     */
    public AdditionalInfo getAdditionalInfo() {
        return additionalInfo;
    }

    /**
     * Sets the additional information.
     *
     * @param additionalInfo The additional information
     * @return This request object
     */
    public BillInquiryRequest setAdditionalInfo(AdditionalInfo additionalInfo) {
        this.additionalInfo = additionalInfo;
        return this;
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
            return "Bill Inquiry{error: \"Failed to convert to JSON: " + e.getMessage() + "\"}";
        }
    }

    /**
     * Inner class for additional information in the bill inquiry request.
     */
    public static class AdditionalInfo {

        @JsonProperty("billerCode")
        private String billerCode;

        @JsonProperty("sourceAccount")
        private String sourceAccount;

        /**
         * Default constructor for Jackson deserialization.
         */
        public AdditionalInfo() {
        }

        /**
         * Constructor with parameters.
         *
         * @param billerCode The biller code
         * @param sourceAccount The source account
         */
        public AdditionalInfo(String billerCode, String sourceAccount) {
            this.billerCode = billerCode;
            this.sourceAccount = sourceAccount;
        }

        /**
         * Gets the biller code.
         *
         * @return The biller code
         */
        public String getBillerCode() {
            return billerCode;
        }

        /**
         * Sets the biller code.
         *
         * @param billerCode The biller code
         * @return This additional info object
         */
        public AdditionalInfo setBillerCode(String billerCode) {
            this.billerCode = billerCode;
            return this;
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

        @Override
        public String toString() {
            return "AdditionalInfo{" +
                    "billerCode='" + billerCode + '\'' +
                    ", sourceAccount='" + sourceAccount + '\'' +
                    '}';
        }
    }
}
