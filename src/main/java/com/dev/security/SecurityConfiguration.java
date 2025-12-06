package com.dev.security;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password4j.BcryptPassword4jPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.ExceptionTranslationFilter;

import com.dev.security.model.service.JwtTokenFilter;


@Configuration
public class SecurityConfiguration {
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) {
		 http.csrf(csrf -> csrf.disable()); //csrf is disable
		 http.cors(withDefaults()); //default
		 http.sessionManagement(session -> {
			 session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);  // RestAPI does not handles states because of stateless
		 });
		 
		 http.authorizeHttpRequests(request -> {
			  request.requestMatchers("/auth/**").permitAll();
			  request.requestMatchers("/admin/**").hasAuthority("Admin");
			  request.requestMatchers("/member/**").hasAnyAuthority("Admin","Member");
			  request.anyRequest().authenticated();
		 });
		 
		 http.addFilterAfter(jwtTokenFilter(), ExceptionTranslationFilter.class); //jwtTokeFilter runs after ExceptionTranslationFilter
		 
		 return http.build();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		 return new BcryptPassword4jPasswordEncoder();
	}
	
	
	@Bean
	JwtTokenFilter jwtTokenFilter() {
		 return new JwtTokenFilter();
	}
	
}
