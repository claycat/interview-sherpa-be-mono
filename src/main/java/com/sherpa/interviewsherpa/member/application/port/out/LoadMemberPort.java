package com.sherpa.interviewsherpa.member.application.port.out;

import java.util.Optional;

import com.sherpa.interviewsherpa.member.domain.Member;

public interface LoadMemberPort {

	Member loadMember(String email);

	Optional<Member> loadMemberOrNull(String email);
}
