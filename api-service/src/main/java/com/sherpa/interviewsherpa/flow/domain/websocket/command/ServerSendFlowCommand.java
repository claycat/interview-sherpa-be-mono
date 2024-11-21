package com.sherpa.interviewsherpa.flow.domain.websocket.command;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;

import com.sherpa.interviewsherpa.flow.adapter.out.persistence.repository.FlowRepository;
import com.sherpa.interviewsherpa.flow.domain.websocket.WebSocketCommand;
import com.sherpa.interviewsherpa.flow.domain.websocket.command.enums.CommandEnum;
import com.sherpa.interviewsherpa.flow.domain.websocket.command.enums.ServerCommandEnum;
import com.sherpa.interviewsherpa.flow.domain.websocket.command.message.WebSocketMessage;
import com.sherpa.interviewsherpa.flow.domain.websocket.command.message.WebSocketServerMessage;

@Component
public class ServerSendFlowCommand implements WebSocketCommand {

	private final FlowRepository flowRepository;

	public ServerSendFlowCommand(FlowRepository flowRepository) {
		this.flowRepository = flowRepository;
	}

	@Override
	public WebSocketMessage execute(TextMessage message) {
		var flowJpaEntity = flowRepository.findAll().getFirst();

		return WebSocketServerMessage.builder()
			.command(ServerCommandEnum.SERVER_SEND_FLOW_RESPONSE)
			.data(flowJpaEntity.getFlowContent())
			.build();
	}

	@Override
	public CommandEnum getCommandType() {
		return ServerCommandEnum.SERVER_SEND_FLOW;
	}
}
