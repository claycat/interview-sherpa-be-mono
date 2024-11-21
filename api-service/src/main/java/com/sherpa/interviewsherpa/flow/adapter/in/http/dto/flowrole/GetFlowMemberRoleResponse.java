package com.sherpa.interviewsherpa.flow.adapter.in.http.dto.flowrole;

import java.util.Optional;

import com.sherpa.interviewsherpa.auth.domain.constant.RoleEnum;

public record GetFlowMemberRoleResponse(
	Optional<RoleEnum> role) {
}
