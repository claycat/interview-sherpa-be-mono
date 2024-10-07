package com.sherpa.interviewsherpa.flow.application.port.in.dto;

import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor(force = true)
public class FlowRequestCommand {
	private final UUID flowId;

	public FlowRequestCommand(UUID flowId) {
		this.flowId = flowId;
	}
}
