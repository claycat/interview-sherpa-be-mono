package com.sherpa.interviewsherpa.flow.application.port.in;

import com.sherpa.interviewsherpa.flow.application.port.in.dto.FlowRequestCommand;
import com.sherpa.interviewsherpa.flow.application.port.in.dto.FlowRequestResult;

public interface FlowRequestUseCase {
	FlowRequestResult getFlow(FlowRequestCommand command);
	
}
