package model.bean;

import java.sql.Timestamp;

public class ConvertHistory {
	private int ID;
	private String Username;
	private String PDFFile;
	private String DOCFile;
	private int state;
	private Timestamp date;
	public ConvertHistory() {
		super();
	}
	public ConvertHistory(int iD, String username, String pDFFile, String dOCFile, int state, Timestamp date) {
		super();
		ID = iD;
		Username = username;
		PDFFile = pDFFile;
		DOCFile = dOCFile;
		this.state = state;
		this.date = date;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
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
