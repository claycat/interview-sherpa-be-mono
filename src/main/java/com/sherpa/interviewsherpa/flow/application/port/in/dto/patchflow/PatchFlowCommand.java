package com.sherpa.interviewsherpa.flow.application.port.in.dto.patchflow;

import java.util.UUID;

import com.sherpa.interviewsherpa.flow.domain.flow.Flow;

public record PatchFlowCommand(UUID flowId, Flow flow) {
}
