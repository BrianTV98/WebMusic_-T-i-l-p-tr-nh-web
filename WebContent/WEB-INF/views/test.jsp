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


</head>

<body>

	  <c:forEach var="music" items="${list}" >
		<div style="padding-left: 50px; padding-right: 50px">
		<div class="row align-items-start">
			<div>				 <img alt="" src="list.uriPhoto"
					style="width: 48px; height: 48px">
			 </div>
			
		<div class="col">
				<div>${music.tenBaiHat }</div>
				<div class="row align-items-start">
					<div class="col">Tác giả: ${music.tacGia }</div>
					<div class="col">Thể hiện: ${music.caSi}</div>
				</div>
			</div>
			
			<div class="col">
				<audio controls="controls"
				src="${music.uriAudio}"
				style="width: 400px; height: 48px; align-content: center;"></audio>
			</div>
		</div>
		
	</div>
	</c:forEach> 
</html>