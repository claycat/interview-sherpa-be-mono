package com.sherpa.interviewsherpa.comment.adaptor.in.mq;

import mq.AIEvaluationRequest;

public interface AIEvaluationRequestPublisher {
	void publishAiEvaluationRequest(AIEvaluationRequest request);
}
