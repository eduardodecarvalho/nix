package com.bank.nix.domain.enums;

public enum Type {

	TED(1, "Ted"),
	DOC(2, "Doc");

	private int cod;
	private String description;

	private Type(int cod, String description) {
		this.cod = cod;
		this.description = description;
	}

	public int getCod() {
		return cod;
	}

	public String getDescription() {
		return description;
	}

	public static Type toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}

		for(Type x : Type.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}

		throw new IllegalArgumentException("Invalid id: " + cod);
	}
}
