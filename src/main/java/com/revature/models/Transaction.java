package com.revature.models;

public class Transaction {
	
	private String transactioComment;
	
	public Transaction(String transactioComment) {
		super();
		this.transactioComment = transactioComment;
	}


	public String getTransactioComment() {
		return transactioComment;
	}

	public void setTransactioComment(String transactioComment) {
		this.transactioComment = transactioComment;
	}
	
	

}
