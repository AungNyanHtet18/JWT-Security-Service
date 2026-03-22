package com.dev.security.api.input;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import jakarta.validation.constraints.NotBlank;

public record SignInForm(
	@NotBlank(message =  "Please enter email for login.")
	String email,
	@NotBlank(message = "Please enter password.")
	String password) {

	public Authentication authentication() {
		return UsernamePasswordAuthenticationToken.unauthenticated(email, password);
	}
}
