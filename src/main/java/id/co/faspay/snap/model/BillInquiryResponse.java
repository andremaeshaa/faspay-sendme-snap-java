package id.co.faspay.snap.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;
import java.util.Objects;

public class BillInquiryResponse {

    @JsonProperty("responseCode")
    private String responseCode;

    @JsonProperty("responseMessage")
    private String responseMessage;

    @JsonProperty("virtualAccountData")
    private VirtualAccountData virtualAccountData;

    @JsonProperty("additionalInfo")
    private Map<String, String> additionalInfo;

    /**
     * Default constructor for Jackson deserialization.
     */
    public BillInquiryResponse() {
    }

    /**
     * Gets the response code.
     *
     * @return The response code
     */
    public String getResponseCode() {
        return responseCode;
    }

    public boolean isSuccess() {
        return Objects.equals(responseCode, "2003200");
    }

    /**
     * Sets the response code.
     *
     * @param responseCode The response code
     * @return This response object
     */
    public BillInquiryResponse setResponseCode(String responseCode) {
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
    public BillInquiryResponse setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
        return this;
    }

    /**
     * Gets the virtual account data.
     *
     * @return The virtual account data
     */
    public VirtualAccountData getVirtualAccountData() {
        return virtualAccountData;
    }

    /**
     * Sets the virtual account data.
     *
     * @param virtualAccountData The virtual account data
     * @return This response object
     */
    public BillInquiryResponse setVirtualAccountData(VirtualAccountData virtualAccountData) {
        this.virtualAccountData = virtualAccountData;
        return this;
    }

    /**
     * Gets the additional info.
     *
     * @return The additional info
     */
    public Map<String, String> getAdditionalInfo() {
        return additionalInfo;
    }

    /**
     * Sets the additional info.
     *
     * @param additionalInfo The additional info
     * @return This response object
     */
    public BillInquiryResponse setAdditionalInfo(Map<String, String> additionalInfo) {
        this.additionalInfo = additionalInfo;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BillInquiryResponse that = (BillInquiryResponse) o;
        return Objects.equals(responseCode, that.responseCode) &&
                Objects.equals(responseMessage, that.responseMessage) &&
                Objects.equals(virtualAccountData, that.virtualAccountData) &&
                Objects.equals(additionalInfo, that.additionalInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(responseCode, responseMessage, virtualAccountData, additionalInfo);
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
            return "BillInquiry{error: \"Failed to convert to JSON: " + e.getMessage() + "\"}";
        }
    }

    /**
     * Represents virtual account data in the bill inquiry response.
     */
    public static class VirtualAccountData {

        @JsonProperty("partnerServiceId")
        private String partnerServiceId;

        @JsonProperty("customerNo") 
        private String customerNo;

        @JsonProperty("virtualAccountNo")
        private String virtualAccountNo;

        @JsonProperty("virtualAccountName")
        private String virtualAccountName;

        @JsonProperty("totalAmount")
        private Amount totalAmount;

        @JsonProperty("virtualAccountTrxType")
        private String virtualAccountTrxType;

        @JsonProperty("partnerReferenceNo")
        private String partnerReferenceNo;

        /**
         * Default constructor for Jackson deserialization.
         */
        public VirtualAccountData() {
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
         * @return This virtual account data object
         */
        public VirtualAccountData setPartnerServiceId(String partnerServiceId) {
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
         * @return This virtual account data object
         */
        public VirtualAccountData setCustomerNo(String customerNo) {
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
         * @return This virtual account data object
         */
        public VirtualAccountData setVirtualAccountNo(String virtualAccountNo) {
            this.virtualAccountNo = virtualAccountNo;
            return this;
        }

        /**
         * Gets the virtual account name.
         *
         * @return The virtual account name
         */
        public String getVirtualAccountName() {
            return virtualAccountName;
        }

        /**
         * Sets the virtual account name.
         *
         * @param virtualAccountName The virtual account name
         * @return This virtual account data object
         */
        public VirtualAccountData setVirtualAccountName(String virtualAccountName) {
            this.virtualAccountName = virtualAccountName;
            return this;
        }

        /**
         * Gets the total amount.
         *
         * @return The total amount
         */
        public Amount getTotalAmount() {
            return totalAmount;
        }

        /**
         * Sets the total amount.
         *
         * @param totalAmount The total amount
         * @return This virtual account data object
         */
        public VirtualAccountData setTotalAmount(Amount totalAmount) {
            this.totalAmount = totalAmount;
            return this;
        }

        /**
         * Gets the virtual account transaction type.
         *
         * @return The virtual account transaction type
         */
        public String getVirtualAccountTrxType() {
            return virtualAccountTrxType;
        }

        /**
         * Sets the virtual account transaction type.
         *
         * @param virtualAccountTrxType The virtual account transaction type
         * @return This virtual account data object
         */
        public VirtualAccountData setVirtualAccountTrxType(String virtualAccountTrxType) {
            this.virtualAccountTrxType = virtualAccountTrxType;
            return this;
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
         * @return This virtual account data object
         */
        public VirtualAccountData setPartnerReferenceNo(String partnerReferenceNo) {
            this.partnerReferenceNo = partnerReferenceNo;
            return this;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            VirtualAccountData that = (VirtualAccountData) o;
            return Objects.equals(partnerServiceId, that.partnerServiceId) &&
                    Objects.equals(customerNo, that.customerNo) &&
                    Objects.equals(virtualAccountNo, that.virtualAccountNo) &&
                    Objects.equals(virtualAccountName, that.virtualAccountName) &&
                    Objects.equals(totalAmount, that.totalAmount) &&
                    Objects.equals(virtualAccountTrxType, that.virtualAccountTrxType) &&
                    Objects.equals(partnerReferenceNo, that.partnerReferenceNo);
        }

        @Override
        public int hashCode() {
            return Objects.hash(partnerServiceId, customerNo, virtualAccountNo, 
                              virtualAccountName, totalAmount, virtualAccountTrxType, partnerReferenceNo);
        }

        @Override
        public String toString() {
            return "VirtualAccountData{" +
                    "partnerServiceId='" + partnerServiceId + '\'' +
                    ", customerNo='" + customerNo + '\'' +
                    ", virtualAccountNo='" + virtualAccountNo + '\'' +
                    ", virtualAccountName='" + virtualAccountName + '\'' +
                    ", totalAmount=" + totalAmount +
                    ", virtualAccountTrxType='" + virtualAccountTrxType + '\'' +
                    ", partnerReferenceNo='" + partnerReferenceNo + '\'' +
                    '}';
        }
    }
}