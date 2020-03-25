package com.bank.nix.domain.dto;

import java.io.Serializable;

public class BankDTO implements Serializable {

    private static final long serialVersionUID = 4697264742234204116L;

    private String name;
    private String code;

    public BankDTO() {
    }

    public BankDTO(final String name, final String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(final String code) {
        this.code = code;
    }

}
