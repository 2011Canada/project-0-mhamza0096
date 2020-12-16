package com.revature.services;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.revature.models.Account;
import com.revature.models.Customer;
import com.revature.models.Transaction;
import com.revature.models.User;
import com.revature.repositories.EmployeeDAO;

public class EmployeeServices {
	
	
	//method will accept or reject account
	public void approveOrRejectAccounts() {
		EmployeeDAO emdao = new EmployeeDAO();
		
		//Retrieve list of all accounts the need to be accepted or rejected
		List<Account> allAccounts = emdao.approveOrRejectAccounts();
		
		for (int i = 0; i < allAccounts.size(); i++) {
			Scanner s = new Scanner(System.in);
			
			int exit = 1;
			while(exit > 0) {
				String input = "";
				System.out.println("Approve(a) or Reject(r) " + allAccounts.get(i).getAccountName() + " of ammount " + allAccounts.get(i).getAmmount());
				
				try {
					input = s.next();
					
					if(input.equals("a")) {
						//accept account
						emdao.acceptUser(allAccounts.get(i));
						BankLauncher.logger.info("Account" + allAccounts.get(i).getAccountName() + " was accepted");
						exit = -1;
					}
					else if (input.equals("r")){
						//remove account
						emdao.rejectUser(allAccounts.get(i));
						BankLauncher.logger.info("Account" + allAccounts.get(i).getAccountName() + " was rejected");
						exit = -1;
					}
					else {
						System.out.println("Invalid input, please try again");
					}
				}catch(InputMismatchException e)
				{				
					System.out.println("Invalid input, please try again");
				}

			}

		}
	}
	
	
	//Retrieve all accounts of all customers from the database and print them to the console
	public void getAllCustomersAccounts(){
		EmployeeDAO emdao = new EmployeeDAO();
		List<Account> al = emdao.getAllCustomersAccounts();
		for (int i = 0; i < al.size(); i++) {
			System.out.println("Account Number: " + al.get(i).getAccountNumber() + 
								" Ammount: " + al.get(i).getAmmount());
		}
	}
	
	//Retrieve all transactions from the database and print them to the console
	public List<Transaction> getAllTransactions() {
		EmployeeDAO emdao = new EmployeeDAO();
		List<Transaction> al = emdao.getAllTransactions();
		for (int i = 0; i < al.size(); i++) {
			System.out.println(al.get(i).getTransactionComment());
		}
		return al;
	}
}
