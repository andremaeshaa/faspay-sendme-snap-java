package id.co.faspay.snap.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class CustomerTopupResponse {
    private String responseCode;
    private String responseMessage;
    private String referenceNo;
    private String partnerReferenceNo;
    private String customerNumber;
    private Amount amount;
    private AdditionalInfo additionalInfo;

    // Default constructor
    public CustomerTopupResponse() {}

    // Constructor with all fields
    public CustomerTopupResponse(String responseCode, String responseMessage, 
                               String referenceNo, String partnerReferenceNo,
                               String customerNumber, Amount amount, 
                               AdditionalInfo additionalInfo) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        this.referenceNo = referenceNo;
        this.partnerReferenceNo = partnerReferenceNo;
        this.customerNumber = customerNumber;
        this.amount = amount;
        this.additionalInfo = additionalInfo;
    }

    // Getters and Setters
    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getReferenceNo() {
        return referenceNo;
    }

    public void setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
    }

    public String getPartnerReferenceNo() {
        return partnerReferenceNo;
    }

    public void setPartnerReferenceNo(String partnerReferenceNo) {
        this.partnerReferenceNo = partnerReferenceNo;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }

    public AdditionalInfo getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(AdditionalInfo additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    // Utility method to check if the response is successful
    @JsonIgnore
    public boolean isSuccess() {
        return "2003800".equals(responseCode);
    }

    // Inner class for additional info
    public static class AdditionalInfo {
        private String sourceAccount;
        private String platformCode;
        private String beneficiaryEmail;
        private String transactionDate;
        private String instructDate;
        private String transactionDescription;
        private String callbackUrl;
        private String transactionReference;
        private String latestTransactionStatus;
        private String transactionStatusDesc;

        // Default constructor
        public AdditionalInfo() {}

        // Constructor with all fields
        public AdditionalInfo(String sourceAccount, String platformCode, 
                            String beneficiaryEmail, String transactionDate,
                            String instructDate, String transactionDescription,
                            String callbackUrl, String transactionReference,
                            String latestTransactionStatus, String transactionStatusDesc) {
            this.sourceAccount = sourceAccount;
            this.platformCode = platformCode;
            this.beneficiaryEmail = beneficiaryEmail;
            this.transactionDate = transactionDate;
            this.instructDate = instructDate;
            this.transactionDescription = transactionDescription;
            this.callbackUrl = callbackUrl;
            this.transactionReference = transactionReference;
            this.latestTransactionStatus = latestTransactionStatus;
            this.transactionStatusDesc = transactionStatusDesc;
        }

        // Getters and Setters
        public String getSourceAccount() {
            return sourceAccount;
        }

        public void setSourceAccount(String sourceAccount) {
            this.sourceAccount = sourceAccount;
        }

        public String getPlatformCode() {
            return platformCode;
        }

        public void setPlatformCode(String platformCode) {
            this.platformCode = platformCode;
        }

        public String getBeneficiaryEmail() {
            return beneficiaryEmail;
        }

        public void setBeneficiaryEmail(String beneficiaryEmail) {
            this.beneficiaryEmail = beneficiaryEmail;
        }

        public String getTransactionDate() {
            return transactionDate;
        }

        public void setTransactionDate(String transactionDate) {
            this.transactionDate = transactionDate;
        }

        public String getInstructDate() {
            return instructDate;
        }

        public void setInstructDate(String instructDate) {
            this.instructDate = instructDate;
        }

        public String getTransactionDescription() {
            return transactionDescription;
        }

        public void setTransactionDescription(String transactionDescription) {
            this.transactionDescription = transactionDescription;
        }

        public String getCallbackUrl() {
            return callbackUrl;
        }

        public void setCallbackUrl(String callbackUrl) {
            this.callbackUrl = callbackUrl;
        }

        public String getTransactionReference() {
            return transactionReference;
        }

        public void setTransactionReference(String transactionReference) {
            this.transactionReference = transactionReference;
        }

        public String getLatestTransactionStatus() {
            return latestTransactionStatus;
        }

        public void setLatestTransactionStatus(String latestTransactionStatus) {
            this.latestTransactionStatus = latestTransactionStatus;
        }

        public String getTransactionStatusDesc() {
            return transactionStatusDesc;
        }

        public void setTransactionStatusDesc(String transactionStatusDesc) {
            this.transactionStatusDesc = transactionStatusDesc;
        }

        // Utility method to check if transaction is pending
        @JsonIgnore
        public boolean isPending() {
            return "03".equals(latestTransactionStatus);
        }

        // Utility method to check if transaction is successful
        @JsonIgnore
        public boolean isTransactionSuccess() {
            return "00".equals(latestTransactionStatus);
        }

        @Override
        public String toString() {
            return "AdditionalInfo{" +
                    "sourceAccount='" + sourceAccount + '\'' +
                    ", platformCode='" + platformCode + '\'' +
                    ", beneficiaryEmail='" + beneficiaryEmail + '\'' +
                    ", transactionDate='" + transactionDate + '\'' +
                    ", instructDate='" + instructDate + '\'' +
                    ", transactionDescription='" + transactionDescription + '\'' +
                    ", callbackUrl='" + callbackUrl + '\'' +
                    ", transactionReference='" + transactionReference + '\'' +
                    ", latestTransactionStatus='" + latestTransactionStatus + '\'' +
                    ", transactionStatusDesc='" + transactionStatusDesc + '\'' +
                    '}';
        }
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
            return "CustomerTopup{error: \"Failed to convert to JSON: " + e.getMessage() + "\"}";
        }
    }
}