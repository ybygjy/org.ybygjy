<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/header.jspf"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div align="center">
	<h1>New/Edit Order</h1>
	<form:form action="/spring/order/saveOrder" method="post" modelAttribute="order">
		<table>
			<form:hidden path="id"/>
			<tr>
				<td>OrderNo:</td>
				<td><form:input path="orderNo"/></td>
			</tr>
			<tr>
				<td>OrderAmount:</td>
				<td>
					<form:input path="orderAmount"/>
				</td>
			</tr>
			<tr>
				<td>orderFlag</td>
				<td>
					<form:input path="orderFlag"/>
				</td>
			</tr>
			<tr>
				<td>buyerId</td>
				<td>
					<form:input path="buyerId"/>
				</td>
			</tr>
			<tr>
				<td>salesId</td>
				<td>
					<form:input path="salesId"/>
				</td>
			</tr>
			<tr>
				<td>sendTime</td>
				<td>
					<form:input path="sendTime"/>
				</td>
			</tr>
			<tr>
				<td>orderRemark</td>
				<td>
					<form:textarea path="orderRemark"/>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="Save">
				</td>
			</tr>
		</table>
	</form:form>
</div>
<%@ include file="/common/footer.jspf"%>