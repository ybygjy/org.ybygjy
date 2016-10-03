<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/header.jspf"%>
<div>
	<%
		String key = "sysUserList";
		Object obj = request.getAttribute(key);
		System.out.println(obj);
	%>
	<%=obj %>
</div>
<%@ include file="/common/footer.jspf"%>