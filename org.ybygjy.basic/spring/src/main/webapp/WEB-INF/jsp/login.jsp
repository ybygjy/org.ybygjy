<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head lang="en">
	  <meta charset="UTF-8">
	  <title>Login Page | Amaze UI Example</title>
	  <meta http-equiv="X-UA-Compatible" content="IE=edge">
	  <meta name="viewport" content="width=device-width, initial-scale=1">
	  <meta name="format-detection" content="telephone=no">
	  <meta name="renderer" content="webkit">
	  <meta http-equiv="Cache-Control" content="no-siteapp" />
	  <link rel="alternate icon" type="image/png" href="assets/i/favicon.png">
	  <link rel="stylesheet" href="amazeui/assets/css/amazeui.min.css"/>
	  <script type="text/javascript" src="amazeui/assets/js/jquery.min.js"></script>
	  <style>
	    .header {
	      text-align: center;
	    }
	    .header h1 {
	      font-size: 200%;
	      color: #333;
	      margin-top: 30px;
	    }
	    .header p {
	      font-size: 14px;
	    }
	  </style>
	</head>
	<body>
		<div class="header">
		  <div class="am-g">
		    <h1>订单管理</h1>
		    <p>订单创建、支付、发货、结算、统计</p>
		  </div>
		  <hr />
		</div>
		<div class="am-g">
		</div>
		<div class="am-g">
		  <div class="am-u-lg-6 am-u-md-8 am-u-sm-centered">
		    <form id="dataForm" method="post" class="am-form" action="/spring/login">
		      <label for="user_no">工号:</label>
		      <input type="text" id="user_no" value="88086" name="user_no">
		      <br>
		      <label for="email">邮箱:</label>
		      <input type="text" id="email" value="88086@163.com" name="user_email">
		      <br>
		      <label for="password">密码:</label>
		      <input type="password" id="password" value="123456" name="password">
		      <br>
		      <label for="remember-me">
		        <input id="remember-me" type="checkbox">
		        记住密码
		      </label>
		      <br />
		      <div class="am-cf">
		        <input id="formSubmitBtn" value="登 录" class="am-btn am-btn-primary am-btn-sm am-fl">
		        <input id="forgetPasswordBtn" value="忘记密码" class="am-btn am-btn-default am-btn-sm am-fr">
		      </div>
		      <input type="hidden" name="method" value="login">
		    </form>
		    <hr>
		    <p>© 2014 AllMobilize, Inc. Licensed under MIT license.</p>
		  </div>
		</div>
	</body>
	<script type="text/javascript">
		$(document).ready(function(e){
			$('#formSubmitBtn').bind('click',function(e){
				$(this).prop('disabled',true);
				$('#dataForm').submit();
			});
			$('#forgetPasswordBtn').bind('click',function(e){
				$(this).prop('disabled',true);
				alert("forgetPasswordBtn::" + e);
			});
			$(document).keypress(function(e){
				if (e.keyCode == '13') {
					$('#dataForm').submit();
				}
			});
		});
	</script>
</html>
