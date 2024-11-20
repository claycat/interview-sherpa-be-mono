package com.sherpa.interviewsherpa.adapter.out.mq;

import mq.AIEvaluationResponse;

public interface AIEvaluationResultPublisher {
	void publishAiEvaluationResult(AIEvaluationResponse result);
}
