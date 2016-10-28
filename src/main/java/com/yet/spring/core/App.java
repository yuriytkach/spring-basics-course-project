package com.yet.spring.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yet.spring.core.beans.Client;
import com.yet.spring.core.loggers.EventLogger;

public class App {

    private Client client;

    private EventLogger eventLogger;
    
    public static void main(String[] args) {
        @SuppressWarnings("resource") // We will remove this suppress in further lessons 
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        App app = (App) ctx.getBean("app");
        
        app.logEvent("Some event for 1");
        app.logEvent("Some event for 2");
    }
    
    public App(Client client, EventLogger eventLogger) {
        super();
        this.client = client;
        this.eventLogger = eventLogger;
    }

    private void logEvent(String msg) {
        String message = msg.replaceAll(client.getId(), client.getFullName());
        eventLogger.logEvent(message);
    }

}
