package com.sherpa.interviewsherpa.flow.application.port.out;

import java.util.UUID;

import com.sherpa.interviewsherpa.flow.domain.flow.Flow;

public interface GetFlowPort {
	Flow loadFlow(UUID flowId);

}
