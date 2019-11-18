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
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css">
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
			<li>
				<form action="main/home.htm" id="search" method="post">
					<div class="row">
						<div class="col">
							<input class="form-control" type="text" name="search"
								style="width: 220px" placeholder="Search" aria-label="Search" />
						</div>
					</div>
				</form>
			</li>
			<li>
				<button class="btn btn-sm btn-outline-success rounded-pill"
					style="position: relative; margin-bottom: 5px;" form="search"
					type="submit">Search</button>
			</li>
			<li>
				<form class="form-inline my-2 my-lg-0" action="main/login.htm"
					method="post">
					<button class="btn btn-outline-success my-2 my-sm-0" type="submit">${login}</button>
				</form>
			</li>
		</ul>

	</div>
	<!-- body -->

	<div style="padding-left: 150px">
		<div style="color: white; size: 16px;margin-left: 10px">
			<b>${message}</b>
		</div>
		<c:forEach var="music" items="${list}">
			<div
				style="padding-left: 50px; padding-right: 50px; margin-top: 30px">

				<div class="row align-items-start">

					<div>
						<img alt="" src="${music.uriPhoto}"
							style="width: 48px; height: 48px">
					</div>

					<div class="col">
						<div style="color: white;">${music.tenBaiHat }</div>
						<div class="row align-items-start">
							<div class="col" style="color: white;">Tác giả:
								${music.tacGia }</div>
							<div class="col" style="color: white;">Thể hiện:
								${music.caSi}</div>
						</div>
					</div>

					<div class="col">
						<button class="btn btn-info" style="margin-bottom: 20px;" value="${music.idBaiHat }"
							onclick="play(this);">
							<i class="fa fa-play" aria-hidden="true"></i>
						</button>
						<audio controls="seeking" id="audio-${music.idBaiHat }"
							src="${music.uriAudio}"
							style="width: 400px; height: 30px; margin-top: 15px; align-content: center; display: none;"></audio>
					</div>
				</div>

			</div>
		</c:forEach>
	</div>
	<script>
		let oldId = 0;
		function pause() {
			try{
				let sel = "audio-" + oldId;
				document.getElementById(sel).pause();
				document.getElementById(sel).style.display = "none";
			}catch(e){
				
			}
		}
		function play(button) {
			pause();
			let id = button.value;
			let sel = "audio-" + id;
			document.getElementById(sel).play();
			document.getElementById(sel).style.display = "unset";
			oldId = id;
		}
	</script>
</body>
</html>