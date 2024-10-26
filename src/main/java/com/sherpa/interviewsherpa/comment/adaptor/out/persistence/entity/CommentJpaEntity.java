package com.sherpa.interviewsherpa.comment.adaptor.out.persistence.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.sherpa.interviewsherpa.member.adapter.out.persistence.entity.MemberJpaEntity;

import jakarta.persistence.Column;
import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Table(name = "comments")
public class CommentJpaEntity {

	@Id
	@UuidGenerator
	@Column(name = "comment_id", nullable = false, updatable = false)
	private UUID id;

	@ManyToOne
	@JoinColumn(name = "member_id", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
	private MemberJpaEntity owner;

	@Column(name = "node_id", nullable = false, updatable = false)
	private UUID nodeId;

	@Column(name = "flow_id", nullable = false, updatable = false)
	private UUID flowId;

	@ManyToOne
	@Setter
	@JoinColumn(name = "parent_id", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
	private CommentJpaEntity parent;

	@Column(nullable = false)
	private String content;

	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt;

	@LastModifiedDate
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at", nullable = false)
	private LocalDateTime updatedAt;

	public void setOwner(MemberJpaEntity owner) {
		if (this.owner != null) {
			this.owner.getComments().remove(this);
		}
		this.owner = owner;
		if (owner != null) {
			owner.getComments().add(this);
		}
	}

	@Builder
	public CommentJpaEntity(String content, UUID nodeId, UUID flowId) {
		this.flowId = flowId;
		this.content = content;
		this.nodeId = nodeId;
	}

}
