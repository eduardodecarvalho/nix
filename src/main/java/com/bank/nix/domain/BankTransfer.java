package com.bank.nix.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.bank.nix.domain.enums.TransferType;

@Entity
@Table(name = "BANK_TRANSFER")
public class BankTransfer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_accout_credit")
	private BankAccount bankAccountCredit;

	@ManyToOne
	@JoinColumn(name = "id_accout_debit")
	private BankAccount bankAccountDebit;
	private BigDecimal value;
	private TransferType type;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setBankAccountDebit(BankAccount bankAccountDebit) {
		this.bankAccountDebit = bankAccountDebit;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public TransferType getType() {
		return type;
	}

	public void setType(TransferType type) {
		this.type = type;
	}

	public BankAccount getBankAccountCredit() {
		return bankAccountCredit;
	}

	public void setBankAccountCredit(BankAccount bankAccountCredit) {
		this.bankAccountCredit = bankAccountCredit;
	}

	public BankAccount getBankAccountDebit() {
		return bankAccountDebit;
	}

}
