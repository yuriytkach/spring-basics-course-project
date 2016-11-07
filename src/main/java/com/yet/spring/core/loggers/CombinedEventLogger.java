package com.yet.spring.core.loggers;

import java.util.Collection;
import java.util.Collections;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.yet.spring.core.beans.Event;

@Component
public class CombinedEventLogger extends AbstractLogger {

    @Resource(name = "combinedLoggers")
    private Collection<EventLogger> loggers;

    @Override
    public void logEvent(Event event) {
        for (EventLogger eventLogger : loggers) {
            eventLogger.logEvent(event);
        }
    }

    public Collection<EventLogger> getLoggers() {
        return Collections.unmodifiableCollection(loggers);
    }
    
    @Value("#{'Combined ' + combinedLoggers.![name].toString()}")
    @Override
    protected void setName(String name) {
        this.name = name;
    }

}