package com.yet.spring.core.aspects;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.junit.Test;

import com.yet.spring.core.loggers.CombinedEventLogger;
import com.yet.spring.core.loggers.ConsoleEventLogger;


public class TestStatisticsAspect {

    @Test
    public void testStatisticCounter() {
        JoinPoint jp = mock(JoinPoint.class);
        
        when(jp.getTarget()).thenReturn(new ConsoleEventLogger())
            .thenReturn(new CombinedEventLogger(Collections.emptyList()))
            .thenReturn(new ConsoleEventLogger());
        
        StatisticsAspect aspect = new StatisticsAspect();
        
        aspect.count(jp);
        aspect.count(jp);
        aspect.count(jp);
        
        verify(jp, atMost(3)).getTarget();
        
        Map<Class<?>, Integer> counters = aspect.getCounter();
        assertEquals(2, counters.size());
        assertEquals(2, counters.get(ConsoleEventLogger.class).intValue());
        assertEquals(1, counters.get(CombinedEventLogger.class).intValue());
    }

}
