package com.revature.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Account;
import com.revature.models.Customer;
import com.revature.models.Transaction;
import com.revature.models.User;
import com.revature.util.ConnectionFactory;

public class EmployeeDAO {
	
	private ConnectionFactory cf = ConnectionFactory.getConnectionFactory();
	
	public void acceptUser(Customer c) {
		Connection conn = this.cf.getConnection();
		String sql = "update users set active_account=? where user_name=? and password=?;";
		try 
		{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setBoolean(1, true);
			ps.setString(2, c.getUserName());
			ps.setString(3, c.getPassword());
			
			int rowsAffected = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void rejectUser(Customer c) {
		Connection conn = this.cf.getConnection();
		String sql = "delete from users where user_name=? and password=?;";
		try 
		{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, c.getUserName());
			ps.setString(2, c.getPassword());
			
			int rowsAffected = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<User> approveOrRejectAccounts(){
		
		String sql = "select * from users";
		Connection conn = this.cf.getConnection();
		List<User> allUsers = new ArrayList<User>();
		try 
		{
			Statement s = conn.createStatement();
			ResultSet res = s.executeQuery(sql);
			while(res.next()) {
				Customer c = new Customer();
				c.setName(res.getString("name"));
				c.setUserName(res.getString("user_name"));
				c.setType(res.getString("type"));
				c.setActiveAccount(res.getBoolean("active_account"));
				c.setPassword(res.getString("password"));
				
				if(c.getType().equals("customer") && c.getActiveAccount() == false) {
					allUsers.add(c);
				}
			}
			return allUsers;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public List<Account> getAllCustomersAccounts(){
		
		String sql = "select * from account";
		Connection conn = this.cf.getConnection();
		List<Account> acc = new ArrayList<Account>();
		Statement s;
		try {
			s = conn.createStatement();
			ResultSet res = s.executeQuery(sql);
			
			while(res.next()) {
				Account a = new Account(res.getInt("account_number"), res.getInt("ammount"));
				acc.add(a);
			}
			return acc;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public List<Transaction> getAllTransactions() {
		String sql = "select * from transactions;";
		Connection conn = this.cf.getConnection();
		List<Transaction> acc = new ArrayList<Transaction>();
		Statement s;
		try {
			s = conn.createStatement();
			ResultSet res = s.executeQuery(sql);
			
			while(res.next()) {
				Transaction t = new Transaction(res.getInt("transaction_ammount"), res.getString("transaction_comment"));
				acc.add(t);
			}
			return acc;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
