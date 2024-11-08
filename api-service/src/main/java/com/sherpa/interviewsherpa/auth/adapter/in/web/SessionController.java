package com.sherpa.interviewsherpa.auth.adapter.in.web;

import java.util.Collections;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;

@RestController
public class SessionController {

	private final HttpSession httpSession;

	public SessionController(HttpSession httpSession) {
		this.httpSession = httpSession;
	}

	@GetMapping("/session")
	public ResponseEntity<Map<String, Object>> getSession() {
		SecurityContext context = (SecurityContext)httpSession.getAttribute(
			HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY);
		if (context != null && context.getAuthentication() != null && context.getAuthentication()
			.getPrincipal() instanceof OAuth2User user) {
			Map<String, Object> userAttributes = user.getAttributes();
			return ResponseEntity.ok(userAttributes);
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Collections.emptyMap());
		}
	}
}
