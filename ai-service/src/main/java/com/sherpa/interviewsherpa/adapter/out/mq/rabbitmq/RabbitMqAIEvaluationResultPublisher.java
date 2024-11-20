package com.sherpa.interviewsherpa.adapter.out.mq.rabbitmq;

import static com.sherpa.interviewsherpa.infra.RabbitMQConfig.*;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.sherpa.interviewsherpa.adapter.out.mq.AIEvaluationResultPublisher;

import lombok.extern.slf4j.Slf4j;
import mq.AIEvaluationResponse;

@Slf4j
@Service
public class RabbitMqAIEvaluationResultPublisher implements AIEvaluationResultPublisher {

	private final RabbitTemplate rabbitTemplate;

	public RabbitMqAIEvaluationResultPublisher(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

	@Override
	public void publishAiEvaluationResult(AIEvaluationResponse response) {
		rabbitTemplate.convertAndSend(EVALUATION_EXCHANGE, EVALUATION_ROUTING_KEY_RESPONSE, response);
		log.info("Published AI evaluation result: {}", response);
	}
}
