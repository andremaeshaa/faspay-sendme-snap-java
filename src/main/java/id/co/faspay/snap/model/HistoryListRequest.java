package id.co.faspay.snap.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.OffsetDateTime;
import java.util.Objects;

/**
 * Request model for the History List API.
 * This class represents the request payload for retrieving transaction history.
 */
public class HistoryListRequest {

    @JsonProperty("fromDateTime")
    private String fromDateTime;

    @JsonProperty("toDateTime")
    private String toDateTime;

    @JsonProperty("additionalInfo")
    private AdditionalInfo additionalInfo;

    /**
     * Default constructor for Jackson deserialization.
     */
    public HistoryListRequest() {
    }

    /**
     * Creates a new history list request with the specified parameters.
     *
     * @param fromDateTime The start date and time for the history query
     * @param toDateTime The end date and time for the history query
     * @param additionalInfo Additional information including account number
     */
    public HistoryListRequest(String fromDateTime, String toDateTime, AdditionalInfo additionalInfo) {
        this.fromDateTime = Objects.requireNonNull(fromDateTime, "fromDateTime must not be null");
        this.toDateTime = Objects.requireNonNull(toDateTime, "toDateTime must not be null");
        this.additionalInfo = Objects.requireNonNull(additionalInfo, "additionalInfo must not be null");
    }

    /**
     * Creates a new history list request with account number.
     *
     * @param fromDateTime The start date and time for the history query
     * @param toDateTime The end date and time for the history query
     * @param accountNo The account number
     */
    public HistoryListRequest(String fromDateTime, String toDateTime, String accountNo) {
        this.fromDateTime = Objects.requireNonNull(fromDateTime, "fromDateTime must not be null");
        this.toDateTime = Objects.requireNonNull(toDateTime, "toDateTime must not be null");
        this.additionalInfo = new AdditionalInfo(accountNo);
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
     * @return This request object
     */
    public HistoryListRequest setFromDateTime(String fromDateTime) {
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
     * @return This request object
     */
    public HistoryListRequest setToDateTime(String toDateTime) {
        this.toDateTime = toDateTime;
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
     * @return This request object
     */
    public HistoryListRequest setAdditionalInfo(AdditionalInfo additionalInfo) {
        this.additionalInfo = additionalInfo;
        return this;
    }

    /**
     * Sets the account number in additional info.
     *
     * @param accountNo The account number
     * @return This request object
     */
    public HistoryListRequest setAccountNo(String accountNo) {
        if (this.additionalInfo == null) {
            this.additionalInfo = new AdditionalInfo();
        }
        this.additionalInfo.setAccountNo(accountNo);
        return this;
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
     * Sets the from date time using OffsetDateTime.
     *
     * @param fromDateTime The from date time as OffsetDateTime
     * @return This request object
     */
    public HistoryListRequest setFromDateTime(OffsetDateTime fromDateTime) {
        this.fromDateTime = fromDateTime != null ? fromDateTime.toString() : null;
        return this;
    }

    /**
     * Sets the to date time using OffsetDateTime.
     *
     * @param toDateTime The to date time as OffsetDateTime
     * @return This request object
     */
    public HistoryListRequest setToDateTime(OffsetDateTime toDateTime) {
        this.toDateTime = toDateTime != null ? toDateTime.toString() : null;
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
        HistoryListRequest that = (HistoryListRequest) o;
        return Objects.equals(fromDateTime, that.fromDateTime) &&
                Objects.equals(toDateTime, that.toDateTime) &&
                Objects.equals(additionalInfo, that.additionalInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fromDateTime, toDateTime, additionalInfo);
    }

    @Override
    public String toString() {
        try {
            ObjectMapper mapper = new ObjectMapper()
                    .registerModule(new com.fasterxml.jackson.datatype.jsr310.JavaTimeModule())
                    .disable(com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            return mapper.writeValueAsString(this);
        } catch (com.fasterxml.jackson.core.JsonProcessingException e) {
            return "HistoryListRequest{error: \"Failed to convert to JSON: " + e.getMessage() + "\"}";
        }
    }


    /**
     * Inner class representing additional information.
     */
    public static class AdditionalInfo {
        @JsonProperty("accountNo")
        private String accountNo;

        /**
         * Default constructor for Jackson deserialization.
         */
        public AdditionalInfo() {
        }

        /**
         * Creates a new additional info with the specified account number.
         *
         * @param accountNo The account number
         */
        public AdditionalInfo(String accountNo) {
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
         * @return This additional info object
         */
        public AdditionalInfo setAccountNo(String accountNo) {
            this.accountNo = accountNo;
            return this;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            AdditionalInfo that = (AdditionalInfo) o;
            return Objects.equals(accountNo, that.accountNo);
        }

        @Override
        public int hashCode() {
            return Objects.hash(accountNo);
        }

        @Override
        public String toString() {
            return "AdditionalInfo{" +
                    "accountNo='" + accountNo + '\'' +
                    '}';
        }
    }
}