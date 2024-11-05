package com.sherpa.interviewsherpa.flow.adapter.out.persistence;

import java.util.List;
import java.util.UUID;

import com.sherpa.interviewsherpa.auth.adapter.out.persistence.entity.MemberFlowRoleJpaEntity;
import com.sherpa.interviewsherpa.auth.adapter.out.persistence.repository.MemberFlowRoleRepository;
import com.sherpa.interviewsherpa.auth.adapter.out.persistence.repository.RoleRepository;
import com.sherpa.interviewsherpa.auth.exception.RoleNotFoundException;
import com.sherpa.interviewsherpa.common.annotation.PersistenceAdapter;
import com.sherpa.interviewsherpa.flow.adapter.out.persistence.entity.FlowJpaEntity;
import com.sherpa.interviewsherpa.flow.adapter.out.persistence.repository.FlowRepository;
import com.sherpa.interviewsherpa.flow.application.port.in.dto.getflow.MemberFlowQueryModel;
import com.sherpa.interviewsherpa.flow.application.port.out.DeleteFlowPort;
import com.sherpa.interviewsherpa.flow.application.port.out.GetFlowPort;
import com.sherpa.interviewsherpa.flow.application.port.out.GetMemberFlowsQueryPort;
import com.sherpa.interviewsherpa.flow.application.port.out.SaveFlowPort;
import com.sherpa.interviewsherpa.flow.application.port.out.UpdateFlowPort;
import com.sherpa.interviewsherpa.flow.domain.Flow;
import com.sherpa.interviewsherpa.flow.domain.flowcontent.FlowContent;
import com.sherpa.interviewsherpa.flow.exception.FlowNotFoundException;
import com.sherpa.interviewsherpa.member.adapter.out.persistence.repository.MemberRepository;
import com.sherpa.interviewsherpa.member.exception.MemberNotFoundException;

@PersistenceAdapter
public class FlowPersistenceAdapter implements SaveFlowPort, GetFlowPort, UpdateFlowPort, GetMemberFlowsQueryPort,
	DeleteFlowPort {

	private final MemberRepository memberRepository;
	private final FlowRepository flowRepository;
	private final FlowMapper flowMapper;
	private final RoleRepository roleRepository;
	private final MemberFlowRoleRepository memberFlowRoleRepository;

	public FlowPersistenceAdapter(MemberRepository memberRepository, FlowRepository flowRepository,
		FlowMapper flowMapper, RoleRepository roleRepository, MemberFlowRoleRepository memberFlowRoleRepository) {
		this.memberRepository = memberRepository;
		this.flowRepository = flowRepository;
		this.flowMapper = flowMapper;
		this.roleRepository = roleRepository;
		this.memberFlowRoleRepository = memberFlowRoleRepository;
	}

	@Override
	public Flow getFlow(UUID flowId) {
		var flowJpaEntity = flowRepository.findById(flowId)
			.orElseThrow(() -> new FlowNotFoundException(flowId));
		return flowMapper.mapToDomain(flowJpaEntity);
	}

	@Override
	public Flow saveFlow(UUID memberId, FlowContent flowContent, String title) {
		var member = memberRepository.findById(memberId).orElseThrow(() -> new MemberNotFoundException(memberId));

		var flowJpaEntity = new FlowJpaEntity(flowContent, title);
		flowJpaEntity.setOwner(member);

		var newFlowJpaEntity = flowRepository.save(flowJpaEntity);
		return flowMapper.mapToDomain(newFlowJpaEntity);
	}

	@Override
	public Flow saveFlow(Flow flow) {
		var flowJpaEntity = flowMapper.mapToJpaEntity(flow);
		var memberJpaEntity = memberRepository.findById(flow.getOwnerId())
			.orElseThrow(() -> new MemberNotFoundException(flow.getOwnerId()));
		flowJpaEntity.setOwner(memberJpaEntity);

		flow.getMemberFlowRoles().forEach(
			roleAssignment -> {
				var member = memberRepository.findById(roleAssignment.getMemberId())
					.orElseThrow(() -> new MemberNotFoundException(roleAssignment.getMemberId()));
				var role = roleRepository.findByName(roleAssignment.getRole().name())
					.orElseThrow(() -> new RoleNotFoundException(roleAssignment.getRole().name()));
				memberFlowRoleRepository.save(new MemberFlowRoleJpaEntity(member, flowJpaEntity, role));
			}
		);

		var newFlowJpaEntity = flowRepository.save(flowJpaEntity);

		return flowMapper.mapToDomain(newFlowJpaEntity);
	}

	@Override
	public Flow updateFlow(UUID flowId, FlowContent flowContent) {
		var flowJpaEntity = flowRepository.findById(flowId)
			.orElseThrow(() -> new FlowNotFoundException(flowId));
		flowJpaEntity.setFlowContent(flowContent);
		var updatedFlowJpaEntity = flowRepository.save(flowJpaEntity);
		return flowMapper.mapToDomain(updatedFlowJpaEntity);
	}

	@Override
	public Flow updateFlowTitle(UUID flowId, String title) {
		var flowJpaEntity = flowRepository.findById(flowId)
			.orElseThrow(() -> new FlowNotFoundException(flowId));

		flowJpaEntity.setTitle(title);
		var updatedFlowJpaEntity = flowRepository.save(flowJpaEntity);
		return flowMapper.mapToDomain(updatedFlowJpaEntity);
	}

	@Override
	public List<MemberFlowQueryModel> getMemberFlows(UUID memberId) {
		var memberFlowJpaEntities = memberRepository.findById(memberId)
			.orElseThrow(() -> new MemberNotFoundException(memberId)).getFlows();

		return memberFlowJpaEntities.stream()
			.map(e -> new MemberFlowQueryModel(e.getId(), e.getTitle(), e.getUpdatedAt(), e.getCreatedAt()))
			.toList();
	}

	@Override
	public void deleteFlow(UUID flowId) {
		flowRepository.deleteById(flowId);
	}
}
