package com.sherpa.interviewsherpa.application.service.dto;

import java.util.UUID;

public record AIEvaluationCommand(
	UUID commentId,
	UUID flowId,
	String question,
	String answer) {
}
