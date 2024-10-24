package com.sherpa.interviewsherpa.flow.application.port.out;

import java.util.UUID;

import com.sherpa.interviewsherpa.flow.domain.flow.Flow;

public interface UpdateFlowPort {
	Flow updateFlow(UUID flowId, Flow flow);

	Flow updateFlowTitle(UUID flowId, String title);
}
