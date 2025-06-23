package id.co.faspay.snap.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Objects;

/**
 * Request model for the Transfer Status API.
 * This class represents the request payload for checking transfer status.
 */
public class StatusTransferRequest {

    @JsonProperty("originalPartnerReferenceNo")
    private String originalPartnerReferenceNo;

    @JsonProperty("originalReferenceNo")
    private String originalReferenceNo;

    @JsonProperty("serviceCode")
    private String serviceCode;

    /**
     * Default constructor for Jackson deserialization.
     */
    public StatusTransferRequest() {
    }

    /**
     * Creates a new status transfer request with the specified parameters.
     *
     * @param originalPartnerReferenceNo The original partner reference number
     * @param originalReferenceNo The original reference number
     * @param serviceCode The service code for the transfer
     */
    public StatusTransferRequest(String originalPartnerReferenceNo, String originalReferenceNo, String serviceCode) {
        this.originalPartnerReferenceNo = Objects.requireNonNull(originalPartnerReferenceNo, "originalPartnerReferenceNo must not be null");
        this.originalReferenceNo = Objects.requireNonNull(originalReferenceNo, "originalReferenceNo must not be null");
        this.serviceCode = Objects.requireNonNull(serviceCode, "serviceCode must not be null");
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
     * @return This request object
     */
    public StatusTransferRequest setOriginalPartnerReferenceNo(String originalPartnerReferenceNo) {
        this.originalPartnerReferenceNo = originalPartnerReferenceNo;
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
     * @return This request object
     */
    public StatusTransferRequest setOriginalReferenceNo(String originalReferenceNo) {
        this.originalReferenceNo = originalReferenceNo;
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
     * @return This request object
     */
    public StatusTransferRequest setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatusTransferRequest that = (StatusTransferRequest) o;
        return Objects.equals(originalPartnerReferenceNo, that.originalPartnerReferenceNo) &&
                Objects.equals(originalReferenceNo, that.originalReferenceNo) &&
                Objects.equals(serviceCode, that.serviceCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(originalPartnerReferenceNo, originalReferenceNo, serviceCode);
    }

    @Override
    public String toString() {
        try {
            return new ObjectMapper()
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(this);
        } catch (com.fasterxml.jackson.core.JsonProcessingException e) {
            return "StatusTransferRequest{error: \"Failed to convert to JSON: " + e.getMessage() + "\"}";
        }
    }
}