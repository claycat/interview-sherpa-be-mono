package ai;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
	use = JsonTypeInfo.Id.NAME,
	property = "type"
)
@JsonSubTypes({
	@JsonSubTypes.Type(value = OpenAIEvaluationContent.class, name = "OpenAIEvaluationContent")
})
public interface AIEvaluationContent {

	Integer getScore();

	Integer getMaxScore();

	String getGoodAspects();

	String getBadAspects();

	List<String> getExpected();

	List<String> getFollowUp();
}