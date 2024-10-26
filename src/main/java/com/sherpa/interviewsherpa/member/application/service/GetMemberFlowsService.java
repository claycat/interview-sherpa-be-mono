package com.sherpa.interviewsherpa.member.application.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.sherpa.interviewsherpa.flow.application.port.in.dto.getflow.MemberFlowQueryModel;
import com.sherpa.interviewsherpa.flow.application.port.out.GetMemberFlowsQueryPort;
import com.sherpa.interviewsherpa.member.application.port.in.GetMemberFlowsUseCase;

@Service
public class GetMemberFlowsService implements GetMemberFlowsUseCase {

	private final GetMemberFlowsQueryPort getMemberFlowsQueryPort;

	public GetMemberFlowsService(GetMemberFlowsQueryPort getMemberFlowsQueryPort) {
		this.getMemberFlowsQueryPort = getMemberFlowsQueryPort;
	}

	@Override
	public List<MemberFlowQueryModel> getMemberFlows(UUID memberId) {
		return getMemberFlowsQueryPort.getMemberFlows(memberId);
	}
}
