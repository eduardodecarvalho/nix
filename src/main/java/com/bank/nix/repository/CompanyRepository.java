package com.bank.nix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.nix.domain.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long>{

}
