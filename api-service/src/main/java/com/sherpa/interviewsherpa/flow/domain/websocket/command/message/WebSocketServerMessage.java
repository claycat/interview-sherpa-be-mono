package com.sherpa.interviewsherpa.flow.domain.websocket.command.message;

import com.sherpa.interviewsherpa.flow.domain.websocket.command.enums.ServerCommandEnum;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor(force = true)
public class WebSocketServerMessage<T> implements WebSocketMessage {
	private final ServerCommandEnum command;
	private final T data;

	@Builder
	public WebSocketServerMessage(ServerCommandEnum command, T data) {
		this.data = data;
		this.command = command;
	}

}
