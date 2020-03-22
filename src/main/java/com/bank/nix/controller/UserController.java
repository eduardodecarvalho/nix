package com.bank.nix.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bank.nix.domain.User;
import com.bank.nix.domain.dto.UserDTO;
import com.bank.nix.service.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping()
    public List<UserDTO> findAll() {
        return userService.findAll().stream().map(
                c -> new UserDTO(c.getName(), c.getRegisteredNumber())).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public UserDTO findById(@PathVariable final Long id) {
        final User user = userService.findById(id);
        return new UserDTO(user.getName(), user.getRegisteredNumber());
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Long create(@RequestBody final UserDTO dto) {
        return userService.create(dto);
    }

    @PutMapping("/{userId}")
    public void update(@RequestBody final UserDTO dto, @PathVariable final Long userId) {
        userService.update(dto, userId);
    }

    @DeleteMapping("/{userId}")
    public void delete(@PathVariable final Long userId) {
        userService.delete(userId);
    }

}
