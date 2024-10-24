package com.sherpa.interviewsherpa.comment.application.port.in.dto;

import java.util.List;

import com.sherpa.interviewsherpa.comment.adaptor.in.http.dto.CommentQueryModel;

public record GetCommentsResult(
	List<CommentQueryModel> comments
) {
}
