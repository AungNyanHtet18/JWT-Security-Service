package com.dev.security.api.input;

import jakarta.validation.constraints.NotBlank;

public record SignInForm(
	@NotBlank(message =  "Please enter email for login.")
	String username,
	@NotBlank(message = "Please enter password.")
	String password) {

}
