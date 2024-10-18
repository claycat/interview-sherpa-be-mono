package com.sherpa.interviewsherpa.auth.application.port.in;

import com.sherpa.interviewsherpa.auth.application.service.oauth.OAuthProvider;
import com.sherpa.interviewsherpa.member.domain.Member;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@Builder
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class OAuthSignInMemberResult {

	private final Member member;
	private final OAuthProvider provider;

}
