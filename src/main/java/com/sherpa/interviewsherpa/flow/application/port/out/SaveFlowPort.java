package com.sherpa.interviewsherpa.flow.application.port.out;

import java.util.UUID;

import com.sherpa.interviewsherpa.flow.domain.flow.Flow;

public interface SaveFlowPort {
	Flow saveFlow(UUID memberId, Flow flow, String title);
}
