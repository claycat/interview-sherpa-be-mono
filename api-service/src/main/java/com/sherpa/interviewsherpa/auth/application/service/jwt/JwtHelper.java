package com.sherpa.interviewsherpa.auth.application.service.jwt;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtHelper {

	private final SecretKey secretKey;

	@Value("${spring.application.name}")
	private String issuer;

	@Value("${jwt.access-key-expiration-s}")
	private long accessKeyExpirationInS;

	public JwtHelper(@Value("${jwt.secret-key}") String secretKeyBase64) {
		this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKeyBase64));
	}

	private long getAccessKeyExpirationInMs() {
		return accessKeyExpirationInS * 1000;
	}

	public String createJwt(String email) {
		Date issuedAt = new Date(System.currentTimeMillis());

		return Jwts.builder()
			.setSubject(email)
			.setIssuer(issuer)
			.setIssuedAt(issuedAt)
			.setExpiration(new Date(issuedAt.getTime() + getAccessKeyExpirationInMs()))
			.signWith(secretKey)
			.compact();
	}
}