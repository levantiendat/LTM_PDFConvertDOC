package model.bo;

import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.spire.pdf.FileFormat;
import com.spire.pdf.PdfDocument;

import model.bean.ConvertHistory;
import model.dao.ConvertDAO;
import model.dao.UploadDAO;

public class UploadFileBO{
	private final static int UPLOAD_FAILED = -1;
	private final static int UPLOAD_PROCESSING = 0;
	private final static int UPLOAD_OK = 1;
	private final static int PROCESSING = 2;
	private final static int CONVERT_FAILED = 3;
	private final static int CONVERT_SUCCESSFULLY = 4;
	public static final String PDF_DIRECTORY = "ConvertFile/PDFFile";
	public static final String DOC_DIRECTORY = "ConvertFile/DOCFile";
	
	
	
	
	
	
	public String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                String clientFileName = s.substring(s.indexOf("=") + 2, s.length() - 1);
                clientFileName = clientFileName.replace("\\", "/");
                int i = clientFileName.lastIndexOf('/');
                return clientFileName.substring(i + 1);
            }
        }
        return null;
    }
	
	//Lấy full path file PDF
	public String getFullPDFPath(String appPath1) {
		String appPath = appPath1.split(".metadata")[0];

        String fullSavePath = appPath + File.separator + PDF_DIRECTORY;
        
        return fullSavePath;
	}
	//Lấy full path file doc
	public String getFullDOCPath(String appPath1) {
		String appPath = appPath1.split(".metadata")[0];

        String fullSavePath = appPath + File.separator + DOC_DIRECTORY;
        
        return fullSavePath;
	}
	//Sinh tên file ngẫu nhiên
	public String generateRandomString() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(20);

        for (int i = 0; i < 20; i++) {
            int randomIndex = random.nextInt(characters.length());
            char randomChar = characters.charAt(randomIndex);
            sb.append(randomChar);
        }

        return sb.toString();
    }
	public String saveUploadFile(Part part, String fullSavePath, String username) {
		String filePath="";
		UploadDAO dao = new UploadDAO();
		String fileName = extractFileName(part);
		String ServerFileName = fileName.split(".pdf")[0]+"_"+generateRandomString()+".pdf";
	    if (fileName != null && fileName.length() > 0) {
	    	dao.addNewRequest(ServerFileName,username, fileName, UPLOAD_PROCESSING);
	        filePath = fullSavePath + File.separator + ServerFileName;
	        System.out.println("Write attachment to file: " + filePath);
	        try {
	            part.write(filePath);
	            dao.changeState(ServerFileName, UPLOAD_OK);
	            return ServerFileName;
	        } catch(Exception e) {
	            e.printStackTrace();
	            dao.changeState(ServerFileName, UPLOAD_FAILED);
	            return ServerFileName;
	        }
	             
	   }else {
		   return "";
	   }
	    
		
		
	}
	
}
