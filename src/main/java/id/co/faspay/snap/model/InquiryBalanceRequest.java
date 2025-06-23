package id.co.faspay.snap.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Objects;

/**
 * Request model for the Balance Inquiry API.
 * This class represents the request payload for inquiring account balance.
 */
public class InquiryBalanceRequest {

    @JsonProperty("accountNo")
    private String accountNo;

    /**
     * Default constructor for Jackson deserialization.
     */
    public InquiryBalanceRequest() {
    }

    /**
     * Creates a new balance inquiry request with the specified account number.
     *
     * @param accountNo The account number to inquire balance
     */
    public InquiryBalanceRequest(String accountNo) {
        this.accountNo = Objects.requireNonNull(accountNo, "accountNo must not be null");
    }

    /**
     * Gets the account number.
     *
     * @return The account number
     */
    public String getAccountNo() {
        return accountNo;
    }

    /**
     * Sets the account number.
     *
     * @param accountNo The account number
     * @return This request object
     */
    public InquiryBalanceRequest setAccountNo(String accountNo) {
        this.accountNo = accountNo;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InquiryBalanceRequest that = (InquiryBalanceRequest) o;
        return Objects.equals(accountNo, that.accountNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNo);
    }

    @Override
    public String toString() {
        try {
            return new ObjectMapper()
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(this);
        } catch (com.fasterxml.jackson.core.JsonProcessingException e) {
            return "InquiryBalanceRequest{error: \"Failed to convert to JSON: " + e.getMessage() + "\"}";
        }
    }
}