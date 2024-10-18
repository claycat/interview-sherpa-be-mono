package com.sherpa.interviewsherpa.flow.application.port.in;

import com.sherpa.interviewsherpa.flow.application.port.in.dto.createflow.CreateFlowCommand;
import com.sherpa.interviewsherpa.flow.application.port.in.dto.createflow.CreateFlowResult;

public interface CreateFlowUseCase {

	CreateFlowResult createFlow(CreateFlowCommand createFlowCommand);
}
