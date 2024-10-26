package com.sherpa.interviewsherpa.flow.application.port.in;

import java.util.UUID;

public interface DeleteFlowUseCase {
	void deleteFlow(UUID flowId);
}
