package com.yet.spring.core.loggers;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.yet.spring.core.beans.Event;

@Component
public class CacheFileEventLogger extends FileEventLogger {

    // Use system property cache.size or 5 if property is not set
    @Value("${cache.size:5}")
    private int cacheSize;

    private List<Event> cache;

    public CacheFileEventLogger() {
    }

    public CacheFileEventLogger(String filename, int cacheSize) {
        super(filename);
        this.cacheSize = cacheSize;
    }

    @PostConstruct
    public void initCache() {
        this.cache = new ArrayList<Event>(cacheSize);
    }

    @PreDestroy
    public void destroy() {
        if (!cache.isEmpty()) {
            writeEventsFromCache();
        }
    }

    @Override
    public void logEvent(Event event) {
        cache.add(event);

        if (cache.size() == cacheSize) {
            writeEventsFromCache();
            cache.clear();
        }
    }

    private void writeEventsFromCache() {
        cache.stream().forEach(super::logEvent);
    }
    
    @Value("#{fileEventLogger.name + ' with cache'}")
    @Override
    protected void setName(String name) {
        this.name = name;
    }

}
