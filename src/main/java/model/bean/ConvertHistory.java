package model.bean;

import java.sql.Timestamp;

public class ConvertHistory {
	private String ServerPDFFile;
	private String Username;
	private String PDFFile;
	private String ServerDOCFile;
	private String DOCFile;
	private int state;
	private Timestamp date;
	public ConvertHistory() {
		super();
	}
	public ConvertHistory(String serverPDFFile, String username, String pDFFile, String serverDOCFile, String dOCFile,
			int state, Timestamp date) {
		super();
		ServerPDFFile = serverPDFFile;
		Username = username;
		PDFFile = pDFFile;
		ServerDOCFile = serverDOCFile;
		DOCFile = dOCFile;
		this.state = state;
		this.date = date;
	}
	public String getServerPDFFile() {
		return ServerPDFFile;
	}
	public void setServerPDFFile(String serverPDFFile) {
		ServerPDFFile = serverPDFFile;
	}
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public String getPDFFile() {
		return PDFFile;
	}
	public void setPDFFile(String pDFFile) {
		PDFFile = pDFFile;
	}
	public String getServerDOCFile() {
		return ServerDOCFile;
	}
	public void setServerDOCFile(String serverDOCFile) {
		ServerDOCFile = serverDOCFile;
	}
	public String getDOCFile() {
		return DOCFile;
	}
	public void setDOCFile(String dOCFile) {
		DOCFile = dOCFile;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	
	
	
}
