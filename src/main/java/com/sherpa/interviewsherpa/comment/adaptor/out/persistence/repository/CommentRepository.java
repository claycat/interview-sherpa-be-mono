package com.sherpa.interviewsherpa.comment.adaptor.out.persistence.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sherpa.interviewsherpa.comment.adaptor.out.persistence.entity.CommentJpaEntity;

@Repository
public interface CommentRepository extends JpaRepository<CommentJpaEntity, UUID> {
	List<CommentJpaEntity> findByNodeId(UUID nodeId);
}
