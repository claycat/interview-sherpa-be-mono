package com.sherpa.interviewsherpa.auth.application.service.oauth;

import org.springframework.transaction.annotation.Transactional;

import com.sherpa.interviewsherpa.auth.application.port.in.OAuthSignInMemberCommand;
import com.sherpa.interviewsherpa.auth.application.port.in.OAuthSignInMemberResult;
import com.sherpa.interviewsherpa.auth.application.port.in.OAuthSignInMemberUseCase;
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

	public OAuthSignInMemberService(SaveOAuthMemberPort saveOAuthMemberPort, LoadOAuthMemberPort loadOAuthMemberPort) {
		this.saveOAuthMemberPort = saveOAuthMemberPort;
		this.loadOAuthMemberPort = loadOAuthMemberPort;
	}

	@Override
	@Transactional
	public OAuthSignInMemberResult signInOAuthMember(OAuthSignInMemberCommand command) {

		var member = loadOAuthMemberPort.loadOAuthMemberOrNull(command.getEmail());
		if (member.isEmpty()) {

			Member newMember = saveOAuthMemberPort.saveOAuthMember(Member.withoutId(
				command.getEmail(),
				command.getName(),
				command.getProfileURL())
			);

			log.info("New member created: {}", newMember.getEmail());
			return new OAuthSignInMemberResult(newMember, command.getOAuthProvider());
		}

		return new OAuthSignInMemberResult(member.get(), command.getOAuthProvider());
	}

}
