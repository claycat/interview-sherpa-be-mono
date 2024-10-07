package com.sherpa.interviewsherpa.flow.domain.websocket.command.message;

import com.sherpa.interviewsherpa.flow.domain.websocket.command.enums.ClientCommandEnum;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor(force = true)
public class WebSocketClientMessage<T> implements WebSocketMessage {
	private final ClientCommandEnum command;
	private final T data;

	@Builder
	public WebSocketClientMessage(ClientCommandEnum command, T data) {
		this.data = data;
		this.command = command;
	}

}

