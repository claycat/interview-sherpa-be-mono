package com.sherpa.interviewsherpa.comment.application.port.in;

import com.sherpa.interviewsherpa.comment.application.port.in.dto.PostCommentCommand;
import com.sherpa.interviewsherpa.comment.application.port.in.dto.PostCommentResult;
import com.sherpa.interviewsherpa.common.annotation.UseCase;

@UseCase
public interface PostCommentUseCase {
	PostCommentResult postComment(PostCommentCommand command);
}
