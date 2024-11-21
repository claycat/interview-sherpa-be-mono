package com.sherpa.interviewsherpa.flow.application.port.in.dto.flowrole;

import java.util.Optional;

import com.sherpa.interviewsherpa.auth.domain.constant.RoleEnum;

public record GetFlowMemberRoleResult(
	Optional<RoleEnum> role
) {
}
