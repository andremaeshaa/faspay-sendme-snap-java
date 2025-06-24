package id.co.faspay.snap.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BillPaymentRequest {
    
    @JsonProperty("partnerReferenceNo")
    private String partnerReferenceNo;
    
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
    
    @JsonProperty("additionalInfo")
    private AdditionalInfo additionalInfo;
    
    // Constructors
    public BillPaymentRequest() {}
    
    // Getters and Setters
    public String getPartnerReferenceNo() {
        return partnerReferenceNo;
    }
    
    public void setPartnerReferenceNo(String partnerReferenceNo) {
        this.partnerReferenceNo = partnerReferenceNo;
    }
    
    public String getPartnerServiceId() {
        return partnerServiceId;
    }
    
    public void setPartnerServiceId(String partnerServiceId) {
        this.partnerServiceId = partnerServiceId;
    }
    
    public String getCustomerNo() {
        return customerNo;
    }
    
    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
    }
    
    public String getVirtualAccountNo() {
        return virtualAccountNo;
    }
    
    public void setVirtualAccountNo(String virtualAccountNo) {
        this.virtualAccountNo = virtualAccountNo;
    }
    
    public String getVirtualAccountName() {
        return virtualAccountName;
    }
    
    public void setVirtualAccountName(String virtualAccountName) {
        this.virtualAccountName = virtualAccountName;
    }
    
    public String getSourceAccount() {
        return sourceAccount;
    }
    
    public void setSourceAccount(String sourceAccount) {
        this.sourceAccount = sourceAccount;
    }
    
    public PaidAmount getPaidAmount() {
        return paidAmount;
    }
    
    public void setPaidAmount(PaidAmount paidAmount) {
        this.paidAmount = paidAmount;
    }
    
    public String getTrxDateTime() {
        return trxDateTime;
    }
    
    public void setTrxDateTime(String trxDateTime) {
        this.trxDateTime = trxDateTime;
    }
    
    public AdditionalInfo getAdditionalInfo() {
        return additionalInfo;
    }
    
    public void setAdditionalInfo(AdditionalInfo additionalInfo) {
        this.additionalInfo = additionalInfo;
    }
    
    // Nested classes
    public static class PaidAmount {
        @JsonProperty("value")
        private String value;
        
        @JsonProperty("currency")
        private String currency;
        
        public PaidAmount() {}
        
        public String getValue() {
            return value;
        }
        
        public void setValue(String value) {
            this.value = value;
        }
        
        public String getCurrency() {
            return currency;
        }
        
        public void setCurrency(String currency) {
            this.currency = currency;
        }
    }
    
    public static class AdditionalInfo {
        @JsonProperty("billerCode")
        private String billerCode;
        
        @JsonProperty("instructDate")
        private String instructDate;
        
        @JsonProperty("callbackUrl")
        private String callbackUrl;
        
        public AdditionalInfo() {}
        
        public String getBillerCode() {
            return billerCode;
        }
        
        public void setBillerCode(String billerCode) {
            this.billerCode = billerCode;
        }
        
        public String getInstructDate() {
            return instructDate;
        }
        
        public void setInstructDate(String instructDate) {
            this.instructDate = instructDate;
        }
        
        public String getCallbackUrl() {
            return callbackUrl;
        }
        
        public void setCallbackUrl(String callbackUrl) {
            this.callbackUrl = callbackUrl;
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
            return "CustomerTopupRequest{error: \"Failed to convert to JSON: " + e.getMessage() + "\"}";
        }
    }
}