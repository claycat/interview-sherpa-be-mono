package com.sherpa.interviewsherpa.comment.adaptor.in.http.dto;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PostCommentRequest(

	@NotNull(message = "Question must not be blank")
	String question,

	@NotBlank(message = "Content must not be blank")
	String content,

	@NotNull(message = "Member ID must not be null")
	UUID memberId,

	@NotNull
	Boolean requestAIEvaluation,

	UUID parentId
) {
}