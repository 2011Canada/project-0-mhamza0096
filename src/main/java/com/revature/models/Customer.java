package com.revature.models;

public class Customer extends User {

	private String address;
	private String phoneNumber;
	private boolean activeAccount;
	
	
	public Customer(String name, String type, String userName, String password, int accountNumber, String address, String phoneNumber) {
		super(name, userName, password, type);
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.activeAccount = false;
	}
	
	public Customer() {
		super();
	}
	
	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public boolean getActiveAccount() {
		return activeAccount;
	}

	public void setActiveAccount(boolean activeAccount) {
		this.activeAccount = activeAccount;
	}
	
	
	
	
	
	

}
