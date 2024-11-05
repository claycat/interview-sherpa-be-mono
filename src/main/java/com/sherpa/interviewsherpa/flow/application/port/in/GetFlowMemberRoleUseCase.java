package com.sherpa.interviewsherpa.flow.application.port.in;

import com.sherpa.interviewsherpa.common.annotation.UseCase;
import com.sherpa.interviewsherpa.flow.application.port.in.dto.flowrole.GetFlowMemberRoleCommand;
import com.sherpa.interviewsherpa.flow.application.port.in.dto.flowrole.GetFlowMemberRoleResult;

@UseCase
public interface GetFlowMemberRoleUseCase {
	GetFlowMemberRoleResult getFlowMemberRole(GetFlowMemberRoleCommand command);
}
