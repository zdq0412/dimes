<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../common/jsp/head.jsp"%>
<script>
	$(function(){
		$.get("output/queryOutput4EmployeePerMonth.do",function(data){
			output(data,'employeeOutput');
		});
		 $.get("output/queryOutput4ProcessPerMonth.do",function(data){
			output(data,'processOutput');
		});
		$.get("output/queryOutput4DeviceSitePerMonth.do",function(data){
			output(data,'deviceSiteOutput');
		}); 
	});
	
	function output(data,id){
		var myChart = echarts.init(document.getElementById(id));
		 option = {
			        grid3D: {},
			        tooltip: {},
			        xAxis3D: {
			            type: 'category'
			        },
			        yAxis3D: {
			            type: 'category'
			        },
			        zAxis3D: {
			        	 type: 'category'
			        },
			        visualMap: {
			            max: 1e8,
			            dimension: '产量'
			        },
			        dataset: {
			            dimensions:data[0],
			            source: data 
			        }, 
			        series: [
			            {
			                type: 'bar3D',
			                shading: 'lambert',
			                encode: {
			                    x: data[0][0],
			                    y: data[0][1],
			                    z: data[0][2]
			                }
			            }
			        ]
			    };
			    myChart.setOption(option);
	}
</script>
<style>
.main{
	height:100%;
	width: 33%;
	float:left;
}
</style>
</head>
<body>
	<div id="main" style="width:1700px;height:800px;">
		<div id="employeeOutput" class="main"></div>
		<div id="processOutput" class="main"></div>
		<div id="deviceSiteOutput" class="main"></div>
	</div>
</body>
</html>