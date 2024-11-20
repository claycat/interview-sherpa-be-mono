package com.sherpa.interviewsherpa.comment.application.port.in;

import com.sherpa.interviewsherpa.comment.application.port.in.dto.PostAICommentCommand;
import com.sherpa.interviewsherpa.common.annotation.UseCase;

@UseCase
public interface PostAICommentUseCase {
	void postAIComment(PostAICommentCommand command);
}
