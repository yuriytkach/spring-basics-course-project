package com.yet.spring.core.aspects;

import com.yet.spring.core.beans.Event;
import com.yet.spring.core.loggers.EventLogger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class TestConsoleLoggerLimitAspect {

    @Test
    public void testAroundLogEvent() throws Throwable {
        ProceedingJoinPoint jp = mock(ProceedingJoinPoint.class);
        EventLogger mockLogger = mock(EventLogger.class);
        Event mockEvent = mock(Event.class);

        when(mockLogger.getName()).thenReturn("MockLogger");

        ConsoleLoggerLimitAspect aspect = new ConsoleLoggerLimitAspect(2, mockLogger);

        aspect.aroundLogEvent(jp, mockEvent);
        aspect.aroundLogEvent(jp, mockEvent);
        aspect.aroundLogEvent(jp, mockEvent);

        verify(jp, atMost(2)).proceed();
        verify(mockLogger, atMost(1)).logEvent(mockEvent);
    }

}
