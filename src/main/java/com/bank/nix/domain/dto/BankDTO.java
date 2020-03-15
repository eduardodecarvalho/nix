package com.bank.nix.domain.dto;

import java.io.Serializable;

public class BankDTO implements Serializable {

	private static final long serialVersionUID = -8327044367006275467L;

	private String name;
	private String code;

	public BankDTO(String name, String code) {
		this.name = name;
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
