package ai;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum AIModelProvider {
	GPT_3("gpt-3"), GPT_4("gpt-4"), GPT_4O("gpt-4o"), GPT_4O_MINI("gpt-4o-mini");

	private final String value;
}
