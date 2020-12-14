package com.revature.models;

public class Transfer {
	
	private int recieverAccount;
	private int ammount;
	private int senderAccount;
	private int transfer_id;
	
	
	public Transfer(int accountNumber, int ammount, int senderAccount) {
		this.recieverAccount = accountNumber;
		this.ammount = ammount;
		this.senderAccount = senderAccount;
	}
	
	public Transfer() {
		super();
	}
	
	public int getRecieverAccount() {
		return recieverAccount;
	}
	public void setRecieverAccount(int recieverAccount) {
		this.recieverAccount = recieverAccount;
	}
	public int getAmmount() {
		return ammount;
	}
	public void setAmmount(int ammount) {
		this.ammount = ammount;
	}
	public int getSenderAccount() {
		return senderAccount;
	}
	public void setSenderAccount(int senderAccount) {
		this.senderAccount = senderAccount;
	}

	public int getTransfer_id() {
		return transfer_id;
	}

	public void setTransfer_id(int transfer_id) {
		this.transfer_id = transfer_id;
	}
	
	
	
	

}
