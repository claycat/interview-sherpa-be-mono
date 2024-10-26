package com.sherpa.interviewsherpa.comment.adaptor.in.http.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record GetCommentResponseDto(
	UUID id,
	String content,
	String author,
	String profileURL,
	LocalDateTime createdAt
) {
}
