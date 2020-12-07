package com.revature.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class DebugLauncher {

	static Logger something =  LogManager.getLogger("com.revature.project-0-hamza.0096");
	
	public static void main(String[] args) {
		
		something.info("hmm");
	}

}
