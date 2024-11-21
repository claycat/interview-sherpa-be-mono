package com.sherpa.interviewsherpa.auth.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.sherpa.interviewsherpa.auth.application.port.in.FlowAccessControlUseCase;
import com.sherpa.interviewsherpa.auth.domain.Permission;
import com.sherpa.interviewsherpa.flow.application.port.out.GetFlowPort;
import com.sherpa.interviewsherpa.flow.domain.Flow;

import jakarta.transaction.Transactional;

@Service
public class FlowAccessControlService implements FlowAccessControlUseCase {

	private final GetFlowPort getFlowPort;

	public FlowAccessControlService(GetFlowPort getFlowPort) {
		this.getFlowPort = getFlowPort;
	}

	@Override
	@Transactional
	public boolean memberHasPermission(UUID flowId, UUID memberId, Permission permission) {
		Flow flow = getFlowPort.getFlow(flowId);

		return flow.getMemberFlowRoles().stream()
			.anyMatch(memberFlowRole ->
				memberFlowRole.getMemberId().equals(memberId) &&
					memberFlowRole.getRole().hasPermission(permission)
			);
	}
}
