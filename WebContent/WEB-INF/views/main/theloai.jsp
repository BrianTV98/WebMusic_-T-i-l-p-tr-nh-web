<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="styles/bootstrap-4.1.2/bootstrap.min.css">
<link href="plugins/font-awesome-4.7.0/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">

<link rel="stylesheet" type="text/css"
	href="plugins/OwlCarousel2-2.3.4/owl.carousel.css">

<link rel="stylesheet" type="text/css"
	href="plugins/OwlCarousel2-2.3.4/owl.theme.default.css">
<link rel="stylesheet" type="text/css"
	href="plugins/OwlCarousel2-2.3.4/animate.css">
<link rel="stylesheet" type="text/css"
	href="resources/styles/main_styles.css">
<link rel="stylesheet" type="text/css"
	href="resources/styles/responsive.css">
<link href='<c:url value="/resources/css/bootstrap.min.css" />'
	rel="stylesheet" />
<link href='<c:url value="/resources/css/mycss.css" />' rel="stylesheet" />
<base href="${pageContext.servletContext.contextPath }/">
<style type="text/css">
#menu ul {
	background: #fff;
	list-style-type: none;
	text-align: center;
}

#menu li {
	color: #f1f1f1;
	display: inline-block;
	width: 120px;
	height: 40px;
	line-height: 40px;
	margin-left: -5px;
}

#menu a {
	text-decoration: none;
	color: #000;
	display: block;
}

#menu a:hover {
	background: #F1F1F1;
	color: #333;
}

body {
	background:
		url("resources/image/backgroup/7ww87G3-music-pictures-wallpaper.jpg");
}
#sizeImage{
	width: 150px;
	height: 150px;
	margin: 50px;
	color: white;
}
</style>
</head>
<body>
	<!-- menu -->
	<div id="menu">
		<ul>
			<li><a href="main/home.htm">Trang chủ</a></li>
			<li><a href="main/theloai.htm">Thể loại</a></li>
			<li><a href="main/album.htm">Album</a></li>
			<li><a href="main/MyMusic.htm">Cá nhân</a></li> 
			<%-- <li>		
				 <form class="form-inline my-2 my-lg-0" action="main/home.htm" method="post">
					<input class="form-control mr-sm-2" type="text" name="search" 
						style="width: 100px" placeholder="Search" aria-label="Search">
					<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
				</form>  
			</li> --%>
			<li></li>
			<li>
				<form class="form-inline my-2 my-lg-0" action="main/login.htm" method="post">
					<button class="btn btn-outline-success my-2 my-sm-0" type="submit">${login}</button>
				</form> 
			</li>
		</ul>

	</div>
	<!-- body -->
	${message}
	<div style="padding-left: 120px; padding-right: 120px; color: white;">
		
		<div class="row">
				<c:forEach var="theloai" items="${list}" >
					<div class="col">
						<div class="row" >
							<a href="main/theloai/${theloai.idTheLoai}.htm"><img alt="" src="${theloai.hinhTheLoai}" id="sizeImage"/></a>
							<div style="padding-left: 50px">${theloai.tenTheLoai}</div>
						</div>
					</div>
				</c:forEach>
		</div>
	</div>
</body>
</html>s