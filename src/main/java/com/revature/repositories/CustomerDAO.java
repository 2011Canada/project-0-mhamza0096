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
import com.revature.models.Transfer;
import com.revature.models.User;
import com.revature.util.ConnectionFactory;

public class CustomerDAO {
	
	ConnectionFactory cf = ConnectionFactory.getConnectionFactory();
	
	public void updateBalance(int accountNumber, int withdrawAmmount) {
		Connection conn = cf.getConnection();
		String sql = "update account set ammount = ammount - ? where account_number = ?;";
		PreparedStatement ps;
		try 
		{
			ps = conn.prepareStatement(sql);
			ps.setInt(1, withdrawAmmount);
			ps.setInt(2, accountNumber);
			ps.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public int withdraw(int accountNumber){
		Connection conn = cf.getConnection();
		String sql = "select ammount from account where account_number = ?;";
		PreparedStatement ps;
		try {
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, accountNumber);
			ResultSet res = ps.executeQuery();
			
			while(res.next()) {
				int ammount = res.getInt("ammount");
				return ammount;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0;
	}
	
	public void deposit(int accountNumber, int ammount) {
		Connection conn = cf.getConnection();
		
		String sql = "update account set ammount = ammount + ? where account_number = ?";
		
		try {
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, ammount);
			ps.setInt(2, accountNumber);
			ps.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void applyForNewAccount(Account a, User u) {
		
		String sql = "insert into account (account_name, ammount, active_account, user_id)"
				+ "values (?, ?, ?, ?);";
		Connection conn = cf.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, a.getAccountName());
			ps.setInt(2, a.getAmmount());
			ps.setBoolean(3, a.isActiveAccount());
			ps.setInt(4, u.getUser_id());
			
			ps.execute();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
	}
	
	public List<Account> viewBalance(User u) {
		String sql = "select * from account where user_id = " + u.getUser_id();
		Connection conn = cf.getConnection();
		List<Account> accList = new ArrayList<Account>();
		
		try {
			Statement s =  conn.createStatement();
			ResultSet res = s.executeQuery(sql);
			
			while(res.next()) {
				Account a = new Account();
				a.setAccountName(res.getString("account_name"));
				a.setAmmount(res.getInt("ammount"));
				a.setAccountNumber(res.getInt("account_number"));
				a.setActiveAccount(res.getBoolean("active_account"));
				accList.add(a);
			}
			return accList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void createAccount(Customer c) {
		Connection conn = cf.getConnection();
		String sql = "insert into users (name, user_name, password, type, phone_number)"
				+ "values (?, ?, ?, ?, ?);";
		try 
		{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, c.getName());
			ps.setString(2, c.getUserName());
			ps.setString(3, c.getPassword());
			ps.setString(4, c.getType());
			ps.setLong(5, Long.parseLong(c.getPhoneNumber()));
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void transferMoney(Transfer t) {
		Connection conn = cf.getConnection();
		String sql =  "insert into money_transfer (receiver_account_number, ammount, sender_account_number)"
				+ " values (?, ?, ?);";
		try {
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, t.getRecieverAccount());
			ps.setInt(2, t.getAmmount());
			ps.setInt(3, t.getSenderAccount());
			ps.execute();
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public List<Account> checkRecievingMoney(User u) {
		String sql = "select * from account where user_id = " + u.getUser_id();
		Connection conn = cf.getConnection();
		List<Account> accList = new ArrayList<Account>();
		
		try {
			Statement s =  conn.createStatement();
			ResultSet res = s.executeQuery(sql);
			
			while(res.next()) {
				Account a = new Account();
				a.setAccountName(res.getString("account_name"));
				a.setAccountNumber(res.getInt("account_number"));
				a.setAmmount(res.getInt("ammount"));
				accList.add(a);
			}
			return accList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Transfer> getReceivingList(int accountNumber){
		String sql = "select * from money_transfer where receiver_account_number = " + accountNumber + ";";
		Connection conn = cf.getConnection();
		List<Transfer> transferList = new ArrayList<Transfer>();
		try {
			Statement s = conn.createStatement();
			ResultSet res = s.executeQuery(sql);
			
			while(res.next()) {
				Transfer t = new Transfer();
				t.setAmmount(res.getInt("ammount"));
				t.setSenderAccount(res.getInt("sender_account_number"));
				t.setRecieverAccount(res.getInt("receiver_account_number"));
				t.setTransfer_id(res.getInt("transfer_id"));
				transferList.add(t);
			}
			return transferList;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}

	public void accept(Transfer t) {
		Connection conn = cf.getConnection();
		String sql_1 = "update account set ammount = ammount - ? where account_number = ?";
		String sql_2 = "update account set ammount = ammount + ? where account_number = ?";
		String sql_3 = "delete from money_transfer where transfer_id = ?";
		
		try {
			
		
			
			PreparedStatement ps2 = conn.prepareStatement(sql_2);
			ps2.setInt(1, t.getAmmount());
			ps2.setInt(2, t.getRecieverAccount());
			ps2.execute();
			
			PreparedStatement ps = conn.prepareStatement(sql_1);
			ps.setInt(1, t.getAmmount());
			ps.setInt(2, t.getSenderAccount());
			ps.execute();
			
			
			PreparedStatement ps3 = conn.prepareStatement(sql_3);
			ps3.setInt(1, t.getTransfer_id());
			ps3.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public void reject(Transfer t) {
		
		Connection conn = cf.getConnection();
		String sql = "delete from money_transfer where transfer_id = ?";
		try {
			
			PreparedStatement ps3 = conn.prepareStatement(sql);
			ps3.setInt(1, t.getTransfer_id());
			ps3.execute();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void updateTransaction(String comment) {
		Connection conn = cf.getConnection();
		String sql = "insert into transactions (transaction_comment)"
				+ " values (?)";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, comment);
			ps.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}



}
