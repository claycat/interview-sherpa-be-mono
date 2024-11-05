package com.sherpa.interviewsherpa.auth.adapter.out.persistence;

import com.sherpa.interviewsherpa.auth.adapter.out.persistence.repository.RoleRepository;
import com.sherpa.interviewsherpa.auth.application.port.out.GetRolePort;
import com.sherpa.interviewsherpa.auth.domain.Role;
import com.sherpa.interviewsherpa.auth.domain.constant.RoleEnum;
import com.sherpa.interviewsherpa.auth.exception.RoleNotFoundException;
import com.sherpa.interviewsherpa.common.annotation.PersistenceAdapter;

@PersistenceAdapter
public class RolePersistenceAdapter implements GetRolePort {

	private final RoleMapper roleMapper;
	private final RoleRepository roleRepository;

	public RolePersistenceAdapter(RoleMapper roleMapper, RoleRepository roleRepository) {
		this.roleMapper = roleMapper;
		this.roleRepository = roleRepository;
	}

	@Override
	public Role getRole(RoleEnum role) {
		var roleJpaEntity = roleRepository.findByName(role)
			.orElseThrow(() -> new RoleNotFoundException(role.name()));

		return roleMapper.mapToDomain(roleJpaEntity);
	}
}
