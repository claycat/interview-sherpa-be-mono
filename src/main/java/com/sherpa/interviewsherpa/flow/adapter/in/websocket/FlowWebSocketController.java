package com.sherpa.interviewsherpa.flow.adapter.in.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sherpa.interviewsherpa.flow.adapter.in.websocket.dto.FlowRequestResponse;
import com.sherpa.interviewsherpa.flow.adapter.in.websocket.dto.FlowUpdateRequest;
import com.sherpa.interviewsherpa.flow.adapter.out.persistence.repository.FlowRepository;
import com.sherpa.interviewsherpa.flow.application.port.in.FlowRequestUseCase;
import com.sherpa.interviewsherpa.flow.domain.websocket.command.WebSocketCommandFactory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class FlowWebSocketController {

	private final WebSocketCommandFactory webSocketCommandFactory;
	private final FlowRequestUseCase flowRequestUseCase;
	@Autowired
	private FlowRepository flowRepository;
	private ObjectMapper objectMapper;

	public FlowWebSocketController(WebSocketCommandFactory webSocketCommandFactory,
		FlowRequestUseCase flowRequestUseCase) {
		this.webSocketCommandFactory = webSocketCommandFactory;
		this.flowRequestUseCase = flowRequestUseCase;
	}

	@MessageMapping("/flow/request")
	@SendTo("/topic/flow")
	public FlowRequestResponse sendFlow() {

		var result = flowRequestUseCase.getFlow(null);
		return new FlowRequestResponse(result.getFlowId(), result.getFlow());
	}

	@MessageMapping("/flow/update")
	@SendTo("/topic/flow")
	public void saveFlow(FlowUpdateRequest request) {

		System.out.println("request = " + request);
		objectMapper = new ObjectMapper();

		log.info("Received flow update request: {}", request);
		var flowJpaEntity = flowRepository.findAll().getFirst();
		flowJpaEntity.setFlowContent(request.getFlow());
		flowRepository.save(flowJpaEntity);
	}

}
