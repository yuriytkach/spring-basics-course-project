package com.yet.spring.core;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.yet.spring.core.App;

public class TestApp {
	
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
		App.main(null);
		String msg = outContent.toString();
		Assert.assertFalse(msg.contains("1"));
		Assert.assertTrue(msg.contains("John Smith"));
	}

}
