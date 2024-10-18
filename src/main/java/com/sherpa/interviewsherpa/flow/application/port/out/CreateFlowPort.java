package com.sherpa.interviewsherpa.flow.application.port.out;

import com.sherpa.interviewsherpa.flow.domain.flow.Flow;

public interface CreateFlowPort {
	public Flow createFlow(String FlowContent);
}
