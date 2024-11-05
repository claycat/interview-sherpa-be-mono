package com.sherpa.interviewsherpa.flow.application.port.in.dto.createflow;

import java.util.UUID;

import com.sherpa.interviewsherpa.flow.domain.flowcontent.FlowContent;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor(force = true)
public class CreateFlowCommand {

	private final UUID memberId;
	private final FlowContent flowContent;
	private final String title;

	public CreateFlowCommand(UUID memberId, FlowContent flowContent, String title) {
		this.memberId = memberId;
		this.flowContent = flowContent;
		this.title = title;
	}

}
