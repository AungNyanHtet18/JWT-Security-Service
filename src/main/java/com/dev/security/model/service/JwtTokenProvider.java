package com.dev.security.model.service;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import jakarta.validation.constraints.NotBlank;

@Service
public class JwtTokenProvider {

	public Authentication parseRefreshToken(@NotBlank(message = "Please enter refresh token.") String token) {
		
		return null;
	}

	public String generateAccessToken(Authentication authentication) {
		
		return null;
	}

	public String generateRefreshToken(Authentication authentication) {
		
		return null;
	}

}
