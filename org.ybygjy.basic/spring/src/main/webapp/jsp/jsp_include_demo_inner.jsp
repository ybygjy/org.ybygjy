<%@ page language="java" pageEncoding="UTF-8"%>
<%!
	int constParam = 202;
%>
<h1>jsp_include_demo_inner=><%=constParam %></h1>
<!-- include指令是静态资源内容合并成最终一个Servlet，jsp:include会在运行时将运行结果合并 -->
<%--@ include file="/jsp/jsp_include_demo.jsp"--%>
<jsp:include page="/jsp/jsp_include_demo.jsp" flush="true"></jsp:include>