package com.sherpa.interviewsherpa.flow.application.service;

import org.springframework.stereotype.Service;

import com.sherpa.interviewsherpa.auth.application.port.out.GetRolePort;
import com.sherpa.interviewsherpa.auth.domain.Role;
import com.sherpa.interviewsherpa.auth.domain.constant.RoleEnum;
import com.sherpa.interviewsherpa.flow.application.port.in.CreateFlowUseCase;
import com.sherpa.interviewsherpa.flow.application.port.in.dto.createflow.CreateFlowCommand;
import com.sherpa.interviewsherpa.flow.application.port.in.dto.createflow.CreateFlowResult;
import com.sherpa.interviewsherpa.flow.application.port.out.SaveFlowPort;
import com.sherpa.interviewsherpa.flow.domain.Flow;
import com.sherpa.interviewsherpa.member.application.port.out.LoadOAuthMemberPort;
import com.sherpa.interviewsherpa.member.domain.Member;

import jakarta.transaction.Transactional;

@Service
public class CreateFlowService implements CreateFlowUseCase {

	private final SaveFlowPort saveFlowPort;
	private final LoadOAuthMemberPort loadOAuthMemberPort;
	private final GetRolePort getRolePort;

	public CreateFlowService(SaveFlowPort saveFlowPort, LoadOAuthMemberPort loadOAuthMemberPort,
		GetRolePort getRolePort) {
		this.saveFlowPort = saveFlowPort;
		this.loadOAuthMemberPort = loadOAuthMemberPort;
		this.getRolePort = getRolePort;
	}

	@Override
	@Transactional
	public CreateFlowResult createFlow(CreateFlowCommand createFlowCommand) {

		Member member = loadOAuthMemberPort.loadOAuthMemberById(createFlowCommand.getMemberId());

		var flow = Flow.withoutId(
			createFlowCommand.getMemberId(),
			createFlowCommand.getFlowContent(),
			createFlowCommand.getTitle()
		);

		Role owner = getRolePort.getRole(RoleEnum.OWNER);
		flow.assignRole(member, owner);

		var newFlow = saveFlowPort.saveFlow(flow);
		return new CreateFlowResult(newFlow.getId());
	}
}
