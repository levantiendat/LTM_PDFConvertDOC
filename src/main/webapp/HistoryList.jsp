<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page language="java" import="model.bean.ConvertHistory" %>
<%@page language="java" import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>History List</title>
<script>
        function logout() {
            // Chuyển hướng đến trang AccountServlet với tham số logout=1
            window.location.href = 'AccountServlet?logout=1';
        }
        function history() {
            window.location.href = 'ResultServlet?show=1';
        }
        function convert(){
        	window.location.href = 'AccountServlet';
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

        h3 {
            color: #FFFFFF;
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

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            padding: 10px;
            text-align: left;
        }

        th {
            background-color: #333;
            color: #fff;
        }

        tr:nth-child(even) {
            background-color: #fff; /* Màu nền của các hàng chẵn */
        }

        tr:nth-child(odd) {
            background-color: #f2f2f2; /* Màu nền của các hàng lẻ */
        }

        tr:hover {
            background-color: #ddd; /* Màu nền khi di chuột qua hàng */
        }
    </style>
</head>
<body>
	<%
		
		String username="";
    	// Kiểm tra xem người dùng đã đăng nhập chưa
    	if (session != null && session.getAttribute("username") != null) {
        	// Lấy thông tin người dùng từ session
         username = (String) session.getAttribute("username");}
		
	%>
    <div>
        <button onClick="history()">Lịch sử chuyển đổi </button>
        <button onClick="convert()">Chuyển đổi file</button>
        <h3>Xin chào, <%=username %></h3>
        <button onclick="logout()">Đăng xuất</button>
    </div>
	<%
		ArrayList<ConvertHistory> list = (ArrayList<ConvertHistory>) request.getAttribute("list");
	%>
	<table border=1>
		<tr>
			<th>Thời gian</th>
			<th>File PDF</th>
			<th>File DOC</th>
			<th>Kết quả</th>
			<th></th>
			<th></th>
		</tr>
		<%for(int i = 0;i < list.size();i++){
			String result="";
			switch (list.get(i).getState()){
				case -1: result="Tải lên thất bại";break;
				case 0: result="Đang tải lên";break;
				case 1: result="Tải lên thành công";break;
				case 2: result="Đang chuyển đổi";break;
				case 3: result="Chuyển đổi thất bại";break;
				case 4: result = "Chuyển đổi thành công"; break;
			}
			%>
			<tr>
				<td><%=list.get(i).getDate().toString() %></td>
				<td><%=list.get(i).getPDFFile() %></td>
				<td><%=list.get(i).getDOCFile() %></td>
				<td><%=result %></td>
				<%if(list.get(i).getState()>=1) {%>
				<td><a href="ResultServlet?PDF=1&PDFFile=<%=list.get(i).getServerPDFFile() %>&File=<%=list.get(i).getPDFFile() %>">Download file PDF</a></td>
				<%} %>
				<%if(list.get(i).getState()==4) {%>
				<td><a href="ResultServlet?DOC=1&DOCFile=<%=list.get(i).getServerDOCFile() %>&File=<%=list.get(i).getDOCFile() %>">Download file DOC</a></td>
				<%} %>
			</tr>
		<%} %>
	</table>
</body>
</html>