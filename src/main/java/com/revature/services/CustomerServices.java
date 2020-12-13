package com.revature.services;

import java.util.List;
import java.util.Scanner;

import com.revature.models.Account;
import com.revature.models.Customer;
import com.revature.models.User;
import com.revature.repositories.CustomerDAO;

public class CustomerServices {

	public void createAccount(String name, String userName, String password, String address, String phoneNumber) {
		Customer c = new Customer(name, "customer", userName, password, address, phoneNumber);
		CustomerDAO cdao = new CustomerDAO();
		cdao.createAccount(c);
	}
	
	public void applyForNewAccount(User u) {
		
		Scanner s = new Scanner(System.in);
		
		System.out.print("Please Enter account Name:");
		String accountName = s.nextLine();
		
		System.out.print("Please Enter starting ammount:");
		int ammount = s.nextInt();
		
		Account a = new Account(accountName, ammount, false);
		CustomerDAO cdao = new CustomerDAO();
		cdao.applyForNewAccount(a, u);
		
	}
	
	public void viewBalance(User u) {
		CustomerDAO cdao = new CustomerDAO();
		List<Account> accList = cdao.viewBalance(u);
		
		for(int i = 0; i < accList.size(); i++) {
			System.out.println(accList.get(i).getAccountName() + " " + accList.get(i).getAmmount());
		}
		
	}
	
	public void withDraw() {
		
	}
	
	public void deposit() {
		
	}
	
	public void transferMoney() {
		
	}
}
