package com.sherpa.interviewsherpa.flow.adapter.in.http.dto.flowrole;

import com.sherpa.interviewsherpa.auth.domain.constant.RoleEnum;

public record GetFlowMemberRoleResponse(
	RoleEnum role) {
}
