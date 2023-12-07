package controller;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import model.bo.ConvertBO;
import model.bo.UploadBO;

/**
 * Servlet implementation class ConvertServlet
 */
@WebServlet("/ConvertServlet")
public class ConvertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String GIVE_DIRECTORY = "ConvertFile/PDFFile";
	public static final String SAVE_DIRECTORY = "ConvertFile/DOCFile";   

    public ConvertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		try {
        	ConvertBO bo = new ConvertBO();
            String appPath = request.getServletContext().getRealPath("");
            HttpSession session = request.getSession();
    		String username = (String) session.getAttribute("username");

            String fullSavePath = bo.getFullSavePath(appPath);
            String fullGivePath = bo.getFullGivePath(appPath);

            File fileSaveDir = new File(fullSavePath);
            if (!fileSaveDir.exists()) {
                fileSaveDir.mkdirs(); 
            }
            bo.ProcessConvert(fullSavePath, fullGivePath);
            
            


            // Xử lý thành công.
            response.sendRedirect("inputFile.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("Login.jsp");
        }
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
