package com.revature.repositories;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.revature.models.Employee;
import com.revature.models.User;
import com.revature.util.ConnectionFactory;

public class LoginDAO {
	
	private ConnectionFactory cf = ConnectionFactory.getConnectionFactory();
	
	public User login(String userName, String password) {
		
		Connection conn = this.cf.getConnection();

		try 
		{
			
			//String sql = "select * from \"users\" where user_name = '" + userName + "' and \"password\" = '" + password + "';";
			String sql = "select * from users;";
			
			Statement s = conn.createStatement();
			ResultSet res = s.executeQuery(sql);
			User u;
			while(res.next()) {
				if(res.getString("type").equals("employee")){
					u = new Employee();
					u.setName(res.getString("name"));
					u.setType(res.getString("type"));
					return u;
				}
				else {
					
				}
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
