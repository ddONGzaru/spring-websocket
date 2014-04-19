package com.zaru.websocket;

import java.util.UUID;

import org.springframework.context.ApplicationEvent;

public class WebSocketEvent extends ApplicationEvent {

	private static final long serialVersionUID = 5996075910333970209L;

	public WebSocketEvent(Object source) {
		super(source);
	}
	
	public String getToken() {
		return UUID.randomUUID().toString();
	}
}
