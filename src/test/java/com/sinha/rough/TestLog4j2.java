package com.sinha.rough;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestLog4j2 {
	
	public static void main(String[] args) {
		
		Logger log = LogManager.getLogger("test");
		
		System.out.println("This is log: ");
		
		log.info("Hello from main");
		log.error("Error");

		log.warn("Warn");

		log.debug("Debug");
	}

}
