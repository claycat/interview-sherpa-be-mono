package com.sherpa.interviewsherpa.flow.adapter.in.websocket;

import java.util.UUID;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.sherpa.interviewsherpa.flow.adapter.in.http.dto.getflow.GetFlowResponse;
import com.sherpa.interviewsherpa.flow.adapter.in.websocket.dto.PatchFlowRequest;
import com.sherpa.interviewsherpa.flow.application.port.in.GetFlowUseCase;
import com.sherpa.interviewsherpa.flow.application.port.in.PatchFlowUseCase;
import com.sherpa.interviewsherpa.flow.application.port.in.dto.getflow.GetFlowCommand;
import com.sherpa.interviewsherpa.flow.application.port.in.dto.patchflow.PatchFlowCommand;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class FlowWebSocketController {

	private final GetFlowUseCase getFlowUseCase;
	private final PatchFlowUseCase patchFlowUseCase;

	public FlowWebSocketController(GetFlowUseCase getFlowUseCase, PatchFlowUseCase patchFlowUseCase) {
		this.getFlowUseCase = getFlowUseCase;
		this.patchFlowUseCase = patchFlowUseCase;
	}

	@MessageMapping("/flow/{flowId}/get")
	@SendTo("/topic/flow/{flowId}")
	public GetFlowResponse sendFlow(@DestinationVariable UUID flowId) {
		var command = new GetFlowCommand(flowId);
		var result = getFlowUseCase.getFlow(command);
		return new GetFlowResponse(result.flow());
	}

	@MessageMapping("/flow/{flowId}/patch")
	@SendTo("/topic/flow/{flowId}")
	public void patchFlow(@DestinationVariable UUID flowId, PatchFlowRequest request) {
		var command = new PatchFlowCommand(flowId, request.flow());
		patchFlowUseCase.patchFlow(command);
	}

}
