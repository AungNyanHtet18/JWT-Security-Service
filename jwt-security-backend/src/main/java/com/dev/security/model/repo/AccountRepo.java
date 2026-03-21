package com.dev.security.model.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.security.model.entity.Account;

public interface AccountRepo extends JpaRepository<Account, Integer>{
	Optional<Account> findOneByEmail(String email);
}
