package com.sherpa.interviewsherpa.comment.application.port.in.dto;

import java.util.List;

public record GetCommentsResult(
	List<CommentQueryModel> comments
) {
}
