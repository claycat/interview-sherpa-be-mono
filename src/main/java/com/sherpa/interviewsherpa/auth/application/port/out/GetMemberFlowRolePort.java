package com.sherpa.interviewsherpa.auth.application.port.out;

import java.util.UUID;

import com.sherpa.interviewsherpa.auth.domain.MemberFlowRole;

public interface GetMemberFlowRolePort {
	MemberFlowRole getMemberFlowRole(UUID flowId, UUID memberId);
}
