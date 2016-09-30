<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<%
			pageContext.setAttribute("name", "UserName", PageContext.REQUEST_SCOPE);
			pageContext.setAttribute("Date", new Date(), PageContext.REQUEST_SCOPE);
		%>
		<%
			pageContext.setAttribute("name", "UserName", PageContext.SESSION_SCOPE);
			pageContext.setAttribute("Date", "Date", PageContext.SESSION_SCOPE);
		%>
		<div>
			<ul>
				<li>${name }</li>
				<li>${Date }</li>
			</ul>
		</div>
		<jsp:forward page="pageContext03.jsp"></jsp:forward>
	</body>
</html>