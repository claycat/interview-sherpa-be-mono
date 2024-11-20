package mq;

import java.io.Serializable;
import java.util.UUID;

import ai.AIEvaluationContent;
import ai.AIModelProvider;

public record AIEvaluationResponse(
	UUID commentId,
	UUID flowId,
	AIModelProvider provider,
	AIEvaluationContent content
) implements Serializable {
}
