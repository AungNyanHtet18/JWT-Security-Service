package com.dev.security.api.output;

import com.dev.security.model.entity.Account;
import com.dev.security.model.entity.Account.Role;

public record AuthResult(
	String name,
	String email,
	Role role,
	String accessToken,
	String refreshToken) {

	public static AuthResult from(Account account, String accessToken, String refreshToken) {
		return new AuthResult(
				account.getName(), 
				account.getEmail(), 
				account.getRole(), 
				accessToken, 
				refreshToken);
	}
}
