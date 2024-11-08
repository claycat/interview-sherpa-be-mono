package com.sherpa.interviewsherpa.auth.configuration;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import io.github.sashirestela.openai.SimpleOpenAI;
import io.github.sashirestela.openai.common.ResponseFormat;
import io.github.sashirestela.openai.domain.chat.Chat;
import io.github.sashirestela.openai.domain.chat.ChatMessage;
import io.github.sashirestela.openai.domain.chat.ChatRequest;

@Component
public class ChatTest implements CommandLineRunner {

	@Value("${openai.api-key}")
	private String apiKey;

	@Override
	public void run(String... args) throws Exception {

		System.out.println("=====================================");
		// demoCallChatWithStructuredOutputs();
		System.out.println("=====================================");

	}

	public static class MathReasoning {

		public List<Step> steps;
		public String finalAnswer;

		public static class Step {

			public String explanation;
			public String output;

		}

	}

	public void demoCallChatWithStructuredOutputs() {
		var openAI = SimpleOpenAI.builder()
			.apiKey(apiKey)
			.build();
		var chatRequest = ChatRequest.builder()
			.model("gpt-4o-mini")
			.message(ChatMessage.SystemMessage
				.of("You are a helpful math tutor. Guide the user through the solution step by step."))
			.message(ChatMessage.UserMessage.of("How can I solve 8x + 7 = -23"))
			.responseFormat(ResponseFormat.jsonSchema(ResponseFormat.JsonSchema.builder()
				.name("MathReasoning")
				.schemaClass(MathReasoning.class)
				.build()))
			.build();
		var chatResponse = openAI.chatCompletions().createStream(chatRequest).join();
		chatResponse.filter(chatResp -> chatResp.getChoices().size() > 0 && chatResp.firstContent() != null)
			.map(Chat::firstContent)
			.forEach(System.out::print);
		System.out.println();
	}

}
