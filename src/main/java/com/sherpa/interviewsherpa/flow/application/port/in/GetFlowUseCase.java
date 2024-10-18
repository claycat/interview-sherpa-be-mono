package com.sherpa.interviewsherpa.flow.application.port.in;

import com.sherpa.interviewsherpa.flow.application.port.in.dto.getflow.GetFlowCommand;
import com.sherpa.interviewsherpa.flow.application.port.in.dto.getflow.GetFlowResult;

public interface GetFlowUseCase {
	GetFlowResult getFlow(GetFlowCommand command);
}
