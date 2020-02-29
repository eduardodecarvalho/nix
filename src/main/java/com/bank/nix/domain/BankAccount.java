package com.bank.nix.domain;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "BANK_ACCOUNT")
public class BankAccount {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private User idUser;
	private String accountNumber;
	private String bankOffice;
	private Bank bank;
	private BigDecimal balance;
	@ElementCollection(targetClass=Integer.class)
	private List<BankTransfer> creditTransactions;
	@ElementCollection(targetClass=Integer.class)
	private List<BankTransfer> debitTransactions;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getBankOffice() {
		return bankOffice;
	}

	public void setBankOffice(String bankOffice) {
		this.bankOffice = bankOffice;
	}

	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	@ManyToOne
	@JoinColumn(name = "id_bank_account")
	public User getIdUser() {
		return idUser;
	}

	public void setIdUser(User idUser) {
		this.idUser = idUser;
	}

	@OneToMany(mappedBy = "BANK_TRANSFER")
	public List<BankTransfer> getCreditTransactions() {
		return creditTransactions;
	}

	public void setCreditTransactions(List<BankTransfer> creditTransactions) {
		this.creditTransactions = creditTransactions;
	}

	@OneToMany(mappedBy = "BANK_TRANSFER")
	public List<BankTransfer> getDebitTransactions() {
		return debitTransactions;
	}

	public void setDebitTransactions(List<BankTransfer> debitTransactions) {
		this.debitTransactions = debitTransactions;
	}

}
