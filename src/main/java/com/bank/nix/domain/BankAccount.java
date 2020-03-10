package com.bank.nix.domain;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "BANK_ACCOUNT")
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    private String accountNumber;
    private String bankOffice;
    private BigDecimal balance;

    @ManyToOne
    @JoinColumn(name = "id_bank")
    private Bank bank;

    @JsonIgnore
    @OneToMany(mappedBy = "bankAccountCredit")
    private List<BankTransfer> creditTransactions;

    @JsonIgnore
    @OneToMany(mappedBy = "bankAccountDebit")
    private List<BankTransfer> debitTransactions;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(final User user) {
        this.user = user;
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

    public List<BankTransfer> getCreditTransactions() {
        return creditTransactions;
    }

    public void setCreditTransactions(final List<BankTransfer> creditTransactions) {
        this.creditTransactions = creditTransactions;
    }

    public List<BankTransfer> getDebitTransactions() {
        return debitTransactions;
    }

    public void setDebitTransactions(final List<BankTransfer> debitTransactions) {
        this.debitTransactions = debitTransactions;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(final Bank bank) {
        this.bank = bank;
    }

}
