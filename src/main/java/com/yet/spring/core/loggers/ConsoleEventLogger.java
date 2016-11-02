package com.yet.spring.core.loggers;

import org.springframework.stereotype.Component;

import com.yet.spring.core.beans.Event;

@Component
public class ConsoleEventLogger implements EventLogger {

    @Override
    public void logEvent(Event event) {
        System.out.println(event.toString());
    }

}
