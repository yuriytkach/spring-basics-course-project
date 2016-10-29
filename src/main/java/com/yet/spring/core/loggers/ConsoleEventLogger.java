package com.yet.spring.core.loggers;

import com.yet.spring.core.beans.Event;

public class ConsoleEventLogger implements EventLogger {

	@Override
	public void logEvent(Event event) {
		System.out.println(event.toString());
	}

}
