package com.sherpa.interviewsherpa.flow.adapter.in.http.dto.createflow;

import java.util.UUID;

import com.sherpa.interviewsherpa.flow.application.port.in.dto.createflow.CreateFlowCommand;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor(force = true)
public class CreateFlowRequest {

	private final UUID memberId;
	private final String flow;

	public CreateFlowRequest(UUID memberId, String flow) {
		this.memberId = memberId;
		this.flow = flow;
	}

	public CreateFlowCommand toCommand() {
		return new CreateFlowCommand(memberId, flow);
	}
}
