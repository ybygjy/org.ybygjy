<%@page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype html>
<html class="no-js">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="description" content="">
  <meta name="keywords" content="">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Amaze UI Examples</title>

  <!-- Set render engine for 360 browser -->
  <meta name="renderer" content="webkit">

  <!-- No Baidu Siteapp-->
  <meta http-equiv="Cache-Control" content="no-siteapp"/>

  <link rel="icon" type="image/png" href="amazeui/assets/i/favicon.png">

  <!-- Add to homescreen for Chrome on Android -->
  <meta name="mobile-web-app-capable" content="yes">
  <link rel="icon" sizes="192x192" href="amazeui/assets/i/app-icon72x72@2x.png">

  <!-- Add to homescreen for Safari on iOS -->
  <meta name="apple-mobile-web-app-capable" content="yes">
  <meta name="apple-mobile-web-app-status-bar-style" content="black">
  <meta name="apple-mobile-web-app-title" content="Amaze UI"/>
  <link rel="apple-touch-icon-precomposed" href="amazeui/assets/i/app-icon72x72@2x.png">

  <!-- Tile icon for Win8 (144x144 + tile color) -->
  <meta name="msapplication-TileImage" content="amazeui/assets/i/app-icon72x72@2x.png">
  <meta name="msapplication-TileColor" content="#0e90d2">

  <!-- SEO: If your mobile URL is different from the desktop URL, add a canonical link to the desktop page https://developers.google.com/webmasters/smartphone-sites/feature-phones -->
  <!--
  <link rel="canonical" href="http://www.example.com/">
  -->

  <link rel="stylesheet" href="amazeui/assets/css/amazeui.min.css">
  <link rel="stylesheet" href="amazeui/assets/css/app.css">
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
    .padding-0 {
      padding: 0px;
    }
  </style>
</head>
<body>
<!--[if lte IE 9]>
<p class="browsehappy">你正在使用<strong>过时</strong>的浏览器，Amaze UI 暂不支持。 请 <a
  href="http://browsehappy.com/" target="_blank">升级浏览器</a>
  以获得更好的体验！</p>
<![endif]-->

<!-- 页面内容 开发时删除 -->
<div class="header">
  <div class="am-g">
    <h1>文件上传</h1>
    <p>文件上传</p>
  </div>
</div>
<div class="am-u-sm-12">
	<form:form action="/spring/upload" enctype="multipart/form-data" method="post" cssClass="am-form" modelAttribute="uploadItem">
	    <div class="am-g">
	      <div class="am-u-sm-12">
	        <table class="am-table am-table-bd am-table-striped admin-content-table" id="table_fileupload">
	          <thead>
	          <tr>
	            <th>文件ID</th><th>文件名</th><th>文件路径</th><th>选择</th><th>管理</th>
	          </tr>
	          </thead>
	          <tbody>
		          <tr>
		          	<td>1</td>
		          	<td><input type="text" name="fileName[0]" class="padding-0"></td>
		          	<td><input type="file" name="fileData[0]" class="padding-0"></td>
		          	<td>&nbsp;</td>
		          	<td>&nbsp;</td>
		          </tr>
	          </tbody>
	          <tfoot>
	          	<tr>
	          		<td>
		            	<button type="button" class="am-btn am-btn-default am-btn-xs" id="btn_addfile">添加</button>
		            	&nbsp;
		            	<button type="button" class="am-btn am-btn-default am-btn-xs" id="btn_delfile">删除</button>
	          		</td>
	          	</tr>
	          </tfoot>
	        </table>
	      </div>
	    </div>
		<div class="am-cf">
			<input type="submit" value="提交">
		</div>
	</form:form>
</div>

<footer class="am-margin-top">
  <hr/>
  <p class="am-text-center">
    <small>by The AmazeUI Team.</small>
  </p>
</footer>
<!-- 以上页面内容 开发时删除 -->

<!--[if lt IE 9]>
<script src="http://libs.baidu.com/jquery/1.11.1/jquery.min.js"></script>
<script src="http://cdn.staticfile.org/modernizr/2.8.3/modernizr.js"></script>
<script src="amazeui/assets/js/amazeui.ie8polyfill.min.js"></script>
<![endif]-->

<!--[if (gte IE 9)|!(IE)]><!-->
<script src="amazeui/assets/js/jquery.min.js"></script>
<!--<![endif]-->
<script src="amazeui/assets/js/amazeui.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$('#btn_addfile').click(function(){
			var fileIndex = $('#table_fileupload tbody tr').length;
			$('#table_fileupload tbody').append("<tr><td>" + (fileIndex + 1) + "</td>" + 
			"<td><input type=\"text\" name=\"fileName[" + fileIndex + "]\" class=\"padding-0\"></td>" + 
			"<td><input type=\"file\" name=\"fileData[" + fileIndex + "]\" class=\"padding-0\"></td>" +
			"<td>&nbsp;</td><td>&nbsp;</td>" +
			"</tr>");
		});
		$('#btn_delfile').click(function(){
			$('#table_fileupload tbody tr:last').remove();
		});
	});
</script>
</body>
</html>
