package com.revature.models;

public class Transaction {
	
	private int transactionAmmount;
	private String transactioComment;
	
	public Transaction(int transactionAmmount, String transactioComment) {
		super();
		this.transactionAmmount = transactionAmmount;
		this.transactioComment = transactioComment;
	}

	public int getTransactionAmmount() {
		return transactionAmmount;
	}

	public void setTransactionAmmount(int transactionAmmount) {
		this.transactionAmmount = transactionAmmount;
	}

	public String getTransactioComment() {
		return transactioComment;
	}

	public void setTransactioComment(String transactioComment) {
		this.transactioComment = transactioComment;
	}
	
	

}
