package com.sherpa.interviewsherpa.member.application.port.out;

import java.util.UUID;

import com.sherpa.interviewsherpa.member.domain.Member;

public interface LoadMemberPort {

	Member loadMember(String email);

	Member loadMemberById(UUID id);

}
