package com.sherpa.interviewsherpa.flow.adapter.in.http.dto.createflow;

import java.util.UUID;

import com.sherpa.interviewsherpa.flow.domain.flowcontent.FlowContent;

public record PatchFlowRequest(
	UUID memberId,
	FlowContent flowContent,
	String title
) {
}
