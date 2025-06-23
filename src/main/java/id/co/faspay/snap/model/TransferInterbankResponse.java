package id.co.faspay.snap.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Response model for the Transfer Interbank API.
 * This class represents the response payload for transferring money between banks.
 */
public class TransferInterbankResponse {

    @JsonProperty("responseCode")
    private String responseCode;

    @JsonProperty("responseMessage")
    private String responseMessage;

    @JsonProperty("referenceNo")
    private String referenceNumber;

    @JsonProperty("partnerReferenceNo")
    private String partnerReferenceNumber;

    @JsonProperty("amount")
    private Amount amount;

    @JsonProperty("beneficiaryAccountNo")
    private String beneficiaryAccountNumber;

    @JsonProperty("beneficiaryBankCode")
    private String beneficiaryBankCode;

    @JsonProperty("sourceAccountNo")
    private String sourceAccountNumber;

    @JsonProperty("originatorInfos")
    private Map<String, String> originatorInfos;

    @JsonProperty("additionalInfo")
    private Map<String, String> additionalInfo;

    /**
     * Default constructor for Jackson deserialization.
     */
    public TransferInterbankResponse() {
        this.originatorInfos = new HashMap<>();
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
    public TransferInterbankResponse setResponseCode(String responseCode) {
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
    public TransferInterbankResponse setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
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
    public TransferInterbankResponse setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
        return this;
    }

    /**
     * Gets the partner reference number.
     *
     * @return The partner reference number
     */
    public String getPartnerReferenceNumber() {
        return partnerReferenceNumber;
    }

    /**
     * Sets the partner reference number.
     *
     * @param partnerReferenceNumber The partner reference number
     * @return This response object
     */
    public TransferInterbankResponse setPartnerReferenceNumber(String partnerReferenceNumber) {
        this.partnerReferenceNumber = partnerReferenceNumber;
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
    public TransferInterbankResponse setAmount(Amount amount) {
        this.amount = amount;
        return this;
    }

    /**
     * Gets the beneficiary account number.
     *
     * @return The beneficiary account number
     */
    public String getBeneficiaryAccountNumber() {
        return beneficiaryAccountNumber;
    }

    /**
     * Sets the beneficiary account number.
     *
     * @param beneficiaryAccountNumber The beneficiary account number
     * @return This response object
     */
    public TransferInterbankResponse setBeneficiaryAccountNumber(String beneficiaryAccountNumber) {
        this.beneficiaryAccountNumber = beneficiaryAccountNumber;
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
    public TransferInterbankResponse setBeneficiaryBankCode(String beneficiaryBankCode) {
        this.beneficiaryBankCode = beneficiaryBankCode;
        return this;
    }

    /**
     * Gets the source account number.
     *
     * @return The source account number
     */
    public String getSourceAccountNumber() {
        return sourceAccountNumber;
    }

    /**
     * Sets the source account number.
     *
     * @param sourceAccountNumber The source account number
     * @return This response object
     */
    public TransferInterbankResponse setSourceAccountNumber(String sourceAccountNumber) {
        this.sourceAccountNumber = sourceAccountNumber;
        return this;
    }

    public Map<String, String> getOriginatorInfos() {
        return originatorInfos;
    }

    /**
     * Gets the additional info.
     *
     * @return The additional info
     */
    public Map<String, String> getAdditionalInfo() {
        return additionalInfo;
    }

    public TransferInterbankResponse setOriginatorInfos(Map<String, String> originatorInfos) {
        this.originatorInfos = originatorInfos;
        return this;
    }

    /**
     * Sets the additional info.
     *
     * @param additionalInfo The additional info
     * @return This response object
     */
    public TransferInterbankResponse setAdditionalInfo(Map<String, String> additionalInfo) {
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
    public TransferInterbankResponse addAdditionalInfo(String key, String value) {
        this.additionalInfo.put(key, value);
        return this;
    }

    /**
     * Gets the beneficiary account name from the additional info.
     *
     * @return The beneficiary account name
     */
    public String getBeneficiaryAccountName() {
        return this.additionalInfo.get("beneficiaryAccountName");
    }

    public TransferInterbankResponse setOriginatorCustomerName(String originatorCustomerName) {
        this.originatorInfos.put("originatorCustomerName", originatorCustomerName);
        return this;
    }

    public TransferInterbankResponse setOriginatorCustomerNo(String originatorCustomerNo) {
        this.originatorInfos.put("originatorCustomerNo", originatorCustomerNo);
        return this;
    }

    public TransferInterbankResponse setOriginatorBankCode(String originatorBankCode) {
        this.originatorInfos.put("originatorBankCode", originatorBankCode);
        return this;
    }

    public String getOriginatorCustomerName() {
        return this.originatorInfos.get("originatorCustomerName");
    }

    public String getOriginatorCustomerNo() {
        return this.originatorInfos.get("originatorCustomerNo");
    }

    public String getOriginatorBankCode() {
        return this.originatorInfos.get("originatorBankCode");
    }

    /**
     * Sets the beneficiary account name in the additional info.
     *
     * @param beneficiaryAccountName The beneficiary account name
     * @return This response object
     */
    public TransferInterbankResponse setBeneficiaryAccountName(String beneficiaryAccountName) {
        this.additionalInfo.put("beneficiaryAccountName", beneficiaryAccountName);
        return this;
    }

    /**
     * Gets the beneficiary bank name from the additional info.
     *
     * @return The beneficiary bank name
     */
    public String getBeneficiaryBankName() {
        return this.additionalInfo.get("beneficiaryBankName");
    }

    /**
     * Sets the beneficiary bank name in the additional info.
     *
     * @param beneficiaryBankName The beneficiary bank name
     * @return This response object
     */
    public TransferInterbankResponse setBeneficiaryBankName(String beneficiaryBankName) {
        this.additionalInfo.put("beneficiaryBankName", beneficiaryBankName);
        return this;
    }

    /**
     * Gets the instruct date from the additional info.
     *
     * @return The instruct date
     */
    public String getInstructDate() {
        return this.additionalInfo.get("instructDate");
    }

    /**
     * Sets the instruct date in the additional info.
     *
     * @param instructDate The instruct date
     * @return This response object
     */
    public TransferInterbankResponse setInstructDate(String instructDate) {
        this.additionalInfo.put("instructDate", instructDate);
        return this;
    }

    /**
     * Gets the transaction description from the additional info.
     *
     * @return The transaction description
     */
    public String getTransactionDescription() {
        return this.additionalInfo.get("transactionDescription");
    }

    /**
     * Sets the transaction description in the additional info.
     *
     * @param transactionDescription The transaction description
     * @return This response object
     */
    public TransferInterbankResponse setTransactionDescription(String transactionDescription) {
        this.additionalInfo.put("transactionDescription", transactionDescription);
        return this;
    }

    /**
     * Gets the callback URL from the additional info.
     *
     * @return The callback URL
     */
    public String getCallbackUrl() {
        return this.additionalInfo.get("callbackUrl");
    }

    /**
     * Sets the callback URL in the additional info.
     *
     * @param callbackUrl The callback URL
     * @return This response object
     */
    public TransferInterbankResponse setCallbackUrl(String callbackUrl) {
        this.additionalInfo.put("callbackUrl", callbackUrl);
        return this;
    }

    /**
     * Gets the latest transaction status from the additional info.
     *
     * @return The latest transaction status
     */
    public String getLatestTransactionStatus() {
        return this.additionalInfo.get("latestTransactionStatus");
    }

    /**
     * Sets the latest transaction status in the additional info.
     *
     * @param latestTransactionStatus The latest transaction status
     * @return This response object
     */
    public TransferInterbankResponse setLatestTransactionStatus(String latestTransactionStatus) {
        this.additionalInfo.put("latestTransactionStatus", latestTransactionStatus);
        return this;
    }

    /**
     * Gets the transaction status description from the additional info.
     *
     * @return The transaction status description
     */
    public String getTransactionStatusDesc() {
        return this.additionalInfo.get("transactionStatusDesc");
    }

    /**
     * Sets the transaction status description in the additional info.
     *
     * @param transactionStatusDesc The transaction status description
     * @return This response object
     */
    public TransferInterbankResponse setTransactionStatusDesc(String transactionStatusDesc) {
        this.additionalInfo.put("transactionStatusDesc", transactionStatusDesc);
        return this;
    }

    /**
     * Checks if the response indicates a successful operation.
     *
     * @return true if the response code indicates success, false otherwise
     */
    public boolean isSuccess() {
        return Objects.equals(responseCode, "2003600");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransferInterbankResponse that = (TransferInterbankResponse) o;
        return Objects.equals(responseCode, that.responseCode) &&
                Objects.equals(responseMessage, that.responseMessage) &&
                Objects.equals(referenceNumber, that.referenceNumber) &&
                Objects.equals(partnerReferenceNumber, that.partnerReferenceNumber) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(beneficiaryAccountNumber, that.beneficiaryAccountNumber) &&
                Objects.equals(beneficiaryBankCode, that.beneficiaryBankCode) &&
                Objects.equals(sourceAccountNumber, that.sourceAccountNumber) &&
                Objects.equals(originatorInfos, that.originatorInfos) &&
                Objects.equals(additionalInfo, that.additionalInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(responseCode, responseMessage, referenceNumber, partnerReferenceNumber,
                amount, beneficiaryAccountNumber, beneficiaryBankCode, sourceAccountNumber, originatorInfos, additionalInfo);
    }

    @Override
    public String toString() {
        try {
            return new ObjectMapper()
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(this);
        } catch (com.fasterxml.jackson.core.JsonProcessingException e) {
            return "TransferInterbankResponse{error: \"Failed to convert to JSON: " + e.getMessage() + "\"}";
        }
    }
}