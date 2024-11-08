package com.sherpa.interviewsherpa.auth.application.port.in;

import java.util.UUID;

import com.sherpa.interviewsherpa.auth.domain.Permission;

public interface FlowAccessControlUseCase {
	public boolean memberHasPermission(UUID flowId, UUID memberId, Permission permission);
}
