package com.sherpa.interviewsherpa.flow.adapter.in.http.dto.getflow;

import com.sherpa.interviewsherpa.flow.domain.flowcontent.FlowContent;

public record GetFlowResponse(FlowContent flowContent, String title) {
}
