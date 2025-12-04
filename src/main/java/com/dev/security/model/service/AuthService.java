package com.dev.security.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.security.api.input.RefreshForm;
import com.dev.security.api.input.SignInForm;
import com.dev.security.api.input.SignUpForm;
import com.dev.security.api.output.AuthResult;
import com.dev.security.model.repo.AccountRepo;

@Service
@Transactional(readOnly = true)
public class AuthService {

	@Autowired
	private AccountRepo repo;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtTokenProvider tokenProvider;
	
	@Transactional
	public AuthResult signUp(SignUpForm form) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	public AuthResult signIn(SignInForm form) {
		
		return null;
	}

	public AuthResult refresh(RefreshForm form) {
		
		
		
		return null;
	}



}
