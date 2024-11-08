package com.sherpa.interviewsherpa.flow.adapter.out.persistence;

import org.springframework.stereotype.Component;

import com.sherpa.interviewsherpa.auth.adapter.out.persistence.MemberFlowRoleMapper;
import com.sherpa.interviewsherpa.flow.adapter.out.persistence.entity.FlowJpaEntity;
import com.sherpa.interviewsherpa.flow.application.port.in.dto.getflow.MemberFlowQueryModel;
import com.sherpa.interviewsherpa.flow.domain.Flow;

@Component
class FlowMapper {

	private final MemberFlowRoleMapper memberFlowRoleMapper;

	FlowMapper(MemberFlowRoleMapper memberFlowRoleMapper) {
		this.memberFlowRoleMapper = memberFlowRoleMapper;
	}

	Flow mapToDomain(FlowJpaEntity flowJpaEntity) {

		return new Flow(
			flowJpaEntity.getId(),
			flowJpaEntity.getOwner().getId(),
			flowJpaEntity.getFlowContent(),
			flowJpaEntity.getTitle(),
			flowJpaEntity.getMemberFlowRoles().stream().map(memberFlowRoleMapper::mapToDomain).toList()
		);
	}

	FlowJpaEntity mapToJpaEntity(Flow flow) {

		return new FlowJpaEntity(
			flow.getFlowContent(),
			flow.getTitle()
		);

	}

	MemberFlowQueryModel mapToQueryModel(FlowJpaEntity flowJpaEntity) {
		return new MemberFlowQueryModel(
			flowJpaEntity.getId(),
			flowJpaEntity.getTitle(),
			flowJpaEntity.getUpdatedAt(),
			flowJpaEntity.getCreatedAt()
		);
	}

}
