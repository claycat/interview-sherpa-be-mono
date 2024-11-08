package com.sherpa.interviewsherpa.flow.application.service;

import com.sherpa.interviewsherpa.common.annotation.UseCase;
import com.sherpa.interviewsherpa.flow.application.port.in.PatchFlowUseCase;
import com.sherpa.interviewsherpa.flow.application.port.in.dto.patchflow.PatchFlowCommand;
import com.sherpa.interviewsherpa.flow.application.port.in.dto.patchflow.PatchFlowTitleCommand;
import com.sherpa.interviewsherpa.flow.application.port.out.UpdateFlowPort;

import jakarta.transaction.Transactional;

@UseCase
public class PatchFlowService implements PatchFlowUseCase {

	private final UpdateFlowPort updateFlowPort;

	public PatchFlowService(UpdateFlowPort updateFlowPort) {
		this.updateFlowPort = updateFlowPort;
	}

	@Override
	@Transactional
	public void patchFlow(PatchFlowCommand patchFlowCommand) {
		var flow = updateFlowPort.updateFlow(patchFlowCommand.flowId(), patchFlowCommand.flowContent());
	}

	@Override
	public void patchFlowTitle(PatchFlowTitleCommand patchFlowTitleCommand) {
		var flow = updateFlowPort.updateFlowTitle(patchFlowTitleCommand.flowId(), patchFlowTitleCommand.title());
	}

}
