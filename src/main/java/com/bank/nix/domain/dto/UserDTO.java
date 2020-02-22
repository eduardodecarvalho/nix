package com.bank.nix.domain.dto;

import java.io.Serializable;

public class UserDTO implements Serializable {

	private static final long serialVersionUID = 3878656591186207046L;

	private String name;
	private String registeredNumber;

	public UserDTO() {
	}

	public UserDTO(String name, String registeredNumber) {
		this.name = name;
		this.registeredNumber = registeredNumber;
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
		this.registeredNumber = registeredNumber;
	}

}
