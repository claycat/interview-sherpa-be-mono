package com.sherpa.interviewsherpa.auth.application.port.in;

import java.util.UUID;

import com.sherpa.interviewsherpa.auth.application.port.in.dto.CreateFlowAccessTokenCommand;
import com.sherpa.interviewsherpa.auth.application.port.in.dto.CreateFlowAccessTokenResult;
import com.sherpa.interviewsherpa.auth.domain.constant.PermissionEnum;
import com.sherpa.interviewsherpa.auth.domain.constant.RoleEnum;

public interface FlowAccessTokenUseCase {
	CreateFlowAccessTokenResult createFlowAccessToken(CreateFlowAccessTokenCommand command);

	RoleEnum getTokenRole(UUID tokenId);

	boolean isValidToken(UUID token, PermissionEnum permission, UUID flowId);
}
