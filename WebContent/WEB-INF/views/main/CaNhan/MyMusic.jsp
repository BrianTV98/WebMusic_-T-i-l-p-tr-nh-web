<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Nhạc của tôi</title>
<link href='<c:url value="/resources/css/bootstrap.min.css" />'
	rel="stylesheet" />
<link href='<c:url value="/resources/css/mycss.css" />' rel="stylesheet" />
<base href="${pageContext.servletContext.contextPath }/">
<style type="text/css">
body{
	background: url("resources/image/backgroup/7ww87G3-music-pictures-wallpaper.jpg");
}
</style>
</head>
<body>
	<div style="padding-left: 50px; padding-right: 50px; margin-top: 50px">
		<div class="row">
			<div class="col-sm"><a  href="main/home.htm">Trang chủ</a></div>
			<div class="col-sm">
      			<a style="size: 16px " href="main/MyMusic.htm">Nhạc của tôi</a>	
    		</div>
			<div class="col-sm">
      			<a style="size: 16px" href="main/QuanLyNhac.htm">Quản lý nhạc</a>	
    		</div>
			<div class="col-sm">
      			<a style="size: 16px">Yêu thích</a>
    		</div>
    		
			<div class="col-sm">
      			<a style="size: 16px" href="main/uploadMusic.htm">Upload</a>	
    		</div>
			
			<form class="form-inline my-2 my-lg-0" action="main/MyMusic.htm" method="post">
					<input class="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search" name="search">
				<button class="btn btn-outline-success " type="submit" style="margin-right: 65px">Search</button>
			</form>	
		</div>
		<c:forEach var="music" items="${list}" >
		<div style="padding-left: 50px; padding-right: 50px; margin-top: 30px">
		
		<div class="row align-items-start" >
		
			<div><img alt="" src="${music.uriPhoto}"
					style="width: 48px; height: 48px">
			 </div>
			 		
		<div class="col">
				<div style="color: white;">${music.tenBaiHat }</div>
				<div class="row align-items-start">
					<div class="col" style="color: white;">Tác giả: ${music.tacGia }</div>
					<div class="col" style="color: white;">Thể hiện: ${music.caSi}</div>
				</div>
			</div>
			
			<div class="col">
				<audio controls="controls"
				src="${music.uriAudio}"
				style="width: 400px; height: 30px;margin-top:15px; align-content: center;"></audio>
			</div>
		</div>
		
	</div>
	</c:forEach> 
	</div>
		
</body>
</html>