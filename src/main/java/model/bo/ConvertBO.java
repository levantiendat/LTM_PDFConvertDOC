package model.bo;

import java.io.File;
import java.util.ArrayList;

import model.bean.ConvertHistory;
import model.dao.ConvertDAO;

public class ConvertBO {
	private final static int PROCESSING = 2;
	private final static int CONVERT_FAILED = 3;
	private final static int CONVERT_SUCCESSFULLY = 4;
	public static final String GIVE_DIRECTORY = "ConvertFile/PDFFile";
	public static final String SAVE_DIRECTORY = "ConvertFile/DOCFile";
	
	public String getFullSavePath(String appPath1) {
		String appPath = appPath1.split(".metadata")[0];

        String fullSavePath = appPath + File.separator + SAVE_DIRECTORY;
        
        return fullSavePath;
	}
	public String getFullGivePath(String appPath1) {
		String appPath = appPath1.split(".metadata")[0];

        String fullSavePath = appPath + File.separator + GIVE_DIRECTORY;
        
        return fullSavePath;
	}
	public ArrayList<ConvertHistory> getAllOKList(){
		ConvertDAO dao = new ConvertDAO();
		return dao.getAllOKList();
	}
	public void ProcessConvert(String fullSavePath, String fullGivePath) {
		ArrayList<ConvertHistory> list = getAllOKList();
		for(ConvertHistory history: list) {
			System.out.println(history.getID() + " "+ history.getUsername()+ " "+ history.getPDFFile()+" " +history.getDOCFile()+ history.getState()+" "+ history.getDate().toString());
		}
	}
}
