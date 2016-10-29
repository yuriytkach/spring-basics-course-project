package com.yet.spring.core.loggers;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.DateFormat;
import java.util.Date;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.yet.spring.core.beans.Event;
import com.yet.spring.core.loggers.ConsoleEventLogger;

public class TestConsoleEventLogger {
	
    private static final String MSG = "Message";
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    
    private PrintStream stdout;
    
    @Before
    public void setUpStreams() {
        stdout = System.out;
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(stdout);
    }

    @Test
    public void testLogEvent() {
        ConsoleEventLogger logger = new ConsoleEventLogger();
        Date date = new Date();
        Event event = new Event(date, DateFormat.getDateTimeInstance());
        event.setMsg(MSG);
        
        logger.logEvent(event);
        
        Assert.assertTrue(outContent.toString().contains(MSG));

        Assert.assertEquals(event.toString().trim(), outContent.toString().trim());
    }

}
