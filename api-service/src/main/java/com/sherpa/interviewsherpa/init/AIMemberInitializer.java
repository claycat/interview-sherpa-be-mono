package com.sherpa.interviewsherpa.init;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.sherpa.interviewsherpa.member.adapter.out.persistence.entity.MemberJpaEntity;
import com.sherpa.interviewsherpa.member.adapter.out.persistence.repository.MemberRepository;

import ai.AIModelProvider;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AIMemberInitializer implements CommandLineRunner {

	private final MemberRepository memberRepository;

	public AIMemberInitializer(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	public static String OPENAI_MEMBER_EMAIL = "openai@test.com";

	public static String resolveEmail(AIModelProvider commentType) {
		return switch (commentType) {
			case AIModelProvider.GPT_4O_MINI, AIModelProvider.GPT_4O, AIModelProvider.GPT_4, AIModelProvider.GPT_3 ->
				OPENAI_MEMBER_EMAIL;
			default -> throw new IllegalArgumentException("Unknown comment type");
		};
	}

	@Override
	public void run(String... args) {

		MemberJpaEntity gptJpaEntity = new MemberJpaEntity(
			OPENAI_MEMBER_EMAIL,
			"ChatGPT",
			"https://private-user-images.githubusercontent.com/53655119/387219880-2278abb1-0cc8-47be-977f-e4a84f9c2a3b.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3MzE5MzQyMjMsIm5iZiI6MTczMTkzMzkyMywicGF0aCI6Ii81MzY1NTExOS8zODcyMTk4ODAtMjI3OGFiYjEtMGNjOC00N2JlLTk3N2YtZTRhODRmOWMyYTNiLnBuZz9YLUFtei1BbGdvcml0aG09QVdTNC1ITUFDLVNIQTI1NiZYLUFtei1DcmVkZW50aWFsPUFLSUFWQ09EWUxTQTUzUFFLNFpBJTJGMjAyNDExMTglMkZ1cy1lYXN0LTElMkZzMyUyRmF3czRfcmVxdWVzdCZYLUFtei1EYXRlPTIwMjQxMTE4VDEyNDUyM1omWC1BbXotRXhwaXJlcz0zMDAmWC1BbXotU2lnbmF0dXJlPTBjOGE4OGExMDNlMDEyZTMyZDgzMjQ2NjdiZmJmYTIwMjEwMzFlOWQ1YjMyNmIyY2VjN2Q5YjI2YTMwOGRlYjQmWC1BbXotU2lnbmVkSGVhZGVycz1ob3N0In0.AyUFKDofh5OFuQ6p--GWzXP7aVnqXh8d2Z0KUaU08co"
		);

		memberRepository.save(gptJpaEntity);
		log.info("AI Member {} saved", gptJpaEntity);

	}
}
