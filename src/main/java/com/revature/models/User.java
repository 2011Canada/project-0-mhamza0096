package com.revature.models;

public class User {
	
	private int user_id;
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

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
	
	
	

}
