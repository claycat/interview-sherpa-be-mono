package com.sherpa.interviewsherpa.auth.adapter.out.persistence.entity;

import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "role_actions")
@Getter
@EqualsAndHashCode
public class RoleActionJpaEntity {

	@Id
	@UuidGenerator
	@Column(name = "role_action_id", nullable = false, updatable = false)
	private UUID id;

	@ManyToOne
	@JoinColumn(name = "role_id", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
	private RoleJpaEntity role;

	@ManyToOne
	@JoinColumn(name = "action_id", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
	private ActionJpaEntity action;

	@Builder
	public RoleActionJpaEntity(RoleJpaEntity role, ActionJpaEntity action) {
		this.action = action;
		setRole(role);
	}

	public void setRole(RoleJpaEntity role) {
		if (this.role != null) {
			this.role.getRoleActions().remove(this);
		}
		this.role = role;
		if (role == null) {
			return;
		}
		this.role.getRoleActions().add(this);
	}
}
