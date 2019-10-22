package com.briup.log;

import java.util.Properties;
import org.apache.log4j.Logger;

import org.apache.log4j.PropertyConfigurator;

import com.briup.util.Log;

public class LogIMP implements Log {
	private static Logger log = null;
//	static {
//		log = Logger.getRootLogger();
//		PropertyConfigurator.configure("src/main/resources/log4j.properties");
//	}

	@Override
	public void init(Properties properties) throws Exception {
		String string = properties.getProperty("log4j-properties");
		log = Logger.getRootLogger();
		PropertyConfigurator.configure(string);
	}

	public void debug(String message) {
		log.debug(message);
	}

	public void info(String message) {
		log.info(message);
	}

	public void warn(String message) {
		log.warn(message);
	}

	public void error(String message) {
		log.error(message);
	}

	public void fatal(String message) {
		log.fatal(message);
	}

}
