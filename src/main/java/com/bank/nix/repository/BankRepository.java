package com.bank.nix.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.nix.domain.Bank;

@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {

    Optional<Bank> findByCode(String code);

}
