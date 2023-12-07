package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import model.bean.ConvertHistory;

public class ConvertDAO {
	private final static int UPLOAD_OK = 1;
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
	//Xuất dữ liệu những file upload thành công
	public ArrayList<ConvertHistory> getAllOKList(){
		try {
			Connection con = getConnection();
			if(con!=null) {
				ArrayList<ConvertHistory> list = new ArrayList<ConvertHistory>();
				String query = String.format("Select * from converthistory where State = '%d'", UPLOAD_OK);
				Statement stmt =con.createStatement();
				ResultSet res=stmt.executeQuery(query);
				while(res.next()) {
					ConvertHistory history = new ConvertHistory();
					history.setID(res.getInt("ID"));
					history.setUsername(res.getString("Username"));
					history.setPDFFile(res.getString("PDFFile"));
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
	public void UpdatResult(ConvertHistory history) {
		try {
			Connection con = getConnection();
			if(con!=null) {
				ArrayList<ConvertHistory> list = new ArrayList<ConvertHistory>();
				
				String query = String.format("UPDATE converthistory SET Username = '%s', PDFFile = '%s', DocFile = '%s', State = '%d', RequestTime = '%s' WHERE ID = %d", history.getUsername(), history.getPDFFile(), history.getDOCFile(), history.getState(), history.getDate().toString(), history.getID());
				
				Statement stmt =con.createStatement();
				stmt.executeUpdate(query);
				
				stmt.close();
				
			} 
		} catch(Exception e) {
			e.printStackTrace();
			
		}
	}
}
