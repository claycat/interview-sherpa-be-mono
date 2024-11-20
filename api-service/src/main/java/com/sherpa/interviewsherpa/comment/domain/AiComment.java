package com.sherpa.interviewsherpa.comment.domain;

import java.util.List;

public class AiComment extends BaseComment {
	private Integer score;
	private Integer maxScore;

	private String goodAspects;
	private String badAspects;

	private List<String> expected;
	private List<String> followUp;

	public AiComment(Integer score, Integer maxScore, String goodAspects, String badAspects, List<String> expected,
		List<String> followUp) {
		this.score = score;
		this.maxScore = maxScore;
		this.goodAspects = goodAspects;
		this.badAspects = badAspects;
		this.expected = expected;
		this.followUp = followUp;
	}
}
