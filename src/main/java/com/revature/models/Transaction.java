package com.revature.models;

public class Transaction {
	
	private String transactionComment;
	
	public Transaction(String transactioComment) {
		super();
		this.transactionComment = transactioComment;
	}


	public String getTransactionComment() {
		return transactionComment;
	}

	public void setTransactioComment(String transactioComment) {
		this.transactionComment = transactioComment;
	}
	
	

}
