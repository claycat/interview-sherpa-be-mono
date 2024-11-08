package com.sherpa.interviewsherpa.comment.adaptor.in.http.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record PostCommentResponse(
	UUID commentId,
	UUID memberId,
	UUID nodeId,
	String content,
	LocalDateTime createdAt) {
}
