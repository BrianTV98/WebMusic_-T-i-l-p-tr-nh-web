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
	#valid{
		color: red;
		size: 5px;
	}
</style>
</head>
<body>
	<section class="login-block">
		<div class="container">
			<div class="row">
				<div class="col-md-4 login-sec">
					<h2 class="text-center">Đăng Nhập</h2>
					<form:form action="index.htm" modelAttribute="user"
						class="login-form">
						<div class="form-group">
							<label for="exampleInputEmail1" class="text-uppercase">Tài
								Khoản</label>
							<form:input path="userName" class="form-control" />
						</div>

						<div class="form-group">
							<label for="exampleInputPassword1" class="text-uppercase">Mật
								khẩu</label>
							<form:input path="passWord" type="password" class="form-control" />
						</div>
						<div id="valid">${message}</div>
						<div class="form-check">
							<label class="form-check-label"> <input type="checkbox"
								class="form-check-input"> <small>Ghi nhớ tài
									khoản của tôi</small>
							</label>
							<button type="submit" class="btn btn-login float-right">Đăng
								nhập</button>
						</div>
						
						<div>
							<label class="textview-register"><a href="register.htm">Bạn chưa có tài khoản! Hãy đăng kí.</a></label>
						</div>
						<div>
							<label class="textview-register"><a href="/WebNgheNhac/login/forgotpassword.htm" style="padding-left: 50px;"><i>Quên mật khẩu!</i>.</a></label>
						</div>
					</form:form>

				</div>
				<div class="col-md-8 banner-sec">
					<div id="carouselExampleIndicators" class="carousel slide"
						data-ride="carousel">
						<div class="carousel-inner" role="listbox">
							<a href="/WebNgheNhac/main/home.htm">
								<div class="carousel-item active">
									<img src='<c:url value="/resources/image/bg_login.jpg"/>' alt="" />
								</div>
							</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
</body>
</html>