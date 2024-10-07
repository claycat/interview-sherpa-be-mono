package com.sherpa.interviewsherpa.auth.application.service.oauth;

import org.springframework.transaction.annotation.Transactional;

import com.sherpa.interviewsherpa.auth.application.port.in.OAuthSignInMemberCommand;
import com.sherpa.interviewsherpa.auth.application.port.in.OAuthSignInMemberResult;
import com.sherpa.interviewsherpa.auth.application.port.in.OAuthSignInMemberUseCase;
import com.sherpa.interviewsherpa.auth.application.service.jwt.JwtHelper;
import com.sherpa.interviewsherpa.common.annotation.UseCase;
import com.sherpa.interviewsherpa.member.application.port.out.LoadOAuthMemberPort;
import com.sherpa.interviewsherpa.member.application.port.out.SaveOAuthMemberPort;
import com.sherpa.interviewsherpa.member.domain.Member;

import lombok.extern.slf4j.Slf4j;

@UseCase
@Slf4j
public class OAuthSignInMemberService implements OAuthSignInMemberUseCase {

	private final SaveOAuthMemberPort saveOAuthMemberPort;
	private final LoadOAuthMemberPort loadOAuthMemberPort;
	private final JwtHelper jwtHelper;

	public OAuthSignInMemberService(SaveOAuthMemberPort saveOAuthMemberPort, LoadOAuthMemberPort loadOAuthMemberPort,
		JwtHelper jwtHelper) {
		this.saveOAuthMemberPort = saveOAuthMemberPort;
		this.loadOAuthMemberPort = loadOAuthMemberPort;
		this.jwtHelper = jwtHelper;
	}

	@Override
	@Transactional
	public OAuthSignInMemberResult signInOAuthMember(OAuthSignInMemberCommand command) {

		var member = loadOAuthMemberPort.loadOAuthMemberOrNull(command.getEmail());
		if (member.isPresent()) {
			return new OAuthSignInMemberResult(jwtHelper.createJwt(member.get().getEmail()));
		}

		Member newMember = Member.withoutId(
			command.getEmail(),
			command.getName(),
			command.getProfileURL()
		);

		log.info("New member created: {}", newMember.getEmail());
		saveOAuthMemberPort.saveOAuthMember(newMember);
		return new OAuthSignInMemberResult(jwtHelper.createJwt(newMember.getEmail()));
	}
}
