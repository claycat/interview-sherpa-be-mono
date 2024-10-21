package com.sherpa.interviewsherpa.comment.adaptor.in.http.dto;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PostCommentRequest(
	@NotBlank(message = "Content must not be blank")
	String content,

	@NotNull(message = "Member ID must not be null")
	UUID memberId,

	UUID parentId
) {
}