package com.bank.nix.service;

import java.util.List;
import java.util.Optional;

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

    public User findById(final Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NixBusinessException(NixBusinessException.USER_NOT_FOUND));
    }

    public Long create(final UserDTO dto) {
        if (dto.getName() == null || dto.getRegisteredNumber() == null) {
            throw new NixBusinessException(NixBusinessException.USER_INVALID_DATA);
        }
        final Optional<User> verifyRegisteredNumber = userRepository.findByRegisteredNumber(dto.getRegisteredNumber());
        if (verifyRegisteredNumber.isPresent()) {
            throw new NixBusinessException(NixBusinessException.REGISTERED_NUMBER_ALREDY_IN_USE);
        }

        final User user = new User(dto.getName(), dto.getRegisteredNumber());
        return userRepository.save(user).getId();
    }

    public void update(final UserDTO dto, final Long userId) {
        final User user = new User(dto.getName(), dto.getRegisteredNumber());
        user.setId(userId);
        userRepository.save(user).getId();
    }
}
