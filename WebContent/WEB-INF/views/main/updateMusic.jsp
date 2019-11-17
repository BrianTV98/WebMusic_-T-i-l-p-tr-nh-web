<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Update Music</title>
<link href='<c:url value="/resources/css/bootstrap.min.css" />'
	rel="stylesheet" />
<link href='<c:url value="/resources/css/mycss.css" />' rel="stylesheet" />
<base href="${pageContext.servletContext.contextPath }/">
<style type="text/css">
#default_padding{
	padding-top: 10px;
	padding-bottom: 10px;
}
#abc{
	color: red;
}
</style>
</head>
<body>
		<section class="register-block" style="padding-left: 200px; padding-right: 200px">
		<div class="container">
			<div class="row">
			</div>
			<div class="clo-8">					
				<div>
					${temp.idBaiHat}
					<div style="color: red;">${message} ${message2}</div>	
					<h2 class="text-center"; style="padding-top: 30px">UPLOAD MUSIC</h2>			
					 <form:form action="main/updateMusic/${idBaiHat}.htm" modelAttribute="music" enctype="multipart/form-data" class="login-form">
						<div class="form-group">
							<label for="exampleInputEmail1" class="text-uppercase">Tên Bài Hát</label>
							<form:input path="tenBaiHat" class="form-control" />
							<form:errors path="tenBaiHat" id="abc"/>
						</div>
						
						<div class="form-group">
							<label for="exampleInputEmail1" class="text-uppercase">Tác giả</label>
							<form:input path="tacGia" class="form-control" />
							<form:errors path="tacGia" id="abc"/>
						</div>
						
						<div class="form-group">
							<label for="exampleInputEmail1" class="text-uppercase">Thể Hiện</label>
							<form:input path="caSi" class="form-control" />
							<form:errors path="caSi" id="abc"/>						
						</div>
						
						<div class="form-group">
							<label for="exampleInputPassword1" class="text-uppercase">Album</label>
							<form:select path="idAlbum"  items="${albums}" itemLabel="tenAlbum" itemValue="idAlbum"/>
							<a href="main/uploadAlbum.htm" style="margin-left: 75px">Thêm Album </a>
						</div>
						<div class="form-group">
							<label for="exampleInputPassword1" class="text-uppercase">Thể loại</label>
							<form:select path="idTheLoai"  items="${theloai}" itemLabel="tenTheLoai" itemValue="idTheLoai"/>
						</div id="default_padding">	
								<div>Hình ảnh đại diện cho bài hát</div>
								<form:input type="file" path="filePhoto" />
								<form:errors path="filePhoto" id="abc"/>
									
						<div style="padding-bottom: 30px; padding-top: 20px; margin-left: 50px">
							<button >Update</button>
							<a style="size: 16px; padding-left: 100px" href="main/MyMusic.htm"><i><<--Quay trở lại nhạc của tôi</i></a>
						</div>						
					</form:form>
				</div>
			</div> 
			</div>
	</section> 
</body>
</html>