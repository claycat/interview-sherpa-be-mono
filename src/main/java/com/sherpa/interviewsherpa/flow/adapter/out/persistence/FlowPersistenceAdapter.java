package com.sherpa.interviewsherpa.flow.adapter.out.persistence;

import java.util.UUID;

import com.sherpa.interviewsherpa.common.annotation.PersistenceAdapter;
import com.sherpa.interviewsherpa.flow.adapter.out.persistence.repository.FlowRepository;
import com.sherpa.interviewsherpa.flow.application.port.out.LoadFlowPort;
import com.sherpa.interviewsherpa.flow.application.port.out.SaveFlowPort;
import com.sherpa.interviewsherpa.flow.domain.flow.Flow;
import com.sherpa.interviewsherpa.flow.exception.FlowNotFoundException;

@PersistenceAdapter
public class FlowPersistenceAdapter implements SaveFlowPort, LoadFlowPort {

	private final FlowRepository flowRepository;

	public FlowPersistenceAdapter(FlowRepository flowRepository) {
		this.flowRepository = flowRepository;
	}

	@Override
	public void saveFlowContent(String flowContent) {

	}

	@Override
	public Flow loadFlow(UUID flowId) {
		return flowRepository.findById(flowId)
			.orElseThrow(() -> new FlowNotFoundException(flowId))
			.getFlowContent();
	}

	@Override
	public Flow loadFirstFlow() {
		return flowRepository.findAll().getFirst().getFlowContent();
	}
}
