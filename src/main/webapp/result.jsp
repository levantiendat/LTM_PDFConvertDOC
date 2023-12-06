<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Result Page</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #e6f7ff; /* Màu xanh dương nhạt */
            text-align: center;
            margin: 0;
            padding: 0;
        }

        h2 {
            color: #333;
            margin-top: 50px;
        }

        p {
            color: #555;
            margin: 20px 0;
        }

        form {
            margin: 20px 0;
        }

        button {
            background-color: #4caf50;
            color: #fff;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        button:hover {
            background-color: #45a049;
        }
    </style>
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
