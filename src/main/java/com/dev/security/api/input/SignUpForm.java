package com.dev.security.api.input;

import java.time.LocalDateTime;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.dev.security.model.entity.Account;
import com.dev.security.model.entity.Account.Role;

import jakarta.validation.constraints.NotBlank;

public record SignUpForm(
	@NotBlank(message = "Please enter your name.")
	String name,
	@NotBlank(message =  "Please enter email for login.")
	String username,
	@NotBlank(message = "Please enter password.")
	String password	) {

	public Account entity(PasswordEncoder encoder) {
		var account = new Account();
		account.setName(name);
		account.setEmail(username);
		account.setRole(Role.Member);
		account.setPassword(encoder.encode(password));
		account.setActivatedAt(LocalDateTime.now());
		return account;
	}
}
