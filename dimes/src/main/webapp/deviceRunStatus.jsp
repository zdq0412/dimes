<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../common/jsp/head.jsp"%>
<script>
	$(function() {
		//设备停机分类
		$.get('lostTimeRecord/queryLostTimeRecordOfHalt.do', function(data) {
			var app = echarts.init(document
					.getElementById('deviceHaltClassify'));
			option = {
				tooltip : {
					trigger : 'axis',
					axisPointer : {
						type : 'cross',
						crossStyle : {
							color : '#999'
						}
					}
				},
				toolbox : {
					feature : {
						magicType : {
							show : true,
							type : [ 'line', 'bar' ]
						},
						restore : {
							show : true
						},
						saveAsImage : {
							show : true
						}
					}
				},
				legend : {
					data : [ '故障时间', '占比' ],
					textStyle : {
						color : '#8FA4BF'
					}
				},
				xAxis : [ {
					type : 'category',
					data : [ '1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月',
							'9月', '10月', '11月', '12月' ],
					axisPointer : {
						type : 'shadow'
					},
					axisLabel : {
						color : '#8FA4BF'
					}
				} ],
				yAxis : [ {
					type : 'value',
					name : '故障小时数',
					min : 0,
					interval : 50,
					axisLabel : {
						formatter : '{value} h',
						color : '#8FA4BF'
					},
					nameTextStyle:{
						color:'#8FA4BF'
					}
				}, {
					type : 'value',
					name : '占比',
					min : 0,
					interval : 5,
					axisLabel : {
						formatter : '{value} %',
						color : '#8FA4BF'
					},
					nameTextStyle:{
						color:'#8FA4BF'
					}
				} ],
				series : [ {
					name : '故障时间',
					type : 'bar',
					data : data.hours
				}, {
					name : '占比',
					type : 'line',
					yAxisIndex : 1,
					data : data.ratios
				} ]
			};

			app.setOption(option);
		});

		//故障时间
		$.get('lostTimeRecord/queryLostTimeRecordFor1Year.do',
				function(result) {
					var myChart = echarts.init(document
							.getElementById('deviceHaltTime'));
					option = {
						legend : {
							textStyle : {
								color : '#8FA4BF'
							}
						},
						tooltip : {
							trigger : 'axis',
							showContent : false
						},
						dataset : {
							source : result.data
						},
						xAxis : {
							type : 'category',
							axisLabel : {
								color : '#8FA4BF'
							}
						},
						yAxis : {
							gridIndex : 0,
							axisLabel : {
								color : '#8FA4BF'
							}
						},
						grid : {
							top : '55%'
						},
						series : [ {
							type : 'line',
							smooth : true,
							seriesLayoutBy : 'row'
						}, {
							type : 'line',
							smooth : true,
							seriesLayoutBy : 'row'
						}, {
							type : 'line',
							smooth : true,
							seriesLayoutBy : 'row'
						}, {
							type : 'line',
							smooth : true,
							seriesLayoutBy : 'row'
						}, {
							type : 'pie',
							id : 'pie',
							radius : '30%',
							center : [ '50%', '25%' ],
							label : {
								formatter : '{b}: {@2012} ({d}%)'
							},
							itemStyle: {
				            	color:'#03BAED'
							},
							encode : {
								itemName : result.data[0][0],
								value : '2012',
								tooltip : '2012'
							}
						} ]
					};

					myChart.on('updateAxisPointer', function(event) {
						var xAxisInfo = event.axesInfo[0];
						if (xAxisInfo) {
							var dimension = xAxisInfo.value + 1;
							myChart.setOption({
								series : {
									id : 'pie',
									label : {
										formatter : '{b}: {@[' + dimension
												+ ']} ({d}%)'
									},
									encode : {
										value : dimension,
										tooltip : dimension
									}
								}
							});
						}
					});
					myChart.setOption(option);
				});
		setInterval($.get("lostTimeRecord/queryLostTime4RealTime.do",function(data){
			realTime(data);
		}),60*1000);

		var time = new Date();
		$("#time").text(
				time.getFullYear() + "-" + (time.getMonth()+1) + "-"
						+ time.getDate());
	});
	//故障实时时间
	function realTime(data){
		var myChart = echarts.init(document.getElementById("realTime"));
		option = {
			    xAxis: {
			        type: 'category',
			        data: data.minutes,
					axisLabel : {
						color : '#8FA4BF'
					}
			    },
			    yAxis: {
			        type: 'value',
					axisLabel : {
						color : '#8FA4BF'
					}
			    },
			    series: [{
			        data: data.lostTimeCounts,
			        type: 'line'
			    }]
			};
		myChart.setOption(option);
	}
</script>
<style>
 body{
 	margin:0;
 }
#outer {
	height: 1080px;
	width: 1920px;
	background-image: url('front/imgs/blue.png');
}

#blank {
	text-align: center;
	width: 1920px;
	height: 40px;
}

#title {
	text-align: center;
	width: 100%;
	height: 50px;
	line-height: 50px;
	font-size: 50px;
	color: #57EEFD;
}

#time {
	width: 100%;
	height: 90px;
	text-align: center;
	line-height: 98px;
	color: #57EEFD;
	font-size: 25px;
}

#middle {
	height: 450px;
	width: 90%;
	margin: auto auto;
}

#left {
	height: 100%;
	width: 49%;
	float: left;
	background-color: #1C2437;
}

#right {
	height: 100%;
	width: 49%;
	float: right;
	background-color: #1C2437;
}

.title {
	height: 40px;
	width: 100%;
	color: #57EEFD;
	font-size: 25px;
	margin-left:10px;
	margin-top:5px;
}

#deviceHaltTime {
	height: 100%;
	width: 100%;
}
#deviceHaltClassify {
	height: 100%;
	width: 100%;
}
#realTimeDiv {
	height: 350px;
	width: 90%;
	background-color: #1C2437;
	margin: 25px auto;
}
</style>
</head>
<body>
	<div id="outer">
		<div id="blank"></div>
		<div id="title">设备运行状态</div>
		<div id="time"></div>

		<div id="middle">
			<div id="left">
				<div class="title">故障时间</div>
				<div id="deviceHaltTime"></div>
			</div>
			<div id="right">
				<div class="title">故障停机分类</div>
				<div id="deviceHaltClassify"></div>
			</div>
		</div>
		<div id="realTimeDiv">
			<div class="title">实时故障时间</div>
			<div id="realTime" style="height:310px;width:100%;">
				
			</div>
		</div>
	</div>
</body>
</html>