<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<h4>JavaBean</h4>
		<div>
			<%
				String userNo = String.valueOf(Math.random());
			%>
			<jsp:useBean id="userBean" class="org.ybygjy.spring.web.entity.User" scope="request"></jsp:useBean>
			<jsp:setProperty property="name" name="userBean" value="UserName_00001"/>
			<jsp:setProperty property="no" name="userBean" value="<%=userNo %>"/>
			<jsp:setProperty property="age" name="userBean" value="15"/>
		</div>
		<div>
			<span><a href="jsp_bean_demo.jsp">GO</a></span>
		</div>
		<div>
			<h4>Bean Info</h4>
			<div>
				<ul>
					<li>${userBean.no }</li>
					<li>${userBean.name }</li>
					<li>${userBean.age }</li>
				</ul>
			</div>
		</div>
	</body>
</html>