package com.sherpa.interviewsherpa.flow.application.port.in;

import com.sherpa.interviewsherpa.flow.application.port.in.dto.patchflow.PatchFlowCommand;

public interface PatchFlowUseCase {

	void patchFlow(PatchFlowCommand patchFlowCommand);

}
