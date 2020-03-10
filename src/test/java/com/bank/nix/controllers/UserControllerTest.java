package com.bank.nix.controllers;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
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
        final ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://localhost:" + port + "/users/all", String.class);
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

        final ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://localhost:" + port + "/users/create", dto, String.class);
        Assert.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

        final Long createdId = Long.parseLong(responseEntity.getBody());

        final String actual = new ObjectMapper().writeValueAsString(new User(userRepository.findById(createdId).get()));

        JSONAssert.assertEquals(dtoString, actual, false);

    }
}
