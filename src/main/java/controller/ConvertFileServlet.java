package controller;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import model.bo.ConvertBO;
import model.bo.UploadFileBO;


@WebServlet("/ConvertFileServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
			maxFileSize = 1024 * 1024 * 10, // 10MB
			maxRequestSize = 1024 * 1024 * 50) // 50MB
public class ConvertFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    private BlockingQueue<String> convertFileQuene;
    public static final String PDF_DIRECTORY = "ConvertFile/PDFFile";
    public Boolean isTakeAppPath = false;
    public ConvertFileServlet() {
        super();
        
        convertFileQuene = new ArrayBlockingQueue<>(1000);
        
        Thread convertThread = new Thread(new ConvertBO(convertFileQuene));
        
        
        convertThread.start();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			Part pdfPart = request.getPart("pdfFile");
			String appPath = request.getServletContext().getRealPath("");
			if(!isTakeAppPath) {
				convertFileQuene.put(appPath);
				isTakeAppPath = true;
			}
			UploadFileBO bo = new UploadFileBO();
			HttpSession session = request.getSession();
			String username = (String) session.getAttribute("username");
			String fullPDFPath = bo.getFullPDFPath(appPath);
			
			File filePDFDir = new File(fullPDFPath);
			if (!filePDFDir.exists()) {
            filePDFDir.mkdirs(); 
			}
			String ServerFileName = bo.saveUploadFile(pdfPart, fullPDFPath, username);
			if(ServerFileName.length()>0) {
				convertFileQuene.put(ServerFileName);
			}

    		String destination = "/inputFile.jsp";
			RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
			rd.forward(request, response);
    		//Chạy 2 luồng
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
