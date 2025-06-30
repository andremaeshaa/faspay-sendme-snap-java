package id.co.faspay.snap.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Response model for the Balance Inquiry API.
 * This class represents the response payload from inquiring account balance.
 */
public class InquiryBalanceResponse {

    @JsonProperty("responseCode")
    private String responseCode;

    @JsonProperty("responseMessage")
    private String responseMessage;

    @JsonProperty("accountNo")
    private String accountNo;

    @JsonProperty("accountInfos")
    private List<AccountInfo> accountInfos;

    /**
     * Default constructor for Jackson deserialization.
     */
    public InquiryBalanceResponse() {
        this.accountInfos = new ArrayList<>();
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
    public InquiryBalanceResponse setResponseCode(String responseCode) {
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
    public InquiryBalanceResponse setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
        return this;
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
     * @return This response object
     */
    public InquiryBalanceResponse setAccountNo(String accountNo) {
        this.accountNo = accountNo;
        return this;
    }

    /**
     * Gets the account information list.
     *
     * @return The account information list
     */
    public List<AccountInfo> getAccountInfos() {
        return accountInfos;
    }

    /**
     * Sets the account information list.
     *
     * @param accountInfos The account information list
     * @return This response object
     */
    public InquiryBalanceResponse setAccountInfos(List<AccountInfo> accountInfos) {
        this.accountInfos = accountInfos;
        return this;
    }

    /**
     * Adds an account info to the list.
     *
     * @param accountInfo The account info to add
     * @return This response object
     */
    public InquiryBalanceResponse addAccountInfo(AccountInfo accountInfo) {
        this.accountInfos.add(accountInfo);
        return this;
    }

    /**
     * Gets the cash balance information if available.
     *
     * @return The cash balance AccountInfo, or null if not found
     */
    public AccountInfo getCashBalance() {
        return accountInfos.stream()
                .filter(info -> "CASH".equals(info.getBalanceType()))
                .findFirst()
                .orElse(null);
    }

    /**
     * Gets the deposit balance information if available.
     *
     * @return The deposit balance AccountInfo, or null if not found
     */
    public AccountInfo getDepositBalance() {
        return accountInfos.stream()
                .filter(info -> "DEPOSIT".equals(info.getBalanceType()))
                .findFirst()
                .orElse(null);
    }

    /**
     * Checks if the balance inquiry was successful.
     *
     * @return True if the inquiry was successful, false otherwise
     */
    public boolean isSuccess() {
        return Objects.equals(responseCode, "2001100");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InquiryBalanceResponse that = (InquiryBalanceResponse) o;
        return Objects.equals(responseCode, that.responseCode) &&
                Objects.equals(responseMessage, that.responseMessage) &&
                Objects.equals(accountNo, that.accountNo) &&
                Objects.equals(accountInfos, that.accountInfos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(responseCode, responseMessage, accountNo, accountInfos);
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
            return "InquiryBalance{error: \"Failed to convert to JSON: " + e.getMessage() + "\"}";
        }
    }

    /**
     * Inner class representing account information with balance details.
     */
    public static class AccountInfo {
        @JsonProperty("balanceType")
        private String balanceType;

        @JsonProperty("amount")
        private Amount amount;

        @JsonProperty("availableBalance")
        private Amount availableBalance;

        @JsonProperty("status")
        private String status;

        /**
         * Default constructor for Jackson deserialization.
         */
        public AccountInfo() {
        }

        /**
         * Creates a new account info with the specified parameters.
         *
         * @param balanceType The balance type (e.g., "CASH", "DEPOSIT")
         * @param amount The total amount
         * @param availableBalance The available balance amount
         * @param status The status code
         */
        public AccountInfo(String balanceType, Amount amount, Amount availableBalance, String status) {
            this.balanceType = balanceType;
            this.amount = amount;
            this.availableBalance = availableBalance;
            this.status = status;
        }

        /**
         * Gets the balance type.
         *
         * @return The balance type
         */
        public String getBalanceType() {
            return balanceType;
        }

        /**
         * Sets the balance type.
         *
         * @param balanceType The balance type
         * @return This account info object
         */
        public AccountInfo setBalanceType(String balanceType) {
            this.balanceType = balanceType;
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
         * @return This account info object
         */
        public AccountInfo setAmount(Amount amount) {
            this.amount = amount;
            return this;
        }

        /**
         * Gets the available balance.
         *
         * @return The available balance
         */
        public Amount getAvailableBalance() {
            return availableBalance;
        }

        /**
         * Sets the available balance.
         *
         * @param availableBalance The available balance
         * @return This account info object
         */
        public AccountInfo setAvailableBalance(Amount availableBalance) {
            this.availableBalance = availableBalance;
            return this;
        }

        /**
         * Gets the status.
         *
         * @return The status
         */
        public String getStatus() {
            return status;
        }

        /**
         * Sets the status.
         *
         * @param status The status
         * @return This account info object
         */
        public AccountInfo setStatus(String status) {
            this.status = status;
            return this;
        }

        /**
         * Checks if the account info status is active.
         *
         * @return True if status is "0001", false otherwise
         */
        public boolean isActive() {
            return Objects.equals(status, "0001");
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            AccountInfo that = (AccountInfo) o;
            return Objects.equals(balanceType, that.balanceType) &&
                    Objects.equals(amount, that.amount) &&
                    Objects.equals(availableBalance, that.availableBalance) &&
                    Objects.equals(status, that.status);
        }

        @Override
        public int hashCode() {
            return Objects.hash(balanceType, amount, availableBalance, status);
        }

        @Override
        public String toString() {
            return "AccountInfo{" +
                    "balanceType='" + balanceType + '\'' +
                    ", amount=" + amount +
                    ", availableBalance=" + availableBalance +
                    ", status='" + status + '\'' +
                    '}';
        }
    }
}