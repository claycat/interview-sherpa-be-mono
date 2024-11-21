package mq;

import java.io.Serializable;
import java.util.UUID;

public record AIEvaluationRequest(
	UUID commentId,
	UUID flowId,
	String question,
	String answer
) implements Serializable {
}
