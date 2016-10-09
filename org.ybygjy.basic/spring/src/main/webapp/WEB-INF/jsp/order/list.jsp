<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/header.jspf" %>
<%@ include file="/common/taglibs.jsp" %>
<!-- jsp:include 指令要求资源必须是jsp 如果是其它类型则会原样输出 -->
<div align="center">
	<h1>OrderList</h1>
	<h3><a href="/spring/order/newOrder">New Order</a></h3>
	<table border="1">
		<thead>
			<tr>
				<th>主键</th>
				<th>订单编号</th>
				<th>orderAmount</th>
				<th>orderFlag</th>
				<th>buyerId</th>
				<th>salesId</th>
				<th>orderRemark</th>
				<th>sendTime</th>
				<th>orderMTime</th>
				<th>orderCTime</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="order" varStatus="status" items="${orderList }">
			<tr>
				<td>${status.index + 1 }_${order.id }</td>
				<td>${order.orderNo }</td>
				<td>${order.orderAmount }</td>
				<td>${order.orderFlag }</td>
				<td>${order.buyerId }</td>
				<td>${order.salesId }</td>
				<td>${order.orderRemark }</td>
				<td>${order.sendTime }</td>
				<td>${order.orderMtime }</td>
				<td>${order.orderCtime }</td>
				<td>
					<a href="/editOrder?order_id=${order.id }">Edit</a>
					<a href="/deleteOrder?order_id=${order.id }">Delete</a>
				</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<%@ include file="/common/footer.jspf"%>