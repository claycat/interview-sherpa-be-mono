package com.sherpa.interviewsherpa.auth.application.service.oauth;

import java.util.Map;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class OAuthAttributes {

	private final String name;
	private final String email;
	private final String profileURL;
	private final OAuthProvider oAuthProvider;

	public OAuthAttributes(OAuthProvider provider, String name, String email, String profileURL) {
		this.oAuthProvider = provider;
		this.name = name;
		this.email = email;
		this.profileURL = profileURL;
	}

	public static OAuthAttributes of(OAuthProvider oAuthProvider, Map<String, Object> attributes) {

		return switch (oAuthProvider) {
			case GOOGLE -> ofGoogle(attributes);
			default -> throw new IllegalArgumentException("No OAuthProvider available for value: " + oAuthProvider);
		};
	}

	private static OAuthAttributes ofGoogle(Map<String, Object> attributes) {
		return new OAuthAttributes(
			OAuthProvider.GOOGLE,
			(String)attributes.get("name"),
			(String)attributes.get("email"),
			(String)attributes.get("picture")
		);
	}

}
