package com.test.flip.Disbursement.dto;

public class DisbursementDto {
    private String bankCode;
    private String accountNumber;
    private Integer amount;
    private String remark;

    public DisbursementDto(String bankCode, String accountNumber, Integer amount, String remark) {
        this.bankCode = bankCode;
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.remark = remark;
    }

    public String getBankCode() {
        return bankCode;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public Integer getAmount() {
        return amount;
    }

    public String getRemark() {
        return remark;
    }


    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
