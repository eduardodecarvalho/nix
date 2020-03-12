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

    public User(final String name, final String registeredNumber) {
        this.name = name;
        this.registeredNumber = registeredNumber.replaceAll("[^0-9]", "");
    }

    public User(final User user) {
        this.name = user.getName();
        this.registeredNumber = user.getRegisteredNumber();
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getRegisteredNumber() {
        return registeredNumber;
    }

    public List<BankAccount> getIdBankAccounts() {
        return idBankAccounts;
    }

    public void setIdBankAccounts(final List<BankAccount> idBankAccounts) {
        this.idBankAccounts = idBankAccounts;
    }

}