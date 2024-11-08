package com.sherpa.interviewsherpa.auth.application.port.in;

import com.sherpa.interviewsherpa.auth.application.service.oauth.OAuthProvider;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor(force = true)
public class OAuthSignInMemberCommand {
	private final String email;
	private final String name;
	private final String profileURL;
	private final OAuthProvider oAuthProvider;

	@Builder
	public OAuthSignInMemberCommand(String email, String name, String profileURL, OAuthProvider oAuthProvider) {
		this.email = email;
		this.name = name;
		this.profileURL = profileURL;
		this.oAuthProvider = oAuthProvider;
	}

}