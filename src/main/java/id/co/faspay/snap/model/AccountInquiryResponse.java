package id.co.faspay.snap.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Response model for the Account Inquiry API.
 * This class represents the response payload from inquiring about a bank account.
 */
public class AccountInquiryResponse {

    @JsonProperty("additionalInfo.status")
    private String status;

    @JsonProperty("responseCode")
    private String responseCode;

    @JsonProperty("responseMessage")
    private String responseMessage;

    @JsonProperty("referenceNo")
    private String referenceNo;

    @JsonProperty("partnerReferenceNo")
    private String partnerReferenceNumber;

    @JsonProperty("beneficiaryAccountName")
    private String accountHolderName;

    @JsonProperty("beneficiaryAccountNo")
    private String accountNumber;

    @JsonProperty("beneficiaryBankCode")
    private String bankCode;

    @JsonProperty("beneficiaryBankName")
    private String bankName;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("additionalInfo")
    private Map<String, String> additionalInfo;

    /**
     * Default constructor for Jackson deserialization.
     */
    public AccountInquiryResponse() {
        this.additionalInfo = new HashMap<>();
    }

    /**
     * Gets the status from additionalInfo.
     *
     * @return The status from additionalInfo, or null if not present
     */
    public String getStatus() {
        return additionalInfo != null ? additionalInfo.get("status") : null;
    }

    /**
     * Gets the message from additionalInfo.
     *
     * @return The message from additionalInfo, or null if not present
     */
    public String getMessage() {
        return additionalInfo != null ? additionalInfo.get("message") : null;
    }


    /**
     * Sets the status of the inquiry.
     *
     * @param status The status
     * @return This response object
     */
    public AccountInquiryResponse setStatus(String status) {
        this.status = status;
        return this;
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
    public AccountInquiryResponse setResponseCode(String responseCode) {
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
    public AccountInquiryResponse setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
        return this;
    }

    /**
     * Gets the bank code.
     *
     * @return The bank code
     */
    public String getBankCode() {
        return bankCode;
    }

    /**
     * Sets the bank code.
     *
     * @param bankCode The bank code
     * @return This response object
     */
    public AccountInquiryResponse setBankCode(String bankCode) {
        this.bankCode = bankCode;
        return this;
    }

    /**
     * Gets the account number.
     *
     * @return The account number
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * Sets the account number.
     *
     * @param accountNumber The account number
     * @return This response object
     */
    public AccountInquiryResponse setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }

    /**
     * Gets the account holder name.
     *
     * @return The account holder name
     */
    public String getAccountHolderName() {
        return accountHolderName;
    }

    /**
     * Sets the account holder name.
     *
     * @param accountHolderName The account holder name
     * @return This response object
     */
    public AccountInquiryResponse setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
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
    public AccountInquiryResponse setPartnerReferenceNumber(String partnerReferenceNumber) {
        this.partnerReferenceNumber = partnerReferenceNumber;
        return this;
    }

    /**
     * Gets the reference number.
     *
     * @return The reference number
     */
    public String getReferenceNo() {
        return referenceNo;
    }

    /**
     * Sets the reference number.
     *
     * @param referenceNo The reference number
     * @return This response object
     */
    public AccountInquiryResponse setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
        return this;
    }

    /**
     * Gets the Faspay reference number.
     * @deprecated Use {@link #getReferenceNo()} instead.
     *
     * @return The reference number
     */
    @Deprecated
    public String getFaspayReferenceNumber() {
        return referenceNo;
    }

    /**
     * Sets the Faspay reference number.
     * @deprecated Use {@link #setReferenceNo(String)} instead.
     *
     * @param faspayReferenceNumber The reference number
     * @return This response object
     */
    @Deprecated
    public AccountInquiryResponse setFaspayReferenceNumber(String faspayReferenceNumber) {
        this.referenceNo = faspayReferenceNumber;
        return this;
    }

    /**
     * Gets the bank name.
     *
     * @return The bank name
     */
    public String getBankName() {
        return bankName;
    }

    /**
     * Sets the bank name.
     *
     * @param bankName The bank name
     * @return This response object
     */
    public AccountInquiryResponse setBankName(String bankName) {
        this.bankName = bankName;
        return this;
    }

    /**
     * Gets the currency.
     *
     * @return The currency
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * Sets the currency.
     *
     * @param currency The currency
     * @return This response object
     */
    public AccountInquiryResponse setCurrency(String currency) {
        this.currency = currency;
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
    public AccountInquiryResponse setAdditionalInfo(Map<String, String> additionalInfo) {
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
    public AccountInquiryResponse addAdditionalInfo(String key, String value) {
        this.additionalInfo.put(key, value);
        return this;
    }

    /**
     * Checks if the inquiry was successful.
     *
     * @return True if the inquiry was successful, false otherwise
     */
    public boolean isSuccess() {
        return Objects.equals(responseCode, "2001600");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountInquiryResponse that = (AccountInquiryResponse) o;
        return Objects.equals(status, that.status) &&
                Objects.equals(responseCode, that.responseCode) &&
                Objects.equals(responseMessage, that.responseMessage) &&
                Objects.equals(referenceNo, that.referenceNo) &&
                Objects.equals(bankCode, that.bankCode) &&
                Objects.equals(bankName, that.bankName) &&
                Objects.equals(accountNumber, that.accountNumber) &&
                Objects.equals(accountHolderName, that.accountHolderName) &&
                Objects.equals(partnerReferenceNumber, that.partnerReferenceNumber) &&
                Objects.equals(currency, that.currency) &&
                Objects.equals(additionalInfo, that.additionalInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, responseCode, responseMessage, referenceNo, bankCode, bankName,
                accountNumber, accountHolderName, partnerReferenceNumber, currency, additionalInfo);
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
            return "AccountInquiry{error: \"Failed to convert to JSON: " + e.getMessage() + "\"}";
        }
    }
}
