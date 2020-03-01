package com.bank.nix.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "USER")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String registeredNumber;

	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<BankAccount> idBankAccounts;

	public User() {
	}

	public User(String name, String registeredNumber) {
		this.name = name;
		this.registeredNumber = registeredNumber;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRegisteredNumber() {
		return registeredNumber;
	}

	public void setRegisteredNumber(String registeredNumber) {
		this.registeredNumber = registeredNumber.replaceAll("[^0-9]", "");
	}

	public List<BankAccount> getIdBankAccounts() {
		return idBankAccounts;
	}

	public void setIdBankAccounts(List<BankAccount> idBankAccounts) {
		this.idBankAccounts = idBankAccounts;
	}

}
