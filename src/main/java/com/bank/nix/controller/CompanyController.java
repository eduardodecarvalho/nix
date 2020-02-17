package com.bank.nix.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.nix.domain.dto.CompanyDTO;
import com.bank.nix.service.CompanyService;

@RestController
@RequestMapping(value = "/companies")
public class CompanyController {

	@Autowired
	private CompanyService companyService;

	@GetMapping("/all")
	public List<CompanyDTO> findAll(){
		companyService.findAll().stream().map(CompanyDTO::new);


	}

	@GetMapping("/{id}")
	public CompanyDTO findById(@PathVariable  Long id) {
		return companyService.findById(id);
	}

	@PostMapping
	public CompanyDTO create(@RequestBody CompanyDTO dto) {
		return companyService.create(dto);
	}
}
