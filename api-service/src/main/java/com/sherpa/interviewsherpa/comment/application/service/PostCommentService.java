package com.sherpa.interviewsherpa.comment.application.service;

import org.springframework.stereotype.Service;

import com.sherpa.interviewsherpa.comment.adaptor.in.mq.AIEvaluationRequestPublisher;
import com.sherpa.interviewsherpa.comment.application.port.in.PostCommentUseCase;
import com.sherpa.interviewsherpa.comment.application.port.in.dto.PostCommentCommand;
import com.sherpa.interviewsherpa.comment.application.port.in.dto.PostCommentResult;
import com.sherpa.interviewsherpa.comment.application.port.out.SaveCommentPort;
import com.sherpa.interviewsherpa.comment.domain.Comment;

import jakarta.transaction.Transactional;
import mq.AIEvaluationRequest;

@Service
public class PostCommentService implements PostCommentUseCase {

	private final SaveCommentPort saveCommentPort;
	private final AIEvaluationRequestPublisher aiEvaluationRequestPublisher;

	public PostCommentService(SaveCommentPort saveCommentPort,
		AIEvaluationRequestPublisher aiEvaluationRequestPublisher) {
		this.saveCommentPort = saveCommentPort;
		this.aiEvaluationRequestPublisher = aiEvaluationRequestPublisher;
	}

	@Override
	@Transactional
	public PostCommentResult postComment(PostCommentCommand command) {

		Comment newComment = saveCommentPort.saveComment(
			command.content(),
			command.memberId(),
			command.parentId(),
			command.nodeId(),
			command.flowId(),
			command.commentType(),
			null
		);

		var aiEvaluationRequest = new AIEvaluationRequest(
			newComment.getId(),
			command.flowId(),
			command.question(),
			command.content()
		);
		aiEvaluationRequestPublisher.publishAiEvaluationRequest(aiEvaluationRequest);

		return new PostCommentResult(newComment.getId(), newComment.getOwnerId(), newComment.getNodeId(),
			newComment.getContent(), newComment.getCreatedAt());
	}
}
