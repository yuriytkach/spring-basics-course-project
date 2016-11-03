package com.yet.spring.core.loggers;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.yet.spring.core.beans.Event;

public class FileEventLogger extends AbstractLogger {
	
	private File file;
	
	private String filename;
	
	public FileEventLogger(String filename) {
		this.filename = filename;
	}
	
	public void init() throws IOException {
		file = new File(filename);
		if (file.exists() && !file.canWrite()) {
			throw new IllegalArgumentException("Can't write to file " + filename);
		} else if (!file.exists()) {
	        file.createNewFile();
		}
	}

	@Override
	public void logEvent(Event event) {
		try {
			FileUtils.writeStringToFile(file, event.toString() + "\n", true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
