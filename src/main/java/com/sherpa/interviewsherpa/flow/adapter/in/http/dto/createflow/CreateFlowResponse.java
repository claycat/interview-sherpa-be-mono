package com.sherpa.interviewsherpa.flow.adapter.in.http.dto.createflow;

import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor(force = true)
public class CreateFlowResponse {

	private final UUID flowId;

	public CreateFlowResponse(UUID flowId) {
		this.flowId = flowId;
	}

}
