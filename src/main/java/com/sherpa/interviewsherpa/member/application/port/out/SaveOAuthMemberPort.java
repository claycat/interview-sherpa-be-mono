package com.sherpa.interviewsherpa.member.application.port.out;

import com.sherpa.interviewsherpa.member.domain.Member;

public interface SaveOAuthMemberPort {

	void saveOAuthMember(Member member);
}
