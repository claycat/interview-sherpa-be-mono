package com.sherpa.interviewsherpa.infra;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
	public static final String EVALUATION_REQUEST_QUEUE = "ai-evaluation-request-queue";
	public static final String EVALUATION_RESPONSE_QUEUE = "ai-evaluation-response-queue";

	public static final String EVALUATION_EXCHANGE = "ai-evaluation-exchange";

	public static final String EVALUATION_ROUTING_KEY_REQUEST = "ai-evaluation-request";
	public static final String EVALUATION_ROUTING_KEY_RESPONSE = "ai-evaluation-response";

	@Bean
	public Queue aiEvaluationRequestQueue() {
		return new Queue(EVALUATION_REQUEST_QUEUE, true);
	}

	@Bean
	public Queue aiEvaluationResponseQueue() {
		return new Queue(EVALUATION_RESPONSE_QUEUE, true);
	}

	@Bean
	public TopicExchange aiEvaluationExchange() {
		return new TopicExchange(EVALUATION_EXCHANGE);
	}

	@Bean
	public Binding aiEvaluationRequestBinding(Queue aiEvaluationRequestQueue, TopicExchange aiEvaluationExchange) {
		return BindingBuilder.bind(aiEvaluationRequestQueue)
			.to(aiEvaluationExchange)
			.with(EVALUATION_ROUTING_KEY_REQUEST);
	}

	@Bean
	public Binding aiEvaluationResponseBinding(Queue aiEvaluationResponseQueue, TopicExchange aiEvaluationExchange) {
		return BindingBuilder.bind(aiEvaluationResponseQueue)
			.to(aiEvaluationExchange)
			.with(EVALUATION_ROUTING_KEY_RESPONSE);
	}

	@Bean
	public Jackson2JsonMessageConverter converter() {
		return new Jackson2JsonMessageConverter();
	}
}
