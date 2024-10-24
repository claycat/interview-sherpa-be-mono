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
	private final String title;

	public CreateFlowCommand(UUID memberId, String flow, String title) {
		this.memberId = memberId;
		this.flow = flow;
		this.title = title;
	}

}
