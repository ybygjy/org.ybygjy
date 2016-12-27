<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/header.jspf"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div align="center">
	<h1>New/Edit Order</h1>
	<form:form action="/spring/order/saveOrder" method="post" modelAttribute="order" cssClass="am-form">
		<form:hidden path="id" id="orderId"/>
		<div class="am-g am-margin-top">
			<div class="am-u-sm-4 am-u-md-2 am-text-right">订单号:</div>
			<div class="am-u-sm-8 am-u-md-4">
				<form:input path="orderNo" id="orderNo" cssClass="am-input-sm"/>
			</div>
			<div class="am-hide-sm-only am-u-md-6 am-text-right">*必填不可重复</div>
		</div>
		<div class="am-g am-margin-top">
			<div class="am-u-sm-4 am-u-md-2 am-text-right">订单金额:</div>
			<div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
				<form:input path="orderAmount" id="orderAmount" cssClass="am-input-sm"/>
			</div>
		</div>
		<div class="am-g am-margin-top">
			<div class="am-u-sm-4 am-u-md-2 am-text-right">订单标识:</div>
			<div class="am-u-sm-8 am-u-md-4">
				<form:input path="orderFlag" id="orderFlag" cssClass="am-input-sm"/>
			</div>
			<div class="am-hide-sm-only am-u-md-6 am-text-right">必值</div>
		</div>
		<div class="am-g am-margin-top">
			<div class="am-u-sm-4 am-u-md-2 am-text-right">买家名称:</div>
			<div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
				<form:input path="buyerName" id="buyerName" cssClass="am-input-sm"/>
			</div>
		</div>
		<div class="am-g am-margin-top">
			<div class="am-u-sm-4 am-u-md-2 am-text-right">买家ID:</div>
			<div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
				<form:input path="buyerId" id="buyerId" cssClass="am-input-sm"/>
			</div>
		</div>
		<div class="am-g am-margin-top">
			<div class="am-u-sm-4 am-u-md-2 am-text-right">销售同学:</div>
			<div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
				<form:input path="salesId" id="salesId" cssClass="am-input-sm"/>
			</div>
		</div>
		<div class="am-g am-margin-top">
			<div class="am-u-sm-4 am-u-md-2 am-text-right">发货日期:</div>
			<div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
				<form:input path="sendTime" id="sendTime" cssClass="am-input-sm"/>
			</div>
		</div>
		<div class="am-g am-margin-top">
			<div class="am-u-sm-4 am-u-md-2 am-text-right">下单日期:</div>
			<div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
				<form:input path="orderCtime" id="orderCtime" cssClass="am-input-sm"/>
			</div>
		</div>
		<div class="am-g am-margin-top">
			<div class="am-u-sm-4 am-u-md-2 am-text-right">备注:</div>
			<div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
				<form:textarea path="orderRemark" id="orderRemark"/>
			</div>
		</div>
		<div class="am-g am-margin-top">
			<div class="am-u-sm-12 am-u-md-12 am-u-end col-end">
				<button class="am-btn am-btn-primary btn-submit">提交</button>
				<button type="reset" class="am-btn am-btn-primary">重置</button>
			</div>
		</div>
	</form:form>
</div>
<%@ include file="/common/footer.jspf"%>
<script type="text/javascript">
	function beforeViewRender() {
		$('#sendTime').attr('type','date');
		$('#orderCtime').attr('type', 'date');
		if ($('#orderId').val() == '0' ){
			$('#orderNo').attr('disabled',true);
		}
	}
	$(document).ready(function(){
		beforeViewRender();
		$('.btn-submit').click(function() {
			console.log('submit');
			$('.am-form').submit();
		});
	});
</script>