package model.bo;

import java.io.File;

import javax.servlet.http.Part;

public class UploadBO {
	private final static int UPLOAD_OK = 1;
	private final static int UPLOAD_FAILED = 0;
	public static final String SAVE_DIRECTORY = "ConvertFile/PDFFile";
	
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
	public String getFullSavePath(String appPath1) {
		String appPath = appPath1.split(".metadata")[0];

        String fullSavePath = appPath + File.separator + SAVE_DIRECTORY;
        
        return fullSavePath;
	}
	public void saveUploadFile(Part part, String fullSavePath) {
		try {
			String fileName = extractFileName(part);

	        if (fileName != null && fileName.length() > 0) {
	            String filePath = fullSavePath + File.separator + fileName;
	            System.out.println("Write attachment to file: " + filePath);

	            // Ghi vào file.
	            part.write(filePath);
	        }
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
