package com.sherpa.interviewsherpa.flow.adapter.out.persistence.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.sherpa.interviewsherpa.auth.adapter.out.persistence.entity.MemberFlowRoleJpaEntity;
import com.sherpa.interviewsherpa.flow.domain.flowcontent.FlowContent;
import com.sherpa.interviewsherpa.member.adapter.out.persistence.entity.MemberJpaEntity;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "flows")
public class FlowJpaEntity {
	@Id
	@UuidGenerator
	@Column(name = "flow_id", nullable = false, updatable = false)
	private UUID id;

	@OneToMany(mappedBy = "flow", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<MemberFlowRoleJpaEntity> memberFlowRoles = new ArrayList<>();

	@ManyToOne
	@JoinColumn(name = "member_id", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
	private MemberJpaEntity owner;

	@Setter
	@Column(name = "flow_content", columnDefinition = "json")
	@Type(JsonType.class)
	private FlowContent flowContent;

	@Setter
	@Column(name = "flow_title", nullable = false)
	private String title;

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
			this.owner.getFlows().remove(this);
		}
		this.owner = owner;
		if (owner != null) {
			owner.getFlows().add(this);
		}
	}

	@Builder
	public FlowJpaEntity(FlowContent flowContent, String title) {
		this.flowContent = flowContent;
		this.title = title;
	}

	public void setMemberFlowRoles(List<MemberFlowRoleJpaEntity> memberFlowRoles) {
		this.memberFlowRoles.clear();
		this.memberFlowRoles.addAll(memberFlowRoles);
	}

}