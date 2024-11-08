package com.sherpa.interviewsherpa.member.adapter.in.http.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record GetMemberFlowDto(
	UUID id,
	String title,
	LocalDateTime updatedAt,
	LocalDateTime createdAt) {
}
