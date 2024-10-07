package com.sherpa.interviewsherpa.auth.application.service.oauth;

import java.util.Map;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Builder
@Getter
public class OAuthAttributes {

	private final String name;
	private final String email;
	private final String profileURL;

	public OAuthAttributes(String name, String email, String profileURL) {
		this.name = name;
		this.email = email;
		this.profileURL = profileURL;
	}

	public static OAuthAttributes of(OAuthProvider oAuthProvider, Map<String, Object> attributes) {

		switch (oAuthProvider) {
			case GOOGLE:
				return ofGoogle(attributes);
			default:
				throw new IllegalArgumentException("No OAuthProvider available for value: " + oAuthProvider);
		}
	}

	private static OAuthAttributes ofGoogle(Map<String, Object> attributes) {
		return new OAuthAttributes(
			(String)attributes.get("name"),
			(String)attributes.get("email"),
			(String)attributes.get("picture")
		);
	}

}
