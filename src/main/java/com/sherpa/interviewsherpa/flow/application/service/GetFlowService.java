package com.sherpa.interviewsherpa.flow.application.service;

import org.springframework.stereotype.Service;

import com.sherpa.interviewsherpa.flow.application.port.in.GetFlowUseCase;
import com.sherpa.interviewsherpa.flow.application.port.in.dto.getflow.GetFlowCommand;
import com.sherpa.interviewsherpa.flow.application.port.in.dto.getflow.GetFlowResult;
import com.sherpa.interviewsherpa.flow.application.port.out.LoadFlowPort;

@Service
public class GetFlowService implements GetFlowUseCase {

	private final LoadFlowPort loadFlowPort;

	public GetFlowService(LoadFlowPort loadFlowPort) {
		this.loadFlowPort = loadFlowPort;
	}

	@Override
	public GetFlowResult getFlow(GetFlowCommand command) {
		var flow = loadFlowPort.loadFlow(command.flowId());
		return new GetFlowResult(flow);
	}
}
