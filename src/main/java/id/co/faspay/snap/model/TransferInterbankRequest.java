package id.co.faspay.snap.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Request model for the Transfer Interbank API.
 * This class represents the request payload for transferring money between banks.
 */
public class TransferInterbankRequest {

    @JsonProperty("partnerReferenceNo")
    private String partnerReferenceNumber;

    @JsonProperty("amount")
    private Amount amount;

    @JsonProperty("beneficiaryAccountName")
    private String beneficiaryAccountName;

    @JsonProperty("beneficiaryAccountNo")
    private String beneficiaryAccountNumber;

    @JsonProperty("beneficiaryBankCode")
    private String beneficiaryBankCode;

    @JsonProperty("beneficiaryEmail")
    private String beneficiaryEmail;

    @JsonProperty("sourceAccountNo")
    private String sourceAccountNumber;

    @JsonProperty("transactionDate")
    private String transactionDate;

    @JsonProperty("originatorInfos")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Map<String, String> originatorInfos;

    @JsonProperty("additionalInfo")
    private Map<String, String> additionalInfo;

    /**
     * Default constructor for Jackson deserialization.
     */
    public TransferInterbankRequest() {
        this.additionalInfo = new HashMap<>();
    }

    /**
     * Creates a new transfer interbank request with the specified parameters.
     *
     * @param partnerReferenceNumber The partner reference number for tracking
     * @param amount The amount to transfer
     * @param beneficiaryAccountName The name of the beneficiary account
     * @param beneficiaryAccountNumber The account number of the beneficiary
     * @param beneficiaryBankCode The bank code of the beneficiary
     * @param sourceAccountNumber The account number of the source
     */
    public TransferInterbankRequest(String partnerReferenceNumber, Amount amount, String beneficiaryAccountName,
                                   String beneficiaryAccountNumber, String beneficiaryBankCode,
                                   String sourceAccountNumber) {
        this.partnerReferenceNumber = Objects.requireNonNull(partnerReferenceNumber, "partnerReferenceNumber must not be null");
        this.amount = Objects.requireNonNull(amount, "amount must not be null");
        this.beneficiaryAccountName = Objects.requireNonNull(beneficiaryAccountName, "beneficiaryAccountName must not be null");
        this.beneficiaryAccountNumber = Objects.requireNonNull(beneficiaryAccountNumber, "beneficiaryAccountNumber must not be null");
        this.beneficiaryBankCode = Objects.requireNonNull(beneficiaryBankCode, "beneficiaryBankCode must not be null");
        this.sourceAccountNumber = Objects.requireNonNull(sourceAccountNumber, "sourceAccountNumber must not be null");
        this.transactionDate = getTimestamp();
//        this.originatorInfos = new HashMap<>();
        this.additionalInfo = new HashMap<>();
    }

    /**
     * Retrieves the originator information as a map of key-value pairs.
     *
     * @return A map containing the originator information, where keys represent the information types and values represent the associated data.
     */
    // Getter dan setter untuk originatorInfos
    public Map<String, String> getOriginatorInfos() {
        return originatorInfos;
    }

    /**
     * Sets the originator information for the transfer interbank request.
     *
     * @param originatorInfos A map containing information about the originator. The keys and
     *                        values in the map represent different attributes of the originator.
     * @return This transfer interbank request object.
     */
    public TransferInterbankRequest setOriginatorInfos(Map<String, String> originatorInfos) {
        this.originatorInfos = originatorInfos;
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
    public TransferInterbankRequest setPartnerReferenceNumber(String partnerReferenceNumber) {
        this.partnerReferenceNumber = partnerReferenceNumber;
        return this;
    }

    /**
     * Gets the amount to transfer.
     *
     * @return The amount to transfer
     */
    public Amount getAmount() {
        return amount;
    }

    /**
     * Sets the amount to transfer.
     *
     * @param amount The amount to transfer
     * @return This request object
     */
    public TransferInterbankRequest setAmount(Amount amount) {
        this.amount = amount;
        return this;
    }

    /**
     * Gets the beneficiary account name.
     *
     * @return The beneficiary account name
     */
    public String getBeneficiaryAccountName() {
        return beneficiaryAccountName;
    }

    /**
     * Sets the beneficiary account name.
     *
     * @param beneficiaryAccountName The beneficiary account name
     * @return This request object
     */
    public TransferInterbankRequest setBeneficiaryAccountName(String beneficiaryAccountName) {
        this.beneficiaryAccountName = beneficiaryAccountName;
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
     * @return This request object
     */
    public TransferInterbankRequest setBeneficiaryAccountNumber(String beneficiaryAccountNumber) {
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
     * @return This request object
     */
    public TransferInterbankRequest setBeneficiaryBankCode(String beneficiaryBankCode) {
        this.beneficiaryBankCode = beneficiaryBankCode;
        return this;
    }

    /**
     * Gets the beneficiary email.
     *
     * @return The beneficiary email
     */
    public String getBeneficiaryEmail() {
        return beneficiaryEmail;
    }

    /**
     * Sets the beneficiary email.
     *
     * @param beneficiaryEmail The beneficiary email
     */
    public void setBeneficiaryEmail(String beneficiaryEmail) {
        this.beneficiaryEmail = beneficiaryEmail;
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
     * @return This request object
     */
    public TransferInterbankRequest setSourceAccountNumber(String sourceAccountNumber) {
        this.sourceAccountNumber = sourceAccountNumber;
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

    public String getTimestamp() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");
        return ZonedDateTime.now().format(formatter);
    }

    /**
     * Sets the transaction date to the current date and time in the format "yyyy-MM-dd'T'HH:mm:ssXXX".
     *
     * @return This TransferInterbankRequest object
     */
    public TransferInterbankRequest setTransactionDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");

        this.transactionDate = ZonedDateTime.now().format(formatter);
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
    public TransferInterbankRequest setAdditionalInfo(Map<String, String> additionalInfo) {
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
    public TransferInterbankRequest addAdditionalInfo(String key, String value) {
        this.additionalInfo.put(key, value);
        return this;
    }

    /**
     * Sets the instruct date in the additional info.
     *
     * @param instructDate The instruct date
     */
    public void setInstructDate(String instructDate) {
        this.additionalInfo.put("instructDate", instructDate);
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
     * Sets the transaction description in the additional info.
     *
     * @param transactionDescription The transaction description
     * @return This request object
     */
    public TransferInterbankRequest setTransactionDescription(String transactionDescription) {
        this.additionalInfo.put("transactionDescription", transactionDescription);
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
     * Sets the callback URL in the additional info.
     *
     * @param callbackUrl The callback URL
     */
    public void setCallbackUrl(String callbackUrl) {
        this.additionalInfo.put("callbackUrl", callbackUrl);
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
     * Sets the originator customer name in the originator information map.
     *
     * @param customerName The originator's customer name to be set
     */
    public void setOriginatorCustomerName(String customerName) {
        if (this.originatorInfos == null) {
            this.originatorInfos = new HashMap<>();
        }
        this.originatorInfos.put("originatorCustomerName", customerName);
    }


    /**
     * Retrieves the originator customer name from the originator information map.
     *
     * @return The originator customer name if present, or null if the originator information is null or the key is not found.
     */
    public String getOriginatorCustomerName() {
        return this.originatorInfos != null ? this.originatorInfos.get("originatorCustomerName") : null;
    }


    /**
     * Sets the originator customer number in the originator information map.
     *
     * @param customerNo The originator customer number to set
     */
    public void setOriginatorCustomerNo(String customerNo) {
        if (this.originatorInfos == null) {
            this.originatorInfos = new HashMap<>();
        }
        this.originatorInfos.put("originatorCustomerNo", customerNo);
    }


    /**
     * Retrieves the originator customer number from the originator information map.
     *
     * @return The originator customer number if present; otherwise, null
     */
    public String getOriginatorCustomerNo() {
        return this.originatorInfos != null ? this.originatorInfos.get("originatorCustomerNo") : null;
    }


    /**
     * Sets the originator bank code in the originator information map.
     *
     * @param bankCode The bank code of the originator to be set
     */
    public void setOriginatorBankCode(String bankCode) {
        if (this.originatorInfos == null) {
            this.originatorInfos = new HashMap<>();
        }
        this.originatorInfos.put("originatorBankCode", bankCode);
    }


    /**
     * Retrieves the originator bank code from the originator information.
     *
     * @return The originator bank code if present; otherwise, null.
     */
    public String getOriginatorBankCode() {
        return this.originatorInfos != null ? this.originatorInfos.get("originatorBankCode") : null;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransferInterbankRequest that = (TransferInterbankRequest) o;
        return Objects.equals(partnerReferenceNumber, that.partnerReferenceNumber) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(beneficiaryAccountName, that.beneficiaryAccountName) &&
                Objects.equals(beneficiaryAccountNumber, that.beneficiaryAccountNumber) &&
                Objects.equals(beneficiaryBankCode, that.beneficiaryBankCode) &&
                Objects.equals(beneficiaryEmail, that.beneficiaryEmail) &&
                Objects.equals(sourceAccountNumber, that.sourceAccountNumber) &&
                Objects.equals(transactionDate, that.transactionDate) &&
                Objects.equals(additionalInfo, that.additionalInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(partnerReferenceNumber, amount, beneficiaryAccountName, beneficiaryAccountNumber,
                beneficiaryBankCode, beneficiaryEmail, sourceAccountNumber, transactionDate, additionalInfo);
    }

    @Override
    public String toString() {
        try {
            return new ObjectMapper()
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(this);
        } catch (com.fasterxml.jackson.core.JsonProcessingException e) {
            return "TransferInterbankRequest{error: \"Failed to convert to JSON: " + e.getMessage() + "\"}";
        }
    }
}