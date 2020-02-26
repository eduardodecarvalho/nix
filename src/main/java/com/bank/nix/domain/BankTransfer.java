package com.bank.nix.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.bank.nix.domain.enums.Type;

@Entity
@Table(name = "BANK_TRANSFER")
public class BankTransfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BankAccount idBankAccountRecipient;
    private BankAccount idBankAccountDepositor;
    private BigDecimal value;
    private Type type;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    @OneToOne
    @JoinColumn(name = "ID_BANK_ACCOUNT_RECIPIENT")
    public BankAccount getIdBankAccountRecipient() {
        return idBankAccountRecipient;
    }

    public void setIdBankAccountRecipient(final BankAccount idBankAccountRecipient) {
        this.idBankAccountRecipient = idBankAccountRecipient;
    }

    @OneToOne
    @JoinColumn(name = "ID_BANK_ACCOUNT_DEPOSITOR")
    public BankAccount getIdBankAccountDepositor() {
        return idBankAccountDepositor;
    }

    public void setIdBankAccountDepositor(final BankAccount idBankAccountDepositor) {
        this.idBankAccountDepositor = idBankAccountDepositor;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(final BigDecimal value) {
        this.value = value;
    }

    public Type getType() {
        return type;
    }

    public void setType(final Type type) {
        this.type = type;
    }

}
