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
	
	public void acceptUser(Account a) {
		Connection conn = this.cf.getConnection();
		String sql = "update account set active_account=? where user_id=?;";
		try 
		{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setBoolean(1, true);
			ps.setInt(2, a.getUser_id());
			
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void rejectUser(Account a) {
		Connection conn = this.cf.getConnection();
		String sql = "delete from account where user_id?;";
		try 
		{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, a.getUser_id());

			
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Account> approveOrRejectAccounts(){
		
		String sql = "select * from users u natural join account a where u.user_id = a.user_id;";
		Connection conn = this.cf.getConnection();
		List<Account> allAccounts = new ArrayList<Account>();
		try 
		{
			Statement s = conn.createStatement();
			ResultSet res = s.executeQuery(sql);
			while(res.next()) {
				Account a = new Account();
				a.setAccountNumber(res.getInt("account_number"));
				a.setAccountName(res.getString("account_name"));
				a.setAmmount(res.getInt("ammount"));
				a.setUser_id(res.getInt("user_id"));
				if(res.getString("type").equals("customer") && res.getBoolean("active_account") == false) {
					allAccounts.add(a);
				}
			}
			return allAccounts;
			
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
				Account a = new Account();
				a.setAccountName(res.getString("account_name"));
				a.setAccountNumber(res.getInt("account_number"));
				a.setAmmount(res.getInt("ammount"));
				a.setActiveAccount(res.getBoolean("active_account"));
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
