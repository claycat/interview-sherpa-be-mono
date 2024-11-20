package com.sherpa.interviewsherpa.adapter.in.mq.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.sherpa.interviewsherpa.adapter.in.mq.AIEvaluationRequestConsumer;
import com.sherpa.interviewsherpa.application.port.in.AIEvaluationUseCase;
import com.sherpa.interviewsherpa.application.service.dto.AIEvaluationCommand;
import com.sherpa.interviewsherpa.infra.RabbitMQConfig;

import lombok.extern.slf4j.Slf4j;
import mq.AIEvaluationRequest;

@Service
@Slf4j
public class RabbitMqAIEvaluationRequestConsumer implements AIEvaluationRequestConsumer {

	private final AIEvaluationUseCase aiEvaluationUseCase;

	public RabbitMqAIEvaluationRequestConsumer(AIEvaluationUseCase aiEvaluationUseCase) {
		this.aiEvaluationUseCase = aiEvaluationUseCase;
	}

	@Override
	@RabbitListener(queues = RabbitMQConfig.EVALUATION_REQUEST_QUEUE)
	public void consumeAIEvaluationRequest(AIEvaluationRequest request) {
		var command = new AIEvaluationCommand(
			request.commentId(),
			request.flowId(),
			request.question(),
			request.answer()
		);
		aiEvaluationUseCase.evaluate(command);
		log.info("Consumed AI evaluation request: {}", request);
	}
}
