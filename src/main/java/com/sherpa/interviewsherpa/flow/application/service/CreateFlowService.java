package com.sherpa.interviewsherpa.flow.application.service;

import org.springframework.stereotype.Service;

import com.sherpa.interviewsherpa.flow.adapter.out.persistence.FlowFactory;
import com.sherpa.interviewsherpa.flow.application.port.in.CreateFlowUseCase;
import com.sherpa.interviewsherpa.flow.application.port.in.dto.createflow.CreateFlowCommand;
import com.sherpa.interviewsherpa.flow.application.port.in.dto.createflow.CreateFlowResult;
import com.sherpa.interviewsherpa.flow.application.port.out.SaveFlowPort;

import jakarta.transaction.Transactional;

@Service
public class CreateFlowService implements CreateFlowUseCase {

	private final SaveFlowPort saveFlowPort;
	private final FlowFactory flowFactory;

	public CreateFlowService(SaveFlowPort saveFlowPort, FlowFactory flowFactory) {
		this.saveFlowPort = saveFlowPort;
		this.flowFactory = flowFactory;
	}

	@Override
	@Transactional
	public CreateFlowResult createFlow(CreateFlowCommand createFlowCommand) {
		var flow = flowFactory.deserialize(createFlowCommand.getFlow());
		var newFlow = saveFlowPort.saveFlow(createFlowCommand.getMemberId(), flow);
		return new CreateFlowResult(newFlow.getId());
	}
}
