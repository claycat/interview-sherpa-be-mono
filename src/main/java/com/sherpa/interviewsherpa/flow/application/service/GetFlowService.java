package com.sherpa.interviewsherpa.flow.application.service;

import org.springframework.stereotype.Service;

import com.sherpa.interviewsherpa.flow.application.port.in.GetFlowUseCase;
import com.sherpa.interviewsherpa.flow.application.port.in.dto.getflow.GetFlowCommand;
import com.sherpa.interviewsherpa.flow.application.port.in.dto.getflow.GetFlowResult;
import com.sherpa.interviewsherpa.flow.application.port.out.GetFlowPort;

@Service
public class GetFlowService implements GetFlowUseCase {

	private final GetFlowPort getFlowPort;

	public GetFlowService(GetFlowPort getFlowPort) {
		this.getFlowPort = getFlowPort;
	}

	@Override
	public GetFlowResult getFlow(GetFlowCommand command) {
		var flow = getFlowPort.loadFlow(command.flowId());
		return new GetFlowResult(flow);
	}
}
