package com.dev.security.utils.exception;

import org.springframework.security.core.AuthenticationException;

public class TokenExpirationException extends AuthenticationException{

	private static final long serialVersionUID = 1L;

	public TokenExpirationException(String msg) {
		super(msg);
	}
	
}
