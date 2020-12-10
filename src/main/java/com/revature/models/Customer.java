package com.revature.models;

public class Customer extends User {

	private int accountNumber;
	private String address;
	private String phoneNumber;
	private boolean activeAccount;
	
	
	public Customer(String name, String type, int accountNumber, String address, String phoneNumber) {
		super(name, type);
		this.accountNumber = accountNumber;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.activeAccount = false;
	}


	public int getAccountNumber() {
		return accountNumber;
	}


	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
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
	
	
	
	

}
