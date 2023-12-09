package model.bo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;

import javax.servlet.http.HttpServletRequest;

import com.spire.pdf.FileFormat;
import com.spire.pdf.PdfDocument;

import model.bean.ConvertHistory;
import model.dao.ConvertDAO;

public class ConvertBO implements Runnable {
	private final static int UPLOAD_FAILED = -1;
	private final static int UPLOAD_PROCESSING = 0;
	private final static int UPLOAD_OK = 1;
	private final static int PROCESSING = 2;
	private final static int CONVERT_FAILED = 3;
	private final static int CONVERT_SUCCESSFULLY = 4;
	public static final String PDF_DIRECTORY = "ConvertFile/PDFFile";
	public static final String DOC_DIRECTORY = "ConvertFile/DOCFile";

	private final BlockingQueue<String> convertFileQuene;
	
	public ConvertBO(BlockingQueue<String> convertFileQuene) {
		this.convertFileQuene = convertFileQuene;
	}
	@Override
	public void run() {
		try {
			ConvertDAO dao = new ConvertDAO();
			String appPath =  convertFileQuene.take();
			String fullDOCPath = getFullDOCPath(appPath);
	        String fullPDFPath = getFullPDFPath(appPath);
	        File fileSaveDir = new File(fullDOCPath);
            if (!fileSaveDir.exists()) {
                fileSaveDir.mkdirs(); 
            }
            while(true) {
            	String ServerPDFFile = convertFileQuene.take();
            	ConvertHistory history = dao.getHistory(ServerPDFFile) ;
            	if(history.getState() == UPLOAD_OK && history!=null) {
            		String ServerPDFPath = fullPDFPath + File.separator + ServerPDFFile;
        			String PDFFile = history.getPDFFile();
        			
        			String ServerDOCFile = ServerPDFFile.replace(".pdf", ".docx");
        			String ServerDOCPath = fullDOCPath + File.separator + ServerDOCFile;
        			String DOCFile = PDFFile.replace(".pdf", ".docx");
        			
        			//ConvertDAO -> Update inProcess
        			history.setServerDOCFile(ServerDOCFile);
        			history.setDOCFile(DOCFile);
        			history.setState(PROCESSING);
        			dao.UpdateResult(history);
        			
        			Boolean result = convertPdfToDocx(ServerPDFPath, ServerDOCPath);
        			try {
        				if(result) {
            				//ConvertDAO -> Update oke
            				history.setState(CONVERT_SUCCESSFULLY);
            				dao.UpdateResult(history);
            			} else {
            				//ConvertDAO -> Update failed
            				history.setState(CONVERT_FAILED);
            				dao.UpdateResult(history);
            			}
        			} catch(Exception e1) {
        				history.setState(CONVERT_FAILED);
        				dao.UpdateResult(history);
        			}
        			
            	}
            }
		} catch(Exception e) {
			e.printStackTrace();
		}
		
        
	}
	public String getFullDOCPath(String appPath1) {
		String appPath = appPath1.split(".metadata")[0];

        String fullSavePath = appPath + File.separator + DOC_DIRECTORY;
        
        return fullSavePath;
	}
	public String getFullPDFPath(String appPath1) {
		String appPath = appPath1.split(".metadata")[0];

        String fullSavePath = appPath + File.separator + PDF_DIRECTORY;
        
        return fullSavePath;
	}
	
	
	private Boolean convertPdfToDocx(String pdfFilePath, String docxFilePath) throws IOException {
        try {
            // Load the PDF document
            PdfDocument doc = new PdfDocument();
            doc.loadFromFile(pdfFilePath);
            
            doc.saveToFile(docxFilePath, FileFormat.DOCX);
            
            doc.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
