package com.bank.nix.controllers;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.bank.nix.domain.Bank;
import com.bank.nix.domain.dto.BankDTO;
import com.bank.nix.domain.dto.UserDTO;
import com.bank.nix.repository.BankRepository;
import com.bank.nix.utils.SpringBootIntegrationTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

class BankControllerTest extends SpringBootIntegrationTest {

	@Autowired
	private BankRepository bankRepository;

	@Test
	void findAll() throws Exception {
		final ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://localhost:" + port + "/banks/all", String.class);
		Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

		final String expected = " [ {" +
				"    \"name\": \"Banco do Brasil\"," +
				"    \"code\": \"001\""
				+ "} ] ";

		JSONAssert.assertEquals(expected, responseEntity.getBody(), true);
	}

	@Test
	void findById() throws Exception {
		final ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://localhost:" + port + "/banks/1", String.class);
		Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

		final String expected = " {" +
				"    \"name\": \"Banco do Brasil\"," +
				"    \"code\": \"001\""
				+ "} ";

		JSONAssert.assertEquals(expected, responseEntity.getBody(), true);
	}

	@Test
	void findByIdNotExistsShouldRetornError() throws JsonMappingException, JsonProcessingException {
		final ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://localhost:" + port + "/banks/99", String.class);
		Assert.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
	}

	@Test
	void create() throws Exception {

		final String dtoString = " {" +
				"    \"name\": \"Caixa Econômica Federal\"," +
				"    \"code\": \"104\""
				+ "} ";

		final BankDTO dto = new ObjectMapper().readValue(dtoString, BankDTO.class);

		final ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://localhost:" + port + "/banks/create", dto, String.class);
		Assert.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

		final Long createdId = Long.parseLong(responseEntity.getBody());

		final String actual = new ObjectMapper().writeValueAsString(new Bank(bankRepository.findById(createdId).get()));

		JSONAssert.assertEquals(dtoString, actual, false);

	}

	@Test
	void createWithoutNameShouldReturnError() throws Exception {
		final String dtoString = "{" +
				"    \"registeredNumber\": \"12345698700\""
				+ "} ";
		final UserDTO dto = new ObjectMapper().readValue(dtoString, UserDTO.class);
		final ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://localhost:" + port + "/users/create", dto, String.class);
		Assert.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
	}

	@Test
	void createWithoutRegisteredNumberShouldReturnError() throws Exception {
		final String dtoString = "{" +
				"    \"name\": \"João\"" +
				"} ";
		final UserDTO dto = new ObjectMapper().readValue(dtoString, UserDTO.class);
		final ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://localhost:" + port + "/users/create", dto, String.class);
		Assert.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
	}

	@Test
	void createWithoutDuplicatedRegisteredNumberShouldReturnError() throws Exception {
		final String dtoString = "{" +
				"    \"name\": \"João\"," +
				"    \"registeredNumber\": \"12365478996\"" +
				"} ";
		final UserDTO dto = new ObjectMapper().readValue(dtoString, UserDTO.class);
		final ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://localhost:" + port + "/users/create", dto, String.class);
		Assert.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
	}

	//	@Test
	//	void updateUserName() throws Exception {
	//		final Long id = 1L;
	//
	//		final String dtoString = " {" +
	//				"    \"name\": \"Maria da Silva\"," +
	//				"    \"registeredNumber\": \"12365478996\"" +
	//				"} ";
	//		final UserDTO dto = new ObjectMapper().readValue(dtoString, UserDTO.class);
	//
	//		final ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:" + port + "/users/update/" + id, HttpMethod.PUT, new HttpEntity<>(dto), String.class);
	//		Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	//
	//		final User actual = new User(userRepository.findById(id).get());
	//
	//		assertEquals(dto.getName().trim(), actual.getName());
	//	}
	//
	//	@Test
	//	void deleteUser() {
	//		final Long idToDelete = 1L;
	//
	//		final ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:" + port + "/users/delete/" + idToDelete, HttpMethod.DELETE, null, String.class);
	//		Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	//
	//		assertFalse(userRepository.findById(idToDelete).isPresent());
	//	}
}
