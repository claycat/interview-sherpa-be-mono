package com.sherpa.interviewsherpa.comment.application.service;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sherpa.interviewsherpa.comment.application.port.in.PostAICommentUseCase;
import com.sherpa.interviewsherpa.comment.application.port.in.dto.PostAICommentCommand;
import com.sherpa.interviewsherpa.comment.application.port.out.GetCommentPort;
import com.sherpa.interviewsherpa.comment.application.port.out.SaveCommentPort;
import com.sherpa.interviewsherpa.comment.constant.CommentType;
import com.sherpa.interviewsherpa.comment.domain.Comment;
import com.sherpa.interviewsherpa.init.AIMemberInitializer;
import com.sherpa.interviewsherpa.member.application.port.out.LoadOAuthMemberPort;
import com.sherpa.interviewsherpa.member.domain.Member;

import jakarta.transaction.Transactional;

@Service
public class PostAICommentService implements PostAICommentUseCase {

	private final SaveCommentPort saveCommentPort;
	private final GetCommentPort getCommentPort;
	private final LoadOAuthMemberPort loadOAuthMemberPort;
	private final ObjectMapper objectMapper;

	public PostAICommentService(SaveCommentPort saveCommentPort, GetCommentPort getCommentPort,
		LoadOAuthMemberPort loadOAuthMemberPort, ObjectMapper objectMapper) {
		this.saveCommentPort = saveCommentPort;
		this.getCommentPort = getCommentPort;
		this.loadOAuthMemberPort = loadOAuthMemberPort;
		this.objectMapper = objectMapper;
	}

	@Override
	@Transactional
	public void postAIComment(PostAICommentCommand command) {

		Comment originalComment = getCommentPort.getComment(command.commentId());
		String aiMemberEmail = AIMemberInitializer.resolveEmail(command.provider());
		Member aiMember = loadOAuthMemberPort.loadOAuthMember(aiMemberEmail);

		try {
			Comment newComment = saveCommentPort.saveComment(
				objectMapper.writeValueAsString(command.content()),
				aiMember.getId(),
				originalComment.getId(),
				originalComment.getNodeId(),
				command.flowId(),
				CommentType.AI,
				command.provider()
			);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}

	}
}
