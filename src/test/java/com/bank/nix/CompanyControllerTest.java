package com.bank.nix;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bank.nix.domain.Company;
import com.bank.nix.service.CompanyService;

@SpringBootTest
class CompanyControllerTest {

	@Autowired
	private CompanyService companyService;

	@Test
	void findAll() {
		Company db = companyService.findAll().get(0);
		assertEquals("Caixa", db.getName());
	}

	@Test
	void findById() {
		Company db = companyService.findById(1L);
		assertEquals("Caixa", db.getName());
	}

	@Test
	void create() {
		Company company = new Company("Santander");
		Company newCompany = companyService.create(company);
		assertEquals(company.getName(), newCompany.getName());
	}
}
