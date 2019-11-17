<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<link href='<c:url value="/resources/css/bootstrap.min.css" />'
	rel="stylesheet" />
<link href='<c:url value="/resources/css/mycss.css" />' rel="stylesheet" />
<base href="${pageContext.servletContext.contextPath }/">
</head>
<body>
	<section class="register-block" style="padding-left: 200px; padding-right: 200px">
		<div class="container">
			<div class="row">
			</div>
			<div class="clo-8">					
				<div>
					<h2 class="text-center" style="padding: 10px">Upload</h2>
					<div style="size: 16; color: red;">${message}</div>	
					 <form:form action="main/uploadAlbum.htm" modelAttribute="albums" enctype="multipart/form-data" class="login-form">
						<div class="form-group">
							<label for="exampleInputEmail1" class="text-uppercase">Tên Album</label>
							<form:input path="tenAlbum" class="form-control" />
							<form:errors path="tenAlbum" id="abc"/>
						</div>
						
						<div class="form-group">
							<label for="exampleInputEmail1" class="text-uppercase">Tác giả</label>
							<form:input path="tenCasiAlbum" class="form-control" />
							<form:errors path="tenCasiAlbum" id="abc"/>
						</div>
						<div>
							<div>Hình Đại Diện Album</div>
							<form:input type="file" path="filephoto" />
							<form:errors path="filephoto" id="abc"/>
						</div>	
						<div style="padding-top: 20px; padding-bottom:  20px">
							<button>Upload</button>
							<a href="main/uploadMusic.htm" style="size: 8; margin-left: 50px"><i><--Trở về upload Music</i></a>
							<a href="main/home.htm" style="size: 8; margin-left: 50px"><i><--Trở về Trang Chủ</i></a>
						</div>     												
					</form:form>
				</div>
			</div> 
			</div>
	</section> 
</body>
</html>