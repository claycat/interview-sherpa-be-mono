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

		if (memberRepository.findByEmail(OPENAI_MEMBER_EMAIL).isPresent()) {
			log.info("AI Member {} already exists", OPENAI_MEMBER_EMAIL);
			return;
		}

		MemberJpaEntity gptJpaEntity = new MemberJpaEntity(
			OPENAI_MEMBER_EMAIL,
			"ChatGPT",
			"https://i.imgur.com/J6m10BI.png"
		);

		memberRepository.save(gptJpaEntity);
		log.info("AI Member {} saved", gptJpaEntity);

	}
}
