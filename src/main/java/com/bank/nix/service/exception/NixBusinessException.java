package com.bank.nix.service.exception;

public class NixBusinessException  extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public static final String USER_NOT_FOUND = "user_not_found";
	public static final String REGISTERED_NUMBER_ALREDY_IN_USE = "registered_number_alredy_in_use";
	public static final String ACCOUNT_INVALID_DATA = "account_invalid_data";
	public static final String USER_INVALID_DATA = "user_invalid_data";

	public NixBusinessException(String msg) {
		super(msg);
	}

}
