package com.zaru.websocket;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WebRequestHandler {

	@Resource
	private ApplicationContext applicationContext;
	
	@RequestMapping(value = "/webEvent", method = RequestMethod.GET)
	public String handle(HttpSession session) {
		
		
		while (true) {
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			applicationContext.publishEvent(new WebSocketEvent(this));
		}
		
		
		//return "index";
	}
	
}
