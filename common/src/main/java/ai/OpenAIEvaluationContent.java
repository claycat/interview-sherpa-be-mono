package ai;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class OpenAIEvaluationContent implements AIEvaluationContent {

	@JsonPropertyDescription("The score of the answer")
	@JsonProperty(required = true)
	private Integer score;

	@JsonPropertyDescription("The maximum score of the answer")
	@JsonProperty(required = true)
	private Integer maxScore;

	@JsonPropertyDescription("The good aspects of the answer")
	@JsonProperty(required = true)
	private String goodAspects;

	@JsonPropertyDescription("The bad aspects of the answer")
	@JsonProperty(required = true)
	private String badAspects;

	@JsonPropertyDescription("The keywords that were expected in the answer. In phrases.")
	@JsonProperty(required = true)
	private List<String> expected;

	@JsonPropertyDescription("The follow up questions that can be asked based on the answer")
	@JsonProperty(required = true)
	private List<String> followUp;

}
