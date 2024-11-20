package com.sherpa.interviewsherpa.adapter.in.mq;

import mq.AIEvaluationRequest;

public interface AIEvaluationRequestConsumer {
	void consumeAIEvaluationRequest(AIEvaluationRequest request);
}
