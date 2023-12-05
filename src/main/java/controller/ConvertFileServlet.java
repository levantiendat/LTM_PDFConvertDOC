package controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.spire.pdf.FileFormat;
import com.spire.pdf.PdfDocument;


import java.io.File;

import java.io.IOException;
import java.nio.file.Paths;

@WebServlet("/ConvertFileServlet")
@MultipartConfig
public class ConvertFileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ConvertFileServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Part filePart = request.getPart("pdfFile"); // "pdfFile" là name của input type=file trong form

            String uploadPath = getServletContext().getRealPath("") + File.separator + "uploads";
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            String pdfFilePath = uploadPath + File.separator + fileName;
            String docxFilePath = pdfFilePath.replace(".pdf", ".docx");
            filePart.write(pdfFilePath);

            convertPdfToDocx(pdfFilePath, docxFilePath);

            request.setAttribute("docxFilePath", docxFilePath);

            RequestDispatcher dispatcher = request.getRequestDispatcher("result.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            String destination = "/inputFile.jsp";
            RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
            rd.forward(request, response);
        }
    }

    private void convertPdfToDocx(String pdfFilePath, String docxFilePath) throws IOException {
        try {
            // Load the PDF document
            PdfDocument doc = new PdfDocument();
            doc.loadFromFile(pdfFilePath);
            
            doc.saveToFile(docxFilePath, FileFormat.DOCX);
            
            doc.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new IOException("Error converting PDF to DOCX.");
        }
    }
}