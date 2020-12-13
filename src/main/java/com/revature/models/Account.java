package com.revature.models;

public class Account {
	
	private int accountNumber;
	private String accountName;
	private int ammount;
	private boolean activeAccount;
	private int user_id;
	
	public Account(String accountName, int ammount, boolean activeAccount) {
		super();
		this.accountName = accountName;
		this.ammount = ammount;
		this.activeAccount = activeAccount;
	}
	
	public Account() {
		// TODO Auto-generated constructor stub
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

	public boolean isActiveAccount() {
		return activeAccount;
	}

	public void setActiveAccount(boolean activeAccount) {
		this.activeAccount = activeAccount;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
	
	
	
	

}
