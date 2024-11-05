package com.sherpa.interviewsherpa.flow.application.port.out;

import java.util.UUID;

import com.sherpa.interviewsherpa.flow.domain.Flow;
import com.sherpa.interviewsherpa.flow.domain.flowcontent.FlowContent;

public interface UpdateFlowPort {
	Flow updateFlow(UUID flowId, FlowContent flow);

	Flow updateFlowTitle(UUID flowId, String title);
}
