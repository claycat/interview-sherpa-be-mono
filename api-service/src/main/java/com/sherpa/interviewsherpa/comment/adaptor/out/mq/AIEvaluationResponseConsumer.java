package com.sherpa.interviewsherpa.comment.adaptor.out.mq;

import mq.AIEvaluationResponse;

public interface AIEvaluationResponseConsumer {
	void consumeAIEvaluationResponse(AIEvaluationResponse response);
}
