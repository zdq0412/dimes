<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../common/jsp/head.jsp"%>
<script>
	$(function() {
		//批号执行数据
		$.get('workSheet/queryAllWorkSheetDetails.do',function(data){
			var $tbody = $("#record");
			for(var i = 0;i<data.rows.length;i++){
				var workSheetDetail = data.rows[i];
				var $row = $("<tr>");
				var $td0 = $("<td>");
				var $td1 = $("<td>");
				var $td2 = $("<td>");
				var $td3 = $("<td>");
				
				$td0.append(workSheetDetail.workSheet.batchNumber);
				$td1.append(workSheetDetail.workSheet.workPieceCode);
				$td2.append(workSheetDetail.completeCount);
				$td3.append(workSheetDetail.processName);
				
				
				$row.append($td0);
				$row.append($td1);
				$row.append($td2);
				$row.append($td3);
				
				$tbody.append($row);
			}
		});
		
		$.get('oee/queryOee4Factory.do',
						function(data) {
							var $main = $("#oee0");
							for (var i = 0; i < data.productionUnits.length; i++) {
								var $div = $("<div style='float:left;height:100%;width:100%;' id='div_"
										+ i + "' >");
								$main.append($div);
								oee("div_" + i, data.values[i] ? data.values[i]
										: 0, data.productionUnits[i].name);
							}
							secondary(data.preMonthOee, data.currentMonthOee,
									data.currentDayOee);
						});

		$.get("output/queryOutput4EmployeePerMonth.do", function(data) {
			output(data, 'employeeOutput');
		});
		$.get("output/queryOutput4ProcessPerMonth.do", function(data) {
			output(data, 'processOutput');
		});
		$.get("output/queryOutput4DeviceSitePerMonth.do", function(data) {
			output(data, 'deviceOutput');
		});
		var time = new Date();
		$("#time").text(
				time.getFullYear() + "-" + (time.getMonth() + 1) + "-"
						+ time.getDate());
	});
	//生产单元
	function oee(id, value, title) {
		var app = echarts.init(document.getElementById(id));
		option = {
			title : {
				show : true,
				text : title,
				left : 'center',
				textStyle : {
					color : '#8FA4BF'
				}
			},
			tooltip : {
				formatter : "{a} <br/>{b} : {c}%"
			},
			toolbox : {
				feature : {
					restore : {},
					saveAsImage : {}
				}
			},
			series : [ {
				title : {
					color : '#8FA4BF'
				},
				name : title,
				type : 'gauge',
				detail : {
					formatter : '{value}%'
				},
				data : [ {
					value : value,
					name : 'OEE'
				} ]

			} ]
		};
		app.setOption(option);
	}
	//当前班
	function secondary(preMonthOee, currentMonthOee, currentDayOee) {
		var app = echarts.init(document.getElementById('oee2'));
		option = {
			tooltip : {
				formatter : "{a} <br/>{c} {b}"
			},
			toolbox : {
				show : true,
				feature : {
					restore : {
						show : true
					},
					saveAsImage : {
						show : true
					}
				}
			},
			series : [
					{
						name : '',
						type : 'gauge',
						z : 3,
						min : 0,
						max : 220,
						splitNumber : 11,
						radius : '70%',
						axisLine : { // 坐标轴线
							lineStyle : { // 属性lineStyle控制线条样式
								width : 10
							}
						},
						axisTick : { // 坐标轴小标记
							length : 15, // 属性length控制线长
							lineStyle : { // 属性lineStyle控制线条样式
								color : 'auto'
							}
						},
						splitLine : { // 分隔线
							length : 20, // 属性length控制线长
							lineStyle : { // 属性lineStyle（详见lineStyle）控制线条样式
								color : 'auto'
							}
						},
						axisLabel : {
							backgroundColor : 'auto',
							borderRadius : 2,
							color : '#eee',
							padding : 3,
							textShadowBlur : 2,
							textShadowOffsetX : 1,
							textShadowOffsetY : 1,
							textShadowColor : '#222'
						},
						title : {
							// 其余属性默认使用全局文本样式，详见TEXTSTYLE
							fontWeight : 'bolder',
							fontSize : 20,
							fontStyle : 'italic',
							color : '#8FA4BF'
						},
						detail : {
							// 其余属性默认使用全局文本样式，详见TEXTSTYLE
							formatter : function(value) {
								value = (value + '').split('.');
								value.length < 2 && (value.push('00'));
								return ('00' + value[0]).slice(-2) + '.'
										+ (value[1] + '00').slice(0, 2);
							},
							fontWeight : 'bolder',
							borderRadius : 3,
							backgroundColor : '#4440',
							borderColor : '#aaa',
							shadowBlur : 5,
							shadowColor : '#333',
							shadowOffsetX : 0,
							shadowOffsetY : 3,
							borderWidth : 2,
							textBorderColor : '#000',
							textBorderWidth : 2,
							textShadowBlur : 2,
							textShadowColor : '#fff',
							textShadowOffsetX : 0,
							textShadowOffsetY : 0,
							fontFamily : 'Arial',
							width : 100,
							color : '#eee',
							rich : {}
						},
						data : [ {
							value : currentMonthOee,
							name : '当前月'
						} ]
					}, {
						name : '',
						type : 'gauge',
						center : [ '17%', '55%' ], // 默认全局居中
						radius : '50%',
						min : 0,
						max : 7,
						endAngle : 45,
						splitNumber : 7,
						axisLine : { // 坐标轴线
							lineStyle : { // 属性lineStyle控制线条样式
								width : 8
							}
						},
						axisTick : { // 坐标轴小标记
							length : 12, // 属性length控制线长
							lineStyle : { // 属性lineStyle控制线条样式
								color : 'auto'
							}
						},
						splitLine : { // 分隔线
							length : 20, // 属性length控制线长
							lineStyle : { // 属性lineStyle（详见lineStyle）控制线条样式
								color : 'auto'
							}
						},
						pointer : {
							width : 5
						},
						title : {
							offsetCenter : [ 0, '-10%' ], // x, y，单位px 
							color : '#eee'
						},
						detail : {
							// 其余属性默认使用全局文本样式，详见TEXTSTYLE
							fontWeight : 'bolder'
						},
						data : [ {
							value : preMonthOee,
							name : '上个月'
						} ]
					}, {
						name : '',
						type : 'gauge',
						center : [ '83%', '55%' ], // 默认全局居中
						radius : '50%',
						min : 0,
						max : 7,
						startAngle : 135,
						splitNumber : 7,
						axisLine : { // 坐标轴线
							lineStyle : { // 属性lineStyle控制线条样式
								width : 8
							}
						},
						axisTick : { // 坐标轴小标记
							length : 12, // 属性length控制线长
							lineStyle : { // 属性lineStyle控制线条样式
								color : 'auto'
							}
						},
						splitLine : { // 分隔线
							length : 20, // 属性length控制线长
							lineStyle : { // 属性lineStyle（详见lineStyle）控制线条样式
								color : 'auto'
							}
						},
						pointer : {
							width : 5
						},
						title : {
							offsetCenter : [ 0, '-10%' ], // x, y，单位px
							color : '#eee'
						},
						detail : {
							// 其余属性默认使用全局文本样式，详见TEXTSTYLE
							fontWeight : 'bolder'
						},
						data : [ {
							value : currentDayOee,
							name : '当天'
						} ]
					} ]
		};

		app.setOption(option);
	}

	/**产量*/
	function output(data, id) {
		var myChart = echarts.init(document.getElementById(id));
		option = {
			grid3D : {},
			tooltip : {},
			xAxis3D : {
				type : 'category',
				nameTextStyle : {
					color : '#8FA4BF'
				},
				axisLabel : {
					color : '#8FA4BF'
				}
			},
			yAxis3D : {
				type : 'category',
				nameTextStyle : {
					color : '#8FA4BF'
				},
				axisLabel : {
					color : '#8FA4BF'
				}
			},
			zAxis3D : {
				type : 'category',
				nameTextStyle : {
					color : '#8FA4BF'
				},
				axisLabel : {
					color : '#8FA4BF'
				}
			},
			visualMap : {
				max : 1e8,
				dimension : '产量'
			},
			dataset : {
				dimensions : data[0],
				source : data
			},
			series : [ {
				type : 'bar3D',
				shading : 'lambert',
				label : {
					color : '#f00'
				},
				encode : {
					x : data[0][0],
					y : data[0][1],
					z : data[0][2]
				}
			} ]
		};
		myChart.setOption(option);
	}
</script>
<style>
body {
	margin: 0;
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

.title {
	height: 40px;
	width: 100%;
	color: #57EEFD;
	font-size: 25px;
	margin-left: 10px;
	margin-top: 5px;
}

#top {
	height: 410px;
	width: 90%;
	margin: auto auto;
	margin-bottom: 20px;
}

#bottom {
	height: 410px;
	width: 90%;
	margin: auto auto;
}

.oee {
	height: 100%;
	width: 32%;
	background-color: #1C2437;
}

.toRight {
	margin-right: 34px;
	float: left;
}

.toLeft {
	float: right;
}

.output {
	height: 100%;
	width: 32%;
	background-color: #1C2437;
}

table{
	width:90%;
	margin:auto auto;
}

tr{
	height:30px;
}

#record tr td{
	text-align: center;
	color:#FFF;
}

#record tr:nth-child(odd){background:#313348;}
#record tr:nth-child(even){background:#2C2E45;}
.productionUnitOee {
	height: 49%;
	width: 100%;
	margin: 5px auto;
}

.oeeContent {
	height: 370px;
	width: 100%;
}

.outputContent {
	height: 360px;
	width: 100%;
}
</style>
</head>
<body>
	<div id="outer">
		<div id="blank"></div>
		<div id="title">生产执行状态</div>
		<div id="time"></div>
		<div id="top">
			<div class="oee toRight">
				<div class="productionUnitOee">
					<div class="title">各单元OEE仪表</div>
					<div id="oee0" class="oeeContent"></div>
				</div>
				<!-- <div class="productionUnitOee">
					<div class="title">各单元OEE数据表</div>
					<div id="oee1" style="height:160px;width:100%;"></div>
				</div> -->
			</div>
			<div class="oee toRight">
				<div class="title">综合OEE仪表板</div>
				<div id="oee2" class="oeeContent"></div>
			</div>
			<div class="oee toLeft">
				<div class="title">批号执行数据表</div>
				<div class="oeeContent">
					<table>
						<thead>
							<tr style="background-color:#1E1F33;color:#82858E;text-align: center;">
								<td style="width:120px;">批号</td>
								<td style="width:120px;">工件代码</td>
								<td style="width:120px;">完工数</td>
								<td style="width:120px;">工序名称</td>
							</tr>
						</thead>
						<tbody id="record"></tbody>
					</table>
				</div>
			</div>
		</div>
		<div id="bottom">
			<div class="output toRight">
				<div class="title">人员VS产量</div>
				<div id="employeeOutput" class="outputContent"></div>
			</div>
			<div class="output toRight">
				<div class="title">工序VS产量</div>
				<div id="processOutput" class="outputContent"></div>
			</div>
			<div class="output toLeft">
				<div class="title">设备VS产量</div>
				<div id="deviceOutput" class="outputContent"></div>
			</div>
		</div>
	</div>
</body>
</html>