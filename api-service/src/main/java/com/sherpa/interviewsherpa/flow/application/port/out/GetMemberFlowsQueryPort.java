package com.sherpa.interviewsherpa.flow.application.port.out;

import java.util.List;
import java.util.UUID;

import com.sherpa.interviewsherpa.flow.application.port.in.dto.getflow.MemberFlowQueryModel;

public interface GetMemberFlowsQueryPort {
	List<MemberFlowQueryModel> getMemberFlows(UUID memberId);
}
