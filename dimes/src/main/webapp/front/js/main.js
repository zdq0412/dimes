var pathes;
	var layouts;
	var colors;
	$(function(){
		$.get("secureEnvironmentRecord/querySecureEnvironment.do",function(data){
			qualityCalendar(data);
		});
	});
	 
	function qualityCalendar(data){
		var myChart = echarts.init(document.getElementById("main"));

		 layouts = [
		    [[0, 0]],
		    [[-0.25, 0], [0.25, 0]],
		    [[0, -0.2], [-0.2, 0.2], [0.2, 0.2]],
		    [[-0.25, -0.25], [-0.25, 0.25], [0.25, -0.25], [0.25, 0.25]]
		];
		 pathes = 'M741.06368 733.310464c8.075264-29.262438 20.615373-40.632422 14.64105-162.810061C966.089728 361.789952 967.93897 72.37847 967.855002 54.693683c0.279347-0.279347 0.418509-0.419533 0.418509-0.419533s-0.17705-0.00512-0.428749-0.00512c0-0.251699 0-0.428749 0-0.428749s-0.139162 0.14633-0.418509 0.425677c-17.695744-0.083866-307.10784 1.760051-515.833958 212.142592-122.181632-5.984256-133.55305 6.563533-162.815693 14.644531C235.35063 295.798886 103.552614 436.975309 90.630758 486.076621c-12.921856 49.105408 39.634227 56.859034 58.579558 58.581197 18.953421 1.724314 121.471386-9.475789 130.09111 4.309094 0 0 16.367411 11.200102 17.226035 41.346662 0.850432 29.796659 15.173222 71.354163 37.123994 97.267302-0.028672 0.027648-0.05632 0.054272-0.083866 0.074752 0.158618 0.13097 0.316211 0.261939 0.474829 0.390861 0.129946 0.149402 0.261939 0.319283 0.393011 0.468685 0.019456-0.019456 0.04608-0.049152 0.075776-0.075674 25.918362 21.961216 67.477504 36.272128 97.269248 37.122458 30.149837 0.859546 41.354547 17.234534 41.354547 17.234534 13.779354 8.608051 2.583962 111.122842 4.302131 130.075546 1.727386 18.95168 9.477222 71.498445 58.579558 58.576077C585.12896 918.526771 726.311117 786.734182 741.06368 733.310464zM595.893555 426.206003c-39.961702-39.965184-39.961702-104.75991 0-144.720077 39.970918-39.96928 104.768307-39.96928 144.730112 0 39.970918 39.960064 39.970918 104.75479 0 144.720077C700.661862 466.171187 635.864474 466.171187 595.893555 426.206003zM358.53312 769.516032c-31.923302-4.573184-54.890394-18.410291-71.41847-35.402342-16.984474-16.526438-30.830387-39.495475-35.405824-71.420621-4.649062-28.082586-20.856832-41.167565-38.76649-38.763827-17.906586 2.40681-77.046886 66.714419-80.857805 89.475891-3.80887 22.752154 29.271859 12.081152 46.424166 27.654861 17.151283 15.590093-2.139853 61.93664-14.733107 86.845952-6.441984 12.735078-10.289766 26.42176-4.22953 33.76087 7.346586 6.070272 21.03593 2.222592 33.769472-4.220109 24.912384-12.585677 71.258829-31.872922 86.842368-14.731469 15.583539 17.160806 4.911002 50.229965 27.674419 46.419251 22.754099-3.807744 87.065395-62.946611 89.466163-80.85248C399.70857 790.374093 386.627072 774.166938 358.53312 769.516032z';
		colors = [
		     '#16B644', '#c4332b','#6862FD', '#FDC763'
		];
		
		var $names = $("#names");
		for(var i = 0;i<data.names.length;i++){
			var $div = $("<div style='margin:auto auto;'>");
			var $text = $("<p style='float:left;font-size:1em;' >");
			$text.append(data.names[i]);
			$div.append($text);
			var $color = $('<div style="float:left;height:15px;width:30px;background-color:' + colors[i] +';margin-right:20px;">');
			$div.append($color);
			
			$names.append($div);
		}
		
		option = {
		    tooltip: {
		    },
		    calendar: [{
		        left: 'center',
		        top: 'middle',
		        cellSize: [55,38],
		        yearLabel: {show: false},
		        orient: 'vertical',
		        dayLabel: {
		            firstDay: 1,
		            nameMap: 'cn'
		        },
		        monthLabel: {
		            show: false
		        },
		        range: data.month
		    }],
		    series: [{
		        type: 'custom',
		        coordinateSystem: 'calendar',
		        renderItem: renderItem,
		        dimensions: [null, {type: 'ordinal'}],
		        data: getData(data)
		    }]
		};

	    myChart.setOption(option);
	}
	function getData(data) {
		var date = +echarts.number.parseDate(data.month + '-01');
	    var end = +echarts.number.parseDate(data.nextMonth + '-01');
	    var dayTime = 3600 * 24 * 1000;
	    var d = [];
	    var j = 0;
	    for (var time = date; time < end; time += dayTime) {
	        var items = [];
	        var counts = data.data[j++];
	        var eventCount = counts.length;
	        for (var i = 0; i < eventCount; i++) {
	        	 items.push(counts[i]);
	        }
	        d.push([
	            echarts.format.formatTime('yyyy-MM-dd', time),
	            items.join('|')
	        ]);
	    }
	    return d;
	}

	function renderItem(params, api) {
	    var cellPoint = api.coord(api.value(0));
	    var cellWidth = params.coordSys.cellWidth;
	    var cellHeight = params.coordSys.cellHeight;

	    var value = api.value(1);
	    var events = value && value.split('|');
	    if (isNaN(cellPoint[0]) || isNaN(cellPoint[1])) {
	        return;
	    }

	    var group = {
	        type: 'group',
	        children: echarts.util.map(layouts[events.length - 1], function (itemLayout, index) {
	        	var config = {
		                type: 'path',
		                shape: {
		                    pathData: pathes,
		                    x: -8,
		                    y: -8,
		                    width: 15,
		                    height: 15
		                },
		                position: [
		                    cellPoint[0] + echarts.number.linearMap(itemLayout[0], [-0.5, 0.5], [-cellWidth / 2, cellWidth / 2]),
		                    cellPoint[1] + echarts.number.linearMap(itemLayout[1], [-0.5, 0.5], [-cellHeight / 2 + 10, cellHeight / 2])
		                ],
		                style: api.style({
 		                    fill: colors[index]
		                })
		            };
	            return  config;
	        }) || []
	    };

	    group.children.push({
	        type: 'text',
	        style: {
	            x: cellPoint[0],
	            y: cellPoint[1] - cellHeight / 2 + 5,
	            text: echarts.format.formatTime('dd', api.value(0)),
	            fill: '#777',
	            textFont: api.font({fontSize: 12})
	        }
	    });

	    return group;
	}
//人员技能
function requestEmployeeSkill(productionUnitId) {
	$.get("employeeSkill/queryEmployeeSkill4empByProductionUnitId.do", {
		productionUnitId : productionUnitId
	}, function(result) {
		employeeSkill(result);
	});
}
//产线级
function employeeSkill(data) {
	var myChart = echarts.init(document.getElementById("employeeSkillGraphBody"));

	var d = data.data;

	d = d.map(function (item) {
	    return [item[1], item[0], item[2] || '-'];
	});

	option = {
	    tooltip: {
	        position: 'top'
	    },
	    animation: false,
	    grid: {
	        height: '50%',
	        y: '10%'
	    },
	    xAxis: {
	        type: 'category',
	        data: data.emps,
	        splitArea: {
	            show: true
	        }
	    },
	    yAxis: {
	        type: 'category',
	        data: data.skillLevels,
	        splitArea: {
	            show: true
	        }
	    },
	    visualMap: {
	        min: 0,
	        max: 10,
	        calculable: true,
	        orient: 'horizontal',
	        left: 'center',
	        bottom: '15%'
	    },
	    series: [{
	        name: 'Punch Card',
	        type: 'heatmap',
	        data: d,
	        label: {
	            normal: {
	                show: true
	            }
	        },
	        itemStyle: {
	            emphasis: {
	                shadowBlur: 10,
	                shadowColor: 'rgba(0, 0, 0, 0.5)'
	            }
	        }
	    }]
	};
	 myChart.setOption(option);
}
//不合格品
function requestNGRecord(productionUnitId) {
	$.get("ngRecord/queryNGRecord4ProductionUnit.do", {
		productionUnitId : productionUnitId
	}, function(result) {
		ngRecord(result);
	});
}
//不合格记录:产线级
function ngRecord(data) {
	var arr = new Array();
	for (var i = 0; i < data.classNameList.length; i++) {
		var className = data.classNameList[i];
		//不合格数
		var ngCount = {
			name : className,
			type : 'bar',
			stack : data.classNameList[0],
			data : data.ngCountMap[className]
		};
		//ppm
		var ppm = {
			name : className,
			type : 'line',
			data : data.ppmMap[className]
		};
		arr.push(ngCount);
		arr.push(ppm);
	}
	var goalNg = {
			name : '目标',
			type : 'line',
			yAxisIndex:1,
			data : data.ngGoalList
		};
	
	arr.push(goalNg);
	var myChart = echarts
			.init(document.getElementById("ngRecordGraphBody"));
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
		legend : {
			data : data.classNameList
		},
		xAxis : [ {
			type : 'category',
			data : data.thisMonth,
			axisPointer : {
				type : 'shadow'
			}
		} ],
		yAxis : [ {
			type : 'value',
			name : 'PPM',
			position:'right',
			axisLabel : {
				formatter : '{value}'
			}
		}, {
			type : 'value',
			name : 'NG数',
			position:'left',
			axisLabel : {
				formatter : '{value}'
			}
		} ],
		series : arr
	};

	myChart.setOption(option);
}
//点击产线，查询该产线的产量
function requestOutput(productionUnitId) {
	$.get("output/queryOutput4ProductionUnit.do", {
		productionUnitId : productionUnitId
	}, function(result) {
		output(result);
	});
}
//产线级
function output(data) {
	var arr = new Array();
	for(var i = 0;i<data.classNameList.length;i++){
		var className = data.classNameList[i];
		var ser = {
				name : className,
				type : 'bar',
				stack:data.classNameList[0],
				data : data.outputMap[className]
			};
		arr.push(ser);
	}
	
	var goalOutput = {
	        data: data.goalOutput,
	        type: 'line'
	    };
	
	arr.push(goalOutput);
	
	var myChart = echarts.init(document.getElementById("outputGraphBody"));
	option = {
		tooltip : {
			trigger : 'axis',
			axisPointer : { // 坐标轴指示器，坐标轴触发有效
				type : 'shadow' // 默认为直线，可选为：'line' | 'shadow'
			}
		},
		legend : {
			data : data.classNameList
		},
		grid : {
			left : '3%',
			right : '4%',
			bottom : '3%',
			containLabel : true
		},
		xAxis : [ {
			type : 'category',
			data : data.days
		} ],
		yAxis : [ {
			type : 'value'
		} ],
		series : arr
	};
	myChart.setOption(option);
}
//点击产线，查询该产线的oee
function requestOee(productionUnitId){
	$.get("oee/queryOee4ProductionUnit.do",{productionUnitId:productionUnitId},function(result){
		oeeGraph(result);
	});
}
//显示oee信息
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
	var myChart = echarts.init(document.getElementById('oeeGraphBody'));
	// 使用刚指定的配置项和数据显示图表。
	myChart.setOption(option);  
}

//点击产线，查询该产线的oee
function requestLostTime(productionUnitId){
	$.get("lostTimeRecord/queryLostTimeReason4ProductionUnit.do",{productionUnitId:productionUnitId},function(result){
		lostTimeReasonGraph(result);
	});
}
//损时分析
function lostTimeReasonGraph(result){
	var series = new Array();
	for(var i = 0;i<result.classes.length;i++){
		var jsonObj =  {
	            name:result.classes[i].name,
	            type:'bar',
	            stack:'班次',
	            data:result.lostTimeList[i]
	        }
		
		series[i] = jsonObj;
	}
	
	series.push({
		name : '目标',
		type : 'line',
		itemStyle : { 
			normal : {
				color:'#008000',
			}
		},
		lineStyle:{
			width:5
		},
		data : result.goalLostTimeList
	});
	
	option = {
		    tooltip : {
		        trigger: 'axis',
		        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
		            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
		        }
		    },
		    legend: {
		        data:result.classes
		    },
		    grid: {
		        left: '3%',
		        right: '4%',
		        bottom: '3%',
		        containLabel: true
		    },
		    xAxis : [
		        {
		            type : 'category',
		            data : result.days
		        }
		    ],
		    yAxis : [
		        {
		            type : 'value',
		            max:24,
		            interval:2
		        }
		    ],
		    series : series
		};
	
	// 基于准备好的dom，初始化echarts实例
	var myChart = echarts.init(document.getElementById('lostTimeReasonGraphBody'));
	// 使用刚指定的配置项和数据显示图表。
	myChart.setOption(option); 
}