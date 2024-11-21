package com.sherpa.interviewsherpa.comment.adaptor.in.mq;

import static com.sherpa.interviewsherpa.infra.RabbitMQConfig.*;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import mq.AIEvaluationRequest;

@Slf4j
@Service
public class RabbitMqAIEvaluationRequestPublisher implements AIEvaluationRequestPublisher {
	private final RabbitTemplate rabbitTemplate;

	public RabbitMqAIEvaluationRequestPublisher(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

	@Override
	public void publishAiEvaluationRequest(AIEvaluationRequest request) {
		rabbitTemplate.convertAndSend(EVALUATION_EXCHANGE, EVALUATION_ROUTING_KEY_REQUEST, request);
		log.info("Published AI evaluation request: {}", request);
	}

}
