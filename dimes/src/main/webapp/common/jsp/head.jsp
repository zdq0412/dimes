<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
    	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/" + request.getContextPath() + "/";
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Insert title here</title>
<script type="text/javascript"
	src="<%=basePath%>common/js/jquery.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>common/css/iview.js">
<script type="text/javascript" src="<%=basePath%>common/js/vue.min.js"></script>
<script type="text/javascript"
	src="<%=basePath%>common/js/iview.min.js"></script>
	<script type="text/javascript">
		var contextPath = "<%=basePath%>";
	</script>