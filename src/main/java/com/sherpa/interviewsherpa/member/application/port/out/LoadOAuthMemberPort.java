package com.sherpa.interviewsherpa.member.application.port.out;

import java.util.Optional;

import com.sherpa.interviewsherpa.member.domain.Member;

public interface LoadOAuthMemberPort {

	Member loadOAuthMember(String email);

	Optional<Member> loadOAuthMemberOrNull(String email);
}
