package com.bank.nix.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name = "credit_transactions")
	public BankAccount getIdBankAccountRecipient() {
		return idBankAccountRecipient;
	}

	public void setIdBankAccountRecipient(BankAccount idBankAccountRecipient) {
		this.idBankAccountRecipient = idBankAccountRecipient;
	}

	@ManyToOne
	@JoinColumn(name = "debit_Transactions")
	public BankAccount getIdBankAccountDepositor() {
		return idBankAccountDepositor;
	}

	public void setIdBankAccountDepositor(BankAccount idBankAccountDepositor) {
		this.idBankAccountDepositor = idBankAccountDepositor;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

}
