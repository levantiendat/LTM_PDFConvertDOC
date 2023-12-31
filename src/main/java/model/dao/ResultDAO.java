package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import model.bean.ConvertHistory;

public class ResultDAO {
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
	public ArrayList<ConvertHistory> getConvertHistoryByUser(String username){
		try {
			Connection con = getConnection();
			if(con!=null) {
				ArrayList<ConvertHistory> list = new ArrayList<ConvertHistory>();
				String query = String.format("Select * from converthistory where Username = '%s'", username);
				Statement stmt =con.createStatement();
				ResultSet res=stmt.executeQuery(query);
				while(res.next()) {
					ConvertHistory history = new ConvertHistory();
					history.setServerPDFFile(res.getString("ServerPDFFile"));
					history.setUsername(res.getString("Username"));
					history.setPDFFile(res.getString("PDFFile"));
					history.setServerDOCFile(res.getString("ServerDOCFile"));
					history.setDOCFile(res.getString("DOCFile"));
					history.setState(res.getInt("State"));
					history.setDate(res.getTimestamp("RequestTime"));
					list.add(history);
				}
				res.close();
				stmt.close();
				return list;
			} else {
				return null;
			}
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
