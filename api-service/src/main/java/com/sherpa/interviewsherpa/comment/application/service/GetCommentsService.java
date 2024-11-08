package com.sherpa.interviewsherpa.comment.application.service;

import org.springframework.stereotype.Service;

import com.sherpa.interviewsherpa.comment.application.port.in.GetCommentsUseCase;
import com.sherpa.interviewsherpa.comment.application.port.in.dto.GetCommentsCommand;
import com.sherpa.interviewsherpa.comment.application.port.in.dto.GetCommentsResult;
import com.sherpa.interviewsherpa.comment.application.port.out.GetCommentsQueryPort;

@Service
public class GetCommentsService implements GetCommentsUseCase {

	private final GetCommentsQueryPort getCommentsQueryPort;

	public GetCommentsService(GetCommentsQueryPort getCommentsQueryPort) {
		this.getCommentsQueryPort = getCommentsQueryPort;
	}

	@Override
	public GetCommentsResult getComments(GetCommentsCommand command) {

		var comments = getCommentsQueryPort.getComments(command.flowId(), command.nodeId());
		return new GetCommentsResult(comments);
	}
}
