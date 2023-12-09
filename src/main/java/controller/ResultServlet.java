package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.ConvertHistory;
import model.bo.ResultBO;
import model.bo.UploadFileBO;


@WebServlet("/ResultServlet")
public class ResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ResultServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		String isShow = request.getParameter("show");
		String isPDF = request.getParameter("PDF");
		String isDOC = request.getParameter("DOC");
				
		if("1".equals(isShow)) {
			ResultBO bo = new ResultBO();
			ArrayList<ConvertHistory> history = bo.getConvertHistoryByUser(username);
			request.setAttribute("list", history);
			String destination = "/HistoryList.jsp";
			RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
			rd.forward(request, response);
		} else if("1".equals(isPDF)) {
			String appPath = request.getServletContext().getRealPath("");
			UploadFileBO upBO = new UploadFileBO();
			String fullPDFPath = upBO.getFullPDFPath(appPath);
			String pdfFile = request.getParameter("PDFFile");
			String newName = request.getParameter("File");
			String filePath = fullPDFPath +"/"+ pdfFile;
			
			File downloadFile = new File(filePath);
	        FileInputStream fileInputStream = new FileInputStream(downloadFile);
	        
	     // Thiết lập các thuộc tính của response cho file PDF và đặt tên mới
	        response.setContentType("application/pdf");
	        response.setContentLength((int) downloadFile.length());
	        response.setHeader("Content-Disposition", "attachment; filename=\"" + newName + "\"");

	        // Copy dữ liệu từ file vào response
	        OutputStream outputStream = response.getOutputStream();
	        byte[] buffer = new byte[4096];
	        int bytesRead = -1;
	        while ((bytesRead = fileInputStream.read(buffer)) != -1) {
	            outputStream.write(buffer, 0, bytesRead);
	        }

	        // Đóng các luồng
	        fileInputStream.close();
	        outputStream.close();
			
		} else if("1".equals(isDOC)) {
			String appPath = request.getServletContext().getRealPath("");
			UploadFileBO upBO = new UploadFileBO();
			String fullDOCPath = upBO.getFullDOCPath(appPath);
			String docFile = request.getParameter("DOCFile");
			String newName = request.getParameter("File");
			String filePath = fullDOCPath+ "/" + docFile;
			
			File downloadFile = new File(filePath);
	        FileInputStream fileInputStream = new FileInputStream(downloadFile);
	        
	     // Thiết lập các thuộc tính của response cho file DOCX và đặt tên mới
	        response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
	        response.setContentLength((int) downloadFile.length());
	        response.setHeader("Content-Disposition", "attachment; filename=\"" + newName + "\"");

	        // Copy dữ liệu từ file vào response
	        OutputStream outputStream = response.getOutputStream();
	        byte[] buffer = new byte[4096];
	        int bytesRead = -1;
	        while ((bytesRead = fileInputStream.read(buffer)) != -1) {
	            outputStream.write(buffer, 0, bytesRead);
	        }

	        // Đóng các luồng
	        fileInputStream.close();
	        outputStream.close();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
