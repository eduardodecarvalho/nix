package com.bank.nix.domain;

import java.math.BigDecimal;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class BankTransfer {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private BankAccount idRecipient;
	private BankAccount idDepositor;
	private BigDecimal value;
}
