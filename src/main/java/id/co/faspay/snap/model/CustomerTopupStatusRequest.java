package id.co.faspay.snap.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomerTopupStatusRequest {
    
    @JsonProperty("originalPartnerReferenceNo")
    private String originalPartnerReferenceNo;
    
    @JsonProperty("originalReferenceNo")
    private String originalReferenceNo;
    
    @JsonProperty("serviceCode")
    private String serviceCode;
    
    // Default constructor
    public CustomerTopupStatusRequest() {
    }
    
    // Constructor with all parameters
    public CustomerTopupStatusRequest(String originalPartnerReferenceNo, String originalReferenceNo, String serviceCode) {
        this.originalPartnerReferenceNo = originalPartnerReferenceNo;
        this.originalReferenceNo = originalReferenceNo;
        this.serviceCode = serviceCode;
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
     */
    public void setOriginalPartnerReferenceNo(String originalPartnerReferenceNo) {
        this.originalPartnerReferenceNo = originalPartnerReferenceNo;
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
     */
    public void setOriginalReferenceNo(String originalReferenceNo) {
        this.originalReferenceNo = originalReferenceNo;
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
     */
    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
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