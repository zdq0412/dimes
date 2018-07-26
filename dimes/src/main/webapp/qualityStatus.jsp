<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../common/jsp/head.jsp"%>
<script>
	$(function() {
		//一次性不合格率
		$.get("fractionDefective/queryFractionDefective.do",function(data){
			fractionDefective(data);
		});
		//报废率
		$.get("scraprate/queryScraprate.do",function(data){
			scraprate(data);
		});
		//质量日历
		$.get("qualityCalendarRecord/queryQualityCalendar.do",function(data){
			qualityCalendar(data);
		});
		//报废状态
		$.get("scrapStatus/queryScrapStatus.do",function(data){
			scrapStatus(data);
		});
		var time = new Date();
		$("#time").text(
				time.getFullYear() + "-" + (time.getMonth() + 1) + "-"
						+ time.getDate());
	});
	
	var pathes;
	var layouts;
	var colors;
	//质量日历
	function qualityCalendar(data){
		var myChart = echarts.init(document.getElementById("left_bottom"));
		 layouts = [
		    [[0, 0]],
		    [[-0.25, 0], [0.25, 0]],
		    [[0, -0.2], [-0.2, 0.2], [0.2, 0.2]],
		    [[-0.25, -0.25], [-0.25, 0.25], [0.25, -0.25], [0.25, 0.25]]
		];
		 pathes = 'M936.857805 523.431322c0 0-42.065715-68.89513-88.786739-68.89513-46.68416 0-95.732122 71.223091-95.732122 71.223091s-44.28544-72.503296-93.440922-71.152538c-35.565466 0.977306-62.89705 30.882406-79.124275 64.06615L579.773747 790.800797c-3.253248 37.391565-5.677568 50.904371-12.002816 69.63497-6.651802 19.698688-19.544883 35.227341-31.650099 45.909606-14.30231 12.621414-29.59831 22.066586-45.854208 27.424563-16.28969 5.362074-30.098739 6.496973-51.536794 6.496973-19.498906 0-36.95104-2.963456-52.395418-8.850534-15.410586-5.887078-28.420403-14.313984-39.034573-25.246003-10.613146-10.930995-18.757939-24.08151-24.435507-39.525171-5.676544-15.443763-8.532685-40.195482-8.532685-59.270963l0-26.232454 74.435273 0c0 24.644301-0.17705 64.452915 8.81408 77.006848 9.02697 12.515021 22.756147 18.092032 41.148826 18.791014 16.728678 0.636518 30.032179-8.061645 30.032179-8.061645s11.922022-10.5472 14.992077-19.756954c2.674995-8.025805 3.565363-22.180147 3.565363-22.180147s2.080461-21.789286 2.080461-34.234675L489.399906 514.299369c-16.678502-18.827776-43.801395-61.938688-82.756096-60.927693-54.699008 1.419366-100.422144 70.059622-100.422144 70.059622s-56.065126-70.059622-93.440922-70.059622c-37.376717 0-91.077939 70.059622-91.077939 70.059622S105.343488 156.737741 476.742042 119.363584l53.70327-74.714624 51.373056 74.714624C964.889395 142.740992 936.857805 523.431322 936.857805 523.431322z';
		colors = [
		     '#16B644', '#c4332b','#6862FD', '#FDC763'
		];
		
		var $names = $("#names");
		for(var i = 0;i<data.names.length;i++){
			var $div = $("<div style='margin:auto auto;'>");
			var $text = $("<p style='float:left;font-size:1.5em;' >");
			$text.append(data.names[i]);
			$div.append($text);
			var $color = $('<div style="float:left;height:20px;width:40px;background-color:' + colors[i] +';margin-right:20px;">');
			$div.append($color);
			
			$names.append($div);
		}
		
		option = {
		    tooltip: {
		    },
		    calendar: [{
		        left: 'center',
		        top: 'middle',
		        cellSize: [70, 29],
		        yearLabel: {show: false},
		        orient: 'vertical',
		        itemStyle:{
		        	color:'#1C2437'
		        },
		        dayLabel: {
		            firstDay: 1,
		            nameMap: 'cn',
		            color:'#8AD5DA'
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
		                    width: 10,
		                    height: 10
		                },
		                position: [
		                    cellPoint[0] + echarts.number.linearMap(itemLayout[0], [-0.5, 0.5], [-cellWidth / 2, cellWidth / 2]),
		                    cellPoint[1] + echarts.number.linearMap(itemLayout[1], [-0.5, 0.5], [-cellHeight / 2 + 20, cellHeight / 2])
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
	            y: cellPoint[1] - cellHeight / 2 + 10,
	            text: echarts.format.formatTime('dd', api.value(0)),
	            fill: '#777',
	            textFont: api.font({fontSize: 8})
	        }
	    });
	    return group;
	}
	//报废状态
	function scrapStatus(data){
		var myChart = echarts.init(document.getElementById("scrapStatus"));
		//报废数量
		var scrapCount = data.dataList;
		option = {
		    tooltip: {},
		    visualMap: {
		        max: 20,
		        inRange: {
		            color: ['#313695', '#4575b4', '#74add1', '#abd9e9', '#e0f3f8', '#ffffbf', '#fee090', '#fdae61', '#f46d43', '#d73027', '#a50026']
		        },
		        textStyle:{
		        	color:'#8FA4BF'
		        }
		    },
		    xAxis3D: {
		        type: 'category',
		        data: data.processes,
		        axisLabel:{
		        	color:'#8FA4BF'
		        },
		        nameTextStyle:{
		        	color:'#8FA4BF'
		        }
		    },
		    yAxis3D: {
		        type: 'category',
		        data: data.days,
		        axisLabel:{
		        	color:'#8FA4BF'
		        },
		        nameTextStyle:{
		        	color:'#8FA4BF'
		        }
		    },
		    zAxis3D: {
		        type: 'value',
		        axisLabel:{
		        	color:'#8FA4BF'
		        },
		        nameTextStyle:{
		        	color:'#8FA4BF'
		        }
		    },
		    grid3D: {
		        boxWidth: 150,
		        boxDepth: 80,
		        viewControl: {
		        },
		        light: {
		            main: {
		                intensity: 1.2,
		                shadow: true
		            },
		            ambient: {
		                intensity: 0.3
		            }
		        }
		    },
		    series: [{
		        type: 'bar3D',
		        data: scrapCount.map(function (item) {
		            return {
		                value: [item[0], item[1], item[2]],
		            }
		        }),
		        shading: 'lambert',

		        label: {
		            textStyle: {
		                fontSize: 16,
		                borderWidth: 1
		            }
		        },

		        emphasis: {
		            label: {
		                textStyle: {
		                    fontSize: 20,
		                    color: '#900'
		                }
		            },
		            itemStyle: {
		                color: '#900'
		            }
		        }
		    }]
		}
			    myChart.setOption(option);
	}
	//报废率
	function scraprate(data){
		var arr = new Array();
		for(var i = 0;i<data.ngTypeNames.length;i++){
			var position = 'middle';
			if(i==0){
				position = 'start';
			}
			if(i==data.ngTypeNames.length-1){
				position = "end";
			}
			var item = {
		            name:data.ngTypeNames[i],
		            type:'line',
		            step: position,
		            data:data.ppmMap[data.ngTypeNames[i]]
		        }
			
			arr.push(item);
		}
		var myChart = echarts.init(document.getElementById("left_middle"));
		option = {
			    /* title: {
			        text: '报废率',
			        textStyle:{
			        	color:'#8FA4BF'
			        }
			    }, */
			    tooltip: {
			        trigger: 'axis'
			    },
			    legend: {
			        data:data.ngTypeNames,
			        textStyle:{
			        	color:'#8FA4BF'
			        }
			    },
			    grid: {
			        left: '3%',
			        right: '4%',
			        bottom: '3%',
			        containLabel: true
			    },
			    toolbox: {
			        feature: {
			            saveAsImage: {}
			        }
			    },
			    xAxis: {
			        type: 'category',
			        data: data.monthList,
			        axisLabel:{
			        	color:'#8FA4BF'
			        }
			    },
			    yAxis: {
			        type: 'value',
			        axisLabel:{
			        	color:'#8FA4BF'
			        }
			    },
			    series: arr
			};

			    myChart.setOption(option);
	}
	//一次性不合格率
	function fractionDefective(data){
		var myCharts = echarts.init(document.getElementById("left_top"));
		var colors = ['#5793f3', '#d14a61', '#675bba'];
		option = {
		    color: colors,
		    tooltip: {
		        trigger: 'none',
		        axisPointer: {
		            type: 'cross'
		        }
		    },
		    legend: {
		        data:['上个月同期', '当月PPM'],
		        textStyle:{
		        	color:'#8FA4BF'
		        }
		    },
		    grid: {
		        top: 70,
		        bottom: 50
		    },
		    xAxis: [
		        {
		            type: 'category',
		            axisTick: {
		                alignWithLabel: true
		            },
		            axisLine: {
		                onZero: false,
		                lineStyle: {
		                    color: colors[1]
		                }
		            },
		            axisPointer: {
		                label: {
		                    formatter: function (params) {
		                        return 'PPM' + params.value
		                            + (params.seriesData.length ? '：' + params.seriesData[0].data : '');
		                    }
		                }
		            },
			        axisLabel:{
			        	color:'#8FA4BF'
			        },
		            data: data.thisMonth
		        },
		        {
		            type: 'category',
		            axisTick: {
		                alignWithLabel: true
		            },
		            axisLine: {
		                onZero: false,
		                lineStyle: {
		                    color: colors[0]
		                }
		            },
		            axisPointer: {
		                label: {
		                    formatter: function (params) {
		                        return 'PPM ' + params.value
		                            + (params.seriesData.length ? '：' + params.seriesData[0].data : '');
		                    }
		                }
		            },axisLabel:{
			        	color:'#8FA4BF'
			        },
		            data: data.preMonth
		        }
		    ],
		    yAxis: [
		        {
		            type: 'value',
		            axisLabel:{
			        	color:'#8FA4BF'
			        }
		        }
		    ],
		    series: [
		        {
		            name:'上个月同期',
		            type:'line',
		            xAxisIndex: 1,
		            smooth: true,
		            data: data.prePPMList
		        },
		        {
		            name:'当月PPM',
		            type:'line',
		            smooth: true,
		            data: data.thisPPMList
		        }
		    ]
		};
		
		myCharts.setOption(option);
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
#content{
	height: 850px;
	width: 90%;
	margin: auto auto;
}
#left {
	height: 100%;
	width: 33%;
	float: left;
	float: left;
}

#right {
	height: 100%;
	width: 66%;
	float: right;
	background-color: #1C2437;
	float: right;
}

.title {
	height: 40px;
	width: 100%;
	color: #57EEFD;
	font-size: 25px;
	margin-left: 10px;
	margin-top: 5px;
}
.leftThree{
	height:32%;
	width:100%;
	margin-bottom:15px;
	background-color: #1C2437;
}
.leftThreeContent{
	height:230px;
	width:100%;
}
#scrapStatus{
	height:810px;
	width:100%;
}
</style>
</head>
<body>
	<div id="outer">
		<div id="blank"></div>
		<div id="title">质量状态</div>
		<div id="time"></div>
		<div id="content">
			<div id="left">
				<div id="top" class="leftThree">
					<div class="title">一次不合格率</div>
					<div id="left_top" class="leftThreeContent"></div>
				</div>
				<div id="middle" class="leftThree">
					<div class="title">报废率</div>
					<div id="left_middle" class="leftThreeContent"></div>
				</div>
				<div id="bottom" class="leftThree" style="margin-bottom:0px;">
					<div class="title">质量日历</div>
					<div id="left_bottom" class="leftThreeContent"></div>
				</div>
			</div>
			<div id="right">
				<div class="title">报废状态</div>
				<div id="scrapStatus"></div>
			</div>
		</div>
	</div>
</body>
</html>