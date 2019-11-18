<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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

	<div class="row">
		<div class="col"></div>
		<div class="">
			<h1 style="align-content: center;">Quên mật khẩu</h1>
		</div>
		<div class="col"></div>
	</div>
	
		<div class="row">
		<div class="col"></div>
		<div class="col">
			<form action="forgotpassword.htm" method="post">
				<p>
					<input class="form-control" id="inputPassword" name="username"
						placeholder="username">
				</p>
				<button class="btn btn-primary">Lấy lại mật khẩu</button>

			</form>
			${message}
			<div>
				<a href="index.htm">${navigation }</a>
			</div>
		</div>
		<div class="col"></div>
	</div>

</html>