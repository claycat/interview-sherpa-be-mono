package com.sherpa.interviewsherpa.flow.application.service;

import com.sherpa.interviewsherpa.common.annotation.UseCase;
import com.sherpa.interviewsherpa.flow.application.port.in.FlowUpdateUseCase;
import com.sherpa.interviewsherpa.flow.application.port.in.dto.FlowUpdateCommand;
import com.sherpa.interviewsherpa.flow.application.port.out.LoadFlowPort;
import com.sherpa.interviewsherpa.flow.application.port.out.SaveFlowPort;

@UseCase
public class FlowUpdateService implements FlowUpdateUseCase {

	private final LoadFlowPort loadFlowPort;
	private final SaveFlowPort saveFlowPort;

	public FlowUpdateService(LoadFlowPort loadFlowPort, SaveFlowPort saveFlowPort) {
		this.loadFlowPort = loadFlowPort;
		this.saveFlowPort = saveFlowPort;
	}

	@Override
	public void updateFlow(FlowUpdateCommand flowUpdateCommand) {

	}
}
