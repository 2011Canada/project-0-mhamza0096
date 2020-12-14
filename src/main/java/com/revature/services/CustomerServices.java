package com.revature.services;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.revature.models.Account;
import com.revature.models.Customer;
import com.revature.models.Transfer;
import com.revature.models.User;
import com.revature.repositories.CustomerDAO;

public class CustomerServices {

	public void createAccount(String name, String userName, String password, String address, String phoneNumber) {
		Customer c = new Customer(name, "customer", userName, password, address, phoneNumber);
		CustomerDAO cdao = new CustomerDAO();
		cdao.createAccount(c);
		cdao.updateTransaction("New account was created by the name of " + c.getName());
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
	
	public void withDraw(User u) {
		Scanner s = new Scanner(System.in);
		CustomerDAO cdao = new CustomerDAO();
		
		System.out.print("Please enter the account number you like to withdraw from");
		int accountNumber = s.nextInt();
		
		System.out.print("Please enter the ammount you like to withdraw:");
		int withdrawAmmount = s.nextInt();
		
		int ammount = cdao.withdraw(accountNumber);
		
		if(withdrawAmmount > ammount) {
			System.out.println("You dont have enough funds in your current account");
		}
		else{
			System.out.println("You withdrew " + withdrawAmmount);
			cdao.updateTransaction(u.getName() + " withdrew " + withdrawAmmount + " from account number " + accountNumber);
			cdao.updateBalance(accountNumber, withdrawAmmount);
		}
		
		
	}

	
	public void deposit(User u) {
		CustomerDAO cdao = new CustomerDAO();
		Scanner s = new Scanner(System.in);
		
		System.out.print("Please enter the account number you like to deposit to:");
		int accountNumber = s.nextInt();
		System.out.print("Please enter the ammount you like to deposit:");
		int ammount = s.nextInt();
		
		cdao.deposit(accountNumber, ammount);
		cdao.updateTransaction(u.getName() + " deposited " + ammount + " to account number " + accountNumber);
	}
	
	public void transferMoney() {
		Scanner s = new Scanner(System.in);
		System.out.print("Please enter the account number you like to transfer money to:");
		int recieverAccountNumber = s.nextInt();
		
		System.out.print("Please enter the accout number of the account you would like to transfer from:");
		int senderAccountNumber = s.nextInt();
		
		System.out.print("Please enter the ammount you like to transfer:");
		int ammount = s.nextInt();
		
		
		
		Transfer t = new Transfer(recieverAccountNumber, ammount, senderAccountNumber);
		CustomerDAO cdao = new CustomerDAO();
		cdao.transferMoney(t);
		cdao.updateTransaction("Account number " + senderAccountNumber +  " is transfering $" + ammount + " to account number " + recieverAccountNumber);

	}

	public void checkRecievingMoney(User u) {
		
		CustomerDAO cdao = new CustomerDAO();
		List<Account>  accountList = cdao.checkRecievingMoney(u);
		
		for(int i = 0; i < accountList.size(); i++) {
			
			List<Transfer> transferList = cdao.getReceivingList(accountList.get(i).getAccountNumber());
			
			for(int j = 0; j < transferList.size(); j++) {
				System.out.println("You are receiving " + transferList.get(j).getAmmount() + " from account number "
						+ transferList.get(j).getSenderAccount());
				System.out.print("Enter a to accept or r to reject:");
				Scanner s = new Scanner(System.in);
				String input = s.next();
				
				if (input.equals("a")) {
					cdao.accept(transferList.get(j));
					cdao.updateTransaction(u.getName() + " accepted $" + transferList.get(j).getAmmount() + " from account number "
						+ transferList.get(j).getSenderAccount());
				}
				else {
					cdao.reject(transferList.get(j));
					cdao.updateTransaction(u.getName() + " rejected $" + transferList.get(j).getAmmount() + " from account number "
							+ transferList.get(j).getSenderAccount());
				}
			}
			
		}
	}
}
