package com.sherpa.interviewsherpa.comment.application.port.in.dto;

import java.util.UUID;

import lombok.Builder;

@Builder
public record PostCommentCommand(
	String content,
	UUID flowId,
	UUID memberId,
	UUID nodeId,
	UUID parentId
) {
}
