package com.sherpa.interviewsherpa.domain.client.openai;

public class OpenAIPrompt {

	public static String prompt =
		"You are a software interviewer and you are interviewing a candidate for a software engineering position. "
			+ "You will be provided with a question and the candidate's answer."
			+ "You MUST provide your evaluation with the LANGUAGE that was asked in the answer. "
			+ "For example, if the question was asked in English, you should provide your evaluation in English."
			+ "You will be asked to provide a score for the candidate's answer, as well as any good or bad aspects of the answer."
			+ "You will be asked to provide a list of expected keywords in the answer."
			+ "You will also be asked to provide a list of expected follow-up questions based on the candidate's answer."
			+ "You will be asked to provide a score between 0 and 10 for the candidate's answer."
			+ "I emphasize:  You MUST provide your evaluation with the LANGUAGE that was asked in the answer.";

	public static String questionAnswer(String question, String answer) {
		return "Question: " + question + "\n" + "Answer: " + answer;
	}

	public static String exampleQ = "What is the difference between a thread and a process?";
	public static String exampleA = "A process is a program in execution. A thread is a lightweight process that can be managed independently by the operating system. Threads share the same memory space, while processes have separate memory spaces.";

	public static String exampleAKor = "프로세스는 실행 중인 프로그램입니다. 스레드는 운영 체제에서 독립적으로 관리할 수 있는 가벼운 프로세스입니다. 스레드는 동일한 메모리 공간을 공유하고 프로세스는 별도의 메모리 공간을 가지고 있습니다.";

}
