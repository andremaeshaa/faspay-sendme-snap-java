package id.co.faspay.snap.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

/**
 * Represents a monetary amount with a value and currency.
 */
public class Amount {

    @JsonProperty("value")
    private String value;

    @JsonProperty("currency")
    private String currency;

    /**
     * Default constructor for Jackson deserialization.
     */
    public Amount() {
    }

    /**
     * Creates a new amount with the specified value and currency.
     *
     * @param value The monetary value
     * @param currency The currency code (e.g., "IDR")
     */
    public Amount(String value, String currency) {
        this.value = Objects.requireNonNull(value, "value must not be null");
        this.currency = Objects.requireNonNull(currency, "currency must not be null");
    }

    /**
     * Gets the monetary value.
     *
     * @return The monetary value
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the monetary value.
     *
     * @param value The monetary value
     * @return This amount object
     */
    public Amount setValue(String value) {
        this.value = value;
        return this;
    }

    /**
     * Gets the currency code.
     *
     * @return The currency code
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * Sets the currency code.
     *
     * @param currency The currency code
     * @return This amount object
     */
    public Amount setCurrency(String currency) {
        this.currency = currency;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Amount amount = (Amount) o;
        return Objects.equals(value, amount.value) &&
                Objects.equals(currency, amount.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, currency);
    }

    @Override
    public String toString() {
        return "{" +
                "\"value\":\"" + value + "\"," +
                "\"currency\":\"" + currency + "\"" +
                "}";
    }
}