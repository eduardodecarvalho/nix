package com.bank.nix.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.nix.domain.User;
import com.bank.nix.domain.dto.UserDTO;
import com.bank.nix.repository.UserRepository;
import com.bank.nix.service.exception.NixBusinessException;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User findById(Long id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new NixBusinessException("User not found"));
	}

	public User create(UserDTO dto) {
		User user = new User();
		user.setName(dto.getName());
		user.setRegisteredNumber(dto.getRegisteredNumber());
		return userRepository.save(user);
	}
}
