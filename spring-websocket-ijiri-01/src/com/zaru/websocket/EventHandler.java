package com.zaru.websocket;

import java.util.Map;
import java.util.UUID;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class EventHandler extends TextWebSocketHandler implements ApplicationListener<WebSocketEvent> {
    
	//세션 일람
    private Map<String, WebSocketSession> sessionMap_ = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        this.sessionMap_.put(session.getId(), session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session,
            CloseStatus status) throws Exception {
        this.sessionMap_.remove(session.getId());
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {

    	//접속되어있는 세션(자신도 포함)에 전송
        for (Entry<String, WebSocketSession> entry : this.sessionMap_.entrySet()) {
            entry.getValue().sendMessage(message);
        }
    }

	@Override
	public void onApplicationEvent(WebSocketEvent event) {
		// TODO Auto-generated method stub
		
		String msg = event.getToken();
		
		if (msg == null || msg == "") {
			msg = UUID.randomUUID().toString();
		}
		
		TextMessage message = new TextMessage(event.getToken()); 
		try {
			handleTextMessage(null, message);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
