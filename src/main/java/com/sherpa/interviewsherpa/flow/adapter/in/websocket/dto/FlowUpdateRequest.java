package com.sherpa.interviewsherpa.flow.adapter.in.websocket.dto;

import com.sherpa.interviewsherpa.flow.domain.flow.Flow;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor(force = true)
public class FlowUpdateRequest {
	private final Flow flow;

	@Builder
	public FlowUpdateRequest(Flow flow) {
		this.flow = flow;
	}
}
