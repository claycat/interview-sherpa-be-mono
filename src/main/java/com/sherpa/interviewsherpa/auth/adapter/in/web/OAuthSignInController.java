package com.sherpa.interviewsherpa.auth.adapter.in.web;

import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.sherpa.interviewsherpa.auth.application.port.in.OAuthSignInMemberCommand;
import com.sherpa.interviewsherpa.auth.application.port.in.OAuthSignInMemberUseCase;
import com.sherpa.interviewsherpa.auth.application.service.oauth.OAuthAttributes;
import com.sherpa.interviewsherpa.auth.application.service.oauth.OAuthProvider;
import com.sherpa.interviewsherpa.member.domain.Member;

import jakarta.servlet.http.HttpSession;

@RestController
public class OAuthSignInController {

	private final OAuthSignInMemberUseCase oAuthSignInMemberUseCase;

	@Value("${url.client}")
	private String clientURL;

	public OAuthSignInController(OAuthSignInMemberUseCase oAuthSignInMemberUseCase) {
		this.oAuthSignInMemberUseCase = oAuthSignInMemberUseCase;
	}

	@GetMapping("/signin/success")
	public RedirectView signIn(OAuth2AuthenticationToken authentication, HttpSession session) {

		OAuth2User user = authentication.getPrincipal();
		var userAttributes = user.getAttributes();
		var oAuthProvider = authentication.getAuthorizedClientRegistrationId();
		var attributes = OAuthAttributes.of(OAuthProvider.fromValue(oAuthProvider), userAttributes);

		var result = oAuthSignInMemberUseCase.signInOAuthMember(OAuthSignInMemberCommand.builder()
			.email(attributes.getEmail())
			.name(attributes.getName())
			.profileURL(attributes.getProfileURL())
			.oAuthProvider(attributes.getOAuthProvider())
			.build()
		);

		setSessionAuthentication(result.getMember(), result.getProvider(), session);

		String redirectURL = clientURL + "/oauth/callback?success=true";
		RedirectView redirectView = new RedirectView(redirectURL);
		redirectView.setStatusCode(HttpStatus.SEE_OTHER);
		return redirectView;
	}

	private void setSessionAuthentication(Member member, OAuthProvider provider, HttpSession session) {

		Map<String, Object> attributes = Map.of(
			"email", member.getEmail(),
			"name", member.getName(),
			"profileURL", member.getProfileURL(),
			"id", member.getId()
		);

		OAuth2User oAuth2User = new DefaultOAuth2User(
			Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")),
			attributes,
			"email"
		);

		var authentication = new OAuth2AuthenticationToken(
			oAuth2User,
			oAuth2User.getAuthorities(),
			provider.name()
		);

		SecurityContext context = SecurityContextHolder.createEmptyContext();
		context.setAuthentication(authentication);
		session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, context);
	}
}
