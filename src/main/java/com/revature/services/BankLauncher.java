package com.revature.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.menus.Menus;

public class BankLauncher {
	public static Logger logger =  LogManager.getLogger("com.revature.project-0-hamza.0096");

	public static void main(String[] args) {
		
		//Launch the main menu of the application
		Menus menus = new Menus();
		menus.mainMenu();
	}

}
