package com.yet.spring.core;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.yet.spring.core.loggers.ConsoleEventLogger;

public class TestConsoleEventLogger {
	
	private static final String MSG = "Message";
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	
	@Before
	public void setUpStreams() {
		System.setOut(new PrintStream(outContent));
	}

	@After
	public void cleanUpStreams() {
	    System.setOut(null);
	}

	@Test
	public void testLogEvent() {
		ConsoleEventLogger logger = new ConsoleEventLogger();
		logger.logEvent(MSG);
		Assert.assertTrue(outContent.toString().contains(MSG));
	}

}
