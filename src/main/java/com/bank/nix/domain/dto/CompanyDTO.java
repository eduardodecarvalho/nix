package com.bank.nix.domain.dto;

import java.io.Serializable;

public class CompanyDTO implements Serializable {

	private static final long serialVersionUID = 3878656591186207046L;

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
