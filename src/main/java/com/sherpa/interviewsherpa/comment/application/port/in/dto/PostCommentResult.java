package com.sherpa.interviewsherpa.comment.application.port.in.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record PostCommentResult(
	UUID commentId, UUID memberId, UUID nodeId, String content, LocalDateTime createdAt
) {
}
