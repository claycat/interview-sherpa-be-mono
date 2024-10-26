package com.sherpa.interviewsherpa.flow.adapter.out.persistence;

import java.util.List;
import java.util.UUID;

import com.sherpa.interviewsherpa.common.annotation.PersistenceAdapter;
import com.sherpa.interviewsherpa.flow.adapter.out.persistence.entity.FlowJpaEntity;
import com.sherpa.interviewsherpa.flow.adapter.out.persistence.repository.FlowRepository;
import com.sherpa.interviewsherpa.flow.application.port.in.dto.getflow.MemberFlowQueryModel;
import com.sherpa.interviewsherpa.flow.application.port.out.DeleteFlowPort;
import com.sherpa.interviewsherpa.flow.application.port.out.GetFlowPort;
import com.sherpa.interviewsherpa.flow.application.port.out.GetMemberFlowsQueryPort;
import com.sherpa.interviewsherpa.flow.application.port.out.SaveFlowPort;
import com.sherpa.interviewsherpa.flow.application.port.out.UpdateFlowPort;
import com.sherpa.interviewsherpa.flow.domain.flow.Flow;
import com.sherpa.interviewsherpa.flow.exception.FlowNotFoundException;
import com.sherpa.interviewsherpa.member.adapter.out.persistence.repository.MemberRepository;
import com.sherpa.interviewsherpa.member.exception.MemberNotFoundException;

@PersistenceAdapter
public class FlowPersistenceAdapter implements SaveFlowPort, GetFlowPort, UpdateFlowPort, GetMemberFlowsQueryPort,
	DeleteFlowPort {

	private final MemberRepository memberRepository;
	private final FlowRepository flowRepository;
	private final FlowMapper flowMapper;

	public FlowPersistenceAdapter(MemberRepository memberRepository, FlowRepository flowRepository,
		FlowMapper flowMapper) {
		this.memberRepository = memberRepository;
		this.flowRepository = flowRepository;
		this.flowMapper = flowMapper;
	}

	@Override
	public Flow loadFlow(UUID flowId) {
		var flowJpaEntity = flowRepository.findById(flowId)
			.orElseThrow(() -> new FlowNotFoundException(flowId));
		return flowMapper.mapToDomainEntity(flowJpaEntity);
	}

	@Override
	public Flow saveFlow(UUID memberId, Flow flow, String title) {
		var member = memberRepository.findById(memberId).orElseThrow(() -> new MemberNotFoundException(memberId));
		var flowJpaEntity = new FlowJpaEntity(flow, title);
		flowJpaEntity.setOwner(member);
		var newFlowJpaEntity = flowRepository.save(flowJpaEntity);
		return flowMapper.mapToDomainEntity(newFlowJpaEntity);
	}

	@Override
	public Flow updateFlow(UUID flowId, Flow flow) {
		var flowJpaEntity = flowRepository.findById(flowId)
			.orElseThrow(() -> new FlowNotFoundException(flowId));
		flowJpaEntity.setFlowContent(flow);
		var updatedFlowJpaEntity = flowRepository.save(flowJpaEntity);
		return flowMapper.mapToDomainEntity(updatedFlowJpaEntity);
	}

	@Override
	public Flow updateFlowTitle(UUID flowId, String title) {
		var flowJpaEntity = flowRepository.findById(flowId)
			.orElseThrow(() -> new FlowNotFoundException(flowId));

		flowJpaEntity.setTitle(title);
		var updatedFlowJpaEntity = flowRepository.save(flowJpaEntity);
		return flowMapper.mapToDomainEntity(updatedFlowJpaEntity);
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
