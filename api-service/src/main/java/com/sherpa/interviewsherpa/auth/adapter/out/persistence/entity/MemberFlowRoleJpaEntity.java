package com.sherpa.interviewsherpa.auth.adapter.out.persistence.entity;

import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.sherpa.interviewsherpa.flow.adapter.out.persistence.entity.FlowJpaEntity;
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
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Table(name = "member_flow_roles")
public class MemberFlowRoleJpaEntity {
	@Id
	@UuidGenerator
	@Column(name = "member_flow_role_id", nullable = false, updatable = false)
	private UUID id;

	@ManyToOne
	@JoinColumn(name = "member_id", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
	private MemberJpaEntity member;

	@ManyToOne
	@JoinColumn(name = "flow_id", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
	private FlowJpaEntity flow;

	@ManyToOne
	@JoinColumn(name = "role_id", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
	private RoleJpaEntity role;

	@Builder
	public MemberFlowRoleJpaEntity(MemberJpaEntity member, FlowJpaEntity flow, RoleJpaEntity role) {
		this.member = member;
		this.flow = flow;
		this.role = role;
		setMember(member);
		setFlow(flow);
		setRole(role);
	}

	public void setMember(MemberJpaEntity member) {
		if (this.member != null) {
			this.member.getMemberFlowRoles().remove(this);
		}
		this.member = member;
		if (member == null) {
			return;
		}
		this.member.getMemberFlowRoles().add(this);
	}

	public void setRole(RoleJpaEntity role) {
		if (this.role != null) {
			this.role.getMemberFlowRoles().remove(this);
		}
		this.role = role;
		if (role == null) {
			return;
		}
		this.role.getMemberFlowRoles().add(this);
	}

	public void setFlow(FlowJpaEntity flow) {
		if (this.flow != null) {
			this.flow.getMemberFlowRoles().remove(this);
		}
		this.flow = flow;
		if (flow == null) {
			return;
		}
		this.flow.getMemberFlowRoles().add(this);
	}

}
