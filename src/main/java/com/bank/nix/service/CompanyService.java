package com.bank.nix.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.nix.domain.Company;
import com.bank.nix.repository.CompanyRepository;
import com.bank.nix.service.exception.NixBusinessException;

@Service
public class CompanyService {

	@Autowired
	private CompanyRepository companyRepository;

	public List<Company> findAll() {
		return companyRepository.findAll();
	}

	public Company findById(Long id) {
		return companyRepository.findById(id)
				.orElseThrow(() -> new NixBusinessException("Company not found"));
	}

	public Company create(Company company) {
		return companyRepository.save(company);
	}
}
