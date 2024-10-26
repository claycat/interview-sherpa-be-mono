package com.sherpa.interviewsherpa.comment.application.port.in.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record CommentQueryModel(
	UUID id,
	String content,
	String author,
	String profileURL,
	LocalDateTime createdAt,
	UUID parentId
) {
}
