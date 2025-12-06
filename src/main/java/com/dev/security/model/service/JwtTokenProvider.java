package com.dev.security.model.service;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import jakarta.validation.constraints.NotBlank;

@Service
public class JwtTokenProvider {

	public enum Type{
		 Access, Refresh
	}
	
	private SecretKey secret = Jwts.SIG.HS256.key().build();
	
	@Value("${app.jwt.issuer}")   //value injection
	private String issuer;
	@Value("${app.jwt.access-token}") 
	private int accessLife;
	@Value("${app.jwt.refresh-token}") 
	private int refreshLife;
	
	public String generateAccessToken(Authentication authentication) {
		return generate(authentication, Type.Access);
	}

	public String generateRefreshToken(Authentication authentication) {
		return generate(authentication, Type.Refresh);
	}
	
	
	public Authentication parseAccessToken(String token) {
		return parse(token, Type.Access);
	}
	
	public Authentication parseRefreshToken(@NotBlank(message = "Please enter refresh token.") String token) {
		return parse(token, Type.Refresh);
	}
	
	private String generate(Authentication authentication, Type type) {
		
		var issueAt = new Date();
		var calendar = Calendar.getInstance();
		calendar.setTime(issueAt);
		
		if(type == Type.Access) {
			calendar.add(Calendar.MINUTE, accessLife);
		}else if(type == Type.Refresh) {
			calendar.add(Calendar.MINUTE, refreshLife);
		}
		
		return Jwts.builder()
					.subject(authentication.getName())
					.claim("role", authentication.getAuthorities().stream()
							.map(a -> a.getAuthority()).collect(Collectors.joining(","))) // passing String Array ( ["ROLE_ADMIN", "ROLE_USER"] ) 
					.claim("type", type.name())
					.signWith(secret)
					.issuer(issuer)
					.issuedAt(issueAt)
					.expiration(calendar.getTime())
					.compact();	
	}
	
	private Authentication parse(String token, Type type) {
			
		var payLoad = Jwts.parser()
						  .verifyWith(secret)
						  .requireIssuer(issuer)
						  .require("type", type.name())
						  .build()
						  .parseSignedClaims(token).getPayload();
		
		var username = payLoad.getSubject();
		var roles = Arrays.stream(payLoad.get("role", String.class)
						.split(",")) //"ADMIN,USER,MANAGER"
						.map(a -> new SimpleGrantedAuthority(a)) 
						.toList();   //List <SimpleGrantedAuthority>
		
		return UsernamePasswordAuthenticationToken.authenticated(username, null, roles);
	}
}
