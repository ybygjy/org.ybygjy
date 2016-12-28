<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<h1>跳转</h1>
		<jsp:forward page="jsp_forward_demo_01.jsp">
			<jsp:param value="param001" name="forward_param1"/>
			<jsp:param value="param002" name="forward_param2"/>
		</jsp:forward>
	</body>
</html>