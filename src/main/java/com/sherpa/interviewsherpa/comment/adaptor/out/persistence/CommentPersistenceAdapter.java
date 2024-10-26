package com.sherpa.interviewsherpa.comment.adaptor.out.persistence;

import java.util.List;
import java.util.UUID;

import com.sherpa.interviewsherpa.comment.adaptor.out.persistence.entity.CommentJpaEntity;
import com.sherpa.interviewsherpa.comment.adaptor.out.persistence.repository.CommentRepository;
import com.sherpa.interviewsherpa.comment.application.port.in.dto.CommentQueryModel;
import com.sherpa.interviewsherpa.comment.application.port.out.DeleteFlowCommentPort;
import com.sherpa.interviewsherpa.comment.application.port.out.GetCommentsQueryPort;
import com.sherpa.interviewsherpa.comment.application.port.out.PostCommentPort;
import com.sherpa.interviewsherpa.comment.domain.Comment;
import com.sherpa.interviewsherpa.comment.exception.CommentNotFoundException;
import com.sherpa.interviewsherpa.common.annotation.PersistenceAdapter;
import com.sherpa.interviewsherpa.member.adapter.out.persistence.repository.MemberRepository;
import com.sherpa.interviewsherpa.member.exception.MemberNotFoundException;

import jakarta.transaction.Transactional;

@PersistenceAdapter
public class CommentPersistenceAdapter implements PostCommentPort, GetCommentsQueryPort, DeleteFlowCommentPort {

	private final CommentRepository commentRepository;
	private final MemberRepository memberRepository;
	private final CommentMapper commentMapper;

	public CommentPersistenceAdapter(CommentRepository commentRepository, MemberRepository memberRepository,
		CommentMapper commentMapper) {
		this.commentRepository = commentRepository;
		this.memberRepository = memberRepository;
		this.commentMapper = commentMapper;
	}

	@Override
	@Transactional
	public Comment saveComment(String content, UUID memberId, UUID parentId, UUID nodeId, UUID flowId) {
		var owner = memberRepository.findById(memberId)
			.orElseThrow(() -> new MemberNotFoundException(memberId));

		CommentJpaEntity parentCommentJpaEntity = null;
		if (parentId != null) {
			parentCommentJpaEntity = commentRepository.findById(parentId)
				.orElseThrow(() -> new CommentNotFoundException(parentId));
		}

		var commentJpaEntity = new CommentJpaEntity(content, nodeId, flowId);
		commentJpaEntity.setOwner(owner);

		if (parentCommentJpaEntity != null) {
			commentJpaEntity.setParent(parentCommentJpaEntity);
		}

		var newCommentJpaEntity = commentRepository.save(commentJpaEntity);

		return commentMapper.mapToDomainEntity(newCommentJpaEntity);
	}

	@Override
	public List<CommentQueryModel> getComments(UUID flowId, UUID nodeId) {
		var commentJpaEntities = commentRepository.findByNodeId(nodeId);
		return commentJpaEntities.stream().map(commentMapper::mapToQueryModel).toList();
	}

	@Override
	public void deleteFlowComments(UUID flowId) {
		commentRepository.deleteByFlowId(flowId);
	}
}
