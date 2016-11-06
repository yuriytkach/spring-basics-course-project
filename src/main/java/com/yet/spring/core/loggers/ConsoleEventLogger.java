package com.yet.spring.core.loggers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.yet.spring.core.beans.Event;

@Component
public class ConsoleEventLogger extends AbstractLogger {

    @Override
    public void logEvent(Event event) {
        System.out.println(event.toString());
    }

    @Value("Console logger")
    @Override
    protected void setName(String name) {
        this.name = name;
    }

}
