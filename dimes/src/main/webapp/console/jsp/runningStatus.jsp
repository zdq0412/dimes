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
<base href="<%=basePath%>"/>
<meta charset="utf-8"/>
 <!-- 浏览器标签图片 -->
    <link rel="shortcut icon" href="console/js/topjui/images/favicon.ico"/>
    <!-- TopJUI框架样式 -->
    <link type="text/css" href="console/js/topjui/css/topjui.core.min.css" rel="stylesheet">
    <link type="text/css" href="console/js/topjui/themes/default/topjui.black.css" rel="stylesheet" id="dynamicTheme"/>
    <!-- FontAwesome字体图标 -->
    <link type="text/css" href="console/js/static/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet"/>
      <link type="text/css" href="console/js/static/plugins/layui/css/layui.css" rel="stylesheet"/>
    <!-- jQuery相关引用 -->
    <script type="text/javascript" src="console/js/static/plugins/jquery/jquery.min.js"></script>
   <!--  <script type="text/javascript" src="console/js/static/plugins/echarts/echarts.min.js"></script> -->
   
    <script type="text/javascript" src="common/js/echarts.js"></script>
    <script type="text/javascript" src="console/js/static/plugins/jquery/jquery.cookie.js"></script>
    <!-- TopJUI框架配置 -->
    <script type="text/javascript" src="console/js/static/public/js/topjui.config.js"></script>
    <!-- TopJUI框架核心 -->
    <script type="text/javascript" src="console/js/topjui/js/topjui.core.min.js"></script>
    <!-- TopJUI中文支持 -->
    <script type="text/javascript" src="console/js/topjui/js/locale/topjui.lang.zh_CN.js"></script>
    <!-- layui框架js -->
    <script type="text/javascript" src="console/js/static/plugins/layui/layui.js" charset="utf-8"></script>
    <!-- 首页js -->
    <script type="text/javascript" src="console/js/static/public/js/topjui.index.js" charset="utf-8"></script>
    <script type="text/javascript">
    	var contextPath = "<%=basePath%>";
    </script>
</head>
<body>
	<div id="main" style="width:1920px;height:1080px;background-color:#6495ED;">
		<div style="height:20px;width:100%;"></div>
		<div id="title" style="width:100%;height:5%; border-bottom: solid 1px gray">
				<span style="font-size:3em;margin-left:30px;line-height:90%;font-weight: bold;">嘉兴迪筑集团数字化车间监控系统</span>
		</div>
		<div id="body" style="width:100%;height:90%;padding-left: 20px;">
			<div id="left" style="width:19%;height:100%;float:left;">
				<div style="height:3%;width:95%;margin:0 auto;font-size:18px;">实时数据</div>
				<div style="height:57%;width:95%;margin:0 auto;border:solid 1px gray"></div>
				<div style="height:3%;width:95%;margin:0 auto;font-size:18px;">报警信息</div>
				<div style="height:37%;width:95%;margin:0 auto;border:solid 1px gray"></div>
			</div>
			<div id="center" style="width:60%;height:100%;float:left">
				<div style="height:3%;width:95%;margin:0 auto;font-size:18px;">车间布局图</div>
				<div style="height:97%;width:95%;margin:0 auto;border:solid 1px gray"></div>
			</div>
			<div id="right" style="width:19%;height:100%;float:left">
				<div style="height:3%;width:95%;margin:0 auto;font-size:18px;">实时占比饼图</div>
				<div style="height:57%;width:95%;margin:0 auto;border:solid 1px gray"></div>
				<div style="height:3%;width:95%;margin:0 auto;font-size:18px;">状态比例TOP图</div>
				<div style="height:37%;width:95%;margin:0 auto;border:solid 1px gray"></div>
			</div>
		</div>
	</div>
</body>
</html>