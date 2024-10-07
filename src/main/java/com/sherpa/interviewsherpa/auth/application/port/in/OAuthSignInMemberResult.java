package com.sherpa.interviewsherpa.auth.application.port.in;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor(force = true)
public class OAuthSignInMemberResult {
	private String accessToken;

	public OAuthSignInMemberResult(String accessToken) {
		this.accessToken = accessToken;
	}
}
