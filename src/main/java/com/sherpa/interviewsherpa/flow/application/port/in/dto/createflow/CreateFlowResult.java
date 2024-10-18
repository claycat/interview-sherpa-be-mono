package com.sherpa.interviewsherpa.flow.application.port.in.dto.createflow;

import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor(force = true)
public class CreateFlowResult {
	private final UUID flowId;

	public CreateFlowResult(UUID flowId) {
		this.flowId = flowId;
	}
}
