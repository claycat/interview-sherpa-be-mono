package com.sherpa.interviewsherpa.flow.application.port.in;

import com.sherpa.interviewsherpa.flow.application.port.in.dto.patchflow.PatchFlowCommand;
import com.sherpa.interviewsherpa.flow.application.port.in.dto.patchflow.PatchFlowTitleCommand;

public interface PatchFlowUseCase {

	void patchFlow(PatchFlowCommand patchFlowCommand);

	void patchFlowTitle(PatchFlowTitleCommand patchFlowTitleCommand);

}
