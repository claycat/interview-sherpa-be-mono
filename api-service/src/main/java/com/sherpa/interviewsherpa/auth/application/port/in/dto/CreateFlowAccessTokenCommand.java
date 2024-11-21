package com.sherpa.interviewsherpa.auth.application.port.in.dto;

import java.util.UUID;

import com.sherpa.interviewsherpa.auth.domain.constant.RoleEnum;

public record CreateFlowAccessTokenCommand(
	UUID flowId,
	RoleEnum role
) {
}
