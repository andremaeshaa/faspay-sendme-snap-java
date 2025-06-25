package id.co.faspay.snap.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

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
    
    // Default constructor
    public BillInquiryRequest() {
    }
    
    // Constructor with all parameters
    public BillInquiryRequest(String partnerReferenceNo, String partnerServiceId, 
                             String customerNo, String virtualAccountNo, 
                             AdditionalInfo additionalInfo) {
        this.partnerReferenceNo = partnerReferenceNo;
        this.partnerServiceId = partnerServiceId;
        this.customerNo = customerNo;
        this.virtualAccountNo = virtualAccountNo;
        this.additionalInfo = additionalInfo;
    }
    
    // Getters and Setters
    public String getPartnerReferenceNo() {
        return partnerReferenceNo;
    }
    
    public BillInquiryRequest setPartnerReferenceNo(String partnerReferenceNo) {
        this.partnerReferenceNo = partnerReferenceNo;
        return this;
    }
    
    public String getPartnerServiceId() {
        return partnerServiceId;
    }
    
    public BillInquiryRequest setPartnerServiceId(String partnerServiceId) {
        this.partnerServiceId = partnerServiceId;
        return this;
    }
    
    public String getCustomerNo() {
        return customerNo;
    }
    
    public BillInquiryRequest setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
        return this;
    }
    
    public String getVirtualAccountNo() {
        return virtualAccountNo;
    }
    
    public BillInquiryRequest setVirtualAccountNo(String virtualAccountNo) {
        this.virtualAccountNo = virtualAccountNo;
        return this;
    }
    
    public AdditionalInfo getAdditionalInfo() {
        return additionalInfo;
    }
    
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
            return "CustomerTopupRequest{error: \"Failed to convert to JSON: " + e.getMessage() + "\"}";
        }
    }
    
    // Inner class for additionalInfo
    public static class AdditionalInfo {
        
        @JsonProperty("billerCode")
        private String billerCode;
        
        @JsonProperty("sourceAccount")
        private String sourceAccount;
        
        // Default constructor
        public AdditionalInfo() {
        }
        
        // Constructor with parameters
        public AdditionalInfo(String billerCode, String sourceAccount) {
            this.billerCode = billerCode;
            this.sourceAccount = sourceAccount;
        }
        
        // Getters and Setters
        public String getBillerCode() {
            return billerCode;
        }
        
        public AdditionalInfo setBillerCode(String billerCode) {
            this.billerCode = billerCode;
            return this;
        }
        
        public String getSourceAccount() {
            return sourceAccount;
        }
        
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