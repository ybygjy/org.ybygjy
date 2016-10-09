<%@page import="java.util.Enumeration"%>
<%@page import="java.util.List" %>
<%@page import="org.ybygjy.spring.web.entity.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" errorPage="/errorpage/error.jsp"%>
<%@ include file="/common/header.jspf" %>
<%@ include file="/common/taglibs.jsp" %>
<%
	List<User> userList = (List<User>) request.getAttribute("userList");
	for (int i = 0; i < userList.size(); i++) {
	    User user = userList.get(i);
	    %>
		<div>
			<ul><%=user.getNo() %></ul>
			<ul><%=user.getName() %></ul>
			<ul><%=user.getAge() %></ul>
		</div>
	    <%
	    this.doWork(out, user.toString());
	}
%>
	<%!
		private void doWork(JspWriter jspWriter, String str) {
	    	try {
		    	jspWriter.write(str);
	    	} catch(Exception e) {
	    	    e.printStackTrace();
	    	}
		}
	%>
	<div>
		<a href="fireException">Fire Exception</a>
	</div>
	<div>
		<h4>EL表达式</h4>
		<div>
			<ul>${userList}</ul>
		</div>
	</div>
<%@ include file="/common/foot.jspf"%>