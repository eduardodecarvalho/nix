package com.bank.nix;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.bank.nix.domain.dto.UserDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
class UserControllerTest {

	@LocalServerPort
	public int randomServerPort;

	public RestTemplate restTemplate = new RestTemplate();

	@LocalServerPort
	public int port;

	@Test
	void findAll() throws JsonMappingException, JsonProcessingException {
		ResponseEntity<String> responseEntity =	restTemplate.getForEntity("http://localhost:" + port + "/users/all", String.class);
		Assert.assertEquals(200, responseEntity.getStatusCodeValue());

		UserDTO user = new ObjectMapper().readValue(responseEntity.getBody(), UserDTO.class);
		assertEquals("Maria", user.getName());
		assertEquals("12365478996", user.getRegisteredNumber());
	}

	@Test
	void findById() throws JsonMappingException, JsonProcessingException {
		ResponseEntity<String> responseEntity =	restTemplate.getForEntity("http://localhost:" + port + "/users/1", String.class);
		Assert.assertEquals(200, responseEntity.getStatusCodeValue());

		UserDTO user = new ObjectMapper().readValue(responseEntity.getBody(), UserDTO.class);

		assertEquals("Maria", user.getName());
		assertEquals("12365478996", user.getRegisteredNumber());
	}

	@Test
	void findByIdNotExistsShouldRetornError() throws JsonMappingException, JsonProcessingException {
		ResponseEntity<String> responseEntity =	restTemplate.getForEntity("http://localhost:" + port + "/users/99", String.class);
		Assert.assertEquals(500, responseEntity.getStatusCodeValue());
	}


	@Test
	void create() {
		String user = " { "
				+ " 'name':'João', "
				+ " 'registeredNumber':'000.456.980-12' "
				+ " } ";

		ResponseEntity<UserDTO> responseEntity = restTemplate.postForEntity("http://localhost:" + port + "/users/create", user, UserDTO.class);
		Assert.assertEquals(200, responseEntity.getStatusCodeValue());

	}
}
