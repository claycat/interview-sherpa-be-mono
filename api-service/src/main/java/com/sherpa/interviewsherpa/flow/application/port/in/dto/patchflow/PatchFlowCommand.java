package com.sherpa.interviewsherpa.flow.application.port.in.dto.patchflow;

import java.util.UUID;

import com.sherpa.interviewsherpa.flow.domain.flowcontent.FlowContent;

public record PatchFlowCommand(UUID flowId, FlowContent flowContent) {
}
