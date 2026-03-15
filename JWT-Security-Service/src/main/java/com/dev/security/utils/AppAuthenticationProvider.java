package com.dev.security.utils;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
//create this class to handle usernamenotfoundexception .As default, we cannot catch exception when user name is not found
public class AppAuthenticationProvider extends DaoAuthenticationProvider { 

	public AppAuthenticationProvider(AppUserDetailsService appUserDetailsService, PasswordEncoder passwordEncoder) {
		super(appUserDetailsService);
		setPasswordEncoder(passwordEncoder);
		setHideUserNotFoundExceptions(false); // set false,As default, it will be true
	}

}
