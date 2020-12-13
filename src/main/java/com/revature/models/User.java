package com.revature.models;

public class User {
	
	private String name;
	private String userName;
	private String type;
	private String password;
	
	public User() {
		
	}
	
	public User(String name, String userName, String password, String type) {
		this.name = name;
		this.type = type;
		this.userName = userName;
		this.password = password;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	
	

}
