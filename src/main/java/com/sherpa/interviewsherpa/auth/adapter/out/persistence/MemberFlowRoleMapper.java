package com.sherpa.interviewsherpa.auth.adapter.out.persistence;

import org.springframework.stereotype.Component;

import com.sherpa.interviewsherpa.auth.adapter.out.persistence.entity.MemberFlowRoleJpaEntity;
import com.sherpa.interviewsherpa.auth.domain.MemberFlowRole;

@Component
public class MemberFlowRoleMapper {

	private final RoleMapper roleMapper;

	public MemberFlowRoleMapper(RoleMapper roleMapper) {
		this.roleMapper = roleMapper;
	}

	public MemberFlowRole mapToDomain(MemberFlowRoleJpaEntity e) {
		return new MemberFlowRole(
			e.getId(),
			e.getMember().getId(),
			roleMapper.mapToDomain(e.getRole()),
			e.getFlow().getId()
		);
	}

}
