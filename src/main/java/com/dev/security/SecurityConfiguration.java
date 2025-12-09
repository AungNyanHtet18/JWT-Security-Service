package com.dev.security;

import static org.springframework.security.config.Customizer.withDefaults;

import java.time.LocalDateTime;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.ExceptionTranslationFilter;

import com.dev.security.model.entity.Account;
import com.dev.security.model.entity.Account.Role;
import com.dev.security.model.repo.AccountRepo;
import com.dev.security.model.service.JwtTokenFilter;
import com.dev.security.utils.exception.SecurityExceptionHandler;


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
		 //ExceptionTranslationFilter handle authentication and authorization exception
		 
		 http.exceptionHandling(exception -> {
			  exception.authenticationEntryPoint(securityExceptionHandler());
			  exception.accessDeniedHandler(securityExceptionHandler());
		 });
		 
		 
		 
		 return http.build();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		 return new BCryptPasswordEncoder();
	}
	
	@Bean
	JwtTokenFilter jwtTokenFilter() {
		 return new JwtTokenFilter();
	}
	
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) {
		 return authenticationConfiguration.getAuthenticationManager();
	}
	
	@Bean
	ApplicationRunner applicationRunner(AccountRepo accountRepo) {
		 return args -> {
			   if(accountRepo.count() == 0L) {
				    var admin = new Account();
				    admin.setName("Admin User");
				    admin.setEmail("admin@gmail.com");
				    admin.setRole(Role.Admin);
				    admin.setPassword(passwordEncoder().encode("password"));
				    admin.setActivatedAt(LocalDateTime.now());
				    accountRepo.save(admin);
			   }
		 };
	} //Run this bean  when application builds
	
	@Bean
	SecurityExceptionHandler securityExceptionHandler() {
		 return new SecurityExceptionHandler();
	}
	
}
