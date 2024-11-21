package com.sherpa.interviewsherpa.flow.application.port.out;

import java.util.UUID;

import com.sherpa.interviewsherpa.flow.domain.Flow;

public interface GetFlowPort {
	Flow getFlow(UUID flowId);

}
