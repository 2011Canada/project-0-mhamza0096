package com.revature.models;

public class Account {
	
	private int accountNumber;
	private int ammount;
	
	public Account(int accountNumber, int ammount) {
		super();
		this.accountNumber = accountNumber;
		this.ammount = ammount;
	}
	
	public int getAccountNumber() {
		return accountNumber;
	}
	
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	public int getAmmount() {
		return ammount;
	}
	
	public void setAmmount(int ammount) {
		this.ammount = ammount;
	}
	
	

}
