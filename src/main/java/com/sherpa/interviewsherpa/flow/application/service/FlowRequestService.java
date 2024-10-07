package com.sherpa.interviewsherpa.flow.application.service;

import com.sherpa.interviewsherpa.common.annotation.UseCase;
import com.sherpa.interviewsherpa.flow.adapter.out.persistence.repository.FlowRepository;
import com.sherpa.interviewsherpa.flow.application.port.in.FlowRequestUseCase;
import com.sherpa.interviewsherpa.flow.application.port.in.dto.FlowRequestCommand;
import com.sherpa.interviewsherpa.flow.application.port.in.dto.FlowRequestResult;
import com.sherpa.interviewsherpa.flow.application.port.out.LoadFlowPort;

@UseCase
public class FlowRequestService implements FlowRequestUseCase {

	private final LoadFlowPort loadFlowPort;
	private final FlowRepository flowRepository;

	public FlowRequestService(LoadFlowPort loadFlowPort, FlowRepository flowRepository) {
		this.loadFlowPort = loadFlowPort;
		this.flowRepository = flowRepository;
	}

	@Override
	public FlowRequestResult getFlow(FlowRequestCommand command) {

		var flowJpaEntity = flowRepository.findAll().getFirst();

		return new FlowRequestResult(flowJpaEntity.getId(), flowJpaEntity.getFlowContent());

		//var flow = loadFlowPort.loadFlow(command.getFlowId());
		//return new FlowRequestResult(flow);

	}
}
