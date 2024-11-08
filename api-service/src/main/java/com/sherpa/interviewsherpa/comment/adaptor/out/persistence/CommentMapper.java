package com.sherpa.interviewsherpa.comment.adaptor.out.persistence;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.sherpa.interviewsherpa.comment.adaptor.out.persistence.entity.CommentJpaEntity;
import com.sherpa.interviewsherpa.comment.application.port.in.dto.CommentQueryModel;
import com.sherpa.interviewsherpa.comment.domain.Comment;

@Component
class CommentMapper {

	Comment mapToDomainEntity(CommentJpaEntity commentJpaEntity) {
		return Comment.newRootWithId(
			commentJpaEntity.getId(),
			commentJpaEntity.getNodeId(),
			commentJpaEntity.getOwner().getId(),
			commentJpaEntity.getContent(),
			commentJpaEntity.getCreatedAt()
		);
	}

	CommentQueryModel mapToQueryModel(CommentJpaEntity commentJpaEntity) {
		UUID parentId = (commentJpaEntity.getParent() != null) ? commentJpaEntity.getParent().getId() : null;

		return new CommentQueryModel(
			commentJpaEntity.getId(),
			commentJpaEntity.getContent(),
			commentJpaEntity.getOwner().getName(),
			commentJpaEntity.getOwner().getProfileURL(),
			commentJpaEntity.getCreatedAt(),
			parentId
		);
	}
}
