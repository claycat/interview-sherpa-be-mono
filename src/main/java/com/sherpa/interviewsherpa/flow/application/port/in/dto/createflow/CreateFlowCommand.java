package com.sherpa.interviewsherpa.flow.application.port.in.dto.createflow;

import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor(force = true)
public class CreateFlowCommand {

	private final UUID memberId;
	private final String flow;

	public CreateFlowCommand(UUID memberId, String flow) {
		this.memberId = memberId;
		this.flow = flow;
	}

}
