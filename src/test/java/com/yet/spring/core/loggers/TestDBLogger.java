package com.yet.spring.core.loggers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.yet.spring.core.beans.Event;
import com.yet.spring.core.spring.DBConfig;
import com.yet.spring.core.spring.LoggerConfig;

public class TestDBLogger {
    
    @BeforeClass
    public static void initTestDbProps() {
        System.setProperty("DB_PROPS", "classpath:db_for_test.properties");
        Event.initAutoId(-1);
    }
    
    private AnnotationConfigApplicationContext ctx;
    
    @Before
    public void createContext() {
        ctx  =  new AnnotationConfigApplicationContext();
        ctx.register(LoggerConfig.class, DBConfig.class);
        ctx.scan(EventLogger.class.getPackage().getName(), Event.class.getPackage().getName());
        ctx.refresh();
    }
    
    @After
    public void closeContext() {
        ctx.close();
    }
    
    
    @Test
    public void testEventAutoIdCounterSet() {
        Event event = new Event(null, null);
        assertTrue(event.getId() > -1);
    }
    
    @Test
    public void testSaveAndGet() {
        DBLogger dbLogger = ctx.getBean("dbLogger", DBLogger.class);
        
        Event event1 = ctx.getBean(Event.class);
        event1.setMsg("a");
        
        dbLogger.logEvent(event1);
        
        int total = dbLogger.getTotalEvents();
        assertEquals(1, total);
        
        Event event2 = ctx.getBean(Event.class);
        event2.setMsg("b");
        
        dbLogger.logEvent(event2);
        
        total = dbLogger.getTotalEvents();
        assertEquals(2, total);
        
        List<Event> allEvents = dbLogger.getAllEvents();
        assertEquals(2, allEvents.size());
        assertTrue(allEvents.contains(event1));
        assertTrue(allEvents.contains(event2));
    }
}