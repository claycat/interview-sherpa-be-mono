package com.sherpa.interviewsherpa.auth.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.sherpa.interviewsherpa.auth.adapter.out.persistence.entity.FlowAccessTokenJpaEntity;
import com.sherpa.interviewsherpa.auth.adapter.out.persistence.entity.RoleJpaEntity;
import com.sherpa.interviewsherpa.auth.adapter.out.persistence.repository.FlowAccessTokenRepository;
import com.sherpa.interviewsherpa.auth.adapter.out.persistence.repository.RoleRepository;
import com.sherpa.interviewsherpa.auth.application.port.in.FlowAccessTokenUseCase;
import com.sherpa.interviewsherpa.auth.application.port.in.dto.CreateFlowAccessTokenCommand;
import com.sherpa.interviewsherpa.auth.application.port.in.dto.CreateFlowAccessTokenResult;
import com.sherpa.interviewsherpa.auth.domain.constant.PermissionEnum;
import com.sherpa.interviewsherpa.auth.domain.constant.RoleEnum;
import com.sherpa.interviewsherpa.auth.exception.RoleNotFoundException;
import com.sherpa.interviewsherpa.flow.adapter.out.persistence.repository.FlowRepository;
import com.sherpa.interviewsherpa.flow.exception.FlowNotFoundException;

import jakarta.transaction.Transactional;

@Service
public class FlowAccessTokenService implements FlowAccessTokenUseCase {

	private final FlowAccessTokenRepository flowAccessTokenRepository;
	private final RoleRepository roleRepository;
	private final FlowRepository flowRepository;

	public FlowAccessTokenService(FlowAccessTokenRepository flowAccessTokenRepository, RoleRepository roleRepository,
		FlowRepository flowRepository) {
		this.flowAccessTokenRepository = flowAccessTokenRepository;
		this.roleRepository = roleRepository;
		this.flowRepository = flowRepository;
	}

	@Override
	@Transactional
	public CreateFlowAccessTokenResult createFlowAccessToken(CreateFlowAccessTokenCommand command) {

		var role = roleRepository.findByName(command.role())
			.orElseThrow(() -> new RoleNotFoundException(command.role()));

		var flow = flowRepository.findById(command.flowId())
			.orElseThrow(() -> new FlowNotFoundException(command.flowId()));

		var flowAccessToken = flowAccessTokenRepository.findByRole_IdAndFlow_Id(role.getId(), command.flowId())
			.stream()
			.findFirst()
			.orElseGet(() -> flowAccessTokenRepository.save(
				new FlowAccessTokenJpaEntity(
					UUID.randomUUID(),
					flow,
					role
				)
			));

		return new CreateFlowAccessTokenResult(flowAccessToken.getToken());
	}

	@Override
	@Transactional
	public RoleEnum getTokenRole(UUID tokenId) {

		return flowAccessTokenRepository.findByToken(tokenId)
			.map(FlowAccessTokenJpaEntity::getRole)
			.map(RoleJpaEntity::getName)
			.orElseThrow(() -> new RuntimeException("Token not found"));
	}

	@Transactional
	public boolean isValidToken(UUID token, PermissionEnum permission, UUID flowId) {
		if (token == null)
			return false;

		var flowAccessToken = flowAccessTokenRepository.findByToken(token)
			.orElseThrow(() -> new RuntimeException("Token not found"));

		return flowAccessToken.getRole()
			.getRolePermissions()
			.stream()
			.anyMatch(rolePermissionJpaEntity -> rolePermissionJpaEntity.getPermission().getName().equals(permission));

	}

}
