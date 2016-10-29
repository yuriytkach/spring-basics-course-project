package com.yet.spring.core;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.util.Date;

import org.junit.Test;

import com.yet.spring.core.beans.Client;
import com.yet.spring.core.beans.Event;
import com.yet.spring.core.loggers.EventLogger;

public class TestApp {
	
    private static final String MSG = "Hello";

    @Test
    public void test() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        Client client = new Client("25", "Bob");
        DummyLogger dummyLogger = new DummyLogger();
        
        App app = new App(client, dummyLogger);
        
        Event event = new Event(new Date(), DateFormat.getDateTimeInstance());
        
        invokeLogEvent(app, event, MSG + " " + client.getId());
        assertTrue(dummyLogger.getEvent().getMsg().contains(MSG));
        assertTrue(dummyLogger.getEvent().getMsg().contains(client.getFullName()));
        
        invokeLogEvent(app, event, MSG + " 0");
        assertTrue(dummyLogger.getEvent().getMsg().contains(MSG));
        assertFalse(dummyLogger.getEvent().getMsg().contains(client.getFullName()));
    }

    private void invokeLogEvent(App app, Event event, String message) throws NoSuchMethodException,
            IllegalAccessException, InvocationTargetException {
        Method method = app.getClass().getDeclaredMethod("logEvent", Event.class, String.class);
        method.setAccessible(true);
        method.invoke(app, event, message);
    }
    
    private class DummyLogger implements EventLogger {
        
        private Event event;

        @Override
        public void logEvent(Event event) {
            this.event = event;
        }

        public Event getEvent() {
            return event;
        }
        
    };

}
