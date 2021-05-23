package com.test.flip.Disbursement.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

import java.math.BigInteger;

public class ResponseDisbursementModel {
    @JsonProperty("id")
    @SerializedName("id")
    private BigInteger transactionId;

    @JsonProperty("amount")
    @SerializedName("amount")
    private Integer amount;

    @JsonProperty("status")
    @SerializedName("status")
    private String status;

    @JsonProperty("timestamp")
    @SerializedName("timestamp")
    private String timestamp;

    @JsonProperty("bank_code")
    @SerializedName("bank_code")
    private String bankCode;

    @JsonProperty("account_number")
    @SerializedName("account_number")
    private String accountNumber;

    @JsonProperty("beneficiary_name")
    @SerializedName("beneficiary_name")
    private String beneficiaryName;

    @JsonProperty("remark")
    @SerializedName("remark")
    private String remark;

    @JsonProperty("receipt")
    @SerializedName("receipt")
    private String receipt;

    @JsonProperty("time_served")
    @SerializedName("time_served")
    private String timeServed;

    @JsonProperty("fee")
    @SerializedName("fee")
    private Integer fee;


    public BigInteger getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(BigInteger transactionId) {
        this.transactionId = transactionId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getBeneficiaryName() {
        return beneficiaryName;
    }

    public void setBeneficiaryName(String beneficiaryName) {
        this.beneficiaryName = beneficiaryName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    public String getTimeServed() {
        return timeServed;
    }

    public void setTimeServed(String timeServed) {
        this.timeServed = timeServed;
    }

    public Integer getFee() {
        return fee;
    }

    public void setFee(Integer fee) {
        this.fee = fee;
    }
}
