package com.sherpa.interviewsherpa.application.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sherpa.interviewsherpa.adapter.out.mq.AIEvaluationResultPublisher;
import com.sherpa.interviewsherpa.application.port.in.AIEvaluationUseCase;
import com.sherpa.interviewsherpa.application.service.dto.AIEvaluationCommand;
import com.sherpa.interviewsherpa.domain.client.openai.OpenAIClient;

import ai.AIModelProvider;
import ai.OpenAIEvaluationContent;
import lombok.extern.slf4j.Slf4j;
import mq.AIEvaluationResponse;

@Slf4j
@Service
public class OpenAIEvaluationService implements AIEvaluationUseCase {

	@Value("${openai.api-key}")
	private String apiKey;

	private final AIEvaluationResultPublisher aiEvaluationResultPublisher;
	private final ObjectMapper objectMapper;

	public OpenAIEvaluationService(AIEvaluationResultPublisher aiEvaluationResultPublisher, ObjectMapper objectMapper) {
		this.aiEvaluationResultPublisher = aiEvaluationResultPublisher;
		this.objectMapper = objectMapper;
	}

	@Override
	public void evaluate(AIEvaluationCommand command) {

		OpenAIClient openAIClient = new OpenAIClient(apiKey);
		var evaluationJson = openAIClient.runEvaluation(command.question(), command.answer());
		try {
			OpenAIEvaluationContent evaluationContent = objectMapper.readValue(evaluationJson,
				OpenAIEvaluationContent.class);

			AIEvaluationResponse evaluationResponse = new AIEvaluationResponse(
				command.commentId(),
				command.flowId(),
				AIModelProvider.GPT_4O_MINI,
				evaluationContent
			);

			aiEvaluationResultPublisher.publishAiEvaluationResult(evaluationResponse);
		} catch (JsonProcessingException e) {
			log.error("Failed to parse evaluation result from OpenAI", e);
			throw new RuntimeException("Failed to parse evaluation result from OpenAI", e);
		}
	}
}
