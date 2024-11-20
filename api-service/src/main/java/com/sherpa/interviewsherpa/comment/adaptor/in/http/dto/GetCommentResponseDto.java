package com.sherpa.interviewsherpa.comment.adaptor.in.http.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.sherpa.interviewsherpa.comment.constant.CommentType;

import ai.AIModelProvider;

public record GetCommentResponseDto(
	UUID id,
	CommentType type,
	AIModelProvider provider,
	UUID parentId,
	String content,
	String author,
	String profileURL,
	LocalDateTime createdAt
) {
}
