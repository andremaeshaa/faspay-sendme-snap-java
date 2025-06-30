package id.co.faspay.snap.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Objects;

public class BillPaymentResponse {
    
    @JsonProperty("responseCode")
    private String responseCode;
    
    @JsonProperty("responseMessage")
    private String responseMessage;
    
    @JsonProperty("virtualAccountData")
    private VirtualAccountData virtualAccountData;
    
    @JsonProperty("additionalInfo")
    private AdditionalInfo additionalInfo;
    
    // Constructors
    public BillPaymentResponse() {}
    
    public BillPaymentResponse(String responseCode, String responseMessage, 
                              VirtualAccountData virtualAccountData, AdditionalInfo additionalInfo) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        this.virtualAccountData = virtualAccountData;
        this.additionalInfo = additionalInfo;
    }
    
    // Getters and Setters
    public boolean isSuccess() {
        return Objects.equals(responseCode, "2003300");
    }

    public String getResponseCode() {
        return responseCode;
    }
    
    public BillPaymentResponse setResponseCode(String responseCode) {
        this.responseCode = responseCode;
        return this;
    }
    
    public String getResponseMessage() {
        return responseMessage;
    }
    
    public BillPaymentResponse setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
        return this;
    }
    
    public VirtualAccountData getVirtualAccountData() {
        return virtualAccountData;
    }
    
    public BillPaymentResponse setVirtualAccountData(VirtualAccountData virtualAccountData) {
        this.virtualAccountData = virtualAccountData;
        return this;
    }
    
    public AdditionalInfo getAdditionalInfo() {
        return additionalInfo;
    }
    
    public BillPaymentResponse setAdditionalInfo(AdditionalInfo additionalInfo) {
        this.additionalInfo = additionalInfo;
        return this;
    }
    
    // Nested classes
    public static class VirtualAccountData {
        @JsonProperty("partnerReferenceNo")
        private String partnerReferenceNo;
        
        @JsonProperty("referenceNo")
        private String referenceNo;
        
        @JsonProperty("partnerServiceId")
        private String partnerServiceId;
        
        @JsonProperty("customerNo")
        private String customerNo;
        
        @JsonProperty("virtualAccountNo")
        private String virtualAccountNo;
        
        @JsonProperty("virtualAccountName")
        private String virtualAccountName;
        
        @JsonProperty("sourceAccount")
        private String sourceAccount;
        
        @JsonProperty("paidAmount")
        private PaidAmount paidAmount;
        
        @JsonProperty("trxDateTime")
        private String trxDateTime;
        
        // Constructors
        public VirtualAccountData() {}
        
        // Getters and Setters
        public String getPartnerReferenceNo() {
            return partnerReferenceNo;
        }
        
        public VirtualAccountData setPartnerReferenceNo(String partnerReferenceNo) {
            this.partnerReferenceNo = partnerReferenceNo;
            return this;
        }
        
        public String getReferenceNo() {
            return referenceNo;
        }
        
        public VirtualAccountData setReferenceNo(String referenceNo) {
            this.referenceNo = referenceNo;
            return this;
        }
        
        public String getPartnerServiceId() {
            return partnerServiceId;
        }
        
        public VirtualAccountData setPartnerServiceId(String partnerServiceId) {
            this.partnerServiceId = partnerServiceId;
            return this;
        }
        
        public String getCustomerNo() {
            return customerNo;
        }
        
        public VirtualAccountData setCustomerNo(String customerNo) {
            this.customerNo = customerNo;
            return this;
        }
        
        public String getVirtualAccountNo() {
            return virtualAccountNo;
        }
        
        public VirtualAccountData setVirtualAccountNo(String virtualAccountNo) {
            this.virtualAccountNo = virtualAccountNo;
            return this;
        }
        
        public String getVirtualAccountName() {
            return virtualAccountName;
        }
        
        public VirtualAccountData setVirtualAccountName(String virtualAccountName) {
            this.virtualAccountName = virtualAccountName;
            return this;
        }
        
        public String getSourceAccount() {
            return sourceAccount;
        }
        
        public VirtualAccountData setSourceAccount(String sourceAccount) {
            this.sourceAccount = sourceAccount;
            return this;
        }
        
        public PaidAmount getPaidAmount() {
            return paidAmount;
        }
        
        public VirtualAccountData setPaidAmount(PaidAmount paidAmount) {
            this.paidAmount = paidAmount;
            return this;
        }
        
        public String getTrxDateTime() {
            return trxDateTime;
        }
        
        public VirtualAccountData setTrxDateTime(String trxDateTime) {
            this.trxDateTime = trxDateTime;
            return this;
        }
    }
    
    public static class PaidAmount {
        @JsonProperty("value")
        private String value;
        
        @JsonProperty("currency")
        private String currency;
        
        // Constructors
        public PaidAmount() {}
        
        public PaidAmount(String value, String currency) {
            this.value = value;
            this.currency = currency;
        }
        
        // Getters and Setters
        public String getValue() {
            return value;
        }
        
        public PaidAmount setValue(String value) {
            this.value = value;
            return this;
        }
        
        public String getCurrency() {
            return currency;
        }
        
        public PaidAmount setCurrency(String currency) {
            this.currency = currency;
            return this;
        }
    }
    
    public static class AdditionalInfo {
        @JsonProperty("billerCode")
        private String billerCode;
        
        @JsonProperty("instructDate")
        private String instructDate;
        
        @JsonProperty("callbackUrl")
        private String callbackUrl;
        
        @JsonProperty("status")
        private String status;
        
        @JsonProperty("message")
        private String message;
        
        // Constructors
        public AdditionalInfo() {}
        
        // Getters and Setters
        public String getBillerCode() {
            return billerCode;
        }
        
        public AdditionalInfo setBillerCode(String billerCode) {
            this.billerCode = billerCode;
            return this;
        }
        
        public String getInstructDate() {
            return instructDate;
        }
        
        public AdditionalInfo setInstructDate(String instructDate) {
            this.instructDate = instructDate;
            return this;
        }
        
        public String getCallbackUrl() {
            return callbackUrl;
        }
        
        public AdditionalInfo setCallbackUrl(String callbackUrl) {
            this.callbackUrl = callbackUrl;
            return this;
        }
        
        public String getStatus() {
            return status;
        }
        
        public AdditionalInfo setStatus(String status) {
            this.status = status;
            return this;
        }
        
        public String getMessage() {
            return message;
        }
        
        public AdditionalInfo setMessage(String message) {
            this.message = message;
            return this;
        }
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
            return "BillPayment{error: \"Failed to convert to JSON: " + e.getMessage() + "\"}";
        }
    }
}