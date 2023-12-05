<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CONVERT PDF TO DOC FILE</title>
</head>
<body>
	<h2>PLEASE CHOOSE PDF FILE TO CONVERT</h2>
	<form method="post" action="ConvertFileServlet" enctype="multipart/form-data">
		<input type="file" name="pdfFile" value="">
		<input type="submit" name="ok" value="CONVERT">
	</form>
</body>
</html>
