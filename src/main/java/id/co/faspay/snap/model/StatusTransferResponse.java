package id.co.faspay.snap.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Response model for the Transfer Status API.
 * This class represents the response payload from checking transfer status.
 */
public class StatusTransferResponse {

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

    @JsonProperty("transactionDate")
    private String transactionDate;

    @JsonProperty("amount")
    private Amount amount;

    @JsonProperty("beneficiaryAccountNo")
    private String beneficiaryAccountNo;

    @JsonProperty("beneficiaryBankCode")
    private String beneficiaryBankCode;

    @JsonProperty("referenceNumber")
    private String referenceNumber;

    @JsonProperty("sourceAccountNo")
    private String sourceAccountNo;

    @JsonProperty("latestTransactionStatus")
    private String latestTransactionStatus;

    @JsonProperty("transactionStatusDesc")
    private String transactionStatusDesc;

    @JsonProperty("additionalInfo")
    private Map<String, String> additionalInfo;

    /**
     * Default constructor for Jackson deserialization.
     */
    public StatusTransferResponse() {
        this.additionalInfo = new HashMap<>();
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
    public StatusTransferResponse setResponseCode(String responseCode) {
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
    public StatusTransferResponse setResponseMessage(String responseMessage) {
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
    public StatusTransferResponse setOriginalReferenceNo(String originalReferenceNo) {
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
    public StatusTransferResponse setOriginalPartnerReferenceNo(String originalPartnerReferenceNo) {
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
    public StatusTransferResponse setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
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
     * @return This response object
     */
    public StatusTransferResponse setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
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
    public StatusTransferResponse setAmount(Amount amount) {
        this.amount = amount;
        return this;
    }

    /**
     * Gets the beneficiary account number.
     *
     * @return The beneficiary account number
     */
    public String getBeneficiaryAccountNo() {
        return beneficiaryAccountNo;
    }

    /**
     * Sets the beneficiary account number.
     *
     * @param beneficiaryAccountNo The beneficiary account number
     * @return This response object
     */
    public StatusTransferResponse setBeneficiaryAccountNo(String beneficiaryAccountNo) {
        this.beneficiaryAccountNo = beneficiaryAccountNo;
        return this;
    }

    /**
     * Gets the beneficiary bank code.
     *
     * @return The beneficiary bank code
     */
    public String getBeneficiaryBankCode() {
        return beneficiaryBankCode;
    }

    /**
     * Sets the beneficiary bank code.
     *
     * @param beneficiaryBankCode The beneficiary bank code
     * @return This response object
     */
    public StatusTransferResponse setBeneficiaryBankCode(String beneficiaryBankCode) {
        this.beneficiaryBankCode = beneficiaryBankCode;
        return this;
    }

    /**
     * Gets the reference number.
     *
     * @return The reference number
     */
    public String getReferenceNumber() {
        return referenceNumber;
    }

    /**
     * Sets the reference number.
     *
     * @param referenceNumber The reference number
     * @return This response object
     */
    public StatusTransferResponse setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
        return this;
    }

    /**
     * Gets the source account number.
     *
     * @return The source account number
     */
    public String getSourceAccountNo() {
        return sourceAccountNo;
    }

    /**
     * Sets the source account number.
     *
     * @param sourceAccountNo The source account number
     * @return This response object
     */
    public StatusTransferResponse setSourceAccountNo(String sourceAccountNo) {
        this.sourceAccountNo = sourceAccountNo;
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
    public StatusTransferResponse setLatestTransactionStatus(String latestTransactionStatus) {
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
    public StatusTransferResponse setTransactionStatusDesc(String transactionStatusDesc) {
        this.transactionStatusDesc = transactionStatusDesc;
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
    public StatusTransferResponse setAdditionalInfo(Map<String, String> additionalInfo) {
        this.additionalInfo = additionalInfo;
        return this;
    }

    /**
     * Adds an entry to the additional info map.
     *
     * @param key The key
     * @param value The value
     * @return This response object
     */
    public StatusTransferResponse addAdditionalInfo(String key, String value) {
        this.additionalInfo.put(key, value);
        return this;
    }

    /**
     * Gets the beneficiary account name from additional info.
     *
     * @return The beneficiary account name
     */
    public String getBeneficiaryAccountName() {
        return additionalInfo != null ? additionalInfo.get("beneficiaryAccountName") : null;
    }

    /**
     * Gets the beneficiary bank name from additional info.
     *
     * @return The beneficiary bank name
     */
    public String getBeneficiaryBankName() {
        return additionalInfo != null ? additionalInfo.get("beneficiaryBankName") : null;
    }

    /**
     * Gets the transaction description from additional info.
     *
     * @return The transaction description
     */
    public String getTransactionDescription() {
        return additionalInfo != null ? additionalInfo.get("transactionDescription") : null;
    }

    /**
     * Gets the callback URL from additional info.
     *
     * @return The callback URL
     */
    public String getCallbackUrl() {
        return additionalInfo != null ? additionalInfo.get("callbackUrl") : null;
    }

    /**
     * Gets the transaction status date from additional info.
     *
     * @return The transaction status date
     */
    public String getTransactionStatusDate() {
        return additionalInfo != null ? additionalInfo.get("transactionStatusDate") : null;
    }

    /**
     * Checks if the transfer status inquiry was successful.
     *
     * @return True if the inquiry was successful, false otherwise
     */
    public boolean isSuccess() {
        return Objects.equals(responseCode, "2003600");
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
        StatusTransferResponse that = (StatusTransferResponse) o;
        return Objects.equals(responseCode, that.responseCode) &&
                Objects.equals(responseMessage, that.responseMessage) &&
                Objects.equals(originalReferenceNo, that.originalReferenceNo) &&
                Objects.equals(originalPartnerReferenceNo, that.originalPartnerReferenceNo) &&
                Objects.equals(serviceCode, that.serviceCode) &&
                Objects.equals(transactionDate, that.transactionDate) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(beneficiaryAccountNo, that.beneficiaryAccountNo) &&
                Objects.equals(beneficiaryBankCode, that.beneficiaryBankCode) &&
                Objects.equals(referenceNumber, that.referenceNumber) &&
                Objects.equals(sourceAccountNo, that.sourceAccountNo) &&
                Objects.equals(latestTransactionStatus, that.latestTransactionStatus) &&
                Objects.equals(transactionStatusDesc, that.transactionStatusDesc) &&
                Objects.equals(additionalInfo, that.additionalInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(responseCode, responseMessage, originalReferenceNo, originalPartnerReferenceNo,
                serviceCode, transactionDate, amount, beneficiaryAccountNo, beneficiaryBankCode,
                referenceNumber, sourceAccountNo, latestTransactionStatus, transactionStatusDesc, additionalInfo);
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
            return "StatusTransfer{error: \"Failed to convert to JSON: " + e.getMessage() + "\"}";
        }
    }
}