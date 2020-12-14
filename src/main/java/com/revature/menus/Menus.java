package com.revature.menus;

import java.io.Console;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.revature.models.User;
import com.revature.repositories.LoginDAO;
import com.revature.services.CustomerServices;
import com.revature.services.EmployeeServices;

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
	
	
	public void login() {
		
		Scanner s = new Scanner(System.in);
		
		User u = null;
		while(u == null) {
			System.out.print("Enter username :");
			String userName = s.nextLine();
			
			System.out.print("Enter Password :");
			String password = s.nextLine();
			
			/////////////////////////////////////////////////////////////////////////
			LoginDAO ldao = new LoginDAO();
			u = ldao.login(userName, password);
			
			if(u == null) {
				System.out.println("Incorrect username or password, please try again");
			}
		}
		
		//////////////////////////////////////////////////////////////////////////
		System.out.print("Logging in.");
		for (int i = 0; i < 10; i++) {
			System.out.print(".");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		if (u.getType().equals("employee")) {
			employeeMenu(u);
			
		}
		else {
			customerMenu(u);
		}
	}
	
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
		/////////////////////////////////////////////////////
		
		
		
	}
	
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
			choice = s.nextInt();
			EmployeeServices es = new EmployeeServices();
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
						es.getAllTransactions();
						choice = 0;
					break;
					
					case 4:
						//do nothing 
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
					"\n6. Check Recieving money" + 
					"\n7. Logout");
			
			Scanner s = new Scanner(System.in);
			input = s.nextInt();
			
			CustomerServices cs = new CustomerServices();
			
			switch(input) {
			case 1:
				cs.applyForNewAccount(u);
				input = 0;
			break;
			
			case 2:
				cs.viewBalance(u);
				input = 0;
			break;
				
			case 3:
				cs.withDraw(u);
				input = 0;
			break;
				
			case 4:
				cs.deposit(u);
				input = 0;
			break;
				
			case 5:
				cs.transferMoney();
				input = 0;
			break;
			
			case 6:
				cs.checkRecievingMoney(u);
				input = 0;
			break;
			
			case 7:
				//do nothing let the method end
			break;
		}
		}
	}

}
