package com.sherpa.interviewsherpa.comment.adaptor.out.mq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.sherpa.interviewsherpa.comment.application.port.in.PostAICommentUseCase;
import com.sherpa.interviewsherpa.comment.application.port.in.dto.PostAICommentCommand;
import com.sherpa.interviewsherpa.infra.RabbitMQConfig;

import lombok.extern.slf4j.Slf4j;
import mq.AIEvaluationResponse;

@Service
@Slf4j
public class RabbitMqAIEvaluationResponseConsumer implements AIEvaluationResponseConsumer {

	private final PostAICommentUseCase postAICommentUseCase;

	public RabbitMqAIEvaluationResponseConsumer(PostAICommentUseCase postAICommentUseCase) {
		this.postAICommentUseCase = postAICommentUseCase;
	}

	@Override
	@RabbitListener(queues = RabbitMQConfig.EVALUATION_RESPONSE_QUEUE)
	public void consumeAIEvaluationResponse(AIEvaluationResponse response) {
		var command = new PostAICommentCommand(
			response.commentId(),
			response.flowId(),
			response.provider(),
			response.content()
		);
		postAICommentUseCase.postAIComment(command);
		log.info("Consumed AI evaluation response: {}", response);
	}
}
