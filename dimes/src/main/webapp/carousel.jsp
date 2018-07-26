<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="common/jsp/head.jsp"%>
<html>
<head>
<title>DIMES轮播显示</title>
<style type="text/css">
iframe {
	height: 100%;
	width: 100%;
}
</style>
</head>
<body>

	<div class="layui-carousel" id="test3" lay-filter="test1">
		<div carousel-item="">
			<div>
				<iframe src="employeeStatus.jsp" id="employeeStatus"></iframe>
			</div>
			<div>
				<iframe src="produceExecuteStatus.jsp" id="produceExecuteStatus"></iframe>
			</div>
			<div>
				<iframe src="deviceRunStatus.jsp" id="deviceRunStatus"></iframe>
			</div>
			<div>
				<iframe src="runningStatus.jsp" id="runningStatus"></iframe>
			</div>
			<div>
				<iframe src="parameterStatus.jsp" id="parameterStatus"></iframe>
			</div>
			<div>
				<iframe src="qualityStatus.jsp" id="qualityStatus"></iframe>
			</div>
		</div>
	</div>

	<script>
		layui.use([ 'carousel' ], function() {
			var carousel = layui.carousel;

			//设定各种参数
			var ins3 = carousel.render({
				elem : '#test3',
				full : true
			});
			//轮播切换事件
			carousel.on("change(test1)", function(obj) {
				switch (obj.index) {
				case 0:
					$("#employeeStatus").attr('src',
							$('#employeeStatus').attr('src'));
					break;
				case 1:
					$("#produceExecuteStatus").attr('src',
							$('#produceExecuteStatus').attr('src'));
					break;
				case 2:
					$("#deviceRunStatus").attr('src',
							$('#deviceRunStatus').attr('src'));
					break;
				case 3:
					$("#runningStatus").attr('src',
							$('#runningStatus').attr('src'));
					break;
				case 4:
					$("#parameterStatus").attr('src',
							$('#parameterStatus').attr('src'));
					break;
				case 5:
					$("#qualityStatus").attr('src',
							$('#qualityStatus').attr('src'));
					break;
				}
			});
		});
	</script>
</body>
</html>
