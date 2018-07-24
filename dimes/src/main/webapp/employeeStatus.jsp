<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../common/jsp/head.jsp"%>
<script>
	$(function() {
		$.get("employeeSkill/queryEmployeeSkill.do",function(data){
			process(data);
		});
		$.get("employeeSkill/queryEmployeeSkill4emp.do",function(data){
			emp(data);
		});
		
		var time = new Date();
		$("#time").text(
				time.getFullYear() + "-" + (time.getMonth()+1) + "-"
						+ time.getDate());
	});
	
	//人员技能：人员
	function emp(data){
		var myChart = echarts.init(document.getElementById("rectangle"));

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
		        },
		        axisLabel : {
		        	color : '#8FA4BF'
				}
		    },
		    yAxis: {
		        type: 'category',
		        data: data.skillLevels,
		        splitArea: {
		            show: true
		        },
		        axisLabel : {
		        	color : '#8FA4BF'
				}
		    },
		    visualMap: {
		        min: 0,
		        max: 10,
		        calculable: true,
		        orient: 'horizontal',
		        left: 'center',
		        bottom: '15%',
		        textStyle:{
		        	color:'#8FA4BF'
		        }
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
		                shadowColor: 'rgba(116,132,157, 0.5)'
		            }
		        }
		    }]
		};
		 myChart.setOption(option);
	}
	
	//人员技能：工序
	function process(data){
		var myChart = echarts.init(document.getElementById("ring"));

		option = {
		    tooltip: {
		        trigger: 'item',
		        formatter: "{a} <br/>{b}: {c} ({d}%)"
		    } ,
		    legend: {
		        orient: 'vertical',
		        x: 'left',
		        data:data.names,
		        textStyle : {
					color : '#51E9FE'
				}
		    },
		    series: [
		        {
		            name:'工序/等级数',
		            type:'pie',
		            selectedMode: 'single',
		            radius: [0, '30%'],

		            label: {
		                normal: {
		                    position: 'inner'
		                }
		            },
		            labelLine: {
		                normal: {
		                    show: false
		                }
		            },
		            data:data.inner
		        },
		        {
		            name:'等级数/工序',
		            type:'pie',
		            radius: ['40%', '55%'],
		            label: {
		                normal: {
		                    formatter: '{a|{a}}{abg|}\n{hr|}\n  {b|{b}：}{c}  {per|{d}%}  ',
		                    backgroundColor: '#0000',
		                    borderColor: '#aaa',
		                    borderWidth: 1,
		                    borderRadius: 4,
		                    rich: {
		                        a: {
		                            color: '#999',
		                            lineHeight: 22,
		                            align: 'center'
		                        },
		                        hr: {
		                            borderColor: '#aaa',
		                            width: '100%',
		                            borderWidth: 0.5,
		                            height: 0
		                        },
		                        b: {
		                            fontSize: 16,
		                            lineHeight: 33
		                        },
		                        per: {
		                            color: '#eee',
		                            backgroundColor: '#334455',
		                            padding: [2, 4],
		                            borderRadius: 2
		                        }
		                    }
		                }
		            },
		            data:data.outer
		        }
		    ]
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
	color: #6062AB;
}

#time {
	width: 100%;
	height: 90px;
	text-align: center;
	line-height: 98px;
	color: #6062AB;
	font-size: 25px;
}

#middle {
	height: 800px;
	width: 90%;
	margin: auto auto;
}

#left {
	height: 100%;
	width: 49%;
	float: left;
	border:solid 1px gray;
}
#skill_ring{
	height:390px;
	width: 100%;
	background-color:red;
}
#skill_rectangle{
	height:390px;
	width: 100%;
}
#ring{
	width:100%;
	height:350px;
}
#rectangle{
	width:100%;
	height:350px;
}
#right {
	height: 100%;
	width: 49%;
	float: right;
	border:solid 1px gray;
}

.title {
	height: 40px;
	width: 100%;
	color: #6062AB;
	font-size: 30px;
	margin-left:10px;
	margin-top:5px;
}
</style>
</head>
<body>
	<div id="outer">
		<div id="blank"></div>
		<div id="title">人员管理状态</div>
		<div id="time"></div>

		<div id="middle">
			<div id="left">
				<div class="skill_ring">
					<div class="title">人员技能</div>
					<div id="ring"></div>
				</div>
				<div id="skill_rectangle">
					<div class="title"></div>
					<div id="rectangle"></div>
				</div>
			</div>
			<div id="right">
				<div class="title">在岗状态</div>
				<div id=""></div>
			</div>
		</div>
	</div>
</body>
</html>