package com.sherpa.interviewsherpa.flow.domain.websocket;

import org.springframework.web.socket.TextMessage;

import com.sherpa.interviewsherpa.flow.domain.websocket.command.enums.CommandEnum;
import com.sherpa.interviewsherpa.flow.domain.websocket.command.message.WebSocketMessage;

public interface WebSocketCommand {

	WebSocketMessage execute(TextMessage message);

	CommandEnum getCommandType();
}
