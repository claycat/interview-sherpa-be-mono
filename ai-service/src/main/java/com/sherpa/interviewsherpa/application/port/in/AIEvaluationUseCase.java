package com.sherpa.interviewsherpa.application.port.in;

import com.sherpa.interviewsherpa.application.service.dto.AIEvaluationCommand;

public interface AIEvaluationUseCase {
	void evaluate(AIEvaluationCommand command);
}
