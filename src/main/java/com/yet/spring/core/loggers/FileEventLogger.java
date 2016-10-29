package com.yet.spring.core.loggers;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.yet.spring.core.beans.Event;

public class FileEventLogger implements EventLogger {
	
	private File file;
	private String filename;
	
	public FileEventLogger(String filename) {
		this.filename = filename;
	}
	
	public void init() {
		file = new File(filename);
		if (file.exists() && !file.canWrite()) {
			throw new IllegalArgumentException("Can't write to file " + filename);
		} else if (!file.exists()) {
		    try {
		        file.createNewFile();
		    } catch (Exception e) {
		        throw new IllegalArgumentException("Can't create file", e);
		    }
		    
		}
	}

	@Override
	public void logEvent(Event event) {
		try {
			FileUtils.writeStringToFile(file, event.toString(), true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
