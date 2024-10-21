package com.sherpa.interviewsherpa.comment.application.service;

import org.springframework.stereotype.Service;

import com.sherpa.interviewsherpa.comment.application.port.in.PostCommentUseCase;
import com.sherpa.interviewsherpa.comment.application.port.in.dto.PostCommentCommand;
import com.sherpa.interviewsherpa.comment.application.port.in.dto.PostCommentResult;
import com.sherpa.interviewsherpa.comment.application.port.out.PostCommentPort;
import com.sherpa.interviewsherpa.comment.domain.Comment;

@Service
public class PostCommentService implements PostCommentUseCase {

	private final PostCommentPort postCommentPort;

	public PostCommentService(PostCommentPort postCommentPort) {
		this.postCommentPort = postCommentPort;
	}

	@Override
	public PostCommentResult postComment(PostCommentCommand command) {
		Comment newComment = postCommentPort.saveComment(command.content(), command.memberId(), command.parentId(),
			command.nodeId());

		return new PostCommentResult(newComment.getId(), newComment.getOwnerId(), newComment.getNodeId(),
			newComment.getContent(), newComment.getCreatedAt());
	}
}
