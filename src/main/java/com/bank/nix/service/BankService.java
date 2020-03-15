package com.bank.nix.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.nix.domain.Bank;
import com.bank.nix.domain.dto.BankDTO;
import com.bank.nix.repository.BankRepository;
import com.bank.nix.service.exception.NixBusinessException;

@Service
public class BankService {

	@Autowired
	private BankRepository bankRepository;

	public List<Bank> findAll() {
		return bankRepository.findAll();
	}

	public Bank findById(Long id) {
		return bankRepository.findById(id).orElseThrow(() -> new NixBusinessException(NixBusinessException.BANK_NOT_FOUND));
	}

	public Long create(BankDTO dto) {
		Bank bank = new Bank(dto.getName(), dto.getCode());
		return bankRepository.save(bank).getId();
	}

}
