package com.bank.nix;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bank.nix.domain.User;
import com.bank.nix.domain.dto.UserDTO;
import com.bank.nix.service.UserService;

@SpringBootTest
class UserControllerTest {

	@Autowired
	private UserService userService;

	@Test
	void findAll() {
		User db = userService.findAll().get(0);
		assertEquals("Maria", db.getName());
		assertEquals("12365478996", db.getRegisteredNumber());
	}

	@Test
	void findById() {
		User db = userService.findById(1L);
		assertEquals("Maria", db.getName());
		assertEquals("12365478996", db.getRegisteredNumber());
	}

	@Test
	void create() {
		UserDTO user = new UserDTO("João", "000.456.980-12");
		User newUser = userService.create(user);
		String registeredNumberToCompare = "00045698012";
		assertEquals(user.getName(), newUser.getName());
		assertEquals(registeredNumberToCompare, newUser.getRegisteredNumber());
	}
}
