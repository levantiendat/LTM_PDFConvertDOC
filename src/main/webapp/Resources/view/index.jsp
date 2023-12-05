<%@ page pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="../Css/style.css">
    <title>Document</title>
</head>

<body>
    <div class="container-fluid">
        <nav class="navbar navbar-expand-lg bg-light navbar-light">
            <div class="logo__nav">
                <a href="">
                    <span class="navbar-brand">
                        <img src="../image/logo.jpg" class="img-fluid" alt="Avatar_logo" width="100" height="100">
                    </span>
                </a>
                <a href="" class="navbar-brand" style="font-size:22px; margin-left: 22px; font-weight: bold;">PDF
                    Converting
                </a>
            </div>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav" style="font-size:18px;">
                    <li class="nav-item">
                        <a class="nav-link" href="#">Lịch sử chuyển đổi</a>
                    </li>
                </ul>
            </div>
            <div class="navbar navbar-expand-lg bg-light navbar-light">
                <ul class="navbar-nav" style="font-size:18px;">
                    <li class="nav-item">
                        <button type="button" class="btn btn-success">Đăng nhập</button>
                    </li>
                    <li class="nav-item" style="display: flex;">
                        <button class="btn btn-primary" type="submit">Đăng kí</button>
                    </li>
                </ul>
            </div>
        </nav>
        <h1 class="mt-5 text-center">Chuyển <kbd>PDF</kbd> sang Word</h1>
        <div class="row">
            <div class="col col-sm-2"></div>
            <div class="col col-sm-8 mt-5"
                style="height: 300px; border-radius:10px; padding: 20px; background-color: #3d99f5;">
                <div class="Wrapper" style="height:100%;">
                    <div class="Content">
                        <div class="Content_image">
                            <img src="../image/pdf.png" class="img-fluid mt-3" alt="pdf_logo" width="50" height="30">
                        </div>
                        <div class="Content_select_file">
    						<div class="dropdown">
        						<form id="uploadForm" action="/ConvertFile/ConvertFileServlet" method="post" enctype="multipart/form-data">
            					<!-- Đặt id cho input để dễ quản lý từ JavaScript -->
            						<input type="file" name="pdfFile" id="fileInput" style="display: none;">
        						</form>

        						<!-- Sử dụng một thẻ a để kích hoạt input file khi được nhấn -->
        						<a class="btn btn-light dropdown-toggle dropdown_button_file" href="javascript:void(0);" id="dropdownMenuButton1" style="font-size: 18px; font-weight: bold;">
            					<i class="fa-solid fa-file-circle-plus" style="font-size:20px;"></i>
            					Chọn từ thiết bị
        							</a>

        						<!-- Sử dụng sự kiện onclick để kích hoạt input file khi thẻ a được nhấn -->
        						<script>
            						document.getElementById('dropdownMenuButton1').onclick = function () {
                						document.getElementById('fileInput').click();
            						};
        						</script>
        
        						<!-- Thêm sự kiện onchange để tự động submit form khi file được chọn -->
        						<script>
            						document.getElementById('fileInput').onchange = function () {
                						document.getElementById('uploadForm').submit();
            						};
        						</script>
    						</div>
    							<div class="Content_select_file_footer">
        						<span class="text-light">Hoặc thả tệp vào đây</span>
    							</div>
							</div>
                    </div>
                </div>
                <div class="col col-sm-2"></div>
            </div>
        </div>
    </div>
    <script src="https://kit.fontawesome.com/960668e674.js" crossorigin="anonymous"></script>
</body>

</html>