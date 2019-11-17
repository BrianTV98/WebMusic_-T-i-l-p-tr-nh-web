<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Đăng Nhập</title>
<link href='<c:url value="/resources/css/bootstrap.min.css" />'
	rel="stylesheet" />
<link href='<c:url value="/resources/css/mycss.css" />' rel="stylesheet" />
<style type="text/css">
	#abc{
		color: red;
	}
</style>
</head>
<body>
	<section class="register-block">
		<div class="container-register" style="padding-left: 25px">
			<div class="row"> 
				<div class="login-sec">
					<h2 class="text-center">Đăng kí</h2>
					<form:form action="register.htm" modelAttribute="user" 
						class="login-form">
						
						<div class="form-group">
							<label for="exampleInputEmail1" class="text-uppercase">Họ và tên</label>
							<form:input path="fullName" class="form-control" />
							<form:errors path="fullName" id ="abc"/>
						</div>
						
						<div class="form-group">
							<label for="exampleInputEmail1" class="text-uppercase">Số điện thoại</label>
							<form:input path="sdt" class="form-control" />
							<form:errors path="sdt" id ="abc"/>
						</div>
						
						<div class="form-group">
							<label for="exampleInputEmail1" class="text-uppercase">Tài Khoản</label>
							<form:input path="userName" class="form-control" />
							<form:errors path="userName" id ="abc"/>
						</div>

						<div class="form-group">
							<label for="exampleInputPassword1" class="text-uppercase">Mật khẩu</label>
							<form:input path="passWord" type="password" class="form-control" />
							<form:errors path="passWord" id ="abc"/>
						</div>
						<div class="form-group">
							<label for="exampleInputPassword1" class="text-uppercase">Xác nhận mật khẩu</label>
							<form:input path="retrypass" type="password" class="form-control" />
							<form:errors path="retrypass" id ="abc"/>
						</div> 	
						<div class="form-check">					
							<button  type="submit" class="btn btn-login float-right" >Đăng kí</button>
						</div>
						
						<div>
							<label class="textview-register"><a href="index.htm">Bạn đã có tài khoản! Hãy đăng nhập.</a></label>
						</div>															
					</form:form>
						
			</div>
			</div>
		</div>
	</section> 
</body>
</html>