package com.sherpa.interviewsherpa.domain.client.openai;

import java.util.stream.Stream;

import ai.AIModelProvider;
import ai.OpenAIEvaluationContent;
import io.github.sashirestela.cleverclient.Event;
import io.github.sashirestela.openai.BaseSimpleOpenAI;
import io.github.sashirestela.openai.SimpleOpenAI;
import io.github.sashirestela.openai.common.ResponseFormat;
import io.github.sashirestela.openai.common.content.ContentPart;
import io.github.sashirestela.openai.domain.assistant.AssistantRequest;
import io.github.sashirestela.openai.domain.assistant.ThreadMessageDelta;
import io.github.sashirestela.openai.domain.assistant.ThreadMessageRequest;
import io.github.sashirestela.openai.domain.assistant.ThreadMessageRole;
import io.github.sashirestela.openai.domain.assistant.ThreadRequest;
import io.github.sashirestela.openai.domain.assistant.ThreadRunRequest;
import io.github.sashirestela.openai.domain.assistant.events.EventName;

public class OpenAIClient {

	private final BaseSimpleOpenAI openAI;
	private String threadId;
	private String assistantId;

	public OpenAIClient(String apiKey) {
		openAI = SimpleOpenAI.builder()
			.apiKey(apiKey)
			.build();

		createThread();
		createAssistant();
	}

	private void createThread() {
		var thread = openAI.threads().create(ThreadRequest.builder().build()).join();
		threadId = thread.getId();
	}

	private void createAssistant() {
		var assistant = openAI.assistants()
			.create(AssistantRequest.builder()
				.name("Gpt Assistant")
				.model(AIModelProvider.GPT_4O_MINI.getValue())
				.instructions(OpenAIPrompt.prompt)
				.temperature(0.2)
				.build())
			.join();
		assistantId = assistant.getId();
	}

	public String runEvaluation(String question, String answer) {

		openAI.threadMessages()
			.create(threadId, ThreadMessageRequest.builder()
				.role(ThreadMessageRole.USER)
				.content(OpenAIPrompt.questionAnswer(question, answer))
				.build())
			.join();

		var runStream = openAI.threadRuns()
			.createStream(threadId, ThreadRunRequest.builder()
				.assistantId(assistantId)
				.parallelToolCalls(Boolean.FALSE)
				.responseFormat(ResponseFormat.jsonSchema(ResponseFormat.JsonSchema.builder()
					.name("GptAnswerResult")
					.schemaClass(OpenAIEvaluationContent.class)
					.build()))
				.build())
			.join();

		var result = handleRunEvents(runStream);
		cleanConversation();

		return result;
	}

	private String handleRunEvents(Stream<Event> runStream) {
		StringBuilder accumulatedText = new StringBuilder();

		runStream.forEach(e -> {
			switch (e.getName()) {
				case EventName.THREAD_MESSAGE_DELTA:
					var messageDeltaFirstContent = ((ThreadMessageDelta)e.getData()).getDelta().getContent().get(0);
					if (messageDeltaFirstContent instanceof ContentPart.ContentPartTextAnnotation) {
						accumulatedText.append(
							((ContentPart.ContentPartTextAnnotation)messageDeltaFirstContent).getText().getValue()
						);
					}
					break;
				default:
					break;
			}
		});

		return accumulatedText.toString();
	}

	private void cleanConversation() {
		openAI.threads().delete(threadId).join();
		openAI.assistants().delete(assistantId).join();
	}

}
