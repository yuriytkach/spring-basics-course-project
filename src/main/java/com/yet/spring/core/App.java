package com.yet.spring.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yet.spring.core.beans.Client;
import com.yet.spring.core.beans.Event;
import com.yet.spring.core.loggers.EventLogger;

public class App {

    private Client client;

    private EventLogger eventLogger;
    
    public static void main(String[] args) {
        @SuppressWarnings("resource") // We will remove this suppress in further lessons 
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        App app = (App) ctx.getBean("app");
        
        Event event = ctx.getBean(Event.class);
        app.logEvent(event, "Some event for 1");
        
        event = ctx.getBean(Event.class);
        app.logEvent(event, "Some event for 2");
    }
    
    public App(Client client, EventLogger eventLogger) {
        super();
        this.client = client;
        this.eventLogger = eventLogger;
    }

    private void logEvent(Event event, String msg) {
        String message = msg.replaceAll(client.getId(), client.getFullName());
        event.setMsg(message);
        eventLogger.logEvent(event);
    }

}
