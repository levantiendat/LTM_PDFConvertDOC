-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th12 09, 2023 lúc 09:36 PM
-- Phiên bản máy phục vụ: 10.4.28-MariaDB
-- Phiên bản PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `pdftodoc`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `account`
--

CREATE TABLE `account` (
  `Username` varchar(30) NOT NULL,
  `Password` varchar(30) NOT NULL,
  `Email` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `account`
--

INSERT INTO `account` (`Username`, `Password`, `Email`) VALUES
('admin', 'admin', 'admin@gmail.vccorp.com'),
('convert0', 'convert0', 'convert0@gmail.com'),
('User1', 'User1', 'User1@convert.com');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `converthistory`
--

CREATE TABLE `converthistory` (
  `ServerPDFFile` varchar(100) NOT NULL,
  `Username` varchar(30) NOT NULL,
  `PDFFile` varchar(100) NOT NULL,
  `ServerDOCFile` varchar(100) NOT NULL,
  `DocFile` varchar(100) NOT NULL,
  `State` int(11) NOT NULL,
  `RequestTime` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `converthistory`
--

INSERT INTO `converthistory` (`ServerPDFFile`, `Username`, `PDFFile`, `ServerDOCFile`, `DocFile`, `State`, `RequestTime`) VALUES
('000_0001_yPCzvgBgiBKi88gy5dJh.pdf', 'convert0', '000_0001.pdf', '000_0001_yPCzvgBgiBKi88gy5dJh.docx', '000_0001.docx', 4, '2023-12-10 02:55:52'),
('102210247_LeVanTienDat_245JaCqVeNU5nYG5p5by.pdf', 'convert0', '102210247_LeVanTienDat.pdf', '102210247_LeVanTienDat_245JaCqVeNU5nYG5p5by.docx', '102210247_LeVanTienDat.docx', 4, '2023-12-10 02:55:48'),
('102210247_LeVanTienDat_R6NKHaQMq2c9R6WFh4bP.pdf', 'convert0', '102210247_LeVanTienDat.pdf', '102210247_LeVanTienDat_R6NKHaQMq2c9R6WFh4bP.docx', '102210247_LeVanTienDat.docx', 4, '2023-12-10 02:55:40'),
('18082023_yLtdNqSl4FGdf4vPEOGt.pdf', 'convert0', '18082023.pdf', '18082023_yLtdNqSl4FGdf4vPEOGt.docx', '18082023.docx', 4, '2023-12-10 03:26:30'),
('21Nh13_KXEIvyjmklIvWnSEMDBM.pdf', 'convert0', '21Nh13.pdf', '21Nh13_KXEIvyjmklIvWnSEMDBM.docx', '21Nh13.docx', 4, '2023-12-10 02:55:37'),
('2C23TCQ_00031469_dpuFJUrDNOx3cOFWbgaq.pdf', 'admin', '2C23TCQ_00031469.pdf', '2C23TCQ_00031469_dpuFJUrDNOx3cOFWbgaq.docx', '2C23TCQ_00031469.docx', 4, '2023-12-10 03:33:51'),
('AVĐV_ITF_K23_zALQc6QOvR80OacIxLam.pdf', 'convert0', 'AVĐV_ITF_K23.pdf', 'AVĐV_ITF_K23_zALQc6QOvR80OacIxLam.docx', 'AVĐV_ITF_K23.docx', 4, '2023-12-10 00:04:25'),
('LTM_FINAL_D1-123 (1)_EqgyfjGz2FqeBQe0B8we.pdf', 'convert0', 'LTM_FINAL_D1-123 (1).pdf', 'LTM_FINAL_D1-123 (1)_EqgyfjGz2FqeBQe0B8we.docx', 'LTM_FINAL_D1-123 (1).docx', 4, '2023-12-10 02:55:44');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`Username`);

--
-- Chỉ mục cho bảng `converthistory`
--
ALTER TABLE `converthistory`
  ADD PRIMARY KEY (`ServerPDFFile`),
  ADD KEY `Username` (`Username`);

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `converthistory`
--
ALTER TABLE `converthistory`
  ADD CONSTRAINT `Username` FOREIGN KEY (`Username`) REFERENCES `account` (`Username`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
