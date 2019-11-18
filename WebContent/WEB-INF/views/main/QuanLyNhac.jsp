<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" type="text/css"
	href='<c:url value="/resources/css/bootstrap.min.css"/>'>
<link href='<c:url value="/resources/css/mycss.css" />' rel="stylesheet" />
<link href="plugins/font-awesome-4.7.0/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href='<c:url value="/resources/js/bootstrap.min.js"/>'>
<title>Cá nhân</title>
<style type="text/css">
#menu li {
	color: #f1f1f1;
	display: inline-block;
	width: 120px;
	height: 40px;
	line-height: 40px;
	margin-left: -5px;
}
</style>
</head>
<body>

	<section class="login-block" >
		<div class="container">
		<div class="row justify-content-end">	
			<div class="col-sm-8">
				<%@include file="../base/navBar.jsp"%>
			</div>
		</div>
			<div class="row">
				<table class="table table-hover">
					<tr>
						<th>STT</th>
						<th>Tên bài hát</th>
						<th>Casi</th>
						<th>Album</th>
						<th>Thể loại</th>
						<th></th>
					</tr>
					
					<c:forEach var="l" items="${list}">
						<tr>
							<td>${l.stt}</td>
							<td>${l.tenBaiHat}</td>
							<td>${l.caSi}</td>
							<td>${l.tenAlbum }
							<td>${l.tenTheLoai }
							<td><a href="deleteMusic/${l.idBaiHat}.htm">Xóa</a></td>
							<td><a href="updateMusic/${l.idBaiHat}.htm">Chỉnh sửa</a></a></td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</section>


</body>
</html>