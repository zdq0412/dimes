$(function(){
	//异步加载OEE值
	$.get("oeeController/queryOEESForShow.do",function(result){
		oeeGraph(result);
	},'json');
	//异步加载 产量信息
	$.get("outputController/queryOutputSForShow.do",function(result){
		outputMonitorBody(result);
	},'json');
	//异步加载不合格产品
	$.get("lowQualityController/queryLowQualitySForShow.do",function(result){
		lowQualityBody(result)
	},'json');
	//异步加载员工信息
	$.get("employeeController/queryEmployees4Front.do",function(result){
		//showEmployees(result.mappings,result.employees)
	},'json');
	//异步加载所有首页图片
	$.get("fileupload/load.do",function(result){
		showPics(result)
	},'json');
	$.get("safetyCrossController/querySafetyCrosses4Frint.do",function(result){
		showSafetyCross(result);
	},'json');
	
});

/*安全绿十字  开始*/
var cellSize = [50, 50];
var pieRadius = 20;

function getData(result) {
	var date = +echarts.number.parseDate(result.currentMonth + '-01');
	var end = +echarts.number.parseDate(result.nextMonth+'-01');
	var dayTime = 3600 * 24 * 1000;
	var data = [];
	for (var time = date; time < end; time += dayTime) {
		data.push([
		           echarts.format.formatTime('yyyy-MM-dd', time),
		           Math.floor(Math.random() * 10000)
		           ]);
	}
	return data;
}

function getPieSeries(scatterData, chart,result) {
	return echarts.util.map(scatterData, function (item, index) {
		var center = chart.convertToPixel('calendar', item);
		return {
			id: index + 'pie',
			type: 'pie',
			center: center,
			label: {
				normal: {
					formatter: '{c}',
					position: 'inside'
				}
			},
			radius: pieRadius,
			data: [
			       {name: '有事故', value: result.safetyCrosses[index].lostTimeAccidents},
			       {name: '暂无数据', value: result.safetyCrosses[index].nearMissAccidents},
			       {name: '无事故', value:result.safetyCrosses[index].noAccidents}
			       ]

		};
	});
}

function getPieSeriesUpdate(scatterData, chart) {
	return echarts.util.map(scatterData, function (item, index) {
		var center = chart.convertToPixel('calendar', item);
		return {
			id: index + 'pie',
			center: center
		};
	});
}
function showSafetyCross(result){
	var scatterData = getData(result);

	option = {
			tooltip : {},
			legend: {
				data: ['有事故', ' ', '无事故'],
				bottom: 20
			},
			calendar: {
				top: 'middle',
				left: 'center',
				orient: 'vertical',
				cellSize: cellSize,
				yearLabel: {
					show: false,
					textStyle: {
						fontSize: 30
					}
				},
				dayLabel: {
					margin: 20,
					firstDay: 1,
					nameMap: ['星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六']
				},
				monthLabel: {
					show: false
				},
				range: [result.currentMonth]
			},
			series: [{
				id: 'label',
				type: 'scatter',
				coordinateSystem: 'calendar',
				symbolSize: 1,
				label: {
					normal: {
						show: true,
						formatter: function (params) {
							return echarts.format.formatTime('dd', params.value[0]);
						},
						offset: [-cellSize[0] / 2 + 10, -cellSize[1] / 2 + 10],
						textStyle: {
							color: '#000',
							fontSize: 14
						}
					}
				},
				data: scatterData
			}],
	        color:['red', '#FFFFFF','green']
	};
	// 基于准备好的dom，初始化echarts实例
	var myChart = echarts.init(document.getElementById('safetyCrossBody'));
	// 使用刚指定的配置项和数据显示图表。
	myChart.setOption(option); 
	if (!myChart.inNode) {
		var pieInitialized;
		setTimeout(function () {
			pieInitialized = true;
			myChart.setOption({
				series: getPieSeries(scatterData, myChart,result)
			});
		}, 10);

		myChart.onresize = function () {
			if (pieInitialized) {
				myChart.setOption({
					series: getPieSeriesUpdate(scatterData, myChart)
				});
			}
		};
	}

}
/*安全绿十字  结束*/

//显示首页图片
function showPics(result){
	var $logo = $("#logo");
	$logo.attr("src",result.logo);
	var $oeeTitle = $("#oeeTitle");
	$oeeTitle.attr("src",result.oeeTitle);
	var $outputTitle = $("#outputTitle");
	$outputTitle.attr("src",result.outputTitle);
	var $lowQualityTitle = $("#lowQualityTitle");
	$lowQualityTitle.attr("src",result.lowQualityTitle);
	var $empTitle = $("#empTitle");
	$empTitle.attr("src",result.empTitle);
	var $empBody = $("#empBody");
	$empBody.attr("src",result.empBody);
	var $errorProofPic = $("#errorProofPic");
	$errorProofPic.attr("src",result.errorProof);
	var $safetyCrossPic = $("#safetyCrossTitle");
	$safetyCrossPic.attr("src",result.safetyCross);
	var $errorProofTitle = $("#errorProofTitle");
	$errorProofTitle.attr("src",result.errorProofTitle);
}

//显示员工及技能信息
function showEmployees(mappings,employees){
	var $tbody = $("#operatorSkillBody tbody");
	$tbody.empty();
	//第一行：说明
	var $tr1 = $("<tr>");
	//第一行第一列
	var $tr1td1 = $("<td colspan='6'>");
	$tr1td1.append("说明");
	//第一行第二列
	var $tr1td2 = $("<td colspan='"+mappings.length+"'>");
	$tr1td2.append("操作技能");
	//将第一行的列追加到行中
	$tr1.append($tr1td1);
	$tr1.append($tr1td2);
	//将第一行追加到tbody中
	$tbody.append($tr1);

	//第二行
	var $tr2 = $("<tr>");
	/*//第二行第一列
	var $tr2td1 = $("<td colspan='6'>");
	//将第二行第一列追加到tr中
	$tr2.append($tr2td1);*/


	//$tbody.append($tr2);
	//第三行
	var $tr3 = $("<tr>");
	//第三行前留列标题
	var titles = ['NO.','班别','工号','姓名','角色','入职日期'];
	for(var i = 0;i<titles.length;i++){
		var $td = $("<td style='width:20px;'>");
		$td.append(titles[i]);

		$tr3.append($td);
	}

	//第二行其他列
	for(var i = 0;i<mappings.length;i++){
		var $td = $("<td>");
		/*		var $td = $("<td rowspan='2'>");
		 */		$td.append(mappings[i].skill.name);

		 $tr3.append($td);
	}

	$tbody.append($tr3);
	//其余行
	for(var i =0 ;i<employees.length;i++){
		var $tr = $("<tr>");
		var $td1 = $("<td>");
		var $td2 = $("<td>");
		var $td3 = $("<td>");
		var $td4 = $("<td>");
		var $td5 = $("<td>");
		var $td6 = $("<td>");

		$td1.append(i);
		$td2.append(employees[i].group.name);
		$td3.append(employees[i].no);
		$td4.append(employees[i].name);
		$td5.append(employees[i].role.name);
		var inDate = employees[i].inDate;
		var date = new Date(inDate);
		var inDateStr = date.getFullYear()+"/" + (date.getMonth()+1) + "/" + date.getDate();
		$td6.append(inDateStr);


		$tr.append($td1);
		$tr.append($td2);
		$tr.append($td3);
		$tr.append($td4);
		$tr.append($td5);
		$tr.append($td6);

		var mappings = employees[i].employeeSkillMapping;

		for(var j = 0;j<mappings.length;j++){
			var $td = $("<td>");
			$td.append(mappings[j].skillLevel.code);

			$tr.append($td);
		}
		$tbody.append($tr);
	}
}

//OEE Graph
function oeeGraph(result){
	var series = new Array();
	var colorArray = new Array();
	colorArray.push('#8FBC8F');
	colorArray.push('#F00');
	colorArray.push('#00F');
	for(var i = 0;i<result.classes.length;i++){
		var jsonObj = {
				name:result.classes[i].name,
				type:'bar',
				itemStyle: {
					normal: {
						color:colorArray[i],
						label: {
							show: false,
							position: 'top',
							formatter: '{c}'
						}
					}
				},
				//data:[50, 75, 100, 150, 200, 250, 150, 100, 95, 160, 50, 45]
				data:result.oeeList[i]
			}
		
		series[i] = jsonObj;
	}
	series.push({
		name:'平均值',
		type:'line',
		itemStyle : { 
			normal : {
				color:'#888'
			}
		},
		data:result.avgs
	});
	
	series.push({
		name : '目标',
		type : 'line',
		itemStyle : { 
			normal : {
				color:'#008000',
			}
		},
		data : result.goalOeeList
	});
	var jsonStr = JSON.stringify(series);
	var  option = {
			title:{
				show : true,
				text : 'oee'
			},
			tooltip: {
				trigger: 'axis'
			},
			legend: {
				data:result.classes
			},
			xAxis: [{
				type: 'category',
				data: result.days
			}],
			yAxis: [{
				type: 'value',
				name: 'oee值/百分比',
				min: 0,
				max: 100,
				interval: 10,
				axisLabel: {
					formatter: '{value}% '
				}
			}],
			series: series
	};
	// 基于准备好的dom，初始化echarts实例
	var myChart = echarts.init(document.getElementById('oeeGraphContent'));
	// 使用刚指定的配置项和数据显示图表。
	myChart.setOption(option);  
}

function requestOee(productionUnitId){
	$.get("oee/queryOee4ProductionUnit.do",{productionUnitId:productionUnitId},function(result){
		oeeGraph(result);
	});
}
//Output Monitor 产量 
function outputMonitorBody(result){
	option = {
			title : {
				text: result.goal.currentMonth
			},
			tooltip : {
				trigger: 'axis'
			},
			legend: {
				data:['A班','B班']
			},
			xAxis : [
			         {
			        	 type : 'category',
			        	 data :result.days
			         }
			         ],
			         yAxis : [{
			        	 type: 'value',
			        	 name: '产量',
			        	 min: 0,
			        	 max: 3500,
			        	 interval: 500,
			        	 axisLabel: {
			        		 formatter: '{value} '
			        	 }
			         }],
			         series : [
			                   {
			                	   name:'A班',
			                	   type:'bar',
			                	   stack:'B班',
			                	   itemStyle: {
			                		   normal: {
			                			   color:'#8FBC8F', 
			                			   label:{show:false}
			                		   }
			                	   },
			                	   data:result.teamA
			                   },
			                   {
			                	   name:'B班',
			                	   type:'bar',
			                	   stack:'B班',
			                	   itemStyle: {normal: {color:'#6699FF', label:{show:false}}},
			                	   data:result.teamB
			                   },//目标
			                   {
			                	   name : '目标',
			                	   type : 'line',
			                	   itemStyle: {normal: {color:'#008000'}},
			                	   data : result.goals
			                   }
			                   ]
	};

	// 基于准备好的dom，初始化echarts实例
	var myChart = echarts.init(document.getElementById('outputMonitorBody'));
	// 使用刚指定的配置项和数据显示图表。
	myChart.setOption(option);
}
//不合格产品 
function lowQualityBody(result){
	option = {
			title : {
				text: result.goal.currentMonth
			},
			tooltip : {
				trigger: 'axis'
			},
			legend: {
				data:['A班','B班']
			},
			xAxis : [
			         {
			        	 type : 'category',
			        	 data :result.days
			         }
			         ],
			         yAxis : [{
			        	 type: 'value',
			        	 name: '数量',
			        	 min: 0,
			        	 max: 30,
			        	 interval: 5,
			        	 axisLabel: {
			        		 formatter: '{value} '
			        	 }
			         }],
			         series : [
			                   {
			                	   name:'A班',
			                	   type:'bar',
			                	   stack:'B班',
			                	   itemStyle: {
			                		   normal: {
			                			   color:'#8FBC8F', 
			                			   label:{show:false}
			                		   }
			                	   },
			                	   data:result.teamA
			                   },
			                   {
			                	   name:'B班',
			                	   type:'bar',
			                	   stack:'B班',
			                	   itemStyle: {normal: {color:'#6699FF', label:{show:false}}},
			                	   data:result.teamB
			                   },//目标
			                   {
			                	   name : '目标',
			                	   type : 'line',
			                	   itemStyle: {normal: {color:'#008000'}},
			                	   data : result.goals
			                   }
			                   ]
	};
	
	// 基于准备好的dom，初始化echarts实例
	var myChart = echarts.init(document.getElementById('lowQualityBody'));
	// 使用刚指定的配置项和数据显示图表。
	myChart.setOption(option);
}