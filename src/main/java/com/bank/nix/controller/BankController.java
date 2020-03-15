package com.bank.nix.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bank.nix.domain.Bank;
import com.bank.nix.domain.dto.BankDTO;
import com.bank.nix.service.BankService;

@RestController
@RequestMapping(value = "/banks")
public class BankController {

	@Autowired
	private BankService bankService;

	@GetMapping("/all")
	public List<BankDTO> findAll() {
		return bankService.findAll().stream().map(
				c -> new BankDTO(c.getName(), c.getCode())).collect(Collectors.toList());
	}

	@GetMapping("/{id}")
	public BankDTO findById(@PathVariable final Long id) {
		final Bank bank = bankService.findById(id);
		return new BankDTO(bank.getName(), bank.getCode());
	}

	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public Long create(@RequestBody final BankDTO dto) {
		return bankService.create(dto);
	}

	//	@PutMapping("/update/{bankId}")
	//	public void update(@RequestBody final BankDTO dto, @PathVariable final Long bankId) {
	//		bankService.update(dto, bankId);
	//	}
	//
	//	@DeleteMapping("/delete/{bankId}")
	//	public void delete(@PathVariable final Long bankId) {
	//		bankService.delete(bankId);
	//	}

}
