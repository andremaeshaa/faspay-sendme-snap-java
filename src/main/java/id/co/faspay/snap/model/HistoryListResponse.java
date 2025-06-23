package id.co.faspay.snap.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Response model for the History List API.
 * This class represents the response payload for retrieving transaction history.
 */
public class HistoryListResponse {

    @JsonProperty("responseCode")
    private String responseCode;

    @JsonProperty("responseMessage")
    private String responseMessage;

    @JsonProperty("detailData")
    private List<DetailData> detailData;

    @JsonProperty("additionalInfo")
    private AdditionalInfo additionalInfo;

    /**
     * Default constructor for Jackson deserialization.
     */
    public HistoryListResponse() {
        this.detailData = new ArrayList<>();
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
    public HistoryListResponse setResponseCode(String responseCode) {
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
    public HistoryListResponse setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
        return this;
    }

    /**
     * Gets the detail data list.
     *
     * @return The detail data list
     */
    public List<DetailData> getDetailData() {
        return detailData;
    }

    /**
     * Sets the detail data list.
     *
     * @param detailData The detail data list
     * @return This response object
     */
    public HistoryListResponse setDetailData(List<DetailData> detailData) {
        this.detailData = detailData;
        return this;
    }

    /**
     * Adds a detail data to the list.
     *
     * @param detailData The detail data to add
     * @return This response object
     */
    public HistoryListResponse addDetailData(DetailData detailData) {
        this.detailData.add(detailData);
        return this;
    }

    /**
     * Gets the additional info.
     *
     * @return The additional info
     */
    public AdditionalInfo getAdditionalInfo() {
        return additionalInfo;
    }

    /**
     * Sets the additional info.
     *
     * @param additionalInfo The additional info
     * @return This response object
     */
    public HistoryListResponse setAdditionalInfo(AdditionalInfo additionalInfo) {
        this.additionalInfo = additionalInfo;
        return this;
    }

    /**
     * Checks if the history list request was successful.
     *
     * @return True if the request was successful, false otherwise
     */
    public boolean isSuccess() {
        return Objects.equals(responseCode, "2001200");
    }

    /**
     * Gets the account number from additional info.
     *
     * @return The account number, or null if additional info is null
     */
    public String getAccountNo() {
        return additionalInfo != null ? additionalInfo.getAccountNo() : null;
    }

    /**
     * Gets transactions by type.
     *
     * @param type The transaction type
     * @return List of transactions with the specified type
     */
    public List<DetailData> getTransactionsByType(String type) {
        return detailData.stream()
                .filter(data -> Objects.equals(data.getType(), type))
                .collect(Collectors.toList());
    }

    /**
     * Gets transactions by status.
     *
     * @param status The transaction status
     * @return List of transactions with the specified status
     */
    public List<DetailData> getTransactionsByStatus(String status) {
        return detailData.stream()
                .filter(data -> Objects.equals(data.getStatus(), status))
                .collect(Collectors.toList());
    }

    /**
     * Gets debit transactions (debitCredit = "D").
     *
     * @return List of debit transactions
     */
    public List<DetailData> getDebitTransactions() {
        return detailData.stream()
                .filter(data -> data.getAdditionalInfo() != null && 
                               "D".equals(data.getAdditionalInfo().getDebitCredit()))
                .collect(Collectors.toList());
    }

    /**
     * Gets credit transactions (debitCredit = "K").
     *
     * @return List of credit transactions
     */
    public List<DetailData> getCreditTransactions() {
        return detailData.stream()
                .filter(data -> data.getAdditionalInfo() != null && 
                               "K".equals(data.getAdditionalInfo().getDebitCredit()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HistoryListResponse that = (HistoryListResponse) o;
        return Objects.equals(responseCode, that.responseCode) &&
                Objects.equals(responseMessage, that.responseMessage) &&
                Objects.equals(detailData, that.detailData) &&
                Objects.equals(additionalInfo, that.additionalInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(responseCode, responseMessage, detailData, additionalInfo);
    }

    @Override
    public String toString() {
        try {
            return new ObjectMapper()
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(this);
        } catch (com.fasterxml.jackson.core.JsonProcessingException e) {
            return "HistoryListResponse{error: \"Failed to convert to JSON: " + e.getMessage() + "\"}";
        }
    }

    /**
     * Inner class representing detail data of a transaction.
     */
    public static class DetailData {
        @JsonProperty("dateTime")
        private String dateTime;

        @JsonProperty("amount")
        private Amount amount;

        @JsonProperty("remark")
        private String remark;

        @JsonProperty("sourceOfFunds")
        private List<SourceOfFunds> sourceOfFunds;

        @JsonProperty("status")
        private String status;

        @JsonProperty("type")
        private String type;

        @JsonProperty("additionalInfo")
        private TransactionAdditionalInfo additionalInfo;

        /**
         * Default constructor for Jackson deserialization.
         */
        public DetailData() {
            this.sourceOfFunds = new ArrayList<>();
        }

        /**
         * Gets the date time.
         *
         * @return The date time
         */
        public String getDateTime() {
            return dateTime;
        }

        /**
         * Sets the date time.
         *
         * @param dateTime The date time
         * @return This detail data object
         */
        public DetailData setDateTime(String dateTime) {
            this.dateTime = dateTime;
            return this;
        }

        /**
         * Sets the date time using OffsetDateTime.
         *
         * @param dateTime The date time as OffsetDateTime
         * @return This detail data object
         */
        public DetailData setDateTime(OffsetDateTime dateTime) {
            this.dateTime = dateTime != null ? dateTime.toString() : null;
            return this;
        }

        /**
         * Gets the date time as OffsetDateTime.
         *
         * @return The date time as OffsetDateTime, or null if parsing fails
         */
        public OffsetDateTime getDateTimeAsOffsetDateTime() {
            try {
                return dateTime != null ? OffsetDateTime.parse(dateTime) : null;
            } catch (Exception e) {
                return null;
            }
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
         * @return This detail data object
         */
        public DetailData setAmount(Amount amount) {
            this.amount = amount;
            return this;
        }

        /**
         * Gets the remark.
         *
         * @return The remark
         */
        public String getRemark() {
            return remark;
        }

        /**
         * Sets the remark.
         *
         * @param remark The remark
         * @return This detail data object
         */
        public DetailData setRemark(String remark) {
            this.remark = remark;
            return this;
        }

        /**
         * Gets the source of funds list.
         *
         * @return The source of funds list
         */
        public List<SourceOfFunds> getSourceOfFunds() {
            return sourceOfFunds;
        }

        /**
         * Sets the source of funds list.
         *
         * @param sourceOfFunds The source of funds list
         * @return This detail data object
         */
        public DetailData setSourceOfFunds(List<SourceOfFunds> sourceOfFunds) {
            this.sourceOfFunds = sourceOfFunds;
            return this;
        }

        /**
         * Adds a source of funds to the list.
         *
         * @param sourceOfFunds The source of funds to add
         * @return This detail data object
         */
        public DetailData addSourceOfFunds(SourceOfFunds sourceOfFunds) {
            this.sourceOfFunds.add(sourceOfFunds);
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
         * @return This detail data object
         */
        public DetailData setStatus(String status) {
            this.status = status;
            return this;
        }

        /**
         * Gets the type.
         *
         * @return The type
         */
        public String getType() {
            return type;
        }

        /**
         * Sets the type.
         *
         * @param type The type
         * @return This detail data object
         */
        public DetailData setType(String type) {
            this.type = type;
            return this;
        }

        /**
         * Gets the additional info.
         *
         * @return The additional info
         */
        public TransactionAdditionalInfo getAdditionalInfo() {
            return additionalInfo;
        }

        /**
         * Sets the additional info.
         *
         * @param additionalInfo The additional info
         * @return This detail data object
         */
        public DetailData setAdditionalInfo(TransactionAdditionalInfo additionalInfo) {
            this.additionalInfo = additionalInfo;
            return this;
        }

        /**
         * Checks if the transaction is successful.
         *
         * @return True if status is "SUCCESS", false otherwise
         */
        public boolean isSuccess() {
            return Objects.equals(status, "SUCCESS");
        }

        /**
         * Checks if the transaction is a debit transaction.
         *
         * @return True if debitCredit is "D", false otherwise
         */
        public boolean isDebit() {
            return additionalInfo != null && "D".equals(additionalInfo.getDebitCredit());
        }

        /**
         * Checks if the transaction is a credit transaction.
         *
         * @return True if debitCredit is "K", false otherwise
         */
        public boolean isCredit() {
            return additionalInfo != null && "K".equals(additionalInfo.getDebitCredit());
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            DetailData that = (DetailData) o;
            return Objects.equals(dateTime, that.dateTime) &&
                    Objects.equals(amount, that.amount) &&
                    Objects.equals(remark, that.remark) &&
                    Objects.equals(sourceOfFunds, that.sourceOfFunds) &&
                    Objects.equals(status, that.status) &&
                    Objects.equals(type, that.type) &&
                    Objects.equals(additionalInfo, that.additionalInfo);
        }

        @Override
        public int hashCode() {
            return Objects.hash(dateTime, amount, remark, sourceOfFunds, status, type, additionalInfo);
        }

        @Override
        public String toString() {
            return "DetailData{" +
                    "dateTime='" + dateTime + '\'' +
                    ", amount=" + amount +
                    ", remark='" + remark + '\'' +
                    ", sourceOfFunds=" + sourceOfFunds +
                    ", status='" + status + '\'' +
                    ", type='" + type + '\'' +
                    ", additionalInfo=" + additionalInfo +
                    '}';
        }
    }

    /**
     * Inner class representing source of funds.
     */
    public static class SourceOfFunds {
        @JsonProperty("source")
        private String source;

        /**
         * Default constructor for Jackson deserialization.
         */
        public SourceOfFunds() {
        }

        /**
         * Creates a new source of funds with the specified source.
         *
         * @param source The source
         */
        public SourceOfFunds(String source) {
            this.source = source;
        }

        /**
         * Gets the source.
         *
         * @return The source
         */
        public String getSource() {
            return source;
        }

        /**
         * Sets the source.
         *
         * @param source The source
         * @return This source of funds object
         */
        public SourceOfFunds setSource(String source) {
            this.source = source;
            return this;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            SourceOfFunds that = (SourceOfFunds) o;
            return Objects.equals(source, that.source);
        }

        @Override
        public int hashCode() {
            return Objects.hash(source);
        }

        @Override
        public String toString() {
            return "SourceOfFunds{" +
                    "source='" + source + '\'' +
                    '}';
        }
    }

    /**
     * Inner class representing transaction additional info.
     */
    public static class TransactionAdditionalInfo {
        @JsonProperty("debitCredit")
        private String debitCredit;

        /**
         * Default constructor for Jackson deserialization.
         */
        public TransactionAdditionalInfo() {
        }

        /**
         * Creates a new transaction additional info with the specified debit credit.
         *
         * @param debitCredit The debit credit indicator
         */
        public TransactionAdditionalInfo(String debitCredit) {
            this.debitCredit = debitCredit;
        }

        /**
         * Gets the debit credit indicator.
         *
         * @return The debit credit indicator
         */
        public String getDebitCredit() {
            return debitCredit;
        }

        /**
         * Sets the debit credit indicator.
         *
         * @param debitCredit The debit credit indicator
         * @return This transaction additional info object
         */
        public TransactionAdditionalInfo setDebitCredit(String debitCredit) {
            this.debitCredit = debitCredit;
            return this;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            TransactionAdditionalInfo that = (TransactionAdditionalInfo) o;
            return Objects.equals(debitCredit, that.debitCredit);
        }

        @Override
        public int hashCode() {
            return Objects.hash(debitCredit);
        }

        @Override
        public String toString() {
            return "TransactionAdditionalInfo{" +
                    "debitCredit='" + debitCredit + '\'' +
                    '}';
        }
    }

    /**
     * Inner class representing additional info for the response.
     */
    public static class AdditionalInfo {
        @JsonProperty("accountNo")
        private String accountNo;

        @JsonProperty("fromDateTime")
        private String fromDateTime;

        @JsonProperty("toDateTime")
        private String toDateTime;

        @JsonProperty("message")
        private String message;

        /**
         * Default constructor for Jackson deserialization.
         */
        public AdditionalInfo() {
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
         * @return This additional info object
         */
        public AdditionalInfo setAccountNo(String accountNo) {
            this.accountNo = accountNo;
            return this;
        }

        /**
         * Gets the from date time.
         *
         * @return The from date time
         */
        public String getFromDateTime() {
            return fromDateTime;
        }

        /**
         * Sets the from date time.
         *
         * @param fromDateTime The from date time
         * @return This additional info object
         */
        public AdditionalInfo setFromDateTime(String fromDateTime) {
            this.fromDateTime = fromDateTime;
            return this;
        }

        /**
         * Gets the to date time.
         *
         * @return The to date time
         */
        public String getToDateTime() {
            return toDateTime;
        }

        /**
         * Sets the to date time.
         *
         * @param toDateTime The to date time
         * @return This additional info object
         */
        public AdditionalInfo setToDateTime(String toDateTime) {
            this.toDateTime = toDateTime;
            return this;
        }

        /**
         * Gets the message.
         *
         * @return The message
         */
        public String getMessage() {
            return message;
        }

        /**
         * Sets the message.
         *
         * @param message The message
         * @return This additional info object
         */
        public AdditionalInfo setMessage(String message) {
            this.message = message;
            return this;
        }

        /**
         * Gets the from date time as OffsetDateTime.
         *
         * @return The from date time as OffsetDateTime, or null if parsing fails
         */
        public OffsetDateTime getFromDateTimeAsOffsetDateTime() {
            try {
                return fromDateTime != null ? OffsetDateTime.parse(fromDateTime) : null;
            } catch (Exception e) {
                return null;
            }
        }

        /**
         * Gets the to date time as OffsetDateTime.
         *
         * @return The to date time as OffsetDateTime, or null if parsing fails
         */
        public OffsetDateTime getToDateTimeAsOffsetDateTime() {
            try {
                return toDateTime != null ? OffsetDateTime.parse(toDateTime) : null;
            } catch (Exception e) {
                return null;
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            AdditionalInfo that = (AdditionalInfo) o;
            return Objects.equals(accountNo, that.accountNo) &&
                    Objects.equals(fromDateTime, that.fromDateTime) &&
                    Objects.equals(toDateTime, that.toDateTime) &&
                    Objects.equals(message, that.message);
        }

        @Override
        public int hashCode() {
            return Objects.hash(accountNo, fromDateTime, toDateTime, message);
        }

        @Override
        public String toString() {
            return "AdditionalInfo{" +
                    "accountNo='" + accountNo + '\'' +
                    ", fromDateTime='" + fromDateTime + '\'' +
                    ", toDateTime='" + toDateTime + '\'' +
                    ", message='" + message + '\'' +
                    '}';
        }
    }
}