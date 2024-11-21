package com.sherpa.interviewsherpa.auth.adapter.out.persistence;

import org.springframework.stereotype.Component;

import com.sherpa.interviewsherpa.auth.adapter.out.persistence.entity.PermissionJpaEntity;
import com.sherpa.interviewsherpa.auth.domain.Permission;

@Component
class PermissionMapper {

	public Permission mapToDomain(PermissionJpaEntity permissionJpaEntity) {
		return new Permission(permissionJpaEntity.getName());
	}

}
