package com.sherpa.interviewsherpa.auth.adapter.in.web;

import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sherpa.interviewsherpa.auth.application.port.in.OAuthSignInMemberCommand;
import com.sherpa.interviewsherpa.auth.application.port.in.OAuthSignInMemberUseCase;
import com.sherpa.interviewsherpa.auth.application.service.oauth.OAuthAttributes;
import com.sherpa.interviewsherpa.auth.application.service.oauth.OAuthProvider;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class OAuthSignInController {

	private final OAuthSignInMemberUseCase oAuthSignInMemberUseCase;

	public OAuthSignInController(OAuthSignInMemberUseCase oAuthSignInMemberUseCase) {
		this.oAuthSignInMemberUseCase = oAuthSignInMemberUseCase;
	}

	@GetMapping("/signin/success")
	@ResponseStatus(HttpStatus.SEE_OTHER)
	public void signIn(OAuth2AuthenticationToken authentication, HttpServletResponse response) {

		OAuth2User user = authentication.getPrincipal();
		var userAttributes = user.getAttributes();
		var oAuthProvider = authentication.getAuthorizedClientRegistrationId();
		var attributes = OAuthAttributes.of(OAuthProvider.fromValue(oAuthProvider), userAttributes);

		var signInMemberResult = oAuthSignInMemberUseCase.signInOAuthMember(OAuthSignInMemberCommand.builder()
			.email(attributes.getEmail())
			.name(attributes.getName())
			.profileURL(attributes.getProfileURL())
			.build()
		);

		var accessToken = signInMemberResult.getAccessToken();
		response.addHeader("Set-Cookie", "accessToken=" + accessToken + "; Path=/; HttpOnly; Secure; SameSite=None");
		response.setHeader("Location", "http://localhost:3000");
	}
}
