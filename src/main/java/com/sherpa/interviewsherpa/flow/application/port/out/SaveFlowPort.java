package com.sherpa.interviewsherpa.flow.application.port.out;

import java.util.UUID;

import com.sherpa.interviewsherpa.flow.domain.Flow;
import com.sherpa.interviewsherpa.flow.domain.flowcontent.FlowContent;

public interface SaveFlowPort {
	Flow saveFlow(UUID memberId, FlowContent flow, String title);

	Flow saveFlow(Flow flow);
}
