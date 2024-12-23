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
@Table(name = "role_permissions")
@Getter
@EqualsAndHashCode
public class RolePermissionJpaEntity {

	@Id
	@UuidGenerator
	@Column(name = "role_permission_id", nullable = false, updatable = false)
	private UUID id;

	@ManyToOne
	@JoinColumn(name = "role_id", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
	private RoleJpaEntity role;

	@ManyToOne
	@JoinColumn(name = "permission_id", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
	private PermissionJpaEntity permission;

	@Builder
	public RolePermissionJpaEntity(RoleJpaEntity role, PermissionJpaEntity permission) {
		this.permission = permission;
		setRole(role);
	}

	public void setRole(RoleJpaEntity role) {
		if (this.role != null) {
			this.role.getRolePermissions().remove(this);
		}
		this.role = role;
		if (role == null) {
			return;
		}
		this.role.getRolePermissions().add(this);
	}
}
