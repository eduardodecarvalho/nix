package com.bank.nix.domain.dto;

import java.math.BigDecimal;

public class BankAccountDTO {

    private Long idUser;

    private String accountNumber;
    private String bankOffice;
    private BigDecimal balance;

    private Long idBank;

    public BankAccountDTO(final Long idUser, final String accountNumber, final String bankOffice, final BigDecimal balance, final Long idBank) {
        this.idUser = idUser;
        this.accountNumber = accountNumber;
        this.bankOffice = bankOffice;
        this.balance = balance;
        this.idBank = idBank;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(final Long idUser) {
        this.idUser = idUser;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(final String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getBankOffice() {
        return bankOffice;
    }

    public void setBankOffice(final String bankOffice) {
        this.bankOffice = bankOffice;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(final BigDecimal balance) {
        this.balance = balance;
    }

    public Long getIdBank() {
        return idBank;
    }

    public void setIdBank(final Long idBank) {
        this.idBank = idBank;
    }

}
