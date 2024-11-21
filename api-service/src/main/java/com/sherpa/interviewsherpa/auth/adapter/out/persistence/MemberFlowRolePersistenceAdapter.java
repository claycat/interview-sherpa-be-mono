package com.sherpa.interviewsherpa.auth.adapter.out.persistence;

import java.util.Optional;
import java.util.UUID;

import com.sherpa.interviewsherpa.auth.adapter.out.persistence.repository.MemberFlowRoleRepository;
import com.sherpa.interviewsherpa.auth.application.port.out.GetMemberFlowRolePort;
import com.sherpa.interviewsherpa.auth.domain.MemberFlowRole;
import com.sherpa.interviewsherpa.common.annotation.PersistenceAdapter;

@PersistenceAdapter
public class MemberFlowRolePersistenceAdapter implements GetMemberFlowRolePort {

	private final MemberFlowRoleRepository memberFlowRoleRepository;
	private final MemberFlowRoleMapper memberFlowRoleMapper;

	public MemberFlowRolePersistenceAdapter(MemberFlowRoleRepository memberFlowRoleRepository,
		MemberFlowRoleMapper memberFlowRoleMapper) {
		this.memberFlowRoleRepository = memberFlowRoleRepository;
		this.memberFlowRoleMapper = memberFlowRoleMapper;
	}

	@Override
	public Optional<MemberFlowRole> getMemberFlowRole(UUID flowId, UUID memberId) {
		return memberFlowRoleRepository.findByFlow_IdAndMember_Id(flowId, memberId)
			.map(memberFlowRoleMapper::mapToDomain);
	}
}
