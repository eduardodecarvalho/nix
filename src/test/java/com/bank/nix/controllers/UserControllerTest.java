package com.bank.nix.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.bank.nix.domain.User;
import com.bank.nix.domain.dto.UserDTO;
import com.bank.nix.repository.UserRepository;
import com.bank.nix.utils.SpringBootIntegrationTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

class UserControllerTest extends SpringBootIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void findAll() throws Exception {
        final ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://localhost:" + port + "/users", String.class);
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        final String expected = " [ {" +
                "    \"name\": \"Maria\"," +
                "    \"registeredNumber\": \"12365478996\""
                + "} ] ";

        JSONAssert.assertEquals(expected, responseEntity.getBody(), true);
    }

    @Test
    void findById() throws Exception {
        final ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://localhost:" + port + "/users/1", String.class);
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        final String expected = " {" +
                "    \"name\": \"Maria\"," +
                "    \"registeredNumber\": \"12365478996\""
                + "} ";

        JSONAssert.assertEquals(expected, responseEntity.getBody(), true);
    }

    @Test
    void findByIdNotExistsShouldRetornError() throws JsonMappingException, JsonProcessingException {
        final ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://localhost:" + port + "/users/99", String.class);
        Assert.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    void create() throws Exception {

        final String dtoString = "{" +
                "    \"name\": \"João\"," +
                "    \"registeredNumber\": \"12345698700\""
                + "} ";

        final UserDTO dto = new ObjectMapper().readValue(dtoString, UserDTO.class);

        final ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://localhost:" + port + "/users", dto, String.class);
        Assert.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

        final Long createdId = Long.parseLong(responseEntity.getBody());

        final String actual = new ObjectMapper().writeValueAsString(new User(userRepository.findById(createdId).get()));

        JSONAssert.assertEquals(dtoString, actual, false);

    }

    @Test
    void createWithoutNameShouldReturnError() throws Exception {
        final String dtoString = "{" +
                "    \"registeredNumber\": \"12345698700\""
                + "} ";
        final UserDTO dto = new ObjectMapper().readValue(dtoString, UserDTO.class);
        final ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://localhost:" + port + "/users", dto, String.class);
        Assert.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    void createWithoutRegisteredNumberShouldReturnError() throws Exception {
        final String dtoString = "{" +
                "    \"name\": \"João\"" +
                "} ";
        final UserDTO dto = new ObjectMapper().readValue(dtoString, UserDTO.class);
        final ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://localhost:" + port + "/users", dto, String.class);
        Assert.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    void createWithoutDuplicatedRegisteredNumberShouldReturnError() throws Exception {
        final String dtoString = "{" +
                "    \"name\": \"João\"," +
                "    \"registeredNumber\": \"12365478996\"" +
                "} ";
        final UserDTO dto = new ObjectMapper().readValue(dtoString, UserDTO.class);
        final ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://localhost:" + port + "/users", dto, String.class);
        Assert.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    void updateUserName() throws Exception {
        final Long id = 1L;

        final String dtoString = " {" +
                "    \"name\": \"Maria da Silva\"," +
                "    \"registeredNumber\": \"12365478996\"" +
                "} ";
        final UserDTO dto = new ObjectMapper().readValue(dtoString, UserDTO.class);

        final ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:" + port + "/users" + id, HttpMethod.PUT, new HttpEntity<>(dto), String.class);
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        final User actual = new User(userRepository.findById(id).get());

        assertEquals(dto.getName().trim(), actual.getName());
    }

    @Test
    void deleteUser() {
        final Long idToDelete = 1L;

        final ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:" + port + "/users/" + idToDelete, HttpMethod.DELETE, null, String.class);
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        assertFalse(userRepository.findById(idToDelete).isPresent());
    }
}
