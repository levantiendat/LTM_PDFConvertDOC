<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>PDF to DOC Converter</title>
    <script>
        function logout() {
            // Chuyển hướng đến trang AccountServlet với tham số logout=1
            window.location.href = 'AccountServlet?logout=1';
        }
        function checkLogin() {
            if (!<%= session.getAttribute("username") != null %>) {
                window.location.href = 'Login.jsp';
            }
        }
    </script>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f4f4;
            text-align: center;
            margin: 0;
            padding: 0;
        }

        h2 {
            color: #333;
            margin-top: 50px;
        }
		h3{
			color: #FFFFFF;
		}
        form {
            max-width: 400px;
            margin: 20px auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        input[type="file"] {
            width: 100%;
            padding: 10px;
            margin: 15px 0;
            box-sizing: border-box;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        input[type="submit"] {
            background-color: #4caf50;
            color: #fff;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #45a049;
        }

        div {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 10px;
            background-color: #333;
            color: #fff;
        }

        div button {
            background-color: transparent;
            color: #fff;
            padding: 10px 15px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
    </style>
</head>
<body onload="checkLogin()">
	<%
		
		String username="";
    	// Kiểm tra xem người dùng đã đăng nhập chưa
    	if (session != null && session.getAttribute("username") != null) {
        	// Lấy thông tin người dùng từ session
         username = (String) session.getAttribute("username");}
		
	%>
    <div>
        <button>Lịch sử chuyển đổi</button>
        <button>Chuyển đổi file</button>
        <h3>Xin chào, <%=username %></h3>
        <button onclick="logout()">Đăng xuất</button>
    </div>
    <h2>Choose a PDF file to convert to DOC</h2>
    <form method="post" action="FileServlet" enctype="multipart/form-data">
        <input type="file" name="pdfFile" accept=".pdf" required>
        <br>
        <input type="submit" name="ok" value="Convert">
    </form>
</body>
</html>
