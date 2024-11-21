package com.sherpa.interviewsherpa.auth.application.port.out;

import java.util.Optional;
import java.util.UUID;

import com.sherpa.interviewsherpa.auth.domain.MemberFlowRole;

public interface GetMemberFlowRolePort {
	Optional<MemberFlowRole> getMemberFlowRole(UUID flowId, UUID memberId);
}
