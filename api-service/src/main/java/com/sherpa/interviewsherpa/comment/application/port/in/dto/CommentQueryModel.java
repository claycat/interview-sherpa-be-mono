package com.sherpa.interviewsherpa.comment.application.port.in.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.sherpa.interviewsherpa.comment.constant.CommentType;

import ai.AIModelProvider;

public record CommentQueryModel(
	UUID id,
	CommentType type,
	AIModelProvider provider,
	String content,
	String author,
	String profileURL,
	LocalDateTime createdAt,
	UUID parentId
) {
}
