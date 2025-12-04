package com.dev.security.api.output;

import com.dev.security.model.entity.Account.Role;

public record AuthResult(
	String name,
	String email,
	Role role,
	String accessToken,
	String refreshToken) {

}
