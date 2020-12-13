package com.revature.services;

import java.util.List;
import java.util.Scanner;

import com.revature.models.Account;
import com.revature.models.Customer;
import com.revature.models.Transaction;
import com.revature.models.User;
import com.revature.repositories.EmployeeDAO;

public class EmployeeServices {

	public void approveOrRejectAccounts() {
		EmployeeDAO emdao = new EmployeeDAO();
		List<Account> allAccounts = emdao.approveOrRejectAccounts();
		for (int i = 0; i < allAccounts.size(); i++) {
			Scanner s = new Scanner(System.in);
			System.out.println("Approve(a) or Reject(r) " + allAccounts.get(i).getAccountName() + " of ammount " + allAccounts.get(i).getAmmount());
			String input = s.next();
			
			if(input.equals("a")) {
				//accept user
				emdao.acceptUser(allAccounts.get(i));
			}
			else {
				//remove user
				emdao.rejectUser(allAccounts.get(i));
			}
		}
	}
	
	public void getAllCustomersAccounts(){
		EmployeeDAO emdao = new EmployeeDAO();
		List<Account> al = emdao.getAllCustomersAccounts();
		for (int i = 0; i < al.size(); i++) {
			System.out.println("Account Number: " + al.get(i).getAccountNumber() + 
								" Ammount: " + al.get(i).getAmmount());
		}
	}
	
	public void getAllTransactions() {
		EmployeeDAO emdao = new EmployeeDAO();
		List<Transaction> al = emdao.getAllTransactions();
		for (int i = 0; i < al.size(); i++) {
			System.out.println(" Ammount: " + al.get(i).getTransactionAmmount()+ 
								" Comment: " + al.get(i).getTransactioComment());
		}
	}
}
