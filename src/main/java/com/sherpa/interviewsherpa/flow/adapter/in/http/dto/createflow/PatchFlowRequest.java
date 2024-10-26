package com.sherpa.interviewsherpa.flow.adapter.in.http.dto.createflow;

import java.util.UUID;

import com.sherpa.interviewsherpa.flow.domain.flow.Flow;

public record PatchFlowRequest(
	UUID memberId,
	Flow flow,
	String title
) {
}
