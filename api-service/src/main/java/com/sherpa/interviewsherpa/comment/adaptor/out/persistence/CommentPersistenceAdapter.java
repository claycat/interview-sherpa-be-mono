package com.sherpa.interviewsherpa.comment.adaptor.out.persistence;

import java.util.List;
import java.util.UUID;

import com.sherpa.interviewsherpa.comment.adaptor.out.persistence.repository.CommentRepository;
import com.sherpa.interviewsherpa.comment.application.port.in.dto.CommentQueryModel;
import com.sherpa.interviewsherpa.comment.application.port.out.DeleteFlowCommentPort;
import com.sherpa.interviewsherpa.comment.application.port.out.GetCommentPort;
import com.sherpa.interviewsherpa.comment.application.port.out.GetCommentsQueryPort;
import com.sherpa.interviewsherpa.comment.domain.Comment;
import com.sherpa.interviewsherpa.comment.exception.CommentNotFoundException;
import com.sherpa.interviewsherpa.common.annotation.PersistenceAdapter;

@PersistenceAdapter
public class CommentPersistenceAdapter implements GetCommentsQueryPort, DeleteFlowCommentPort,
	GetCommentPort {

	private final CommentRepository commentRepository;
	private final CommentMapper commentMapper;

	public CommentPersistenceAdapter(CommentRepository commentRepository, CommentMapper commentMapper) {
		this.commentRepository = commentRepository;
		this.commentMapper = commentMapper;
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

	@Override
	public Comment getComment(UUID commentId) {
		var commentJpaEntity = commentRepository.findById(commentId)
			.orElseThrow(() -> new CommentNotFoundException(commentId));
		return commentMapper.mapToDomainEntity(commentJpaEntity);
	}
}
