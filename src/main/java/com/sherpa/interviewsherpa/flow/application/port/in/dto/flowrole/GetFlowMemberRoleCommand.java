package com.sherpa.interviewsherpa.flow.application.port.in.dto.flowrole;

import java.util.UUID;

public record GetFlowMemberRoleCommand(
	UUID flowId,
	UUID memberId
) {
}
