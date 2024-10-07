package com.sherpa.interviewsherpa.member.adapter.out.persistence.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.sherpa.interviewsherpa.auth.adapter.out.persistence.entity.MemberFlowRoleJpaEntity;
import com.sherpa.interviewsherpa.flow.adapter.out.persistence.entity.FlowJpaEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name = "members")
public class MemberJpaEntity {
	@Id
	@UuidGenerator
	@Column(name = "member_id", nullable = false, updatable = false)
	private UUID id;

	@Setter
	@Column(name = "email", nullable = false)
	private String email;

	@Setter
	@Column(name = "name")
	private String name;

	@Setter
	@Column(name = "profile_url")
	private String profileURL;

	@OneToMany(mappedBy = "member")
	private List<MemberFlowRoleJpaEntity> memberFlowRoles = new ArrayList<>();

	@OneToMany(mappedBy = "owner")
	private List<FlowJpaEntity> flows = new ArrayList<>();

	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt;

	@LastModifiedDate
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at", nullable = false)
	private LocalDateTime updatedAt;

	@Builder
	public MemberJpaEntity(String email, String name, String profileURL) {
		this.email = email;
		this.name = name;
		this.profileURL = profileURL;
	}
}
