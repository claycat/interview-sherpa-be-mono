package com.sherpa.interviewsherpa.flow.application.port.in;

import com.sherpa.interviewsherpa.flow.application.port.in.dto.FlowUpdateCommand;

public interface FlowUpdateUseCase {

	void updateFlow(FlowUpdateCommand flowUpdateCommand);

}
