package com.sherpa.interviewsherpa.flow.application.port.in.dto.flowrole;

import com.sherpa.interviewsherpa.auth.domain.constant.RoleEnum;

public record GetFlowMemberRoleResult(
	RoleEnum role
) {
}
