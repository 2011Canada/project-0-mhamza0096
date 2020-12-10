package com.revature.menus;

import java.io.Console;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menus {
	
	public void mainMenu() {
		int choice = 0;
		while ((choice <= 0) || (choice > 4)) {
			
			System.out.println("**************************************************" + 
					"\n***************** Bank of Canada *****************" + 
					"\n**************************************************" +
					"\n1. Login" + 
					"\n2. Create new account" +
					"\n3. Exit the application");
	
			try {
				Scanner s = new Scanner(System.in);
				choice = s.nextInt();
			}
			catch(InputMismatchException e){
				choice = 0;
				System.out.println("Invalid input, please try again!");
			}
			
			if((choice <= 0) || (choice > 4))
			{
				System.out.println("Invalid input, please try again!");
			}
		}
		switch(choice) {
			case 1:
				login();
			break;
			
			case 2:
				createNewAccount();
			break;
				
			case 3:
				System.out.println("Thank you for Using Bank of Canada");
			break;
		}
	}
	
	
	public void login() {
		
		Scanner s = new Scanner(System.in);
		
		System.out.print("Enter username :");
		String username = s.nextLine();
		
		System.out.print("Enter Password :");
		String password = s.nextLine();
		
		/////////////////////////////////////////////////////////////////////////
		
		
		//////////////////////////////////////////////////////////////////////////
		System.out.print("Logging in.");
		for (int i = 0; i < 10; i++) {
			System.out.print(".");
			try {
				Thread.sleep(700);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		employeeMenu();
		
		
		
	}
	
	public void createNewAccount() {
		System.out.println("Create new Account");
		Scanner s = new Scanner(System.in);
		
		System.out.print("Enter name:");
		String name = s.nextLine();
		
		System.out.print("Enter address:");
		String address = s.nextLine();
		
		System.out.print("Enter phone number:");
		String phoneNumber = s.nextLine();
		
		/////////////////////////////////////////////////////
		
		/////////////////////////////////////////////////////
		
		System.out.println("Your account has been successfully created and is pending for approval");
		
		
	}
	
	public void employeeMenu() {
		System.out.println("\nWelcome [name of employee]" + 
							"\n1. Approve or Reject accounts" + 
							"\n2. View customers bank account" + 
							"\n3. View transaction logs");
		
							
	}
	
	public void customerMenu() {
		
	}

}
