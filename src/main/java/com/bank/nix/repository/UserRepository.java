package com.bank.nix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.nix.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
