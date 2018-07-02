<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<% 
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>" />
<meta charset="utf-8" />
<!-- 浏览器标签图片 -->
<link rel="shortcut icon" href="console/js/topjui/images/favicon.ico" />
<!-- TopJUI框架样式 -->
<link type="text/css" href="console/js/topjui/css/topjui.core.min.css"
	rel="stylesheet">
<link type="text/css"
	href="console/js/topjui/themes/default/topjui.black.css"
	rel="stylesheet" id="dynamicTheme" />
<!-- FontAwesome字体图标 -->
<link type="text/css"
	href="console/js/static/plugins/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" />
<link type="text/css"
	href="console/js/static/plugins/layui/css/layui.css" rel="stylesheet" />
<!-- jQuery相关引用 -->
<script type="text/javascript"
	src="console/js/static/plugins/jquery/jquery.min.js"></script>
<!--  <script type="text/javascript" src="console/js/static/plugins/echarts/echarts.min.js"></script> -->

<script type="text/javascript" src="common/js/echarts.js"></script>
<script type="text/javascript"
	src="console/js/static/plugins/jquery/jquery.cookie.js"></script>
<!-- TopJUI框架配置 -->
<script type="text/javascript"
	src="console/js/static/public/js/topjui.config.js"></script>
<!-- TopJUI框架核心 -->
<script type="text/javascript"
	src="console/js/topjui/js/topjui.core.min.js"></script>
<!-- TopJUI中文支持 -->
<script type="text/javascript"
	src="console/js/topjui/js/locale/topjui.lang.zh_CN.js"></script>
<!-- layui框架js -->
<script type="text/javascript"
	src="console/js/static/plugins/layui/layui.js" charset="utf-8"></script>
<!-- 首页js -->
<script type="text/javascript"
	src="console/js/static/public/js/topjui.index.js" charset="utf-8"></script>
<script type="text/javascript">
    	var contextPath = "<%=basePath%>";
	$(function() {
		$.get("deviceSite/queryDeviceSite4ParameterStatusShow.do", function(result) {
			
			if(result.error){
				alert(result.error);
				return ;
			}
		
			var $main = $("#main");
			$main.append("<div id='title'>")
			for(var i = 0;i<result.deviceSites.length;i++){
		//	for(var i = 0;i<5;i++){
				$titleDiv = $("<div class='title'>");
				$titleDiv.append(result.deviceSites[i].name);
				$contentDiv = $("<div class='content'>");
				
				$main.append($titleDiv);
				$main.append($contentDiv);
				
				$picDiv = $("<div class='pic'>");
				
				$oeeDiv = $("<div class='oee'>");
				$rtyDiv = $("<div class='rty' style='padding:auto auto;'>");
				
				$rtyText = $("<div style='height:30% ;width:100%;font-size:0.5em;'>");
				$rtyText.text("良品率");
				$rtyDiv.append($rtyText);
				
				$rtyValue = $("<div style='height:70% ;width:100%;font-size:1em;'>");
				$rtyDiv.append($rtyValue);
				
				$parameterDiv = $("<div class='parameter'>");
				
				var $table = $("<table style='width:100%;'>");
				var $tr = $("<tr style='backgroun-color:#F00'>");
				$table.append($tr);
				
				var $parameterNameTd = $("<th>参数名</th>");
				var $parameterValueTd = $("<th>参数值</th>");
				$tr.append($parameterNameTd);
				$tr.append($parameterValueTd);
				//循环显示参数信息
				for(var j = 0;j<result.parameters[i].length;j++){
					var $contentTr = $("<tr>");
					var $contentNameTd = $("<td>");
					var $contentValueTd = $("<td>");
					
					$contentTr.append($contentNameTd);
					$contentTr.append($contentValueTd);
					
					$table.append($contentTr);
					$contentNameTd.append(result.parameters[i][j].parameter.name);
					$contentValueTd.append(result.parameters[i][j].trueValue);
				}
				
				$parameterDiv.append($table);
				
				$contentDiv.append($picDiv);
				$contentDiv.append($oeeDiv);
				$contentDiv.append($rtyDiv);
				$contentDiv.append($parameterDiv);
				$rtyValue.append((result.rtys[i]?result.rtys[i]:0) + "%");
				oee($oeeDiv.get(0),result.oees[i]?result.oees[i]:0);
				$picDiv.append("<img src='" + result.deviceSites[i].device.photo + "' style='height:100%;width:100%;'/>");
			}
		});
	});
	//生产单元
	function oee(divObj,value,title){
		var app = echarts.init(divObj);
		option = {
				title:{
					show:true,
					text:title,
					left:'center'
				},
			    tooltip : {
			        formatter: "{a} <br/>{b} : {c}%"
			    },
			    toolbox: {
			        feature: {
			            restore: {},
			            saveAsImage: {}
			        }
			    },
			    series: [
			        {
			            name: title,
			            type: 'gauge',
			            detail: {formatter:'{value}%'},
			            data: [{value: value, name: ''}]
			        }
			    ]
			};
		app.setOption(option);
	}
</script>
<style>
#main {
	height: 1080px;
	width: 1920px;
	background-color: #191970;
}

table tr td,table tr th{
	text-align:center;
	font-size:1.5em;
}

#title {
	width: 100%;
	height: 50px;
	line-height: 50px;
}

.title {
	background-color: red;
	width: 100%;
	height: 30px;
}

.content {
	background-color: blue;
	width: 100%;
	height: 300px;
}

.pic {
	float: left;
	height: 100%;
	width: 25%;
	background-color: green;
}

.oee {
	float: left;
	height: 100%;
	width: 25%;
	background-color: pink;
}

.rty {
	float: left;
	height: 100%;
	width: 25%;
	background-color: yellow;
	font-size:10em;
	text-align: center;
	padding:auto auto;
}

.parameter {
	float: left;
	height: 100%;
	width: 25%;
	background-color: gray;
}
</style>
</head>
<body>
	<div id="main">
		<!-- <div id="title">五金车间设备状态跟踪与监控</div>
		<div class="title"></div>
		<div class="content">
			<div class="pic"></div>
			<div class="oee"></div>
			<div class="rty"></div>
			<div class="parameter"></div>
		</div>
		<div class="title"></div>
		<div class="content">
			<div class="pic"></div>
			<div class="oee"></div>
			<div class="rty"></div>
			<div class="parameter"></div>
		</div>
		<div class="title"></div>
		<div class="content">
			<div class="pic"></div>
			<div class="oee"></div>
			<div class="rty"></div>
			<div class="parameter"></div>
		</div>
		<div class="title"></div>
		<div class="content">
			<div class="pic"></div>
			<div class="oee"></div>
			<div class="rty"></div>
			<div class="parameter"></div>
		</div> -->
	</div>
</body>
</html>