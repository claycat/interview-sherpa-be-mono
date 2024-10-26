package com.sherpa.interviewsherpa.flow.application.port.in.dto.getflow;

import java.time.LocalDateTime;
import java.util.UUID;

public record MemberFlowQueryModel(
	UUID id,
	String title,
	LocalDateTime updatedAt,
	LocalDateTime createdAt
) {
}
