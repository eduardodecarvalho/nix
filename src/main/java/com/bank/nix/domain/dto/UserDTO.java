package com.bank.nix.domain.dto;

import java.io.Serializable;

public class UserDTO implements Serializable {

    private static final long serialVersionUID = 3878656591186207046L;

    private String name;
    private String registeredNumber;

    public UserDTO() {
    }

    public UserDTO(final String name, final String registeredNumber) {
        this.name = name;
        this.registeredNumber = registeredNumber;
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

    public void setRegisteredNumber(final String registeredNumber) {
        this.registeredNumber = registeredNumber;
    }

}
