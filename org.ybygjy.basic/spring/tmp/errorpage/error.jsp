<%@page import="java.io.PrintStream"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>出错啦</title>
	</head>
	<body>
		<div style="width: 800px;height: 100%; background-color: #abcdef;">
			<p>出错啦!</p>
			<%=exception.toString()%>
			<%
				StackTraceElement[] steArr = exception.getStackTrace();
				for (int i = 0; i < steArr.length; i++) {
				    out.println(steArr[i]);
				    out.println("<br>");
				}
			%>
		</div>
	</body>
</html>