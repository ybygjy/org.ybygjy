<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<jsp:useBean id="userBean" class="org.ybygjy.spring.web.entity.User"></jsp:useBean>
		<jsp:setProperty property="*" name="userBean"/>
		<div>
			<h4>UserBean</h4>
			<div>
				<ul>
					<li>${userBean.name }</li>
					<li>${userBean.no }</li>
				</ul>
			</div>
		</div>
	</body>
</html>