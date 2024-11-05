package com.sherpa.interviewsherpa.auth.adapter.out.persistence.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.sherpa.interviewsherpa.auth.domain.constant.RoleEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "roles")
@EntityListeners(AuditingEntityListener.class)
@Getter
public class RoleJpaEntity {

	@Id
	@UuidGenerator
	@Column(name = "role_id", nullable = false, updatable = false)
	private UUID id;

	@Enumerated(EnumType.STRING)
	@Column(name = "name", nullable = false)
	private RoleEnum name;

	@OneToMany(mappedBy = "role")
	private List<RolePermissionJpaEntity> rolePermissions = new ArrayList<>();

	@OneToMany(mappedBy = "role")
	private List<MemberFlowRoleJpaEntity> memberFlowRoles = new ArrayList<>();

	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt;

	@LastModifiedDate
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at", nullable = false)
	private LocalDateTime updatedAt;

	@Builder
	public RoleJpaEntity(RoleEnum name) {
		this.name = name;
	}
}
