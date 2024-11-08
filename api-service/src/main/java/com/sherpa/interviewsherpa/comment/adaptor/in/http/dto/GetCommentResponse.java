package com.sherpa.interviewsherpa.comment.adaptor.in.http.dto;

import java.util.List;

public record GetCommentResponse(
	List<GetCommentResponseDto> comments
) {
}
