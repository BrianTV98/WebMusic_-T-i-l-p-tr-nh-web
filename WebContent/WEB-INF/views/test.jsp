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
	${message}
	<form action="test.htm" method="post">
		<p><input name="from" placeholder="From"></p>
		<p><input name="to" placeholder="to"></p>
		<p><input name="subject" placeholder="Subject"></p>
		<p><input name="body" placeholder="Body"></p>
		<button>Send</button>
	</form>
</html>