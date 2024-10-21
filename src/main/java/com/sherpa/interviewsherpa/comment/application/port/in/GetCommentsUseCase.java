package com.sherpa.interviewsherpa.comment.application.port.in;

import com.sherpa.interviewsherpa.comment.application.port.in.dto.GetCommentsCommand;
import com.sherpa.interviewsherpa.comment.application.port.in.dto.GetCommentsResult;
import com.sherpa.interviewsherpa.common.annotation.UseCase;

@UseCase
public interface GetCommentsUseCase {
	GetCommentsResult getComments(GetCommentsCommand command);
}
