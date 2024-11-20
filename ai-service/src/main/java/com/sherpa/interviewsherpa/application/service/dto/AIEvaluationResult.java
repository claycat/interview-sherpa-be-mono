package com.sherpa.interviewsherpa.application.service.dto;

import java.util.UUID;

import ai.AIEvaluationContent;
import ai.AIModelProvider;

public record AIEvaluationResult(
	UUID commentId,
	AIModelProvider provider,
	AIEvaluationContent content
) {
}
