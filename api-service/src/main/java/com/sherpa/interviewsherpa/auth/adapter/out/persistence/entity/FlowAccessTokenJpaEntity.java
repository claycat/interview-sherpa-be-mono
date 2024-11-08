package com.sherpa.interviewsherpa.auth.adapter.out.persistence.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.sherpa.interviewsherpa.flow.adapter.out.persistence.entity.FlowJpaEntity;

import jakarta.persistence.Column;
import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "flow_access_tokens")
@EntityListeners(AuditingEntityListener.class)
@Getter
@ToString
public class FlowAccessTokenJpaEntity {

	@Id
	@UuidGenerator
	@Column(name = "flow_access_token_id", nullable = false, updatable = false)
	private UUID id;

	@Column(name = "token", nullable = false)
	private UUID token;

	@ManyToOne
	@JoinColumn(name = "flow_id", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
	private FlowJpaEntity flow;

	@OneToOne
	@JoinColumn(name = "role_id", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
	private RoleJpaEntity role;

	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt;

	@LastModifiedDate
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at", nullable = false)
	private LocalDateTime updatedAt;

	public FlowAccessTokenJpaEntity(UUID token, FlowJpaEntity flow, RoleJpaEntity role) {
		this.token = token;
		this.flow = flow;
		this.role = role;
	}
}
