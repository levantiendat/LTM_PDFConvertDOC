package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class UploadDAO {
	private final static int UPLOAD_OK = 1;
	private final static int UPLOAD_FAILED = 0;
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
	public void addNewRequest(String ServerPDFFile,String username, String pdfFilePath, int state) {
		try {
			Connection con = getConnection();
			if(con!=null) {
				Statement stmt =con.createStatement();
				
				Timestamp now = new Timestamp(System.currentTimeMillis());
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		        String formattedTimestamp = dateFormat.format(now);
		        String query = String.format("INSERT INTO `converthistory` (`ServerPDFFile`, `Username`, `PDFFile`, `ServerDOCFile`, `DocFile`, `State`, `RequestTime`) VALUES ('%s', '%s', '%s', '', '', '%d', '%s')", ServerPDFFile,username, pdfFilePath, state, formattedTimestamp );
		        stmt.executeUpdate(query);
		        stmt.close();
			}
			
		} catch (Exception e) {
			e.printStackTrace();;
		}
	}
	public void changeState(String ServerPDFFile, int State) {
		try {
			Connection con = getConnection();
			if(con!=null) {
				Statement stmt =con.createStatement();
				
				Timestamp now = new Timestamp(System.currentTimeMillis());
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		        String formattedTimestamp = dateFormat.format(now);
		        String query = String.format("UPDATE `converthistory` SET State = '%d' WHERE ServerPDFFile = '%s'",State, ServerPDFFile );
		        stmt.executeUpdate(query);
		        stmt.close();
			}
			
		} catch (Exception e) {
			e.printStackTrace();;
		}
	}
}
