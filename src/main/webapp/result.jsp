<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Result Page</title>
</head>
<body>
    <h2>Conversion Result</h2>

    <% 
    // Lấy đường dẫn file DOCX từ thuộc tính đã đặt trong servlet
    String docxFilePath = (String)request.getAttribute("docxFilePath");
    %>

    <% if (docxFilePath != null && !docxFilePath.isEmpty()) { %>
        <p>File DOCX đã được tạo thành công. Bạn có thể tải về bằng cách nhấp vào nút bên dưới:</p>
        
        <form action="DownloadFileServlet" method="post">
            <input type="hidden" name="filePath" value="<%= docxFilePath %>">
            <button type="submit">Download DOCX</button>
        </form>
    <% } else { %>
        <p>Có lỗi xảy ra trong quá trình chuyển đổi.</p>
    <% } %>
</body>
</html>
