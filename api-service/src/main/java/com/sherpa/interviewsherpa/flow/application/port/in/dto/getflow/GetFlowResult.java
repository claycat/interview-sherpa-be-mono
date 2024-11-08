package com.sherpa.interviewsherpa.flow.application.port.in.dto.getflow;

import com.sherpa.interviewsherpa.flow.domain.flowcontent.FlowContent;

public record GetFlowResult(FlowContent flowContent, String title) {
}
