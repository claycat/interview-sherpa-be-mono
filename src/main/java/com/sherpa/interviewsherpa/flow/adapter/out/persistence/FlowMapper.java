package com.sherpa.interviewsherpa.flow.adapter.out.persistence;

import org.springframework.stereotype.Component;

import com.sherpa.interviewsherpa.flow.adapter.out.persistence.entity.FlowJpaEntity;
import com.sherpa.interviewsherpa.flow.application.port.in.dto.getflow.MemberFlowQueryModel;
import com.sherpa.interviewsherpa.flow.domain.flow.Flow;

@Component
class FlowMapper {

	Flow mapToDomainEntity(FlowJpaEntity flowJpaEntity) {
		return Flow.withId(
			flowJpaEntity.getId(),
			flowJpaEntity.getFlowContent().getNodes(),
			flowJpaEntity.getFlowContent().getEdges(),
			flowJpaEntity.getFlowContent().getViewport()
		);
	}

	FlowJpaEntity mapToJpaEntity(Flow flow) {
		return FlowJpaEntity.builder()
			.flowContent(flow)
			.build();
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
