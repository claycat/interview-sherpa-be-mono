package com.sherpa.interviewsherpa.member.application.port.out;

import com.sherpa.interviewsherpa.member.domain.Member;

public interface SaveOAuthMemberPort {

	Member saveOAuthMember(Member member);
}
