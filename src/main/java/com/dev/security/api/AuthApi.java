package com.dev.security.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.security.api.input.RefreshForm;
import com.dev.security.api.input.SignInForm;
import com.dev.security.api.input.SignUpForm;
import com.dev.security.api.output.AuthResult;
import com.dev.security.model.service.AuthService;

@RestController
@RequestMapping("auth")
public class AuthApi {
	
	@Autowired
	private AuthService authService;
	
	@PostMapping("signin")
	AuthResult signIn(@RequestBody @Validated SignInForm form) {
		 return authService.signIn(form);
	}
	
	@PostMapping("refresh")
	AuthResult signUp(@RequestBody @Validated RefreshForm form) {
		 return authService.refresh(form);
	}
	
	@PostMapping("signup")
	AuthResult signUp(@RequestBody @Validated SignUpForm form) {
		 return authService.signUp(form);
	}	
}
