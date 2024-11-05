package com.sherpa.interviewsherpa.auth.adapter.out.persistence;

import org.springframework.stereotype.Component;

import com.sherpa.interviewsherpa.auth.adapter.out.persistence.entity.RoleJpaEntity;
import com.sherpa.interviewsherpa.auth.domain.Permission;
import com.sherpa.interviewsherpa.auth.domain.Role;
import com.sherpa.interviewsherpa.auth.domain.constant.RoleEnum;

@Component
class RoleMapper {
	public Role mapToDomain(RoleJpaEntity roleJpaEntity) {
		return new Role(roleJpaEntity.getName(),
			roleJpaEntity.getRolePermissions()
				.stream()
				.map(rolePermissionJpaEntity -> new Permission(rolePermissionJpaEntity.getPermission().getName()))
				.toList()
		);
	}

	public RoleJpaEntity mapToJpaEntity(Role role) {
		return new RoleJpaEntity(RoleEnum.valueOf(role.name().name()));
	}
}
