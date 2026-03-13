package com.dev.security.api.input;

import java.time.LocalDateTime;
import com.dev.security.model.entity.Account;
import com.dev.security.model.entity.Account.Role;
import jakarta.validation.constraints.NotBlank;

public record SignUpForm(
	@NotBlank(message = "Please enter your name.")
	String username,
	@NotBlank(message =  "Please enter email for login.")
	String email) {

	public Account entity() {
		var account = new Account();
		account.setName(username);
		account.setEmail(email);
		account.setRole(Role.Member);
		account.setActivatedAt(LocalDateTime.now());
		return account;
	}
}
