package com.bank.nix.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.nix.domain.User;
import com.bank.nix.domain.dto.UserDTO;
import com.bank.nix.service.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/all")
	public List<UserDTO> findAll(){
		return userService.findAll().stream().map(
				c -> new UserDTO(c.getName(), c.getRegisteredNumber())).collect(Collectors.toList());
	}

	@GetMapping("/{id}")
	public UserDTO findById(@PathVariable  Long id) {
		User user = userService.findById(id);
		return new UserDTO(user.getName(), user.getRegisteredNumber());
	}

	@PostMapping("/create")
	public UserDTO create(@RequestBody UserDTO dto) {
		User user = userService.create(dto);
		return new UserDTO(user.getName(), user.getRegisteredNumber());
	}
}
