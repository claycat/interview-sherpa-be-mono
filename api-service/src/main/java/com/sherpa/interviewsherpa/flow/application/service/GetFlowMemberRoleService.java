package com.sherpa.interviewsherpa.flow.application.service;

import org.springframework.stereotype.Service;

import com.sherpa.interviewsherpa.auth.application.port.out.GetMemberFlowRolePort;
import com.sherpa.interviewsherpa.flow.application.port.in.GetFlowMemberRoleUseCase;
import com.sherpa.interviewsherpa.flow.application.port.in.dto.flowrole.GetFlowMemberRoleCommand;
import com.sherpa.interviewsherpa.flow.application.port.in.dto.flowrole.GetFlowMemberRoleResult;

@Service
public class GetFlowMemberRoleService implements GetFlowMemberRoleUseCase {

	private final GetMemberFlowRolePort getMemberFlowRolePort;

	public GetFlowMemberRoleService(GetMemberFlowRolePort getMemberFlowRolePort) {
		this.getMemberFlowRolePort = getMemberFlowRolePort;
	}

	@Override
	public GetFlowMemberRoleResult getFlowMemberRole(GetFlowMemberRoleCommand command) {
		var optionalRole = getMemberFlowRolePort.getMemberFlowRole(command.flowId(), command.memberId())
			.map(memberFlowRole -> memberFlowRole.getRole().name());
		return new GetFlowMemberRoleResult(optionalRole);
	}
}
