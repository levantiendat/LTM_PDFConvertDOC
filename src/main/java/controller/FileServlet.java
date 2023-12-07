package controller;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import model.bo.UploadBO;


@WebServlet("/FileServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
                maxFileSize = 1024 * 1024 * 10, // 10MB
                maxRequestSize = 1024 * 1024 * 50) // 50MB
public class FileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public static final String SAVE_DIRECTORY = "ConvertFile/PDFFile";

    public FileServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
        	UploadBO bo = new UploadBO();
            String appPath = request.getServletContext().getRealPath("");
            HttpSession session = request.getSession();
    		String username = (String) session.getAttribute("username");

            String fullSavePath = bo.getFullSavePath(appPath);


            File fileSaveDir = new File(fullSavePath);
            if (!fileSaveDir.exists()) {
                fileSaveDir.mkdirs(); 
            }

            
            Part pdfPart = request.getPart("pdfFile");
            bo.saveUploadFile(pdfPart, fullSavePath, username);


            // Upload thành công.
            response.sendRedirect("inputFile.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("Login.jsp");
        }
    }

    

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}


