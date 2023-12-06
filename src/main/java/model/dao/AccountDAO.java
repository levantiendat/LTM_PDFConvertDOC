package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import model.bean.Account;

public class AccountDAO {
	public Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url="jdbc:mysql://127.0.0.1:3306/pdftodoc";
			Connection con=DriverManager.getConnection(url,"root","");
			return con;
		} catch(Exception e) {
			return null;
		}
	}
	public Boolean addNewAccount(Account account) {
		try {
			Connection con = getConnection();
			if(con!=null) {
				Statement stmt =con.createStatement();
				String query = String.format("INSERT INTO `account` (`Username`, `Password`, `Email`) VALUES ('%s', '%s', '%s');", account.getUsername(), account.getPassword(),
						 account.getEmail());
	            stmt.executeUpdate(query);
	            stmt.close();
	            return true;
			} else {
				return false;
			}
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public Account getAccount(String username, String password) {
		
		try {
			Account account = new Account();
			Connection con = getConnection();
			if(con!=null) {
				Statement stmt =con.createStatement();
				String query=String.format("SELECT * FROM account WHERE Username = '%s' AND Password = '%s'",username, password);
		        ResultSet res=stmt.executeQuery(query);

		        if(res.next()){
		        	account.setUsername(res.getString("Username"));
		        	account.setPassword(res.getString("Password"));
		        	account.setEmail(res.getString("Email"));
		            

		            res.close();
		            stmt.close();
		            return account;
		        }
		        else{
		            res.close();
		            stmt.close();
		            return null;
		        }
			}else {
				return null;
			}
			
		} catch(Exception e) {
			return null;
		}
		
	}
}
