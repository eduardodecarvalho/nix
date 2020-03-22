package com.bank.nix.service;

import java.util.List;
import java.util.Optional;

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

    public Bank findById(final Long id) {
        return bankRepository.findById(id)
                .orElseThrow(() -> new NixBusinessException(NixBusinessException.BANK_NOT_FOUND));
    }

    public Long create(final BankDTO dto) {
        if (dto.getName() == null || dto.getCode() == null) {
            throw new NixBusinessException(NixBusinessException.BANK_INVALID_DATA);
        }
        final Optional<Bank> verifyCode = bankRepository.findByCode(dto.getCode());
        if (verifyCode.isPresent()) {
            throw new NixBusinessException(NixBusinessException.CODE_ALREDY_IN_USE);
        }

        final Bank bank = new Bank(dto.getName(), dto.getCode());
        return bankRepository.save(bank).getId();
    }

    public void update(final BankDTO dto, final Long bankId) {
        final Bank bank = bankRepository.findById(bankId).orElseThrow(() -> new NixBusinessException(NixBusinessException.BANK_NOT_FOUND));

        if (dto.getName() == null || dto.getCode() == null) {
            throw new NixBusinessException(NixBusinessException.BANK_INVALID_DATA);
        }

        bank.setId(bankId);

        bankRepository.save(bank).getId();
    }
}
