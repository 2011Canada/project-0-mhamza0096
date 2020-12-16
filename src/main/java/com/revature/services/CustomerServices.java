package com.revature.services;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.revature.models.Account;
import com.revature.models.Customer;
import com.revature.models.Transfer;
import com.revature.models.User;
import com.revature.repositories.CustomerDAO;

public class CustomerServices {

	//method that will create a new account
	public void createAccount(String name, String userName, String password, String address, String phoneNumber) {
		//create a new customer object based on the user input
		Customer c = new Customer(name, "customer", userName, password, address, phoneNumber);
		CustomerDAO cdao = new CustomerDAO();
		
		//persist the customer object to the database
		cdao.createAccount(c);
		
		//update the transaction table in the database
		cdao.updateTransaction("New account was created by the name of " + c.getName());
	}
	
	//method will let user apply for a new account
	public void applyForNewAccount(User u) {
		
		Scanner s = new Scanner(System.in);
		
		System.out.print("Please Enter account Name:");
		String accountName = s.nextLine();
		int exit = 1;
		int ammount = 0;
		
		
		while(exit > 0) {
			System.out.print("Please Enter starting ammount:");
			try {
				ammount = s.nextInt();
				exit = -1;
			}catch(InputMismatchException e) {
				System.out.println("Invalid input, please try again");
				s.next();
			}
			
		}
		
		//create a new account object based on the user input, also set account activation to false
		Account a = new Account(accountName, ammount, false);
		CustomerDAO cdao = new CustomerDAO();
		cdao.applyForNewAccount(a, u);
		BankLauncher.logger.info(u.getName() + " applied for a new bank account");
		
	}
	
	//method checks and retrieves balances of all users' accounts
	public void viewBalance(User u) {
		CustomerDAO cdao = new CustomerDAO();
		
		//retrieve the list of accounts user the user's name
		List<Account> accList = cdao.viewBalance(u);
		
		//print out all the accounts of the user
		for(int i = 0; i < accList.size(); i++) {
			if(accList.get(i).isActiveAccount()) {
				System.out.println("Account number: " + accList.get(i).getAccountNumber() + " " + accList.get(i).getAccountName() + " " + accList.get(i).getAmmount());
			}
		}
		BankLauncher.logger.info(u.getName() + " viewed their balance");
		
	}
	
	//withdraw money from one of user's accounts
	public void withDraw(User u) {
		Scanner s = new Scanner(System.in);
		CustomerDAO cdao = new CustomerDAO();
		
		int exit = 1;
		int accountNumber = 0;
		int withdrawAmmount = 0;
		
		while(exit > 0) {
			try {
				System.out.print("Please enter the account number you like to withdraw from:");
				accountNumber = s.nextInt();
				
				System.out.print("Please enter the ammount you like to withdraw:");
				withdrawAmmount = s.nextInt();
				exit = -1;
			}catch(InputMismatchException e) {
				System.out.println("\nInvalid input, please try again");
				accountNumber = 0;
				s.next();
			}

		}
		
		//retrieve the amount from the user's account for checking
		int ammount = cdao.withdraw(accountNumber);
		
		if(withdrawAmmount > ammount) {
			System.out.println("You dont have enough funds in your current account or you account does not exist");
		}
		else{
			System.out.println("You withdrew " + withdrawAmmount);
			cdao.updateTransaction(u.getName() + " withdrew " + withdrawAmmount + " from account number " + accountNumber);
			cdao.updateBalance(accountNumber, withdrawAmmount);
			BankLauncher.logger.info(u.getName() + " withdrew " + ammount + " from account number " + accountNumber);
		}
		
		
	}

	//the following method will deposit money into one of user's accounts
	public void deposit(User u) {
		CustomerDAO cdao = new CustomerDAO();
		Scanner s = new Scanner(System.in);
		int exit = 1;
		int accountNumber = 0;
		int ammount = 0; 
		
		while(exit > 0) {
			try {
				
				System.out.print("Please enter the account number you like to deposit to:");
				accountNumber = s.nextInt();
				System.out.print("Please enter the ammount you like to deposit:");
				ammount = s.nextInt();
				exit = -1;
				
			}catch(InputMismatchException e) {
				System.out.println("Invalid input, please try again");
				s.next();
			}
		}
		//call the DAO and deposit the amount and update the database
		cdao.deposit(accountNumber, ammount);
		cdao.updateTransaction(u.getName() + " deposited " + ammount + " to account number " + accountNumber);
		BankLauncher.logger.info(u.getName() + " deposited " + ammount + " to account number " + accountNumber);
	}
	
	//method will transfer money from one of current user' accounts and transfer to another user's account
	public void transferMoney() {
		Scanner s = new Scanner(System.in);
		int recieverAccountNumber = 0;
		int senderAccountNumber = 0;
		int ammount = 0;
		int exit = 1;
		
		while(exit > 0) {
			try {
				System.out.print("Please enter the account number you like to transfer money to:");
				recieverAccountNumber = s.nextInt();
				
				System.out.print("Please enter the accout number of the account you would like to transfer from:");
				senderAccountNumber = s.nextInt();
				
				System.out.print("Please enter the ammount you like to transfer:");
				ammount = s.nextInt();
				exit = -1;
			}catch(InputMismatchException e) {
				System.out.println("Invalid input, please try again");
				s.next();
			}

		}
		
		//create a new transfer object with the data provided by the user
		Transfer t = new Transfer(recieverAccountNumber, ammount, senderAccountNumber);
		CustomerDAO cdao = new CustomerDAO();
		
		//perform the transfer
		cdao.transferMoney(t);
		cdao.updateTransaction("Account number " + senderAccountNumber +  " is transfering $" + ammount + " to account number " + recieverAccountNumber);
		BankLauncher.logger.info("Account number " + senderAccountNumber +  " is transfering $" + ammount + " to account number " + recieverAccountNumber);
	}
	
	//method will check if the user has any incoming transfers 
	public void checkReceivingMoney(User u) {
		
		CustomerDAO cdao = new CustomerDAO();
		
		//retrieve list of accounts that the user is receiving money from
		List<Account>  accountList = cdao.checkRecievingMoney(u);
		
		for(int i = 0; i < accountList.size(); i++) {
			
			List<Transfer> transferList = cdao.getReceivingList(accountList.get(i).getAccountNumber());
			
			for(int j = 0; j < transferList.size(); j++) {

				int exit = 1;
				while(exit > 0) {
					
					System.out.println("You are receiving " + transferList.get(j).getAmmount() + " from account number "
							+ transferList.get(j).getSenderAccount());
					System.out.print("Enter a to accept or r to reject:");
					Scanner s = new Scanner(System.in);
					String input = s.next();
					
					//update the database if the user accepts the transfer and update the logfile and transaction tables
					if (input.equals("a")) {
						cdao.accept(transferList.get(j));
						cdao.updateTransaction(u.getName() + " accepted $" + transferList.get(j).getAmmount() + " from account number "
							+ transferList.get(j).getSenderAccount());
						BankLauncher.logger.info(u.getName() + " accepted $" + transferList.get(j).getAmmount() + " from account number "
							+ transferList.get(j).getSenderAccount());
						exit = -1;
					}
					//update the database if the user rejects the transfer and update the logfile and transaction tables
					else if(input.equals("r")){
						cdao.reject(transferList.get(j));
						cdao.updateTransaction(u.getName() + " rejected $" + transferList.get(j).getAmmount() + " from account number "
								+ transferList.get(j).getSenderAccount());
						BankLauncher.logger.info(u.getName() + " rejected $" + transferList.get(j).getAmmount() + " from account number "
								+ transferList.get(j).getSenderAccount());
						exit = -1;
					}
					else {
						System.out.println("Invalid input, please try again");
						
					}
				}

			}
			
		}
	}
}
