package id.co.faspay.snap.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Request model for the Account Inquiry API.
 * This class represents the request payload for inquiring about a bank account.
 */
public class AccountInquiryRequest {

    @JsonProperty("beneficiaryBankCode")
    private String bankCode;

    @JsonProperty("beneficiaryAccountNo")
    private String accountNumber;

    @JsonProperty("partnerReferenceNo")
    private String partnerReferenceNumber;

    @JsonProperty("additionalInfo")
    private Map<String, String> additionalInfo;

    @JsonIgnore
    private String sourceAccount;


    /**
     * Default constructor for Jackson deserialization.
     */
    public AccountInquiryRequest() {
        this.additionalInfo = new HashMap<>();
    }

    /**
     * Creates a new account inquiry request with the specified parameters.
     *
     * @param bankCode The bank code
     * @param accountNumber The account number to inquire about
     * @param partnerReferenceNumber The partner reference number for tracking
     */
    public AccountInquiryRequest(String bankCode, String accountNumber, String partnerReferenceNumber) {
        this.bankCode = Objects.requireNonNull(bankCode, "bankCode must not be null");
        this.accountNumber = Objects.requireNonNull(accountNumber, "accountNumber must not be null");
        this.partnerReferenceNumber = Objects.requireNonNull(partnerReferenceNumber, "partnerReferenceNumber must not be null");
        this.additionalInfo = new HashMap<>();
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
     * @return This request object
     */
    public AccountInquiryRequest setBankCode(String bankCode) {
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
     * @return This request object
     */
    public AccountInquiryRequest setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
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
     * @return This request object
     */
    public AccountInquiryRequest setPartnerReferenceNumber(String partnerReferenceNumber) {
        this.partnerReferenceNumber = partnerReferenceNumber;
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
     * @return This request object
     */
    public AccountInquiryRequest setAdditionalInfo(Map<String, String> additionalInfo) {
        this.additionalInfo = additionalInfo;
        return this;
    }

    /**
     * Adds an entry to the additional info map.
     *
     * @param key The key
     * @param value The value
     * @return This request object
     */
    public AccountInquiryRequest addAdditionalInfo(String key, String value) {
        this.additionalInfo.put(key, value);
        return this;
    }

    /**
     * Sets the source account in the additional info.
     *
     * @param sourceAccount The source account
     * @return This request object
     */
    public AccountInquiryRequest setSourceAccount(String sourceAccount) {
        this.additionalInfo.put("sourceAccount", sourceAccount);
        return this;
    }

    /**
     * Gets the source account from the additional info.
     *
     * @return The source account
     */
    public String getSourceAccount() {
        return this.additionalInfo.get("sourceAccount");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountInquiryRequest that = (AccountInquiryRequest) o;
        return Objects.equals(bankCode, that.bankCode) &&
                Objects.equals(accountNumber, that.accountNumber) &&
                Objects.equals(partnerReferenceNumber, that.partnerReferenceNumber) &&
                Objects.equals(additionalInfo, that.additionalInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bankCode, accountNumber, partnerReferenceNumber, additionalInfo);
    }

    @Override
    public String toString() {
        try {
            return new ObjectMapper()
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(this);
        } catch (com.fasterxml.jackson.core.JsonProcessingException e) {
            return "AccountInquiryRequest{error: \"Failed to convert to JSON: " + e.getMessage() + "\"}";
        }
    }
//    public String toString() {
//        return "AccountInquiryRequest{" +
//                "bankCode='" + bankCode + '\'' +
//                ", accountNumber='" + accountNumber + '\'' +
//                ", partnerReferenceNumber='" + partnerReferenceNumber + '\'' +
//                ", additionalInfo=" + additionalInfo +
//                '}';
//    }
}
