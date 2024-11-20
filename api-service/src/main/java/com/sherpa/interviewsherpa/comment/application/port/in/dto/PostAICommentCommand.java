package com.sherpa.interviewsherpa.comment.application.port.in.dto;

import java.util.UUID;

import ai.AIEvaluationContent;
import ai.AIModelProvider;

public record PostAICommentCommand(
	UUID commentId,
	UUID flowId,
	AIModelProvider provider,
	AIEvaluationContent content
) {
}
