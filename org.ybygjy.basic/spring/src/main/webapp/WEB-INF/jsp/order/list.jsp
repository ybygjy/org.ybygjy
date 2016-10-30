<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/header.jspf" %>
<div class="admin-content-body">
	<div class="am-cf am-padding am-padding-bottom-0">
		<div class="am-fl am-cf">
			<strong class="am-text-primary am-text-lg">表格</strong>/<small>Table</small>
		</div>
	</div>
	<hr/>
	<div class="am-g">
		<div class="am-u-sm-12 am-u-md-6">
			<div class="am-btn-toolbar">
				<div class="am-btn-group am-btn-group-xs">
					<button type="button" class="am-btn am-btn-default" ><span class="am-icon-plus"></span><a href="/spring/order/newOrder"> 新增</a></button>
					<button type="button" class="am-btn am-btn-default" ><span class="am-icon-plus"></span> 删除</button>
					<button type="button" class="am-btn am-btn-default" ><span class="am-icon-plus"></span> 审核</button>
				</div>
			</div>
		</div>
		<div class="am-u-sm-12 am-u-md-3">
			<div class="am-form-group">
				<select data-am-selected="{btnSize:'sm'}">
					<option value="option1">One</option>
					<option value="option2">One</option>
					<option value="option3">One</option>
				</select>
			</div>
		</div>
		<div class="am-u-sm-12 am-u-md-3">
			<div class="am-input-group am-input-group-sm">
				<input type="text" class="am-form-field">
				<span class="am-input-group-btn">
					<button class="am-btn am-btn-default" type="button">搜索</button>
				</span>
			</div>
		</div>
	</div>
	<div class="am-g">
		<div class="am-u-sm-12">
			<form class="am-form">
				<table class="am-table am-table-striped am-table-hover table-main">
					<thead>
						<tr>
							<th class="table-check">
								<input type="checkbox"/>
							</th>
							<th class="table-id">订单编号</th>
							<th class="table-title">orderAmount</th>
							<th class="table-type">orderFlag</th>
							<th class="table-title">buyerId</th>
							<th class="table-title">salesId</th>
							<th class="table-title">orderRemark</th>
							<th class="table-date am-hide-sm-only">sendTime</th>
							<th class="table-date">orderMTime</th>
							<th class="table-date">orderCTime</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="order" varStatus="status" items="${orderList }">
						<tr>
							<td>
								<input type="checkbox" order_id="${order.id }">
							</td>
							<td>${order.orderNo }</td>
							<td>${order.orderAmount }</td>
							<td>${order.orderFlag }</td>
							<td>${order.buyerId }</td>
							<td>${order.salesId }</td>
							<td>${order.orderRemark }</td>
							<td><fmt:formatDate value="${order.sendTime }" pattern="yyyy-MM-dd"/></td>
							<td><fmt:formatDate value="${order.orderMtime }" pattern="yyyy-MM-dd"/></td>
							<td><fmt:formatDate value="${order.orderCtime }" pattern="yyyy-MM-dd"/></td>
							<td>
								<a href="/spring/order/editOrder?order_id=${order.id }">编辑</a>
								<a href="/spring/order/deleteOrder?order_id=${order.id }">删除</a>
							</td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
			</form>
		</div>
	</div>
</div>
<%@ include file="/common/footer.jspf"%>