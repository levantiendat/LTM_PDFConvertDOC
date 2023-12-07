package model.bo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.spire.pdf.FileFormat;
import com.spire.pdf.PdfDocument;

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
		for(ConvertHistory request: list) {
			ConvertDAO dao = new ConvertDAO();
			String fileName = request.getPDFFile();
			String giveFilePath = fullGivePath + File.separator + fileName;
			
			String saveFileName = fileName.replace(".pdf", ".docx");
			String saveFilePath = fullSavePath + File.separator + saveFileName;
			//ConvertDAO -> Update inProcess
			request.setDOCFile(saveFileName);
			request.setState(PROCESSING);
			dao.UpdatResult(request);
			try {
				Boolean result = convertPdfToDocx(giveFilePath, saveFilePath);
				if(result) {
					//ConvertDAO -> Update oke
					request.setState(CONVERT_SUCCESSFULLY);
					dao.UpdatResult(request);
				} else {
					//ConvertDAO -> Update failed
					request.setState(CONVERT_FAILED);
					dao.UpdatResult(request);
				}
			} catch(Exception e) {
				e.printStackTrace();
				//ConvertDAO -> Update failed
				request.setState(CONVERT_FAILED);
				dao.UpdatResult(request);
			}
			
		}
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
