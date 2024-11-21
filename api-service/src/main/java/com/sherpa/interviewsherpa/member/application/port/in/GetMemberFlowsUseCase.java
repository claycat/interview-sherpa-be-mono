package com.sherpa.interviewsherpa.member.application.port.in;

import java.util.List;
import java.util.UUID;

import com.sherpa.interviewsherpa.flow.application.port.in.dto.getflow.MemberFlowQueryModel;

public interface GetMemberFlowsUseCase {
	List<MemberFlowQueryModel> getMemberFlows(UUID memberId);
}
