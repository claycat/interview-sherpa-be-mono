package com.sherpa.interviewsherpa.comment.application.port.in.dto;

import java.util.UUID;

import com.sherpa.interviewsherpa.comment.constant.CommentType;

import lombok.Builder;

@Builder
public record PostCommentCommand(
	String question,
	String content,
	UUID flowId,
	UUID memberId,
	UUID nodeId,
	UUID parentId,
	Boolean requestAIEvaluation,
	CommentType commentType
) {
}
