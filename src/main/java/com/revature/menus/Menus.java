package com.revature.menus;

import java.io.Console;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.revature.models.User;
import com.revature.repositories.LoginDAO;
import com.revature.services.BankLauncher;
import com.revature.services.CustomerServices;
import com.revature.services.EmployeeServices;

public class Menus {
	
	//Main menu that is called from the launcher 
	public void mainMenu() {
		int choice = 0;
		while ((choice <= 0) || (choice > 4)) {
			
			System.out.println("\n**************************************************" + 
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
			
			//pick the appropriate menu to call next based on the user input above
			switch(choice) {
			case 1:
				login();
				choice = 0;
			break;
			
			case 2:
				createNewAccount();
				choice = 0;
			break;
				
			case 3:
				System.out.println("Thank you for Using Bank of Canada");
			break;
		}
		}

	}
	
	//Menu for user to login
	public void login() {
		
		Scanner s = new Scanner(System.in);
		
		User u = null;
		while(u == null) {
			
			//get username and password from user
			System.out.print("Enter username :");
			String userName = s.nextLine();
			
			System.out.print("Enter Password :");
			String password = s.nextLine();
			
			
			LoginDAO ldao = new LoginDAO();
			//retrieve the user from the database
			u = ldao.login(userName, password);
			
			//if user does not exist add the login attempt to the log file
			if(u == null) {
				System.out.println("Incorrect username or password, please try again");
				BankLauncher.logger.debug("Login attempt was made with username: " + userName + " and password: " + password);
			}
		}
		
		//fancy animation to show logging in
		System.out.print("Logging in.");
		for (int i = 0; i < 10; i++) {
			System.out.print(".");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//add debug info of login to the log file
		BankLauncher.logger.debug("User " + u.getName() + " logged in");
		
		//based on the user type whether that is an employee or a customer pick the appropriate menu
		if (u.getType().equals("employee")) {
			employeeMenu(u);
			
		}
		else {
			customerMenu(u);
		}
	}
	
	//Menu to create a new account
	public void createNewAccount() {
		System.out.println("Create new Account");
		Scanner s = new Scanner(System.in);
		
		System.out.print("Enter name:");
		String name = s.nextLine();
		
		System.out.print("Enter username:");
		String userName = s.nextLine();
		
		System.out.print("Enter password:");
		String password = s.nextLine();
		
		System.out.print("Enter address:");
		String address = s.nextLine();
		
		System.out.print("Enter phone number:");
		String phoneNumber = s.nextLine();
		
		/////////////////////////////////////////////////////
		CustomerServices cs = new CustomerServices();
		cs.createAccount(name, userName, password, address, phoneNumber);
		BankLauncher.logger.info("New account was created with username " + userName);
	}
	
	//menu for an employee
	public void employeeMenu(User u) {
		
		int choice = 0;
		
		while(choice <= 0 || choice > 4) {
			
			System.out.println("\n**********************************");
			System.out.println("\nWelcome " + u.getName() +  
								"\n1. Approve or Reject accounts" + 
								"\n2. View customers bank account" + 
								"\n3. View transaction logs" + 
								"\n4. Logout");
			
			Scanner s = new Scanner(System.in);
			
			try {
				choice = s.nextInt();
			}catch(InputMismatchException e) {
				System.out.println("Invalid input, please try again");
				choice = 0;
			}
			
			
			EmployeeServices es = new EmployeeServices();
			//call the appropriate service based on the user's choice
			switch (choice) {
					case 1:
						//approve or reject accounts
						es.approveOrRejectAccounts();
						choice = 0;
					break;

					case 2:
						//view customers bank account
						es.getAllCustomersAccounts();
						choice = 0;
					break;
					
					case 3:
						//get all transactions
						es.getAllTransactions();
						choice = 0;
					break;
					
					case 4:
						//Logout
					break;
			}	
			
		}
		
			
	}
	
	public void customerMenu(User u) {
		int input = 0;
		
		
		while(input <= 0 || input > 7) {
			System.out.println("\n**********************************");
			System.out.println("\nWelcome " + u.getName() +  
					"\n1. Apply for new Account" + 
					"\n2. View Balance" + 
					"\n3. Withdraw" + 
					"\n4. Deposit" + 
					"\n5. Transfer money" + 
					"\n6. Check Receiving money" + 
					"\n7. Logout");
			
			Scanner s = new Scanner(System.in);
			try {
				input = s.nextInt();
			}catch(InputMismatchException e) {
				System.out.println("Invalid input, please try again");
				input = 0;
			}
			CustomerServices cs = new CustomerServices();
			
			//call the appropriate customer service method based on the user choice
			switch(input) {
			case 1:
				//apply for a new bank account
				cs.applyForNewAccount(u);
				input = 0;
			break;
			
			case 2:
				//view balance for all accounts
				cs.viewBalance(u);
				input = 0;
			break;
				
			case 3:
				//withdraw any amount
				cs.withDraw(u);
				input = 0;
			break;
				
			case 4:
				//deposit money to one of user's accounts
				cs.deposit(u);
				input = 0;
			break;
				
			case 5:
				//transfer money to any other users
				cs.transferMoney();
				input = 0;
			break;
			
			case 6:
				//check whether or not you have received any transfers
				cs.checkReceivingMoney(u);
				input = 0;
			break;
			
			case 7:
				//logout
			break;
		}
		}
	}

}
