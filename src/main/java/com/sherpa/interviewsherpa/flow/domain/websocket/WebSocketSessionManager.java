package com.sherpa.interviewsherpa.flow.domain.websocket;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

@Component
public class WebSocketSessionManager {
	private final CopyOnWriteArrayList<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

	public void addSession(WebSocketSession session) {
		sessions.add(session);
	}

	public void removeSession(WebSocketSession session) {
		sessions.remove(session);
	}

	public void broadCast(TextMessage message) throws IOException {
		for (WebSocketSession session : sessions) {
			session.sendMessage(message);
		}
	}

}
