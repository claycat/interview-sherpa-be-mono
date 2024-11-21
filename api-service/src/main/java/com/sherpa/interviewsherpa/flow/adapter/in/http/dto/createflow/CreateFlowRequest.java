package com.sherpa.interviewsherpa.flow.adapter.in.http.dto.createflow;

import java.util.UUID;

import com.sherpa.interviewsherpa.flow.application.port.in.dto.createflow.CreateFlowCommand;
import com.sherpa.interviewsherpa.flow.domain.flowcontent.FlowContent;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor(force = true)
public class CreateFlowRequest {

	private final UUID memberId;
	private final FlowContent flowContent;
	private final String title;

	public CreateFlowRequest(UUID memberId, FlowContent flowContent, String title) {
		this.memberId = memberId;
		this.flowContent = flowContent;
		this.title = title;
	}

	public CreateFlowCommand toCommand() {
		return new CreateFlowCommand(memberId, flowContent, title);
	}
}
