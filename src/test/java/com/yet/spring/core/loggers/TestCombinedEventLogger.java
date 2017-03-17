package com.yet.spring.core.loggers;

import com.yet.spring.core.beans.Event;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class TestCombinedEventLogger {

    private Collection<EventLogger> loggers;

    @Before
    public void createLoggers() {
        loggers = Stream.of(
                mock(EventLogger.class),
                mock(EventLogger.class)
        ).collect(Collectors.toList());
    }

    @Test
    public void testCallingAllLoggers() {
        Event event = mock(Event.class);

        CombinedEventLogger combinedEventLogger = new CombinedEventLogger(loggers);
        combinedEventLogger.logEvent(event);

        loggers.forEach(logger -> verify(logger, atMost(1)).logEvent(event));
    }

    /* Do we really need this test of a setter?! :) */
    @Test
    public void testAllLoggersReturnedInSetter() {
        CombinedEventLogger combinedEventLogger = new CombinedEventLogger(loggers);
        combinedEventLogger.getLoggers()
            .stream()
            .map(logger -> loggers.contains(logger))
            .forEach(Assert::assertTrue);
    }
}
