package com.sherpa.interviewsherpa.flow.domain.websocket.command;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sherpa.interviewsherpa.flow.domain.websocket.WebSocketCommand;
import com.sherpa.interviewsherpa.flow.domain.websocket.command.enums.CommandEnum;

@Component
public class WebSocketCommandFactory {

	private final Map<CommandEnum, WebSocketCommand> commandMap = new HashMap<>();

	@Autowired
	public WebSocketCommandFactory(List<WebSocketCommand> commands) {
		for (WebSocketCommand command : commands) {
			commandMap.put(command.getCommandType(), command);
		}
	}

	public WebSocketCommand getCommand(CommandEnum commandEnum) {

		return commandMap.get(commandEnum);
	}

}
